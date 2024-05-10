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

package org.jtrfp.jtrfp.raw;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;


/**
 * Abstract test for any parser which reads the included dummy file and implements IRawData.
 * Implementors must implement methods for providing said IRawData Object.
 * @author Chuck Ritola
 *
 */


public abstract class AbstractIRawDataTest extends TestCase {
    /**
     * Provides a new IRawData Object to be tested.
     * Objects should not be cached. Result shall provide the content
     * as parsed from test.RAW dummy file and will be tested as such.
     * Implementor should use getInputStream() to read the dummy file as needed.
     * @return	IRawData Object to be tested.
     * @since May 2, 2024
     */
    protected abstract IRawData getSubject();
    
    /**
     * Returns a new InputStream representing the dummy file to be parsed
     * by the test subject. Implementor should use this when implementing getSubject().
     * @return A new InputStream representing the data of the test.RAW dummy file.
     * @since May 2, 2024
     */
    protected InputStream getInputStream() {
	final InputStream result = AbstractIRawDataTest.class.getResourceAsStream("/org/jtrfp/jtrfp/raw/jfdt/test.RAW");
	assertNotNull(result);
	return result;
    }
    
    @Test
    public void testSubject() {
	assertNotNull(getSubject());
    }
    
    @Test
    public void testGetWidth() {
	assertEquals(256,getSubject().getWidth());
    }
    
    @Test
    public void testGetHeight() {
	assertEquals(256,getSubject().getHeight());
    }
    
    @Test
    public void test255Pixel() {
	assertEquals(255,getSubject().getValueAt(17, 17)&0xFF);
    }
    
    @Test
    public void testTopLeftCorner() {
	assertEquals(2,getSubject().getValueAt(0, 0));
    }
    
    @Test
    public void testTopBorder() {
	assertEquals(1,getSubject().getValueAt(0, 1));
    }
    
    @Test
    public void testZeroPixel() {
	assertEquals(0,getSubject().getValueAt(1, 1));
    }
}//end AbstractIRawDataTest
