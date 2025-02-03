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
package org.jtrfp.jtrfp.qke.jfdt;

import java.io.BufferedInputStream;
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

/**
 * Read/Write parser for Terminal Reality QKE "quake" file.<br><br>
 * <table>
 * <caption>Compatibility testing</caption>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>Hellbender</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class QKEFile extends SelfParsingFile {
    int numGroundQuakes;
    private GroundQuakeEntry[] groundQuakeEntries;
    private int numBoxQuakes;
    private List<BoxQuakeEntry> boxQuakeEntries;

    public QKEFile(InputStream is) throws IOException, IllegalAccessException {
	super(new BufferedInputStream(is,1024));
    }
    
    public QKEFile() {}

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.ignoreEOF(false);
	try {p.expectString("-------- Ground Quake Count -------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	catch(UnrecognizedFormatException e) {
	    //Old TVF3 format.
	    oldFormat(p);
	    return;
	}
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numGroundQuakes", int.class), false);
	for (int i = 0; i < getNumGroundQuakes(); i++) {
	    p.expectString("------- Ground Quake Entry "+(i+1)+"-------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(
		    p.indexedProperty("groundQuakeEntries", GroundQuakeEntry.class, i),
		    ClassInclusion.classOf(GroundQuakeEntry.class));
	}//end for(i)
	p.expectString("-------- Box Quake Count --------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numBoxQuakes", int.class), false);
	for (int i = 0; i < getNumBoxQuakes(); i++) {
	    p.stringEndingWith("----\r\n", p.property("dummy", String.class), false);
	    //p.expectString("-------- Box Quake Entry "+(i+1)+" --------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(
		    p.indexedProperty("boxQuakeEntries", BoxQuakeEntry.class, i),
		    ClassInclusion.classOf(BoxQuakeEntry.class));
	}//end for(i)
    }// end describeFormat()
    
    private void oldFormat(Parser p) {
	//These are generally filled with zeroes and not used.
    }//end oldFormat(...)
    
    public String getDummy() {return "";}
    public void setDummy(String s) {}

    public static class GroundQuakeEntry implements ThirdPartyParseable {
	int sectorX0, sectorY0, qkeType;
	int sectorX1, sectorY1,sectorX2, sectorY2, layer;
	int unknown0, unknown1, unknown2, unknown3;
	int unknownSpeed, speed1, pause1, speed2, pause2;
	int unknown4, unknown5, unknown6, unknown7;
	String sound1;
	String sound2;
	String unknown8;
	String unknown9;
	String unknown10;
	// !--Additional quake info--
	int unknown11;

	@Override
	public void describeFormat(Parser p) throws UnrecognizedFormatException {
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("qkeType", int.class),
		    false);
	    p.stringCSVEndingWith("\r\n", int.class, false, "sectorX0","sectorY0");
	    p.stringCSVEndingWith("\r\n", int.class, false, "sectorX1","sectorY1","sectorX2","sectorY2","layer");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown0","unknown1","unknown2","unknown3");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknownSpeed","speed1","pause1","speed2","pause2");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown4","unknown5","unknown6","unknown7");
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("sound1", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("sound2", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown8", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown9", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown10", String.class), false);
	    p.expectString("!--Additional quake info--\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown11", int.class), false);
	}// end describeFormat()

	public int getSectorX0() {
	    return sectorX0;
	}

	public void setSectorX0(int sectorX0) {
	    this.sectorX0 = sectorX0;
	}

	public int getSectorY0() {
	    return sectorY0;
	}

	public void setSectorY0(int sectorY0) {
	    this.sectorY0 = sectorY0;
	}

	public int getSectorX1() {
	    return sectorX1;
	}

	public void setSectorX1(int sectorX1) {
	    this.sectorX1 = sectorX1;
	}

	public int getSectorY1() {
	    return sectorY1;
	}

	public void setSectorY1(int sectorY1) {
	    this.sectorY1 = sectorY1;
	}

	public int getSectorX2() {
	    return sectorX2;
	}

	public void setSectorX2(int sectorX2) {
	    this.sectorX2 = sectorX2;
	}

	public int getSectorY2() {
	    return sectorY2;
	}

	public void setSectorY2(int sectorY2) {
	    this.sectorY2 = sectorY2;
	}

	public int getLayer() {
	    return layer;
	}

	public void setLayer(int layer) {
	    this.layer = layer;
	}

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

	public int getUnknownSpeed() {
	    return unknownSpeed;
	}

	public void setUnknownSpeed(int unknownSpeed) {
	    this.unknownSpeed = unknownSpeed;
	}

	public int getSpeed1() {
	    return speed1;
	}

	public void setSpeed1(int speed1) {
	    this.speed1 = speed1;
	}

	public int getPause1() {
	    return pause1;
	}

	public void setPause1(int pause1) {
	    this.pause1 = pause1;
	}

	public int getSpeed2() {
	    return speed2;
	}

	public void setSpeed2(int speed2) {
	    this.speed2 = speed2;
	}

	public int getPause2() {
	    return pause2;
	}

	public void setPause2(int pause2) {
	    this.pause2 = pause2;
	}

	public int getUnknown4() {
	    return unknown4;
	}

	public void setUnknown4(int unknown4) {
	    this.unknown4 = unknown4;
	}

	public int getUnknown5() {
	    return unknown5;
	}

	public void setUnknown5(int unknown5) {
	    this.unknown5 = unknown5;
	}

	public int getUnknown6() {
	    return unknown6;
	}

	public void setUnknown6(int unknown6) {
	    this.unknown6 = unknown6;
	}

	public int getUnknown7() {
	    return unknown7;
	}

	public void setUnknown7(int unknown7) {
	    this.unknown7 = unknown7;
	}

	public String getSound1() {
	    return sound1;
	}

	public void setSound1(String sound1) {
	    this.sound1 = sound1;
	}

	public String getSound2() {
	    return sound2;
	}

	public void setSound2(String sound2) {
	    this.sound2 = sound2;
	}

	public String getUnknown8() {
	    return unknown8;
	}

	public void setUnknown8(String unknown8) {
	    this.unknown8 = unknown8;
	}

	public String getUnknown9() {
	    return unknown9;
	}

	public void setUnknown9(String unknown9) {
	    this.unknown9 = unknown9;
	}

	public String getUnknown10() {
	    return unknown10;
	}

	public void setUnknown10(String unknown10) {
	    this.unknown10 = unknown10;
	}

	public int getUnknown11() {
	    return unknown11;
	}

	public void setUnknown11(int unknown11) {
	    this.unknown11 = unknown11;
	}

	public int getQkeType() {
	    return qkeType;
	}

	public void setQkeType(int qkeType) {
	    this.qkeType = qkeType;
	}

    }// end QKEBlock

    /**
     * @return the numGroundQuakes
     */
    public int getNumGroundQuakes() {
	return numGroundQuakes;
    }

    /**
     * @param numGroundQuakes
     *            the numGroundQuakes to set
     */
    public void setNumGroundQuakes(int numGroundQuakes) {
	this.numGroundQuakes = numGroundQuakes;
    }

    public GroundQuakeEntry[] getGroundQuakeEntries() {
        return groundQuakeEntries;
    }

    public void setGroundQuakeEntries(GroundQuakeEntry[] entries) {
        this.groundQuakeEntries = entries;
    }

    public int getNumBoxQuakes() {
        return numBoxQuakes;
    }

    public void setNumBoxQuakes(int numBoxQuakes) {
        this.numBoxQuakes = numBoxQuakes;
    }
    
    public static class BoxQuakeEntry implements ThirdPartyParseable {
	private int unknown0;
	private int unknown1, unknown2;
	private int unknown3, unknown4, unknown5;
	private int unknown6, unknown7, unknown8, unknown9;
	private int unknown10,unknown11,unknown12,unknown13,unknown14;
	private int unknown15,unknown16,unknown17,unknown18,unknown19;
	String unknown20,unknown21,unknown22,unknown23,unknown24;
	
	private int unknownAddlQuakeInfo;
	private int unknownBoxQuakeSwitchInfo0;
	private String unknownBoxQuakeSwitchInfo1;
	private String unknownBoxQuakeSwitchInfo2;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown0", int.class), false);
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown1","unknown2");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown3","unknown4","unknown5");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown6","unknown7","unknown8","unknown9");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown10","unknown11","unknown12","unknown13","unknown14");
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown15","unknown16","unknown17","unknown18","unknown19");
	    
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown20", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown21", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown22", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown23", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknown24", String.class), false);
	    
	    try {
		p.expectString("!--Additional quake info--\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownAddlQuakeInfo", int.class), false);
	    }catch(UnrecognizedFormatException e) {}
	    try {
		p.expectString("@--Box quake switch info--\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownBoxQuakeSwitchInfo0", int.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownBoxQuakeSwitchInfo1", String.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownBoxQuakeSwitchInfo2", String.class), false);
	    }catch(UnrecognizedFormatException e) {}
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

	public int getUnknown5() {
	    return unknown5;
	}

	public void setUnknown5(int unknown5) {
	    this.unknown5 = unknown5;
	}

	public int getUnknown6() {
	    return unknown6;
	}

	public void setUnknown6(int unknown6) {
	    this.unknown6 = unknown6;
	}

	public int getUnknown7() {
	    return unknown7;
	}

	public void setUnknown7(int unknown7) {
	    this.unknown7 = unknown7;
	}

	public int getUnknown8() {
	    return unknown8;
	}

	public void setUnknown8(int unknown8) {
	    this.unknown8 = unknown8;
	}

	public int getUnknown9() {
	    return unknown9;
	}

	public void setUnknown9(int unknown9) {
	    this.unknown9 = unknown9;
	}

	public int getUnknown10() {
	    return unknown10;
	}

	public void setUnknown10(int unknown10) {
	    this.unknown10 = unknown10;
	}

	public int getUnknown11() {
	    return unknown11;
	}

	public void setUnknown11(int unknown11) {
	    this.unknown11 = unknown11;
	}

	public int getUnknown12() {
	    return unknown12;
	}

	public void setUnknown12(int unknown12) {
	    this.unknown12 = unknown12;
	}

	public int getUnknown13() {
	    return unknown13;
	}

	public void setUnknown13(int unknown13) {
	    this.unknown13 = unknown13;
	}

	public int getUnknown14() {
	    return unknown14;
	}

	public void setUnknown14(int unknown14) {
	    this.unknown14 = unknown14;
	}

	public int getUnknown15() {
	    return unknown15;
	}

	public void setUnknown15(int unknown15) {
	    this.unknown15 = unknown15;
	}

	public int getUnknown16() {
	    return unknown16;
	}

	public void setUnknown16(int unknown16) {
	    this.unknown16 = unknown16;
	}

	public int getUnknown17() {
	    return unknown17;
	}

	public void setUnknown17(int unknown17) {
	    this.unknown17 = unknown17;
	}

	public int getUnknown18() {
	    return unknown18;
	}

	public void setUnknown18(int unknown18) {
	    this.unknown18 = unknown18;
	}

	public int getUnknown19() {
	    return unknown19;
	}

	public void setUnknown19(int unknown19) {
	    this.unknown19 = unknown19;
	}

	public String getUnknown20() {
	    return unknown20;
	}

	public void setUnknown20(String unknown20) {
	    this.unknown20 = unknown20;
	}

	public String getUnknown21() {
	    return unknown21;
	}

	public void setUnknown21(String unknown21) {
	    this.unknown21 = unknown21;
	}

	public String getUnknown22() {
	    return unknown22;
	}

	public void setUnknown22(String unknown22) {
	    this.unknown22 = unknown22;
	}

	public String getUnknown23() {
	    return unknown23;
	}

	public void setUnknown23(String unknown23) {
	    this.unknown23 = unknown23;
	}

	public String getUnknown24() {
	    return unknown24;
	}

	public void setUnknown24(String unknown24) {
	    this.unknown24 = unknown24;
	}

	public int getUnknownAddlQuakeInfo() {
	    return unknownAddlQuakeInfo;
	}

	public void setUnknownAddlQuakeInfo(int unknownAddlQuakeInfo) {
	    this.unknownAddlQuakeInfo = unknownAddlQuakeInfo;
	}

	public int getUnknownBoxQuakeSwitchInfo0() {
	    return unknownBoxQuakeSwitchInfo0;
	}

	public void setUnknownBoxQuakeSwitchInfo0(int unknownBoxQuakeSwitchInfo0) {
	    this.unknownBoxQuakeSwitchInfo0 = unknownBoxQuakeSwitchInfo0;
	}

	public String getUnknownBoxQuakeSwitchInfo1() {
	    return unknownBoxQuakeSwitchInfo1;
	}

	public void setUnknownBoxQuakeSwitchInfo1(String unknownBoxQuakeSwitchInfo1) {
	    this.unknownBoxQuakeSwitchInfo1 = unknownBoxQuakeSwitchInfo1;
	}

	public String getUnknownBoxQuakeSwitchInfo2() {
	    return unknownBoxQuakeSwitchInfo2;
	}

	public void setUnknownBoxQuakeSwitchInfo2(String unknownBoxQuakeSwitchInfo2) {
	    this.unknownBoxQuakeSwitchInfo2 = unknownBoxQuakeSwitchInfo2;
	}
	
    }//end BoxQuakeEntry

    public List<BoxQuakeEntry> getBoxQuakeEntries() {
        return boxQuakeEntries;
    }

    public void setBoxQuakeEntries(List<BoxQuakeEntry> boxQuakeEntries) {
        this.boxQuakeEntries = boxQuakeEntries;
    }
}// end QKEFile
