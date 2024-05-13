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

package org.jtrfp.jtrfp.sit.jfdt;

import java.io.BufferedInputStream;
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
 * Read/Write parser for Terminal Reality SIT file assets.
 * <table>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>MTM1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>MTM2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>CART</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class SITFile extends SelfParsingFile {
    private String lvlFile, raceTrackName, raceTrackLocale, trackLogoBMP, trackMapBMP, trackFlybyAVI,
    			trackAnnouncerWAV, trackDescriptionTXT;
    private RaceType trackRaceType;
    private int trackLongitude, trackLatitude, redbookAudioTrack, viewMode, spotd, spotp, spoth, zoom;
    private double raceTime, raceStartTime, dragDebugTimer, trackLength;
    private boolean controlFlag, autoShift, autoStage, bothStaged, bothStagedPrev,
    			stageComFlag, bonusLapFlag;
    
    private int numVehicles, numRamps , numBoxes , numCylinders , numTopCrush , 
	    numCourseSegments, numExtendedCourseSegments, weatherMask;
    
    private int courseDirection, backdropType, backdropCount;
    private Integer stadiumFlag, x, z, sx, sz, ambientSound;
    
    private String stadiumModelBINFile;
    
    private List<String> backdropModelBINFiles;
    private List<Vehicle> vehicles;
    private List<Box> boxes;
    private List<Ramp> ramps;
    private List<CourseDefinition> courseSegments, extendedCourseSegments;
    private List<TopCrush> topCrushes;
    private List<BoxName> boxNames;
    private List<BoxType> boxTypes;
    private List<Path> paths;
    private List<EnvironmentalLight> environmentalLights;
    
    private Vehicle yourTruck;
    private Integer version;
    
    private String authorName, detailedDescription;
    
    private int numBoxTypes, numEnvironmentalLights, numPaths;
    private Integer flag;
    
    private Integer yourVehicleNumber, paceLaps;
    private double brakeBias, weightJacker, brakeProportion;
    private int fuelKnob, boostKnob, revLimiter, frontAntiRollBar, rearAntiRollBar;
    
    private List<VarlowEntry> varlowEntries;
    
    private String signaturePrefix, signatureSuffix;
    
    public SITFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is, 1024));
    }

    @Override
    public void describeFormat(Parser p)
	    throws UnrecognizedFormatException {
	try {
	    if(p.parseMode == ParseMode.WRITE && getVersion() == null)
		throw new UnrecognizedFormatException();
	p.expectString("version\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("version", Integer.class), false);
	if(getVersion()==7)
	    try{describeVersion7(p);}
	catch(UnrecognizedFormatException e) {e.printStackTrace();}
	else if(getVersion()==6)
	    try {describeVersion6(p);}
	catch(UnrecognizedFormatException e) {e.printStackTrace();}
	else
	    throw new UnsupportedOperationException("Unsupported SIT version: "+getVersion());
	} catch(UnrecognizedFormatException e) {
	    describeClassic(p);
	}
	
    }//end describeFormat(...)
    
    private void describeClassic(Parser p) {
	p.setUnrecognizedFormatExceptionOnStringParseProblem(false);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("lvlFile", String.class), false);
	p.expectString("!Race Track Name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("raceTrackName", String.class), false);
	p.expectString("Race Track Locale\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("raceTrackLocale", String.class), false);
	p.expectString("Track Longtitude, Latitude\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "trackLongitude","trackLatitude");
	p.expectString("Track Logo .BMP file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackLogoBMP", String.class), false);
	p.expectString("Track Map .BMP file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackMapBMP", String.class), false);
	p.expectString("Track Fly-By .AVI file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackFlybyAVI", String.class), false);
	p.expectString("Track Announcer .WAV file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackAnnouncerWAV", String.class), false);
	p.expectString("Track Description .TXT file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackDescriptionTXT", String.class), false);
	p.expectString("Track Race Type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackRaceType", RaceType.class), false);
	p.expectString("@Redbook Audio Track\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("redbookAudioTrack", int.class), false);
	try {
	    if(p.parseMode == ParseMode.WRITE && getAmbientSound() == null)
		throw new UnrecognizedFormatException();
	    p.expectString("!ambient sound,track length,weather mask\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("ambientSound", Integer.class), false);
	    p.stringEndingWith(",", p.property("trackLength", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("weatherMask", int.class), false);
	} catch(UnrecognizedFormatException e) {}//Ignore failure of optional parse
	p.expectString("viewmode,spotd,spotp,spoth,zoom\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "viewMode","spotd","spotp","spoth","zoom");
	try {//Optional in HILLCLIM
	    p.expectString("$racetime, raceStartTime, dragDebugTimer\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "raceTime","raceStartTime","dragDebugTimer");
	    p.expectString("controlflag, autoShift, autoStage, bothStaged, bothStagedPrev\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "controlFlag","autoShift","autoStage","bothStaged","bothStagedPrev");
	    p.expectString("stageComFlag, bonusLapFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "stageComFlag", "bonusLapFlag");
	} catch(UnrecognizedFormatException e) {}
	//Found in CART
	try {
	    if(p.parseMode == ParseMode.WRITE && (getYourVehicleNumber() == null || getPaceLaps() == null))
		throw new UnrecognizedFormatException();
	    p.expectString("*** More stuff ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.expectString("yourVehicleNumber,paceLaps\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", Integer.class, false, "yourVehicleNumber","paceLaps");
	    p.expectString("brakeBias,fuelKnob,boostKnob,brakeProportion\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("brakeBias", double.class), false);
	    p.stringEndingWith(",", p.property("fuelKnob", int.class), false);
	    p.stringEndingWith(",", p.property("boostKnob", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("brakeProportion", double.class), false);
	    p.expectString("weightJacker,revLimiter,fAntiRollBar,rAntiRollBar\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("weightJacker", double.class), false);
	    p.stringEndingWith(",", p.property("revLimiter", int.class), false);
	    p.stringEndingWith(",", p.property("frontAntiRollBar", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rearAntiRollBar", int.class), false);
	} catch(UnrecognizedFormatException e) {}
	
	//Let it go through the motions
	p.expectString("*** Your Truck (Not used anymore) ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.subParseProposedClasses(p.property("yourTruck", Vehicle.class), ClassInclusion.classOf(Vehicle.class));
	//Vehicles
	p.expectString("*** Vehicles ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numVehicles", int.class), false);
	for(int i = 0 ; i < numVehicles; i++)
	    p.subParseProposedClasses(p.indexedProperty("vehicles", Vehicle.class, i), ClassInclusion.classOf(Vehicle.class));
	
	p.expectString("*** Ramps ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numRamps", int.class), false);
	for(int i = 0 ; i < numRamps; i++)
	    p.subParseProposedClasses(p.indexedProperty("ramps", Ramp.class, i), ClassInclusion.classOf(Ramp.class));
	
	p.expectString("*** Boxes ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numBoxes", int.class), false);
	for(int i = 0 ; i < numBoxes; i++) {
	    p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("boxes", Box.class, i), ClassInclusion.classOf(Box.class));
	    }
	
	p.expectString("*** Cylinders ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numCylinders", int.class), false);
	
	p.expectString("*** Top Crush ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numTopCrush", int.class), false);
	for(int i = 0 ; i < numTopCrush; i++) 
	    p.subParseProposedClasses(p.indexedProperty("topCrushes", TopCrush.class, i), ClassInclusion.classOf(TopCrush.class));
	
	//Course definitions
	p.expectString("*** Course ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("c1Count,course_direction\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "numCourseSegments", "courseDirection");
	for(int i = 0 ; i < numCourseSegments; i++) {
	    try {p.expectString("********************************************* "+(i*2+1)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//Variant found in HILCLIM.SET has no number at the end
	    }
	    p.subParseProposedClasses(p.indexedProperty("courseSegments", CourseDefinition.class, i), ClassInclusion.classOf(CourseDefinition.class));
	    }
	//Extended course definition
	p.expectString("@*********** Extended Course Definitions *************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numExtendedCourseSegments", int.class), false);
	for(int i = 0 ; i < numExtendedCourseSegments; i++) {
	    p.expectString("[Course "+(i+1)+"] c1Count,course_direction\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("extendedCourseSegments", ExtendedCourseDefinition.class, i), ClassInclusion.classOf(ExtendedCourseDefinition.class));
	}
	//Stadium
	p.expectString("*** Stadium ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	try {//MTM1 stadium data
	    //Escape if using other form
	    if(p.parseMode == ParseMode.WRITE && ((getSx() != null && getX() != null && getZ() != null && getSz() != null) || getStadiumFlag() == null))
		throw new UnrecognizedFormatException();
	    p.expectString("stadiumFlag,stadiumModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("stadiumFlag", Integer.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("stadiumModelBINFile", String.class), false);
	} catch(UnrecognizedFormatException e) {
	    try {//CART doesn't use stadium anymore
		if(p.parseMode == ParseMode.WRITE && getStadiumFlag() != null)
		    throw new UnrecognizedFormatException();
		p.expectString("Stadium is not\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.expectString("used anymore!\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    } catch(UnrecognizedFormatException e0) {
		//Try MTM2 stadium data
		p.expectString("!stadiumFlag,x,z,sx,sz,stadiumModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringCSVEndingWith(",", Integer.class, false, "stadiumFlag","x","z","sx","sz");
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("stadiumModelBINFile", String.class), false);
	    }// catch(not CART)
	}//end catch(not MTM1)
	
	p.expectString("*** Backdrop ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("backdropType,backdropCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(",", p.property("backdropType", int.class), false);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("backdropCount", int.class), false);
	try {p.expectString("backdropModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	for(int i = 0 ; i < getBackdropCount(); i++)
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("backdropModelBINFiles", String.class, i), false);
	}catch(UnrecognizedFormatException e) {}
	
	try {//Found in CART
	    if(p.parseMode == ParseMode.WRITE && getFlag() == null)
		throw new UnrecognizedFormatException();//Detect if not CART when writing and escape.
	    p.expectString("*** Flag ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("flag", Integer.class), false);
	    p.expectString("*** VARLOW ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    try {for(int i = 0; i < 17; i++) 
		p.subParseProposedClasses(p.indexedProperty("varlowEntries", VarlowEntry.class, i), ClassInclusion.classOf(VarlowEntry.class));}
	    catch(IndexOutOfBoundsException e) {}//Caught in case of running out of entries while writing.
	} catch(UnrecognizedFormatException e) {}//Optional clause
    }//end describeClassic(...)
    
    private void describeVersion6(Parser p) {
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("lvlFile", String.class), false);
	p.expectString("authorName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("authorName", String.class), false);
	p.expectString("detailed description\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("detailedDescription", String.class), false);
	p.expectString("!Race Track Name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("raceTrackName", String.class), false);
	
	p.expectString("Track Logo .BMP file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackLogoBMP", String.class), false);
	
	p.expectString("Track Race Type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackRaceType", RaceType.class), false);
	p.expectString("@Redbook Audio Track\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("redbookAudioTrack", int.class), false);
	try {
	    if(p.parseMode == ParseMode.WRITE && getAmbientSound() == null)
		throw new UnrecognizedFormatException();
	    p.expectString("!ambient sound,track length,weather mask\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("ambientSound", Integer.class), false);
	    p.stringEndingWith(",", p.property("trackLength", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("weatherMask", int.class), false);
	} catch(UnrecognizedFormatException e) {}//Ignore failure of optional parse
	p.expectString("viewmode,spotd,spotp,spoth,zoom\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "viewMode","spotd","spotp","spoth","zoom");
	try {
	    p.expectString("$racetime, raceStartTime, dragDebugTimer\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "raceTime","raceStartTime","dragDebugTimer");
	    p.expectString("controlflag, autoShift, autoStage, bothStaged, bothStagedPrev\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "controlFlag","autoShift","autoStage","bothStaged","bothStagedPrev");
	    p.expectString("stageComFlag, bonusLapFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "stageComFlag", "bonusLapFlag");
	} catch(UnrecognizedFormatException e) {}
	//No "Your Truck" in version 6 apparently.
	//Vehicles
	p.expectString("*** Vehicles ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numVehicles", int.class), false);
	for(int i = 0 ; i < getNumVehicles(); i++) {
	    p.expectString("Vehicle "+(i+1)+" of "+getNumVehicles()+" -------------------------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("vehicles", Vehicle.class, i), ClassInclusion.classOf(Vehicle7.class));
	}
	
	p.expectString("*** Boxes ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numBoxes", int.class), false);
	for(int i = 0 ; i < numBoxes; i++) {
	    p.expectString(("Box "+(i+1)+" of "+getNumBoxes()+" -----------------------------").substring(0, 40)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("boxes", Box.class, i), ClassInclusion.classOf(Box6.class));
	    }
	p.expectString("%%*** Environmental Lights ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numEnvironmentalLights", int.class), false);
	//Environmental lights
	for(int i = 0 ; i < getNumEnvironmentalLights(); i++) {
	    p.expectString(("Environmental light "+(i+1)+" of "+getNumEnvironmentalLights()+" -------------").substring(0, 40)+"\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("environmentalLights", EnvironmentalLight.class, i), ClassInclusion.classOf(EnvironmentalLight.class));
	}
	//Course definitions
	p.expectString("*** Course ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("c1Count,course_direction\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "numCourseSegments", "courseDirection");
	for(int i = 0 ; i < getNumCourseSegments(); i++) {
	    try {p.expectString("********************************************* "+(i*2+1)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//Variant found in HILCLIM.SET has no number at the end
	    }
	    p.subParseProposedClasses(p.indexedProperty("courseSegments", CourseDefinition.class, i), ClassInclusion.classOf(CourseDefinition.class));
	}
	//Extended course definitions
	p.expectString("@*********** Extended Course Definitions *************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numExtendedCourseSegments", int.class), false);
	for(int i = 0 ; i < numExtendedCourseSegments; i++) {
	    p.expectString("[Course "+(i+1)+"] c1Count,course_direction\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("extendedCourseSegments", ExtendedCourseDefinition.class, i), ClassInclusion.classOf(ExtendedCourseDefinition.class));
	}
	//Paths
	p.expectString("#*** Paths ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("pathCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numPaths", int.class), false);
	for(int i = 0 ; i < getNumPaths(); i++) {
	    p.expectString(("Path "+(i+1)+" of "+getNumPaths()+" ----------------------------").substring(0, 40)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    //                                                  --------------------------
	    p.subParseProposedClasses(p.indexedProperty("paths", Path.class, i), ClassInclusion.classOf(Path.class));
	}
	//Stadium
	p.expectString("*** Stadium ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("!stadiumFlag,x,z,sx,sz,stadiumModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith(",", Integer.class, false, "stadiumFlag","x","z","sx","sz");
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("stadiumModelBINFile", String.class), false);
	
	//Backdrop
	p.expectString("*** Backdrop ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("backdropType,backdropCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(",", p.property("backdropType", int.class), false);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("backdropCount", int.class), false);
	try {p.expectString("backdropModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	for(int i = 0 ; i < getBackdropCount(); i++)
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("backdropModelBINFiles", String.class, i), false);
	}catch(UnrecognizedFormatException e) {}
	
	//Signature
	p.ignoreEOF(true);//Some don't have this part
	p.expectString("signature: ", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(" ", p.property("signaturePrefix", String.class), false);
	p.stringEndingWith(" ", p.property("signatureSuffix", String.class), false);
	
	p.expectString("// WARNING: Do not edit this file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
    }//end describeVersion6
    
    private void describeVersion7(Parser p) {
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("lvlFile", String.class), false);
	p.expectString("authorName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("authorName", String.class), false);
	p.expectString("detailed description\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("detailedDescription", String.class), false);
	p.expectString("!Race Track Name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("raceTrackName", String.class), false);
	
	p.expectString("Track Logo .BMP file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackLogoBMP", String.class), false);
	
	p.expectString("Track Race Type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("trackRaceType", RaceType.class), false);
	p.expectString("@Redbook Audio Track\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("redbookAudioTrack", int.class), false);
	try {
	    if(p.parseMode == ParseMode.WRITE && getAmbientSound() == null)
		throw new UnrecognizedFormatException();
	    p.expectString("!ambient sound,track length,weather mask\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("ambientSound", Integer.class), false);
	    p.stringEndingWith(",", p.property("trackLength", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("weatherMask", int.class), false);
	} catch(UnrecognizedFormatException e) {}//Ignore failure of optional parse
	p.expectString("viewmode,spotd,spotp,spoth,zoom\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "viewMode","spotd","spotp","spoth","zoom");
	try {
	    p.expectString("$racetime, raceStartTime, dragDebugTimer\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "raceTime","raceStartTime","dragDebugTimer");
	    p.expectString("controlflag, autoShift, autoStage, bothStaged, bothStagedPrev\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "controlFlag","autoShift","autoStage","bothStaged","bothStagedPrev");
	    p.expectString("stageComFlag, bonusLapFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", boolean.class, false, "stageComFlag", "bonusLapFlag");
	} catch(UnrecognizedFormatException e) {}
	//No "Your Truck" in version 7 apparently.
	//Vehicles
	p.expectString("*** Vehicles ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numVehicles", int.class), false);
	for(int i = 0 ; i < getNumVehicles(); i++) {
	    p.expectString("Vehicle "+(i+1)+" of "+getNumVehicles()+" -------------------------\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("vehicles", Vehicle.class, i), ClassInclusion.classOf(Vehicle7.class));
	}
	
	p.expectString("*** Boxes ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("// boxTypeList\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numBoxTypes", int.class), false);
	for(int i = 0 ; i < getNumBoxTypes(); i++)
	    p.subParseProposedClasses(p.indexedProperty("boxTypes", BoxType.class, i), ClassInclusion.classOf(BoxType.class));
	p.expectString("// boxCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numBoxes", int.class), false);
	p.expectString("// Box name list\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	for(int i = 0 ; i < getNumBoxes(); i++)
	    p.subParseProposedClasses(p.indexedProperty("boxNames", BoxName.class, i), ClassInclusion.classOf(BoxName.class));
	p.expectString("// Box data list\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	for(int i = 0 ; i < getNumBoxes(); i++) {
	    p.expectString("{ "+(i+1)+" ", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("boxes", Box.class, i), ClassInclusion.nestedClassesOf(Box7.class));
	    }
	
	p.expectString("%%*** Environmental Lights ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numEnvironmentalLights", int.class), false);
	//Environmental lights
	for(int i = 0 ; i < getNumEnvironmentalLights(); i++) {
	    p.expectString(("Environmental light "+(i+1)+" of "+getNumEnvironmentalLights()+" -------------").substring(0, 40)+"\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("environmentalLights", EnvironmentalLight.class, i), ClassInclusion.classOf(EnvironmentalLight.class));
	}
	//Course definitions
	p.expectString("*** Course ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("c1Count,course_direction\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", int.class, false, "numCourseSegments", "courseDirection");
	for(int i = 0 ; i < getNumCourseSegments(); i++) {
	    try {p.expectString("********************************************* "+(i*2+1)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//Variant found in HILCLIM.SET has no number at the end
	    }
	    p.subParseProposedClasses(p.indexedProperty("courseSegments", CourseDefinition.class, i), ClassInclusion.classOf(CourseDefinition.class));
	}
	//Extended course definition
	p.expectString("@*********** Extended Course Definitions *************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numExtendedCourseSegments", int.class), false);
	for(int i = 0 ; i < numExtendedCourseSegments; i++) {
	    p.expectString("[Course "+(i+1)+"] c1Count,course_direction\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.indexedProperty("extendedCourseSegments", ExtendedCourseDefinition.class, i), ClassInclusion.classOf(ExtendedCourseDefinition.class));
	}
	//Paths
	p.expectString("#*** Paths ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("pathCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numPaths", int.class), false);
	for(int i = 0 ; i < getNumPaths(); i++) {
	    p.expectString(("Path "+(i+1)+" of "+getNumPaths()+" ----------------------------").substring(0, 40)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    //                                                  --------------------------
	    p.subParseProposedClasses(p.indexedProperty("paths", Path.class, i), ClassInclusion.classOf(Path.class));
	}
	
	//Stadium
	p.expectString("*** Stadium ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	try {//MTM1 stadium data
	    //Escape if using other form
	    if(p.parseMode == ParseMode.WRITE && (getSx() != null && getX() != null && getZ() != null && getSz() != null))
		throw new UnrecognizedFormatException();
	    p.expectString("stadiumFlag,stadiumModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("stadiumFlag", Integer.class), false);}
	catch(UnrecognizedFormatException e) {
	    //Try MTM2 stadium data
	    p.expectString("!stadiumFlag,x,z,sx,sz,stadiumModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith(",", Integer.class, false, "stadiumFlag","x","z","sx","sz");
	}
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("stadiumModelBINFile", String.class), false);
	p.expectString("*** Backdrop ***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.expectString("backdropType,backdropCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringEndingWith(",", p.property("backdropType", int.class), false);
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("backdropCount", int.class), false);
	try {p.expectString("backdropModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	for(int i = 0 ; i < getBackdropCount(); i++)
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("backdropModelBINFiles", String.class, i), false);
	}catch(UnrecognizedFormatException e) {}
    }//end describeVersion7(...)
    
    public static class VarlowEntry implements ThirdPartyParseable {
	private int unknownInt;
	private double x,y,z;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("unknownInt", int.class), false);
	    p.stringEndingWith(",", p.property("x", double.class), false);
	    p.stringEndingWith(",", p.property("y", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("z", double.class), false);
	}//end describeFormat(...)

	public int getUnknownInt() {
	    return unknownInt;
	}

	public void setUnknownInt(int unknownInt) {
	    this.unknownInt = unknownInt;
	}

	public double getX() {
	    return x;
	}

	public void setX(double x) {
	    this.x = x;
	}

	public double getY() {
	    return y;
	}

	public void setY(double y) {
	    this.y = y;
	}

	public double getZ() {
	    return z;
	}

	public void setZ(double z) {
	    this.z = z;
	}
	
    }//end VarlowEntry
    
    public static class EnvironmentalLight implements ThirdPartyParseable {
	private int lightType, intensity, onMillis, offMillis, parent, sourceOrientation;
	private double positionX, positionY, positionZ, theta, phi, psi, headingSpinSpeed,
			sourceRadius, coneLength, coneBaseRadius, coneRimRadius;
	private String sourceTexture, coneTexture;
	private boolean createsLensFlare;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("lightType", int.class), false);
	    
	    p.expectString("wPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("positionX", double.class), false);
	    p.stringEndingWith(",", p.property("positionY", double.class), false);
	    p.stringEndingWith("\r\n", p.property("positionZ", double.class), false);
	    
	    p.expectString("initWOrient\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("theta", double.class), false);
	    p.stringEndingWith(",", p.property("phi", double.class), false);
	    p.stringEndingWith("\r\n", p.property("psi", double.class), false);
	    
	    p.expectString("headingSpinSpeed\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("headingSpinSpeed", double.class), false);
	    
	    p.expectString("sourceOrient,sourceRadius,sourceTexture\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("sourceOrientation", int.class), false);
	    p.stringEndingWith(",", p.property("sourceRadius", double.class), false);
	    p.stringEndingWith("\r\n", p.property("sourceTexture", String.class), false);
	    
	    p.expectString("coneLength,coneBaseRadius,coneRimRadius,coneTexture\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("coneLength", double.class), false);
	    p.stringEndingWith(",", p.property("coneBaseRadius", double.class), false);
	    p.stringEndingWith(",", p.property("coneRimRadius", double.class), false);
	    p.stringEndingWith("\r\n", p.property("coneTexture", String.class), false);
	    
	    p.expectString("intensity\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("intensity", int.class), false);
	    
	    p.expectString("msOn,msOff\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("onMillis", int.class), false);
	    p.stringEndingWith("\r\n", p.property("offMillis", int.class), false);
	    
	    p.expectString("createsLensFlare\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("createsLensFlare", boolean.class), false);
	    
	    p.expectString("parent\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("parent", int.class), false);
	}//end describeFormat(...)

	public int getLightType() {
	    return lightType;
	}

	public void setLightType(int lightType) {
	    this.lightType = lightType;
	}

	public int getIntensity() {
	    return intensity;
	}

	public void setIntensity(int intensity) {
	    this.intensity = intensity;
	}

	public int getOnMillis() {
	    return onMillis;
	}

	public void setOnMillis(int onMillis) {
	    this.onMillis = onMillis;
	}

	public int getOffMillis() {
	    return offMillis;
	}

	public void setOffMillis(int offMillis) {
	    this.offMillis = offMillis;
	}

	public int getParent() {
	    return parent;
	}

	public void setParent(int parent) {
	    this.parent = parent;
	}

	public double getPositionX() {
	    return positionX;
	}

	public void setPositionX(double positionX) {
	    this.positionX = positionX;
	}

	public double getPositionY() {
	    return positionY;
	}

	public void setPositionY(double positionY) {
	    this.positionY = positionY;
	}

	public double getPositionZ() {
	    return positionZ;
	}

	public void setPositionZ(double positionZ) {
	    this.positionZ = positionZ;
	}

	public double getTheta() {
	    return theta;
	}

	public void setTheta(double theta) {
	    this.theta = theta;
	}

	public double getPhi() {
	    return phi;
	}

	public void setPhi(double phi) {
	    this.phi = phi;
	}

	public double getPsi() {
	    return psi;
	}

	public void setPsi(double psi) {
	    this.psi = psi;
	}

	public double getHeadingSpinSpeed() {
	    return headingSpinSpeed;
	}

	public void setHeadingSpinSpeed(double headingSpinSpeed) {
	    this.headingSpinSpeed = headingSpinSpeed;
	}

	public int getSourceOrientation() {
	    return sourceOrientation;
	}

	public void setSourceOrientation(int sourceOrientation) {
	    this.sourceOrientation = sourceOrientation;
	}

	public double getSourceRadius() {
	    return sourceRadius;
	}

	public void setSourceRadius(double sourceRadius) {
	    this.sourceRadius = sourceRadius;
	}

	public double getConeLength() {
	    return coneLength;
	}

	public void setConeLength(double coneLength) {
	    this.coneLength = coneLength;
	}

	public double getConeBaseRadius() {
	    return coneBaseRadius;
	}

	public void setConeBaseRadius(double coneBaseRadius) {
	    this.coneBaseRadius = coneBaseRadius;
	}

	public double getConeRimRadius() {
	    return coneRimRadius;
	}

	public void setConeRimRadius(double coneRimRadius) {
	    this.coneRimRadius = coneRimRadius;
	}

	public String getSourceTexture() {
	    return sourceTexture;
	}

	public void setSourceTexture(String sourceTexture) {
	    this.sourceTexture = sourceTexture;
	}

	public String getConeTexture() {
	    return coneTexture;
	}

	public void setConeTexture(String coneTexture) {
	    this.coneTexture = coneTexture;
	}

	public boolean isCreatesLensFlare() {
	    return createsLensFlare;
	}

	public void setCreatesLensFlare(boolean createsLensFlare) {
	    this.createsLensFlare = createsLensFlare;
	}
	
    }//end EnvironmentalLight
    
    public static class PathPoint implements ThirdPartyParseable {
	private double positionX, positionY, positionZ, theta, phi, psi;
	private double velocity, pauseTime, time;
	private boolean segmentFlag, orientationFlag, velocityFlag, followTerrainFlag, animationFlag, aiFlag, lightFlag;
	
	private String eventWAVFile, continuousWAVFile;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("wPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("positionX", double.class), false);
	    p.stringEndingWith(",", p.property("positionY", double.class), false);
	    p.stringEndingWith("\r\n", p.property("positionZ", double.class), false);
	    
	    p.expectString("wOrient\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("theta", double.class), false);
	    p.stringEndingWith(",", p.property("phi", double.class), false);
	    p.stringEndingWith("\r\n", p.property("psi", double.class), false);
	    
	    p.expectString("vel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("velocity", double.class), false);
	    p.expectString("time\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("time", double.class), false);
	    p.expectString("pauseTime\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("pauseTime", double.class), false);
	    
	    p.expectString("segmentFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("segmentFlag", boolean.class), false);
	    
	    p.expectString("orientFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("orientationFlag", boolean.class), false);
	    
	    p.expectString("velFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("velocityFlag", boolean.class), false);
	    
	    p.expectString("followTerrainFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("followTerrainFlag", boolean.class), false);
	    
	    p.expectString("animationFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("animationFlag", boolean.class), false);
	    
	    p.expectString("lightFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("lightFlag", boolean.class), false);
	    
	    p.expectString("AIFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("aiFlag", boolean.class), false);
	    
	    p.expectString("pointWavName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("eventWAVFile", String.class), false);
	    
	    p.expectString("continuousWavName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("continuousWAVFile", String.class), false);
	}//end describeFormat(...)

	public double getPositionX() {
	    return positionX;
	}

	public void setPositionX(double positionX) {
	    this.positionX = positionX;
	}

	public double getPositionY() {
	    return positionY;
	}

	public void setPositionY(double positionY) {
	    this.positionY = positionY;
	}

	public double getPositionZ() {
	    return positionZ;
	}

	public void setPositionZ(double positionZ) {
	    this.positionZ = positionZ;
	}

	public double getTheta() {
	    return theta;
	}

	public void setTheta(double theta) {
	    this.theta = theta;
	}

	public double getPhi() {
	    return phi;
	}

	public void setPhi(double phi) {
	    this.phi = phi;
	}

	public double getPsi() {
	    return psi;
	}

	public void setPsi(double psi) {
	    this.psi = psi;
	}

	public double getVelocity() {
	    return velocity;
	}

	public void setVelocity(double velocity) {
	    this.velocity = velocity;
	}

	public double getPauseTime() {
	    return pauseTime;
	}

	public void setPauseTime(double pauseTime) {
	    this.pauseTime = pauseTime;
	}

	public boolean isSegmentFlag() {
	    return segmentFlag;
	}

	public void setSegmentFlag(boolean segmentFlag) {
	    this.segmentFlag = segmentFlag;
	}

	public boolean isOrientationFlag() {
	    return orientationFlag;
	}

	public void setOrientationFlag(boolean orientationFlag) {
	    this.orientationFlag = orientationFlag;
	}

	public boolean isVelocityFlag() {
	    return velocityFlag;
	}

	public void setVelocityFlag(boolean velocityFlag) {
	    this.velocityFlag = velocityFlag;
	}

	public boolean isFollowTerrainFlag() {
	    return followTerrainFlag;
	}

	public void setFollowTerrainFlag(boolean followTerrainFlag) {
	    this.followTerrainFlag = followTerrainFlag;
	}

	public boolean isAnimationFlag() {
	    return animationFlag;
	}

	public void setAnimationFlag(boolean animationFlag) {
	    this.animationFlag = animationFlag;
	}

	public boolean isAiFlag() {
	    return aiFlag;
	}

	public void setAiFlag(boolean aiFlag) {
	    this.aiFlag = aiFlag;
	}

	public String getEventWAVFile() {
	    return eventWAVFile;
	}

	public void setEventWAVFile(String eventWAVFile) {
	    this.eventWAVFile = eventWAVFile;
	}

	public String getContinuousWAVFile() {
	    return continuousWAVFile;
	}

	public void setContinuousWAVFile(String continuousWAVFile) {
	    this.continuousWAVFile = continuousWAVFile;
	}

	public double getTime() {
	    return time;
	}

	public void setTime(double time) {
	    this.time = time;
	}

	public boolean isLightFlag() {
	    return lightFlag;
	}

	public void setLightFlag(boolean lightFlag) {
	    this.lightFlag = lightFlag;
	}
    }//end PathPoint
    
    public static class Path implements ThirdPartyParseable {
	private int numPoints, type, direction, numCycles, stopFlag, collisionType, object;
	private double pauseTimeElapsed, time, t, tension;
	
	private List<PathPoint> pathPoints;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("pointCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("numPoints", int.class), false);
	    p.expectString("type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("type", int.class), false);
	    p.expectString("direction\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("direction", int.class), false);
	    p.expectString("cycles\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("numCycles", int.class), false);
	    p.expectString("time\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("time", double.class), false);
	    p.expectString("pauseTimeElapsed\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("pauseTimeElapsed", double.class), false);
	    p.expectString("t\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("t", double.class), false);
	    p.expectString("tension\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("tension", double.class), false);
	    p.expectString("stopFlag\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("stopFlag", int.class), false);
	    p.expectString("collisionType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("collisionType", int.class), false);
	    p.expectString("object\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("object", int.class), false);
	    
	    for(int i = 0 ; i < getNumPoints(); i++) {
		p.expectString(("Point "+(i+1)+" of "+getNumPoints()+" ---------------------------").substring(0, 40)+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("pathPoints", PathPoint.class, i), ClassInclusion.classOf(PathPoint.class));
	    }
	}//end describeFormat(...)

	public int getNumPoints() {
	    return numPoints;
	}

	public void setNumPoints(int numPoints) {
	    this.numPoints = numPoints;
	}

	public int getType() {
	    return type;
	}

	public void setType(int type) {
	    this.type = type;
	}

	public int getDirection() {
	    return direction;
	}

	public void setDirection(int direction) {
	    this.direction = direction;
	}

	public int getNumCycles() {
	    return numCycles;
	}

	public void setNumCycles(int numCycles) {
	    this.numCycles = numCycles;
	}

	public int getStopFlag() {
	    return stopFlag;
	}

	public void setStopFlag(int stopFlag) {
	    this.stopFlag = stopFlag;
	}

	public int getCollisionType() {
	    return collisionType;
	}

	public void setCollisionType(int collisionType) {
	    this.collisionType = collisionType;
	}

	public int getObject() {
	    return object;
	}

	public void setObject(int object) {
	    this.object = object;
	}

	public double getPauseTimeElapsed() {
	    return pauseTimeElapsed;
	}

	public void setPauseTimeElapsed(double pauseTimeElapsed) {
	    this.pauseTimeElapsed = pauseTimeElapsed;
	}

	public double getT() {
	    return t;
	}

	public void setT(double t) {
	    this.t = t;
	}

	public double getTension() {
	    return tension;
	}

	public void setTension(double tension) {
	    this.tension = tension;
	}

	public void setTime(double time) {
	    this.time = time;
	}

	public List<PathPoint> getPathPoints() {
	    return pathPoints;
	}

	public void setPathPoints(List<PathPoint> pathPoints) {
	    this.pathPoints = pathPoints;
	}

	public double getTime() {
	    return time;
	}
	
    }//end Path
    
    public static class BoxType implements ThirdPartyParseable {
	private int index;
	private String name;
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(" ", p.property("name", String.class), false);
	    p.stringEndingWith("\r\n", p.property("index", int.class), false);
	}
	public int getIndex() {
	    return index;
	}
	public void setIndex(int index) {
	    this.index = index;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
    }//end BoxName
    
    public static class BoxName implements ThirdPartyParseable {
	private int index;
	private String name;
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(" ", p.property("index", int.class), false);
	    p.stringEndingWith("\r\n", p.property("name", String.class), false);
	}
	public int getIndex() {
	    return index;
	}
	public void setIndex(int index) {
	    this.index = index;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
    }//end BoxName
    
    public static class ExtendedCourseDefinition implements ThirdPartyParseable {
	private List<CourseDefinition> extendedCourseDefinitions;
	private int numExtendedCourseDefinitions, courseDirection;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    //p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numExtendedCourseDefinitions", int.class), false);
	    p.stringCSVEndingWith("\r\n", int.class, false, "numExtendedCourseDefinitions","courseDirection");
	    for(int i = 0 ; i < getNumExtendedCourseDefinitions(); i++) {
		p.expectString("********************************************* "+(i*2+1)+"\r\n", null);
		p.subParseProposedClasses(p.indexedProperty("extendedCourseDefinitions", CourseDefinition.class, i), ClassInclusion.classOf(CourseDefinition.class));
	    }//end for(subCourseDefs)
	}//end describeFormat(...)

	public List<CourseDefinition> getExtendedCourseDefinitions() {
	    return extendedCourseDefinitions;
	}

	public void setExtendedCourseDefinitions(
		List<CourseDefinition> extendedCourseDefinitions) {
	    this.extendedCourseDefinitions = extendedCourseDefinitions;
	}

	public int getNumExtendedCourseDefinitions() {
	    return numExtendedCourseDefinitions;
	}

	public void setNumExtendedCourseDefinitions(int numExtendedCourseDefinitions) {
	    this.numExtendedCourseDefinitions = numExtendedCourseDefinitions;
	}

	public int getCourseDirection() {
	    return courseDirection;
	}

	public void setCourseDirection(int courseDirection) {
	    this.courseDirection = courseDirection;
	}
	
    }//end ExtendedCourseDefinition
    
    public static class CourseDefinition implements ThirdPartyParseable {
	private int segmentType, speedType;
	private double startX, startY, startZ, endX, endY, endZ, 
			decPoint, speed, lastEntry, speedLimit, trackWidth;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    pCSV(p, "ctype,cspeed_type",int.class,"segmentType","speedType");
	    pCSV(p, "cstart",double.class,"startX","startY","startZ");
	    pCSV(p, "cend",double.class,"endX","endY","endZ");
	    pCSV(p, "cdec_point,cspeed,lastentry",double.class,"decPoint","speed","lastEntry");
	    pCSV(p, "&cSpeedLimit,cTrackWidth",double.class,"speedLimit","trackWidth");
	}//end describeFormat(...)

	public int getSegmentType() {
	    return segmentType;
	}

	public void setSegmentType(int segmentType) {
	    this.segmentType = segmentType;
	}

	public int getSpeedType() {
	    return speedType;
	}

	public void setSpeedType(int speedType) {
	    this.speedType = speedType;
	}

	public double getStartX() {
	    return startX;
	}

	public void setStartX(double startX) {
	    this.startX = startX;
	}

	public double getStartY() {
	    return startY;
	}

	public void setStartY(double startY) {
	    this.startY = startY;
	}

	public double getStartZ() {
	    return startZ;
	}

	public void setStartZ(double startZ) {
	    this.startZ = startZ;
	}

	public double getEndX() {
	    return endX;
	}

	public void setEndX(double endX) {
	    this.endX = endX;
	}

	public double getEndY() {
	    return endY;
	}

	public void setEndY(double endY) {
	    this.endY = endY;
	}

	public double getEndZ() {
	    return endZ;
	}

	public void setEndZ(double endZ) {
	    this.endZ = endZ;
	}

	public double getDecPoint() {
	    return decPoint;
	}

	public void setDecPoint(double decPoint) {
	    this.decPoint = decPoint;
	}

	public double getSpeed() {
	    return speed;
	}

	public void setSpeed(double speed) {
	    this.speed = speed;
	}

	public boolean isLastEntry() {
	    return getLastEntry() != 0;
	}
	
	public double getLastEntry() {
	    return lastEntry;
	}

	public void setLastEntry(double lastEntry) {
	    this.lastEntry = lastEntry;
	}

	public double getSpeedLimit() {
	    return speedLimit;
	}

	public void setSpeedLimit(double speedLimit) {
	    this.speedLimit = speedLimit;
	}

	public double getTrackWidth() {
	    return trackWidth;
	}

	public void setTrackWidth(double trackWidth) {
	    this.trackWidth = trackWidth;
	}
	
    }//end CourseItem
    
    public static abstract class Placeable implements ThirdPartyParseable {
	private double posX, posY, posZ, theta, phi, psi,
			mass, velX, velY, velZ, p, q, r;
	private String modelBINFile;
	public double getPosX() {
	    return posX;
	}
	public void setPosX(double posX) {
	    this.posX = posX;
	}
	public double getPosY() {
	    return posY;
	}
	public void setPosY(double posY) {
	    this.posY = posY;
	}
	public double getPosZ() {
	    return posZ;
	}
	public void setPosZ(double posZ) {
	    this.posZ = posZ;
	}
	public double getTheta() {
	    return theta;
	}
	public void setTheta(double theta) {
	    this.theta = theta;
	}
	public double getPhi() {
	    return phi;
	}
	public void setPhi(double phi) {
	    this.phi = phi;
	}
	public double getPsi() {
	    return psi;
	}
	public void setPsi(double psi) {
	    this.psi = psi;
	}
	public double getMass() {
	    return mass;
	}
	public void setMass(double mass) {
	    this.mass = mass;
	}
	public double getVelX() {
	    return velX;
	}
	public void setVelX(double velX) {
	    this.velX = velX;
	}
	public double getVelY() {
	    return velY;
	}
	public void setVelY(double velY) {
	    this.velY = velY;
	}
	public double getVelZ() {
	    return velZ;
	}
	public void setVelZ(double velZ) {
	    this.velZ = velZ;
	}
	public double getP() {
	    return p;
	}
	public void setP(double p) {
	    this.p = p;
	}
	public double getQ() {
	    return q;
	}
	public void setQ(double q) {
	    this.q = q;
	}
	public double getR() {
	    return r;
	}
	public void setR(double r) {
	    this.r = r;
	}
	public String getModelBINFile() {
	    return modelBINFile;
	}
	public void setModelBINFile(String modelBINFile) {
	    this.modelBINFile = modelBINFile;
	}
    }//end Placeable
    
    public static class TopCrush extends Placeable {
	private String cabModelBINFile;
	private double pos2X, pos2Y, pos2Z;
	private double length,width,height,length2,width2,height2;
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.expectString("ipos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("ipos2\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "pos2X", "pos2Y", "pos2Z");
	    p.expectString("theta,phi,psi\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    try {
		pCSV(p,"length,width,height",double.class, "length","width","height");
		pCSV(p,"length2,width2,height2",double.class, "length2","width2","height2");
	    } catch(UnrecognizedFormatException e) {
		pCSV(p,"modelName", String.class, "modelBINFile");
		pCSV(p,"cabModelName", String.class,"cabModelBINFile");
	    }
	    pCSV(p,"mass", double.class, "mass");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	}//end describeFormat(...)
	
	public String getCabModelBINFile() {
	    return cabModelBINFile;
	}
	public void setCabModelBINFile(String cabModelBINFile) {
	    this.cabModelBINFile = cabModelBINFile;
	}

	public double getPos2X() {
	    return pos2X;
	}

	public void setPos2X(double pos2X) {
	    this.pos2X = pos2X;
	}

	public double getPos2Y() {
	    return pos2Y;
	}

	public void setPos2Y(double pos2Y) {
	    this.pos2Y = pos2Y;
	}

	public double getPos2Z() {
	    return pos2Z;
	}

	public void setPos2Z(double pos2Z) {
	    this.pos2Z = pos2Z;
	}

	public double getLength() {
	    return length;
	}

	public void setLength(double length) {
	    this.length = length;
	}

	public double getWidth() {
	    return width;
	}

	public void setWidth(double width) {
	    this.width = width;
	}

	public double getHeight() {
	    return height;
	}

	public void setHeight(double height) {
	    this.height = height;
	}

	public double getLength2() {
	    return length2;
	}

	public void setLength2(double length2) {
	    this.length2 = length2;
	}

	public double getWidth2() {
	    return width2;
	}

	public void setWidth2(double width2) {
	    this.width2 = width2;
	}

	public double getHeight2() {
	    return height2;
	}

	public void setHeight2(double height2) {
	    this.height2 = height2;
	}
    }//end TopCrush
    
    public static class Ramp extends Placeable {
	private Double length, width, height;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.expectString("ipos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("theta,phi,psi\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    //TODO: Detect if length,width,height not set on write and throw UFE
	    try 					{
		if(p.parseMode == ParseMode.WRITE && (getLength() == null || getWidth() == null || getHeight() == null))
		    throw new UnrecognizedFormatException("Entry apparently not present in bean being written.");
		pCSV(p,"length,width,height",Double.class,"length","width","height");}
	    catch(UnrecognizedFormatException e) 	{pCSV(p,"model", String.class, "modelBINFile");}
	    
	    pCSV(p,"mass", double.class, "mass");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	}//end describeFormat(...)

	public Double getLength() {
	    return length;
	}

	public void setLength(Double length) {
	    this.length = length;
	}

	public Double getWidth() {
	    return width;
	}

	public void setWidth(Double width) {
	    this.width = width;
	}

	public Double getHeight() {
	    return height;
	}

	public void setHeight(Double height) {
	    this.height = height;
	}
	
    }//end Ramp
    
    public static abstract class Box7 extends Box6 {
	private String name;
	private int collisionType;
	
	protected abstract String getBoxTag();
	protected abstract void describeInnerFormat(Parser p);
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
		p.expectString(getBoxTag()+"\r\n\t\"", FailureBehavior.UNRECOGNIZED_FORMAT);
		try {
		    describeInnerFormat(p);
		} catch(Exception e) {e.printStackTrace(); throw e;}

		p.expectString("} "+getBoxTag()+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	}//end describeFormat(...)
	
	public static class FlyingObjectBox extends Box7 {
		@Override
		public String getBoxTag() {return "CFlyingObject";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n\t", p.property("parent", int.class), false);
		    p.stringEndingWith("    // timePerFrame\r\n", p.property("timePerFrame", double.class), false);
		    
		}//end describeInnerFormat(...)
	    }//end FlyingObjectBox
	
	public static class TrainBox extends Box7 {
		@Override
		public String getBoxTag() {return "CTrain";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n\t", p.property("parent", int.class), false);
		    p.stringEndingWith("    // timePerFrame\r\n\t", p.property("timePerFrame", double.class), false);
		    
		    p.stringEndingWith(",", p.property("velX", double.class), false);
		    p.stringEndingWith(",", p.property("velY", double.class), false);
		    p.stringEndingWith("    // trainVelocity\r\n", p.property("velZ", double.class), false);
		}//end describeInnerFormat(...)
	    }//end TrainBox
	
	public static class TreasureBox extends Box7 {
		@Override
		public String getBoxTag() {return "CTreasure";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n\t", p.property("parent", int.class), false);
		    p.stringEndingWith("    // timePerFrame\r\n", p.property("timePerFrame", double.class), false);
		}//end describeInnerFormat(...)
	    }//end TreasureBox
	
	public static class CheckpointBox extends Box7 {
		@Override
		public String getBoxTag() {return "CCheckpoint";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n", p.property("parent", int.class), false);
		}//end describeInnerFormat(...)
	    }//end CheckpointBox

	public static class TriggerBox extends Box7 {
		@Override
		public String getBoxTag() {return "CTriggerBox";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n", p.property("parent", int.class), false);
		}//end describeInnerFormat(...)
	    }//end TriggerBox
	
	public static class NonCollideBox extends Box7 {
		@Override
		public String getBoxTag() {return "CNonCollide";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n\t", p.property("parent", int.class), false);
		    p.stringEndingWith("    // timePerFrame\r\n\t", p.property("timePerFrame", double.class), false);
		    p.stringEndingWith("    // castShadowOnMe\r\n", p.property("castShadowOnMe", boolean.class), false);
		}//end describeInnerFormat(...)
	    }//end NonCollideBox
	    
	    public static class CollisionBox extends Box7 {
		@Override
		public String getBoxTag() {return "CCollisionBox";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n", p.property("parent", int.class), false);
		}//end describeInnerFormat(...)
	    }//end CollisionBox
	    
	    public static class Collide extends Box7 {
		
		@Override
		public String getBoxTag() {return "CCollide";}
		
		@Override
		public void describeInnerFormat(Parser p)
			throws UnrecognizedFormatException {
		    p.stringEndingWith("\"    // name\r\n\t\"", p.property("name", String.class), false);
		    p.stringEndingWith("\"    // staticName\r\n\t", p.property("modelBINFile", String.class), false);
		    
		    p.stringEndingWith(",", p.property("length", double.class), false);
		    p.stringEndingWith(",", p.property("width", double.class), false);
		    p.stringEndingWith("    // size\r\n\t", p.property("height", double.class), false);
		    
		    p.stringEndingWith(",", p.property("posX", double.class), false);
		    p.stringEndingWith(",", p.property("posY", double.class), false);
		    p.stringEndingWith("    // wPos\r\n\t", p.property("posZ", double.class), false);
		    
		    p.stringEndingWith(",", p.property("theta", double.class), false);
		    p.stringEndingWith(",", p.property("phi", double.class), false);
		    p.stringEndingWith("    // wOrient\r\n\t", p.property("psi", double.class), false);
		    
		    p.stringEndingWith("    // mass\r\n\t", p.property("mass", double.class), false);
		    p.stringEndingWith("    // priority\r\n\t\"", p.property("priority", int.class), false);
		    
		    p.stringEndingWith("\"    // soundEffectWavName\r\n\t\"", p.property("sfxWAVFile", String.class), false);
		    p.stringEndingWith("\"    // continuousWavName\r\n\t", p.property("continuousWAVFile", String.class), false);
		    
		    p.stringEndingWith("    // parent\r\n\t", p.property("parent", int.class), false);
		    p.stringEndingWith("    // timePerFrame\r\n\t", p.property("timePerFrame", double.class), false);
		    p.stringEndingWith("    // castShadowOnMe\r\n\t", p.property("castShadowOnMe", boolean.class), false);
		    p.stringEndingWith("    // collisionType\r\n", p.property("collisionType", int.class), false);
		}//end describeInnerFormat(...)
	    }//end Collide

	public String getDummy() {return "";}
	public void setDummy(String d) {}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getCollisionType() {
	    return collisionType;
	}

	public void setCollisionType(int collisionType) {
	    this.collisionType = collisionType;
	}
    }//end Box7
    
    public static class Box6 extends Box {
	private double timePerFrame;
	private boolean castShadowOnMe;
	private int parent;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("wPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("wOrient\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    
	    //Model or dims
	    try {pCSV(p,"model", String.class, "modelBINFile");}
	    catch(UnrecognizedFormatException e) {
		pCSV(p,"length,width,height", Double.class, "length","width","height");
	    }
	    
	    pCSV(p,"mass", double.class, "mass");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	    pCSV(p, "!type,flags", int.class, "boxType", "boxFlags");
	    pCSV(p,"priority", int.class, "priority");
	    p.expectString("@sound effect entries\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("sfxWAVFile", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("continuousWAVFile", String.class), false);
	    p.expectString("#parent\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("parent", int.class), false);
	    p.expectString("$timePerFrame\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("timePerFrame", double.class), false);
	    p.expectString("%castShadowOnMe\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("castShadowOnMe", boolean.class), false);
	}//end describeFormat(...)

	public double getTimePerFrame() {
	    return timePerFrame;
	}

	public void setTimePerFrame(double timePerFrame) {
	    this.timePerFrame = timePerFrame;
	}

	public boolean isCastShadowOnMe() {
	    return castShadowOnMe;
	}

	public void setCastShadowOnMe(boolean castShadowOnMe) {
	    this.castShadowOnMe = castShadowOnMe;
	}

	public int getParent() {
	    return parent;
	}

	public void setParent(int parent) {
	    this.parent = parent;
	}//end describeFormat(...)
    }//end Box6
    
    public static class Box extends Placeable {
	private String sfxWAVFile, continuousWAVFile;
	private int boxType, boxFlags, priority, unknown1, unknown2;
	//private String modelBIN;
	private double length, width, height;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("ipos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("theta,phi,psi\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    
	    //Model or dims
	    try {pCSV(p,"model", String.class, "modelBINFile");}
	    catch(UnrecognizedFormatException e) {
		pCSV(p,"length,width,height", Double.class, "length","width","height");
	    }
	    
	    pCSV(p,"mass", double.class, "mass");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	    pCSV(p, "!type,flags", int.class, "boxType", "boxFlags");
	    pCSV(p,"priority", int.class, "priority");
	    p.expectString("@sound effect entries\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("sfxWAVFile", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("continuousWAVFile", String.class), false);
	    p.stringCSVEndingWith("\r\n", int.class, false, "unknown1","unknown2");
	}//end describeFormat(...)

	public String getSfxWAVFile() {
	    return sfxWAVFile;
	}

	public void setSfxWAVFile(String sfxWAVFile) {
	    this.sfxWAVFile = sfxWAVFile;
	}

	public String getContinuousWAVFile() {
	    return continuousWAVFile;
	}

	public void setContinuousWAVFile(String unknownWAVFile) {
	    this.continuousWAVFile = unknownWAVFile;
	}

	public int getBoxType() {
	    return boxType;
	}

	public void setBoxType(int boxType) {
	    this.boxType = boxType;
	}

	public int getBoxFlags() {
	    return boxFlags;
	}

	public void setBoxFlags(int boxFlags) {
	    this.boxFlags = boxFlags;
	}

	public int getPriority() {
	    return priority;
	}

	public void setPriority(int priority) {
	    this.priority = priority;
	}

	public int getUnknown1() {
	    return unknown1;
	}

	public void setUnknown1(int unknown1) {
	    this.unknown1 = unknown1;
	}

	public int getUnknown2() {
	    return unknown2;
	}

	public void setUnknown2(int unknown2) {
	    this.unknown2 = unknown2;
	}

	public double getLength() {
	    return length;
	}

	public void setLength(double length) {
	    this.length = length;
	}

	public double getWidth() {
	    return width;
	}

	public void setWidth(double width) {
	    this.width = width;
	}

	public double getHeight() {
	    return height;
	}

	public void setHeight(double height) {
	    this.height = height;
	}
	
    }//end Box
    
    public static class Vehicle7 extends Vehicle {
	@Override
	public void describeFormat(Parser p) {
	    p.expectString("staticName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("truckFile", String.class), false);
	    p.expectString("wPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("wOrient\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	    
	    pCSV(p,"faxle.angle,faxle.steering_angle",		double.class,	"frontAxleAngle","frontAxleSteeringAngle");
	    pCSV(p,"faxle.rtire.on_gnd,faxle.ltire.on_gnd",	int.class,	"frontAxleRightTireOnGround","frontAxleLeftTireOnGround");
	    pCSV(p,"raxle.angle,raxle.steering_angle",		double.class,	"rearAxleAngle","rearAxleSteeringAngle");
	    pCSV(p,"raxle.rtire.on_gnd,raxle.ltire.on_gnd",	int.class,	"rearAxleRightTireOnGround","rearAxleLeftTireOnGround");
	    pCSV(p,"xm.gear",int.class,"transmissionGear");
	    pCSV(p,"ap.autopilot,ap.cnumber",int.class,"autopilotEnabled","autopilotNumber");
	    pCSV(p,"ap.speed_control,ap.course_control,ap.lasterror",double.class,"autopilotSpeedControl","autopilotCourseControl","autopilotLastError");
	    pCSV(p,"!ap.courseToFollow",int.class,"autopilotCourseToFollow");
	    try {//Optional MTM2 item
		if(p.parseMode == ParseMode.WRITE && getDamageCode() == null)
		    throw new UnrecognizedFormatException();
		pCSV(p,"@damageCode",Integer.class,"damageCode");
	    }catch(UnrecognizedFormatException e) {}
	    pCSV(p,"$heliTimer,heliTheta,heliPhi,heliPsi",double.class,"helicopterTimer","helicopterTheta","helicopterPhi","helicopterPsi");
	    pCSV(p,"heliPos",double.class,"heliPosX","heliPosY","heliPosZ");
	    try {//Optional entries
		if(p.parseMode == ParseMode.WRITE && getLapTimes() == null)
		    throw new UnrecognizedFormatException();
		pCSV(p,"^segments,laps,staged,bonusLaps,finishedRace,nextcheckpoint",int.class,"numSegments","numLaps","numStaged","numBonusLaps","raceFinished","nextCheckpoint");
		pCSV(p,"totalracetime,fastestLap,dragTimer",double.class,"totalRaceTime","fastestLap","dragTimer");
		for(int i = 0; i < 20; i++)
		    p.subParseProposedClasses(p.indexedProperty("lapTimes", LapTime.class, i), ClassInclusion.classOf(LapTime.class));
	    } catch(UnrecognizedFormatException e) {}
	}
    }//end Vehicle7
    
    public static class Vehicle extends Placeable {
	private List<LapTime> lapTimes;
	private double frontAxleAngle, frontAxleSteeringAngle;
	private int frontAxleRightTireOnGround, frontAxleLeftTireOnGround;
	
	private double rearAxleAngle, rearAxleSteeringAngle;
	private int rearAxleRightTireOnGround, rearAxleLeftTireOnGround;
	
	private int transmissionGear, autopilotEnabled, autopilotNumber;
	private double autopilotSpeedControl, autopilotCourseControl, autopilotLastError, performanceScalar, deadCarTimer;
	private int autopilotCourseToFollow;
	
	double helicopterTimer, helicopterTheta, helicopterPhi, helicopterPsi, heliPosX, heliPosY, heliPosZ;
	private int numLaps, numStaged, numBonusLaps, raceFinished, nextCheckpoint;
	private Integer damageCode, numSegments;
	private double totalRaceTime, fastestLap, dragTimer;
	
	private String truckFile;
	
	private int guyToFollow, place, pit, unknownCTF0, modelStatus, currentPitStop;
	private double lineOffset, pitTimer, unknownCTF1, unknownCTF2, unknownCTF3;

	private String driverName;
	private List<CARTLapTimeRow> cartLapTimeRows;
	
	private boolean deadCar;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    //VEHICLE STATE BLOCK
	    p.expectString("*********************************************\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    ////////////////*********************************************
	    try{p.expectString("truckFile\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {
		p.expectString("vehicleFile\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    }
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("truckFile", String.class), false);
	    p.expectString("ipos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "posX", "posY", "posZ");
	    p.expectString("bvel\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "velX", "velY", "velZ");
	    p.expectString("theta,phi,psi\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "theta", "phi", "psi");
	    p.expectString("p,q,r\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringCSVEndingWith("\r\n", double.class, false, "p","q","r");
	    
	    pCSV(p,"faxle.angle,faxle.steering_angle",		double.class,	"frontAxleAngle","frontAxleSteeringAngle");
	    pCSV(p,"faxle.rtire.on_gnd,faxle.ltire.on_gnd",	int.class,	"frontAxleRightTireOnGround","frontAxleLeftTireOnGround");
	    pCSV(p,"raxle.angle,raxle.steering_angle",		double.class,	"rearAxleAngle","rearAxleSteeringAngle");
	    pCSV(p,"raxle.rtire.on_gnd,raxle.ltire.on_gnd",	int.class,	"rearAxleRightTireOnGround","rearAxleLeftTireOnGround");
	    pCSV(p,"xm.gear",int.class,"transmissionGear");
	    pCSV(p,"ap.autopilot,ap.cnumber",int.class,"autopilotEnabled","autopilotNumber");
	    pCSV(p,"ap.speed_control,ap.course_control,ap.lasterror",double.class,"autopilotSpeedControl","autopilotCourseControl","autopilotLastError");
	    pCSV(p,"!ap.courseToFollow",int.class,"autopilotCourseToFollow");
	    try {//Optional MTM2 item
		if(p.parseMode == ParseMode.WRITE && getDamageCode() == null)
		    throw new UnrecognizedFormatException();
		pCSV(p,"@damageCode",Integer.class,"damageCode");
	    }catch(UnrecognizedFormatException e) {}
	    //Found in CART
	    try {//Skip if not CART while writing. If driver name is null, assume not CART
		if(p.parseMode == ParseMode.WRITE && getDriverName() == null)
		    throw new UnrecognizedFormatException();
		p.expectString("@ap.guy2follow,ap.lineOffset,ap.place,ap.pit\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(",", p.property("guyToFollow", int.class), false);
		p.stringEndingWith(",", p.property("lineOffset", double.class), false);
		p.stringEndingWith(",", p.property("place", int.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("pit", int.class), false);
		
		p.expectString("ap.pitTimer,ap.pitCTF,ap.pitOffset\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(",", p.property("pitTimer", double.class), false);
		p.stringEndingWith(",", p.property("unknownCTF0", int.class), false);
		p.stringEndingWith(",", p.property("unknownCTF1", double.class), false);
		p.stringEndingWith(",", p.property("unknownCTF2", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownCTF3", double.class), false);
		
		p.expectString("driverName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("driverName", String.class), false);
		
		p.expectString("modelStatus\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("modelStatus", int.class), false);
		
		pCSV(p,"&segments,laps,bonusLaps,finishedRace,nextcheckpoint",int.class,"numSegments","numLaps","numBonusLaps","raceFinished","nextCheckpoint");
		pCSV(p,"totalracetime,fastestLap",double.class,"totalRaceTime","fastestLap");
		
		p.expectString("^currentPitStop\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("currentPitStop", int.class), false);
		try {//Optional in CART
		    p.expectString("&performanceScalar,deadCar,deadCarTimer\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.stringEndingWith(",", p.property("performanceScalar", double.class), false);
			p.stringEndingWith(",", p.property("deadCar", boolean.class), false);
			p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("deadCarTimer", double.class), false);
		} catch(UnrecognizedFormatException e) {}
		p.expectString("***Lap time***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		for(int i = 0; i < 30; i++)
			p.subParseProposedClasses(p.indexedProperty("cartLapTimeRows", CARTLapTimeRow.class, i), ClassInclusion.classOf(CARTLapTimeRow.class));
	    } catch(UnrecognizedFormatException e0) {//Found in MTM and EVO
		pCSV(p,"$heliTimer,heliTheta,heliPhi,heliPsi",double.class,"helicopterTimer","helicopterTheta","helicopterPhi","helicopterPsi");
		pCSV(p,"heliPos",double.class,"heliPosX","heliPosY","heliPosZ");
		try {//Optional entries
		    if(p.parseMode == ParseMode.WRITE && getNumSegments() == null)
			throw new UnrecognizedFormatException();
		    pCSV(p,"^segments,laps,staged,bonusLaps,finishedRace,nextcheckpoint",int.class,"numSegments","numLaps","numStaged","numBonusLaps","raceFinished","nextCheckpoint");
		    pCSV(p,"totalracetime,fastestLap,dragTimer",double.class,"totalRaceTime","fastestLap","dragTimer");
		    for(int i = 0; i < 20; i++)
			p.subParseProposedClasses(p.indexedProperty("lapTimes", LapTime.class, i), ClassInclusion.classOf(LapTime.class));
		} catch(UnrecognizedFormatException e1) {}
	    }//end MTM/EVO
	}//end describeFormat(...)

	public List<LapTime> getLapTimes() {
	    return lapTimes;
	}

	public void setLapTimes(List<LapTime> lapTimes) {
	    this.lapTimes = lapTimes;
	}

	public double getFrontAxleAngle() {
	    return frontAxleAngle;
	}

	public void setFrontAxleAngle(double frontAxleAngle) {
	    this.frontAxleAngle = frontAxleAngle;
	}

	public double getFrontAxleSteeringAngle() {
	    return frontAxleSteeringAngle;
	}

	public void setFrontAxleSteeringAngle(double frontAxleSteeringAngle) {
	    this.frontAxleSteeringAngle = frontAxleSteeringAngle;
	}

	public int getFrontAxleRightTireOnGround() {
	    return frontAxleRightTireOnGround;
	}

	public void setFrontAxleRightTireOnGround(int frontAxleRightTireOnGround) {
	    this.frontAxleRightTireOnGround = frontAxleRightTireOnGround;
	}

	public int getFrontAxleLeftTireOnGround() {
	    return frontAxleLeftTireOnGround;
	}

	public void setFrontAxleLeftTireOnGround(int frontAxleLeftTireOnGround) {
	    this.frontAxleLeftTireOnGround = frontAxleLeftTireOnGround;
	}

	public double getRearAxleAngle() {
	    return rearAxleAngle;
	}

	public void setRearAxleAngle(double rearAxleAngle) {
	    this.rearAxleAngle = rearAxleAngle;
	}

	public double getRearAxleSteeringAngle() {
	    return rearAxleSteeringAngle;
	}

	public void setRearAxleSteeringAngle(double rearAxleSteeringAngle) {
	    this.rearAxleSteeringAngle = rearAxleSteeringAngle;
	}

	public int getRearAxleRightTireOnGround() {
	    return rearAxleRightTireOnGround;
	}

	public void setRearAxleRightTireOnGround(int rearAxleRightTireOnGround) {
	    this.rearAxleRightTireOnGround = rearAxleRightTireOnGround;
	}

	public int getRearAxleLeftTireOnGround() {
	    return rearAxleLeftTireOnGround;
	}

	public void setRearAxleLeftTireOnGround(int rearAxleLeftTireOnGround) {
	    this.rearAxleLeftTireOnGround = rearAxleLeftTireOnGround;
	}

	public int getTransmissionGear() {
	    return transmissionGear;
	}

	public void setTransmissionGear(int transmissionGear) {
	    this.transmissionGear = transmissionGear;
	}

	public int getAutopilotEnabled() {
	    return autopilotEnabled;
	}

	public void setAutopilotEnabled(int autopilotEnabled) {
	    this.autopilotEnabled = autopilotEnabled;
	}

	public int getAutopilotNumber() {
	    return autopilotNumber;
	}

	public void setAutopilotNumber(int autopilotNumber) {
	    this.autopilotNumber = autopilotNumber;
	}

	public double getAutopilotSpeedControl() {
	    return autopilotSpeedControl;
	}

	public void setAutopilotSpeedControl(double autopilotSpeedControl) {
	    this.autopilotSpeedControl = autopilotSpeedControl;
	}

	public double getAutopilotCourseControl() {
	    return autopilotCourseControl;
	}

	public void setAutopilotCourseControl(double autopilotCourseControl) {
	    this.autopilotCourseControl = autopilotCourseControl;
	}

	public double getAutopilotLastError() {
	    return autopilotLastError;
	}

	public void setAutopilotLastError(double autopilotLastError) {
	    this.autopilotLastError = autopilotLastError;
	}

	public double getHelicopterTimer() {
	    return helicopterTimer;
	}

	public void setHelicopterTimer(double helicopterTimer) {
	    this.helicopterTimer = helicopterTimer;
	}

	public double getHelicopterTheta() {
	    return helicopterTheta;
	}

	public void setHelicopterTheta(double helicopterTheta) {
	    this.helicopterTheta = helicopterTheta;
	}

	public double getHelicopterPsi() {
	    return helicopterPsi;
	}

	public void setHelicopterPsi(double helicopterPsi) {
	    this.helicopterPsi = helicopterPsi;
	}

	public double getHeliPosX() {
	    return heliPosX;
	}

	public void setHeliPosX(double heliPosX) {
	    this.heliPosX = heliPosX;
	}

	public double getHeliPosY() {
	    return heliPosY;
	}

	public void setHeliPosY(double heliPosY) {
	    this.heliPosY = heliPosY;
	}

	public double getHeliPosZ() {
	    return heliPosZ;
	}

	public void setHeliPosZ(double heliPosZ) {
	    this.heliPosZ = heliPosZ;
	}

	public Integer getNumSegments() {
	    return numSegments;
	}

	public void setNumSegments(Integer numSegments) {
	    this.numSegments = numSegments;
	}

	public int getNumLaps() {
	    return numLaps;
	}

	public void setNumLaps(int numLaps) {
	    this.numLaps = numLaps;
	}

	public int getNumStaged() {
	    return numStaged;
	}

	public void setNumStaged(int numStaged) {
	    this.numStaged = numStaged;
	}

	public int getNumBonusLaps() {
	    return numBonusLaps;
	}

	public void setNumBonusLaps(int numBonusLaps) {
	    this.numBonusLaps = numBonusLaps;
	}

	public int getRaceFinished() {
	    return raceFinished;
	}

	public void setRaceFinished(int raceFinished) {
	    this.raceFinished = raceFinished;
	}

	public int getNextCheckpoint() {
	    return nextCheckpoint;
	}

	public void setNextCheckpoint(int nextCheckpoint) {
	    this.nextCheckpoint = nextCheckpoint;
	}

	public double getTotalRaceTime() {
	    return totalRaceTime;
	}

	public void setTotalRaceTime(double totalRaceTime) {
	    this.totalRaceTime = totalRaceTime;
	}

	public double getFastestLap() {
	    return fastestLap;
	}

	public void setFastestLap(double fastestLap) {
	    this.fastestLap = fastestLap;
	}

	public double getDragTimer() {
	    return dragTimer;
	}

	public void setDragTimer(double dragTimer) {
	    this.dragTimer = dragTimer;
	}

	public double getHelicopterPhi() {
	    return helicopterPhi;
	}

	public void setHelicopterPhi(double helicopterPhi) {
	    this.helicopterPhi = helicopterPhi;
	}

	public Integer getDamageCode() {
	    return damageCode;
	}

	public void setDamageCode(Integer damageCode) {
	    this.damageCode = damageCode;
	}

	public int getAutopilotCourseToFollow() {
	    return autopilotCourseToFollow;
	}

	public void setAutopilotCourseToFollow(int autopilotCourseToFollow) {
	    this.autopilotCourseToFollow = autopilotCourseToFollow;
	}

	public String getTruckFile() {
	    return truckFile;
	}

	public void setTruckFile(String truckFile) {
	    this.truckFile = truckFile;
	}

	public int getGuyToFollow() {
	    return guyToFollow;
	}

	public void setGuyToFollow(int guyToFollow) {
	    this.guyToFollow = guyToFollow;
	}

	public int getPlace() {
	    return place;
	}

	public void setPlace(int place) {
	    this.place = place;
	}

	public int getPit() {
	    return pit;
	}

	public void setPit(int pit) {
	    this.pit = pit;
	}

	public int getUnknownCTF0() {
	    return unknownCTF0;
	}

	public void setUnknownCTF0(int unknownCTF0) {
	    this.unknownCTF0 = unknownCTF0;
	}

	public int getModelStatus() {
	    return modelStatus;
	}

	public void setModelStatus(int modelStatus) {
	    this.modelStatus = modelStatus;
	}

	public int getCurrentPitStop() {
	    return currentPitStop;
	}

	public void setCurrentPitStop(int currentPitStop) {
	    this.currentPitStop = currentPitStop;
	}

	public double getLineOffset() {
	    return lineOffset;
	}

	public void setLineOffset(double lineOffset) {
	    this.lineOffset = lineOffset;
	}

	public double getPitTimer() {
	    return pitTimer;
	}

	public void setPitTimer(double pitTimer) {
	    this.pitTimer = pitTimer;
	}

	public double getUnknownCTF2() {
	    return unknownCTF2;
	}

	public void setUnknownCTF2(double unknownCTF2) {
	    this.unknownCTF2 = unknownCTF2;
	}

	public String getDriverName() {
	    return driverName;
	}

	public void setDriverName(String driverName) {
	    this.driverName = driverName;
	}

	public double getUnknownCTF1() {
	    return unknownCTF1;
	}

	public void setUnknownCTF1(double unknownCTF1) {
	    this.unknownCTF1 = unknownCTF1;
	}

	public double getUnknownCTF3() {
	    return unknownCTF3;
	}

	public void setUnknownCTF3(double unknownCTF3) {
	    this.unknownCTF3 = unknownCTF3;
	}

	public List<CARTLapTimeRow> getCartLapTimeRows() {
	    return cartLapTimeRows;
	}

	public void setCartLapTimeRows(List<CARTLapTimeRow> cartLapTimeRows) {
	    this.cartLapTimeRows = cartLapTimeRows;
	}

	public double getPerformanceScalar() {
	    return performanceScalar;
	}

	public void setPerformanceScalar(double performanceScalar) {
	    this.performanceScalar = performanceScalar;
	}

	public double getDeadCarTimer() {
	    return deadCarTimer;
	}

	public void setDeadCarTimer(double deadCarTimer) {
	    this.deadCarTimer = deadCarTimer;
	}

	public boolean isDeadCar() {
	    return deadCar;
	}

	public void setDeadCar(boolean deadCar) {
	    this.deadCar = deadCar;
	}

    }//end Vehicle
    
    public static class CARTLapTimeRow implements ThirdPartyParseable {
	private List<Double> lapTimeRow;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    for(int i = 0 ; i < 9; i++) {
		p.stringEndingWith(",", p.indexedProperty("lapTimeRow", Double.class, i), false);
	    }
	    p.stringEndingWith("\r\n", p.indexedProperty("lapTimeRow", Double.class, 9), false);
	}//end describeFormat(...)

	public List<Double> getLapTimeRow() {
	    return lapTimeRow;
	}

	public void setLapTimeRow(List<Double> lapTimeRow) {
	    this.lapTimeRow = lapTimeRow;
	}
	
    }//end CARTLapTimeRow
    
    public static class LapTime implements ThirdPartyParseable {
	private List<CheckpointTime> checkpointTimes;
	private double lapTime;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("***Lap time***\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("lapTime", double.class), false);
	    p.expectString("*****Checkpoint times*****\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0 ; i < 20; i++)
		p.subParseProposedClasses(
			p.indexedProperty("checkpointTimes", CheckpointTime.class, i),
			ClassInclusion.classOf(CheckpointTime.class));
	}

	public List<CheckpointTime> getCheckpointTimes() {
	    return checkpointTimes;
	}

	public void setCheckpointTimes(List<CheckpointTime> checkpointTimes) {
	    this.checkpointTimes = checkpointTimes;
	}

	public double getLapTime() {
	    return lapTime;
	}

	public void setLapTime(double lapTime) {
	    this.lapTime = lapTime;
	}
    }//end LapTime
    
    public static class CheckpointTime implements ThirdPartyParseable {
	private double checkpointTime;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("checkpointTime", double.class), false);
	}

	public double getCheckpointTime() {
	    return checkpointTime;
	}

	public void setCheckpointTime(double checkpointTime) {
	    this.checkpointTime = checkpointTime;
	}
	
    }//end CheckpointTime
    
    private static void pCSV(Parser p, String key, Class<?> propertyClass, String ... pNames) {
	p.expectString(key+"\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	p.stringCSVEndingWith("\r\n", propertyClass, false, pNames);
	
    }

    public String getLvlFile() {
        return lvlFile;
    }

    public void setLvlFile(String lvlFile) {
        this.lvlFile = lvlFile;
    }

    public String getRaceTrackName() {
        return raceTrackName;
    }

    public void setRaceTrackName(String raceTrackName) {
        this.raceTrackName = raceTrackName;
    }

    public String getRaceTrackLocale() {
        return raceTrackLocale;
    }

    public void setRaceTrackLocale(String raceTrackLocale) {
        this.raceTrackLocale = raceTrackLocale;
    }

    public String getTrackLogoBMP() {
        return trackLogoBMP;
    }

    public void setTrackLogoBMP(String trackLogoBMP) {
        this.trackLogoBMP = trackLogoBMP;
    }

    public String getTrackMapBMP() {
        return trackMapBMP;
    }

    public void setTrackMapBMP(String trackMapBMP) {
        this.trackMapBMP = trackMapBMP;
    }

    public String getTrackFlybyAVI() {
        return trackFlybyAVI;
    }

    public void setTrackFlybyAVI(String trackFlybyAVI) {
        this.trackFlybyAVI = trackFlybyAVI;
    }

    public String getTrackDescriptionTXT() {
        return trackDescriptionTXT;
    }

    public void setTrackDescriptionTXT(String trackDescriptionTXT) {
        this.trackDescriptionTXT = trackDescriptionTXT;
    }

    public RaceType getTrackRaceType() {
        return trackRaceType;
    }

    public void setTrackRaceType(RaceType trackRaceType) {
        this.trackRaceType = trackRaceType;
    }

    public int getTrackLongitude() {
        return trackLongitude;
    }

    public void setTrackLongitude(int trackLongitude) {
        this.trackLongitude = trackLongitude;
    }

    public int getTrackLatitude() {
        return trackLatitude;
    }

    public void setTrackLatitude(int trackLatitude) {
        this.trackLatitude = trackLatitude;
    }

    public int getRedbookAudioTrack() {
        return redbookAudioTrack;
    }

    public void setRedbookAudioTrack(int redbookAudioTrack) {
        this.redbookAudioTrack = redbookAudioTrack;
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public int getSpotd() {
        return spotd;
    }

    public void setSpotd(int spotd) {
        this.spotd = spotd;
    }

    public int getSpotp() {
        return spotp;
    }

    public void setSpotp(int spotp) {
        this.spotp = spotp;
    }

    public int getSpoth() {
        return spoth;
    }

    public void setSpoth(int spoth) {
        this.spoth = spoth;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public double getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(double raceTime) {
        this.raceTime = raceTime;
    }

    public double getRaceStartTime() {
        return raceStartTime;
    }

    public void setRaceStartTime(double raceStartTime) {
        this.raceStartTime = raceStartTime;
    }

    public double getDragDebugTimer() {
        return dragDebugTimer;
    }

    public void setDragDebugTimer(double dragDebugTimer) {
        this.dragDebugTimer = dragDebugTimer;
    }

    public boolean isControlFlag() {
        return controlFlag;
    }

    public void setControlFlag(boolean controlFlag) {
        this.controlFlag = controlFlag;
    }

    public boolean isAutoShift() {
        return autoShift;
    }

    public void setAutoShift(boolean autoShift) {
        this.autoShift = autoShift;
    }

    public boolean isAutoStage() {
        return autoStage;
    }

    public void setAutoStage(boolean autoStage) {
        this.autoStage = autoStage;
    }

    public boolean isBothStaged() {
        return bothStaged;
    }

    public void setBothStaged(boolean bothStaged) {
        this.bothStaged = bothStaged;
    }

    public boolean isBothStagedPrev() {
        return bothStagedPrev;
    }

    public void setBothStagedPrev(boolean bothStagedPrev) {
        this.bothStagedPrev = bothStagedPrev;
    }

    public boolean isStageComFlag() {
        return stageComFlag;
    }

    public void setStageComFlag(boolean stageComFlag) {
        this.stageComFlag = stageComFlag;
    }

    public boolean isBonusLapFlag() {
        return bonusLapFlag;
    }

    public void setBonusLapFlag(boolean bonusLapFlag) {
        this.bonusLapFlag = bonusLapFlag;
    }

    public int getNumVehicles() {
        return numVehicles;
    }

    public void setNumVehicles(int numVehicles) {
        this.numVehicles = numVehicles;
    }

    public int getNumRamps() {
        return numRamps;
    }

    public void setNumRamps(int numRamps) {
        this.numRamps = numRamps;
    }

    public int getNumBoxes() {
        return numBoxes;
    }

    public void setNumBoxes(int numBoxes) {
        this.numBoxes = numBoxes;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public void setNumCylinders(int numCylinders) {
        this.numCylinders = numCylinders;
    }

    public int getNumTopCrush() {
        return numTopCrush;
    }

    public void setNumTopCrush(int numTopCrush) {
        this.numTopCrush = numTopCrush;
    }

    public int getNumCourseSegments() {
        return numCourseSegments;
    }

    public void setNumCourseSegments(int numCourseSegments) {
        this.numCourseSegments = numCourseSegments;
    }

    public int getNumExtendedCourseSegments() {
        return numExtendedCourseSegments;
    }

    public void setNumExtendedCourseSegments(int numExtendedCourseSegments) {
        this.numExtendedCourseSegments = numExtendedCourseSegments;
    }

    public int getCourseDirection() {
        return courseDirection;
    }

    public void setCourseDirection(int courseDirection) {
        this.courseDirection = courseDirection;
    }

    public int getBackdropType() {
        return backdropType;
    }

    public void setBackdropType(int backdropType) {
        this.backdropType = backdropType;
    }

    public int getBackdropCount() {
        return backdropCount;
    }

    public void setBackdropCount(int backdropCount) {
        this.backdropCount = backdropCount;
    }

    public String getStadiumModelBINFile() {
        return stadiumModelBINFile;
    }

    public void setStadiumModelBINFile(String stadiumModelBINFile) {
        this.stadiumModelBINFile = stadiumModelBINFile;
    }

    public List<String> getBackdropModelBINFiles() {
        return backdropModelBINFiles;
    }

    public void setBackdropModelBINFiles(List<String> backdropModelBINFiles) {
        this.backdropModelBINFiles = backdropModelBINFiles;
    }

    public Vehicle getYourTruck() {
        return yourTruck;
    }

    public void setYourTruck(Vehicle yourTruck) {
        this.yourTruck = yourTruck;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public List<CourseDefinition> getCourseSegments() {
        return courseSegments;
    }

    public void setCourseSegments(List<CourseDefinition> courses) {
        this.courseSegments = courses;
    }

    public List<CourseDefinition> getExtendedCourseSegments() {
        return extendedCourseSegments;
    }

    public void setExtendedCourseSegments(List<CourseDefinition> extendedCourseSegments) {
        this.extendedCourseSegments = extendedCourseSegments;
    }

    public List<Ramp> getRamps() {
        return ramps;
    }

    public void setRamps(List<Ramp> ramps) {
        this.ramps = ramps;
    }

    public List<TopCrush> getTopCrushes() {
        return topCrushes;
    }

    public void setTopCrushes(List<TopCrush> topCrushes) {
        this.topCrushes = topCrushes;
    }

    public double getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(double trackLength) {
        this.trackLength = trackLength;
    }

    public Integer getAmbientSound() {
        return ambientSound;
    }

    public void setAmbientSound(Integer ambientSound) {
        this.ambientSound = ambientSound;
    }

    public int getWeatherMask() {
        return weatherMask;
    }

    public void setWeatherMask(int weatherMask) {
        this.weatherMask = weatherMask;
    }

    public String getTrackAnnouncerWAV() {
        return trackAnnouncerWAV;
    }

    public void setTrackAnnouncerWAV(String trackAnnouncerWAV) {
        this.trackAnnouncerWAV = trackAnnouncerWAV;
    }

    public Integer getStadiumFlag() {
        return stadiumFlag;
    }

    public void setStadiumFlag(Integer stadiumFlag) {
        this.stadiumFlag = stadiumFlag;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    public Integer getSz() {
        return sz;
    }

    public void setSz(Integer sz) {
        this.sz = sz;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public int getNumEnvironmentalLights() {
        return numEnvironmentalLights;
    }

    public void setNumEnvironmentalLights(int numEnvironmentalLights) {
        this.numEnvironmentalLights = numEnvironmentalLights;
    }

    public List<BoxName> getBoxNames() {
        return boxNames;
    }

    public void setBoxNames(List<BoxName> boxNames) {
        this.boxNames = boxNames;
    }

    public List<BoxType> getBoxTypes() {
        return boxTypes;
    }

    public void setBoxTypes(List<BoxType> boxTypes) {
        this.boxTypes = boxTypes;
    }

    public int getNumBoxTypes() {
        return numBoxTypes;
    }

    public void setNumBoxTypes(int numBoxTypes) {
        this.numBoxTypes = numBoxTypes;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public int getNumPaths() {
        return numPaths;
    }

    public void setNumPaths(int numPaths) {
        this.numPaths = numPaths;
    }

    public List<EnvironmentalLight> getEnvironmentalLights() {
        return environmentalLights;
    }

    public void setEnvironmentalLights(
    	List<EnvironmentalLight> environmentalLights) {
        this.environmentalLights = environmentalLights;
    }

    public Integer getYourVehicleNumber() {
        return yourVehicleNumber;
    }

    public void setYourVehicleNumber(Integer yourVehicleNumber) {
        this.yourVehicleNumber = yourVehicleNumber;
    }

    public Integer getPaceLaps() {
        return paceLaps;
    }

    public void setPaceLaps(Integer paceLaps) {
        this.paceLaps = paceLaps;
    }

    public double getBrakeBias() {
        return brakeBias;
    }

    public void setBrakeBias(double brakeBias) {
        this.brakeBias = brakeBias;
    }

    public double getWeightJacker() {
        return weightJacker;
    }

    public void setWeightJacker(double weightJacker) {
        this.weightJacker = weightJacker;
    }

    public int getFuelKnob() {
        return fuelKnob;
    }

    public void setFuelKnob(int fuelKnob) {
        this.fuelKnob = fuelKnob;
    }

    public int getBoostKnob() {
        return boostKnob;
    }

    public void setBoostKnob(int boostKnob) {
        this.boostKnob = boostKnob;
    }

    public int getRevLimiter() {
        return revLimiter;
    }

    public void setRevLimiter(int revLimiter) {
        this.revLimiter = revLimiter;
    }

    public int getFrontAntiRollBar() {
        return frontAntiRollBar;
    }

    public void setFrontAntiRollBar(int frontAntiRollBar) {
        this.frontAntiRollBar = frontAntiRollBar;
    }

    public int getRearAntiRollBar() {
        return rearAntiRollBar;
    }

    public void setRearAntiRollBar(int rearAntiRollBar) {
        this.rearAntiRollBar = rearAntiRollBar;
    }

    public double getBrakeProportion() {
        return brakeProportion;
    }

    public void setBrakeProportion(double brakeProportion) {
        this.brakeProportion = brakeProportion;
    }

    public List<VarlowEntry> getVarlowEntries() {
        return varlowEntries;
    }

    public void setVarlowEntries(List<VarlowEntry> varlowEntries) {
        this.varlowEntries = varlowEntries;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getSignaturePrefix() {
        return signaturePrefix;
    }

    public void setSignaturePrefix(String signaturePrefix) {
        this.signaturePrefix = signaturePrefix;
    }

    public String getSignatureSuffix() {
        return signatureSuffix;
    }

    public void setSignatureSuffix(String signatureSuffix) {
        this.signatureSuffix = signatureSuffix;
    }
    
}//end SITFile
