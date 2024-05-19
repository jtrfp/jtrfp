package org.jtrfp.jtrfp.tnl.jfdt;

import java.io.InputStream;

import org.jtrfp.jtrfp.AbstractParserTest;
import org.jtrfp.jtrfp.tnl.jfdt.TNLFile.Segment.FlickerLightType;
import org.jtrfp.jtrfp.tnl.jfdt.TNLFile.Segment.Obstacle;
import org.junit.Test;

public class TNLFileTest extends AbstractParserTest<TNLFile> {

    @Test
    public void testTNLFile() {
	assertNotNull(subject);
    }

    @Test
    public void testGetNumSegments() {
	assertEquals(2,subject.getNumSegments());
    }

    @Test
    public void testGetSegments() {
	assertNotNull(subject.getSegments());
    }
    
    @Test
    public void testSegment0StartX() {
	assertEquals(111,subject.getSegments().get(0).getStartX());
    }
    
    @Test
    public void testSegment0StartY() {
	assertEquals(222,subject.getSegments().get(0).getStartY());
    }
    
    @Test
    public void testSegment0EndX() {
	assertEquals(333,subject.getSegments().get(0).getEndX());
    }
    
    @Test
    public void testSegment0EndY() {
	assertEquals(444,subject.getSegments().get(0).getEndY());
    }
    
    @Test
    public void testSegment0NumPolygons() {
	assertEquals(2,subject.getSegments().get(0).getNumPolygons());
    }
    
    @Test
    public void testSegment0StartAngle1() {
	assertEquals(666,subject.getSegments().get(0).getStartAngle1());
    }
    
    @Test
    public void testSegment0StartAngle2() {
	assertEquals(777,subject.getSegments().get(0).getStartAngle2());
    }
    
    @Test
    public void testSegment0RotationSpeed() {
	assertEquals(888,subject.getSegments().get(0).getRotationSpeed());
    }
    
    @Test
    public void testSegment0StartWidth() {
	assertEquals(999,subject.getSegments().get(0).getStartWidth());
    }
    
    @Test
    public void testSegment0StartHeight() {
	assertEquals(111,subject.getSegments().get(0).getStartHeight());
    }
    
    @Test
    public void testSegment0EndAngle1() {
	assertEquals(222,subject.getSegments().get(0).getEndAngle1());
    }
    
    @Test
    public void testSegment0EndAngle2() {
	assertEquals(333,subject.getSegments().get(0).getEndAngle2());
    }
    
    @Test
    public void testSegment0EndWidth() {
	assertEquals(444,subject.getSegments().get(0).getEndWidth());
    }
    
    @Test
    public void testSegment0EndHeight() {
	assertEquals(555,subject.getSegments().get(0).getEndHeight());
    }
    
    @Test
    public void testSegment0Unknown1() {
	assertEquals(666,subject.getSegments().get(0).getUnknown1());
    }
    
    @Test
    public void testSegment0LightPolygon() {
	assertEquals(7,subject.getSegments().get(0).getLightPolygon());
    }
    
    @Test
    public void testSegment0Cutout() {
	assertTrue(subject.getSegments().get(0).isCutout());
    }
    
    @Test
    public void testSegment0Obstacle() {
	assertEquals(Obstacle.closedDoor,subject.getSegments().get(0).getObstacle());
    }
    
    @Test
    public void testSegment0ObstacleTextureIndex() {
	assertEquals(3,subject.getSegments().get(0).getObstacleTextureIndex());
    }
    
    @Test
    public void testSegment0TextureIndices() {
	assertNotNull(subject.getSegments().get(0).getPolyTextureIndices());
    }
    
    @Test
    public void testSegment0TextureIndex0() {
	assertEquals((Integer)4,subject.getSegments().get(0).getPolyTextureIndices().get(0));
    }
    
    @Test
    public void testSegment0TextureIndex1() {
	assertEquals((Integer)5,subject.getSegments().get(0).getPolyTextureIndices().get(1));
    }
    
    @Test
    public void testSegment0FlickerLightType() {
	assertEquals(FlickerLightType.on1Sec,subject.getSegments().get(0).getFlickerLightType());
    }
    
    @Test
    public void testSegment0FlickerLightStrength() {
	assertEquals(2222,subject.getSegments().get(0).getFlickerLightStrength());
    }
    
    @Test
    public void testSegment0AmbientLight() {
	assertEquals(3333,subject.getSegments().get(0).getAmbientLight());
    }
    
    ////
    
    @Test
    public void testSegment1StartX() {
	assertEquals(-111,subject.getSegments().get(1).getStartX());
    }
    
    @Test
    public void testSegment1StartY() {
	assertEquals(-222,subject.getSegments().get(1).getStartY());
    }
    
    @Test
    public void testSegment1EndX() {
	assertEquals(-333,subject.getSegments().get(1).getEndX());
    }
    
    @Test
    public void testSegment1EndY() {
	assertEquals(-444,subject.getSegments().get(1).getEndY());
    }
    
    @Test
    public void testSegment1NumPolygons() {
	assertEquals(2,subject.getSegments().get(1).getNumPolygons());
    }
    
    @Test
    public void testSegment1StartAngle1() {
	assertEquals(-666,subject.getSegments().get(1).getStartAngle1());
    }
    
    @Test
    public void testSegment1StartAngle2() {
	assertEquals(-777,subject.getSegments().get(1).getStartAngle2());
    }
    
    @Test
    public void testSegment1RotationSpeed() {
	assertEquals(-888,subject.getSegments().get(1).getRotationSpeed());
    }
    
    @Test
    public void testSegment1StartWidth() {
	assertEquals(-999,subject.getSegments().get(1).getStartWidth());
    }
    
    @Test
    public void testSegment1StartHeight() {
	assertEquals(-111,subject.getSegments().get(1).getStartHeight());
    }
    
    @Test
    public void testSegment1EndAngle1() {
	assertEquals(-222,subject.getSegments().get(1).getEndAngle1());
    }
    
    @Test
    public void testSegment1EndAngle2() {
	assertEquals(-333,subject.getSegments().get(1).getEndAngle2());
    }
    
    @Test
    public void testSegment1EndWidth() {
	assertEquals(-444,subject.getSegments().get(1).getEndWidth());
    }
    
    @Test
    public void testSegment1EndHeight() {
	assertEquals(-555,subject.getSegments().get(1).getEndHeight());
    }
    
    @Test
    public void testSegment1Unknown1() {
	assertEquals(-666,subject.getSegments().get(1).getUnknown1());
    }
    
    @Test
    public void testSegment1LightPolygon() {
	assertEquals(-7,subject.getSegments().get(1).getLightPolygon());
    }
    
    @Test
    public void testSegment1Cutout() {
	assertTrue(subject.getSegments().get(1).isCutout());
    }
    
    @Test
    public void testSegment1Obstacle() {
	assertEquals(Obstacle.closedDoor,subject.getSegments().get(1).getObstacle());
    }
    
    @Test
    public void testSegment1ObstacleTextureIndex() {
	assertEquals(-3,subject.getSegments().get(1).getObstacleTextureIndex());
    }
    
    @Test
    public void testSegment1TextureIndices() {
	assertNotNull(subject.getSegments().get(1).getPolyTextureIndices());
    }
    
    @Test
    public void testSegment1TextureIndex0() {
	assertEquals((Integer)(-4),subject.getSegments().get(1).getPolyTextureIndices().get(0));
    }
    
    @Test
    public void testSegment1TextureIndex1() {
	assertEquals((Integer)(-5),subject.getSegments().get(1).getPolyTextureIndices().get(1));
    }
    
    @Test
    public void testSegment1FlickerLightType() {
	assertEquals(FlickerLightType.on1Sec,subject.getSegments().get(1).getFlickerLightType());
    }
    
    @Test
    public void testSegment1FlickerLightStrength() {
	assertEquals(-2222,subject.getSegments().get(1).getFlickerLightStrength());
    }
    
    @Test
    public void testSegment1AmbientLight() {
	assertEquals(-3333,subject.getSegments().get(1).getAmbientLight());
    }
    
    ////////////////
    
    @Override
    protected TNLFile generateSubject(InputStream is) throws Exception {
	return new TNLFile(is);
    }

    @Override
    protected String getResourceString() {
	return "/org/jtrfp/jtrfp/tnl/jfdt/test.TNL";
    }

}
