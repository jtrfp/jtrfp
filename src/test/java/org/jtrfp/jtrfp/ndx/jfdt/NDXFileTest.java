package org.jtrfp.jtrfp.ndx.jfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class NDXFileTest {
    private NDXFile getSubject() {
	return new NDXFile().read(NDXFileTest.class.getResourceAsStream("/org/jtrfp/jtrfp/ndx/jfdt/test.NDX"));
    }

    @Test
    public void testRead() {
	getSubject();
    }

    @Test
    public void testReadWrite() throws Exception {
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	getSubject().write(baos);
	final String outString = new String(baos.toByteArray());
	final String inString = new String(NDXFileTest.class.getResourceAsStream("/org/jtrfp/jtrfp/ndx/jfdt/test.NDX").readAllBytes());
	
	Scanner inScanner = new Scanner(inString);
	Scanner outScanner = new Scanner(outString);
	
	inScanner.useDelimiter("\r\n");
	outScanner.useDelimiter("\r\n");
	
	int lineCount = 0;
	while(inScanner.hasNext()) {
	    final String in = inScanner.next();
	    final String out = outScanner.next();
	    assertEquals("Line "+lineCount,in,out);
	    lineCount++;
	}
	inScanner.close();
	outScanner.close();
	assertEquals(4,lineCount);
    }

    @Test
    public void testGetWidths() {
	assertNotNull(getSubject().getWidths());
    }
    
    @Test
    public void testGetWidth0() {
	assertEquals((Integer)0,getSubject().getWidths().get(0));
    }
    
    @Test
    public void testGetWidth3() {
	assertEquals((Integer)3,getSubject().getWidths().get(3));
    }

    @Test
    public void testAsciiWidth() {
	assertEquals(1,getSubject().asciiWidth((byte)'!'));
    }

    @Test
    public void testSetWidths() {
	final NDXFile subject = new NDXFile();
	subject.setWidths(List.of(0,1,2,3));
    }
    
    @Test
    public void testSetWidthsGetExclamation() {
	final NDXFile subject = new NDXFile();
	subject.setWidths(List.of(0,1,2,3));
	assertEquals(1,getSubject().asciiWidth((byte)'!'));
    }

}//end NDXFileTest
