package org.jtrfp.jtrfp.glt.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class GLTFileTest extends AbstractParserTest<GLTFile> {

    @Test
    public void testNonNull() {
	assertNotNull(subject);
    }
    
    @Test
    public void testNumBlocks() {
	assertEquals(2,subject.getNumBlocks());
    }
    
    @Test
    public void testEntriesNotNull() {
	assertNotNull(subject.getGltEntries());
    }
    
    @Test
    public void testFirstEntryInt0() {
	assertEquals(111,subject.getGltEntries().get(0).getInt0());
    }
    
    @Test
    public void testFirstEntryInt1() {
	assertEquals(222,subject.getGltEntries().get(0).getInt1());
    }
    
    @Test
    public void testFirstEntryInt2() {
	assertEquals(333,subject.getGltEntries().get(0).getInt2());
    }
    
    @Test
    public void testFirstEntryInt3() {
	assertEquals(4,subject.getGltEntries().get(0).getInt3());
    }
    
    @Test
    public void testFirstEntryBoolean0() {
	assertTrue(subject.getGltEntries().get(0).isBoolean0());
    }
    
    @Test
    public void testFirstEntryInt4() {
	assertEquals(555,subject.getGltEntries().get(0).getInt4());
    }
    
    @Test
    public void testFirstEntryInt5() {
	assertEquals(6,subject.getGltEntries().get(0).getInt5());
    }
    
    @Test
    public void testFirstEntryInt6() {
	assertEquals(7,subject.getGltEntries().get(0).getInt6());
    }
    
    /// SECOND ENTRY
    
    @Test
    public void testSecondEntryInt0() {
	assertEquals(888,subject.getGltEntries().get(1).getInt0());
    }
    
    @Test
    public void testSecondEntryInt1() {
	assertEquals(999,subject.getGltEntries().get(1).getInt1());
    }
    
    @Test
    public void testSecondEntryInt2() {
	assertEquals(1111,subject.getGltEntries().get(1).getInt2());
    }
    
    @Test
    public void testSecondEntryInt3() {
	assertEquals(2,subject.getGltEntries().get(1).getInt3());
    }
    
    @Test
    public void testSecondEntryBoolean0() {
	assertTrue(subject.getGltEntries().get(1).isBoolean0());
    }
    
    @Test
    public void testSecondEntryInt4() {
	assertEquals(3333,subject.getGltEntries().get(1).getInt4());
    }
    
    @Test
    public void testSecondEntryInt5() {
	assertEquals(4444,subject.getGltEntries().get(1).getInt5());
    }
    
    @Test
    public void testSecondEntryInt6() {
	assertEquals(5555,subject.getGltEntries().get(1).getInt6());
    }
    
    // UTILITIES

    @Override
    protected GLTFile generateSubject(InputStream is) throws Exception {
	return new GLTFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/glt/jfdt/test.GLT";
    }

}//end GLTFileTest
