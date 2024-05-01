/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/

package org.jtrfp.jtrfp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanMap;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * This file is part of jTRFP.
 * Abstract parser test to provide automated loading and deep-javaBean-level read-write-read-compare testing of parsers.
 * @author Chuck Ritola
 *
 * @param <T>	The type of the parsed object being tested. i.e. IPodData or RAWFile.
 */

public abstract class AbstractParserTest<T extends ThirdPartyParseable> extends TestCase {
    protected T subject;
    
    /**
     * Returns a new "Test Subject" for the implementing Test Case. Each returned subject must be a fresh, non-cached Object.
     * @param is
     * @return	New test subject of the parameterized type.
     * @throws Exception
     * @since Apr 11, 2024
     */
    protected abstract T generateSubject(InputStream is) throws Exception;
    /**
     * Returns the resource string containing the test reference file.
     * @return	String of the resource path for reading the test reference file in this TestCase.
     * @since Apr 11, 2024
     */
    protected abstract String getResourceString();
    
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
    
    /**
     * Reads the reference file through the Parser being tested to a Bean, then
     * uses a new Parser to write said Bean to a file, and read it back again to a second Bean.
     * Then the two Beans are compared for discrepancies. Passing will demonstrate that the read and write
     * at least do not omit or distort each others' data. It is tempting to compare the output file to the 
     * reference file instead byte-for-byte, except that the original TRI engines aren't actually that strict.
     * @throws Exception
     * @since Apr 11, 2024
     */
    
    @Test
    public void testReadWrite() throws Exception {
	try {
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    assertNotNull(os);
	    new Parser().writeBean(subject, os);
	    final byte [] tmp = os.toByteArray();
	    final T newAsset = generateSubject(new ByteArrayInputStream(tmp));
	    final BeanMap subjectMap = new BeanMap(subject);
	    final BeanMap newMap = new BeanMap(newAsset);
	    compareBeanRecursive(subjectMap, newMap);
	} catch(Exception e) {
	    printLineByLineComparison();
	    throw e;
	}
    }//end testReadWrite()

    /**
     * Deep comparison of two Beans, one called the "reference" and one called the "subject."
     * The subject (i.e. "Test Subject") is typically the item being tested for inaccuracies while the reference acts
     * as the standard by which the subject is analyzed.
     * @param referenceMap
     * @param subjectMap
     * @since Apr 11, 2024
     */
    
    private void compareBeanRecursive(BeanMap referenceMap, BeanMap subjectMap) {
	for(Entry<Object,Object> ent : referenceMap.entrySet()) {
	    final Object refVal = ent.getValue();
	    final String key = (String)ent.getKey();
	    final Object subjectVal = subjectMap.get(key);
	    //System.out.println("key="+key+" refVal="+lVal+" testVal="+rVal);
	    if(refVal instanceof String || refVal instanceof Number || refVal instanceof Class || refVal instanceof Comparable) {
		assertEquals("Property: "+key,refVal,subjectMap.get(key));
	    } else if(refVal instanceof List) {
		final List<?> refList = (List<?>)refVal;
		final List<?> subjList = (List<?>)subjectVal;
		for(int i = 0 ; i < refList.size(); i++)
		    compareBeanRecursive(new BeanMap(refList.get(i)), new BeanMap(subjList.get(i)));
	    } else
		compareBeanRecursive(new BeanMap(refVal), new BeanMap(subjectVal));
	}//end for(entries)
    }//end compareBeanRecursive(...)
    
    /**
     * Prints a line-by-line comparison of the test reference file vs the file generated by a parser.
     * This is used to manually identify discrepancies introduced by a malfunctioning parser.
     * @throws Exception
     * @since Apr 11, 2024
     */
    
    protected void printLineByLineComparison() throws Exception {
	InputStream is = null;
	try{is = getClass().getResourceAsStream(getResourceString());
	 assertNotNull(is);
	 final BufferedReader orig = new BufferedReader(new InputStreamReader(is));

	 final PipedInputStream pis = new PipedInputStream();
	 final PipedOutputStream os = new PipedOutputStream(pis);
	 final BufferedReader saved = new BufferedReader(new InputStreamReader(pis));
	 assertNotNull(os);
	 new Parser().writeBean(subject, os);
	 
	 String oString, sString = "";
	 do {
	     oString = orig.readLine();
	     sString = saved.readLine();
	     System.out.println("ORIG:\t"+oString);
	     System.out.println("PROC:\t"+sString);
	     System.out.println();
	     
	 } while (oString != null || sString != null);
	 assertNotNull(subject);}
	finally{if(is!=null)is.close();}
    }//end printLineByLineComparison()
}//end AbstractParserTest
