/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.trn.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;

/**
 * Read/Write parses a Terminal Reality TRN file asset.
 * TRN files define a tournament in TRI racing games.
 * @author Chuck Ritola
 *
 */

public class TRNFile extends SelfParsingFile {
    private ArrayList<TrackEntry> trackEntries;
    private int numberOfTracks;
    private String tournamentName, tournamentDescriptionFile;
    private boolean hasDragRaceFlag;
    
    public TRNFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.expectString("== Tournament Name ==\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("tournamentName", String.class), false);
	p.expectString("== Tournament Description File ==\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("tournamentDescriptionFile", String.class), false);
	p.expectString("== Has Drag Race Flag ==\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("hasDragRaceFlag", boolean.class), false);
	p.expectString("== Number Of Tracks ==\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("numberOfTracks", int.class), false);
	
	for(int i = 0 ; i < getNumberOfTracks(); i++) {
	    p.expectString("== Track Entry #"+(i+1)+" ==\r\n", FailureBehavior.PANIC);
	    p.subParseProposedClasses(p.indexedProperty("trackEntries", TrackEntry.class, i), ClassInclusion.classOf(TrackEntry.class));
	}
    }//end describeFormat(..)
    
    public static class TrackEntry implements ThirdPartyParseable {
	private int unknown0;
	private String sitFileName;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("sitFileName", String.class), false);
	    p.stringEndingWith("\r\n", p.property("unknown0", int.class), false);
	}

	public int getUnknown0() {
	    return unknown0;
	}

	public void setUnknown0(int unknown0) {
	    this.unknown0 = unknown0;
	}

	public String getSitFileName() {
	    return sitFileName;
	}

	public void setSitFileName(String sitFileName) {
	    this.sitFileName = sitFileName;
	}
	
    }//end TrackEntry

    public ArrayList<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentDescriptionFile() {
        return tournamentDescriptionFile;
    }

    public void setTournamentDescriptionFile(String tournamentDescriptionFile) {
        this.tournamentDescriptionFile = tournamentDescriptionFile;
    }

    public boolean isHasDragRaceFlag() {
        return hasDragRaceFlag;
    }

    public void setHasDragRaceFlag(boolean hasDragRaceFlag) {
        this.hasDragRaceFlag = hasDragRaceFlag;
    }

    public void setTrackEntries(ArrayList<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

}//end TRNFile
