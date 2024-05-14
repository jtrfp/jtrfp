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
package org.jtrfp.jtrfp.nav.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.jfdt.Location3D;
import org.jtrfp.jtrfp.nav.jfdt.HellbenderNAVFile.START;
import org.junit.Test;

public class NAVFileHellbenderTest extends AbstractParserTest<HellbenderNAVFile> {
    
    @Test
    public void testGetNumNAVPoints() {
	assertEquals(2,subject.getNumNavigationPoints());
    }
    
    @Test
    public void testGetDelegate() {
	assertTrue(subject instanceof HellbenderNAVFile);
    }
    
    @Test
    public void testGetNAVObjects() {
	assertNotNull(subject.getNavObjects());
    }
    
    @Test
    public void testFirstNAVIsStartingPoint() {
	assertTrue(subject.getNavObjects().get(0) instanceof NAVData.StartingPoint);
    }
    
    @Test
    public void testFirstNAVLocX() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	final Location3D loc = sp.getLocationOnMap();
	assertEquals(1111,loc.getX());
    }
    
    @Test
    public void testFirstNAVLocY() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	final Location3D loc = sp.getLocationOnMap();
	assertEquals(2222,loc.getY());
    }
    
    @Test
    public void testFirstNAVLocZ() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	final Location3D loc = sp.getLocationOnMap();
	assertEquals(3333,loc.getZ());
    }
    
    @Test
    public void testFirstNAVPriority() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals(4,sp.getPriority());
    }
    
    @Test
    public void testFirstNAVTime() {
	START sp = (START)(subject.getNavObjects().get(0));
	assertEquals(5,sp.getTime());
    }
    
    @Test
    public void testFirstNAVCompletionSoundFile() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals("completionSound.wav",sp.getCompletionSoundFile());
    }
    
    @Test
    public void testFirstNAVCompletionText() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals("Completion text",sp.getCompletionText());
    }
    
    @Test
    public void testFirstNAVProximitySoundFile() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals("proximitySoundFile.wav",sp.getProximitySoundFile());
    }
    
    @Test
    public void testFirstNAVDescription() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals("NAV Description",sp.getDescription());
    }
    
    @Test
    public void testFirstNAVPitch() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals(11,sp.getPitch());
    }
    
    @Test
    public void testFirstNAVRoll() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals(22,sp.getRoll());
    }
    
    @Test
    public void testFirstNAVYaw() {
	NAVData.StartingPoint sp = (NAVData.StartingPoint)(subject.getNavObjects().get(0));
	assertEquals(33,sp.getYaw());
    }
    
    @Override
    protected HellbenderNAVFile generateSubject(InputStream is) throws Exception {
	return (HellbenderNAVFile)(new NAVFile(is).getDelegate());
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/nav/jfdt/testHellbender.NAV";
    }
    
}//end NAVFileHellbenderTest
