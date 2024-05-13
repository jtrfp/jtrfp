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

package org.jtrfp.jtrfp.klp.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.FailureBehavior;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.TRParsers;

/**
 * Read/Write parser for Terminal Reality .KLP asset file from InputStream.
 * 
 * <table>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>MTM1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>MTM2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class KLPFile extends SelfParsingFile {
    private KLPElement delegate;

    public KLPFile(InputStream inputStream) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(inputStream,1024));
    }

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.subParseProposedClasses(p.property("delegate", KLPElement.class), ClassInclusion.nestedClassesOf(KLPElement.class));
    }//end describeFormat(...)
    
    public static abstract class ListKLPElement extends KLPElement {
	private int numSampleRanges;
	private ArrayList<SampleRange> sampleRanges;
	@Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numSampleRanges", int.class), false);
		for(int i = 0; i < getNumSampleRanges(); i++)
		    p.subParseProposedClasses(p.indexedProperty("sampleRanges", SampleRange.class, i), ClassInclusion.classOf(SampleRange.class));
	    }//end describeFormat
	
	public static class SampleRange implements ThirdPartyParseable {
	    private int startIndex, endIndex;

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.stringEndingWith(" ", p.property("startIndex", int.class), false);
		p.stringEndingWith(new String[]{"\r\n"," \r\n"}, p.property("endIndex", int.class), false);
	    }

	    public int getStartIndex() {
	        return startIndex;
	    }

	    public void setStartIndex(int startIndex) {
	        this.startIndex = startIndex;
	    }

	    public int getEndIndex() {
	        return endIndex;
	    }

	    public void setEndIndex(int endIndex) {
	        this.endIndex = endIndex;
	    }
	    
	}//end SamplePoint

	public int getNumSampleRanges() {
	    return numSampleRanges;
	}

	public void setNumSampleRanges(int numSampleRanges) {
	    this.numSampleRanges = numSampleRanges;
	}

	public ArrayList<SampleRange> getSampleRanges() {
	    return sampleRanges;
	}

	public void setSampleRanges(ArrayList<SampleRange> sampleRanges) {
	    this.sampleRanges = sampleRanges;
	}

    }//end ListKLPElement
    
    public static abstract class KLPElement implements ThirdPartyParseable {
	public static class PlayOnce extends KLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("0 ", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }//end describeFormat
	    
	}//end PlayOnce

	public static class Loop extends KLPElement {
	    private int startIndex, endIndex;

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("1 ", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(" ",    p.property("startIndex", int.class), false);
		p.stringEndingWith("\r\n", p.property("endIndex", int.class), false);
	    }//end describeFormat(...)

	    public int getStartIndex() {
	        return startIndex;
	    }

	    public void setStartIndex(int startIndex) {
	        this.startIndex = startIndex;
	    }

	    public int getEndIndex() {
	        return endIndex;
	    }

	    public void setEndIndex(int endIndex) {
	        this.endIndex = endIndex;
	    }

	}//end Loop
	
	public static class ForwardAndBack extends KLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("2 ", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }//end describeFormat
	    
	}//end ForwardAndBack

	public static class DefinedList extends ListKLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("3\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		super.describeFormat(p);
	    }//end describeFormat(...)

	}//end DefinedList
	
	public static class Unknown0 extends ListKLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("4\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		super.describeFormat(p);
	    }//end describeFormat
	    
	}//end Unknown0

	public static class Unknown1 extends ListKLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("5\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		super.describeFormat(p);
	    }//end describeFormat(...)

	}//end Unknown1
	
	public static class Random extends ListKLPElement {

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.expectString("6\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		super.describeFormat(p);
	    }//end describeFormat(...)

	}//end Random
    }//end KLPElement

    public KLPElement getDelegate() {
        return delegate;
    }

    public void setDelegate(KLPElement delegate) {
        this.delegate = delegate;
    }
}//end KLPFile
