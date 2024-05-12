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

package org.jtrfp.jtrfp.sit;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;


/**
 * Abstract test for any parser which reads the included dummy file and implements ISitData.
 * Implementors must implement methods for providing said ISitData Object.
 * @author Chuck Ritola
 *
 */


public abstract class AbstractISitDataTest extends TestCase {
    /**
     * Provides a new ISitData Object to be tested.
     * Objects should not be cached. Result shall provide the content
     * as parsed from MTM1Test.SIT dummy file and will be tested as such.
     * Implementor should use getInputStream() to read the dummy file as needed.
     * @return	ISitData Object to be tested.
     * @since May 7, 2024
     */
    protected abstract ISitData getSubject();
    
    /**
     * Returns a new InputStream representing the dummy file to be parsed
     * by the test subject. Implementor should use this when implementing getSubject().
     * @return A new InputStream representing the data of the MTM1Test.SIT dummy file.
     * @since May 7, 2024
     */
    protected InputStream getInputStream() {
	final InputStream result = AbstractISitDataTest.class.getResourceAsStream("/org/jtrfp/jtrfp/sit/jfdt/MTM1Test.SIT");
	assertNotNull(result);
	return result;
    }
    
    @Test
    public void testSubject() {
	assertNotNull(getSubject());
    }
    
    @Test
    public void testBoxes() {
	assertNotNull(getSubject().getBoxes());
    }
    
    @Test
    public void testBox0() {
	assertNotNull(getSubject().getBoxes()[0]);
    }
    
    @Test
    public void testBoxModelName() {
	assertEquals("testModel1.bin",getSubject().getBoxes()[0].getModelName());
    }
    
    @Test
    public void testBoxPhi() {
	assertEquals(6.6f,getSubject().getBoxes()[0].getPhi());
    }
    
    @Test
    public void testBoxPsi() {
	assertEquals(7.7f,getSubject().getBoxes()[0].getPsi());
    }
    
    @Test
    public void testBoxTheta() {
	assertEquals(5.5f,getSubject().getBoxes()[0].getTheta());
    }
    
    @Test
    public void testBoxX() {
	assertEquals(2.2f,getSubject().getBoxes()[0].getPosition().getX());
    }
    @Test
    public void testBoxY() {
	assertEquals(3.3f,getSubject().getBoxes()[0].getPosition().getY());
    }
    @Test
    public void testBoxZ() {
	assertEquals(4.4f,getSubject().getBoxes()[0].getPosition().getZ());
    }
}//end AbstractISitDataTest
