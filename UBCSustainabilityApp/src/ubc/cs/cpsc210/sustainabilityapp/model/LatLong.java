package ubc.cs.cpsc210.sustainabilityapp.model;

/**
 * Encapsulates a latitude/longitude pair.
 */
public class LatLong {
	private double latitude;
	private double longitude;
	
	/** 
	 * Construct a new LatLong object
	 * @param latitude The latitude
	 * @param longitude The longitude
	 */
	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * We override hashCode() and equals(), so that RouteEndpoints (which contains two LatLong values) can
	 * be used as a key in a HashMap.
	 * 
	 * Code for this method was generated using Eclipse: Source > Generate hashCode() and equals().
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * We override hashCode() and equals(), so that RouteEndpoints (which contains two LatLong values) can
	 * be used as a key in a HashMap.
	 * 
	 * Code for this method was generated using Eclipse: Source > Generate hashCode() and equals().
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatLong other = (LatLong) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
}
