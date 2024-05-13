package org.jtrfp.jtrfp.def.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.def.jfdt.DEFFile.EnemyDefinition.EnemyLogic;
import org.jtrfp.jtrfp.def.jfdt.DEFFile.EnemyDefinition.FireSpread;
import org.jtrfp.jtrfp.jfdt.Location3D;
import org.jtrfp.jtrfp.jfdt.Powerup;
import org.jtrfp.jtrfp.jfdt.Weapon;
import org.junit.Assert;

public class DEFFileTVF3Test extends AbstractParserTest<DEFFile> {
    
    public void testGetNumEnemyDefinitions() {
	assertEquals(2,subject.getNumEnemyDefinitions());
    }
    
    public void testGetEnemyDefinitions() {
	assertNotNull(subject.getEnemyDefinitions());
    }
    
    public void testEnemyDefinitionQuantity() {
	assertEquals(2,subject.getEnemyDefinitions().size());
    }
    
    public void testFirstEnemyDefinitionLogic() {
	assertEquals(EnemyLogic.groundStatic, subject.getEnemyDefinitions().get(0).getLogic());
    }
    
    public void testFirstEnemyDefinitionUnknown1() {
	assertEquals(1, subject.getEnemyDefinitions().get(0).getUnknown1());
    }
    
    public void testFirstEnemyDefinitionBoundingBoxRadius() {
	assertEquals(2, subject.getEnemyDefinitions().get(0).getBoundingBoxRadius());
    }
    
    public void testFirstEnemyDefinitionPivotX() {
	assertEquals(3, subject.getEnemyDefinitions().get(0).getPivotX());
    }
    
    public void testFirstEnemyDefinitionPivotY() {
	assertEquals(4, subject.getEnemyDefinitions().get(0).getPivotY());
    }
    
    public void testFirstEnemyDefinitionPivotZ() {
	assertEquals(5, subject.getEnemyDefinitions().get(0).getPivotZ());
    }
    
    public void testFirstEnemyDefinitionComplexModelFile() {
	assertEquals("test.bin", subject.getEnemyDefinitions().get(0).getComplexModelFile());
    }
    
    public void testFirstEnemyDefinitionSimpleModel() {
	assertEquals("simpleTest.bin", subject.getEnemyDefinitions().get(0).getSimpleModel());
    }
    // LINE 2
    public void testFirstEnemyDefinitionThrustSpeed() {
	assertEquals(6, subject.getEnemyDefinitions().get(0).getThrustSpeed());
    }
    
    public void testFirstEnemyDefinitionRotationSpeed() {
	assertEquals(7, subject.getEnemyDefinitions().get(0).getRotationSpeed());
    }
    
    public void testFirstEnemyDefinitionFireSpeed() {
	assertEquals(8, subject.getEnemyDefinitions().get(0).getFireSpeed());
    }
    
    public void testFirstEnemyDefinitionFireStrength() {
	assertEquals(9, subject.getEnemyDefinitions().get(0).getFireStrength());
    }
    
    public void testFirstEnemyDefinitionWeapon() {
	assertEquals(Weapon.blueFireBall, subject.getEnemyDefinitions().get(0).getWeapon());
    }
    // LINE 3
    public void testFirstEnemyDefinitionShowOnBriefing() {
	assertEquals(true, subject.getEnemyDefinitions().get(0).isShowOnBriefing());
    }
    
    public void testFirstEnemyDefinitionCreateRandomly() {
	assertEquals(false, subject.getEnemyDefinitions().get(0).isCreateRandomly());
    }
    
    public void testFirstEnemyDefinitionPowerupProbability() {
	assertEquals(2, subject.getEnemyDefinitions().get(0).getPowerupProbability());
    }
    
    public void testFirstEnemyDefinitionPowerup() {
	assertEquals(Powerup.MAM, subject.getEnemyDefinitions().get(0).getPowerup());
    }
    //LINE 4
    public void testFirstEnemyDefinitionNumRandomFiringVertices() {
	assertEquals(8, subject.getEnemyDefinitions().get(0).getNumRandomFiringVertices());
    }
    
    public void testFirstEnemyDefinitionFiringVertices() {
	Assert.assertArrayEquals(new Integer[] {1,2,3,4,5,6,7,8}, subject.getEnemyDefinitions().get(0).getFiringVertices());
    }
    
    //NEWHIT
    public void testFirstEnemyDefinitionNumNewHitboxes() {
	assertEquals(16, subject.getEnemyDefinitions().get(0).getNumNewHBoxes());
    }
    
    public void testFirstEnemyDefinitionNewHBoxes() {
	Assert.assertArrayEquals(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}, subject.getEnemyDefinitions().get(0).getHboxVertices());
    }
    //!NewAtakRet
    public void testFirstEnemyDefinitionAttackDistance() {
	assertEquals(1, subject.getEnemyDefinitions().get(0).getAttackDistance());
    }
    
    public void testFirstEnemyDefinitionRetreatDistance() {
	assertEquals(2, subject.getEnemyDefinitions().get(0).getRetreatDistance());
    }
    
    public void testFirstEnemyDefinitionObjectIsBoss() {
	assertEquals(false, subject.getEnemyDefinitions().get(0).isObjectIsBoss());
    }
    
    public void testFirstEnemyDefinitionUnknown() {
	assertEquals(4, subject.getEnemyDefinitions().get(0).getUnknown());
    }
    
    public void testFirstEnemyDefinitionDescription() {
	assertEquals("Test Description", subject.getEnemyDefinitions().get(0).getDescription());
    }
    
    //#New2ndweapon
    public void testFirstEnemyDefinitionFireSpread() {
	assertEquals(FireSpread.horiz3, subject.getEnemyDefinitions().get(0).getFireSpread());
    }
    
    public void testFirstEnemyDefinitionSecondaryWeapon() {
	assertEquals(Weapon.purpleLaser, subject.getEnemyDefinitions().get(0).getSecondaryWeapon());
    }
    
    public void testFirstEnemyDefinitionSecondWeaponDistance() {
	assertEquals(3, subject.getEnemyDefinitions().get(0).getSecondWeaponDistance());
    }
    
    public void testFirstEnemyDefinitionFireVelocity() {
	assertEquals(2000000, subject.getEnemyDefinitions().get(0).getFireVelocity());
    }
    // XSFX
    
    public void testFirstEnemyDefinitionBossFireSFXFile() {
	assertEquals("testSFX1.wav", subject.getEnemyDefinitions().get(0).getBossFireSFXFile());
    }
    
    public void testFirstEnemyDefinitionBossYellSFXFile() {
	assertEquals("testSFX2.wav", subject.getEnemyDefinitions().get(0).getBossYellSFXFile());
    }
    
    //TODO: Hellbender stuff
    
    //Placements
    public void testGetNumPlacements() {
	assertEquals(2,subject.getNumPlacements());
    }
    
    public void testFirstPlacementDefIndex() {
	assertEquals(0,subject.getEnemyPlacements().get(0).getDefIndex());
    }
    
    public void testFirstPlacementStrength() {
	assertEquals(5,subject.getEnemyPlacements().get(0).getStrength());
    }
    
    public void testFirstPlacementLocationOnMap() {
	final Location3D loc = subject.getEnemyPlacements().get(0).getLocationOnMap();
	assertEquals(-6,loc.getX());
	assertEquals(7 ,loc.getY());
	assertEquals(-8,loc.getZ());
    }
    
    public void testFirstPlacementLocationPitch() {
	assertEquals(9,subject.getEnemyPlacements().get(0).getPitch());
    }
    
    public void testFirstPlacementLocationRoll() {
	assertEquals(-10,subject.getEnemyPlacements().get(0).getRoll());
    }
    
    public void testFirstPlacementLocationYaw() {
	assertEquals(11,subject.getEnemyPlacements().get(0).getYaw());
    }
    
    @Override
    protected DEFFile generateSubject(InputStream is) throws Exception {
	return new DEFFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/def/jfdt/testTVF3.DEF";
    }
}//end DEFFileTest
