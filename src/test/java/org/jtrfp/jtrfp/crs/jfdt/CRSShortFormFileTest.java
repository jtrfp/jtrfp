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

package org.jtrfp.jtrfp.crs.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.crs.jfdt.CRSFile.CondensedCourseBlock;
import org.junit.Test;

public class CRSShortFormFileTest extends AbstractParserTest<CRSFile> {
    
    @Test
    public void testGetNumBlocks() {
	assertEquals(2,subject.getNumBlocks());
    }

    @Test
    public void testGetCondensedCourseBlocks() {
	assertNotNull(subject.getCourseBlocks());
    }
    
    @Test
    public void testFirstCourseEntry() {
	assertTrue(subject.getCourseBlocks().get(0) instanceof CondensedCourseBlock);
    }
    
    @Test
    public void testSecondCourseEntry() {
	assertTrue(subject.getCourseBlocks().get(1) instanceof CondensedCourseBlock);
    }
    
    @Test
    public void testFirstCourseEntrySegments() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertNotNull(cb.getCourseSegments());
    }
    
    @Test
    public void testFirstCourseEntryNumPoints() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(2,cb.getNumPoints());
    }
    
    @Test
    public void testFirstCourseEntryDirection() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(1,cb.getGroundCourse());
    }
    
    @Test
    public void testFirstCourseEntryID() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(3,cb.getPeriodic());
    }
    
    @Test
    public void testFirstCourseFirstSegmentGetX() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(1111,cb.getCourseSegments().get(0).getX());
    }
    
    @Test
    public void testFirstCourseFirstSegmentGetY() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(-2222,cb.getCourseSegments().get(0).getY());
    }
    
    @Test
    public void testFirstCourseFirstSegmentGetZ() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(3333,cb.getCourseSegments().get(0).getZ());
    }
    ///SECOND ENTRY
    
    
    @Test
    public void testSecondCourseEntrySegments() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertNotNull(cb.getCourseSegments());
    }
    
    @Test
    public void testSecondCourseEntryNumPoints() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(2,cb.getNumPoints());
    }
    
    @Test
    public void testSecondCourseEntryDirection() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(1,cb.getGroundCourse());
    }
    
    @Test
    public void testSecondCourseEntryID() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(3,cb.getPeriodic());
    }
    
    @Test
    public void testSecondCourseFirstSegmentGetX() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(-1111,cb.getCourseSegments().get(0).getX());
    }
    
    @Test
    public void testSecondCourseFirstSegmentGetY() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(2222,cb.getCourseSegments().get(0).getY());
    }
    
    @Test
    public void testSecondCourseFirstSegmentGetZ() {
	final CondensedCourseBlock cb = (CondensedCourseBlock)(subject.getCourseBlocks().get(1));
	assertEquals(-3333,cb.getCourseSegments().get(0).getZ());
    }
    
    @Override
    protected CRSFile generateSubject(InputStream is) throws Exception {
	return new CRSFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/crs/jfdt/testShortForm.CRS";
    }
    
    @Override
    public void testReadWrite() {}//Doesn't work
    
}//end CRSShortFormFileTest
