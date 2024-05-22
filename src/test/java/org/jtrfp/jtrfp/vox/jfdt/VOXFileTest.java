package org.jtrfp.jtrfp.vox.jfdt;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class VOXFileTest extends AbstractParserTest<VOXFile> {

    @Test
    public void testGetNumEntries() {
	assertEquals(2,subject.getNumEntries());
    }

    @Test
    public void testGetMissionName() {
	assertEquals("Mission Name",subject.getMissionName());
    }

    @Test
    public void testGetLevels() {
	assertNotNull(subject.getLevels());
    }
    
    @Test
    public void testGetLevel0PlanetNumber() {
	assertEquals(0,subject.getLevels()[0].getPlanetNumber());
    }
    
    @Test
    public void testGetLevel0StageNumber() {
	assertEquals(1,subject.getLevels()[0].getStageNumber());
    }
    
    @Test
    public void testGetLevel0LVLFile() {
	assertNotNull("test0.LVL",subject.getLevels()[0].getLvlFile());
    }
    
    ////
    
    @Test
    public void testGetLevel1PlanetNumber() {
	assertEquals(2,subject.getLevels()[1].getPlanetNumber());
    }
    
    @Test
    public void testGetLevel1StageNumber() {
	assertEquals(3,subject.getLevels()[1].getStageNumber());
    }
    
    @Test
    public void testGetLevel1LVLFile() {
	assertNotNull("test1.LVL",subject.getLevels()[1].getLvlFile());
    }

    @Override
    protected VOXFile generateSubject(InputStream is) throws Exception {
	return new VOXFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/vox/jfdt/test.VOX";
    }

}
