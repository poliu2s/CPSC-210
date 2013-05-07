package ubc.cs.cpsc210.sustainabilityapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/*
 * Captures which POIs or Features that the user wishes to include in his/her tour
 */
public class TourState {
	POIRegistry classReg;
	KeyValueStore classMem;
	
	List<PointOfInterest> selectedPOI;
	List<PointOfInterest> unselectedPOI;
	List<Feature> selectedFeature;

	/**
	 * Constructor
	 * @param reg provides the registry that holds the points data from the XML file
	 * @param mem the place in memory that holds all the strings reprsenting the POIs and features
	 */
	public TourState(POIRegistry reg, KeyValueStore mem) {
		classReg = reg;
		classMem = mem;
		
		selectedPOI = new ArrayList<PointOfInterest>();
		unselectedPOI = new ArrayList<PointOfInterest>();
		selectedFeature = new ArrayList<Feature>();

	}
	
	
	/**  
	 * Get currently selected points of interest from storage in walking 
	 * tour order. 
	 * 
	 * Effects: return currently selected points of interest in  
	 *   associated KeyValueStore object in walking 
	 *   tour order. If there are no selected points of interest  
	 *   initialized in storage, return all registered points of 
	 *   interest. 
	 */
	public List<PointOfInterest> getSelectedPOIs() {
		
		
		selectedPOI = new ArrayList<PointOfInterest>();
		List<String> myPOI = classMem.getStringList("selectedPOI");
		for(PointOfInterest i : classReg.getDefault().pointsByLocation) {
			
			if (myPOI.contains(i.getId())) {
				selectedPOI.add(i);
			}
			
		}
		if (selectedPOI.size() > 0 ) {
			return selectedPOI;
		}
		else {
			return classReg.getDefault().pointsByLocation;
		}
	
	}
	
	
	/** 
	 * Get currently selected features from storage. 
	 * 
	 * Effects: return currently selected features. If there are no 
	 *   selected features initialized in storage, return all  
	 *   available features. 
	 */
	public List<Feature> getSelectedFeatures() {
		
		selectedFeature = new ArrayList<Feature>();
		Set<Feature> tempFeature = new TreeSet<Feature>();
		Set<Feature> allFeatures = new TreeSet<Feature>();
		List<String> myFeatures = classMem.getStringList("selectedFeatures");
		for(PointOfInterest i : classReg.getDefault().pointsByLocation) {
			for (Feature j : i.getFeatures()) {
				for (String k : myFeatures) {
					if (k.compareTo(j.toString()) == 0) {
						tempFeature.add(j);
					}
					
				}
				allFeatures.add(j);
			}
		}
		
		
		for (Feature i : tempFeature) {
			selectedFeature.add(i);
		}
		/*

		if (selectedFeature.size() > 0) {
			return selectedFeature;
		} else {
			
			for (Feature i : allFeatures) {
				selectedFeature.add(i);
			}
			return selectedFeature;
		}
		*/
		return selectedFeature;
		
	}
	
	/** 
	 * Set the selected points of interest.  
	 *  
	 * Requires: selectedPOIs is a non-null list and all items in the list 
	 *   are valid registered points of interest 
	 * 
	 * Effects: The selectedPOIs is remembered as selected points of 
	 *   interest using the KeyValueStore object associated with this  
	 *   object and each feature, for which the selectedPOIs include all 
	 *   examples of points in the registry with that feature, becomes 
	 *   selected. The set of selected features is also remembered in 
	 *   the associated KeyValueStore object. 
	 */
	public void setSelectedPOIs(List<PointOfInterest> selectedPOIs) {
		selectedPOI = new ArrayList<PointOfInterest>();
		unselectedPOI = new ArrayList<PointOfInterest>();
		selectedFeature = new ArrayList<Feature>();

		
		
		// Take the selectedPOIs and make a list
		for (PointOfInterest i : selectedPOIs) {
			selectedPOI.add(i);
					
		}

		
		// Add all points of register into unselected list first
		for (PointOfInterest i : POIRegistry.pointsByLocation) {
			unselectedPOI.add(i);
	
		}
		
		// Remove all the POIs that were selected
		for (PointOfInterest i : selectedPOIs) {
			
			for (int j = 0; j<unselectedPOI.size(); j++) {
				if (unselectedPOI.get(j).getId().equals(i.getId())) {
					unselectedPOI.remove(j);
				}
			}
					
		}

		
		//Find the features of the selectedPOI (Set A)
		Set<Feature> fa = new TreeSet<Feature>();
		for (PointOfInterest i : selectedPOI) {
			fa.addAll(i.getFeatures());
		}
		
		
		//Find the features of the unselectedPOI (Set B)
		Set<Feature> fb = new TreeSet<Feature>();
		for (PointOfInterest i : unselectedPOI) {
			fb.addAll(i.getFeatures());
		}
		
		
		// Subtract Set A - Set B
		for (Feature i : fb) {
			if (fa.contains(i)) {
				fa.remove(i);
			}
		}
		
		// Put the difference in selectedFeature
		for (Feature i : fa ){
			selectedFeature.add(i);
		}
		
		// Put the selected features and POIs in KeyValueStore object
		classMem = new MemoryKeyValueStore();
		List<String> listofselectedPOI = new ArrayList<String>();
		for (PointOfInterest i : selectedPOI) {
			listofselectedPOI.add(i.getId());
		}
		classMem.putStringList("selectedPOI", listofselectedPOI);
		
		

		List<String> listofselectedFeatures = new ArrayList<String>();
		for (Feature i : selectedFeature) {
			listofselectedFeatures.add(i.toString());
		}
		classMem.putStringList("selectedFeatures", listofselectedFeatures);
		
		
	}
	
	

	/** 
	 * Set the selected features. 
	 * 
	 * Requires: selectedFeatures is a non-null list and all items in the 
	 *   list are valid features 
	 * 
	 * Effects: The selectedFeatures is remembered as selected features 
	 *    using the KeyValueStore object associated with this object and 
	 *    the set of POIs that contain a feature in selectedFeatures are 
	 *    selected and remembered in the KeyValueStore object, replacing 
	 *    any previously stored selected POIs. 
	 */
	public void setSelectedFeatures(List<Feature> selectedFeatures) {
		selectedPOI = new ArrayList<PointOfInterest>();
		unselectedPOI = new ArrayList<PointOfInterest>();
		selectedFeature = new ArrayList<Feature>();

		
		for (Feature i : selectedFeatures) {
			selectedFeature.add(i);
		}
		
		
		Boolean flag = false;
		for (PointOfInterest i : POIRegistry.pointsByLocation) {
			flag = false;
			
			
			for (Feature j : selectedFeature) {
				if (i.getFeatures().contains(j)) {
					flag = true;
				}
			}
			
			if (flag == true) {
				selectedPOI.add(i);
			}
			
		}
		
		// Put the selected features and POIs in KeyValueStore object
		classMem = new MemoryKeyValueStore();
		List<String> listofselectedPOI = new ArrayList<String>();
		for (PointOfInterest i : selectedPOI) {
			listofselectedPOI.add(i.getId());
		}
		classMem.putStringList("selectedPOI", listofselectedPOI);
				
				
		List<String> listofselectedFeatures = new ArrayList<String>();
		for (Feature i : selectedFeature) {
			listofselectedPOI.add(i.toString());
		}
		classMem.putStringList("selectedFeatures", listofselectedFeatures);

		
	}
}
