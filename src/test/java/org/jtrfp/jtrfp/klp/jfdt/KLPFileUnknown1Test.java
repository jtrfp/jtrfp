/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.klp.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.klp.jfdt.KLPFile.KLPElement.Unknown1;
import org.junit.Test;

public class KLPFileUnknown1Test extends AbstractParserTest<KLPFile> {

    @Test
    public void testDelegate() {
	assertNotNull(subject.getDelegate());
    }
    
    @Test
    public void testDelegateType() {
	assertTrue(""+subject.getDelegate().getClass().getSimpleName(),subject.getDelegate() instanceof Unknown1);
    }
    
    @Test
    public void testGetNumSampleRanges() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(2,l.getNumSampleRanges());
    }
    
    @Test
    public void testSampleRangeListSize() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(2,l.getSampleRanges().size());
    }
    
    @Test
    public void testFirstSampleRangeStart() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(3,l.getSampleRanges().get(0).getStartIndex());
    }
    
    @Test
    public void testFirstSampleRangeEnd() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(4,l.getSampleRanges().get(0).getEndIndex());
    }
    
    @Test
    public void testSecondSampleRangeStart() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(5,l.getSampleRanges().get(1).getStartIndex());
    }
    
    @Test
    public void testSecondSampleRangeEnd() {
	final Unknown1 l = (Unknown1)subject.getDelegate();
	assertEquals(6,l.getSampleRanges().get(1).getEndIndex());
    }

    @Override
    protected KLPFile generateSubject(InputStream is) throws Exception {
	return new KLPFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/klp/jfdt/testUnknown1.KLP";
    }

}//end KLPFileUnknown1Test
