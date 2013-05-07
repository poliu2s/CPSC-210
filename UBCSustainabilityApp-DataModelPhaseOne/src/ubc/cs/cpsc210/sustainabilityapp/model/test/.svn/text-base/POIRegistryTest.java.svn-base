package ubc.cs.cpsc210.sustainabilityapp.model.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ubc.cs.cpsc210.sustainabilityapp.model.Feature;
import ubc.cs.cpsc210.sustainabilityapp.model.POIRegistry;
import ubc.cs.cpsc210.sustainabilityapp.model.PointOfInterest;
import ubc.cs.cpsc210.sustainabilityapp.model.Feature;

/**
 * Test the POIRegistry 
 * 
 * @author CPSC 210 Instructor
 *
 */
public class POIRegistryTest {
	
	// The registry to test
	private POIRegistry registry;
	
	@Before
	public void setUp() {
		registry = POIRegistry.getDefault();
	}
	
	/**
	 * Test looking up a specific point of interest, namely Law. You could add other specific
	 * tests for completeness...
	 */
	@Test
	public void testLookupPointLaw() {
		// Form input
		PointOfInterest poi = registry.lookupPoint("Law");
		assertEquals(poi.getDisplayName(),"Law Building");
		
		// Operate on registry
		List<Feature> poiFeatures = poi.getFeatures();
		
		// Check result
		assertEquals(2, poiFeatures.size());
		assertTrue(poiFeatures.contains(Feature.LEED_CERTIFICATION));
		assertTrue(poiFeatures.contains(Feature.WASTEWATER_TREATMENT));
	}
	
	/**
	 * Test the return of all points of interest in the registry alphabetically.
	 */
	@Test
	public void testGetPointsAlphabetical() {
		// Operate on registry
		List<PointOfInterest> points = registry.getPointsAlphabetical();
		
		// Check output
		assertEquals(7, points.size());
		assertEquals("BRDC", points.get(0).getId());
		// Sort is by display name with capitals sorted before lower case
		assertEquals("CIRS", points.get(1).getId());
		assertEquals("CK_CHOI", points.get(2).getId());
		assertEquals("CHEM", points.get(3).getId());
		assertEquals("Law", points.get(4).getId());
		assertEquals("LSC", points.get(5).getId());
		assertEquals("UBC_FARM", points.get(6).getId());
	}
	
	/**
	 * Test the return of all points of interest with a certain feature.
	 */
	@Test
	public void testGetPointsWithFeatureLEED() {
		List<PointOfInterest> points = registry.getPointsWithFeature(Feature.LEED_CERTIFICATION);
		assertEquals(3, points.size());
		for (PointOfInterest poi: points) {
			List<Feature> features = poi.getFeatures();
			assertTrue(features.contains(Feature.LEED_CERTIFICATION));
		}
	}
	
	/**
	 * Test the return of points in walking tour order
	 */
	@Test
	public void testGetPointsByLocation() {
		List<PointOfInterest> points = registry.getPointsByLocation();
		assertEquals(7, points.size());
		assertEquals("Law", points.get(0).getId());
		assertEquals("CK_CHOI", points.get(1).getId());
		assertEquals("CHEM", points.get(2).getId());
		assertEquals("CIRS", points.get(3).getId());
		assertEquals("BRDC", points.get(4).getId());
		assertEquals("UBC_FARM", points.get(5).getId());
		assertEquals("LSC", points.get(6).getId());		
	}
	

}
