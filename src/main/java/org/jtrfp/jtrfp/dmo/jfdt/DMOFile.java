/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.dmo.jfdt;

import java.io.BufferedInputStream;
import java.io.EOFException;
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
 * Parses a Terminal Reality DMO file asset. DMO files (demo files) contain
 * player actions for running simulated demos of the game when not in actual play.
 * @author Chuck Ritola
 *
 */

public class DMOFile extends SelfParsingFile {
    private int numEntries;
    private String lvlFile;
    private ArrayList<DMOEntry> dmoEntries;
    
    public DMOFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    @Override
    public void describeFormat(Parser p)
	    throws UnrecognizedFormatException {
	try {p.stringEndingWith("\r\n", p.property("numEntries", int.class), false);}
	catch(UnrecognizedFormatException e) {
	    if(e.getCause() instanceof EOFException)//Some of these files are empty 
		return; else throw e;
	    }//end catch{UnrecognizedFormatException}
	p.stringEndingWith("\r\n", p.property("lvlFile", String.class), false);
	
	for(int i = 0 ; i < getNumEntries(); i++) {
	    try {p.subParseProposedClasses(p.indexedProperty("dmoEntries", DMOEntry.class, i), ClassInclusion.classOf(PositionalDMOEntry.class));}
	    catch(UnrecognizedFormatException e) {
		p.subParseProposedClasses(p.indexedProperty("dmoEntries", DMOEntry.class, i), ClassInclusion.classOf(KeyEventDMOEntry.class));
	    }
	}//end for(i)
    }//end describeFormat(...)
    
    public static abstract class DMOEntry implements ThirdPartyParseable {
	private int entryType, timeOffset;

	public int getEntryType() {
	    return entryType;
	}

	public void setEntryType(int entryType) {
	    this.entryType = entryType;
	}

	public int getTimeOffset() {
	    return timeOffset;
	}

	public void setTimeOffset(int timeOffset) {
	    this.timeOffset = timeOffset;
	}
    }//end DMOEntry
    
    public static class PositionalDMOEntry extends DMOEntry {
	private int x, y, z, pitch, roll, yaw, unknown0;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("0,", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("timeOffset", int.class), false);
	    p.stringCSVEndingWith("\r\n", int.class, false, "x","y","z");
	    p.stringCSVEndingWith("\r\n", int.class, false, "pitch", "roll", "yaw", "unknown0");
	}

	public int getX() {
	    return x;
	}

	public void setX(int x) {
	    this.x = x;
	}

	public int getY() {
	    return y;
	}

	public void setY(int y) {
	    this.y = y;
	}

	public int getZ() {
	    return z;
	}

	public void setZ(int z) {
	    this.z = z;
	}

	public int getPitch() {
	    return pitch;
	}

	public void setPitch(int pitch) {
	    this.pitch = pitch;
	}

	public int getRoll() {
	    return roll;
	}

	public void setRoll(int roll) {
	    this.roll = roll;
	}

	public int getYaw() {
	    return yaw;
	}

	public void setYaw(int yaw) {
	    this.yaw = yaw;
	}

	public int getUnknown0() {
	    return unknown0;
	}

	public void setUnknown0(int unknown0) {
	    this.unknown0 = unknown0;
	}
	
    }//end NoKeypressDMOEntry
    
    public static class KeyEventDMOEntry extends DMOEntry {//Keypress entries are longer
	private int keyNumber;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("1,", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("timeOffset", int.class), false);
	    p.stringEndingWith("\r\n", p.property("keyNumber", int.class), false);
	}//end describeFormat(...)

	public int getKeyNumber() {
	    return keyNumber;
	}

	public void setKeyNumber(int keyNumber) {
	    this.keyNumber = keyNumber;
	}
	
    }//end KeyEventDMOEntry

    public int getNumEntries() {
        return numEntries;
    }

    public void setNumEntries(int numEntries) {
        this.numEntries = numEntries;
    }

    public String getLvlFile() {
        return lvlFile;
    }

    public void setLvlFile(String lvlFile) {
        this.lvlFile = lvlFile;
    }

    public ArrayList<DMOEntry> getDmoEntries() {
        return dmoEntries;
    }

    public void setDmoEntries(ArrayList<DMOEntry> dmoEntries) {
        this.dmoEntries = dmoEntries;
    }

}//end DMOFile
