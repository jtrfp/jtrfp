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

package org.jtrfp.jtrfp.loc.jfdt;

import java.io.BufferedInputStream;
import java.io.EOFException;
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
 * Parser for Terminal Reality LOC asset file (localization or textual skin)
 * @author Chuck Ritola
 *
 */

public class LOCFile extends SelfParsingFile {
    private int unknown0, numObjectives, numParts;
    private List<LOCEntry> locEntries;
    private List<LOCObjective> locObjectives;
    private List<PartEntry> partEntries;
    private Integer version;
    private String listingDescription, detailedDescription, completionText;
    private String plainText;

    public LOCFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    @Override
    public void describeFormat(Parser p)
	    throws UnrecognizedFormatException {
	try {
	    if(p.parseMode == ParseMode.WRITE && (getVersion() == null || getVersion() == -1))
		throw new UnrecognizedFormatException();
	    describeVersion1Format(p);
	} catch(UnrecognizedFormatException e) {
	    try {describePartsFormat(p);}
	    catch(UnrecognizedFormatException e0) {
		try {
		    if(p.parseMode == ParseMode.WRITE && getLocEntries() == null)
			throw new UnrecognizedFormatException();
		describeClassicFormat(p);
		} catch(UnrecognizedFormatException e1) {
			if(p.parseMode == ParseMode.READ)
			    try{plainText = new String(p.is.readAllBytes());} catch(Exception e2) {e2.printStackTrace();}
			if(p.parseMode == ParseMode.WRITE && getPlainText() != null)
			    try{p.os.write(getPlainText().getBytes());} catch(Exception e2) {e2.printStackTrace();}
		    }//end plainTextFormat
	    }//end legacyformat
	}//end partsFormat
    }//end describeFormat(...)

    private void describePartsFormat(Parser p) throws UnrecognizedFormatException {
	if(p.parseMode == ParseMode.WRITE && getPartEntries() == null)
	    throw new UnrecognizedFormatException();
	p.expectString("partCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("numParts", int.class), false);
	
	for(int i = 0; i < getNumParts(); i++) {
	    try{p.expectString("--- Part "+(i+1)+" ---\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString(" --- Part "+(i+1)+" ---\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//One of them has a leading space. -_-
	    }
	    p.subParseProposedClasses(p.indexedProperty("partEntries", PartEntry.class, i), ClassInclusion.classOf(PartEntry.class));
	}//end for(i)
    }//end describePartsFormat(...)
    
    public static class PartEntry implements ThirdPartyParseable {
	private String name, description;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    try{p.expectString("Name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}//EN
	    catch(UnrecognizedFormatException e0) {//EN+whitespace
		try {p.expectString("Name \r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e1) {//FR
		p.expectString("Nom\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }}
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("name", String.class), false);
	    try{p.expectString("Description\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}//EN/FR
	    catch(UnrecognizedFormatException e) {//DE
		try {p.expectString("Beschreibung\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
		catch(UnrecognizedFormatException e0) {
		    p.expectString("Beschreibung \r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//At least one has a trailing space. -_-
		}
	    }
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("description", String.class), false);
	}//end describeFormat(...)

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}
	
    }//end PartEntry
    
    private void describeClassicFormat(Parser p) {
	p.ignoreEOF(false);
	p.expectString("TRI Message System\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith("\r\n", p.property("unknown0", int.class), false);
	p.expectString("LOC\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	try {
	    int i = 0;
	    do {
		p.subParseProposedClasses(p.indexedProperty("locEntries", LOCEntry.class, i), ClassInclusion.nestedClassesOf(LOCFile.class));

	    } while(!(getLocEntries().get(i++) instanceof ENDEntry));
	} catch(UnrecognizedFormatException e) {
	    if(e.getCause() instanceof EOFException)
		return;
	    else throw e;
	} catch(ArrayIndexOutOfBoundsException e) {
	    return;
	}
    }//end describeLegacyFormat(...)

    private void describeVersion1Format(Parser p) {
	if(p.parseMode == ParseMode.WRITE && getVersion() != 1)
	    throw new UnrecognizedFormatException();
	try {p.expectString("version\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	catch(UnrecognizedFormatException e) {
	    p.expectString("Version\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	}
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("version", Integer.class), false);
	
	p.expectString("listingDescription\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("listingDescription", String.class), false);
		
	try{p.expectString("detailedDescription\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	catch(UnrecognizedFormatException e) {//Sometimes the caps vary...
	    p.expectString("DetailedDescription\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	}
	
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("detailedDescription", String.class), false);
	
	p.expectString("completionText\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//Some of these have multiple lines
	p.stringEndingWith("objectiveCount\r\n", p.property("completionText", String.class), false);
	
	//p.expectString("objectiveCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numObjectives", int.class), false);
	
	p.ignoreEOF(true);
	for(int i = 0; i < getNumObjectives(); i++) {
	    try{p.expectString("objectiveDescription"+(i+1)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("ObjectiveDescription"+(i+1)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }
	    p.subParseProposedClasses(p.indexedProperty("locObjectives", LOCObjective.class, i), ClassInclusion.classOf(LOCObjective.class));
	}//end for(i)
    }//end Parser
    
    public static class LOCObjective implements ThirdPartyParseable {
	private String description;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("description", String.class), false);
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}
	
    }//end LOCObjective

    public static class ENDEntry extends LOCEntry {

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("@@END\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	}

    }//end ENDEntry

    public static class LOCEntry implements ThirdPartyParseable {
	private String tag, comment, string;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("@@TAG\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n@", p.property("tag", String.class), false);
	    try {
		p.expectString("@COMMENT\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith("\r\n@", p.property("comment", String.class), false);
	    } catch(UnrecognizedFormatException e) {}//Optional part
	    p.expectString("@STRING\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("string", String.class), false);
	}//end describeFormat()

	public String getTag() {
	    return tag;
	}

	public void setTag(String tag) {
	    this.tag = tag;
	}

	public String getComment() {
	    return comment;
	}

	public void setComment(String comment) {
	    this.comment = comment;
	}

	public String getString() {
	    return string;
	}

	public void setString(String string) {
	    this.string = string;
	}
    }//end LOCEntry

    public int getUnknown0() {
	return unknown0;
    }

    public void setUnknown0(int unknown0) {
	this.unknown0 = unknown0;
    }

    public List<LOCEntry> getLocEntries() {
	return locEntries;
    }

    public void setLocEntries(List<LOCEntry> locEntries) {
	this.locEntries = locEntries;
    }

    public int getNumObjectives() {
        return numObjectives;
    }

    public void setNumObjectives(int numObjectives) {
        this.numObjectives = numObjectives;
    }

    public List<LOCObjective> getLocObjectives() {
        return locObjectives;
    }

    public void setLocObjectives(List<LOCObjective> locObjectives) {
        this.locObjectives = locObjectives;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getCompletionText() {
        return completionText;
    }

    public void setCompletionText(String completionText) {
        this.completionText = completionText;
    }

    public int getNumParts() {
        return numParts;
    }

    public void setNumParts(int numParts) {
        this.numParts = numParts;
    }

    public List<PartEntry> getPartEntries() {
        return partEntries;
    }

    public void setPartEntries(List<PartEntry> partEntries) {
        this.partEntries = partEntries;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

}//end LOCFile
