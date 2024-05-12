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

import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jtrfp.raw.jfdt.RAWFile;

public class CLRFile extends RAWFile {

    public CLRFile(InputStream inputStream) throws IllegalAccessException,
	    IOException {
	super(inputStream);
    }

}//end CLRFile
