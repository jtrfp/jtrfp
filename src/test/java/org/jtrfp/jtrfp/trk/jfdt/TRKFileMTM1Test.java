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

public class TRKFileMTM1Test extends AbstractParserTest<TRKFile> {

    @Test
    public void testGetTrkData() {
	assertNotNull(subject.getTrkData());
    }

    @Test
    public void testGetTruckName() {
	assertEquals("Test Truck",subject.getTruckName());
    }

    @Test
    public void testGetTruckModelBaseName() {
	assertEquals("testBin.BIN",subject.getTruckModelBaseName());
    }

    @Test
    public void testGetTireModelBaseName() {
	assertEquals("testTire.BIN",subject.getTireModelBaseName());
    }
    
    @Test
    public void testGetRightRearTirePosZInt() {
	assertEquals(2.2,subject.getRightRearTirePosZ());
    }

    @Test
    public void testGetLeftFrontTirePosX() {
	assertEquals(2.2,subject.getLeftFrontTirePosX());
    }

    @Test
    public void testGetLeftFrontTirePosY() {
	assertEquals(6.6,subject.getLeftFrontTirePosY());
    }

    @Test
    public void testGetLeftFrontTirePosZ() {
	assertEquals(1.1,subject.getLeftFrontTirePosZ());
    }

    @Test
    public void testGetRightFrontTirePosX() {
	assertEquals(1.1,subject.getRightFrontTirePosX());
    }

    @Test
    public void testGetRightFrontTirePosY() {
	assertEquals(1.1,subject.getRightFrontTirePosX());
    }
    
    @Test
    public void testGetRightFrontTirePosZ() {
	assertEquals(9.9,subject.getRightFrontTirePosZ());
    }

    @Test
    public void testGetLeftRearTirePosX() {
	assertEquals(4.4,subject.getLeftRearTirePosX());
    }

    @Test
    public void testGetLeftRearTirePosY() {
	assertEquals(8.8,subject.getLeftRearTirePosY());
    }

    @Test
    public void testGetLeftRearTirePosZ() {
	assertEquals(3.3,subject.getLeftRearTirePosZ());
    }

    @Test
    public void testGetRightRearTirePosX() {
	assertEquals(3.3,subject.getRightRearTirePosX());
    }

    @Test
    public void testGetRightRearTirePosY() {
	assertEquals(7.7,subject.getRightRearTirePosY());
    }

    @Test
    public void testGetRightRearTirePosZ() {
	assertEquals(2.2,subject.getRightRearTirePosZ());
    }

    @Test
    public void testGetScrapePoint1X() {
	assertEquals(4.4,subject.getScrapePoints().get(0).getX());
    }
    
    public void testGetScrapePoint1Y() {
	assertEquals(5.5,subject.getScrapePoints().get(0).getY());
    }
    
    public void testGetScrapePoint1Z() {
	assertEquals(6.6,subject.getScrapePoints().get(0).getZ());
    }
    
    @Test
    public void testGetScrapePoint2X() {
	assertEquals(7.7,subject.getScrapePoints().get(1).getX());
    }
    
    public void testGetScrapePoint2Y() {
	assertEquals(8.8,subject.getScrapePoints().get(1).getY());
    }
    
    public void testGetScrapePoint2Z() {
	assertEquals(9.9,subject.getScrapePoints().get(1).getZ());
    }
    
    @Test
    public void testGetScrapePoint3X() {
	assertEquals(1.1,subject.getScrapePoints().get(2).getX());
    }
    
    public void testGetScrapePoint3Y() {
	assertEquals(2.2,subject.getScrapePoints().get(2).getY());
    }
    
    public void testGetScrapePoint3Z() {
	assertEquals(3.3,subject.getScrapePoints().get(2).getZ());
    }
    
    @Test
    public void testGetScrapePoint4X() {
	assertEquals(4.4,subject.getScrapePoints().get(3).getX());
    }
    
    public void testGetScrapePoint4Y() {
	assertEquals(5.5,subject.getScrapePoints().get(3).getY());
    }
    
    public void testGetScrapePoint4Z() {
	assertEquals(6.6,subject.getScrapePoints().get(3).getZ());
    }
    
    @Test
    public void testGetScrapePoint5X() {
	assertEquals(7.7,subject.getScrapePoints().get(4).getX());
    }
    
    public void testGetScrapePoint5Y() {
	assertEquals(8.8,subject.getScrapePoints().get(4).getY());
    }
    
    public void testGetScrapePoint5Z() {
	assertEquals(9.9,subject.getScrapePoints().get(4).getZ());
    }
    
    @Test
    public void testGetScrapePoint6X() {
	assertEquals(1.1,subject.getScrapePoints().get(5).getX());
    }
    
    public void testGetScrapePoint6Y() {
	assertEquals(2.2,subject.getScrapePoints().get(5).getY());
    }
    
    public void testGetScrapePoint6Z() {
	assertEquals(3.3,subject.getScrapePoints().get(5).getZ());
    }
    
    @Test
    public void testGetScrapePoint7X() {
	assertEquals(4.4,subject.getScrapePoints().get(6).getX());
    }
    
    public void testGetScrapePoint7Y() {
	assertEquals(5.5,subject.getScrapePoints().get(6).getY());
    }
    
    public void testGetScrapePoint7Z() {
	assertEquals(6.6,subject.getScrapePoints().get(6).getZ());
    }
    
    @Test
    public void testGetScrapePoint8X() {
	assertEquals(7.7,subject.getScrapePoints().get(7).getX());
    }
    
    public void testGetScrapePoint8Y() {
	assertEquals(8.8,subject.getScrapePoints().get(7).getY());
    }
    
    public void testGetScrapePoint8Z() {
	assertEquals(9.9,subject.getScrapePoints().get(7).getZ());
    }
    
    @Test
    public void testGetScrapePoint9X() {
	assertEquals(1.1,subject.getScrapePoints().get(8).getX());
    }
    
    public void testGetScrapePoint9Y() {
	assertEquals(2.2,subject.getScrapePoints().get(8).getY());
    }
    
    public void testGetScrapePoint9Z() {
	assertEquals(3.3,subject.getScrapePoints().get(8).getZ());
    }
    
    @Test
    public void testGetScrapePoint10X() {
	assertEquals(4.4,subject.getScrapePoints().get(9).getX());
    }
    
    public void testGetScrapePoint10Y() {
	assertEquals(5.5,subject.getScrapePoints().get(9).getY());
    }
    
    public void testGetScrapePoint10Z() {
	assertEquals(6.6,subject.getScrapePoints().get(9).getZ());
    }
    
    @Test
    public void testGetScrapePoint11X() {
	assertEquals(7.7,subject.getScrapePoints().get(10).getX());
    }
    
    public void testGetScrapePoint11Y() {
	assertEquals(8.8,subject.getScrapePoints().get(10).getY());
    }
    
    public void testGetScrapePoint11Z() {
	assertEquals(9.9,subject.getScrapePoints().get(10).getZ());
    }
    
    @Test
    public void testGetScrapePoint12X() {
	assertEquals(1.1,subject.getScrapePoints().get(11).getX());
    }
    
    public void testGetScrapePoint12Y() {
	assertEquals(2.2,subject.getScrapePoints().get(11).getY());
    }
    
    public void testGetScrapePoint12Z() {
	assertEquals(3.3,subject.getScrapePoints().get(11).getZ());
    }
    
    ////
    @Test
    public void testGetWaveFile1() {
	assertEquals("test0.wav",subject.getWaveFile1());
    }

    @Test
    public void testGetWaveFile2() {
	assertEquals("test1.wav",subject.getWaveFile2());
    }

    @Test
    public void testGetWaveFile3() {
	assertEquals("test2.wav",subject.getWaveFile3());
    }

    @Test
    public void testGetInstrumentCluster() {
	assertEquals("testInstrumentCluster",subject.getInstrumentCluster());
    }
/*
    @Test
    public void testGetAxlebarOffset() {
	fail("Not yet implemented");
    }

    @Test
    public void testGetDriveshaftPos() {
	fail("Not yet implemented");
    }
*/
    @Override
    protected TRKFile generateSubject(InputStream is) throws Exception {
	return new TRKFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/trk/jfdt/MTM1Test.TRK";
    }

}//end TRKFileMTM1Test
