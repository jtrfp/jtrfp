/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.bin.jfdt;

import java.io.InputStream;
import java.util.List;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.FaceBlock.FaceBlockVertexWithUV;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.OpaqueZeroFaceBlock;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.TextureBlock;
import org.junit.Test;

public class BINModelStaticTest extends AbstractParserTest<BINFile.Model> {

    @Test
    public void testGetScale() {
	assertEquals(0xFF00,subject.getScale());
    }

    @Test
    public void testGetUnknown1() {
	assertEquals(0x01,subject.getUnknown1());
    }
    
    @Test
    public void testGetUnknown2() {
	assertEquals(0x02,subject.getUnknown2());
    }
    
    @Test
    public void testNumVertices() {
	assertEquals(4,subject.getNumVertices());
    }
    
    @Test
    public void testVertex0X() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x00,vertices.get(0).getX());
    }
    
    @Test
    public void testVertex0Y() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x01,vertices.get(0).getY());
    }
    
    @Test
    public void testVertex0Z() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x02,vertices.get(0).getZ());
    }
    ////
    @Test
    public void testVertex1X() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x03,vertices.get(1).getX());
    }
    
    @Test
    public void testVertex1Y() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x04,vertices.get(1).getY());
    }
    
    @Test
    public void testVertex1Z() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x05,vertices.get(1).getZ());
    }
    ////
    @Test
    public void testVertex2X() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x06,vertices.get(2).getX());
    }
    
    @Test
    public void testVertex2Y() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x07,vertices.get(2).getY());
    }
    
    @Test
    public void testVertex2Z() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x08,vertices.get(2).getZ());
    }
    ////
    @Test
    public void testVertex3X() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x09,vertices.get(3).getX());
    }
    
    @Test
    public void testVertex3Y() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x0A,vertices.get(3).getY());
    }
    
    @Test
    public void testVertex3Z() {
	List<BINFile.Model.Vertex> vertices = subject.getVertices();
	assertEquals(0x0B,vertices.get(3).getZ());
    }
    ////
    
    @Test
    public void testFirstDataBlockType() {
	assertTrue(subject.getDataBlocks().get(0) instanceof TextureBlock);
    }
    
    @Test
    public void testFirstDataBlockUnknown() {
	final TextureBlock fb = (TextureBlock)(subject.getDataBlocks().get(0));
	assertEquals(0x01, fb.getUnknown());
    }
    
    @Test
    public void testFirstDataBlockTextureName() {
	final TextureBlock fb = (TextureBlock)(subject.getDataBlocks().get(0));
	assertEquals("Test.RAW", fb.getTextureFileName());
    }
    
    @Test
    public void testSecondDataBlockType() {
	assertTrue(subject.getDataBlocks().get(0) instanceof TextureBlock);
    }
    
    
    @Test
    public void testSecondDataBlockVertexType() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(FaceBlockVertexWithUV.class, fb.getFaceBlockVertexType());
    }
    
    @Test
    public void testSecondDataBlockNormalX() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x00,fb.getNormalX());
    }
    
    @Test
    public void testSecondDataBlockNormalY() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x01,fb.getNormalY());
    }
    
    @Test
    public void testSecondDataBlockNormalZ() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x02,fb.getNormalZ());
    }
    
    @Test
    public void testSecondDataBlockMagic() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x03,fb.getMagic());
    }
    ////
    @Test
    public void testSecondDataBlockVertex0Index() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x00,((FaceBlockVertexWithUV)(fb.getVertices().get(0))).getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex0U() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x01,((FaceBlockVertexWithUV)(fb.getVertices().get(0))).getTextureCoordinateU());
    }
    
    @Test
    public void testSecondDataBlockVertex0V() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x02,((FaceBlockVertexWithUV)(fb.getVertices().get(0))).getTextureCoordinateV());
    }
    ////
    @Test
    public void testSecondDataBlockVertex1Index() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x03,((FaceBlockVertexWithUV)(fb.getVertices().get(1))).getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex1U() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x04,((FaceBlockVertexWithUV)(fb.getVertices().get(1))).getTextureCoordinateU());
    }
    
    @Test
    public void testSecondDataBlockVertex1V() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x05,((FaceBlockVertexWithUV)(fb.getVertices().get(1))).getTextureCoordinateV());
    }
    
    ////
    
    @Test
    public void testSecondDataBlockVertex2Index() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x06,((FaceBlockVertexWithUV)(fb.getVertices().get(2))).getVertexIndex());
    }
    
    @Test
    public void testSecondDataBlockVertex2U() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x07,((FaceBlockVertexWithUV)(fb.getVertices().get(2))).getTextureCoordinateU());
    }
    
    @Test
    public void testSecondDataBlockVertex2V() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x08,((FaceBlockVertexWithUV)(fb.getVertices().get(2))).getTextureCoordinateV());
    }
    
    ////
    
    @Test
    public void testSecondDataBlockVertex3Index() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x09,((FaceBlockVertexWithUV)(fb.getVertices().get(3))).getVertexIndex());
    }
    ////
    
    @Test
    public void testSecondDataBlockVertex3U() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x0A,((FaceBlockVertexWithUV)(fb.getVertices().get(3))).getTextureCoordinateU());
    }
    
    @Test
    public void testSecondDataBlockVertex3V() {
	final OpaqueZeroFaceBlock fb = (OpaqueZeroFaceBlock)(subject.getDataBlocks().get(1));
	assertEquals(0x0B,((FaceBlockVertexWithUV)(fb.getVertices().get(3))).getTextureCoordinateV());
    }
    
    @Override
    protected BINFile.Model generateSubject(InputStream is) throws Exception {
	return new BINFile.Model(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/bin/jfdt/testStatic.BIN";
    }

}//end BINModelTest
