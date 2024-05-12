package org.jtrfp.jtrfp.tex.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class ClassicTEXFileTest extends AbstractParserTest<TEXFile> {

    @Override
    protected TEXFile generateSubject(InputStream is) throws Exception {
	return new TEXFile(is);
    }
    
    @Test
    public void testSubject() {
	assertNotNull(subject);
    }
    
    @Test
    public void testTextureCount() {
	assertEquals(2, subject.getTextureCount());
    }
    
    @Test
    public void testFirst() {
	assertEquals("First.raw", subject.getTextureEntries().get(0).getRawFileName());
    }
    
    @Test
    public void testSecond() {
	assertEquals("Second.raw", subject.getTextureEntries().get(1).getRawFileName());
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/tex/jfdt/testClassic.TEX";
    }
    
}//end ClassicTEXFileTest
