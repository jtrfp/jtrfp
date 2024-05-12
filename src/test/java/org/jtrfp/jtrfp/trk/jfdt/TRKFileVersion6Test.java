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

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.trk.jfdt.TRKFile.Type6and7TruckFile;
import org.junit.Test;

public class TRKFileVersion6Test
	extends AbstractParserTest<Type6and7TruckFile> {
    
    @Test
    public void testGetTruckName() {
	assertEquals("Test Truck",subject.getTruckName());
    }

    @Test
    public void testGetTruckModelBaseName() {
	assertEquals("testBin.BIN",subject.getTruckModelBaseName());
    }

    @Test
    public void testGetTireModelBaseName() {
	assertEquals("testTire.BIN",subject.getTireModelBaseName());
    }

    @Test
    public void testGetLeftFrontTirePosX() {
	assertEquals(2.2,subject.getLeftFrontTirePosX());
    }

    @Test
    public void testGetLeftFrontTirePosY() {
	assertEquals(3.3,subject.getLeftFrontTirePosY());
    }

    @Test
    public void testGetLeftFrontTirePosZ() {
	assertEquals(4.4,subject.getLeftFrontTirePosZ());
    }

    @Test
    public void testGetRightFrontTirePosX() {
	assertEquals(8.8,subject.getRightFrontTirePosX());
    }

    @Test
    public void testGetRightFrontTirePosY() {
	assertEquals(9.9,subject.getRightFrontTirePosY());
    }
    
    @Test
    public void testGetRightFrontTirePosZ() {
	assertEquals(1.1,subject.getRightFrontTirePosZ());
    }

    @Test
    public void testGetLeftRearTirePosX() {
	assertEquals(8.8,subject.getLeftRearTirePosX());
    }

    @Test
    public void testGetLeftRearTirePosY() {
	assertEquals(9.9,subject.getLeftRearTirePosY());
    }

    @Test
    public void testGetLeftRearTirePosZ() {
	assertEquals(1.1,subject.getLeftRearTirePosZ());
    }

    @Test
    public void testGetRightRearTirePosX() {
	assertEquals(5.5,subject.getRightRearTirePosX());
    }

    @Test
    public void testGetRightRearTirePosY() {
	assertEquals(6.6,subject.getRightRearTirePosY());
    }

    @Test
    public void testGetRightRearTirePosZ() {
	assertEquals(7.7,subject.getRightRearTirePosZ());
    }
    
    ////
    @Test
    public void testGetWaveFile1() {
	assertEquals("test0.wav",subject.getWaveFile1());
    }

    @Test
    public void testGetWaveFile2() {
	assertEquals("test1.wav",subject.getWaveFile2());
    }

    @Test
    public void testGetWaveFile3() {
	assertEquals("test2.wav",subject.getWaveFile3());
    }

    @Test
    public void testGetInstrumentCluster() {
	assertEquals("testInstrumentCluster",subject.getInstrumentCluster());
    }
    
    /////////////////////
    
    @Test
    public void testGetAxleModelName() {
	assertEquals("testAxle.BIN",subject.getAxleModelName());
    }

    @Test
    public void testGetShockTextureName() {
	assertEquals("testShock.RAW",subject.getShockTextureName());
    }

    @Test
    public void testGetBarTextureName() {
	assertEquals("testAxleBar.RAW",subject.getBarTextureName());
    }
    
    @Test
    public void testGetAxlebarOffsetX() {
	assertEquals(1.1,subject.getAxlebarOffset().getX());
    }
    
    @Test
    public void testGetAxlebarOffsetY() {
	assertEquals(2.2,subject.getAxlebarOffset().getY());
    }
    
    @Test
    public void testGetAxlebarOffsetZ() {
	assertEquals(3.3,subject.getAxlebarOffset().getZ());
    }

    @Test
    public void testGetDriveshaftPosX() {
	assertEquals(4.4,subject.getDriveshaftPos().getX());
    }
    
    @Test
    public void testGetDriveshaftPosY() {
	assertEquals(5.5,subject.getDriveshaftPos().getY());
    }
    
    @Test
    public void testGetDriveshaftPosZ() {
	assertEquals(6.6,subject.getDriveshaftPos().getZ());
    }
    
    //// LIGHTS
    
    @Test
    public void testGetNumLights() {
	assertEquals(2,subject.getNumLights());
    }
    
    @Test
    public void testLight0GetType() {
	assertEquals((Integer)3,subject.getLightTypes().get(0));
    }
    
    @Test
    public void testLight0GetBodyAxisPositionX() {
	assertEquals(1.1,subject.getLightBodyAxisPositions().get(0).getX());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionY() {
	assertEquals(2.2,subject.getLightBodyAxisPositions().get(0).getY());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionZ() {
	assertEquals(3.3,subject.getLightBodyAxisPositions().get(0).getZ());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionBitmapRadius() {
	assertEquals(4.4,subject.getLightBodyAxisPositions().get(0).getBitmapRadius());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionHeading() {
	assertEquals(5.5,subject.getLightHeadingPitchSpinSpeeds().get(0).getX());
    }
    
    @Test
    public void testLight0GetBodyAxisPositionPitch() {
	assertEquals(6.6,subject.getLightHeadingPitchSpinSpeeds().get(0).getY());
    }
    
    @Test
    public void testLight0GetSpin() {
	assertEquals(7.7,subject.getLightHeadingPitchSpinSpeeds().get(0).getZ());
    }
    
    @Test
    public void testLight0GetLength() {
	assertEquals(1.1,subject.getLightCones().get(0).getLengthFeet());
    }
    
    @Test
    public void testLight0GetBaseRadius() {
	assertEquals(2.2,subject.getLightCones().get(0).getBaseRadiusFeet());
    }
    
    @Test
    public void testLightCone0GetRimRadius() {
	assertEquals(3.3,subject.getLightCones().get(0).getRimRadiusFeet());
    }
    
    @Test
    public void testLightCone0GetTextureName() {
	assertEquals("testLightTexture.RAW",subject.getLightCones().get(0).getTextureName());
    }
    
    @Test
    public void testLight0GetSourceBitmapName() {
	assertEquals("testLightSourceBitmap.RAW",subject.getLightSourceRAWs().get(0));
    }
    
    @Test
    public void testLight0GetLightOnMS() {
	assertEquals(4,subject.getLightTimesMS().get(0).getOnTimeMS());
    }
    
    @Test
    public void testLight0GetLightOffMS() {
	assertEquals(5,subject.getLightTimesMS().get(0).getOffTimeMS());
    }
    
    //////////////////////////////////////

    @Test
    public void testGetTruckMake() {
	assertEquals("Test Make",subject.getTruckMake());
    }

    @Test
    public void testGetTruckModel() {
	assertEquals("Test Truck Model",subject.getTruckModel());
    }

    @Test
    public void testGetTruckClass() {
	assertEquals("Test Truck Class",subject.getTruckClass());
    }

    @Test
    public void testGetTruckCost() {
	assertEquals(1234,subject.getTruckCost());
    }

    @Test
    public void testGetTruckModelYear() {
	assertEquals(4567,subject.getTruckModelYear());
    }

    @Test
    public void testGetTruckQuickClass() {
	assertEquals(1,subject.getTruckQuickClass());
    }

    @Test
    public void testGetTruckLength() {
	assertEquals(1.1,subject.getTruckLength());
    }

    @Test
    public void testGetTruckWidth() {
	assertEquals(2.2,subject.getTruckWidth());
    }

    @Test
    public void testGetTruckHeight() {
	assertEquals(3.3,subject.getTruckHeight());
    }

    @Test
    public void testGetTruckWheelbase() {
	assertEquals(4.4,subject.getTruckWheelbase());
    }

    @Test
    public void testGetTruckFrontTrack() {
	assertEquals(5.5,subject.getTruckFrontTrack());
    }
    
    @Test
    public void testGetTruckRearTrack() {
	assertEquals(6.6,subject.getTruckRearTrack());
    }

    @Test
    public void testGetTruckAcceleration() {
	assertEquals(7.7,subject.getTruckAcceleration());
    }

    @Test
    public void testGetTruckTopSpeed() {
	assertEquals(8.8,subject.getTruckTopSpeed());
    }

    @Test
    public void testGetTruckHandling() {
	assertEquals(9.9,subject.getTruckHandling());
    }

    @Test
    public void testGetRightFrontCF() {
	assertEquals(6.6,subject.getRightFrontCF());
    }

    @Test
    public void testGetLeftFrontCF() {
	assertEquals(7.7,subject.getLeftFrontCF());
    }

    @Test
    public void testGetRightRearCF() {
	assertEquals(8.8,subject.getRightRearCF());
    }

    @Test
    public void testGetLeftRearCF() {
	assertEquals(9.9,subject.getLeftRearCF());
    }

    @Test
    public void testGetMaxAngleFrontAxle() {
	assertEquals(1.1,subject.getMaxAngleFrontAxle());
    }

    @Test
    public void testGetMaxAngleRearAxle() {
	assertEquals(2.2,subject.getMaxAngleRearAxle());
    }

    @Test
    public void testGetMaxCompressionFrontAxle() {
	assertEquals(3.3,subject.getMaxCompressionFrontAxle());
    }

    @Test
    public void testGetMaxCompressionRearAxle() {
	assertEquals(4.4,subject.getMaxCompressionRearAxle());
    }

    @Test
    public void testGetSpringRateFrontAxle() {
	assertEquals(5.5,subject.getSpringRateFrontAxle());
    }

    @Test
    public void testGetSpringRateRearAxle() {
	assertEquals(6.6,subject.getSpringRateRearAxle());
    }

    @Test
    public void testGetTorquePercentFrontAxle() {
	assertEquals(7.7,subject.getTorquePercentFrontAxle());
    }

    @Test
    public void testGetTorquePercentRearAxle() {
	assertEquals(8.8,subject.getTorquePercentRearAxle());
    }

    @Test
    public void testGetBiasFrontAxle() {
	assertEquals(9.9,subject.getBiasFrontAxle());
    }

    @Test
    public void testGetBiasRearAxle() {
	assertEquals(1.1,subject.getBiasRearAxle());
    }

    @Test
    public void testGetGearRatioPark() {
	assertEquals(1.1,subject.getGearRatioPark());
    }

    @Test
    public void testGetGearRatioReverse() {
	assertEquals(2.2,subject.getGearRatioReverse());
    }

    @Test
    public void testGetGearRatioNeutral() {
	assertEquals(3.3,subject.getGearRatioNeutral());
    }

    @Test
    public void testGetGearRatio1st() {
	assertEquals(4.4,subject.getGearRatio1st());
    }

    @Test
    public void testGetGearRatio2nd() {
	assertEquals(5.5,subject.getGearRatio2nd());
    }

    @Test
    public void testGetGearRatio3rd() {
	assertEquals(6.6,subject.getGearRatio3rd());
    }

    @Test
    public void testGetGearRatio4th() {
	assertEquals(7.7,subject.getGearRatio4th());
    }

    @Test
    public void testGetGearRatio5th() {
	assertEquals(8.8,subject.getGearRatio5th());
    }

    @Test
    public void testGetGearInertiaPark() {
	assertEquals(-1.1,subject.getGearInertiaPark());
    }

    @Test
    public void testGetGearInertiaReverse() {
	assertEquals(-2.2,subject.getGearInertiaReverse());
    }

    @Test
    public void testGetGearInertiaNeutral() {
	assertEquals(-3.3,subject.getGearInertiaNeutral());
    }

    @Test
    public void testGetGearInertia1st() {
	assertEquals(-4.4,subject.getGearInertia1st());
    }

    @Test
    public void testGetGearInertia2nd() {
	assertEquals(-5.5,subject.getGearInertia2nd());
    }

    @Test
    public void testGetGearInertia3rd() {
	assertEquals(-6.6,subject.getGearInertia3rd());
    }

    @Test
    public void testGetGearInertia4th() {
	assertEquals(-7.7,subject.getGearInertia4th());
    }

    @Test
    public void testGetGearInertia5th() {
	assertEquals(-8.8,subject.getGearInertia5th());
    }

    @Test
    public void testGetTransmissionFinalDrive() {
	assertEquals(1.1,subject.getTransmissionFinalDrive());
    }

    @Test
    public void testGetTransmissionAxleRatio() {
	assertEquals(2.2,subject.getTransmissionAxleRatio());
    }

    @Test
    public void testGetTransmissionPercentLoss() {
	assertEquals(3.3,subject.getTransmissionPercentLoss());
    }

    @Test
    public void testGetTransmissionTransferLowRatio() {
	assertEquals(4.4,subject.getTransmissionTransferLowRatio());
    }

    @Test
    public void testGetTransmissionType() {
	assertEquals(5, subject.getTransmissionType());
    }

    @Test
    public void testGetEngineType() {
	assertEquals(7,subject.getEngineType());
    }

    @Test
    public void testGetEngineAspiration() {
	assertEquals(8,subject.getEngineAspiration());
    }

    @Test
    public void testGetEngineMaxHP() {
	assertEquals(6.6,subject.getEngineMaxHP());
    }

    @Test
    public void testGetEngineRPMAtMaxHP() {
	assertEquals(7.7,subject.getEngineRPMAtMaxHP());
    }

    @Test
    public void testGetEngineMaxTorque() {
	assertEquals(8.8,subject.getEngineMaxTorque());
    }

    @Test
    public void testGetEngineRPMAtMaxTorque() {
	assertEquals(9.9,subject.getEngineRPMAtMaxTorque());
    }

    @Test
    public void testGetEngineRedline() {
	assertEquals(1.1,subject.getEngineRedline());
    }

    @Test
    public void testGetEngineRedlineTimer() {
	assertEquals(2.2,subject.getEngineRedlineTimer());
    }

    @Test
    public void testGetNumTorqueTableEntries() {
	assertEquals(3,subject.getNumTorqueTableEntries());
    }

    @Test
    public void testGetTorqueTableEntries() {
	assertNotNull(subject.getTorqueTableEntries());
    }

    @Test
    public void testGetEngineUpshiftRPM() {
	assertEquals(4.4,subject.getEngineUpshiftRPM());
    }

    @Test
    public void testGetEngineDownshiftRPM() {
	assertEquals(5.5,subject.getEngineDownshiftRPM());
    }

    @Test
    public void testGetEngineFrictionCF() {
	assertEquals(6.6,subject.getEngineFrictionCF());
    }

    @Test
    public void testGetEngineFuelConsumption() {
	assertEquals(7.7,subject.getEngineFuelConsumption());
    }

    @Test
    public void testGetDriveType() {
	assertEquals(5,subject.getDriveType());
    }

    @Test
    public void testGetFrontSuspensionType() {
	assertEquals(6,subject.getFrontSuspensionType());
    }

    @Test
    public void testGetRearSuspensionType() {
	assertEquals(7,subject.getRearSuspensionType());
    }

    @Test
    public void testGetFrontSuspensionSpringType() {
	assertEquals(8,subject.getFrontSuspensionSpringType());
    }

    @Test
    public void testGetRearSuspensionSpringType() {
	assertEquals(9,subject.getRearSuspensionSpringType());
    }

    @Test
    public void testGetEngineDisplacement() {
	assertEquals(3.3,subject.getEngineDisplacement());
    }

    @Test
    public void testGetDryWeight() {
	assertEquals(4.4,subject.getDryWeight());
    }

    @Test
    public void testGetIx() {
	assertEquals(5.5,subject.getIxyz().getX());
    }
    
    @Test
    public void testGetIy() {
	assertEquals(6.6,subject.getIxyz().getY());
    }
    
    @Test
    public void testGetIz() {
	assertEquals(7.7,subject.getIxyz().getZ());
    }

    @Test
    public void testGetRefAreaX() {
	assertEquals(8.8,subject.getRefArea().getX());
    }
    
    @Test
    public void testGetRefAreaY() {
	assertEquals(9.9,subject.getRefArea().getY());
    }
    
    @Test
    public void testGetRefAreaZ() {
	assertEquals(1.1,subject.getRefArea().getZ());
    }

    @Test
    public void testGetCgModifierX() {
	assertEquals(6.6,subject.getCgModifier().getX());
    }
    
    @Test
    public void testGetCgModifierY() {
	assertEquals(7.7,subject.getCgModifier().getY());
    }
    
    @Test
    public void testGetCgModifierZ() {
	assertEquals(8.8,subject.getCgModifier().getZ());
    }

    @Test
    public void testGetCl() {
	assertEquals(2.2,subject.getCl());
    }

    @Test
    public void testGetCd() {
	assertEquals(3.3,subject.getCd());
    }

    @Test
    public void testGetCy() {
	assertEquals(4.4,subject.getCy());
    }

    @Test
    public void testGetSteeringScalar() {
	assertEquals(9.9,subject.getSteeringScalar());
    }

    @Test
    public void testGetBrakeBalance() {
	assertEquals(1.1,subject.getBrakeBalance());
    }

    @Test
    public void testGetMaxBrakeForcePercent() {
	assertEquals(2.2,subject.getMaxBrakeForcePercent());
    }

    @Test
    public void testGetClp() {
	assertEquals(3.3,subject.getClp());
    }

    @Test
    public void testGetCmq() {
	assertEquals(4.4,subject.getCmq());
    }

    @Test
    public void testGetCnr() {
	assertEquals(5.5,subject.getCnr());
    }

    @Test
    public void testGetFuelWeight() {
	assertEquals(4.4,subject.getFuelWeight());
    }

    @Test
    public void testGetTeamRequirement() {
	assertNull(subject.getTeamRequirement());
    }

    @Test
    public void testGetRightFrontSpringArm() {
	assertEquals(2.2,subject.getRightFrontSpringArm());
    }

    @Test
    public void testGetLeftFrontSpringArm() {
	assertEquals(3.3,subject.getLeftFrontSpringArm());
    }

    @Test
    public void testGetRightRearSpringArm() {
	assertEquals(4.4,subject.getRightRearSpringArm());
    }

    @Test
    public void testGetLeftRearSpringArm() {
	assertEquals(5.5,subject.getLeftRearSpringArm());
    }

    @Test
    public void testGetSlipDifferentialTypeFrontAxle() {
	assertEquals(2,subject.getSlipDifferentialTypeFrontAxle());
    }

    @Test
    public void testGetSlipDifferentialTypeRearAxle() {
	assertEquals(3,subject.getSlipDifferentialTypeRearAxle());
    }

    @Test
    public void testGetMaxGear() {
	assertEquals(8,subject.getMaxGear());
    }

    @Test
    public void testGetDriverHeadPositionX() {
	assertEquals(2.2,subject.getDriverHeadPosition().getX());
    }
    
    @Test
    public void testGetDriverHeadPositionY() {
	assertEquals(3.3,subject.getDriverHeadPosition().getY());
    }
    
    @Test
    public void testGetDriverHeadPositionZ() {
	assertEquals(4.4,subject.getDriverHeadPosition().getZ());
    }

    @Test
    public void testGetWhipAntennaPositionX() {
	assertEquals(5.5,subject.getWhipAntennaPosition().getX());
    }
    
    @Test
    public void testGetWhipAntennaPositionY() {
	assertEquals(6.6,subject.getWhipAntennaPosition().getY());
    }
    
    @Test
    public void testGetWhipAntennaPositionZ() {
	assertEquals(7.7,subject.getWhipAntennaPosition().getZ());
    }

    @Test
    public void testGetScpt0X() {
	assertEquals(-1.1,subject.getScpt().get(0).getX());
    }
    
    @Test
    public void testGetScpt0Y() {
	assertEquals(2.2,subject.getScpt().get(0).getY());
    }
    
    @Test
    public void testGetScpt0Z() {
	assertEquals(-3.3,subject.getScpt().get(0).getZ());
    }

    @Test
    public void testGetColors() {
	assertNull(subject.getColors());
    }
    
    @Test
    public void testGetStockParts() {
	assertNotNull(subject.getStockParts());
    }
    
    @Test
    public void testGetStockPart0() {
	assertEquals("Item0",subject.getStockParts().get(0));
    }
    
    @Test
    public void testGetStockPart1() {
	assertEquals("Item1",subject.getStockParts().get(1));
    }

    @Test
    public void testGetNumColors() {
	assertEquals(2,subject.getNumColors());
    }

    @Test
    public void testGetNumStockParts() {
	assertEquals(2,subject.getNumStockParts());
    }

    @Test
    public void testGetSignaturePrefix() {
	assertEquals("01234567",subject.getSignaturePrefix());
    }

    @Test
    public void testGetSignatureSuffix() {
	assertEquals("89ABCDEF",subject.getSignatureSuffix());
    }

    @Test
    public void testGetVehicleColors() {
	assertNotNull(subject.getVehicleColors());
    }
    
    @Test
    public void testGetVehicleColor0Unknown0() {
	assertEquals(1.1,subject.getVehicleColors().get(0).getUnknown0());
    }
    
    @Test
    public void testGetVehicleColor0Unknown1() {
	assertEquals(2.2,subject.getVehicleColors().get(0).getUnknown1());
    }
    
    @Test
    public void testGetVehicleColor0Unknown2() {
	assertEquals(3.3,subject.getVehicleColors().get(0).getUnknown2());
    }
    
    ///
    
    @Test
    public void testGetVehicleColor0Unknown3() {
	assertEquals(1,subject.getVehicleColors().get(0).getUnknown3());
    }
    
    @Test
    public void testGetVehicleColor0Unknown4() {
	assertEquals(2,subject.getVehicleColors().get(0).getUnknown4());
    }
    
    @Test
    public void testGetVehicleColor0Unknown5() {
	assertEquals(3,subject.getVehicleColors().get(0).getUnknown5());
    }
    
    @Test
    public void testGetVehicleColor0UnknownString() {
	assertEquals("testString0",subject.getVehicleColors().get(0).getUnknownString());
    }

    @Test
    public void testGetVersion() {
	assertEquals(6,subject.getVersion());
    }

    @Override
    protected Type6and7TruckFile generateSubject(InputStream is)
	    throws Exception {
	return (Type6and7TruckFile)(new TRKFile(is).getTrkData());
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/trk/jfdt/version6Test.TRK";
    }

}//end TRKFileVersion6Test
