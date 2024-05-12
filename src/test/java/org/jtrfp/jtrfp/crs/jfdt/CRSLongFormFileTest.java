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
import org.jtrfp.jtrfp.crs.jfdt.CRSFile.CourseBlock;
import org.junit.Test;

public class CRSLongFormFileTest extends AbstractParserTest<CRSFile> {

    @Test
    public void testGetNumBlocks() {
	assertEquals(2,subject.getNumBlocks());
    }

    @Test
    public void testGetCourseBlocks() {
	assertNotNull(subject.getCourseBlocks());
    }
    
    @Test
    public void testFirstCourseEntry() {
	assertTrue(subject.getCourseBlocks().get(0) instanceof CourseBlock);
    }
    
    @Test
    public void testSecondCourseEntry() {
	assertTrue(subject.getCourseBlocks().get(1) instanceof CourseBlock);
    }
    
    @Test
    public void testFirstCourseEntrySegments() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertNotNull(cb.getCourseSegments());
    }
    
    @Test
    public void testFirstCourseEntryNumSegments() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(2,cb.getNumSegs());
    }
    
    @Test
    public void testFirstCourseEntryDirection() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(1,cb.getDirection());
    }
    
    @Test
    public void testFirstCourseEntryID() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(222,cb.getId());
    }
    
    @Test
    public void testFirstCourseFirstSegmentStartX() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(1111,cb.getCourseSegments().get(0).getCourseStartX());
    }
    
    @Test
    public void testFirstCourseFirstSegmentStartY() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(2222,cb.getCourseSegments().get(0).getCourseStartY());
    }
    
    @Test
    public void testFirstCourseFirstSegmentStartZ() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(3333,cb.getCourseSegments().get(0).getCourseStartZ());
    }
    
    @Test
    public void testFirstCourseFirstSegmentEndX() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(4444,cb.getCourseSegments().get(0).getCourseEndX());
    }
    
    @Test
    public void testFirstCourseFirstSegmentEndY() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(5555,cb.getCourseSegments().get(0).getCourseEndY());
    }
    
    @Test
    public void testFirstCourseFirstSegmentEndZ() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(6666,cb.getCourseSegments().get(0).getCourseEndZ());
    }
    
    @Test
    public void testFirstCourseFirstSegmentType() {
	final CourseBlock cb = (CourseBlock)(subject.getCourseBlocks().get(0));
	assertEquals(7,cb.getCourseSegments().get(0).getCourseType());
    }

    @Override
    protected CRSFile generateSubject(InputStream is) throws Exception {
	return new CRSFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/crs/jfdt/testLongForm.CRS";
    }
    
}//end CRSLongFormFileTest
