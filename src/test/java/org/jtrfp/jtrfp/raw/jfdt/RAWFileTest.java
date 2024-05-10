package org.jtrfp.jtrfp.raw.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class RAWFileTest extends AbstractParserTest<RAWFile> {

    @Test
    public void testLoad() {
	assertNotNull(subject);
    }

    @Test
    public void testGetSideLength() {
	assertEquals(256,subject.getSideLength());
    }
    
    @Test
    public void test255Pixel() {
	assertEquals(255,subject.getRawBytes()[4369]&0xFF);
    }
    
    @Test
    public void testTopLeftCorner() {
	assertEquals(2,subject.getRawBytes()[0]);
    }
    
    @Test
    public void testTopBorder() {
	assertEquals(1,subject.getRawBytes()[1]);
    }
    
    @Test
    public void testValueAt() {
	final byte [] bytes = subject.getRawBytes();
	for(int y = 0; y < 256; y++) {
	    for(int x = 0; x < 256; x++)
		assertEquals("x="+x+",y="+y,bytes[x+y*256]&0xFF,subject.valueAt(x, y));
	}
    }
    
    @Test
    public void testZeroPixel() {
	assertEquals(0,subject.getRawBytes()[256*1+1]);
    }
    

    @Override
    protected RAWFile generateSubject(InputStream is) throws Exception {
	return new RAWFile(is);
    }

    /**
     * test.RAW has a nonzero border with an "x" near 18,18
     */
    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/raw/jfdt/test.RAW";
    }

}//end RAWFileTest
