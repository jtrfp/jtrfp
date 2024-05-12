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

import org.junit.Test;

public class TRKFileMTM2Test extends TRKFileMTM1Test {

    @Test
    public void testGetAxleModelName() {
	assertEquals("testAxle.BIN",subject.getAxleModelName());
    }

    @Test
    public void testGetShockTextureName() {
	assertEquals("testShock.RAW",subject.getShockTextureName());
    }

    @Test
    public void testGetBarTextureName() {
	assertEquals("testAxleBar.RAW",subject.getBarTextureName());
    }
    
    @Test
    public void testGetAxlebarOffsetX() {
	assertEquals(1.1,subject.getAxlebarOffset().getX());
    }
    
    @Test
    public void testGetAxlebarOffsetY() {
	assertEquals(2.2,subject.getAxlebarOffset().getY());
    }
    
    @Test
    public void testGetAxlebarOffsetZ() {
	assertEquals(3.3,subject.getAxlebarOffset().getZ());
    }

    @Test
    public void testGetDriveshaftPosX() {
	assertEquals(4.4,subject.getDriveshaftPos().getX());
    }
    
    @Test
    public void testGetDriveshaftPosY() {
	assertEquals(5.5,subject.getDriveshaftPos().getY());
    }
    
    @Test
    public void testGetDriveshaftPosZ() {
	assertEquals(6.6,subject.getDriveshaftPos().getZ());
    }
    
    //// LIGHTS
    
    @Test
    public void testGetNumLights() {
	assertEquals(2,subject.getNumLights());
    }
    
    @Test
    public void testLight0GetType() {
	assertEquals((Integer)3,subject.getLightTypes().get(0));
    }
    
    @Test
    public void testLight0GetBodyAxisPositionX() {
	assertEquals(1.1,subject.getLightBodyAxisPositions().get(0).getX());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionY() {
	assertEquals(2.2,subject.getLightBodyAxisPositions().get(0).getY());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionZ() {
	assertEquals(3.3,subject.getLightBodyAxisPositions().get(0).getZ());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionBitmapRadius() {
	assertEquals(4.4,subject.getLightBodyAxisPositions().get(0).getBitmapRadius());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionHeading() {
	assertEquals(5.5,subject.getLightHeadingPitchSpinSpeeds().get(0).getX());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionPitch() {
	assertEquals(6.6,subject.getLightHeadingPitchSpinSpeeds().get(0).getY());
    }
    
    @Test
    public void testLight0GetSpin() {
	assertEquals(7.7,subject.getLightHeadingPitchSpinSpeeds().get(0).getZ());
    }
    
    @Test
    public void testLight0GetLength() {
	assertEquals(1.1,subject.getLightCones().get(0).getLengthFeet());
    }
    
    @Test
    public void testLight0GetBaseRadius() {
	assertEquals(2.2,subject.getLightCones().get(0).getBaseRadiusFeet());
    }
    
    @Test
    public void testLightCone0GetRimRadius() {
	assertEquals(3.3,subject.getLightCones().get(0).getRimRadiusFeet());
    }
    
    @Test
    public void testLightCone0GetTextureName() {
	assertEquals("testLightTexture.RAW",subject.getLightCones().get(0).getTextureName());
    }
    
    @Test
    public void testLight0GetSourceBitmapName() {
	assertEquals("testLightSourceBitmap.RAW",subject.getLightSourceRAWs().get(0));
    }
    
    @Test
    public void testLight0GetLightOnMS() {
	assertEquals(4,subject.getLightTimesMS().get(0).getOnTimeMS());
    }
    
    @Test
    public void testLight0GetLightOffMS() {
	assertEquals(5,subject.getLightTimesMS().get(0).getOffTimeMS());
    }

    @Override
    protected TRKFile generateSubject(InputStream is) throws Exception {
	return new TRKFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/trk/jfdt/MTM2Test.TRK";
    }

}//end TRKFileMTM2Test
