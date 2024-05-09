package org.jtrfp.jtrfp.sit.jfdt;

import java.io.InputStream;

import org.junit.Test;

public class SITFileMTM2Test extends SITFileMTM1Test {
    
    @Test
    public void testAmbientSound() {
	assertEquals((Integer)1,subject.getAmbientSound());
    }
    
    @Test
    public void testTrackLength() {
	assertEquals(2.2,subject.getTrackLength());
    }
    
    @Test
    public void testWeatherMask() {
	assertEquals(3,subject.getWeatherMask());
    }
    
    @Test
    public void testFirstVehicleDamageCode() {
	assertEquals((Integer)4,subject.getVehicles().get(0).getDamageCode());
    }
    
    @Override
    protected SITFile generateSubject(InputStream is) throws Exception {
	return new SITFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/sit/jfdt/MTM2Test.SIT";
    }

}//end SITFileMTM2Test
