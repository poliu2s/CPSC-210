package ubc.cs.cpsc210.sustainabilityapp.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ubc.cs.cpsc210.sustainabilityapp.model.Feature;
import ubc.cs.cpsc210.sustainabilityapp.model.KeyValueStore;
import ubc.cs.cpsc210.sustainabilityapp.model.MemoryKeyValueStore;
import ubc.cs.cpsc210.sustainabilityapp.model.POIRegistry;
import ubc.cs.cpsc210.sustainabilityapp.model.PointOfInterest;
import ubc.cs.cpsc210.sustainabilityapp.model.TourState;

/** 
 * Test the TourState class
 * 
 * @author CPSC 210 Instructor
 */
public class TourStateTest {
	
	// Fields needed for test
	private TourState tourState;
	private KeyValueStore store;
	private POIRegistry registry;
	
	@Before
	public void setUp() {
		store = new MemoryKeyValueStore();
		registry = POIRegistry.getDefault();
		tourState = new TourState(registry, store);
	}
	
	/**
	 * Test setting a single point that will not enable a feature because the feature
	 * exists for multiple points.
	 */
	@Test
	public void testSetSelectedPOIsOnePOINoFeature() {
		// Form input
		PointOfInterest lawPOI = registry.lookupPoint("Law");
		List<PointOfInterest> listWithOnePOI = new ArrayList<PointOfInterest>();
		listWithOnePOI.add(lawPOI);
		// Operate on tour state
		tourState.setSelectedPOIs(listWithOnePOI);
		
		// Check output
		// Check only law is selected
		List<PointOfInterest> selectedPOI = tourState.getSelectedPOIs();
		assertEquals(1,selectedPOI.size());
		// No feature should be selected
		List<Feature> featuresSelected = tourState.getSelectedFeatures();
		assertEquals(0,featuresSelected.size());
	}
	
	/**
	 * Test selecting a single point that enables one feature.
	 */
	@Test
	public void testSetSelectedPOIsWithOnePOIFeature() {
		// Form input
		PointOfInterest BRDCPOI = registry.lookupPoint("BRDC");
		List<PointOfInterest> listWithOnePOI = new ArrayList<PointOfInterest>();
		listWithOnePOI.add(BRDCPOI);
		// Operate on tourState
		tourState.setSelectedPOIs(listWithOnePOI);
		// One feature should be selected
		List<Feature> featuresSelected = tourState.getSelectedFeatures();
		assertEquals(1,featuresSelected.size());
		assertTrue(featuresSelected.contains(Feature.BIOFUEL));
	}
	
	/**
	 * Test selecting multiple points that enables four features, one of which
	 * crosscuts multiple points
	 */
	@Test
	public void testSetSelectedPOIsMultiplePOIsForAllOfSomeFeatures() {
		// Form input
		PointOfInterest LSCPOI = registry.lookupPoint("LSC");
		PointOfInterest LawPOI = registry.lookupPoint("Law");
		PointOfInterest CIRSPOI = registry.lookupPoint("CIRS");
		List<PointOfInterest> listOfPOI = new ArrayList<PointOfInterest>();
		listOfPOI.add(LSCPOI);
		listOfPOI.add(LawPOI);
		listOfPOI.add(CIRSPOI);
		// Operate on tour
		tourState.setSelectedPOIs(listOfPOI);
		// Four feature should be selected
		List<Feature> featuresSelected = tourState.getSelectedFeatures();
		assertEquals(4,featuresSelected.size());
		assertTrue(featuresSelected.contains(Feature.LEED_CERTIFICATION));
		assertTrue(featuresSelected.contains(Feature.SOLAR_ENERGY));
		assertTrue(featuresSelected.contains(Feature.RAINWATER_RECOVERY));
		assertTrue(featuresSelected.contains(Feature.GEOTHERMAL));
	}
	
	/**
	 * Select multiple POIs that doesn't enable any feature.
	 */
	@Test
	public void testSetSelectedPOIsMultiplePOIsForPartialFeature() {
		// Form input
		PointOfInterest lawPOI = registry.lookupPoint("Law");
		PointOfInterest LSCPOI = registry.lookupPoint("LSC");
		List<PointOfInterest> listPOI = new ArrayList<PointOfInterest>();
		listPOI.add(lawPOI);
		listPOI.add(LSCPOI);
		// Operate on tourState
		tourState.setSelectedPOIs(listPOI);
		// No feature should be selected
		List<Feature> featuresSelected = tourState.getSelectedFeatures();
		assertEquals(0,featuresSelected.size());		
	}
	
	/**
	 * Test selecting features that enables one point
	 */
	@Test
	public void testSetSelectedFeaturesOneFeatureOnePOI() {
		// Form input
		List<Feature> featureList = new ArrayList<Feature>();
		featureList.add(Feature.BIOFUEL);
		// Operate on tourState
		tourState.setSelectedFeatures(featureList);
		// Check result
		List<PointOfInterest> selectedPOIs = tourState.getSelectedPOIs();
		assertEquals(1, selectedPOIs.size());
	
		assertTrue(selectedPOIs.contains(registry.lookupPoint("BRDC")));
	}
	
	/**
	 * Test selecting features that is listed with multiple points
	 */
	@Test
	public void testSetSelectedFeaturesOneFeatureMultiplePOI() {
		// Form input
		List<Feature> featureList = new ArrayList<Feature>();
		featureList.add(Feature.LEED_CERTIFICATION);
		// Operate on tour
		tourState.setSelectedFeatures(featureList);
		// Check result
		List<PointOfInterest> selectedPOIs = tourState.getSelectedPOIs();
		assertEquals(3, selectedPOIs.size());
		assertTrue(selectedPOIs.contains(registry.lookupPoint("Law")));
		assertTrue(selectedPOIs.contains(registry.lookupPoint("CIRS")));
		assertTrue(selectedPOIs.contains(registry.lookupPoint("LSC")));
		
	}

}
