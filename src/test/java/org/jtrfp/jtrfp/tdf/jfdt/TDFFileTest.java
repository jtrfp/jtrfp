package org.jtrfp.jtrfp.tdf.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.tdf.jfdt.TDFFile.TunnelLogic;
import org.junit.Test;

public class TDFFileTest extends AbstractParserTest<TDFFile> {

    @Test
    public void testGetNumTunnels() {
	assertEquals(2,subject.getNumTunnels());
    }

    @Test
    public void testGetTunnels() {
	assertNotNull(subject.getTunnels());
    }
    
    ///
    @Test
    public void testGetTunnel0EntrancePosX() {
	assertEquals(111,subject.getTunnels()[0].getEntrance().getX());
    }
    
    @Test
    public void testGetTunnel0EntrancePosY() {
	assertEquals(222,subject.getTunnels()[0].getEntrance().getY());
    }
    
    @Test
    public void testGetTunnel0EntrancePosZ() {
	assertEquals(333,subject.getTunnels()[0].getEntrance().getZ());
    }
    ///
    @Test
    public void testGetTunnel0ExitPosX() {
	assertEquals(444,subject.getTunnels()[0].getExit().getX());
    }
    
    @Test
    public void testGetTunnel0ExitPosY() {
	assertEquals(555,subject.getTunnels()[0].getExit().getY());
    }
    
    @Test
    public void testGetTunnel0ExitPosZ() {
	assertEquals(666,subject.getTunnels()[0].getExit().getZ());
    }
    
    @Test
    public void testGetTunnel0EntranceLogic() {
	assertEquals(TunnelLogic.visible,subject.getTunnels()[0].getEntranceLogic());
    }
    
    @Test
    public void testGetTunnel0ExitLogic() {
	assertEquals(TunnelLogic.visibleUnlessBoss,subject.getTunnels()[0].getExitLogic());
    }
    
    @Test
    public void testGetTunnel0ExtranceTextureFile() {
	assertEquals("entrance0.RAW",subject.getTunnels()[0].getEntranceTerrainTextureFile());
    }
    
    @Test
    public void testGetTunnel0ExitTextureFile() {
	assertEquals("exit0.RAW",subject.getTunnels()[0].getExitTerrainTextureFile());
    }
    
    @Test
    public void testGetTunnel0LVLFile() {
	assertEquals("test0.LVL",subject.getTunnels()[0].getTunnelLVLFile());
    }
    
    ////
    
    ///
    @Test
    public void testGetTunnel1EntrancePosX() {
	assertEquals(777,subject.getTunnels()[1].getEntrance().getX());
    }
    
    @Test
    public void testGetTunnel1EntrancePosY() {
	assertEquals(888,subject.getTunnels()[1].getEntrance().getY());
    }
    
    @Test
    public void testGetTunnel1EntrancePosZ() {
	assertEquals(999,subject.getTunnels()[1].getEntrance().getZ());
    }
    ///
    @Test
    public void testGetTunnel1ExitPosX() {
	assertEquals(111,subject.getTunnels()[1].getExit().getX());
    }
    
    @Test
    public void testGetTunnel1ExitPosY() {
	assertEquals(222,subject.getTunnels()[1].getExit().getY());
    }
    
    @Test
    public void testGetTunnel1ExitPosZ() {
	assertEquals(333,subject.getTunnels()[1].getExit().getZ());
    }
    
    @Test
    public void testGetTunnel1EntranceLogic() {
	assertEquals(TunnelLogic.visibleUnlessBoss,subject.getTunnels()[1].getEntranceLogic());
    }
    
    @Test
    public void testGetTunnel1ExitLogic() {
	assertEquals(TunnelLogic.visible,subject.getTunnels()[1].getExitLogic());
    }
    
    @Test
    public void testGetTunnel1ExtranceTextureFile() {
	assertEquals("entrance1.RAW",subject.getTunnels()[1].getEntranceTerrainTextureFile());
    }
    
    @Test
    public void testGetTunnel1ExitTextureFile() {
	assertEquals("exit1.RAW",subject.getTunnels()[1].getExitTerrainTextureFile());
    }
    
    @Test
    public void testGetTunnel1LVLFile() {
	assertEquals("test1.LVL",subject.getTunnels()[1].getTunnelLVLFile());
    }
    
    ////

    @Override
    protected TDFFile generateSubject(InputStream is) throws Exception {
	return new TDFFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/tdf/jfdt/test.TDF";
    }

}//end TDFFileTest
