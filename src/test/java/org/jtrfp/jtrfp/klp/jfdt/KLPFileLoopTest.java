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
import org.jtrfp.jtrfp.klp.jfdt.KLPFile.KLPElement.Loop;
import org.junit.Test;

public class KLPFileLoopTest extends AbstractParserTest<KLPFile> {

    @Test
    public void testDelegate() {
	assertNotNull(subject.getDelegate());
    }
    
    @Test
    public void testDelegateType() {
	assertTrue(subject.getDelegate() instanceof Loop);
    }
    
    @Test
    public void testStart() {
	Loop obj = (Loop)(subject.getDelegate());
	assertEquals(2,obj.getStartIndex());
    }
    
    @Test
    public void testEnd() {
	Loop obj = (Loop)(subject.getDelegate());
	assertEquals(3,obj.getEndIndex());
    }

    @Override
    protected KLPFile generateSubject(InputStream is) throws Exception {
	return new KLPFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/klp/jfdt/testLoop.KLP";
    }

}//end KLPFileLoopTest
