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

package org.jtrfp.jtrfp.tex.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.Parser.ParseMode;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.TRParsers;

/**
 * Read/Write parser for Terminal Reality .TEX file assets.
 * Supports classic and version 1
 * 
 * <table>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>MTM1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>MTM2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>CART</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * 
 * @author Chuck Ritola
 *
 */

public class TEXFile extends SelfParsingFile {
    private Integer texVersion;
    private int textureCount, shadowTextureCount;
    private List<TextureEntry> textureEntries, shadowTextureEntries;
    
    public TEXFile() {super();}
    public TEXFile(InputStream is) throws IOException, IllegalAccessException {
	super(new BufferedInputStream(is,1024));
    }
    
    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	try {
	    if(p.parseMode == ParseMode.WRITE && getTexVersion() == null)
		throw new UnrecognizedFormatException();
	    p.expectString("Version\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("texVersion", Integer.class), false);
	    p.expectString("textureCount,shadowTextureCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("textureCount", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("shadowTextureCount", int.class), false);
	    for(int i = 0 ; i < getTextureCount(); i++) 
		p.subParseProposedClasses(p.indexedProperty("textureEntries", TextureEntry.class, i), ClassInclusion.classOf(TextureEntry.class));
	    for(int i = 0 ; i < getShadowTextureCount(); i++) 
		p.subParseProposedClasses(p.indexedProperty("shadowTextureEntries", TextureEntry.class, i), ClassInclusion.classOf(TextureEntry.class));
	    
	} catch(UnrecognizedFormatException e) {
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("textureCount", Integer.class), false);
	    p.ignoreEOF=true;
	    for(int i = 0 ; i < getTextureCount(); i++)
		p.subParseProposedClasses(p.indexedProperty("textureEntries", TextureEntry.class, i), ClassInclusion.classOf(TextureEntry.class));
	}
    }//end describeFormat(...)
    
    public static class TextureEntry implements ThirdPartyParseable {
	private int unknown0, unknown1;
	private String rawFileName;
	public TextureEntry() {}

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    try {p.stringEndingWithAndFailAt(new String[]{","},new String[] {"\r\n"}, p.property("unknown0", int.class), false);
	    p.stringEndingWith(",", p.property("unknown1", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rawFileName", String.class), false);}
	    catch(UnrecognizedFormatException e) {
		//Try old format
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rawFileName", String.class), false);
	    }
	}//end describeFormat()

	public int getUnknown0() {
	    return unknown0;
	}

	public void setUnknown0(int unknown0) {
	    this.unknown0 = unknown0;
	}

	public int getUnknown1() {
	    return unknown1;
	}

	public void setUnknown1(int unknown1) {
	    this.unknown1 = unknown1;
	}

	public String getRawFileName() {
	    return rawFileName;
	}

	public void setRawFileName(String rawFileName) {
	    this.rawFileName = rawFileName;
	}
    }//end TextureEntry

    public Integer getTexVersion() {
        return texVersion;
    }

    public void setTexVersion(Integer texVersion) {
        this.texVersion = texVersion;
    }

    public int getTextureCount() {
        return textureCount;
    }

    public void setTextureCount(int textureCount) {
        this.textureCount = textureCount;
    }

    public int getShadowTextureCount() {
        return shadowTextureCount;
    }

    public void setShadowTextureCount(int shadowTextureCount) {
        this.shadowTextureCount = shadowTextureCount;
    }

    public List<TextureEntry> getTextureEntries() {
        return textureEntries;
    }

    public void setTextureEntries(List<TextureEntry> textureEntries) {
        this.textureEntries = textureEntries;
    }

    public List<TextureEntry> getShadowTextureEntries() {
        return shadowTextureEntries;
    }

    public void setShadowTextureEntries(List<TextureEntry> shadowTextureEntries) {
        this.shadowTextureEntries = shadowTextureEntries;
    }

}//end TEXFile
