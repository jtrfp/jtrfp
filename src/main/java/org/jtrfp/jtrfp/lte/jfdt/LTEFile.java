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
package org.jtrfp.jtrfp.lte.jfdt;

import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.UnrecognizedFormatException;


/**
 * A read/write parser for Terminal Reality LTE asset files.
 * @author Chuck Ritola
 *
 */
public class LTEFile extends SelfParsingFile {
    private byte [] lteData;
    public LTEFile(InputStream is) throws IOException, IllegalAccessException {
	super(is);
    }
    @Override
    public void describeFormat(Parser parser)
	    throws UnrecognizedFormatException {
	parser.bytesOfCount(4096, parser.property("lteData", byte[].class));
    }//end describeFormat(...)
    /**
     * @return the lteData
     */
    public byte[] getLteData() {
        return lteData;
    }
    /**
     * @param lteData the lteData to set
     */
    public void setLteData(byte[] lteData) {
        this.lteData = lteData;
    }
    
    public int getGradientIndex(int colorIndex, int lightLevel){
	if(lightLevel<0 || lightLevel>15)
	    throw new IllegalArgumentException("Lightlevel should be in range [0,15]. Got "+lightLevel);
	if(colorIndex<0 || colorIndex>255){
	    throw new IllegalArgumentException("ColorIndex should be in range [0,255]. Got "+colorIndex);
	}
	return lteData[colorIndex*16+lightLevel]&0xFF;
    }//end gradientIndex(...)
}//end LTEFile
