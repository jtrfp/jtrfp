package org.jtrfp.jtrfp.def.jfdt;

public class DEFFileHellbenderTest extends DEFFileTVF3Test {
    
    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/def/jfdt/testHellbender.DEF";
    }
    
    public void testFirstEnemyPathToFollow() {
	assertEquals(Integer.valueOf(5),subject.getEnemyDefinitions().get(0).getPathToFollow());
    }
    
    public void testFirstEnemyCannonDamage() {
	assertEquals(Integer.valueOf(4),subject.getEnemyDefinitions().get(0).getCannonDamage());
    }
    
    public void testFirstEnemyLaserDamage() {
	assertEquals(Integer.valueOf(5),subject.getEnemyDefinitions().get(0).getLaserDamage());
    }
    
    public void testFirstEnemyMissileDamage() {
	assertEquals(Integer.valueOf(6),subject.getEnemyDefinitions().get(0).getMissileDamage());
    }
    
    public void testFirstEnemyFriendly() {
	assertEquals(Boolean.TRUE,subject.getEnemyDefinitions().get(0).isFriendly());
    }
    
    public void testFirstEnemyEscapeSFX() {
	assertEquals("escapeTest.wav",subject.getEnemyDefinitions().get(0).getEscapeSFX());
    }
    
    public void testFirstEnemyDestroySFX() {
	assertEquals("destroyTest.wav",subject.getEnemyDefinitions().get(0).getDestroySFX());
    }
}//end DEFFileHellbenderTest
