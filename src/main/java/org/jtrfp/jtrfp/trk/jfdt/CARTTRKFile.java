/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.trk.jfdt;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.TRParsers;
import org.jtrfp.jtrfp.jfdt.TripletDouble;

/**
 * Parses a CART Precision Racing .TRK file, which describes a <i>track</i>, not to be confused with
 * .TRK files in MTM and Evo, which describe <i>trucks</i>. Non-CART .TRK files should be parsed
 * using TRKFile<br><br>
 * 
 * <table>
 * <caption>Compatibility testing</caption>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>CART</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class CARTTRKFile extends SelfParsingFile {
    private int trackBlockCount;
    private double trackScale, trackLength;
    private List<TrackBlock> trackBlocks;
    private String trackBackground;
    
    public CARTTRKFile(InputStream is) throws IOException, IllegalAccessException {
	super(is);
    }

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.expectString("CRaceTrack.trackCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("trackBlockCount", int.class), false);
	
	p.expectString("CRaceTrack.trackBackground\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("trackBackground", String.class), false);
	
	p.expectString("CRaceTrack.scale\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("trackScale", double.class), false);
	
	p.expectString("CRaceTrack.length\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("trackLength", double.class), false);
	
	for(int i = 0; i < getTrackBlockCount(); i++)
	    p.subParseProposedClasses(p.indexedProperty("trackBlocks", TrackBlock.class, i), ClassInclusion.classOf(TrackBlock.class));
	
    }//end describeFormat(...)
    
    public static class TrackBlock implements ThirdPartyParseable {
	private int numPoints, numSegments, curveFlag;
	private double altitude, grade, interpGrade, width, interpWidth;
	private TripletDouble pFactor;
	private List<Integer> types, wallTypes;
	private List<TripletDouble> pList;
	private TripletDouble hFactor;
	private List<TextureEntry> textureEntries;
	private List<WallTextureEntry> wallTextureEntries;
	private List<Double> heightOffsets, pointOffsets;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("pointCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("numPoints", int.class), false);

	    p.expectString("segmentCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("numSegments", int.class), false);

	    p.expectString("curveFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("curveFlag", int.class), false);

	    p.expectString("p\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    
	    p.subParseProposedClasses(p.property("pFactor", TripletDouble.EndingWithNewline.class),
		    ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));

	    p.expectString("type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumSegments(); i++)
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("types", Integer.class, i), false);
	    
	    p.expectString("plist\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumPoints(); i++)
		p.subParseProposedClasses(p.indexedProperty("pList", TripletDouble.EndingWithNewline.class, i), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    
	    p.expectString("!texture\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumSegments(); i++)
		p.subParseProposedClasses(p.indexedProperty("textureEntries", TextureEntry.class, i), ClassInclusion.classOf(TextureEntry.class));
	    
	    p.expectString("wallType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumPoints(); i++) 
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("wallTypes", Integer.class, i), false);
	    
	    p.expectString("wallTexture\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumPoints(); i++)
		p.subParseProposedClasses(p.indexedProperty("wallTextureEntries", WallTextureEntry.class, i), ClassInclusion.classOf(WallTextureEntry.class));
	    
	    p.expectString("h\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("hFactor", TripletDouble.EndingWithNewline.class),
		    ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    p.expectString("pointOffset\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumPoints(); i++)
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("pointOffsets", Double.class, i), false);
	    
	    p.expectString("!altitude\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("altitude", double.class), false);

	    p.expectString("grade\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("grade", double.class), false);

	    p.expectString("%interpGrade\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("interpGrade", double.class), false);

	    p.expectString("$width,interpWidth\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("width", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("interpWidth", double.class), false);

	    p.expectString("^heightOffset\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumPoints(); i++)
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("heightOffsets", Double.class, i), false);
	    
	}//end describeFormat(...)

	public int getNumPoints() {
	    return numPoints;
	}

	public void setNumPoints(int numPoints) {
	    this.numPoints = numPoints;
	}

	public int getNumSegments() {
	    return numSegments;
	}

	public void setNumSegments(int numSegments) {
	    this.numSegments = numSegments;
	}

	public int getCurveFlag() {
	    return curveFlag;
	}

	public void setCurveFlag(int curveFlag) {
	    this.curveFlag = curveFlag;
	}

	public double getAltitude() {
	    return altitude;
	}

	public void setAltitude(double altitude) {
	    this.altitude = altitude;
	}

	public double getGrade() {
	    return grade;
	}

	public void setGrade(double grade) {
	    this.grade = grade;
	}

	public double getInterpGrade() {
	    return interpGrade;
	}

	public void setInterpGrade(double interpGrade) {
	    this.interpGrade = interpGrade;
	}

	public double getWidth() {
	    return width;
	}

	public void setWidth(double width) {
	    this.width = width;
	}

	public double getInterpWidth() {
	    return interpWidth;
	}

	public void setInterpWidth(double interpWidth) {
	    this.interpWidth = interpWidth;
	}

	public TripletDouble getPFactor() {
	    return pFactor;
	}

	public void setPFactor(TripletDouble pFactor) {
	    this.pFactor = pFactor;
	}

	public List<Integer> getTypes() {
	    return types;
	}

	public void setTypes(List<Integer> types) {
	    this.types = types;
	}

	public List<Integer> getWallTypes() {
	    return wallTypes;
	}

	public void setWallTypes(List<Integer> wallTypes) {
	    this.wallTypes = wallTypes;
	}

	public List<Double> getPointOffsets() {
	    return pointOffsets;
	}

	public void setPointOffsets(List<Double> pointOffsets) {
	    this.pointOffsets = pointOffsets;
	}

	public List<TripletDouble> getPList() {
	    return pList;
	}

	public void setPList(List<TripletDouble> pList) {
	    this.pList = pList;
	}

	public TripletDouble getHFactor() {
	    return hFactor;
	}

	public void setHFactor(TripletDouble hFactor) {
	    this.hFactor = hFactor;
	}

	public List<TextureEntry> getTextureEntries() {
	    return textureEntries;
	}

	public void setTextureEntries(List<TextureEntry> textureEntries) {
	    this.textureEntries = textureEntries;
	}

	public List<WallTextureEntry> getWallTextureEntries() {
	    return wallTextureEntries;
	}

	public void setWallTextureEntries(
		List<WallTextureEntry> wallTextureEntries) {
	    this.wallTextureEntries = wallTextureEntries;
	}

	public List<Double> getHeightOffsets() {
	    return heightOffsets;
	}

	public void setHeightOffsets(List<Double> heightOffsets) {
	    this.heightOffsets = heightOffsets;
	}
	
    }//end TrackBlock
    
    public static class WallTextureEntry implements ThirdPartyParseable {
	private int unknown0, unknown1, unknown2, unknown3;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("unknown0", int.class), false);
	    p.stringEndingWith(",", p.property("unknown1", int.class), false);
	    p.stringEndingWith(",", p.property("unknown2", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown3", int.class), false);
	}//end describeFormat(...)

	public int getUnknown0() {
	    return unknown0;
	}

	public void setUnknown0(int unknown0) {
	    this.unknown0 = unknown0;
	}

	public int getUnknown1() {
	    return unknown1;
	}

	public void setUnknown1(int unknown1) {
	    this.unknown1 = unknown1;
	}

	public int getUnknown2() {
	    return unknown2;
	}

	public void setUnknown2(int unknown2) {
	    this.unknown2 = unknown2;
	}

	public int getUnknown3() {
	    return unknown3;
	}

	public void setUnknown3(int unknown3) {
	    this.unknown3 = unknown3;
	}
    }//end WallTextureEntry
    
    public static class TextureEntry implements ThirdPartyParseable {
	private int unknown0, unknown1, unknown2, unknown3, unknown4;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("unknown0", int.class), false);
	    p.stringEndingWith(",", p.property("unknown1", int.class), false);
	    p.stringEndingWith(",", p.property("unknown2", int.class), false);
	    p.stringEndingWith(",", p.property("unknown3", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown4", int.class), false);
	}//end describeFormat(...)

	public int getUnknown0() {
	    return unknown0;
	}

	public void setUnknown0(int unknown0) {
	    this.unknown0 = unknown0;
	}

	public int getUnknown1() {
	    return unknown1;
	}

	public void setUnknown1(int unknown1) {
	    this.unknown1 = unknown1;
	}

	public int getUnknown2() {
	    return unknown2;
	}

	public void setUnknown2(int unknown2) {
	    this.unknown2 = unknown2;
	}

	public int getUnknown3() {
	    return unknown3;
	}

	public void setUnknown3(int unknown3) {
	    this.unknown3 = unknown3;
	}

	public int getUnknown4() {
	    return unknown4;
	}

	public void setUnknown4(int unknown4) {
	    this.unknown4 = unknown4;
	}
	
    }//end ThirdPartyParseable

    public int getTrackBlockCount() {
        return trackBlockCount;
    }

    public void setTrackBlockCount(int trackBlockCount) {
        this.trackBlockCount = trackBlockCount;
    }

    public double getTrackScale() {
        return trackScale;
    }

    public void setTrackScale(double trackScale) {
        this.trackScale = trackScale;
    }

    public double getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(double trackLength) {
        this.trackLength = trackLength;
    }

    public List<TrackBlock> getTrackBlocks() {
        return trackBlocks;
    }

    public void setTrackBlocks(List<TrackBlock> trackBlocks) {
        this.trackBlocks = trackBlocks;
    }

    public String getTrackBackground() {
        return trackBackground;
    }

    public void setTrackBackground(String trackBackground) {
        this.trackBackground = trackBackground;
    }

}//end CARTTRKFile
