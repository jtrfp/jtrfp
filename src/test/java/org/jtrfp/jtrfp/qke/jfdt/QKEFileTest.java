package org.jtrfp.jtrfp.qke.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.junit.Test;

public class QKEFileTest extends AbstractParserTest<QKEFile> {

    //// GROUND QUAKES
    
    @Test
    public void testGetNumGroundQuakes() {
	assertEquals(2,subject.getNumGroundQuakes());
    }

    @Test
    public void testGetEntries() {
	assertNotNull(subject.getGroundQuakeEntries());
    }
    
    @Test
    public void testFirstGroundQuakeType() {
	assertEquals(0,subject.getGroundQuakeEntries()[0].getQkeType());
    }
    
    @Test
    public void testFirstGroundQuakeSectorX0() {
	assertEquals(1,subject.getGroundQuakeEntries()[0].getSectorX0());
    }
    
    @Test
    public void testFirstGroundQuakeSectorY0() {
	assertEquals(2,subject.getGroundQuakeEntries()[0].getSectorY0());
    }
    
    @Test
    public void testFirstGroundQuakeSectorX1() {
	assertEquals(3,subject.getGroundQuakeEntries()[0].getSectorX1());
    }
    
    @Test
    public void testFirstGroundQuakeSectorY1() {
	assertEquals(4,subject.getGroundQuakeEntries()[0].getSectorY1());
    }
    
    @Test
    public void testFirstGroundQuakeSectorX2() {
	assertEquals(5,subject.getGroundQuakeEntries()[0].getSectorX2());
    }
    
    @Test
    public void testFirstGroundQuakeSectorY2() {
	assertEquals(6,subject.getGroundQuakeEntries()[0].getSectorY2());
    }
    
    @Test
    public void testFirstGroundQuakeLayer() {
	assertEquals(7,subject.getGroundQuakeEntries()[0].getLayer());
    }
    //UNKNOWN
    @Test
    public void testFirstGroundQuakeUnknown0() {
	assertEquals(8,subject.getGroundQuakeEntries()[0].getUnknown0());
    }
    @Test
    public void testFirstGroundQuakeUnknown1() {
	assertEquals(9,subject.getGroundQuakeEntries()[0].getUnknown1());
    }
    @Test
    public void testFirstGroundQuakeUnknown2() {
	assertEquals(10,subject.getGroundQuakeEntries()[0].getUnknown2());
    }
    @Test
    public void testFirstGroundQuakeUnknown3() {
	assertEquals(11,subject.getGroundQuakeEntries()[0].getUnknown3());
    }
    
    //// BOX QUAKES
    
    @Test
    public void testGetNumBoxQuakes() {
	assertEquals(2,subject.getNumBoxQuakes());
    }

    @Test
    public void testGetBoxQuakeEntries() {
	assertNotNull(subject.getBoxQuakeEntries());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown0() {
	assertEquals(1,subject.getBoxQuakeEntries().get(0).getUnknown0());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown1() {
	assertEquals(2,subject.getBoxQuakeEntries().get(0).getUnknown1());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown2() {
	assertEquals(3,subject.getBoxQuakeEntries().get(0).getUnknown2());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown3() {
	assertEquals(4,subject.getBoxQuakeEntries().get(0).getUnknown3());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown4() {
	assertEquals(5,subject.getBoxQuakeEntries().get(0).getUnknown4());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown5() {
	assertEquals(6,subject.getBoxQuakeEntries().get(0).getUnknown5());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown6() {
	assertEquals(7,subject.getBoxQuakeEntries().get(0).getUnknown6());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown7() {
	assertEquals(8,subject.getBoxQuakeEntries().get(0).getUnknown7());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown8() {
	assertEquals(9,subject.getBoxQuakeEntries().get(0).getUnknown8());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown9() {
	assertEquals(10,subject.getBoxQuakeEntries().get(0).getUnknown9());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown10() {
	assertEquals(11,subject.getBoxQuakeEntries().get(0).getUnknown10());
    }
    ////
    @Test
    public void testGetBoxQuakeEntryUnknown11() {
	assertEquals(12,subject.getBoxQuakeEntries().get(0).getUnknown11());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown12() {
	assertEquals(13,subject.getBoxQuakeEntries().get(0).getUnknown12());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown13() {
	assertEquals(14,subject.getBoxQuakeEntries().get(0).getUnknown13());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown14() {
	assertEquals(15,subject.getBoxQuakeEntries().get(0).getUnknown14());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown15() {
	assertEquals(16,subject.getBoxQuakeEntries().get(0).getUnknown15());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown16() {
	assertEquals(17,subject.getBoxQuakeEntries().get(0).getUnknown16());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown17() {
	assertEquals(18,subject.getBoxQuakeEntries().get(0).getUnknown17());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown18() {
	assertEquals(19,subject.getBoxQuakeEntries().get(0).getUnknown18());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown19() {
	assertEquals(20,subject.getBoxQuakeEntries().get(0).getUnknown19());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown20() {
	assertEquals("test0.wav",subject.getBoxQuakeEntries().get(0).getUnknown20());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown21() {
	assertEquals("test1.wav",subject.getBoxQuakeEntries().get(0).getUnknown21());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown22() {
	assertEquals("test2.wav",subject.getBoxQuakeEntries().get(0).getUnknown22());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknown23() {
	assertEquals("test3.wav",subject.getBoxQuakeEntries().get(0).getUnknown23());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknownAddlQuakeInfo() {
	assertEquals(2,subject.getBoxQuakeEntries().get(0).getUnknownAddlQuakeInfo());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknownBoxQuakeSwitchInfo0() {
	assertEquals(1,subject.getBoxQuakeEntries().get(0).getUnknownBoxQuakeSwitchInfo0());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknownBoxQuakeSwitchInfo1() {
	assertEquals("test1.RAW",subject.getBoxQuakeEntries().get(0).getUnknownBoxQuakeSwitchInfo1());
    }
    
    @Test
    public void testGetBoxQuakeEntryUnknownBoxQuakeSwitchInfo2() {
	assertEquals("test0.RAW",subject.getBoxQuakeEntries().get(0).getUnknownBoxQuakeSwitchInfo2());
    }

    @Override
    protected QKEFile generateSubject(InputStream is) throws Exception {
	return new QKEFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/qke/jfdt/test.QKE";
    }

}//end QKEFileTest
