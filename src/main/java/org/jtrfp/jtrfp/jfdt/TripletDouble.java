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
package org.jtrfp.jtrfp.jfdt;

import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.UnrecognizedFormatException;

public class TripletDouble extends AbstractDoubleTriplet {
    public static class EndingWithComma extends TripletDouble {
	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.stringEndingWith(",", prs.property("x", Double.class), false);
	    prs.stringEndingWith(",", prs.property("y", Double.class), false);
	    prs.stringEndingWith(",", prs.property("z", Double.class), false);
	}
    }// end EndingWithComma
    public static class EndingWithNewline extends TripletDouble {
	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.stringEndingWith(",", prs.property("x", Double.class), false);
	    prs.stringEndingWith(",", prs.property("y", Double.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("z", Double.class), false);
	}
    }// end EndingWithNewline
}// end Location3D
