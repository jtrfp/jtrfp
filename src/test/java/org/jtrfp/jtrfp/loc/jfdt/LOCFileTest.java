package org.jtrfp.jtrfp.loc.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.loc.jfdt.LOCFile.ENDEntry;
import org.junit.Test;

public class LOCFileTest extends AbstractParserTest<LOCFile> {

    @Test
    public void testGetUnknown0() {
	assertEquals(111,subject.getUnknown0());
    }

    @Test
    public void testGetLocEntries() {
	assertNotNull(subject.getLocEntries());
    }
    
    @Test
    public void testLocEntriesSize() {
	assertEquals(4,subject.getLocEntries().size());
    }
    
    @Test
    public void testFirstEntryNullComment() {
	assertNull(subject.getLocEntries().get(0).getComment());
    }
    
    @Test
    public void testSecondEntryComment() {
	assertEquals("Second entry comment.",subject.getLocEntries().get(1).getComment());
    }
    
    @Test
    public void testSecondEntryString() {
	assertEquals("Second test string",subject.getLocEntries().get(1).getString());
    }
    
    @Test
    public void testSecondEntryTag() {
	assertEquals("Second test tag:",subject.getLocEntries().get(1).getTag());
    }
    
    //THIRD
    @Test
    public void testThirdEntryComment() {
	assertEquals("Third entry comment",subject.getLocEntries().get(2).getComment());
    }
    
    @Test
    public void testThirdEntryString() {
	assertEquals("Third test string.",subject.getLocEntries().get(2).getString());
    }
    
    @Test
    public void testThirdEntryTag() {
	assertEquals("Third test tag.\r\n\r\nWith new lines.",subject.getLocEntries().get(2).getTag());
    }
    
    @Test
    public void testEndEntry() {
	assertEquals(ENDEntry.class,subject.getLocEntries().get(3).getClass());
    }

    @Override
    protected LOCFile generateSubject(InputStream is) throws Exception {
	return new LOCFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/loc/jfdt/test.LOC";
    }

}//end LOCFileTest
