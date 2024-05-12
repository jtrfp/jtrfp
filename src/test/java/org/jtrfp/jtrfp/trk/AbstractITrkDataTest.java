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

package org.jtrfp.jtrfp.trk;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;


/**
 * Abstract test for any parser which reads the included dummy file and implements ITrkData.
 * Implementors must implement methods for providing said ITrkData Object.
 * @author Chuck Ritola
 *
 */


public abstract class AbstractITrkDataTest extends TestCase {
    /**
     * Provides a new ITrkData Object to be tested.
     * Objects should not be cached. Result shall provide the content
     * as parsed from MTM1Test.TRK dummy file and will be tested as such.
     * Implementor should use getInputStream() to read the dummy file as needed.
     * @return	ITrkData Object to be tested.
     * @since May 7, 2024
     */
    protected abstract ITrkData getSubject();
    
    /**
     * Returns a new InputStream representing the dummy file to be parsed
     * by the test subject. Implementor should use this when implementing getSubject().
     * @return A new InputStream representing the data of the MTM2Test.TRK dummy file.
     * @since May 7, 2024
     */
    protected InputStream getInputStream() {
	final InputStream result = AbstractITrkDataTest.class.getResourceAsStream("/org/jtrfp/jtrfp/trk/jfdt/MTM2Test.TRK");
	assertNotNull(result);
	return result;
    }
    
    @Test
    public void testSubject() {
	assertNotNull(getSubject());
    }
    
    @Test
    public void testAxleModelName() {
	assertEquals("testAxle.BIN",getSubject().getAxleModelName());
    }
    
    @Test
    public void testBarTextureName() {
	assertEquals("testAxleBar.RAW",getSubject().getBarTextureName());
    }
    
    @Test
    public void testShockTextureName() {
	assertEquals("testShock.RAW",getSubject().getShockTextureName());
    }
    
    @Test
    public void testTireModelBaseName() {
	assertEquals("testTire.BIN",getSubject().getTireModelBaseName());
    }
    
    @Test
    public void testTruckModelBaseName() {
	assertEquals("testBin.BIN",getSubject().getTruckModelBaseName());
    }
    
    @Test
    public void testTruckName() {
	assertEquals("Test Truck",getSubject().getTruckName());
    }
    
    @Test
    public void testAxlebarOffsetX() {
	assertEquals(1.1f,getSubject().getAxlebarOffset().getX());
    }
    
    @Test
    public void testAxlebarOffsetY() {
	assertEquals(2.2f,getSubject().getAxlebarOffset().getY());
    }
    
    @Test
    public void testAxlebarOffsetZ() {
	assertEquals(3.3f,getSubject().getAxlebarOffset().getZ());
    }
    
    @Test
    public void testDriveshaftPosX() {
	assertEquals(4.4f,getSubject().getDriveshaftPos().getX());
    }
    
    @Test
    public void testDriveshaftPosY() {
	assertEquals(5.5f,getSubject().getDriveshaftPos().getY());
    }
    
    @Test
    public void testDriveshaftPosZ() {
	assertEquals(6.6f,getSubject().getDriveshaftPos().getZ());
    }
    
    @Test
    public void testRightFrontTirePosX() {
	assertEquals(1.1f,getSubject().getRightFrontTirePos().getX());
    }
    
    @Test
    public void testRightFrontTirePosY() {
	assertEquals(5.5f,getSubject().getRightFrontTirePos().getY());
    }
    
    @Test
    public void testRightFrontTirePosZ() {
	assertEquals(9.9f,getSubject().getRightFrontTirePos().getZ());
    }
    
    @Test
    public void testLeftFrontTirePosX() {
	assertEquals(2.2f,getSubject().getLeftFrontTirePos().getX());
    }
    
    @Test
    public void testLeftFrontTirePosY() {
	assertEquals(6.6f,getSubject().getLeftFrontTirePos().getY());
    }
    
    @Test
    public void testLeftFrontTirePosZ() {
	assertEquals(1.1f,getSubject().getLeftFrontTirePos().getZ());
    }
   
    ////
    
    @Test
    public void testRightRearTirePosX() {
	assertEquals(3.3f,getSubject().getRightRearTirePos().getX());
    }
    
    @Test
    public void testRightRearTirePosY() {
	assertEquals(7.7f,getSubject().getRightRearTirePos().getY());
    }
    
    @Test
    public void testRightRearTirePosZ() {
	assertEquals(2.2f,getSubject().getRightRearTirePos().getZ());
    }
    
    @Test
    public void testLeftRearTirePosX() {
	assertEquals(4.4f,getSubject().getLeftRearTirePos().getX());
    }
    
    @Test
    public void testLeftRearTirePosY() {
	assertEquals(8.8f,getSubject().getLeftRearTirePos().getY());
    }
    
    @Test
    public void testLeftRearTirePosZ() {
	assertEquals(3.3f,getSubject().getLeftRearTirePos().getZ());
    }
    
}//end AbstractITrkDataTest
