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
package org.jtrfp.jtrfp.raw.jfdt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.UnrecognizedFormatException;

/**
 * Parses a RAW file, which essentially is an unformatted blob of bytes, and makes attempts
 * at speculating its dimensions and formatting based on patterns found in the early TRI games.
 * <b>Pixels in this class are row-major and not column major as found in IRawData!</b>
 * @author Chuck Ritola
 *
 */

public class RAWFile extends SelfParsingFile {
    private byte[] 		rawBytes;
    private SoftReference<BufferedImage[]>segImg = new SoftReference<>(null);
    private double 		scaleWidth = 1, scaleHeight = 1;
    private Dimension 		dims;
    private int 		segWidth, segHeight, numSegs = -1;
    private Color[] 		palette;

    private SegmentDirection 	dir;

    private enum SegmentDirection {
	VERTICAL, HORIZONTAL
    }

    public RAWFile(InputStream inputStream) throws IllegalAccessException,
	    IOException {
	super(inputStream);
    }

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.bytesEndingWith(null, p.property("rawBytes", byte[].class), false);
    }

    /**
     * @return Raw bytes representing this RAW file, 256x256x1B rasterized.
     */
    public byte[] getRawBytes() {
	return rawBytes;
    }

    /**
     * @param rawBytes
     *            Raw bytes representing this RAW file, 256x256x1B rasterized.
     */
    public void setRawBytes(byte[] rawBytes) {
	this.rawBytes = rawBytes;
    }

    public int getSideLength() {
	return (int) Math.sqrt(rawBytes.length);
    }

    public int valueAt(int x, int y) {
	return (int) rawBytes[x + (y * getSideLength())] & 0xFF;
    }

    public void setPalette(Color[] palette) {
	if (palette == null)
	    throw new NullPointerException("Palette must be non-null.");
	this.palette = palette;
    }
    
    /**
     * Makes an educated guess at the width and height of this image.
     * @return
     * @since May 2, 2024
     */
    public Dimension getSpeculativeDimensions() {
	if(SpecialRAWDimensions.hasIntegralSquareRoot(rawBytes.length)) {
	    final int sideLen = getSideLength();
	    return new Dimension(sideLen,sideLen);
	} else return SpecialRAWDimensions.getSpecialDimensions(rawBytes.length);
    }//end getSpeuclativeWidth()

    /**
     * Attempts to break this presumably-non-power-of-two image into smaller power-of-two images.
     * Palette property must be set before invoking or na IllegalStateException will be thrown.
     * @param upScalePowerOfTwo
     * @return
     * @since May 2, 2024
     */
    public BufferedImage[] asSegments(int upScalePowerOfTwo) {
	if (palette == null)
	    throw new IllegalStateException(
		    "Palette property must be set before extracting Image.");
	BufferedImage [] segments = segImg.get();
	if (segments == null) {
	    dims = SpecialRAWDimensions
		    .getSpecialDimensions(getRawBytes().length);
	    int lesserDim, greaterDim;
	    if (dims.getHeight() > dims.getWidth()) {
		dir = SegmentDirection.VERTICAL;
		lesserDim = (int) dims.getWidth();
		greaterDim = (int) dims.getHeight();
	    } else {
		dir = SegmentDirection.HORIZONTAL;
		lesserDim = (int) dims.getHeight();
		greaterDim = (int) dims.getWidth();
	    }
	    int newLDim = lesserDim, newGDim = greaterDim;
	    // If non-square
	    if (lesserDim != greaterDim) {
		if (!SpecialRAWDimensions.isPowerOfTwo(lesserDim)) {
		    newLDim = nextPowerOfTwo(lesserDim);
		} else {
		}
		newGDim = nextMultiple(greaterDim, newLDim);
	    }// end if(non-square)
	    else {// Square, make sure they are a power-of-two
		if (!SpecialRAWDimensions.isPowerOfTwo(lesserDim)) {
		    newGDim = nextPowerOfTwo(greaterDim);
		    newLDim = nextPowerOfTwo(lesserDim);
		} else {}
	    }// end if(square)
	    newGDim *= Math.pow(2, upScalePowerOfTwo);
	    newLDim *= Math.pow(2, upScalePowerOfTwo);
	    int newWidth = 0, newHeight = 0;
	    if (dir == SegmentDirection.VERTICAL) {
		newWidth = newLDim;
		newHeight = newGDim;
	    } else {
		newWidth = newGDim;
		newHeight = newLDim;
	    }// Horizontal
	    scaleWidth = (double) newWidth / dims.getWidth();
	    scaleHeight = (double) newHeight / dims.getHeight();
	    
	    // Break into segments
	    numSegs = newGDim / newLDim;
	    // Square so height and width are the same.
	    segHeight = newLDim;
	    segWidth = newLDim;
	    
	    segments = new BufferedImage[numSegs];
	    segImg = new SoftReference<>(segments);
	    for (int seg = 0; seg < numSegs; seg++) {
		BufferedImage segBuf = new BufferedImage(segWidth, segHeight,
			BufferedImage.TYPE_INT_ARGB);
		segments[seg] = segBuf;
		// Generate the bitmap
		for (int y = 0; y < segHeight; y++) {
		    for (int x = 0; x < segWidth; x++) {
			Color c = getFilteredSegmentedPixelAt(x, y, seg);
			segBuf.setRGB(x, y, c.getRGB());
		    }// end for(x)
		}// end for(y)
	    }// end (create rgb888)
	}// end if(segImg==null)
	return segments;
    }//end asSegments(...)

    private int nextMultiple(int originalValue, int multiplicand) {
	return ((int) Math.ceil((double) originalValue / (double) multiplicand))
		* multiplicand;
    }

    private int nextPowerOfTwo(double v) {
	return (int) Math.pow(2, Math.ceil(log2(v)));
    }
    
    private static double log2(double v){
	return Math.log(v)/Math.log(2);
    }

    private Color getFilteredSegmentedPixelAt(double u, double v, int segment) {
	switch (dir) {
	case HORIZONTAL:
	    return getFilteredScaledGlobalPixelAt(u + segment * segWidth, v);
	case VERTICAL:
	    return getFilteredScaledGlobalPixelAt(u, v + segment * segHeight);
	}// end switch(dir)
	throw new RuntimeException("Invalid segment direction: " + dir);
    }// end getFilteredSegmentedPixelAt(...)

    private Color getFilteredScaledGlobalPixelAt(double u, double v) {
	return getFilteredGlobalPixelAt(u / scaleWidth, v / scaleHeight);
    }

    private Color getFilteredGlobalPixelAt(double u, double v) {
	u -= .5;
	v -= .5;
	int x = (int) Math.floor(u);
	int y = (int) Math.floor(v);

	Color tl = getPixelAt(x, y + 1);
	Color tr = getPixelAt(x + 1, y + 1);
	Color bl = getPixelAt(x, y);
	Color br = getPixelAt(x + 1, y);
	
	double uRatio = (u - x);
	double vRatio = (v - y);
	
	double uInverse = 1 - uRatio;
	double vInverse = 1 - vRatio;

	double r = ((double) bl.getRed() * uInverse + (double) br.getRed()
		* uRatio)
		* vInverse
		+ ((double) tl.getRed() * uInverse + (double) tr.getRed()
			* uRatio) * vRatio;
	double g = ((double) bl.getGreen() * uInverse + (double) br.getGreen()
		* uRatio)
		* vInverse
		+ ((double) tl.getGreen() * uInverse + (double) tr.getGreen()
			* uRatio) * vRatio;
	double b = ((double) bl.getBlue() * uInverse + (double) br.getBlue()
		* uRatio)
		* vInverse
		+ ((double) tl.getBlue() * uInverse + (double) tr.getBlue()
			* uRatio) * vRatio;
	double a = ((double) bl.getAlpha() * uInverse + (double) br.getAlpha()
		* uRatio)
		* vInverse
		+ ((double) tl.getAlpha() * uInverse + (double) tr.getAlpha()
			* uRatio) * vRatio;
	
	return new Color((int) r, (int) g, (int) b, (int) a);
    }

    private final Color getPixelAt(int x, int y) {
	if (x < 0)
	    return palette[0];
	if (y < 0)
	    return palette[0];
	if (x >= dims.getWidth())
	    return palette[0];
	if (y >= dims.getHeight())
	    return palette[0];
	return palette[getRawBytes()[x + y * (int) dims.getWidth()] & 0xFF];
    }
}// end RAWFile
