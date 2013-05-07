package ubc.cs.cpsc210.sustainabilityapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ubc.cs.cpsc210.sustainabilityapp.model.KeyValueStore;
import ubc.cs.cpsc210.sustainabilityapp.model.POIRegistry;
import ubc.cs.cpsc210.sustainabilityapp.model.PointOfInterest;
import ubc.cs.cpsc210.sustainabilityapp.model.TourState;

public class OldTourStateTest {

	@Test
	public void testSetSelectedPOIs() {
		POIRegistry registry = POIRegistry.getDefault();
		
		TourState state = new TourState(POIRegistry.getDefault(), getSimpleKeyValueStore());
		
		// With no state, all points should be selected.
		Assert.assertEquals(state.getSelectedPOIs(), registry.getPointsByLocation());
		
		List<PointOfInterest> testPoints = new ArrayList<PointOfInterest>();
		testPoints.add(registry.lookupPoint("LAW"));
		testPoints.add(registry.lookupPoint("CK_CHOI"));
		
		state.setSelectedPOIs(testPoints);
		
		Assert.assertEquals(state.getSelectedPOIs(), testPoints);
	}
	
	private KeyValueStore getSimpleKeyValueStore() {
		return new KeyValueStore() {
			private Map<String, Object> map = new HashMap<String, Object>();
			
			@Override
			public void putStringList(String key, List<String> value) {
				map.put(key, value);
			}

			@Override
			public List<String> getStringList(String key) {
				return (List<String>)map.get(key);
			}
			
		};
	}

}
