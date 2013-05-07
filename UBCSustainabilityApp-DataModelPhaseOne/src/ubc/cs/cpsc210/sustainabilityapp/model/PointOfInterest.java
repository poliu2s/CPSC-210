package ubc.cs.cpsc210.sustainabilityapp.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Central data structure that keeps all information relating to a particular point of interest
 * along the tour in one place. Has an expandable list of features that can be dynamically altered
 * upon initialization.
 * 
 *
 */

public class PointOfInterest {

	
	// Class Fields
	private String identification;
	private String displayName;
	private String address;
	private float latitude;
	private float longitude;
	private String description;
	private List<Feature> features;
	
	
	/**
	 * Constructor that creates a new POI with an ID and display name
	 * @param newID - the id that the new POI will take
	 * @param newDisplayName -- a displayName that gives more information on the title of the building
	 */
	public PointOfInterest(String newID, String newDisplayName) {
		identification = newID;
		displayName = newDisplayName;
		features = new ArrayList<Feature>();
	}
	
	/**
	 * Sets the features for a particular POI
	 * @param the list of features that the POI will contain
	 */
	public void setFeatures(List<Feature> featur) {
		features = featur;
	}
	
	/**
	 * Return the list of features for the POI
	 * @return list of POIs to return
	 */
	public List<Feature> getFeatures() {
		return features;
	}
	
	/**
	 * Sets the ID of the particular POI
	 * @param takes a string that represents the ID and sets it to the class field
	 */
	public void setid(String a) {
		identification = a;
	}
	
	
	/**
	 * Sets the address class field
	 * @param givenAddress is the string that becomes the POIs address
	 */
	public void setAddress(String givenAddress){
		address = givenAddress;
	}
	
	/**
	 * Sets the latitude position of the POI
	 * @param lat - a float value that represents the POI in GPS coordinates
	 */
	public void setLat(float lat){
		latitude = lat;
	}
	
	/**
	 * Sets the longitude position of the POI
	 * @param longi - a float value that represents the POI in GPS coordinates
	 */
	public void setLong(float longi) {
		longitude = longi;
	}
	
	/**
	 * Sets the description
	 * @param descrip - the string that the description for the POI will take
	 */
	public void setDescription(String descrip) {
		description = descrip;
	}
	
	/**
	 * Returns the display name
	 * @return returns a string that is the display name
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Returns the id
	 * @return returns a string that is the id
	 */
	public String getId() {
		return identification;
	}
	
	/**
	 * Returns the address
	 * @return returns a string that is the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Returns the latitude
	 * @return returns a float that is the latitude
	 */
	public float getLat() {
		return latitude;
	}
	
	/**
	 * Returns the longitude
	 * @return returns a float that is the longitude
	 */
	public float getLong() {
		return longitude;
	}
	
	/**
	 * Returns the description
	 * @return returns a string that is the description
	 */
	public String getDescription() {
		return description;
	}
}
