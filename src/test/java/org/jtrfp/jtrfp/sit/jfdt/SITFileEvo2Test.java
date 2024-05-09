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

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.sit.jfdt.SITFile.Box7;
import org.jtrfp.jtrfp.sit.jfdt.SITFile.Vehicle;
import org.junit.Test;

public class SITFileEvo2Test extends AbstractParserTest<SITFile> {
    
    @Test
    public void testVersion() {
	assertEquals((Integer)7,subject.getVersion());
    }
    
    @Test
    public void testLVL() {
	assertEquals("testLVL.lvl",subject.getLvlFile());
    }
    
    @Test
    public void testAuthorName() {
	assertEquals("Test Author Name",subject.getAuthorName());
    }
    
    @Test
    public void testDetailedDescription() {
	assertEquals("Test Description",subject.getDetailedDescription());
    }
    
    @Test
    public void testRaceTrackName() {
	assertEquals("Test Track",subject.getRaceTrackName());
    }
    
    @Test
    public void testGetTrackLogoBMP() {
	assertEquals("testTrackLogo.bmp",subject.getTrackLogoBMP());
    }
    @Test
    public void testGetRaceType() {
	assertEquals(RaceType.Race,subject.getTrackRaceType());
    }
    
    @Test
    public void testGetRedbookAudioTrack() {
	assertEquals(3,subject.getRedbookAudioTrack());
    }
    
    @Test
    public void testGetAmbientSound() {
	assertEquals((Integer)4,subject.getAmbientSound());
    }
    
    @Test
    public void testGetTrackLength() {
	assertEquals(5555.5555,subject.getTrackLength());
    }
    
    @Test
    public void testGetWeatherMask() {
	assertEquals(6666,subject.getWeatherMask());
    }
    
    @Test
    public void testGetViewMode() {
	assertEquals(4,subject.getViewMode());
    }
    
    @Test
    public void testGetSpotD() {
	assertEquals(5,subject.getSpotd());
    }
    
    @Test
    public void testGetSpotP() {
	assertEquals(6,subject.getSpotp());
    }
    
    @Test
    public void testGetSpotH() {
	assertEquals(7,subject.getSpoth());
    }
    
    @Test
    public void testGetZoom() {
	assertEquals(8,subject.getZoom());
    }
    
    @Test
    public void testRaceTime() {
	assertEquals(1.1,subject.getRaceTime(),.0001);
    }
    
    @Test
    public void testRaceStartTime() {
	assertEquals(2.2,subject.getRaceStartTime(),.0001);
    }
    
    @Test
    public void testDragDebugTimer() {
	assertEquals(3.3,subject.getDragDebugTimer(),.0001);
    }
    
    @Test
    public void testControlFlag() {
	assertEquals(true,subject.isControlFlag());
    }
    
    @Test
    public void testAutoShift() {
	assertEquals(true,subject.isAutoShift());
    }
    
    @Test
    public void testAutoStage() {
	assertEquals(true,subject.isAutoStage());
    }
    
    @Test
    public void testBothStaged() {
	assertEquals(true,subject.isBothStaged());
    }
    
    @Test
    public void testBothStagedPrev() {
	assertEquals(true,subject.isBothStagedPrev());
    }
    
    @Test
    public void testStageComFlag() {
	assertEquals(true,subject.isStageComFlag());
    }
    
    @Test
    public void testBonusLapFlag() {
	assertEquals(true,subject.isBonusLapFlag());
    }
    
    @Test
    public void testNumVehicles() {
	assertEquals(8,subject.getNumVehicles());
    }
    
    @Test
    public void testVehiclesPresent() {
	assertNotNull(subject.getVehicles());
    }
    
    @Test
    public void testVehicleGetPosX() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1.1,v.getPosX());
	}
    }
    
    @Test
    public void testVehicleGetPosY() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,2.2,v.getPosY());
	}
    }
    
    @Test
    public void testVehicleGetPosZ() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,3.3,v.getPosZ());
	}
    }
    
    @Test
    public void testVehicleGetVelX() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,3.3,v.getVelX());
	}
    }
    
    @Test
    public void testVehicleGetVelY() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,4.4,v.getVelY());
	}
    }
    
    @Test
    public void testVehicleGetVelZ() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,5.5,v.getVelZ());
	}
    }
    
    @Test
    public void testVehicleGetTheta() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,6.6,v.getTheta());
	}
    }
    
    @Test
    public void testVehicleGetPhi() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,7.7,v.getPhi());
	}
    }
    
    @Test
    public void testVehicleGetPsi() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,8.8,v.getPsi());
	}
    }
    
    @Test
    public void testVehicleGetP() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,9.9,v.getP());
	}
    }
    
    @Test
    public void testVehicleGetQ() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1.1,v.getQ());
	}
    }
    
    @Test
    public void testVehicleGetR() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,2.2,v.getR());
	}
    }
    
    @Test
    public void testVehicleGetFrontAxleAngle() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,3.3,v.getFrontAxleAngle());
	}
    }
    
    @Test
    public void testVehicleGetRearAxleAngle() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,0.0,v.getRearAxleAngle());
	}
    }
    
    @Test
    public void testVehicleGetFrontAxleSteeringAngle() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,-4.4,v.getFrontAxleSteeringAngle());
	}
    }
    
    @Test
    public void testVehicleGetRearAxleSteeringAngle() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1.1,v.getRearAxleSteeringAngle());
	}
    }
    
    @Test
    public void testVehicleGetRearAxleLeftTireOnGround() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,-1,v.getRearAxleLeftTireOnGround());
	}
    }
    
    @Test
    public void testVehicleGetRearAxleRightTireOnGround() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1,v.getRearAxleRightTireOnGround());
	}
    }
    
    @Test
    public void testVehicleGetFrontAxleLeftTireOnGround() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1,v.getFrontAxleLeftTireOnGround());
	}
    }
    
    @Test
    public void testVehicleGetFrontAxleRightTireOnGround() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,-1,v.getFrontAxleRightTireOnGround());
	}
    }
    
    @Test
    public void testVehicleGetTransmissionGear() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,4,v.getTransmissionGear());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotNumber() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1,v.getAutopilotNumber());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotEnabled() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1,v.getAutopilotEnabled());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotSpeedControl() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,2.2,v.getAutopilotSpeedControl());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotCourseControl() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,3.3,v.getAutopilotCourseControl());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotLastError() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,4.4,v.getAutopilotLastError());
	}
    }
    
    @Test
    public void testVehicleGetAutopilotCourseToFollow() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,2,v.getAutopilotCourseToFollow());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterTimer() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,5.5,v.getHelicopterTimer());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterTheta() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,6.6,v.getHelicopterTheta());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterPhi() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,7.7,v.getHelicopterPhi());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterPsi() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,8.8,v.getHelicopterPsi());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterPosX() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,9.9,v.getHeliPosX());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterPosY() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,0.0,v.getHeliPosY());
	}
    }
    
    @Test
    public void testVehicleGetHelicopterPosZ() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,1.1,v.getHeliPosZ());
	}
    }
    
    @Test
    public void testUniqueVehicles() {
	final Set<Vehicle> uniqueVehicles = new HashSet<>();
	int numVehicles;
	assertEquals(8,numVehicles = subject.getNumVehicles());
	for(int i = 0; i < subject.getVehicles().size(); i++) {
	    final Vehicle v = subject.getVehicles().get(i);
	    uniqueVehicles.add(v);
	    assertEquals("index "+i, "testTruckFile.trk",v.getTruckFile());
	}//end for(i)
	
	//Ensure that each of these vehicles is unique and we're not just inspecting the same Vehicle over and over
	assertEquals(numVehicles, uniqueVehicles.size());
    }//end testUniqueVehicles()
    
    @Test
    public void testNumBoxTypes() {
	assertEquals(2,subject.getNumBoxTypes());
    }
    
    @Test
    public void testBox0TypeIndex() {
	assertEquals(6,subject.getBoxTypes().get(0).getIndex());
    }
    
    @Test
    public void testBox0TypeName() {
	subject.getBoxTypes().get(0).getName();
	assertEquals("CVehicle",subject.getBoxTypes().get(0).getName());
    }
    
    @Test
    public void testBox1TypeIndex() {
	assertEquals(1,subject.getBoxTypes().get(1).getIndex());
    }
    
    @Test
    public void testBox1TypeName() {
	assertEquals("CBox",subject.getBoxTypes().get(1).getName());
    }
    
    ///BOX NAMES
    @Test
    public void testBox0NameName() {
	assertEquals("CCollide",subject.getBoxNames().get(0).getName());
    }
    
    @Test
    public void testBox0NameIndex() {
	assertEquals(1,subject.getBoxNames().get(0).getIndex());
    }
    
    @Test
    public void testBox1NameName() {
	assertEquals("CCollide",subject.getBoxNames().get(1).getName());
    }
    
    @Test
    public void testBox1NameIndex() {
	assertEquals(2,subject.getBoxNames().get(1).getIndex());
    }
    
    ///BOX DATA
    @Test
    public void testBox0Name() {
	assertEquals("testName",((SITFile.Box7)subject.getBoxes().get(0)).getName());
    }
    
    @Test
    public void testBox0StaticName() {
	assertEquals("testStaticName.smf",((SITFile.Box7)subject.getBoxes().get(0)).getModelBINFile());
    }
    
    @Test
    public void testBox0Width() {
	assertEquals(6.6,((SITFile.Box7)subject.getBoxes().get(0)).getWidth());
    }
    
    @Test
    public void testBox0Height() {
	assertEquals(7.7,((SITFile.Box7)subject.getBoxes().get(0)).getHeight());
    }
    
    @Test
    public void testBox0Length() {
	assertEquals(5.5,((SITFile.Box7)subject.getBoxes().get(0)).getLength());
    }
    
    ////
    
    @Test
    public void testNumBoxes() {
	assertEquals(2,subject.getNumBoxes());
    }
    
    @Test
    public void testBoxesPresent() {
	assertNotNull(subject.getBoxes());
    }
    
    @Test
    public void testFirstBoxPosX() {
	assertEquals(2.2,subject.getBoxes().get(0).getPosX());
    }
    
    @Test
    public void testFirstBoxPosY() {
	assertEquals(3.3,subject.getBoxes().get(0).getPosY());
    }
    
    @Test
    public void testFirstBoxPosZ() {
	assertEquals(4.4,subject.getBoxes().get(0).getPosZ());
    }
    
    @Test
    public void testFirstBoxTheta() {
	assertEquals(5.5,subject.getBoxes().get(0).getTheta());
    }
    
    @Test
    public void testFirstBoxPhi() {
	assertEquals(6.6,subject.getBoxes().get(0).getPhi());
    }
    
    @Test
    public void testFirstBoxPsi() {
	assertEquals(7.7,subject.getBoxes().get(0).getPsi());
    }
    
    @Test
    public void testFirstBoxMass() {
	assertEquals(8.8,subject.getBoxes().get(0).getMass());
    }
    
    @Test
    public void testFirstBoxPriority() {
	assertEquals(7,subject.getBoxes().get(0).getPriority());
    }
    
    @Test
    public void testFirstBoxSfxWAVFile() {
	assertEquals("NULL0.WAV",subject.getBoxes().get(0).getSfxWAVFile());
    }
    
    @Test
    public void testFirstBoxContinuousWAVFile() {
	assertEquals("NULL1.WAV",subject.getBoxes().get(0).getContinuousWAVFile());
    }
    
    @Test
    public void testFirstBoxParent() {
	assertEquals(8,((Box7)(subject.getBoxes().get(0))).getParent());
    }
    
    @Test
    public void testFirstBoxTimePerFrame() {
	assertEquals(9.9,((Box7)subject.getBoxes().get(0)).getTimePerFrame());
    }
    
    @Test
    public void testFirstBoxCastShadowOnMe() {
	assertEquals(true,((Box7)subject.getBoxes().get(0)).isCastShadowOnMe());
    }
    
    @Test
    public void testFirstBoxCollisionType() {
	assertEquals(3,((Box7)subject.getBoxes().get(0)).getCollisionType());
    }
    
    // ENVIRONMENTAL LIGHTS
    @Test
    public void testNumEnvironmentalLights() {
	assertEquals(2,subject.getNumEnvironmentalLights());
    }
    
    @Test
    public void testEnvironmentalLights() {
	assertNotNull(subject.getEnvironmentalLights());
    }
    
    //// LIGHT 0 (1 in file)
    @Test
    public void testEnvironmentalLight0Type() {
	assertEquals(1,subject.getEnvironmentalLights().get(0).getLightType());
    }
    
    @Test
    public void testEnvironmentalLight0PosX() {
	assertEquals(2.2,subject.getEnvironmentalLights().get(0).getPositionX());
    }

    @Test
    public void testEnvironmentalLight0PosY() {
	assertEquals(3.3,subject.getEnvironmentalLights().get(0).getPositionY());
    }
    
    @Test
    public void testEnvironmentalLight0PosZ() {
	assertEquals(4.4,subject.getEnvironmentalLights().get(0).getPositionZ());
    }
    
    @Test
    public void testEnvironmentalLight0Theta() {
	assertEquals(5.5,subject.getEnvironmentalLights().get(0).getTheta());
    }
    
    @Test
    public void testEnvironmentalLight0Phi() {
	assertEquals(6.6,subject.getEnvironmentalLights().get(0).getPhi());
    }
    
    @Test
    public void testEnvironmentalLight0Psi() {
	assertEquals(7.7,subject.getEnvironmentalLights().get(0).getPsi());
    }
    
    @Test
    public void testEnvironmentalLight0HeadingSpinSpeed() {
	assertEquals(8.8,subject.getEnvironmentalLights().get(0).getHeadingSpinSpeed());
    }
    
    @Test
    public void testEnvironmentalLight0SourceOrientation() {
	assertEquals(2,subject.getEnvironmentalLights().get(0).getSourceOrientation());
    }
    
    @Test
    public void testEnvironmentalLight0SourceRadius() {
	assertEquals(9.9,subject.getEnvironmentalLights().get(0).getSourceRadius());
    }
    
    @Test
    public void testEnvironmentalLight0SourceTexture() {
	assertEquals("sourceTexture.raw",subject.getEnvironmentalLights().get(0).getSourceTexture());
    }
    
    @Test
    public void testEnvironmentalLight0coneLength() {
	assertEquals(1.1,subject.getEnvironmentalLights().get(0).getConeLength());
    }
    
    @Test
    public void testEnvironmentalLight0coneBaseRadius() {
	assertEquals(2.2,subject.getEnvironmentalLights().get(0).getConeBaseRadius());
    }
    
    @Test
    public void testEnvironmentalLight0coneRimRadius() {
	assertEquals(3.3,subject.getEnvironmentalLights().get(0).getConeRimRadius());
    }
    
    @Test
    public void testEnvironmentalLight0coneTexture() {
	assertEquals("coneTexture.raw",subject.getEnvironmentalLights().get(0).getConeTexture());
    }
    
    @Test
    public void testEnvironmentalLight0Intensity() {
	assertEquals(4444,subject.getEnvironmentalLights().get(0).getIntensity());
    }
    
    @Test
    public void testEnvironmentalLight0MSOn() {
	assertEquals(1,subject.getEnvironmentalLights().get(0).getOnMillis());
    }
    
    @Test
    public void testEnvironmentalLight0MSOff() {
	assertEquals(2,subject.getEnvironmentalLights().get(0).getOffMillis());
    }
    
    @Test
    public void testEnvironmentalLight0LensFlare() {
	assertEquals(true,subject.getEnvironmentalLights().get(0).isCreatesLensFlare());
    }
    
    @Test
    public void testEnvironmentalLight0Parent() {
	assertEquals(2,subject.getEnvironmentalLights().get(0).getParent());
    }
    
    // COURSES
    @Test
    public void testNumCourseSegments() {
	assertEquals(2,subject.getNumCourseSegments());
    }
    
    @Test
    public void testNumCourseDirection() {
	assertEquals(3,subject.getCourseDirection());
    }
    
    @Test
    public void testCoursesPresent() {
	assertNotNull(subject.getCourseSegments());
    }
    
    @Test
    public void testFirstCourseSegmentType() {
	assertEquals(1,subject.getCourseSegments().get(0).getSegmentType());
    }
    
    @Test
    public void testFirstCourseSegmentSpeedType() {
	assertEquals(2,subject.getCourseSegments().get(0).getSpeedType());
    }
    
    @Test
    public void testFirstCourseSegmentStartX() {
	assertEquals(0.0,subject.getCourseSegments().get(0).getStartX());
    }
    
    @Test
    public void testFirstCourseSegmentStartY() {
	assertEquals(1.1,subject.getCourseSegments().get(0).getStartY());
    }
    
    @Test
    public void testFirstCourseSegmentStartZ() {
	assertEquals(2.2,subject.getCourseSegments().get(0).getStartZ());
    }
    
    @Test
    public void testFirstCourseSegmentEndX() {
	assertEquals(3.3,subject.getCourseSegments().get(0).getEndX());
    }
    
    @Test
    public void testFirstCourseSegmentEndY() {
	assertEquals(4.4,subject.getCourseSegments().get(0).getEndY());
    }
    
    @Test
    public void testFirstCourseSegmentEndZ() {
	assertEquals(5.5,subject.getCourseSegments().get(0).getEndZ());
    }
    
    @Test
    public void testFirstCourseSegmentDECPoint() {
	assertEquals(6.6,subject.getCourseSegments().get(0).getDecPoint());
    }
    
    @Test
    public void testFirstCourseSegmentSpeed() {
	assertEquals(7.7,subject.getCourseSegments().get(0).getSpeed());
    }
    
    @Test
    public void testFirstCourseSegmentIsLastEntry() {
	assertFalse(subject.getCourseSegments().get(0).isLastEntry());
    }
    
    @Test
    public void testFirstCourseSpeedLimit() {
	assertEquals(8.8,subject.getCourseSegments().get(0).getSpeedLimit());
    }
    
    @Test
    public void testFirstCourseTrackWidth() {
	assertEquals(9.9,subject.getCourseSegments().get(0).getTrackWidth());
    }
    //EXTENDED COURSE DEFINITIONS
    @Test
    public void testGetNumExtendedCourseDefinitions() {
	assertEquals(4,subject.getNumExtendedCourseSegments());
    }
    
    @Test
    public void testGetExtendedCourseDefinitions() {
	assertNotNull(subject.getExtendedCourseSegments());
    }
    
    //PATHS
    @Test
    public void testGetNumPaths() {
	assertEquals(2,subject.getNumPaths());
    }
    
    @Test
    public void testGetPaths() {
	assertNotNull(subject.getPaths());
    }
    
    @Test
    public void testPath0GetNumPoints() {
	assertEquals(2,subject.getPaths().get(0).getNumPoints());
    }
    
    @Test
    public void testPath0GetType() {
	assertEquals(1,subject.getPaths().get(0).getType());
    }
    
    @Test
    public void testPath0GetDirection() {
	assertEquals(2,subject.getPaths().get(0).getDirection());
    }
    
    @Test
    public void testPath0GetCycles() {
	assertEquals(3,subject.getPaths().get(0).getNumCycles());
    }
    
    @Test
    public void testPath0GetTime() {
	assertEquals(4.0,subject.getPaths().get(0).getTime());
    }
    
    @Test
    public void testPath0GetPauseTimeElapsed() {
	assertEquals(5.0,subject.getPaths().get(0).getPauseTimeElapsed());
    }
    
    @Test
    public void testPath0GetT() {
	assertEquals(6.0,subject.getPaths().get(0).getT());
    }
    
    @Test
    public void testPath0GetTension() {
	assertEquals(7.0,subject.getPaths().get(0).getTension());
    }
    
    @Test
    public void testPath0GetStopFlag() {
	assertEquals(8,subject.getPaths().get(0).getStopFlag());
    }
    
    @Test
    public void testPath0GetCollisionType() {
	assertEquals(9,subject.getPaths().get(0).getCollisionType());
    }
    
    @Test
    public void testPath0GetObject() {
	assertEquals(10,subject.getPaths().get(0).getObject());
    }
    
    //PATH0 POINT0
    @Test
    public void testPath0GetPathPoints() {
	assertNotNull(subject.getPaths().get(0).getPathPoints());
    }
    
    @Test
    public void testPath0PathPoint0GetPositionX() {
	assertEquals(111.1,subject.getPaths().get(0).getPathPoints().get(0).getPositionX());
    }
    
    @Test
    public void testPath0PathPoint0GetPositionY() {
	assertEquals(222.2,subject.getPaths().get(0).getPathPoints().get(0).getPositionY());
    }
    
    @Test
    public void testPath0PathPoint0GetPositionZ() {
	assertEquals(333.3,subject.getPaths().get(0).getPathPoints().get(0).getPositionZ());
    }
    
    @Test
    public void testPath0PathPoint0GetTheta() {
	assertEquals(444.4,subject.getPaths().get(0).getPathPoints().get(0).getTheta());
    }
    
    @Test
    public void testPath0PathPoint0GetPhi() {
	assertEquals(555.5,subject.getPaths().get(0).getPathPoints().get(0).getPhi());
    }
    
    @Test
    public void testPath0PathPoint0GetPsi() {
	assertEquals(666.6,subject.getPaths().get(0).getPathPoints().get(0).getPsi());
    }
    
    @Test
    public void testPath0PathPoint0GetVelocity() {
	assertEquals(7.0,subject.getPaths().get(0).getPathPoints().get(0).getVelocity());
    }
    
    @Test
    public void testPath0PathPoint0GetTime() {
	assertEquals(8.0,subject.getPaths().get(0).getPathPoints().get(0).getTime());
    }
    
    @Test
    public void testPath0PathPoint0GetPauseTime() {
	assertEquals(9.0,subject.getPaths().get(0).getPathPoints().get(0).getPauseTime());
    }
    
    @Test
    public void testPath0PathPoint0SegmentFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isSegmentFlag());
    }
    
    @Test
    public void testPath0PathPoint0OrientFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isOrientationFlag());
    }
    
    @Test
    public void testPath0PathPoint0VelocityFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isVelocityFlag());
    }
    
    @Test
    public void testPath0PathPoint0FollowTerrainFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isFollowTerrainFlag());
    }
    
    @Test
    public void testPath0PathPoint0AnimationFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isFollowTerrainFlag());
    }
    
    @Test
    public void testPath0PathPoint0LightFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isLightFlag());
    }
    
    @Test
    public void testPath0PathPoint0AIFlag() {
	assertTrue(subject.getPaths().get(0).getPathPoints().get(0).isAiFlag());
    }
    
    @Test
    public void testPath0PathPoint0EventWAVFile() {
	assertEquals("pointWav.wav",subject.getPaths().get(0).getPathPoints().get(0).getEventWAVFile());
    }
    
    @Test
    public void testPath0PathPoint0ContinuousWAVFile() {
	assertEquals("continuousWav.wav",subject.getPaths().get(0).getPathPoints().get(0).getContinuousWAVFile());
    }
    
    //STADIUM
    @Test
    public void testStadiumFlag() {
	assertEquals(Integer.valueOf(1),subject.getStadiumFlag());
    }
    
    @Test
    public void testStadiumX() {
	assertEquals(Integer.valueOf(2),subject.getX());
    }
    
    @Test
    public void testStadiumZ() {
	assertEquals(Integer.valueOf(3),subject.getZ());
    }
    
    @Test
    public void testStadiumSX() {
	assertEquals(Integer.valueOf(4),subject.getSx());
    }
    
    @Test
    public void testStadiumSZ() {
	assertEquals(Integer.valueOf(5),subject.getSz());
    }
    
    @Test
    public void testStadiumModelName() {
	assertEquals("testStadiumModel.bin",subject.getStadiumModelBINFile());
    }
    
    @Test
    public void testBackdropType() {
	assertEquals(2,subject.getBackdropType());
    }
    
    @Test
    public void testBackdropCount() {
	assertEquals(3,subject.getBackdropCount());
    }
    
    @Test
    public void testBackdrops() {
	assertEquals("testBackdrop0.SMF",subject.getBackdropModelBINFiles().get(0));
	assertEquals("testBackdrop1.SMF",subject.getBackdropModelBINFiles().get(1));
	assertEquals("testBackdrop2.SMF",subject.getBackdropModelBINFiles().get(2));
    }
    
    @Override
    protected SITFile generateSubject(InputStream is) throws Exception {
	return new SITFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/sit/jfdt/Evo2Test.SIT";
    }

}//end SITFileEvo2Test
