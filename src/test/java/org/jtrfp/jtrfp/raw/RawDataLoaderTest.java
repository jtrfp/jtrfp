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

package org.jtrfp.jtrfp.raw;

import org.jtrfp.jtrfp.internal.raw.RawDataLoader;

public class RawDataLoaderTest extends AbstractIRawDataTest {

    @Override
    protected IRawData getSubject() {
	try {return RawDataLoader.load(getInputStream());}
	catch(Throwable t) {throw new RuntimeException(t);}
    }

}//end RawDataLoaderTest
