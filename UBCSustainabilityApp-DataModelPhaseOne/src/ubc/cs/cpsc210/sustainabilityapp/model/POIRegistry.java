package ubc.cs.cpsc210.sustainabilityapp.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class POIRegistry {
	static ArrayList<PointOfInterest> pointsByLocation;
	static POIHandler tempStorage = new POIHandler();
	
	/**
	 * Static default instance.
	 */
	private static POIRegistry defaultInstance = createDefaultInstance();
	
	public static POIRegistry getDefault() {
		return defaultInstance;
	}
	
	/**
	 * Creates the default POIRegistry instance, which contains a set of
	 * hard-coded UBC sustainability POI's.
	 */
	private static POIRegistry createDefaultInstance() {
		POIRegistry result = new POIRegistry();

		// Call parser here
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			
			SAXParser blah = factory.newSAXParser();
			blah.parse( new File("UBC-Sustainability-MapInfo.xml"), new POIHandler());
			POIRegistry.pointsByLocation = tempStorage.getListPOI();
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		return result;
	}
	
	/**
	 * Uses a string to search for POI in the registry and returns it if found
	 * @param poi - String that describe POI (ID)
	 * @return the POI that the id refers to in the registry
	 */
	public PointOfInterest lookupPoint(String poi) {
		PointOfInterest foundPoint = null;
		for (PointOfInterest i: pointsByLocation) {
			if (i.getId().equals(poi)) {
				return i;
			}
		}
		
		return foundPoint;
	}
	
	/**
	 * Returns the POIs in alphabetical order in a list, using bubblesort algorithm
	 * @return List of POIs in alphabetical order
	 */
	public List<PointOfInterest> getPointsAlphabetical() {
		List<PointOfInterest> returnPOIs = new ArrayList<PointOfInterest>();
		
		//Make a copy in returnPOIs
		for (PointOfInterest i: pointsByLocation) {
			returnPOIs.add(i);
		}
		
		// Used bubblesort algorithm to sort the POIs by lowest ascii id characters
		boolean flag = true;
		PointOfInterest temp;
		
		while (flag) {
			flag = false;
			for (int i = 0; i<returnPOIs.size()-1; i++ ) {
				
				// Swap if the second element is larger
				if (returnPOIs.get(i).getDisplayName().compareTo( returnPOIs.get(i+1).getDisplayName()) > 0 ) {
					temp = returnPOIs.get(i);
					returnPOIs.set(i, returnPOIs.get(i+1));
					returnPOIs.set(i+1, temp);
					flag = true;
				}
				
			}
		}
		
		return returnPOIs;
	}
	
	/**
	 * Gets all points in the order they were found in the XML parser
	 * @return Returns list of POIs
	 */
	public List<PointOfInterest> getPointsByLocation() {
		
		
		return pointsByLocation;
	}
	
	
	/**
	 * Gets all POIs with a specific feature that is specified
	 * @param feature1 - the feature that the user is interested in finding POIs that have this feature
	 * @return List of POIs with the given features
	 */
	public List<PointOfInterest> getPointsWithFeature(Feature feature1) {
		List<PointOfInterest> returnPOIs = new ArrayList<PointOfInterest>();
		
		for (PointOfInterest i: pointsByLocation) {
			
			for (Feature j : i.getFeatures()) {
				if (j.toString().equals(feature1.toString())) {
					returnPOIs.add(i);
				}
			}
			
		}
		
		return returnPOIs;
	}

}
