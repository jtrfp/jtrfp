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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;

/**
 * Read/Write parses a Terminal Reality .CRS asset file from InputStream<br><br>
 * 
 * <table>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>Hellbender</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * @author Chuck Ritola
 *
 */

public class CRSFile extends SelfParsingFile {
    private int numBlocks;
    private ArrayList<AbstractCourseBlock> courseBlocks;
    
    public CRSFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    @Override
    public void describeFormat(Parser p)
	    throws UnrecognizedFormatException {
	p.stringEndingWith("\r\n", p.property("numBlocks", int.class), false);
	for(int i = 0; i < getNumBlocks(); i++) {
	    try {
		if(courseBlocks != null && courseBlocks.size() > i && courseBlocks.get(i) instanceof CondensedCourseBlock)
		    throw new UnrecognizedFormatException("");
		p.expectString("*********************************************\r\n[Course "+(i+1)+"] ID,numSegs,direction\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("courseBlocks", CourseBlock.class, i), ClassInclusion.classOf(CourseBlock.class));
	    } catch(UnrecognizedFormatException e) {
		p.expectString("*********************************************\r\nnumPoints,groundCourse,periodic\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("courseBlocks", CondensedCourseBlock.class, i), ClassInclusion.classOf(CondensedCourseBlock.class));
	    }//end condensed course block
	}//end for(numBlocks)

    }//end describeFormat(...)

    public static abstract class AbstractCourseBlock implements ThirdPartyParseable {}
    
    public static class CondensedCourseBlock extends AbstractCourseBlock {
	private int numPoints, groundCourse, periodic;
	private ArrayList<CondensedCourseSegment> courseSegments;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringCSVEndingWith("\r\n", int.class, false, "numPoints","groundCourse","periodic");
	    for(int i = 0 ; i < getNumPoints(); i++) {
		p.subParseProposedClasses(p.indexedProperty("courseSegments", CondensedCourseSegment.class, i), ClassInclusion.classOf(CondensedCourseSegment.class));
	    }
	}//end describeFormat()

	public int getNumPoints() {
	    return numPoints;
	}

	public void setNumPoints(int numPoints) {
	    this.numPoints = numPoints;
	}

	public int getGroundCourse() {
	    return groundCourse;
	}

	public void setGroundCourse(int groundCourse) {
	    this.groundCourse = groundCourse;
	}

	public int getPeriodic() {
	    return periodic;
	}

	public void setPeriodic(int periodic) {
	    this.periodic = periodic;
	}

	public ArrayList<CondensedCourseSegment> getCourseSegments() {
	    return courseSegments;
	}

	public void setCourseSegments(
		ArrayList<CondensedCourseSegment> courseSegments) {
	    this.courseSegments = courseSegments;
	}
    }//end CondensedCourseBlock
    
    public static class CondensedCourseSegment implements ThirdPartyParseable {
	private int x,y,z;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringCSVEndingWith("\r\n", int.class, false, "x","y","z");
	}//end describeFormat(...)

	public int getX() {
	    return x;
	}

	public void setX(int x) {
	    this.x = x;
	}

	public int getY() {
	    return y;
	}

	public void setY(int y) {
	    this.y = y;
	}

	public int getZ() {
	    return z;
	}

	public void setZ(int z) {
	    this.z = z;
	}
	
    }//end CondensedCourseSegment
    
    public static class CourseBlock extends AbstractCourseBlock {
	private int id, numSegs, direction;
	private ArrayList<CourseSegment> courseSegments;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringCSVEndingWith("\r\n", int.class, false, "id","numSegs","direction");
	    for(int i = 0 ; i < getNumSegs(); i++) {
		p.subParseProposedClasses(p.indexedProperty("courseSegments", CourseSegment.class, i), ClassInclusion.classOf(CourseSegment.class));
	    }
	}//end describeFormat()

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public int getNumSegs() {
	    return numSegs;
	}

	public void setNumSegs(int numSegs) {
	    this.numSegs = numSegs;
	}

	public int getDirection() {
	    return direction;
	}

	public void setDirection(int direction) {
	    this.direction = direction;
	}

	public ArrayList<CourseSegment> getCourseSegments() {
	    return courseSegments;
	}

	public void setCourseSegments(ArrayList<CourseSegment> courseSegments) {
	    this.courseSegments = courseSegments;
	}
	
    }//end CourseBlock
    
    public static class CourseSegment implements ThirdPartyParseable {
	private int courseType;
	private int courseStartX, courseStartY, courseStartZ;
	private int courseEndX, courseEndY, courseEndZ;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("*********************************************\r\nCourse type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("courseType", int.class), false);
	    p.expectString("Course start (x,y,z)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", int.class, false, "courseStartX","courseStartY","courseStartZ");
	    try {p.expectString("Course end (x,y,z)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("Course end\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }
	    p.stringCSVEndingWith("\r\n", int.class, false, "courseEndX","courseEndY","courseEndZ");
	}//end describeFormat(...)

	public int getCourseType() {
	    return courseType;
	}

	public void setCourseType(int courseType) {
	    this.courseType = courseType;
	}

	public int getCourseStartX() {
	    return courseStartX;
	}

	public void setCourseStartX(int courseStartX) {
	    this.courseStartX = courseStartX;
	}

	public int getCourseStartY() {
	    return courseStartY;
	}

	public void setCourseStartY(int courseStartY) {
	    this.courseStartY = courseStartY;
	}

	public int getCourseStartZ() {
	    return courseStartZ;
	}

	public void setCourseStartZ(int courseStartZ) {
	    this.courseStartZ = courseStartZ;
	}

	public int getCourseEndX() {
	    return courseEndX;
	}

	public void setCourseEndX(int courseEndX) {
	    this.courseEndX = courseEndX;
	}

	public int getCourseEndY() {
	    return courseEndY;
	}

	public void setCourseEndY(int courseEndY) {
	    this.courseEndY = courseEndY;
	}

	public int getCourseEndZ() {
	    return courseEndZ;
	}

	public void setCourseEndZ(int courseEndZ) {
	    this.courseEndZ = courseEndZ;
	}
	
    }//end CourseSegment

    public int getNumBlocks() {
        return numBlocks;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public ArrayList<AbstractCourseBlock> getCourseBlocks() {
        return courseBlocks;
    }

    public void setCourseBlocks(ArrayList<AbstractCourseBlock> courseBlocks) {
        this.courseBlocks = courseBlocks;
    }

}//end CRSFile
