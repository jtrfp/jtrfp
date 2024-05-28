package org.jtrfp.jtrfp.trn.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class TRNFileTest extends AbstractParserTest<TRNFile> {

    @Test
    public void testGetTrackEntries() {
	assertNotNull(subject.getTrackEntries());
    }

    @Test
    public void testGetNumberOfTracks() {
	assertEquals(2, subject.getNumberOfTracks());
    }

    @Test
    public void testGetTournamentName() {
	assertEquals("tournamentName",subject.getTournamentName());
    }

    @Test
    public void testGetTournamentDescriptionFile() {
	assertEquals("tournamentDescription.file", subject.getTournamentDescriptionFile());
    }

    @Test
    public void testIsHasDragRaceFlag() {
	assertEquals(true,subject.isHasDragRaceFlag());
    }
    
    //// TRACK 0
    @Test
    public void testTrack0SitFile() {
	assertEquals("sitFile0.SIT",subject.getTrackEntries().get(0).getSitFileName());
    }
    
    @Test
    public void testTrack0Unknown0() {
	assertEquals(1,subject.getTrackEntries().get(0).getUnknown0());
    }
    
    //// TRACK 1
    @Test
    public void testTrack1SitFile() {
	assertEquals("sitFile1.SIT",subject.getTrackEntries().get(1).getSitFileName());
    }
    
    @Test
    public void testTrack1Unknown0() {
	assertEquals(2,subject.getTrackEntries().get(1).getUnknown0());
    }
    

    @Override
    protected TRNFile generateSubject(InputStream is) throws Exception {
	return new TRNFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/trn/jfdt/test.TRN";
    }

}//end TRNFileTest
