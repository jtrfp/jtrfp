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

package org.jtrfp.jtrfp.glt.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;

/**
 * Read/Write parses a poorly-understood Terminal Reality GLT file asset
 * @author Chuck Ritola
 *
 */

public class GLTFile extends SelfParsingFile {
    private int numBlocks;
    private ArrayList<GLTEntry> gltEntries;
    
    public GLTFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }
    @Override
    public void describeFormat(Parser p)
	    throws UnrecognizedFormatException {
	p.stringEndingWith("\r\n", p.property("numBlocks", int.class), false);
	
	for(int i = 0 ; i < getNumBlocks(); i++)
	    p.subParseProposedClasses(p.indexedProperty("gltEntries", GLTEntry.class, i), ClassInclusion.classOf(GLTEntry.class));
    }//end describeFormat(...)
    
    public static class GLTEntry implements ThirdPartyParseable {
	private String texture0, texture1, texture2;
	private int int0, int1, int2, int3;
	private int int4, int5, int6;
	private boolean boolean0;
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith("\r\n", p.property("texture0", String.class), false);
	    p.stringEndingWith("\r\n", p.property("texture1", String.class), false);
	    p.stringEndingWith("\r\n", p.property("texture2", String.class), false);

	    p.stringEndingWith(",", p.property("int0", int.class), false);
	    p.stringEndingWith(",", p.property("int1", int.class), false);
	    p.stringEndingWith(",", p.property("int2", int.class), false);
	    p.stringEndingWith(",", p.property("int3", int.class), false);
	    p.stringEndingWith("\r\n", p.property("boolean0", boolean.class), false);

	    p.stringEndingWith(",", p.property("int4", int.class), false);
	    p.stringEndingWith(",", p.property("int5", int.class), false);
	    p.stringEndingWith("\r\n", p.property("int6", int.class), false);
	}
	public String getTexture0() {
	    return texture0;
	}
	public void setTexture0(String texture0) {
	    this.texture0 = texture0;
	}
	public String getTexture1() {
	    return texture1;
	}
	public void setTexture1(String texture1) {
	    this.texture1 = texture1;
	}
	public String getTexture2() {
	    return texture2;
	}
	public void setTexture2(String texture2) {
	    this.texture2 = texture2;
	}
	public int getInt0() {
	    return int0;
	}
	public void setInt0(int int0) {
	    this.int0 = int0;
	}
	public int getInt1() {
	    return int1;
	}
	public void setInt1(int int1) {
	    this.int1 = int1;
	}
	public int getInt2() {
	    return int2;
	}
	public void setInt2(int int2) {
	    this.int2 = int2;
	}
	public int getInt3() {
	    return int3;
	}
	public void setInt3(int int3) {
	    this.int3 = int3;
	}
	public int getInt4() {
	    return int4;
	}
	public void setInt4(int int4) {
	    this.int4 = int4;
	}
	public int getInt5() {
	    return int5;
	}
	public void setInt5(int int5) {
	    this.int5 = int5;
	}
	public int getInt6() {
	    return int6;
	}
	public void setInt6(int int6) {
	    this.int6 = int6;
	}
	public boolean isBoolean0() {
	    return boolean0;
	}
	public void setBoolean0(boolean boolean0) {
	    this.boolean0 = boolean0;
	}
    }//end GLTEntry

    public int getNumBlocks() {
        return numBlocks;
    }

    public void setNumBlocks(int numBlocks) {
        this.numBlocks = numBlocks;
    }

    public ArrayList<GLTEntry> getGltEntries() {
        return gltEntries;
    }

    public void setGltEntries(ArrayList<GLTEntry> gltEntries) {
        this.gltEntries = gltEntries;
    }

}//end GLTFile
