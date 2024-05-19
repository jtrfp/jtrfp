package org.jtrfp.jtrfp.lte.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class LTEFileTest extends AbstractParserTest<LTEFile> {

    @Test
    public void testGetLteData() {
	assertNotNull(subject.getLteData());
    }

    @Test
    public void testGradientIndex() {
	assertEquals(64,subject.getGradientIndex(5, 4));
    }
    
    @Test
    public void testGradientIndexZero() {
	assertEquals(0,subject.getGradientIndex(5, 5));
    }
    
    @Test //Can't use `expected =` since we're extending a TestCase
    public void testGradientIndexBadGradCoord(){
	try {assertEquals(0,subject.getGradientIndex(5, 16));}
	catch(IllegalArgumentException e) {return;}
	fail();
    }
    
    @Test //Can't use `expected =` since we're extending a TestCase
    public void testGradientIndexBadIndexCoord(){
	try{assertEquals(0,subject.getGradientIndex(256, 0));}
	catch(IllegalArgumentException e) {return;}
	fail();
    }

    @Override
    protected LTEFile generateSubject(InputStream is) throws Exception {
	return new LTEFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/lte/jfdt/test.LTE";
    }

}//end LTEFileTest
