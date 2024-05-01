/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2014. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/
package org.jtrfp.jtrfp.jfdt;

import org.jtrfp.jtrfp.Vertex3f;

import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;

/**
 * Abstract implementation of a Terminal Reality heading vector consisting of a triplet of signed doubles 
 * followed by a Windows-style carriage-return newline, or a comma if using EndingWithComma nested class.
 * Ending with a comma is useful if the triplet is part of a longer list of comma-separated values.
 * @author Chuck Ritola
 *
 */
public class AbstractDoubleTriplet implements ThirdPartyParseable {
    double x, y, z;

    @Override
    public void describeFormat(Parser prs) throws UnrecognizedFormatException {
	prs.stringEndingWith(",", prs.property("x", Double.class), false);
	prs.stringEndingWith(",", prs.property("y", Double.class), false);
	prs.stringEndingWith(new String[]{"\r\n","\n"}, prs.property("z", Double.class), false);
    }

    public static class EndingWithComma extends AbstractDoubleTriplet {
	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.stringEndingWith(",", prs.property("x", Double.class), false);
	    prs.stringEndingWith(",", prs.property("y", Double.class), false);
	    prs.stringEndingWith(",", prs.property("z", Double.class), false);
	}
    }// end EndingWithComma

    /**
     * @return legacy X component of this vector.
     */
    public double getX() {
	return x;
    }

    /**
     * @param Legacy X component for this vector.
     */
    public void setX(double x) {
	this.x = x;
    }

    /**
     * @return Legacy Y component of this vector.
     */
    public double getY() {
	return y;
    }

    /**
     * @param Legacy Y component for this vector.
     */
    public void setY(double y) {
	this.y = y;
    }

    /**
     * @return Legacy Z component of this vector.
     */
    public double getZ() {
	return z;
    }

    /**
     * @param Legacy Z component for this vector.
     */
    public void setZ(double z) {
	this.z = z;
    }

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " x=" + x + " y=" + y + " z="
		+ z + "\n";
    }
    
    /**
     * Returns an immutable representation of this triplet as a Vertex3f.
     * The returned Vertex3f will not reflect changes to this object.
     * @return A vertex3f of the values at the time of invocation.
     * @since Nov 9, 2014
     */
    public Vertex3f asVertex3f(){
	return new Vertex3f((float)x,(float)y,(float)z);
    }
    
    /**
     * Imports contents of a vertex3f doubleo this object.
     * Note that float-double conversion may result in lost precision and range.
     * @return this object
     * @since Nov 9, 2014
     */
    public AbstractDoubleTriplet importFromVertex3f(Vertex3f importFrom){
	setX((double)importFrom.getX());
	setY((double)importFrom.getY());
	setZ((double)importFrom.getZ());
	return this;
    }
}// end AbstractVector
