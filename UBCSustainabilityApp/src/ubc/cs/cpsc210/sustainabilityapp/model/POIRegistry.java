package ubc.cs.cpsc210.sustainabilityapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A POI registry instance stores all the available POI's.
 */
public class POIRegistry {
	/**
	 * Static default instance -- holds a hard-coded set of UBC sustainability POI's.
	 */
	private static POIRegistry defaultInstance = createDefaultInstance();
	
	/**
	 * Maps from POI ID to corresponding POI.
	 */
	private Map<String, PointOfInterest> registeredPoints = new HashMap<String, PointOfInterest>();
	
	/**
	 * Stores POI's according to their geographical location.  This defines the ordering of the POI's
	 * on the walking tour.  
	 */
	private List<PointOfInterest> pointsByLocation = new ArrayList<PointOfInterest>();

	/**
	 * Get the default instance, containing a set of hard-coded UBC sustainability POI's.
	 * @return The one instance of the POIRegistry
	 */
	public static POIRegistry getDefault() {
		return defaultInstance;
	}

	/**
	 * Given a POI ID, looks up the corresponding POI.
	 * 
	 * @param id Unique ID of the POI. 
	 * @return The corresponding POI, or null if no matching POI is found.
	 */
	public PointOfInterest lookupPoint(String id) {
		return registeredPoints.get(id);
	}

	/**
	 * Get POIs in alphabetical order, sorted by display name.
	 * @return POIs in alphabetical order
	 */
	public List<PointOfInterest> getPointsAlphabetical() {
		List<PointOfInterest> result = new ArrayList<PointOfInterest>(pointsByLocation);
		Collections.sort(result, new Comparator<PointOfInterest>() {

			@Override
			public int compare(PointOfInterest object1, PointOfInterest object2) {
				return object1.getDisplayName().compareTo(object2.getDisplayName());
			}
			
		});
		return result;
	}
	
	/**
	 * Get POIs in walking tour order.
	 * @return The POIs in walking tour order.
	 */
	public List<PointOfInterest> getPointsByLocation() {
		return pointsByLocation;
	}

	/**
	 * Get POIs which display a specified sustainability feature.
	 * 
	 * @param The feature of interest.
	 * @return The POIs with the specified feature
	 */
	public List<PointOfInterest> getPointsWithFeature(Feature feature) {
		List<PointOfInterest> result = new ArrayList<PointOfInterest>();
		for (PointOfInterest point: registeredPoints.values()) {
			if (point.getFeatures().contains(feature)) {
				result.add(point);
			}
		}
		return result;
	}
	
	/**
	 * Add a point to the registry
	 * @param point Point to add
	 */
	private void addPoint(PointOfInterest point) {
		registeredPoints.put(point.getId(), point);
		pointsByLocation.add(point);
	}

	/**
	 * Creates the default POIRegistry instance, which contains a set of hard-coded UBC sustainability
	 * POI's.
	 * @return The one and only instance of POIRegistry
	 */
	private static POIRegistry createDefaultInstance() {
		POIRegistry result = new POIRegistry();
		
		PointOfInterest point;
		
		// We hardcode the POI for ease of use on Android

		// Ordering is important here -- this defines the ordering of the points on the walking tour.
		point = new PointOfInterest("LAW", "Law Building");
		point.setAddress("1822 East Mall, Vancouver, BC");
		point.setLatLong(new LatLong(49.269041,-123.25319));
		point.setDescription("LEED Gold certification. Renovating the old building would have cost four-fifths the amount to " +
				"construct a new building. In contrast to the Chemistry Centre, UBC decided to construct a new law building " +
				"instead of renovating -- each project is evaluated individually to determine what is best " +
				"environmentally, economically and socially. The Faculty of Law outgrew the old building so now " +
				"they will enjoy state-of-the-art teaching and research spaces that promote legal discussions and " +
				"student engagement.");
		point.setFeatures(new Feature[]{Feature.LEED_CERTIFICATION});
		result.addPoint(point);

		point = new PointOfInterest("CK_CHOI", "CK Choi Building");
		point.setAddress("1855 West Mall, Vancouver, BC");
		point.setLatLong(new LatLong(49.26786,-123.2582));
		point.setDescription("Representative of the first green buildings in North America, along with the " +
				"Liu Centre for Global Issues. No certification because this was the one building to start " +
				"the green building movement in North America. Made out of 50% recycled or reused materials, " +
				"including bricks that are over 300 years old! All sewage treated on site with composting " +
				"toilets and a wetland inspired by NASA.");
		point.setFeatures(new Feature[]{Feature.LOW_IMPACT_MATERIALS, Feature.WASTEWATER_TREATMENT});
		result.addPoint(point);
		
		point = new PointOfInterest("CHEM", "Chemistry Centre");
		point.setAddress("2036 Main Mall, Vancouver, BC");
		point.setLatLong(new LatLong(49.266179,-123.253805));
		point.setDescription("By reusing 100% of the exterior shell and 60% of the interior elements " +
				"the renewal project diverted 80% of the construction waste into recycling and away from " +
				"the landfill. High-efficiency lighting and heat recovery inside the building reduces energy " +
				"use by 21% compared to the original building.");
		point.setFeatures(new Feature[] {Feature.LOW_IMPACT_MATERIALS});
		result.addPoint(point);

		point = new PointOfInterest("CIRS", "CIRS");
		point.setAddress("2260 West Mall, Vancouver, BC");
		point.setLatLong(new LatLong(49.261763,-123.253411));
		point.setDescription("CIRS is a state-of-the-art 'living laboratory' where researchers and " +
				"industry partners can explore current and future " +
				"building systems and technologies, using the building itself as their lab. " +
				"The building sports a range of features, including a rain water harvest system, a deciduous facade, " +
				"geothermal heating and cooling, wastewater treatment, a green roof, and more. Also check out nearby" +
				"Sustainability Street, a demonstration urban landscape that collects rainwater from the street's watershed and stores it " +
				"in an underground well for use in CIRS.");
		point.setFeatures(new Feature[]{Feature.GEOTHERMAL, Feature.LOW_IMPACT_MATERIALS, Feature.RAINWATER_RECOVERY, 
				Feature.SOLAR_ENERGY, Feature.WASTEWATER_TREATMENT, Feature.LEED_CERTIFICATION});
		result.addPoint(point);

		point = new PointOfInterest("BRDC", "Bioenergy Research and Demonstration Centre");
		point.setAddress("Agronomy Road and Lower Mall");
		point.setLatLong(new LatLong(49.260231,-123.253766));
		point.setDescription("Creating energy from carbon neutral sources is critical to ensure environmental " +
				"sustainability. UBC will use Vancouver’s wood waste and turn it into synthetic gas, as " +
				"opposed to gas from the Earth’s crust, to run a generator that will provide enough steam " +
				"to heat 25% of the campus or sufficient electricity to power the 1,600 bed Marine Drive " +
				"Student Residence. This generator will be housed inside the first Cross Laminated Timber " +
				"building in North America.");
		point.setFeatures(new Feature[] {Feature.BIOFUEL});
		result.addPoint(point);

		point = new PointOfInterest("UBC_FARM", "UBC Farm");
		point.setAddress("6186 South Campus Road, Vancouver, BC");
		point.setLatLong(new LatLong(49.25143,-123.238181));
		point.setDescription("The UBC Farm is a 24-hectare learning and research farm located at the " +
				"south end of the main campus. The farm is student-driven and " +
				"integrated with the wider community. As the only working farmland within the city of Vancouver, " +
				"the UBC Farm is an urban agrarian gem, featuring a landscape of unique beauty.");
		point.setFeatures(new Feature[]{Feature.SUSTAINABLE_AGRICULTURE});
		result.addPoint(point);
		
		point = new PointOfInterest("LSC", "Life Sciences Centre");
		point.setAddress("2350 Health Sciences Mall, Vancouver, BC");
		point.setLatLong(new LatLong(49.26239,-123.246244));
		point.setDescription("Canada’s largest LEED Gold building. Over 3,500 people learn and do " +
				"cutting-edge medical research every day. The building does not disrupt its surrounding " +
				"watershed, nor does it pollute the night sky with light.");
		point.setFeatures(new Feature[] {Feature.LEED_CERTIFICATION});
		result.addPoint(point);

		return result;
	}

}
