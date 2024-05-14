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
package org.jtrfp.jtrfp.ndx.jfdt;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Read/Write parses a Terminal Reality .NDX file asset, used to define font character widths.
 * Due to its simplicity, this implementation does not use the jFDT API.
 * @author Chuck Ritola
 *
 */

public class NDXFile {
    private List<Integer> widths = new ArrayList<>();

    public NDXFile read(InputStream is) {
	final Scanner s = new Scanner(is);
	while (s.hasNext()) {
	    String line = s.nextLine();
	    widths.add(Integer.parseInt(line));
	}// end while(hasNext)
	s.close();
	return this;
    }// end read()

    public NDXFile write(OutputStream os) {
	final PrintStream ps = new PrintStream(os);
	for (Integer w : widths) {
	    ps.print(w);
	    ps.print("\r\n");
	}
	return this;
    }//end write()

    /**
     * @return the widths
     */
    public List<Integer> getWidths() {
	return widths;
    }

    /**
     * Returns the width in pixels of the supplied ASCII value, if available.
     * Characters below index 32 are unavailable.
     * 
     * @param asciiValue
     * @return Width of the provided ASCII value, or -1 if unavailable.
     * @since Feb 27, 2014
     */
    public int asciiWidth(byte asciiValue) {
	try {
	    return widths.get(asciiValue - 32);
	} catch (ArrayIndexOutOfBoundsException e) {
	    return -1;
	}
    }// end asciiWidth

    /**
     * 
     * Manually set widths by their ASCII index.
     * Characters below index 32 are unavailable.
     * @param widths
     */
    public void setWidths(List<Integer> widths) {
	for(int i=32; i<this.widths.size(); i++){
	    this.widths.set(i-32, widths.get(i));
	}
    }//end setWidths(...)

}// end NDXFile
