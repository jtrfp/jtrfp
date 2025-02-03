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

package org.jtrfp.jtrfp.trk.jfdt;

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
import org.jtrfp.jtrfp.jfdt.TripletDouble;

/**
 * Read/Write parser for Terminal Reality TRK file assets.
 * This bean stores version 6 and 7 data in an internal TRKData delegate
 * which can be read using getTrkData() and casting the result to
 * Type6and7TruckFile. CART TRK files are not supported by this parser.
 * CART .TRK files should be parsed using CARTTRKFile<br><br>
 * 
 * <table>
 * <caption>Compatibility testing</caption>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>MTM1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>MTM2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo1</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * <tr><td>Evo2</td><td>Yes</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class TRKFile extends SelfParsingFile {
    private TRKData trkData;
    
    public TRKFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    @Override
    public void describeFormat(Parser parser)
	    throws UnrecognizedFormatException {
	parser.subParseProposedClasses(parser.property("trkData", TRKData.class), ClassInclusion.classes(Type6and7TruckFile.class,MTM1TRKFile.class,MTM1TRKFile.class));
    }
    
    public static class LightBodyAxisPosition implements ThirdPartyParseable {
	double x,y,z,bitmapRadius;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringCSVEndingWith("\r\n", double.class, false, "x","y","z","bitmapRadius");
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

	public double getBitmapRadius() {
	    return bitmapRadius;
	}

	public void setBitmapRadius(double bitmapRadius) {
	    this.bitmapRadius = bitmapRadius;
	}
	
    }//end LightBodyAxisPosition
    
    public static class LightOnOffMS implements ThirdPartyParseable {
	private int onTimeMS, offTimeMS;

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("onTimeMS", int.class), false);
	    p.stringEndingWith("\r\n", p.property("offTimeMS", int.class), false);
	}

	public int getOnTimeMS() {
	    return onTimeMS;
	}

	public void setOnTimeMS(int onTimeMS) {
	    this.onTimeMS = onTimeMS;
	}

	public int getOffTimeMS() {
	    return offTimeMS;
	}

	public void setOffTimeMS(int offTimeMS) {
	    this.offTimeMS = offTimeMS;
	}
	
    }//end LightOnOffMS
    
    public static class LightCone implements ThirdPartyParseable {
	private double lengthFeet, baseRadiusFeet, rimRadiusFeet;
	private String textureName;
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("lengthFeet", double.class), false);
	    p.stringEndingWith(",", p.property("baseRadiusFeet", double.class), false);
	    p.stringEndingWith(",", p.property("rimRadiusFeet", double.class), false);
	    p.stringEndingWith("\r\n", p.property("textureName", String.class), false);
	}
	public double getLengthFeet() {
	    return lengthFeet;
	}
	public void setLengthFeet(double lengthFeet) {
	    this.lengthFeet = lengthFeet;
	}
	public double getBaseRadiusFeet() {
	    return baseRadiusFeet;
	}
	public void setBaseRadiusFeet(double baseRadiusFeet) {
	    this.baseRadiusFeet = baseRadiusFeet;
	}
	public double getRimRadiusFeet() {
	    return rimRadiusFeet;
	}
	public void setRimRadiusFeet(double rimRadiusFeet) {
	    this.rimRadiusFeet = rimRadiusFeet;
	}
	public String getTextureName() {
	    return textureName;
	}
	public void setTextureName(String textureName) {
	    this.textureName = textureName;
	}
    }//end LightCone
    
    public static class Type6and7TruckFile extends TRKData {
	private String truckMake, truckModel, truckClass;
	private int truckCost, truckModelYear, truckQuickClass, version;
	private double truckLength,truckWidth,truckHeight,truckWheelbase,truckFrontTrack,truckRearTrack,truckAcceleration,truckTopSpeed,truckHandling;
	private double rightFrontCF, leftFrontCF, rightRearCF, leftRearCF, maxAngleFrontAxle, maxAngleRearAxle, maxCompressionFrontAxle;
	private double maxCompressionRearAxle, springRateFrontAxle, springRateRearAxle, torquePercentFrontAxle, torquePercentRearAxle, biasFrontAxle, biasRearAxle;
	private double gearRatioPark, gearRatioReverse, gearRatioNeutral, gearRatio1st, gearRatio2nd, gearRatio3rd;
	private double gearInertiaPark, gearInertiaReverse, gearInertiaNeutral, gearInertia1st, gearInertia2nd, gearInertia3rd, gearInertia4th;
	private Double gearInertia5th, gearRatio4th, gearRatio5th;
	private double transmissionFinalDrive, transmissionAxleRatio, transmissionPercentLoss, TransmissionTransferLowRatio;
	private int transmissionType, engineType, engineAspiration, numTorqueTableEntries;
	private double engineMaxHP, engineRPMAtMaxHP, engineMaxTorque, engineRPMAtMaxTorque, engineRedline, engineRedlineTimer;
	private List<Double> torqueTableEntries;
	private double engineUpshiftRPM, engineDownshiftRPM, engineFrictionCF, engineFuelConsumption;
	private int driveType, frontSuspensionType, rearSuspensionType, frontSuspensionSpringType, rearSuspensionSpringType;
	private double engineDisplacement, fuelWeight, dryWeight;
	private TripletDouble ixyz, refArea, cgModifier;
	private double cl, cd, cy, steeringScalar, brakeBalance, maxBrakeForcePercent,clp,cmq,cnr;
	private String teamRequirement;
	private int slipDifferentialTypeFrontAxle, slipDifferentialTypeRearAxle, maxGear; 
	private double rightFrontSpringArm, leftFrontSpringArm, rightRearSpringArm, leftRearSpringArm; 
	private TripletDouble driverHeadPosition, whipAntennaPosition;
	private List<TripletDouble> scpt;
	private List<String> colors, stockParts;
	private int numColors, numStockParts;
	private String signaturePrefix, signatureSuffix, dummy;
	private List<VehicleColor> vehicleColors;
	
	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {
	    p.expectString("version\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("version", int.class), false);
	    if(!(getVersion() == 6 || getVersion() == 7))
		throw new UnrecognizedFormatException("Unrecognized version: "+getVersion());
	    
	    p.expectString("truckName\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckName", String.class), false);
	    
	    p.expectString("truckMake\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckMake", String.class), false);
	    
	    p.expectString("truckModel\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckModel", String.class), false);
	    
	    p.expectString("truckClass\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckClass", String.class), false);
	    
	    p.expectString("truckCost\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckCost", int.class), false);
	    
	    p.expectString("truckModelYear\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckModelYear", int.class), false);
	    
	    p.expectString("truckLength\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckLength", double.class), false);
	    
	    p.expectString("truckWidth\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckWidth", double.class), false);
	    
	    p.expectString("truckHeight\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckHeight", double.class), false);
	    
	    p.expectString("truckWheelbase\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckWheelbase", double.class), false);
	    p.expectString("truckFrontTrack\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckFrontTrack", double.class), false);
	    p.expectString("truckRearTrack\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckRearTrack", double.class), false);
	    p.expectString("truckAcceleration\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckAcceleration", double.class), false);
	    p.expectString("truckTopSpeed\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckTopSpeed", double.class), false);
	    
	    p.expectString("truckHandling\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckHandling", double.class), false);
	    
	    p.expectString("truckQuickClass\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckQuickClass", int.class), false);
	    
	    p.expectString("truckModelBaseName\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("truckModelBaseName", String.class), false);
	    
	    p.expectString("tireModelBaseName\r\n",FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith("\r\n", p.property("tireModelBaseName", String.class), false);
	    
	    p.expectString("axleModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("axleModelName", String.class), false);
	    
	    p.expectString("shockTextureName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("shockTextureName", String.class), false);
	    
	    p.expectString("barTextureName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("barTextureName", String.class), false);
	    
	    p.expectString("axlebarOffset\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("axlebarOffset", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    p.expectString("driveshaftPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("driveshaftPos", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    //Left off here
	    p.expectString("driverHead.initBPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("driverHeadPosition", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    
	    p.expectString("whipAntenna.bPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("whipAntennaPosition", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    
	    if(getVersion() == 7) {
		p.expectString("teamRequirement\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);//TODO Analyze for poss. enum/boolean values
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("teamRequirement", String.class), false);
	    }
	    
	    //Already in properties
	    p.expectString("faxle.rtire.static_bpos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("rightFrontTirePosX", double.class), false);
	    p.stringEndingWith(",", p.property("rightFrontTirePosY", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontTirePosZ", double.class), false);
	    
	    p.expectString("faxle.ltire.static_bpos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("leftFrontTirePosX", double.class), false);
	    p.stringEndingWith(",", p.property("leftFrontTirePosY", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontTirePosZ", double.class), false);
	    
	    p.expectString("raxle.rtire.static_bpos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("rightRearTirePosX", double.class), false);
	    p.stringEndingWith(",", p.property("rightRearTirePosY", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearTirePosZ", double.class), false);
	    p.expectString("raxle.ltire.static_bpos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("leftRearTirePosX", double.class), false);
	    p.stringEndingWith(",", p.property("leftRearTirePosY", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearTirePosZ", double.class), false);
	    //SPRING ARM
	    p.expectString("faxle.rtire.spring_arm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontSpringArm", double.class), false);
	    p.expectString("faxle.ltire.spring_arm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontSpringArm", double.class), false);
	    p.expectString("raxle.rtire.spring_arm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearSpringArm", double.class), false);
	    p.expectString("raxle.ltire.spring_arm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearSpringArm", double.class), false);
	    
	    //CF
	    p.expectString("faxle.rtire.CF\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontCF", double.class), false);
	    p.expectString("faxle.ltire.CF\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontCF", double.class), false);
	    
	    p.expectString("raxle.rtire.CF\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearCF", double.class), false);
	    
	    p.expectString("raxle.ltire.CF\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearCF", double.class), false);
	    
	    //MaxAngle
	    p.expectString("faxle.maxangle\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxAngleFrontAxle", double.class), false);
	    p.expectString("raxle.maxangle\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxAngleRearAxle", double.class), false);
	    //Max Compression
	    p.expectString("faxle.maxcompr\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxCompressionFrontAxle", double.class), false);
	    
	    p.expectString("raxle.maxcompr\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxCompressionRearAxle", double.class), false);
	    
	    //Spring Rate
	    p.expectString("faxle.spring_rate\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("springRateFrontAxle", double.class), false);
	    
	    p.expectString("raxle.spring_rate\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("springRateRearAxle", double.class), false);
	    
	    //Torque Pct
	    p.expectString("faxle.torque_pct\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("torquePercentFrontAxle", double.class), false);
	    p.expectString("raxle.torque_pct\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("torquePercentRearAxle", double.class), false);
	    
	    //Axle Bias
	    p.expectString("faxle.axleBias\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("biasFrontAxle", double.class), false);
	    
	    p.expectString("raxle.axleBias\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("biasRearAxle", double.class), false);
	    
	    //Slip differential type
	    p.expectString("faxle.slipDiffType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("slipDifferentialTypeFrontAxle", int.class), false);
	    
	    p.expectString("raxle.slipDiffType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("slipDifferentialTypeRearAxle", int.class), false);
	    //Max gear
	    p.expectString("xm.maxGear\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxGear", int.class), false);

	    //XXX: Messy code below
	    //Gear Ratios
	    try {
		if(p.parseMode == ParseMode.WRITE && getGearRatio5th() == null)
		    throw new UnrecognizedFormatException();
		p.expectString("xm.gear_ratio[] - Park/Reverse/Neutral/1st/2nd/3rd/4th/5th\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioPark", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioReverse", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioNeutral", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio1st", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio2nd", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio3rd", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio4th", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio5th", Double.class), false);
		//Gear Inertia
		p.expectString("xm.gearInertia[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaPark", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaReverse", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaNeutral", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia1st", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia2nd", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia3rd", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia4th", double.class), false);
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia5th", Double.class), false);
	    } catch(UnrecognizedFormatException e0) {
		try {
		    if(p.parseMode == ParseMode.WRITE && getGearRatio4th() == null)
			    throw new UnrecognizedFormatException();
		    p.expectString("xm.gear_ratio[] - Park/Reverse/Neutral/1st/2nd/3rd/4th\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioPark", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioReverse", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioNeutral", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio1st", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio2nd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio3rd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio4th", double.class), false);
		    //Gear Inertia
		    p.expectString("xm.gearInertia[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaPark", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaReverse", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaNeutral", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia1st", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia2nd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia3rd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia4th", double.class), false);
		} catch(UnrecognizedFormatException e1) {
		    p.expectString("xm.gear_ratio[] - Park/Reverse/Neutral/1st/2nd/3rd\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioPark", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioReverse", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatioNeutral", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio1st", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio2nd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearRatio3rd", double.class), false);
		    //Gear Inertia
		    p.expectString("xm.gearInertia[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaPark", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaReverse", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertiaNeutral", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia1st", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia2nd", double.class), false);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("gearInertia3rd", double.class), false);
		}//end catch(e1)
	    }//end catch(e0)
	    
	    p.expectString("xm.final_drive\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("transmissionFinalDrive", double.class), false);
	    //Axle ratio
	    p.expectString("xm.axle_ratio\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("transmissionAxleRatio", double.class), false);
	    //% loss
	    p.expectString("xm.pct_loss\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("transmissionPercentLoss", double.class), false);
	    //Transfer-low ratio
	    p.expectString("xm.xfrLowRatio\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("transmissionTransferLowRatio", double.class), false);
	    //Transmission type
	    p.expectString("xm.type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("transmissionType", int.class), false);
	    //Engine max horsepower
	    p.expectString("eng.maxHP\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineMaxHP", double.class), false);
	    //Engine RPM @ Max Horsepower
	    p.expectString("eng.maxHPRPM\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineRPMAtMaxHP", double.class), false);
	    //Engine max torque
	    p.expectString("eng.maxTorque\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineMaxTorque", double.class), false);
	    //Engine RPM at max torque
	    p.expectString("eng.maxTorqueRPM\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineRPMAtMaxTorque", double.class), false);
	    //Engine Redline
	    p.expectString("eng.redline\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineRedline", double.class), false);
	    //Engine Redline Timer
	    p.expectString("eng.redlineTimer\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineRedlineTimer", double.class), false);
	    //Engine Torque Table
	    p.expectString("eng.torqueTableCount\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numTorqueTableEntries", int.class), false);
	    p.expectString("eng.torqueTable[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumTorqueTableEntries(); i++) {
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("torqueTableEntries", Double.class, i), false);
	    }//end for(i)
	    //Engine upshift RPM
	    p.expectString("eng.upshift_rpm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineUpshiftRPM", double.class), false);
	    //Engine downshift RPM
	    p.expectString("eng.dnshift_rpm\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineDownshiftRPM", double.class), false);
	    //Engine friction CF
	    p.expectString("eng.friction_cf\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineFrictionCF", double.class), false);
	    //Engine fuel consumption
	    p.expectString("eng.fuel_consum\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineFuelConsumption", double.class), false);
	    //Engine type
	    p.expectString("eng.type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineType", int.class), false);
	    //Engine aspiration
	    p.expectString("eng.aspiration\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineAspiration", int.class), false);
	    //Engine displacement
	    p.expectString("eng.displacement\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("engineDisplacement", double.class), false);
	    //Fuel Weight
	    p.expectString("fuel.weight\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("fuelWeight", double.class), false);
	    //sc[].pt
	    p.expectString("sc[].pt\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0 ; i < 12; i++)
		p.subParseProposedClasses(p.indexedProperty("scpt", TripletDouble.EndingWithNewline.class, i), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    //Dry Weight
	    p.expectString("dry_weight\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("dryWeight", double.class), false);
	    //i (xyz)
	    p.expectString("ixx,iyy,izz\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("ixyz", TripletDouble.EndingWithNewline.class), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    //refArea
	    p.expectString("refArea\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("refArea", TripletDouble.EndingWithNewline.class), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    
	    //CL
	    p.expectString("CL\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("cl", double.class), false);
	    //CD
	    p.expectString("CD\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("cd", double.class), false);
	    //CY
	    p.expectString("CY\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("cy", double.class), false);
	    
	    //Drive Type
	    p.expectString("drive_type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("driveType", int.class), false);
	    //cgModifier
	    p.expectString("cgModifier\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("cgModifier", TripletDouble.EndingWithNewline.class), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    //Steering Scalar
	    p.expectString("steering_scaler\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("steeringScalar", double.class), false);
	    //Brake balance
	    p.expectString("brakeBalance\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("brakeBalance", double.class), false);
	    //Max Brake Force %
	    p.expectString("max_brake_force_pct\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("maxBrakeForcePercent", double.class), false);
	    //clp,cmq,cnr
	    p.expectString("clp,cmq,cnr\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(",", p.property("clp", double.class), false);
	    p.stringEndingWith(",", p.property("cmq", double.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("cnr", double.class), false);
	    
	    //Front Suspension Type
	    p.expectString("frontSuspType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("frontSuspensionType", int.class), false);
	    //Rear Suspension Type
	    p.expectString("rearSuspType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rearSuspensionType", int.class), false);
	    //Front Suspension Spring Type
	    p.expectString("frontSuspSpringType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("frontSuspensionSpringType", int.class), false);
	    //Rear Suspension Spring Type
	    p.expectString("rearSuspSpringType\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rearSuspensionSpringType", int.class), false);
	    
	    //INSTRUMENT CLUSTER
	    p.expectString("Instrument Cluster\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("instrumentCluster", String.class), false);
	    //WAVE FILE
	    p.expectString("Wave File\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile1", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile2", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile3", String.class), false);

	    //LIGHTS
	    //try {
	    p.expectString("Number of Lights\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numLights", int.class), false);
	    for(int i = 0; i < getNumLights(); i++) {
		p.expectString("Light "+i+" type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith("\r\n",p.indexedProperty("lightTypes", Integer.class, i),false);
		p.expectString("Light "+i+" body axis pos x,y,z (ft), bitmap radius (ft)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("lightBodyAxisPositions", LightBodyAxisPosition.class, i), ClassInclusion.classOf(LightBodyAxisPosition.class));
		p.expectString("Light "+i+" heading (rad), pitch (rad), heading spin speed (rad/sec)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		//p.expectString("Light "+i+" heading (rad), heading spin speed (rad/sec)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("lightHeadingPitchSpinSpeeds", TripletDouble.EndingWithNewline.class, i), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
		p.expectString("Light "+i+" cone: length (ft), base radius (ft), rim radius (ft), texture name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("lightCones", LightCone.class, i), ClassInclusion.classOf(LightCone.class));
		p.expectString("Light "+i+" source: bitmap name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.stringEndingWith("\r\n", p.indexedProperty("lightSourceRAWs", String.class, i), false);
		p.expectString("Light "+i+" ms on, ms off (0 if light doesn't blink)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.indexedProperty("lightTimesMS", LightOnOffMS.class, i), ClassInclusion.classOf(LightOnOffMS.class));
	    }
	    //} catch(UnrecognizedFormatException e) {}
	    
	    //Stock parts
	    p.expectString("numStockParts\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numStockParts", int.class), false);
	    p.expectString("stockPartList[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumStockParts(); i++)
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("stockParts", String.class, i), false);
	    
	    p.expectString("numColors\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numColors", int.class), false);
	    p.expectString("colorList[]\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    for(int i = 0; i < getNumColors(); i++) {
		p.subParseProposedClasses(p.indexedProperty("vehicleColors", VehicleColor.class, i), ClassInclusion.classOf(VehicleColor.class));
		//p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.indexedProperty("colors", String.class, i), false);
	    }
	    //Signature
	    p.expectString("signature: ", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(" ", p.property("signaturePrefix", String.class), false);
	    p.stringEndingWith(" ", p.property("signatureSuffix", String.class), false);
	    p.ignoreEOF(true);
	    p.expectString("// WARNING: Do not edit this file\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	}//end describeFormat(...)
	
	public static class VehicleColor implements ThirdPartyParseable {
	    private double unknown0, unknown1, unknown2;
	    private int unknown3, unknown4, unknown5;
	    private String unknownString;

	    @Override
	    public void describeFormat(Parser p)
		    throws UnrecognizedFormatException {
		p.stringCSVEndingWith(",", double.class, false, "unknown0","unknown1","unknown2");
		p.stringCSVEndingWith(",", int.class, false, "unknown3","unknown4","unknown5");
		p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("unknownString", String.class), false);
	    }

	    public double getUnknown0() {
	        return unknown0;
	    }

	    public void setUnknown0(double unknown0) {
	        this.unknown0 = unknown0;
	    }

	    public double getUnknown1() {
	        return unknown1;
	    }

	    public void setUnknown1(double unknown1) {
	        this.unknown1 = unknown1;
	    }

	    public double getUnknown2() {
	        return unknown2;
	    }

	    public void setUnknown2(double unknown2) {
	        this.unknown2 = unknown2;
	    }

	    public int getUnknown3() {
	        return unknown3;
	    }

	    public void setUnknown3(int unknown3) {
	        this.unknown3 = unknown3;
	    }

	    public int getUnknown4() {
	        return unknown4;
	    }

	    public void setUnknown4(int unknown4) {
	        this.unknown4 = unknown4;
	    }

	    public int getUnknown5() {
	        return unknown5;
	    }

	    public void setUnknown5(int unknown5) {
	        this.unknown5 = unknown5;
	    }

	    public String getUnknownString() {
	        return unknownString;
	    }

	    public void setUnknownString(String unknownString) {
	        this.unknownString = unknownString;
	    }
	    
	}//end VehicleColor

	public String getTruckMake() {
	    return truckMake;
	}

	public void setTruckMake(String truckMake) {
	    this.truckMake = truckMake;
	}

	public String getTruckModel() {
	    return truckModel;
	}

	public void setTruckModel(String truckModel) {
	    this.truckModel = truckModel;
	}

	public String getTruckClass() {
	    return truckClass;
	}

	public void setTruckClass(String truckClass) {
	    this.truckClass = truckClass;
	}

	public int getTruckCost() {
	    return truckCost;
	}

	public void setTruckCost(int truckCost) {
	    this.truckCost = truckCost;
	}

	public int getTruckModelYear() {
	    return truckModelYear;
	}

	public void setTruckModelYear(int truckModelYear) {
	    this.truckModelYear = truckModelYear;
	}

	public int getTruckQuickClass() {
	    return truckQuickClass;
	}

	public void setTruckQuickClass(int truckQuickClass) {
	    this.truckQuickClass = truckQuickClass;
	}

	public double getTruckLength() {
	    return truckLength;
	}

	public void setTruckLength(double truckLength) {
	    this.truckLength = truckLength;
	}

	public double getTruckWidth() {
	    return truckWidth;
	}

	public void setTruckWidth(double truckWidth) {
	    this.truckWidth = truckWidth;
	}

	public double getTruckHeight() {
	    return truckHeight;
	}

	public void setTruckHeight(double truckHeight) {
	    this.truckHeight = truckHeight;
	}

	public double getTruckWheelbase() {
	    return truckWheelbase;
	}

	public void setTruckWheelbase(double truckWheelbase) {
	    this.truckWheelbase = truckWheelbase;
	}

	public double getTruckFrontTrack() {
	    return truckFrontTrack;
	}

	public void setTruckFrontTrack(double truckFrontTrack) {
	    this.truckFrontTrack = truckFrontTrack;
	}

	public double getTruckAcceleration() {
	    return truckAcceleration;
	}

	public void setTruckAcceleration(double truckAcceleration) {
	    this.truckAcceleration = truckAcceleration;
	}

	public double getTruckTopSpeed() {
	    return truckTopSpeed;
	}

	public void setTruckTopSpeed(double truckTopSpeed) {
	    this.truckTopSpeed = truckTopSpeed;
	}

	public double getTruckHandling() {
	    return truckHandling;
	}

	public void setTruckHandling(double truckHandling) {
	    this.truckHandling = truckHandling;
	}

	public double getRightFrontCF() {
	    return rightFrontCF;
	}

	public void setRightFrontCF(double rightFrontCF) {
	    this.rightFrontCF = rightFrontCF;
	}

	public double getLeftFrontCF() {
	    return leftFrontCF;
	}

	public void setLeftFrontCF(double leftFrontCF) {
	    this.leftFrontCF = leftFrontCF;
	}

	public double getRightRearCF() {
	    return rightRearCF;
	}

	public void setRightRearCF(double rightRearCF) {
	    this.rightRearCF = rightRearCF;
	}

	public double getLeftRearCF() {
	    return leftRearCF;
	}

	public void setLeftRearCF(double leftRearCF) {
	    this.leftRearCF = leftRearCF;
	}

	public double getMaxAngleFrontAxle() {
	    return maxAngleFrontAxle;
	}

	public void setMaxAngleFrontAxle(double maxAngleFrontAxle) {
	    this.maxAngleFrontAxle = maxAngleFrontAxle;
	}

	public double getMaxAngleRearAxle() {
	    return maxAngleRearAxle;
	}

	public void setMaxAngleRearAxle(double maxAngleRearAxle) {
	    this.maxAngleRearAxle = maxAngleRearAxle;
	}

	public double getMaxCompressionFrontAxle() {
	    return maxCompressionFrontAxle;
	}

	public void setMaxCompressionFrontAxle(double maxCompressionFrontAxle) {
	    this.maxCompressionFrontAxle = maxCompressionFrontAxle;
	}

	public double getMaxCompressionRearAxle() {
	    return maxCompressionRearAxle;
	}

	public void setMaxCompressionRearAxle(double maxCompressionRearAxle) {
	    this.maxCompressionRearAxle = maxCompressionRearAxle;
	}

	public double getSpringRateFrontAxle() {
	    return springRateFrontAxle;
	}

	public void setSpringRateFrontAxle(double springRateFrontAxle) {
	    this.springRateFrontAxle = springRateFrontAxle;
	}

	public double getSpringRateRearAxle() {
	    return springRateRearAxle;
	}

	public void setSpringRateRearAxle(double springRateRearAxle) {
	    this.springRateRearAxle = springRateRearAxle;
	}

	public double getTorquePercentFrontAxle() {
	    return torquePercentFrontAxle;
	}

	public void setTorquePercentFrontAxle(double torquePercentFrontAxle) {
	    this.torquePercentFrontAxle = torquePercentFrontAxle;
	}

	public double getTorquePercentRearAxle() {
	    return torquePercentRearAxle;
	}

	public void setTorquePercentRearAxle(double torquePercentRearAxle) {
	    this.torquePercentRearAxle = torquePercentRearAxle;
	}

	public double getBiasFrontAxle() {
	    return biasFrontAxle;
	}

	public void setBiasFrontAxle(double biasFrontAxle) {
	    this.biasFrontAxle = biasFrontAxle;
	}

	public double getBiasRearAxle() {
	    return biasRearAxle;
	}

	public void setBiasRearAxle(double biasRearAxle) {
	    this.biasRearAxle = biasRearAxle;
	}

	public double getGearRatioPark() {
	    return gearRatioPark;
	}

	public void setGearRatioPark(double gearRatioPark) {
	    this.gearRatioPark = gearRatioPark;
	}

	public double getGearRatioReverse() {
	    return gearRatioReverse;
	}

	public void setGearRatioReverse(double gearRatioReverse) {
	    this.gearRatioReverse = gearRatioReverse;
	}

	public double getGearRatioNeutral() {
	    return gearRatioNeutral;
	}

	public void setGearRatioNeutral(double gearRatioNeutral) {
	    this.gearRatioNeutral = gearRatioNeutral;
	}

	public double getGearRatio1st() {
	    return gearRatio1st;
	}

	public void setGearRatio1st(double gearRatio1st) {
	    this.gearRatio1st = gearRatio1st;
	}

	public double getGearRatio2nd() {
	    return gearRatio2nd;
	}

	public void setGearRatio2nd(double gearRatio2nd) {
	    this.gearRatio2nd = gearRatio2nd;
	}

	public double getGearRatio3rd() {
	    return gearRatio3rd;
	}

	public void setGearRatio3rd(double gearRatio3rd) {
	    this.gearRatio3rd = gearRatio3rd;
	}

	public Double getGearRatio4th() {
	    return gearRatio4th;
	}

	public void setGearRatio4th(Double gearRatio4th) {
	    this.gearRatio4th = gearRatio4th;
	}

	public Double getGearRatio5th() {
	    return gearRatio5th;
	}

	public void setGearRatio5th(Double gearRatio5th) {
	    this.gearRatio5th = gearRatio5th;
	}

	public double getGearInertiaPark() {
	    return gearInertiaPark;
	}

	public void setGearInertiaPark(double gearInertiaPark) {
	    this.gearInertiaPark = gearInertiaPark;
	}

	public double getGearInertiaReverse() {
	    return gearInertiaReverse;
	}

	public void setGearInertiaReverse(double gearInertiaReverse) {
	    this.gearInertiaReverse = gearInertiaReverse;
	}

	public double getGearInertiaNeutral() {
	    return gearInertiaNeutral;
	}

	public void setGearInertiaNeutral(double gearInertiaNeutral) {
	    this.gearInertiaNeutral = gearInertiaNeutral;
	}

	public double getGearInertia1st() {
	    return gearInertia1st;
	}

	public void setGearInertia1st(double gearInertia1st) {
	    this.gearInertia1st = gearInertia1st;
	}

	public double getGearInertia2nd() {
	    return gearInertia2nd;
	}

	public void setGearInertia2nd(double gearInertia2nd) {
	    this.gearInertia2nd = gearInertia2nd;
	}

	public double getGearInertia3rd() {
	    return gearInertia3rd;
	}

	public void setGearInertia3rd(double gearInertia3rd) {
	    this.gearInertia3rd = gearInertia3rd;
	}

	public double getGearInertia4th() {
	    return gearInertia4th;
	}

	public void setGearInertia4th(double gearInertia4th) {
	    this.gearInertia4th = gearInertia4th;
	}

	public Double getGearInertia5th() {
	    return gearInertia5th;
	}

	public void setGearInertia5th(Double gearInertia5th) {
	    this.gearInertia5th = gearInertia5th;
	}

	public double getTransmissionFinalDrive() {
	    return transmissionFinalDrive;
	}

	public void setTransmissionFinalDrive(double transmissionFinalDrive) {
	    this.transmissionFinalDrive = transmissionFinalDrive;
	}

	public double getTransmissionAxleRatio() {
	    return transmissionAxleRatio;
	}

	public void setTransmissionAxleRatio(double transmissionAxleRatio) {
	    this.transmissionAxleRatio = transmissionAxleRatio;
	}

	public double getTransmissionPercentLoss() {
	    return transmissionPercentLoss;
	}

	public void setTransmissionPercentLoss(double transmissionPercentLoss) {
	    this.transmissionPercentLoss = transmissionPercentLoss;
	}

	public double getTransmissionTransferLowRatio() {
	    return TransmissionTransferLowRatio;
	}

	public void setTransmissionTransferLowRatio(
		double transmissionTransferLowRatio) {
	    TransmissionTransferLowRatio = transmissionTransferLowRatio;
	}

	public int getTransmissionType() {
	    return transmissionType;
	}

	public void setTransmissionType(int transmissionType) {
	    this.transmissionType = transmissionType;
	}

	public int getEngineType() {
	    return engineType;
	}

	public void setEngineType(int engineType) {
	    this.engineType = engineType;
	}

	public int getEngineAspiration() {
	    return engineAspiration;
	}

	public void setEngineAspiration(int engineAspiration) {
	    this.engineAspiration = engineAspiration;
	}

	public double getEngineMaxHP() {
	    return engineMaxHP;
	}

	public void setEngineMaxHP(double engineMaxHP) {
	    this.engineMaxHP = engineMaxHP;
	}

	public double getEngineRPMAtMaxHP() {
	    return engineRPMAtMaxHP;
	}

	public void setEngineRPMAtMaxHP(double engineRPMAtMaxHP) {
	    this.engineRPMAtMaxHP = engineRPMAtMaxHP;
	}

	public double getEngineMaxTorque() {
	    return engineMaxTorque;
	}

	public void setEngineMaxTorque(double engineMaxTorque) {
	    this.engineMaxTorque = engineMaxTorque;
	}

	public double getEngineRPMAtMaxTorque() {
	    return engineRPMAtMaxTorque;
	}

	public void setEngineRPMAtMaxTorque(double engineRPMAtMaxTorque) {
	    this.engineRPMAtMaxTorque = engineRPMAtMaxTorque;
	}

	public double getEngineRedline() {
	    return engineRedline;
	}

	public void setEngineRedline(double engineRedline) {
	    this.engineRedline = engineRedline;
	}

	public double getEngineRedlineTimer() {
	    return engineRedlineTimer;
	}

	public void setEngineRedlineTimer(double engineRedlineTimer) {
	    this.engineRedlineTimer = engineRedlineTimer;
	}

	public int getNumTorqueTableEntries() {
	    return numTorqueTableEntries;
	}

	public void setNumTorqueTableEntries(int numTorqueTableEntries) {
	    this.numTorqueTableEntries = numTorqueTableEntries;
	}

	public List<Double> getTorqueTableEntries() {
	    return torqueTableEntries;
	}

	public void setTorqueTableEntries(List<Double> torqueTableEntries) {
	    this.torqueTableEntries = torqueTableEntries;
	}

	public double getEngineUpshiftRPM() {
	    return engineUpshiftRPM;
	}

	public void setEngineUpshiftRPM(double engineUpshiftRPM) {
	    this.engineUpshiftRPM = engineUpshiftRPM;
	}

	public double getEngineDownshiftRPM() {
	    return engineDownshiftRPM;
	}

	public void setEngineDownshiftRPM(double engineDownshiftRPM) {
	    this.engineDownshiftRPM = engineDownshiftRPM;
	}

	public double getEngineFrictionCF() {
	    return engineFrictionCF;
	}

	public void setEngineFrictionCF(double engineFrictionCF) {
	    this.engineFrictionCF = engineFrictionCF;
	}

	public double getEngineFuelConsumption() {
	    return engineFuelConsumption;
	}

	public void setEngineFuelConsumption(double engineFuelConsumption) {
	    this.engineFuelConsumption = engineFuelConsumption;
	}

	public int getDriveType() {
	    return driveType;
	}

	public void setDriveType(int driveType) {
	    this.driveType = driveType;
	}

	public int getFrontSuspensionType() {
	    return frontSuspensionType;
	}

	public void setFrontSuspensionType(int frontSuspensionType) {
	    this.frontSuspensionType = frontSuspensionType;
	}

	public int getRearSuspensionType() {
	    return rearSuspensionType;
	}

	public void setRearSuspensionType(int rearSuspensionType) {
	    this.rearSuspensionType = rearSuspensionType;
	}

	public int getFrontSuspensionSpringType() {
	    return frontSuspensionSpringType;
	}

	public void setFrontSuspensionSpringType(int frontSuspensionSpringType) {
	    this.frontSuspensionSpringType = frontSuspensionSpringType;
	}

	public int getRearSuspensionSpringType() {
	    return rearSuspensionSpringType;
	}

	public void setRearSuspensionSpringType(int rearSuspensionSpringType) {
	    this.rearSuspensionSpringType = rearSuspensionSpringType;
	}

	public double getEngineDisplacement() {
	    return engineDisplacement;
	}

	public void setEngineDisplacement(double engineDisplacement) {
	    this.engineDisplacement = engineDisplacement;
	}

	public double getDryWeight() {
	    return dryWeight;
	}

	public void setDryWeight(double dryWeight) {
	    this.dryWeight = dryWeight;
	}

	public TripletDouble getIxyz() {
	    return ixyz;
	}

	public void setIxyz(TripletDouble ixyz) {
	    this.ixyz = ixyz;
	}

	public TripletDouble getRefArea() {
	    return refArea;
	}

	public void setRefArea(TripletDouble refArea) {
	    this.refArea = refArea;
	}

	public TripletDouble getCgModifier() {
	    return cgModifier;
	}

	public void setCgModifier(TripletDouble cgModifier) {
	    this.cgModifier = cgModifier;
	}

	public double getCl() {
	    return cl;
	}

	public void setCl(double cl) {
	    this.cl = cl;
	}

	public double getCd() {
	    return cd;
	}

	public void setCd(double cd) {
	    this.cd = cd;
	}

	public double getCy() {
	    return cy;
	}

	public void setCy(double cy) {
	    this.cy = cy;
	}

	public double getSteeringScalar() {
	    return steeringScalar;
	}

	public void setSteeringScalar(double steeringScalar) {
	    this.steeringScalar = steeringScalar;
	}

	public double getBrakeBalance() {
	    return brakeBalance;
	}

	public void setBrakeBalance(double brakeBalance) {
	    this.brakeBalance = brakeBalance;
	}

	public double getMaxBrakeForcePercent() {
	    return maxBrakeForcePercent;
	}

	public void setMaxBrakeForcePercent(double maxBrakeForcePercent) {
	    this.maxBrakeForcePercent = maxBrakeForcePercent;
	}

	public double getClp() {
	    return clp;
	}

	public void setClp(double clp) {
	    this.clp = clp;
	}

	public double getCmq() {
	    return cmq;
	}

	public void setCmq(double cmq) {
	    this.cmq = cmq;
	}

	public double getCnr() {
	    return cnr;
	}

	public void setCnr(double cnr) {
	    this.cnr = cnr;
	}

	public double getFuelWeight() {
	    return fuelWeight;
	}

	public void setFuelWeight(double fuelWeight) {
	    this.fuelWeight = fuelWeight;
	}

	public String getTeamRequirement() {
	    return teamRequirement;
	}

	public void setTeamRequirement(String teamRequirement) {
	    this.teamRequirement = teamRequirement;
	}

	public double getRightFrontSpringArm() {
	    return rightFrontSpringArm;
	}

	public void setRightFrontSpringArm(double rightFrontSpringArm) {
	    this.rightFrontSpringArm = rightFrontSpringArm;
	}

	public double getLeftFrontSpringArm() {
	    return leftFrontSpringArm;
	}

	public void setLeftFrontSpringArm(double leftFrontSpringArm) {
	    this.leftFrontSpringArm = leftFrontSpringArm;
	}

	public double getRightRearSpringArm() {
	    return rightRearSpringArm;
	}

	public void setRightRearSpringArm(double rightRearSpringArm) {
	    this.rightRearSpringArm = rightRearSpringArm;
	}

	public double getLeftRearSpringArm() {
	    return leftRearSpringArm;
	}

	public void setLeftRearSpringArm(double leftRearSpringArm) {
	    this.leftRearSpringArm = leftRearSpringArm;
	}

	public int getSlipDifferentialTypeFrontAxle() {
	    return slipDifferentialTypeFrontAxle;
	}

	public void setSlipDifferentialTypeFrontAxle(
		int slipDifferentialTypeFrontAxle) {
	    this.slipDifferentialTypeFrontAxle = slipDifferentialTypeFrontAxle;
	}

	public int getSlipDifferentialTypeRearAxle() {
	    return slipDifferentialTypeRearAxle;
	}

	public void setSlipDifferentialTypeRearAxle(int slipDifferentialTypeRearAxle) {
	    this.slipDifferentialTypeRearAxle = slipDifferentialTypeRearAxle;
	}

	public int getMaxGear() {
	    return maxGear;
	}

	public void setMaxGear(int maxGear) {
	    this.maxGear = maxGear;
	}

	public TripletDouble getDriverHeadPosition() {
	    return driverHeadPosition;
	}

	public void setDriverHeadPosition(TripletDouble driverHeadPosition) {
	    this.driverHeadPosition = driverHeadPosition;
	}

	public TripletDouble getWhipAntennaPosition() {
	    return whipAntennaPosition;
	}

	public void setWhipAntennaPosition(TripletDouble whipAntennaPosition) {
	    this.whipAntennaPosition = whipAntennaPosition;
	}

	public double getTruckRearTrack() {
	    return truckRearTrack;
	}

	public void setTruckRearTrack(double truckRearTrack) {
	    this.truckRearTrack = truckRearTrack;
	}

	public List<TripletDouble> getScpt() {
	    return scpt;
	}

	public void setScpt(List<TripletDouble> scpt) {
	    this.scpt = scpt;
	}

	public List<String> getColors() {
	    return colors;
	}

	public void setColors(List<String> colors) {
	    this.colors = colors;
	}

	public List<String> getStockParts() {
	    return stockParts;
	}

	public void setStockParts(List<String> stockParts) {
	    this.stockParts = stockParts;
	}

	public int getNumColors() {
	    return numColors;
	}

	public void setNumColors(int numColors) {
	    this.numColors = numColors;
	}

	public int getNumStockParts() {
	    return numStockParts;
	}

	public void setNumStockParts(int numStockParts) {
	    this.numStockParts = numStockParts;
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

	public String getDummy() {
	    return dummy;
	}

	public void setDummy(String dummy) {
	    this.dummy = dummy;
	}

	public List<VehicleColor> getVehicleColors() {
	    return vehicleColors;
	}

	public void setVehicleColors(List<VehicleColor> vehicleColors) {
	    this.vehicleColors = vehicleColors;
	}

	public int getVersion() {
	    return version;
	}

	public void setVersion(int version) {
	    this.version = version;
	}
	
    }//end Type7TruckFile
    
    public static abstract class TRKData implements ThirdPartyParseable {
	private String truckName, truckModelBaseName, tireModelBaseName, axleModelName, shockTextureName, barTextureName,
			waveFile1,waveFile2,waveFile3,instrumentCluster;
	private double  leftFrontTirePosX, leftFrontTirePosY, leftFrontTirePosZ, 
			rightFrontTirePosX, rightFrontTirePosY, rightFrontTirePosZ, 
			leftRearTirePosX, leftRearTirePosY, leftRearTirePosZ, 
			rightRearTirePosX, rightRearTirePosY, rightRearTirePosZ;
	
	private TripletDouble axlebarOffset, driveshaftPos;
	
	private int numLights;
	
	private List<Integer> lightTypes;
	private List<LightBodyAxisPosition> lightBodyAxisPositions;
	private List<TripletDouble> lightHeadingPitchSpinSpeeds;
	private List<LightCone> lightCones;
	private List<String> lightSourceRAWs;
	private List<LightOnOffMS> lightTimesMS;
	
	private List<TripletDouble> scrapePoints;
	public String getTruckName() {
	    return truckName;
	}
	public void setTruckName(String truckName) {
	    this.truckName = truckName;
	}
	public String getTruckModelBaseName() {
	    return truckModelBaseName;
	}
	public void setTruckModelBaseName(String truckModelBaseName) {
	    this.truckModelBaseName = truckModelBaseName;
	}
	public String getTireModelBaseName() {
	    return tireModelBaseName;
	}
	public void setTireModelBaseName(String tireModelBaseName) {
	    this.tireModelBaseName = tireModelBaseName;
	}
	public String getAxleModelName() {
	    return axleModelName;
	}
	public void setAxleModelName(String axleModelName) {
	    this.axleModelName = axleModelName;
	}
	public String getShockTextureName() {
	    return shockTextureName;
	}
	public void setShockTextureName(String shockTextureName) {
	    this.shockTextureName = shockTextureName;
	}
	public String getBarTextureName() {
	    return barTextureName;
	}
	public void setBarTextureName(String barTextureName) {
	    this.barTextureName = barTextureName;
	}
	public void setRightRearTirePosZ(int rightRearTirePosZ) {
	    this.rightRearTirePosZ = rightRearTirePosZ;
	}
	public double getLeftFrontTirePosX() {
	    return leftFrontTirePosX;
	}
	public void setLeftFrontTirePosX(double leftFrontTirePosX) {
	    this.leftFrontTirePosX = leftFrontTirePosX;
	}
	public double getLeftFrontTirePosY() {
	    return leftFrontTirePosY;
	}
	public void setLeftFrontTirePosY(double leftFrontTirePosY) {
	    this.leftFrontTirePosY = leftFrontTirePosY;
	}
	public double getLeftFrontTirePosZ() {
	    return leftFrontTirePosZ;
	}
	public void setLeftFrontTirePosZ(double leftFrontTirePosZ) {
	    this.leftFrontTirePosZ = leftFrontTirePosZ;
	}
	public double getRightFrontTirePosX() {
	    return rightFrontTirePosX;
	}
	public void setRightFrontTirePosX(double rightFrontTirePosX) {
	    this.rightFrontTirePosX = rightFrontTirePosX;
	}
	public double getRightFrontTirePosY() {
	    return rightFrontTirePosY;
	}
	public void setRightFrontTirePosY(double rightFrontTirePosY) {
	    this.rightFrontTirePosY = rightFrontTirePosY;
	}
	public double getRightFrontTirePosZ() {
	    return rightFrontTirePosZ;
	}
	public void setRightFrontTirePosZ(double rightFrontTirePosZ) {
	    this.rightFrontTirePosZ = rightFrontTirePosZ;
	}
	public double getLeftRearTirePosX() {
	    return leftRearTirePosX;
	}
	public void setLeftRearTirePosX(double leftRearTirePosX) {
	    this.leftRearTirePosX = leftRearTirePosX;
	}
	public double getLeftRearTirePosY() {
	    return leftRearTirePosY;
	}
	public void setLeftRearTirePosY(double leftRearTirePosY) {
	    this.leftRearTirePosY = leftRearTirePosY;
	}
	public double getLeftRearTirePosZ() {
	    return leftRearTirePosZ;
	}
	public void setLeftRearTirePosZ(double leftRearTirePosZ) {
	    this.leftRearTirePosZ = leftRearTirePosZ;
	}
	public double getRightRearTirePosX() {
	    return rightRearTirePosX;
	}
	public void setRightRearTirePosX(double rightRearTirePosX) {
	    this.rightRearTirePosX = rightRearTirePosX;
	}
	public double getRightRearTirePosY() {
	    return rightRearTirePosY;
	}
	public void setRightRearTirePosY(double rightRearTirePosY) {
	    this.rightRearTirePosY = rightRearTirePosY;
	}
	public double getRightRearTirePosZ() {
	    return rightRearTirePosZ;
	}
	public void setRightRearTirePosZ(double rightRearTirePosZ) {
	    this.rightRearTirePosZ = rightRearTirePosZ;
	}
	public List<TripletDouble> getScrapePoints() {
	    return scrapePoints;
	}
	public void setScrapePoints(List<TripletDouble> scrapePoints) {
	    this.scrapePoints = scrapePoints;
	}
	public String getWaveFile1() {
	    return waveFile1;
	}
	public void setWaveFile1(String waveFile1) {
	    this.waveFile1 = waveFile1;
	}
	public String getWaveFile2() {
	    return waveFile2;
	}
	public void setWaveFile2(String waveFile2) {
	    this.waveFile2 = waveFile2;
	}
	public String getWaveFile3() {
	    return waveFile3;
	}
	public void setWaveFile3(String waveFile3) {
	    this.waveFile3 = waveFile3;
	}
	public String getInstrumentCluster() {
	    return instrumentCluster;
	}
	public void setInstrumentCluster(String instrumentCluster) {
	    this.instrumentCluster = instrumentCluster;
	}
	public TripletDouble getAxlebarOffset() {
	    return axlebarOffset;
	}
	public void setAxlebarOffset(TripletDouble axlebarOffset) {
	    this.axlebarOffset = axlebarOffset;
	}
	public TripletDouble getDriveshaftPos() {
	    return driveshaftPos;
	}
	public void setDriveshaftPos(TripletDouble driveshaftPos) {
	    this.driveshaftPos = driveshaftPos;
	}
	public int getNumLights() {
	    return numLights;
	}
	public void setNumLights(int numLights) {
	    this.numLights = numLights;
	}
	public List<Integer> getLightTypes() {
	    return lightTypes;
	}
	public void setLightTypes(List<Integer> lightTypes) {
	    this.lightTypes = lightTypes;
	}
	public List<LightBodyAxisPosition> getLightBodyAxisPositions() {
	    return lightBodyAxisPositions;
	}
	public void setLightBodyAxisPositions(
		List<LightBodyAxisPosition> lightBodyAxisPositions) {
	    this.lightBodyAxisPositions = lightBodyAxisPositions;
	}
	public List<TripletDouble> getLightHeadingPitchSpinSpeeds() {
	    return lightHeadingPitchSpinSpeeds;
	}
	public void setLightHeadingPitchSpinSpeeds(
		List<TripletDouble> lightHeadingPitchSpinSpeeds) {
	    this.lightHeadingPitchSpinSpeeds = lightHeadingPitchSpinSpeeds;
	}
	public List<LightCone> getLightCones() {
	    return lightCones;
	}
	public void setLightCones(List<LightCone> lightCones) {
	    this.lightCones = lightCones;
	}
	public List<String> getLightSourceRAWs() {
	    return lightSourceRAWs;
	}
	public void setLightSourceRAWs(List<String> lightSourceRAWs) {
	    this.lightSourceRAWs = lightSourceRAWs;
	}
	public List<LightOnOffMS> getLightTimesMS() {
	    return lightTimesMS;
	}
	public void setLightTimesMS(List<LightOnOffMS> lightTimesMS) {
	    this.lightTimesMS = lightTimesMS;
	}
    };
    
    public static class MTM1TRKFile extends TRKData {

	@Override
	public void describeFormat(Parser p)
		throws UnrecognizedFormatException {//MTM and MTM2 have slightly different names for various key labels
	    try {p.expectString("truckName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {p.expectString("MTM2 truckName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("truckName", String.class), false);
	    
	    try{p.expectString("truckModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {p.expectString("truckModelBaseName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("truckModelBaseName", String.class), false);
	    
	    try{p.expectString("tireModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    catch(UnrecognizedFormatException e) {p.expectString("tireModelBaseName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("tireModelBaseName", String.class), false);
	    
	    //Present in MTM2 only
	    try {p.expectString("axleModelName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("axleModelName", String.class), false);
	    } catch(UnrecognizedFormatException e) {}
	    
	    //Present in MTM2 only
	    try {p.expectString("shockTextureName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("shockTextureName", String.class), false);
	    } catch(UnrecognizedFormatException e) {}
	    
	    //Present in MTM2 only
	    try {p.expectString("barTextureName\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("barTextureName", String.class), false);
	    } catch(UnrecognizedFormatException e) {}
	    
	    //Present in MTM2 only
	    try {
		if(p.parseMode == ParseMode.WRITE && getAxlebarOffset() == null)
		    throw new UnrecognizedFormatException();
		p.expectString("axlebarOffset\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		p.subParseProposedClasses(p.property("axlebarOffset", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    } catch(UnrecognizedFormatException e) {}
	    
	  //Present in MTM2 only
	    try {
		if(p.parseMode == ParseMode.WRITE && getDriveshaftPos() == null)
		    throw new UnrecognizedFormatException();
		p.expectString("driveshaftPos\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.subParseProposedClasses(p.property("driveshaftPos", TripletDouble.EndingWithNewline.class),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    } catch(UnrecognizedFormatException e) {}
	    
	    p.expectString("faxle.rtire.static_bpos.x\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontTirePosX", double.class), false);
	    p.expectString("faxle.ltire.static_bpos.x\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontTirePosX", double.class), false);
	    p.expectString("raxle.rtire.static_bpos.x\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearTirePosX", double.class), false);
	    p.expectString("raxle.ltire.static_bpos.x\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearTirePosX", double.class), false);

	    p.expectString("faxle.rtire.static_bpos.y\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontTirePosY", double.class), false);
	    p.expectString("faxle.ltire.static_bpos.y\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontTirePosY", double.class), false);
	    p.expectString("raxle.rtire.static_bpos.y\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearTirePosY", double.class), false);
	    p.expectString("raxle.ltire.static_bpos.y\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearTirePosY", double.class), false);

	    p.expectString("faxle.rtire.static_bpos.z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightFrontTirePosZ", double.class), false);
	    p.expectString("faxle.ltire.static_bpos.z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftFrontTirePosZ", double.class), false);
	    p.expectString("raxle.rtire.static_bpos.z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("rightRearTirePosZ", double.class), false);
	    p.expectString("raxle.ltire.static_bpos.z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("leftRearTirePosZ", double.class), false);
	    
	    //SCRAPE POINTS
	    for(int i = 1; i < 13; i++) {//12 scrape points
		try{p.expectString("Scrape point "+i+" (body axis) x,y,z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
		catch(UnrecognizedFormatException e) {p.expectString("Scrape point "+i+" body axis x,y,z\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);}
		
		p.subParseProposedClasses(
			p.indexedProperty("scrapePoints", TripletDouble.EndingWithNewline.class, i-1),
			ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
	    }
	    
	    //INSTRUMENT CLUSTER
	    p.expectString("Instrument Cluster\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("instrumentCluster", String.class), false);
	    //WAVE FILE
	    p.expectString("Wave File\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile1", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile2", String.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("waveFile3", String.class), false);
	    
	    //MTM2: LIGHTS
	    try {
		p.expectString("Number of Lights\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
		    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numLights", int.class), false);
		for(int i = 0; i < getNumLights(); i++) {
		    	p.expectString("Light "+i+" type\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.stringEndingWith("\r\n",p.indexedProperty("lightTypes", Integer.class, i),false);
			p.expectString("Light "+i+" body axis pos x,y,z (ft), bitmap radius (ft)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.subParseProposedClasses(p.indexedProperty("lightBodyAxisPositions", LightBodyAxisPosition.class, i), ClassInclusion.classOf(LightBodyAxisPosition.class));
			p.expectString("Light "+i+" heading (rad), pitch (rad), heading spin speed (rad/sec)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.subParseProposedClasses(p.indexedProperty("lightHeadingPitchSpinSpeeds", TripletDouble.class, i), ClassInclusion.classOf(TripletDouble.EndingWithNewline.class));
			p.expectString("Light "+i+" cone: length (ft), base radius (ft), rim radius (ft), texture name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.subParseProposedClasses(p.indexedProperty("lightCones", LightCone.class, i), ClassInclusion.classOf(LightCone.class));
			p.expectString("Light "+i+" source: bitmap name\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.stringEndingWith("\r\n", p.indexedProperty("lightSourceRAWs", String.class, i), false);
			p.expectString("Light "+i+" ms on, ms off (0 if light doesn't blink)\r\n", FailureBehavior.UNRECOGNIZED_FORMAT);
			p.subParseProposedClasses(p.indexedProperty("lightTimesMS", LightOnOffMS.class, i), ClassInclusion.classOf(LightOnOffMS.class));
		    }
	    } catch(UnrecognizedFormatException e) {}
	}//describeFormat()

    }//end MTM1TRKFile

    public TRKData getTrkData() {
        return trkData;
    }

    public void setTrkData(TRKData trkData) {
        this.trkData = trkData;
    }

    public String getTruckName() {
	return trkData.getTruckName();
    }

    public void setTruckName(String truckName) {
	trkData.setTruckName(truckName);
    }

    public String getTruckModelBaseName() {
	return trkData.getTruckModelBaseName();
    }

    public void setTruckModelBaseName(String truckModelBaseName) {
	trkData.setTruckModelBaseName(truckModelBaseName);
    }

    public String getTireModelBaseName() {
	return trkData.getTireModelBaseName();
    }

    public void setTireModelBaseName(String tireModelBaseName) {
	trkData.setTireModelBaseName(tireModelBaseName);
    }

    public String getAxleModelName() {
	return trkData.getAxleModelName();
    }

    public void setAxleModelName(String axleModelName) {
	trkData.setAxleModelName(axleModelName);
    }

    public String getShockTextureName() {
	return trkData.getShockTextureName();
    }

    public void setShockTextureName(String shockTextureName) {
	trkData.setShockTextureName(shockTextureName);
    }

    public String getBarTextureName() {
	return trkData.getBarTextureName();
    }

    public void setBarTextureName(String barTextureName) {
	trkData.setBarTextureName(barTextureName);
    }

    public void setRightRearTirePosZ(int rightRearTirePosZ) {
	trkData.setRightRearTirePosZ(rightRearTirePosZ);
    }

    public String toString() {
	return trkData.toString();
    }

    public int hashCode() {
	return trkData.hashCode();
    }

    public double getLeftFrontTirePosX() {
	return trkData.getLeftFrontTirePosX();
    }

    public void setLeftFrontTirePosX(double leftFrontTirePosX) {
	trkData.setLeftFrontTirePosX(leftFrontTirePosX);
    }

    public double getLeftFrontTirePosY() {
	return trkData.getLeftFrontTirePosY();
    }

    public void setLeftFrontTirePosY(double leftFrontTirePosY) {
	trkData.setLeftFrontTirePosY(leftFrontTirePosY);
    }

    public double getLeftFrontTirePosZ() {
	return trkData.getLeftFrontTirePosZ();
    }

    public void setLeftFrontTirePosZ(double leftFrontTirePosZ) {
	trkData.setLeftFrontTirePosZ(leftFrontTirePosZ);
    }

    public double getRightFrontTirePosX() {
	return trkData.getRightFrontTirePosX();
    }

    public void setRightFrontTirePosX(double rightFrontTirePosX) {
	trkData.setRightFrontTirePosX(rightFrontTirePosX);
    }

    public boolean equals(Object obj) {
	return trkData.equals(obj);
    }

    public double getRightFrontTirePosY() {
	return trkData.getRightFrontTirePosY();
    }

    public void setRightFrontTirePosY(double rightFrontTirePosY) {
	trkData.setRightFrontTirePosY(rightFrontTirePosY);
    }

    public double getRightFrontTirePosZ() {
	return trkData.getRightFrontTirePosZ();
    }

    public void setRightFrontTirePosZ(double rightFrontTirePosZ) {
	trkData.setRightFrontTirePosZ(rightFrontTirePosZ);
    }

    public double getLeftRearTirePosX() {
	return trkData.getLeftRearTirePosX();
    }

    public void setLeftRearTirePosX(double leftRearTirePosX) {
	trkData.setLeftRearTirePosX(leftRearTirePosX);
    }

    public double getLeftRearTirePosY() {
	return trkData.getLeftRearTirePosY();
    }

    public void setLeftRearTirePosY(double leftRearTirePosY) {
	trkData.setLeftRearTirePosY(leftRearTirePosY);
    }

    public double getLeftRearTirePosZ() {
	return trkData.getLeftRearTirePosZ();
    }

    public void setLeftRearTirePosZ(double leftRearTirePosZ) {
	trkData.setLeftRearTirePosZ(leftRearTirePosZ);
    }

    public double getRightRearTirePosX() {
	return trkData.getRightRearTirePosX();
    }

    public void setRightRearTirePosX(double rightRearTirePosX) {
	trkData.setRightRearTirePosX(rightRearTirePosX);
    }

    public double getRightRearTirePosY() {
	return trkData.getRightRearTirePosY();
    }

    public void setRightRearTirePosY(double rightRearTirePosY) {
	trkData.setRightRearTirePosY(rightRearTirePosY);
    }

    public double getRightRearTirePosZ() {
	return trkData.getRightRearTirePosZ();
    }

    public void setRightRearTirePosZ(double rightRearTirePosZ) {
	trkData.setRightRearTirePosZ(rightRearTirePosZ);
    }

    public List<TripletDouble> getScrapePoints() {
	return trkData.getScrapePoints();
    }

    public void setScrapePoints(List<TripletDouble> scrapePoints) {
	trkData.setScrapePoints(scrapePoints);
    }

    public String getWaveFile1() {
	return trkData.getWaveFile1();
    }

    public void setWaveFile1(String waveFile1) {
	trkData.setWaveFile1(waveFile1);
    }

    public String getWaveFile2() {
	return trkData.getWaveFile2();
    }

    public void setWaveFile2(String waveFile2) {
	trkData.setWaveFile2(waveFile2);
    }

    public String getWaveFile3() {
	return trkData.getWaveFile3();
    }

    public void setWaveFile3(String waveFile3) {
	trkData.setWaveFile3(waveFile3);
    }

    public String getInstrumentCluster() {
	return trkData.getInstrumentCluster();
    }

    public void setInstrumentCluster(String instrumentCluster) {
	trkData.setInstrumentCluster(instrumentCluster);
    }

    public TripletDouble getAxlebarOffset() {
	return trkData.getAxlebarOffset();
    }

    public void setAxlebarOffset(TripletDouble axlebarOffset) {
	trkData.setAxlebarOffset(axlebarOffset);
    }

    public TripletDouble getDriveshaftPos() {
	return trkData.getDriveshaftPos();
    }

    public void setDriveshaftPos(TripletDouble driveshaftPos) {
	trkData.setDriveshaftPos(driveshaftPos);
    }

    public int getNumLights() {
	return trkData.getNumLights();
    }

    public List<Integer> getLightTypes() {
	return trkData.getLightTypes();
    }

    public List<LightBodyAxisPosition> getLightBodyAxisPositions() {
	return trkData.getLightBodyAxisPositions();
    }

    public List<TripletDouble> getLightHeadingPitchSpinSpeeds() {
	return trkData.getLightHeadingPitchSpinSpeeds();
    }

    public List<LightCone> getLightCones() {
	return trkData.getLightCones();
    }

    public List<String> getLightSourceRAWs() {
	return trkData.getLightSourceRAWs();
    }

    public List<LightOnOffMS> getLightTimesMS() {
	return trkData.getLightTimesMS();
    }

}//end TRKFile
