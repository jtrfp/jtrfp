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

package org.jtrfp.jtrfp.clr;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Abstract test for any parser which reads the included dummy file and implements IClrData.
 * Implementors must implement methods for providing said IClrData Object.
 * @author Chuck Ritola
 *
 */

public abstract class AbstractIClrDataTest extends TestCase {
    /**
     * Provides a new IClrData Object to be tested.
     * Objects should not be cached. Result shall provide the content
     * as parsed from test2Byte.CLR dummy file and will be tested as such.
     * Implementor should use getInputStream() to read the dummy file as needed.
     * @return	IClrData Object to be tested.
     * @since May 2, 2024
     */
    protected abstract IClrData getSubject();
    
    /**
     * Returns a new InputStream representing the dummy file to be parsed
     * by the test subject. Implementor should use this when implementing getSubject().
     * @return A new InputStream representing the data of the test2Byte.CLR dummy file.
     * @since May 2, 2024
     */
    protected InputStream getInputStream() {
	final InputStream result = AbstractIClrDataTest.class.getResourceAsStream("/org/jtrfp/jtrfp/clr/jfdt/test2Byte.CLR");
	assertNotNull(result);
	return result;
    }
    
    @Test
    public void testSubject() {
	assertNotNull(getSubject());
    }
    
    @Test
    public void testGetWidth() {
	assertNotNull(128);
    }
    
    @Test
    public void testGetHeight() {
	assertNotNull(128);
    }
    
    @Test
    public void testColor0_0() {
	assertEquals(0,getSubject().getColorAt(0, 0));
    }
    
    @Test
    public void testType0_0() {
	assertEquals(5,getSubject().getTypeAt(0, 0));
    }
    
    @Test
    public void testColor0_1() {
	assertEquals(1,getSubject().getColorAt(0, 1));
    }
    
    @Test
    public void testColor1_1() {
	assertEquals(3,getSubject().getColorAt(1, 1));
    }
    
    @Test
    public void testType1_2() {
	assertEquals(15,getSubject().getTypeAt(1, 2));
    }
    
    @Test
    public void testColor1_0() {
	assertEquals(2,getSubject().getColorAt(1, 0));
    }
    
}//end AbstractIClrDataTest
