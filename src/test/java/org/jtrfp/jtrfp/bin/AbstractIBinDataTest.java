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

package org.jtrfp.jtrfp.bin;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Abstract test for any parser which reads the included dummy file and implements IBinData.
 * Implementors must implement methods for providing said IBinData Object.
 * @author Chuck Ritola
 *
 */

public abstract class AbstractIBinDataTest extends TestCase {
    /**
     * Provides a new IBinData Object to be tested.
     * Objects should not be cached. Result shall provide the content
     * as parsed from testStatic.BIN dummy file and will be tested as such.
     * Implementor should use getInputStream() to read the dummy file as needed.
     * @return	IBinData Object to be tested.
     * @since May 2, 2024
     */
    protected abstract IBinData getSubject();
    
    /**
     * Returns a new InputStream representing the dummy file to be parsed
     * by the test subject. Implementor should use this when implementing getSubject().
     * @return A new InputStream representing the data of the testStatic.BIN dummy file.
     * @since May 2, 2024
     */
    protected InputStream getInputStream() {
	final InputStream result = AbstractIBinDataTest.class.getResourceAsStream("/org/jtrfp/jtrfp/bin/jfdt/testStatic.BIN");
	assertNotNull(result);
	return result;
    }
    
    @Test
    public void testSubject() {
	assertNotNull(getSubject());
    }
    
    @Test
    public void testTextureNames() {
	assertNotNull(getSubject().getTextureNames());
    }
    
    @Test
    public void testFaces() {
	assertNotNull(getSubject().getFaces());
    }
    
    @Test
    public void testID() {
	assertEquals(IBinData.ID_MODEL,getSubject().getId());
    }
    
    @Test
    public void testVertexes() {
	assertNotNull(getSubject().getVertexes());
    }
    
    @Test
    public void testNormals() {
	assertNotNull(getSubject().getNormals());
    }
    
    @Test
    public void testVertexCount() {
	assertEquals(4,getSubject().getVertexCount());
    }
    
    @Test
    public void testVertex0X() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x00,vertices[0].getX());
    }
    
    @Test
    public void testVertex0Y() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x01,vertices[0].getY());
    }
    
    @Test
    public void testVertex0Z() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x02,vertices[0].getZ());
    }
    
    @Test
    public void testVertex1X() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x03,vertices[1].getX());
    }
    
    @Test
    public void testVertex1Y() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x04,vertices[1].getY());
    }
    
    @Test
    public void testVertex1Z() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x05,vertices[1].getZ());
    }
    
    @Test
    public void testVertex2X() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x06,vertices[2].getX());
    }
    
    @Test
    public void testVertex2Y() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x07,vertices[2].getY());
    }
    
    @Test
    public void testVertex2Z() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x08,vertices[2].getZ());
    }
    
    @Test
    public void testVertex3X() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x09,vertices[3].getX());
    }
    
    @Test
    public void testVertex3Y() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x0A,vertices[3].getY());
    }
    
    @Test
    public void testVertex3Z() {
	final IBinVertex[] vertices = getSubject().getVertexes();
	assertEquals(0x0B,vertices[3].getZ());
    }
    
    @Test
    public void testFirstTextureName() {
	assertEquals(2,getSubject().getVertexes()[0].getZ());
    }
    
    @Test
    public void testSecondDataBlockVertex0Index() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	assertEquals(0,face[0].getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex0U() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	assertEquals(1f/(float)0xFF0000,face[0].getU());
    }
    
    @Test
    public void testSecondDataBlockVertex0V() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(2f/(float)0xFF0000,face[0].getV());
    }
    
    @Test
    public void testSecondDataBlockVertex1Index() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	assertEquals(3,face[1].getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex1U() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(4f/(float)0xFF0000,face[1].getU());
    }
    
    @Test
    public void testSecondDataBlockVertex1V() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(5f/(float)0xFF0000,face[1].getV());
    }
    
    @Test
    public void testSecondDataBlockVertex2Index() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(6,face[2].getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex2U() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(7f/(float)0xFF0000,face[2].getU());
    }
    
    @Test
    public void testSecondDataBlockVertex2V() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(8f/(float)0xFF0000,face[2].getV());
    }
    
    @Test
    public void testSecondDataBlockVertex3Index() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(9,face[3].getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex3U() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(10f/(float)0xFF0000,face[3].getU());
    }
    
    @Test
    public void testSecondDataBlockVertex3V() {
	IBinTexCoord [] face = getSubject().getFaces()[0].getTexCoords();
	
	assertEquals(11f/(float)0xFF0000,face[3].getV());
    }
    
}//end AbstractIBinDataTest
