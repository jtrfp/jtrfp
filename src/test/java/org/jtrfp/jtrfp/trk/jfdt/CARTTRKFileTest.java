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

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class CARTTRKFileTest extends AbstractParserTest<CARTTRKFile> {

    @Test
    public void testCARTTRKFile() {
	assertNotNull(subject);
    }

    @Test
    public void testGetTrackBlockCount() {
	assertEquals(2,subject.getTrackBlockCount());
    }

    @Test
    public void testGetTrackScale() {
	assertEquals(1.2345, subject.getTrackScale());
    }

    @Test
    public void testGetTrackLength() {
	assertEquals(6.7890, subject.getTrackLength());
    }

    @Test
    public void testGetTrackBlocks() {
	assertNotNull(subject.getTrackBlocks());
    }

    @Test
    public void testGetTrackBackground() {
	assertEquals("background.RAW",subject.getTrackBackground());
    }
    
    //// TRACK BLOCK 0
    @Test
    public void testTrackBlock0() {
	assertNotNull(subject.getTrackBlocks().get(0));
    }
    
    @Test
    public void testTrackBlock0NumSegments() {
	assertEquals(2,subject.getTrackBlocks().get(0).getNumSegments());
    }
    
    @Test
    public void testTrackBlock0CurveFlag() {
	assertEquals(3,subject.getTrackBlocks().get(0).getCurveFlag());
    }
    
    @Test
    public void testTrackBlock0PFactorX() {
	assertEquals(11.1,subject.getTrackBlocks().get(0).getPFactor().getX());
    }
    
    @Test
    public void testTrackBlock0PFactorY() {
	assertEquals(22.2,subject.getTrackBlocks().get(0).getPFactor().getY());
    }
    
    @Test
    public void testTrackBlock0PFactorZ() {
	assertEquals(33.3,subject.getTrackBlocks().get(0).getPFactor().getZ());
    }
    
    //// TYPES
    
    @Test
    public void testTrackBlock0GetTypes() {
	assertNotNull(subject.getTrackBlocks().get(0).getTypes());
    }
    
    @Test
    public void testTrackBlock0GetTypes0() {
	assertEquals((Integer)4,subject.getTrackBlocks().get(0).getTypes().get(0));
    }
    
    @Test
    public void testTrackBlock0GetTypes1() {
	assertEquals((Integer)5,subject.getTrackBlocks().get(0).getTypes().get(1));
    }
    
    //// P LIST
    
    @Test
    public void testTrackBlock0GetPList() {
	assertNotNull(subject.getTrackBlocks().get(0).getPList());
    }
    
    @Test
    public void testTrackBlock0GetPList0X() {
	assertEquals(444.4,subject.getTrackBlocks().get(0).getPList().get(0).getX());
    }
    
    @Test
    public void testTrackBlock0GetPList0Y() {
	assertEquals(555.5,subject.getTrackBlocks().get(0).getPList().get(0).getY());
    }
    
    @Test
    public void testTrackBlock0GetPList0Z() {
	assertEquals(666.6,subject.getTrackBlocks().get(0).getPList().get(0).getZ());
    }
    
    @Test
    public void testTrackBlock0GetPList1X() {
	assertEquals(777.7,subject.getTrackBlocks().get(0).getPList().get(1).getX());
    }
    
    @Test
    public void testTrackBlock0GetPList1Y() {
	assertEquals(888.8,subject.getTrackBlocks().get(0).getPList().get(1).getY());
    }
    
    @Test
    public void testTrackBlock0GetPList1Z() {
	assertEquals(999.9,subject.getTrackBlocks().get(0).getPList().get(1).getZ());
    }
    
    //// TEXTURE BLOCK
    @Test
    public void testTrackBlock0GetTextureEntries() {
	assertNotNull(subject.getTrackBlocks().get(0).getTextureEntries());
    }
    
    @Test
    public void testTrackBlock0GetTextureEntry0Unknown0() {
	assertEquals(1,subject.getTrackBlocks().get(0).getTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testTrackBlock0GetTextureEntry0Unknown1() {
	assertEquals(2,subject.getTrackBlocks().get(0).getTextureEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testTrackBlock0GetTextureEntry0Unknown2() {
	assertEquals(3,subject.getTrackBlocks().get(0).getTextureEntries().get(0).getUnknown2());
    }
    
    @Test
    public void testTrackBlock0GetTextureEntry0Unknown3() {
	assertEquals(4,subject.getTrackBlocks().get(0).getTextureEntries().get(0).getUnknown3());
    }
    
    @Test
    public void testTrackBlock0GetTextureEntry0Unknown4() {
	assertEquals(5,subject.getTrackBlocks().get(0).getTextureEntries().get(0).getUnknown4());
    }
    
    //WALL TYPE
    
    @Test
    public void testTrackBlock0GetWallType0() {
	assertEquals((Integer)0,subject.getTrackBlocks().get(0).getWallTypes().get(0));
    }
    @Test
    public void testTrackBlock0GetWallType1() {
	assertEquals((Integer)1,subject.getTrackBlocks().get(0).getWallTypes().get(1));
    }
    
    //WALL TEXTURE
    
    @Test
    public void testTrackBlock0GetWallTexture0Unknown0() {
	assertEquals(2,subject.getTrackBlocks().get(0).getWallTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture0Unknown1() {
	assertEquals(3,subject.getTrackBlocks().get(0).getWallTextureEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture0Unknown2() {
	assertEquals(4,subject.getTrackBlocks().get(0).getWallTextureEntries().get(0).getUnknown2());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture0Unknown3() {
	assertEquals(5,subject.getTrackBlocks().get(0).getWallTextureEntries().get(0).getUnknown3());
    }
    //
    @Test
    public void testTrackBlock0GetWallTexture1Unknown0() {
	assertEquals(6,subject.getTrackBlocks().get(0).getWallTextureEntries().get(1).getUnknown0());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture1Unknown1() {
	assertEquals(7,subject.getTrackBlocks().get(0).getWallTextureEntries().get(1).getUnknown1());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture1Unknown2() {
	assertEquals(8,subject.getTrackBlocks().get(0).getWallTextureEntries().get(1).getUnknown2());
    }
    
    @Test
    public void testTrackBlock0GetWallTexture1Unknown3() {
	assertEquals(9,subject.getTrackBlocks().get(0).getWallTextureEntries().get(1).getUnknown3());
    }
    
    //// H FACTOR
    @Test
    public void testTrackBlock0GetHFactorX() {
	assertEquals(-1.1,subject.getTrackBlocks().get(0).getHFactor().getX());
    }
    
    @Test
    public void testTrackBlock0GetHFactorY() {
	assertEquals(2.2,subject.getTrackBlocks().get(0).getHFactor().getY());
    }
    
    @Test
    public void testTrackBlock0GetHFactor() {
	assertEquals(-3.3,subject.getTrackBlocks().get(0).getHFactor().getZ());
    }
    
    //// POINT OFFSETS
    
    @Test
    public void testTrackBlock0GetPointOffset0() {
	assertEquals(-111.1,subject.getTrackBlocks().get(0).getPointOffsets().get(0));
    }
    
    @Test
    public void testTrackBlock0GetPointOffset1() {
	assertEquals(222.2,subject.getTrackBlocks().get(0).getPointOffsets().get(1));
    }
    
    //// ALTITUDE, GRADE, INTERPGRADE, WIDTH, INTERPWIDTH
    
    @Test
    public void testTrackBlock0GetAltitude() {
	assertEquals(333.3,subject.getTrackBlocks().get(0).getAltitude());
    }
    
    @Test
    public void testTrackBlock0GetGrade() {
	assertEquals(4.44,subject.getTrackBlocks().get(0).getGrade());
    }
    
    @Test
    public void testTrackBlock0GetInterpGrade() {
	assertEquals(5.55,subject.getTrackBlocks().get(0).getInterpGrade());
    }
    
    @Test
    public void testTrackBlock0GetWidth() {
	assertEquals(6.6,subject.getTrackBlocks().get(0).getWidth());
    }
    
    @Test
    public void testTrackBlock0GetInterpWidth() {
	assertEquals(7.7,subject.getTrackBlocks().get(0).getInterpWidth());
    }
    
    //// HEIGHT OFFSET
    @Test
    public void testTrackBlock0GetHeightOffset0() {
	assertEquals(8.8,subject.getTrackBlocks().get(0).getHeightOffsets().get(0));
    }
    
    @Test
    public void testTrackBlock0GetHeightOffset1() {
	assertEquals(9.9,subject.getTrackBlocks().get(0).getHeightOffsets().get(1));
    }
    
    //// TRACK BLOCK 1
    
    @Test
    public void testTrackBlock1() {
	assertNotNull(subject.getTrackBlocks().get(1));
    }
    
    @Test
    public void testTrackBlock1NumSegments() {
	assertEquals(2,subject.getTrackBlocks().get(1).getNumSegments());
    }
    
    @Test
    public void testTrackBlock1CurveFlag() {
	assertEquals(1,subject.getTrackBlocks().get(1).getCurveFlag());
    }
    
    @Test
    public void testTrackBlock1PFactorX() {
	assertEquals(2.2,subject.getTrackBlocks().get(1).getPFactor().getX());
    }
    
    @Test
    public void testTrackBlock1PFactorY() {
	assertEquals(3.3,subject.getTrackBlocks().get(1).getPFactor().getY());
    }
    
    @Test
    public void testTrackBlock1PFactorZ() {
	assertEquals(4.4,subject.getTrackBlocks().get(1).getPFactor().getZ());
    }
    
    //// TYPES
    
    @Test
    public void testTrackBlock1GetTypes() {
	assertNotNull(subject.getTrackBlocks().get(1).getTypes());
    }
    
    @Test
    public void testTrackBlock1GetTypes0() {
	assertEquals((Integer)5,subject.getTrackBlocks().get(1).getTypes().get(0));
    }
    
    @Test
    public void testTrackBlock1GetTypes1() {
	assertEquals((Integer)6,subject.getTrackBlocks().get(1).getTypes().get(1));
    }
    
    //// P LIST
    
    @Test
    public void testTrackBlock1GetPList() {
	assertNotNull(subject.getTrackBlocks().get(1).getPList());
    }
    
    @Test
    public void testTrackBlock1GetPList0X() {
	assertEquals(5.5,subject.getTrackBlocks().get(1).getPList().get(0).getX());
    }
    
    @Test
    public void testTrackBlock1GetPList0Y() {
	assertEquals(6.6,subject.getTrackBlocks().get(1).getPList().get(0).getY());
    }
    
    @Test
    public void testTrackBlock1GetPList0Z() {
	assertEquals(7.7,subject.getTrackBlocks().get(1).getPList().get(0).getZ());
    }
    
    @Test
    public void testTrackBlock1GetPList1X() {
	assertEquals(8.8,subject.getTrackBlocks().get(1).getPList().get(1).getX());
    }
    
    @Test
    public void testTrackBlock1GetPList1Y() {
	assertEquals(9.9,subject.getTrackBlocks().get(1).getPList().get(1).getY());
    }
    
    @Test
    public void testTrackBlock1GetPList1Z() {
	assertEquals(0.0,subject.getTrackBlocks().get(1).getPList().get(1).getZ());
    }
    
    //// TEXTURE BLOCK
    @Test
    public void testTrackBlock1GetTextureEntries() {
	assertNotNull(subject.getTrackBlocks().get(1).getTextureEntries());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry0Unknown0() {
	assertEquals(1,subject.getTrackBlocks().get(1).getTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry0Unknown1() {
	assertEquals(2222,subject.getTrackBlocks().get(1).getTextureEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry0Unknown2() {
	assertEquals(3333,subject.getTrackBlocks().get(1).getTextureEntries().get(0).getUnknown2());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry0Unknown3() {
	assertEquals(4444,subject.getTrackBlocks().get(1).getTextureEntries().get(0).getUnknown3());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry0Unknown4() {
	assertEquals(5555,subject.getTrackBlocks().get(1).getTextureEntries().get(0).getUnknown4());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry1Unknown0() {
	assertEquals(0,subject.getTrackBlocks().get(1).getTextureEntries().get(1).getUnknown0());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry1Unknown1() {
	assertEquals(6666,subject.getTrackBlocks().get(1).getTextureEntries().get(1).getUnknown1());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry1Unknown2() {
	assertEquals(7777,subject.getTrackBlocks().get(1).getTextureEntries().get(1).getUnknown2());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry1Unknown3() {
	assertEquals(8888,subject.getTrackBlocks().get(1).getTextureEntries().get(1).getUnknown3());
    }
    
    @Test
    public void testTrackBlock1GetTextureEntry1Unknown4() {
	assertEquals(9999,subject.getTrackBlocks().get(1).getTextureEntries().get(1).getUnknown4());
    }
    
    //WALL TYPE
    
    @Test
    public void testTrackBlock1GetWallType0() {
	assertEquals((Integer)0,subject.getTrackBlocks().get(1).getWallTypes().get(0));
    }
    @Test
    public void testTrackBlock1GetWallType1() {
	assertEquals((Integer)1,subject.getTrackBlocks().get(1).getWallTypes().get(1));
    }
    
    //WALL TEXTURE
    
    @Test
    public void testTrackBlock1GetWallTexture0Unknown0() {
	assertEquals(3333,subject.getTrackBlocks().get(1).getWallTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture0Unknown1() {
	assertEquals(4444,subject.getTrackBlocks().get(1).getWallTextureEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture0Unknown2() {
	assertEquals(5555,subject.getTrackBlocks().get(1).getWallTextureEntries().get(0).getUnknown2());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture0Unknown3() {
	assertEquals(6666,subject.getTrackBlocks().get(1).getWallTextureEntries().get(0).getUnknown3());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture1Unknown0() {
	assertEquals(7777,subject.getTrackBlocks().get(1).getWallTextureEntries().get(1).getUnknown0());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture1Unknown1() {
	assertEquals(8888,subject.getTrackBlocks().get(1).getWallTextureEntries().get(1).getUnknown1());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture1Unknown2() {
	assertEquals(9999,subject.getTrackBlocks().get(1).getWallTextureEntries().get(1).getUnknown2());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture1Unknown3() {
	assertEquals(0000,subject.getTrackBlocks().get(1).getWallTextureEntries().get(1).getUnknown3());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture2Unknown0() {
	assertEquals(1111,subject.getTrackBlocks().get(1).getWallTextureEntries().get(2).getUnknown0());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture2Unknown1() {
	assertEquals(2222,subject.getTrackBlocks().get(1).getWallTextureEntries().get(2).getUnknown1());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture2Unknown2() {
	assertEquals(3333,subject.getTrackBlocks().get(1).getWallTextureEntries().get(2).getUnknown2());
    }
    
    @Test
    public void testTrackBlock1GetWallTexture2Unknown3() {
	assertEquals(4444,subject.getTrackBlocks().get(1).getWallTextureEntries().get(2).getUnknown3());
    }
    
    
    //// H FACTOR
    @Test
    public void testTrackBlock1GetHFactorX() {
	assertEquals(-1.1,subject.getTrackBlocks().get(1).getHFactor().getX());
    }
    
    @Test
    public void testTrackBlock1GetHFactorY() {
	assertEquals(-2.2,subject.getTrackBlocks().get(1).getHFactor().getY());
    }
    
    @Test
    public void testTrackBlock1GetHFactor() {
	assertEquals(-3.3,subject.getTrackBlocks().get(1).getHFactor().getZ());
    }
    
    //// POINT OFFSETS
    
    @Test
    public void testTrackBlock1GetPointOffset0() {
	assertEquals(-444.4,subject.getTrackBlocks().get(1).getPointOffsets().get(0));
    }
    
    @Test
    public void testTrackBlock1GetPointOffset1() {
	assertEquals(-555.5,subject.getTrackBlocks().get(1).getPointOffsets().get(1));
    }
    
    @Test
    public void testTrackBlock1GetPointOffset2() {
	assertEquals(-666.6,subject.getTrackBlocks().get(1).getPointOffsets().get(2));
    }
    
    //// ALTITUDE, GRADE, INTERPGRADE, WIDTH, INTERPWIDTH
    
    @Test
    public void testTrackBlock1GetAltitude() {
	assertEquals(777.7,subject.getTrackBlocks().get(1).getAltitude());
    }
    
    @Test
    public void testTrackBlock1GetGrade() {
	assertEquals(8.8,subject.getTrackBlocks().get(1).getGrade());
    }
    
    @Test
    public void testTrackBlock1GetInterpGrade() {
	assertEquals(9.9,subject.getTrackBlocks().get(1).getInterpGrade());
    }
    
    @Test
    public void testTrackBlock1GetWidth() {
	assertEquals(11.1,subject.getTrackBlocks().get(1).getWidth());
    }
    
    @Test
    public void testTrackBlock1GetInterpWidth() {
	assertEquals(22.2,subject.getTrackBlocks().get(1).getInterpWidth());
    }
    
    //// HEIGHT OFFSET
    @Test
    public void testTrackBlock1GetHeightOffset0() {
	assertEquals(3.3,subject.getTrackBlocks().get(1).getHeightOffsets().get(0));
    }
    
    @Test
    public void testTrackBlock1GetHeightOffset1() {
	assertEquals(4.4,subject.getTrackBlocks().get(1).getHeightOffsets().get(1));
    }
    
    @Test
    public void testTrackBlock1GetHeightOffset2() {
	assertEquals(5.5,subject.getTrackBlocks().get(1).getHeightOffsets().get(2));
    }
    
    //// BOILERPLATE CODE

    @Override
    protected CARTTRKFile generateSubject(InputStream is) throws Exception {
	return new CARTTRKFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/trk/jfdt/testCART.TRK";
    }

}//end CARTTRKFileTest
