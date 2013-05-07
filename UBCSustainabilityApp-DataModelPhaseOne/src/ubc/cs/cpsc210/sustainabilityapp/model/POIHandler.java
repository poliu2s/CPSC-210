package ubc.cs.cpsc210.sustainabilityapp.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The core of the XML SAX Parser
 * Breaks up the given XML file provided into separate elements and produces a
 * List of Points of Interest that is ultimately returned to the POI registry
 *
 */
public class POIHandler extends DefaultHandler{
	
	// Class fields
	static ArrayList<PointOfInterest> listofPOIs = new ArrayList<PointOfInterest>();
	private StringBuffer accumulator = new StringBuffer();
	private PointOfInterest a;
	private List<Feature> tempFeatures;

	/**
	 * Constructor that assigns memory allocation for temporary storage location of encountered features
	 */
	public POIHandler() {
		tempFeatures = new ArrayList<Feature>();
	}
	
	/**
	 * Event handler that is called when an new element tag is found
	 * @param qName - provides the string that is in the tag
	 * @param attributes - provides the strings that hold attributes at the beginning of a particular
	 * tag
	 * @param others - necessary for the method signature to match the SAX parser
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("POI")) {
			
			a = new PointOfInterest(attributes.getValue("Id"), attributes.getValue("DisplayName"));

			
		}  

		accumulator = new StringBuffer();
	}

	/**
	 * Method that is called whenever characters are found between two XML tags
	 * Here, all the characters are put into a string buffer called accumulator to be collected
	 * into a PointOfInterest class later on
	 * @param ch - character that is found
	 * @param start - start index of the characters found
	 * @param length - end index of characters found
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {

		accumulator.append(ch, start, length);
	}

	
	/**
	 * Called whenever the end tag in XML is detected. Determines which end tag it is and places
	 * it in the correct class field in the PointOfInterest class
	 * @param qName - tells you which end tag it is and most important in sliding it into the POI class
	 * @param others - necessary for method signature to match SAX parser
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equals("Address")) {
			a.setAddress(accumulator.toString().trim());
			accumulator = new StringBuffer();

		} else if (qName.equals("Lat")) {
			try {
				a.setLat( Float.valueOf(accumulator.toString().trim()).floatValue() );
				accumulator = new StringBuffer();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		} else if (qName.equals("Long")) {
			try {
				a.setLong( Float.valueOf(accumulator.toString().trim()).floatValue() );
				accumulator = new StringBuffer();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		} else if (qName.equals("Description")) {
			a.setDescription(accumulator.toString().trim());
			accumulator = new StringBuffer();
			



		} else if (qName.equals("Feature")) {
			
			String feat = accumulator.toString().trim();
			
			if (feat.equals("Biofuel")) {
				tempFeatures.add(Feature.BIOFUEL);
			} else if (feat.equals("Geothermal")) {
				tempFeatures.add(Feature.GEOTHERMAL);
			} else if (feat.equals("LEED Certification")) {
				tempFeatures.add(Feature.LEED_CERTIFICATION);
			} else if (feat.equals("Low Impact Materials")) {
				tempFeatures.add(Feature.LOW_IMPACT_MATERIALS);
			} else if (feat.equals("Rainwater Recovery")) {
				tempFeatures.add(Feature.RAINWATER_RECOVERY);
			} else if (feat.equals("Solar Energy")) {
				tempFeatures.add(Feature.SOLAR_ENERGY);
			} else if (feat.equals("Sustainable Agriculture")) {
				tempFeatures.add(Feature.SUSTAINABLE_AGRICULTURE);
			} else if (feat.equals("Wastewater Treatment")) {
				tempFeatures.add(Feature.WASTEWATER_TREATMENT);
			}
			

			accumulator = new StringBuffer();
			

		} else if (qName.equals("POI")) {

			
			a.setFeatures(tempFeatures);	
			
			listofPOIs.add(a);
			tempFeatures = new ArrayList<Feature>();

		}
	}
	
	/**
	 * Returns the List of Points of Interest so that it can be used in the registry class
	 * @return the list of POIs to be stored in the registry
	 */
	public ArrayList<PointOfInterest> getListPOI() {
		
		return listofPOIs;
	}
}
