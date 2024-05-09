package org.jtrfp.jtrfp.sit.jfdt;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.sit.jfdt.SITFile.Box6;
import org.jtrfp.jtrfp.sit.jfdt.SITFile.Vehicle;
import org.junit.Test;

public class SITFileEvo1Test extends AbstractParserTest<SITFile> {
    
    @Test
    public void testVersion() {
	assertEquals((Integer)6,subject.getVersion());
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
    
    ///////
    
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
	    assertEquals("index "+i,2,v.getRearAxleLeftTireOnGround());
	}
    }
    
    @Test
    public void testVehicleGetRearAxleRightTireOnGround() {
	final List<Vehicle> vehicles = subject.getVehicles();
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    assertEquals("index "+i,-2,v.getRearAxleRightTireOnGround());
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
	    assertEquals("index "+i,0,v.getAutopilotEnabled());
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
    
    ////
    
    @Test
    public void testUniqueVehicles() {
	final Set<Vehicle> uniqueVehicles = new HashSet<>();
	final List<Vehicle> vehicles = subject.getVehicles();
	int numVehicles;
	assertEquals(8,numVehicles = subject.getNumVehicles());
	for(int i = 0; i < vehicles.size(); i++) {
	    final Vehicle v = vehicles.get(i);
	    uniqueVehicles.add(v);
	    assertEquals("index "+i, "testTruckFile.trk",v.getTruckFile());
	}//end for(i)
	
	//Ensure that each of these vehicles is unique and we're not just inspecting the same Vehicle over and over
	assertEquals(numVehicles, uniqueVehicles.size());
    }//end testUniqueVehicles()
    
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
    public void testFirstBoxModel() {
	assertEquals("testModel1.bin",subject.getBoxes().get(0).getModelBINFile());
    }
    
    @Test
    public void testFirstBoxMass() {
	assertEquals(8.8,subject.getBoxes().get(0).getMass());
    }
    
    @Test
    public void testFirstBoxBVelX() {
	assertEquals(9.9,subject.getBoxes().get(0).getVelX());
    }
    
    @Test
    public void testFirstBoxBVelY() {
	assertEquals(0.0,subject.getBoxes().get(0).getVelY());
    }
    
    @Test
    public void testFirstBoxBVelZ() {
	assertEquals(1.1,subject.getBoxes().get(0).getVelZ());
    }
    
    @Test
    public void testFirstBoxP() {
	assertEquals(2.2,subject.getBoxes().get(0).getP());
    }
    
    @Test
    public void testFirstBoxQ() {
	assertEquals(3.3,subject.getBoxes().get(0).getQ());
    }
    
    @Test
    public void testFirstBoxR() {
	assertEquals(4.4,subject.getBoxes().get(0).getR());
    }
    
    @Test
    public void testFirstBoxType() {
	assertEquals(5,subject.getBoxes().get(0).getBoxType());
    }
    
    @Test
    public void testFirstBoxFlags() {
	assertEquals(6,subject.getBoxes().get(0).getBoxFlags());
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
	assertEquals(8,((Box6)(subject.getBoxes().get(0))).getParent());
    }
    
    @Test
    public void testFirstBoxTimePerFrame() {
	assertEquals(9.9,((Box6)subject.getBoxes().get(0)).getTimePerFrame());
    }
    
    @Test
    public void testFirstBoxCastShadowOnMe() {
	assertEquals(true,((Box6)subject.getBoxes().get(0)).isCastShadowOnMe());
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
    
    // COURSES
    @Test
    public void testNumCourseSegments() {
	assertEquals(2,subject.getNumCourseSegments());
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
	assertEquals(0,subject.getCourseSegments().get(0).getSpeedType());
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
	assertEquals("testBackdrop0.bin",subject.getBackdropModelBINFiles().get(0));
	assertEquals("testBackdrop1.bin",subject.getBackdropModelBINFiles().get(1));
	assertEquals("testBackdrop2.bin",subject.getBackdropModelBINFiles().get(2));
    }
    
    @Test
    public void testSignaturePrefix() {
	assertEquals("01234567",subject.getSignaturePrefix());
    }
    
    @Test
    public void testSignatureSuffix() {
	assertEquals("89ABCDEF",subject.getSignatureSuffix());
    }
    
    @Override
    protected SITFile generateSubject(InputStream is) throws Exception {
	return new SITFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/sit/jfdt/Evo1Test.SIT";
    }

}//end SITFileEvo1Test
