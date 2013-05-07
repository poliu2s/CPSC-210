package ubc.cs.cpsc210.sustainabilityapp.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ubc.cs.cpsc210.sustainabilityapp.model.PointOfInterest;
import ubc.cs.cpsc210.sustainabilityapp.model.Feature;

/**
 * Test the PointOfInterest class
 * 
 * @author CPSC 210 Instructor
 */
public class PointOfInterestTest {
	
	private PointOfInterest poi;
	
	@Before
	public void setUp() {
		poi = new PointOfInterest("newPOI", "newPOIDisplayName");
	}
	
	/**
	 * Test we can store a feature list of size one.
	 */
	@Test
	public void testSetFeaturesOne() {
		// Form input
		List<Feature> featureList = new ArrayList<Feature>();
		featureList.add(Feature.LOW_IMPACT_MATERIALS);
		
		// Operate on point of interest
		poi.setFeatures(featureList);
		List<Feature> resultFeatureList = poi.getFeatures();
		
		// Correct number of features returned?
		assertEquals(1, resultFeatureList.size());
		// Correct feature returned?
		assertTrue(resultFeatureList.contains(Feature.LOW_IMPACT_MATERIALS));
	}
	
	/**
	 * Test we can store a longer feature list
	 */
	@Test
	public void testSetFeaturesMultiple() {
		// Form input
		List<Feature> featureList = new ArrayList<Feature>();
		featureList.add(Feature.LOW_IMPACT_MATERIALS);
		featureList.add(Feature.LEED_CERTIFICATION);
		
		// Operate on point of interest
		poi.setFeatures(featureList);
		
		// Check result
		List<Feature> resultFeatureList = poi.getFeatures();
		assertEquals(2, resultFeatureList.size());
		assertTrue(resultFeatureList.contains(Feature.LOW_IMPACT_MATERIALS));
		assertTrue(resultFeatureList.contains(Feature.LEED_CERTIFICATION));
	}

}
