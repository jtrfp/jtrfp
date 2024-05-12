package org.jtrfp.jtrfp.tex.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class Version1TEXFileTest extends AbstractParserTest<TEXFile> {

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
    public void testShadowCount() {
	assertEquals(3, subject.getShadowTextureCount());
    }
    
    @Test
    public void testFirstTexture() {
	assertEquals("First.RAW", subject.getTextureEntries().get(0).getRawFileName());
    }
    
    @Test
    public void testFirstTextureUnknown0() {
	assertEquals(1, subject.getTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testFirstTextureUnknown1() {
	assertEquals(2, subject.getTextureEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testSecondTexture() {
	assertEquals("Second.RAW", subject.getTextureEntries().get(1).getRawFileName());
    }
    
    @Test
    public void testFirstShadow() {
	assertEquals("Third.RAW", subject.getShadowTextureEntries().get(0).getRawFileName());
    }
    
    @Test
    public void testSecondShadow() {
	assertEquals("Fourth.RAW", subject.getShadowTextureEntries().get(1).getRawFileName());
    }
    
    @Test
    public void testFirstShadowTextureUnknown0() {
	assertEquals(5, subject.getShadowTextureEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testFirstShadowTextureUnknown1() {
	assertEquals(6, subject.getShadowTextureEntries().get(0).getUnknown1());
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/tex/jfdt/testVersion1.TEX";
    }
    
}//end Version1TEXFileTest
