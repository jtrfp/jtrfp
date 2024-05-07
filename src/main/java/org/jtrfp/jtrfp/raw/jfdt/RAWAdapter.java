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

import org.jtrfp.jtrfp.raw.IRawData;

/**
 * Wraps a jFDT RAWAdapter to implement the mtmX IRawData interface.
 * Also provides row-major -> column-major coordinate conversion for getValueAt(...)
 * @author Chuck Ritola
 *
 */

public class RAWAdapter implements IRawData {
    private final RAWFile delegate;
    
    public RAWAdapter(RAWFile delegate) {
	this.delegate = delegate;
    }

    @Override
    public int getWidth() {
	return (int)delegate.getSpeculativeDimensions().getWidth();
    }

    @Override
    public int getHeight() {
	return (int)delegate.getSpeculativeDimensions().getHeight();
    }

    @Override // mtmX is column-major
    public int getValueAt(int x, int y) {
	return delegate.getRawBytes()[y+x*getWidth()];
    }

}//end RAWAdapter
