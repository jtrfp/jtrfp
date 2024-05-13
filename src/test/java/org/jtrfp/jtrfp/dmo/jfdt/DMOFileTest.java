package org.jtrfp.jtrfp.dmo.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.dmo.jfdt.DMOFile.PositionalDMOEntry;
import org.junit.Test;

public class DMOFileTest extends AbstractParserTest<DMOFile> {

    @Test
    public void testNumEntries() {
	assertEquals(2,subject.getNumEntries());
    }
    
    @Test
    public void testLVLFile() {
	assertEquals("test.lvl",subject.getLvlFile());
    }
    
    @Test
    public void testEntriesNotNull() {
	assertNotNull(subject.getDmoEntries());
    }
    
    @Test
    public void testFirstEntryType() {
	assertEquals(0,subject.getDmoEntries().get(0).getEntryType());
    }
    
    @Test
    public void testFirstEntryTimeOffset() {
	assertEquals(1,subject.getDmoEntries().get(0).getTimeOffset());
    }
    
    @Test
    public void testFirstEntryClass() {
	assertEquals(PositionalDMOEntry.class,subject.getDmoEntries().get(0).getClass());
    }
    
    @Test
    public void testFirstEntryX() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(2,ent.getX());
    }
    
    @Test
    public void testFirstEntryY() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(3,ent.getY());
    }
    
    @Test
    public void testFirstEntryZ() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(4,ent.getZ());
    }
    
    @Test
    public void testFirstEntryPitch() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(5,ent.getPitch());
    }
    
    @Test
    public void testFirstEntryRoll() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(6,ent.getRoll());
    }
    
    @Test
    public void testFirstEntryYaw() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(7,ent.getYaw());
    }
    
    @Test
    public void testFirstEntryUnknown0() {
	final PositionalDMOEntry ent = (PositionalDMOEntry)subject.getDmoEntries().get(0);
	assertEquals(8,ent.getUnknown0());
    }

    @Override
    protected DMOFile generateSubject(InputStream is) throws Exception {
	return new DMOFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/dmo/jfdt/test.DMO";
    }

}//end DMOFileTest
