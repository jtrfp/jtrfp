package org.jtrfp.jtrfp.pod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.junit.Test;

import junit.framework.TestCase;

public class IPODDataType1Test extends TestCase {
    IPodData subject;
    
    protected void setUp() throws Exception {
	super.setUp();
	//Load the test file
	InputStream is = null;
	try{is = getClass().getResourceAsStream(getResourceString());
	 assertNotNull(is);
	 subject = generateSubject(is);
	 assertNotNull(subject);}
	finally{if(is!=null)is.close();}
    }//end setUp()
    
    protected void tearDown() throws Exception {
	/*Supposedly some testing suites keep a test object instantiated, 
	 * meaning this stale ref could hang around for a while if not nulled.
	 */
	super.tearDown();
	subject=null;
    }//end tearDown

    @Test
    public void testSubject() {
	assertNotNull(subject);
    }
    
    @Test
    public void testComment() {
	assertEquals("This is a test file.",subject.getComment());
    }
    
    @Test
    public void testEntryCount() {
	assertEquals(2,subject.getEntryCount());
    }
    
    @Test
    public void testFirstPath() {
	assertEquals("TEST\\TEST0.TST",subject.getEntries()[0].getPath());
    }
    
    @Test
    public void testSecondPath() {
	assertEquals("TEST\\TEST1.TST",subject.getEntries()[1].getPath());
    }
    
    @Test
    public void testFirstData() throws Exception {
	final InputStream is = subject.getEntries()[0].getInputStreamFromPod();
	byte [] bytes = is.readAllBytes();
	
	assertEquals("TEST",new String(bytes));
    }
    
    @Test
    public void testSecondData() throws Exception {
	final InputStream is = subject.getEntries()[1].getInputStreamFromPod();
	byte [] bytes = is.readAllBytes();
	
	assertEquals("test",new String(bytes));
    }
    
    protected IPodData generateSubject(InputStream is) throws Exception {
	final File tmp = File.createTempFile("testPodFile", ".POD");
	final FileOutputStream os = new FileOutputStream(tmp);
	while(is.available() != 0)
	    os.write(is.read());
	os.close();
	is.close();
	return new PodFile(tmp).getData();
    }

    
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/pod/testType1.POD";
    }

}//end IPODDataType1Test
