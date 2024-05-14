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
package org.jtrfp.jtrfp.nav.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.Location3D;
import org.jtrfp.jtrfp.jfdt.TRParsers;

/**
 * Read/Write parser for Terminal Reality NAV asset file, Terminal Velocity and Fury3 formats only.
 * See HellbenderNAVFile for Hellbender format. These files are used
 * as internal delegates of NAVFile.
 * @author Chuck Ritola
 *
 */

public class TVF3NAVFile extends SelfParsingFile implements NAVData {
    int numNavigationPoints;
    List<? extends NAVSubObjectData> navObjects;
    
    public TVF3NAVFile(InputStream sourceStream) throws IOException, IllegalAccessException {
	super(new BufferedInputStream(sourceStream,1024));
    }
    
    public TVF3NAVFile() {}

    @Override
    public void describeFormat(Parser prs) throws UnrecognizedFormatException {
	prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		prs.property("numNavigationPoints", int.class), false);
	for (int i = 0; i < getNumNavigationPoints(); i++)
	    prs.subParseProposedClasses(
		    prs.indexedProperty("navObjects", NAVSubObject.class, i),
		    ClassInclusion.nestedClassesOf(TVF3NAVFile.class));
    }// end describeFormat()

    public static abstract class NAVSubObject implements ThirdPartyParseable, NAVSubObjectData {
	// int type;
	Location3D locationOnMap;
	String description;

	/**
	 * @return the locationOnMap
	 */
	public Location3D getLocationOnMap() {
	    return locationOnMap;
	}

	/**
	 * @param locationOnMap
	 *            the locationOnMap to set
	 */
	public void setLocationOnMap(Location3D locationOnMap) {
	    this.locationOnMap = locationOnMap;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
	    return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
	    this.description = description;
	}
	
	@Override
	public String toString() {
	    return super.toString()+" loc="+locationOnMap.toString()+" desc="+description;
	}
	
	public int getPriority() {return 0;}
	public void setPriority(int newPriority) {}
	public String getProximitySoundFile() {return null;}
	public void setProximitySoundFile(String newPriority) {}
	public String getCompletionText() {return null;}
	public void setCompletionText(String text) {throw new UnsupportedOperationException("TV/F3 does not use completion text in its NAV objectives.");}
	public void setCompletionTextFile(String newPriority) {}
	public String getCompletionSoundFile() {return null;}
	public void setCompletionSoundFile(String newPriority) {}
    }// end NAVSubObject

    public static class START extends NAVSubObject implements NAVData.StartingPoint {
	int pitch, roll, yaw;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("6\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    // prs.subParse("locationOnMap", Location3D.class);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	    prs.stringCSVEndingWith("\r\n", int.class, false, "pitch", "roll",
		    "yaw");
	}

	/**
	 * @return the pitch
	 */
	public int getPitch() {
	    return pitch;
	}

	/**
	 * @param pitch
	 *            the pitch to set
	 */
	public void setPitch(int pitch) {
	    this.pitch = pitch;
	}

	/**
	 * @return the roll
	 */
	public int getRoll() {
	    return roll;
	}

	/**
	 * @param roll
	 *            the roll to set
	 */
	public void setRoll(int roll) {
	    this.roll = roll;
	}

	/**
	 * @return the yaw
	 */
	public int getYaw() {
	    return yaw;
	}

	/**
	 * @param yaw
	 *            the yaw to set
	 */
	public void setYaw(int yaw) {
	    this.yaw = yaw;
	}

    }// end CHK

    public static class BOS extends NAVSubObject implements NAVData.Boss {
	int bossIndex;
	String musicFile;
	String unused;
	// !NewH
	int numTargets;
	int[] targets;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("5\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("bossIndex", int.class),
		    false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("musicFile", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused", String.class),
		    false);
	    prs.expectString("!NewH\r\n", FailureBehavior.IGNORE);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("numTargets", int.class),
		    false);
	    for (int i = 0; i < getNumTargets(); i++) {
		prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
			prs.indexedProperty("targets", int.class, i), false);
	    }
	}

	/**
	 * @return the bossIndex
	 */
	public int getBossIndex() {
	    return bossIndex;
	}

	/**
	 * @param bossIndex
	 *            the bossIndex to set
	 */
	public void setBossIndex(int bossIndex) {
	    this.bossIndex = bossIndex;
	}

	/**
	 * @return the musicFile
	 */
	public String getMusicFile() {
	    return musicFile;
	}

	/**
	 * @param musicFile
	 *            the musicFile to set
	 */
	public void setMusicFile(String musicFile) {
	    this.musicFile = musicFile;
	}

	/**
	 * @return the unused
	 */
	public String getUnused() {
	    return unused;
	}

	/**
	 * @param unused
	 *            the unused to set
	 */
	public void setUnused(String unused) {
	    this.unused = unused;
	}

	/**
	 * @return the numTargets
	 */
	public int getNumTargets() {
	    return numTargets;
	}

	/**
	 * @param numTargets
	 *            the numTargets to set
	 */
	public void setNumTargets(int numTargets) {
	    this.numTargets = numTargets;
	}

	/**
	 * @return the targets
	 */
	public int[] getTargets() {
	    return targets;
	}

	/**
	 * @param targets
	 *            the targets to set
	 */
	public void setTargets(int[] targets) {
	    this.targets = targets;
	}

    }// end CHK

    public static class XIT extends NAVSubObject implements NAVData.ExitTunnel {
	String unused1, unused2;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("4\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    System.out.println("Found XIT");
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused1", String.class),
		    false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused2", String.class),
		    false);
	}

	/**
	 * @return the unused1
	 */
	public String getUnused1() {
	    return unused1;
	}

	/**
	 * @param unused1
	 *            the unused1 to set
	 */
	public void setUnused1(String unused1) {
	    this.unused1 = unused1;
	}

	/**
	 * @return the unused2
	 */
	public String getUnused2() {
	    return unused2;
	}

	/**
	 * @param unused2
	 *            the unused2 to set
	 */
	public void setUnused2(String unused2) {
	    this.unused2 = unused2;
	}
    }// end XIT

    public static class DUN extends NAVSubObject implements NAVData.Jumpzone {
	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("3\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	}

    }// end DUN

    public static class CHK extends NAVSubObject implements NAVData.Checkpoint {
	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("2\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	}

    }// end CHK

    public static class TAG extends NAVSubObject {
	private String tagName;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString(";", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("tagName", String.class),
		    false);
	}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
	    return tagName;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
	    this.tagName = tagName;
	}
    }// end TAG

    public static class TUN extends NAVSubObject implements NAVData.EnterTunnel {
	private String unused;
	private String tunnelFileName;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("1\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("tunnelFileName", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused", String.class),
		    false);
	}

	/**
	 * @return the unused
	 */
	public String getUnused() {
	    return unused;
	}

	/**
	 * @param unused
	 *            the unused to set
	 */
	public void setUnused(String unused) {
	    this.unused = unused;
	}

	/**
	 * @return the tunnelFileName
	 */
	public String getTunnelFileName() {
	    return tunnelFileName;
	}

	/**
	 * @param tunnelFileName
	 *            the tunnelFileName to set
	 */
	public void setTunnelFileName(String tunnelFileName) {
	    this.tunnelFileName = tunnelFileName;
	}

    }// end TUN

    public static class TGT extends NAVSubObject implements NAVData.DestroyTarget {
	int numTargets;
	int[] targets;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.expectString("0\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    prs.subParseProposedClasses(
		    prs.property("locationOnMap", Location3D.class),
		    ClassInclusion.classOf(Location3D.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("description", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("numTargets", int.class),
		    false);
	    for (int i = 0; i < getNumTargets(); i++) {
		prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
			prs.indexedProperty("targets", int.class, i), false);
	    }
	}// end descriptionFormat()

	/**
	 * @return the numTargets
	 */
	public int getNumTargets() {
	    return numTargets;
	}

	/**
	 * @param numTargets
	 *            the numTargets to set
	 */
	public void setNumTargets(int numTargets) {
	    this.numTargets = numTargets;
	}

	/**
	 * @return the targets
	 */
	public int[] getTargets() {
	    return targets;
	}

	/**
	 * @param targets
	 *            the targets to set
	 */
	public void setTargets(int[] targets) {
	    this.targets = targets;
	}

    }// end NAVSubObject

    /**
     * @return the numNavigationPoints
     */
    public int getNumNavigationPoints() {
	return numNavigationPoints;
    }

    /**
     * @param numNavigationPoints
     *            the numNavigationPoints to set
     */
    public void setNumNavigationPoints(int numNavigationPoints) {
	this.numNavigationPoints = numNavigationPoints;
    }

    /**
     * @param navObjects
     *            the navObjects to set
     */
    public void setNavObjects(ArrayList<NAVSubObject> navObjects) {
	this.navObjects = navObjects;
    }

    public List<? extends NAVSubObjectData> getNavObjects() {
        return navObjects;
    }

    public void setNavObjects(List<? extends NAVSubObjectData> navObjects) {
        this.navObjects = navObjects;
    }

}// end NAVFile
