package org.jtrfp.jtrfp.pup.ffdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.jfdt.Powerup;
import org.jtrfp.jtrfp.pup.jfdt.PUPFile;
import org.junit.Test;

public class PUPFileTest extends AbstractParserTest<PUPFile> {

    @Test
    public void testGetPowerupLocations() {
	assertNotNull(subject.getPowerupLocations());
    }

    @Test
    public void testGetNumPowerups() {
	assertEquals(2,subject.getNumPowerups());
    }
    
    @Test
    public void testGetPowerup0X() {
	assertEquals(111,subject.getPowerupLocations()[0].getX());
    }
    
    @Test
    public void testGetPowerup0Y() {
	assertEquals(222,subject.getPowerupLocations()[0].getY());
    }
    
    @Test
    public void testGetPowerup0Z() {
	assertEquals(333,subject.getPowerupLocations()[0].getZ());
    }
    
    @Test
    public void testGetPowerup0Type() {
	assertEquals(Powerup.RTL,subject.getPowerupLocations()[0].getType());
    }
    
    @Test
    public void testGetPowerup1X() {
	assertEquals(444,subject.getPowerupLocations()[1].getX());
    }
    
    @Test
    public void testGetPowerup1Y() {
	assertEquals(555,subject.getPowerupLocations()[1].getY());
    }
    
    @Test
    public void testGetPowerup1Z() {
	assertEquals(666,subject.getPowerupLocations()[1].getZ());
    }
    
    @Test
    public void testGetPowerup1Type() {
	assertEquals(Powerup.PAC,subject.getPowerupLocations()[1].getType());
    }

    @Override
    protected PUPFile generateSubject(InputStream is) throws Exception {
	return new PUPFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/pup/jfdt/test.PUP";
    }

}//end PUPFileTest
