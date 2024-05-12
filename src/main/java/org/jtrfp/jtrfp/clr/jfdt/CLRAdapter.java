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

package org.jtrfp.jtrfp.clr.jfdt;

import org.jtrfp.jtrfp.clr.IClrData;

/**
 * Implements the IClrData interface by wrapping a jFDT CLRFile
 * Also converts buffer mapping from CLRFile's row-major to IClrData's column-major orientation.
 * This adapter is for 2-byte tiles only. Use on TV,F3, or F!Zone will result in errors.
 * @author Chuck Ritola
 *
 */

public class CLRAdapter implements IClrData {
    private final CLRFile delegate;
    private final int sideLength;
    private final int maxColorIdx;
    
    public CLRAdapter(CLRFile delegate) {
	this.delegate = delegate;
	sideLength = (int)Math.sqrt(delegate.getRawBytes().length/2);
	int maxColorIdx = 0;
	
	for(int x = 0 ; x < getWidth(); x++) {
	    for(int y = 0; y < getHeight(); y++)
		maxColorIdx = Math.max(maxColorIdx, getColorAt(x,y));
	}//end for(x)
	this.maxColorIdx = maxColorIdx;
    }//end constructor

    @Override
    public int getWidth() {
	return sideLength;
    }

    @Override
    public int getHeight() {
	return sideLength;
    }

    @Override
    public int getColorCount() {
	return maxColorIdx + 1;
    }

    @Override
    public int getColorAt(int x, int y) {
	final int idx = (y+x*getWidth())*2;
	final byte [] raw = delegate.getRawBytes();
	return ((raw[idx+1] & 0x0F) << 8)
		+ (raw[idx] & 0xFF);
    }

    @Override
    public int getTypeAt(int x, int y) {
	final int idx = (y+x*getWidth())*2;
	return (delegate.getRawBytes()[idx+1] & 0xF0) >> 4;
    }

}//end CLRAdapter
