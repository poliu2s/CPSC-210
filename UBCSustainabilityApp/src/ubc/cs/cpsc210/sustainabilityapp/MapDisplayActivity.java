package ubc.cs.cpsc210.sustainabilityapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mapsforge.android.maps.ArrayItemizedOverlay;
import org.mapsforge.android.maps.ArrayWayOverlay;
import org.mapsforge.android.maps.GeoPoint;
import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapController;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.MapViewMode;
import org.mapsforge.android.maps.OverlayItem;
import org.mapsforge.android.maps.OverlayWay;

import ubc.cs.cpsc210.sustainabilityapp.model.LatLong;
import ubc.cs.cpsc210.sustainabilityapp.model.POIRegistry;
import ubc.cs.cpsc210.sustainabilityapp.model.PointOfInterest;
import ubc.cs.cpsc210.sustainabilityapp.model.SharedPreferencesKeyValueStore;
import ubc.cs.cpsc210.sustainabilityapp.model.TourState;
import ubc.cs.cpsc210.sustainabilityapp.routing.RouteInfo;
import ubc.cs.cpsc210.sustainabilityapp.routing.RoutingService;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Activity representing the Map tab in the UI.
 */
public class MapDisplayActivity extends MapActivity {
	private static final int DARK_BLUE = Color.rgb(0x57, 0x81, 0xFC);

	private final static String LOG_TAG = "MapDisplayActivity";
	
	private final static GeoPoint ICICS_GEOPOINT = new GeoPoint(49.260887,-123.24902);
	
	/**
	 * Overlay for POI markers.
	 */
	private ArrayItemizedOverlay poiOverlay;
	
	/**
	 * Overlay for the user's current location.
	 */
	private ArrayItemizedOverlay myLocationOverlay;
	
	/**
	 * Overlay for the route connecting the selected POI's. 
	 */
	private ArrayWayOverlay tourOverlay;
	
	/**
	 * Overlay for the route connecting the user's current location to the nearest 
	 * selected POI.
	 */
	private ArrayWayOverlay routeToTourOverlay;

	/**
	 * Manages and stores selected features and POI's.
	 */
	private TourState tourState;
	
	/**
	 * Currently selected POI's.
	 */
	private List<PointOfInterest> selectedPOIs;
	

	/**
	 * Wrapper for a service which calculates routes between POI's, and between the user's current location and the
	 * nearest selected POI.
	 */
	private RoutingService routingService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tourState = new TourState(POIRegistry.getDefault(), 
				new SharedPreferencesKeyValueStore(this, TourState.STORE_NAME));
		
		routingService = new RoutingService();
		
		MapView mapView = new MapView(this);

		// Maps are downloaded from the Mapnik server -- an internet connection is required.
		mapView.setMapViewMode(MapViewMode.MAPNIK_TILE_DOWNLOAD);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);

		MapController mapController = mapView.getController();
		
		// Center the map on ICICS.
		mapController.setCenter(ICICS_GEOPOINT);
		
		// With the Mapnik server, this zoom level results in a map which encompasses most of
		// the UBC campus.
		mapController.setZoom(mapView.getMaxZoomLevel() - 4);
		
		poiOverlay = createPOIOverlay();
		myLocationOverlay = createMyLocationOverlay();
		tourOverlay = createTourOverlay();
		routeToTourOverlay = createRouteToTourOverlay();
		
		// Order matters: overlays added later are displayed on top of overlays added earlier.
		mapView.getOverlays().add(routeToTourOverlay);
		mapView.getOverlays().add(tourOverlay);
		mapView.getOverlays().add(poiOverlay);
		mapView.getOverlays().add(myLocationOverlay);

		setContentView(mapView);
		
		
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

		//------------------------------------------------------------------
		//------------------------------------------------------------------
		
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	   	String bestProvider = locationManager.getBestProvider(criteria, true);
	   	
	   	MyLocationListener ll = new MyLocationListener();
	   	
	   	locationManager.requestLocationUpdates(bestProvider, 1000, 0, ll);

		
	}

	@Override 
	protected void onDestroy() {
		if (routingService != null) {
			routingService.shutdown();
		}
		super.onDestroy();
	}

	/**
	 * Update the overlays based on the selected POI's and the user's current location.
	 */
	@Override
	protected void onResume() {
		selectedPOIs = tourState.getSelectedPOIs();
		
		updateTour(selectedPOIs);
		
		super.onResume();
	}

	/**
	 * Update the POI markers and the route connecting them.
	 */
	private void updateTour(List<PointOfInterest> pois) {
		// IMPLEMENT ME!
		tourOverlay.clear();
		poiOverlay.clear();
		
		
		// First plot the points of interest
		for(PointOfInterest p: pois) {
			plotPOI(poiOverlay, p);
		}
		
		
		
		// Next determine all of the points on the tour and use a helper
		// method on this class to retrieve the tour route and display it
		List<LatLong> mylist = new ArrayList<LatLong>();
		for(PointOfInterest p: pois) {
			
			mylist.add(p.getLatLong());
		}
		
		findRouteAndUpdateOverlay(tourOverlay, mylist, true);
		
	}
	

	/**
	 * Plot a POI on the specified overlay.
	 */
	private void plotPOI(ArrayItemizedOverlay overlay, PointOfInterest poi) {
		// IMPLEMENT ME!
				
		
		GeoPoint gp = new GeoPoint(poi.getLatLong().getLatitude(), poi.getLatLong().getLongitude());
		OverlayItem item = new OverlayItem(gp, poi.getDisplayName(), poi.getDescription());
		overlay.addItem(item);
		
	}


	/** 
	 * Given a location and a list of POI's, find the POI closest to the specified location.
	 * 
	 * This is based on "line-of-sight" distance between points, using an approximation which works
	 * okay for short distances (surface of the earth is approximated by a plane).
	 */
	private PointOfInterest findClosestPOI(Location location, List<PointOfInterest> pois) {
		double approxLatitude = pois.get(0).getLatLong().getLatitude();
		
		PointOfInterest closest = null;
		double minDistValue = Double.MAX_VALUE;
		
		LatLong locationLatLong = new LatLong(location.getLatitude(), location.getLongitude());
		
		for (PointOfInterest poi: pois) {
			double distValue = getDistanceValue(approxLatitude, locationLatLong, poi.getLatLong());
			if (distValue < minDistValue) {
				minDistValue = distValue;
				closest = poi;
			}
		}
		
		return closest;
	}
	
	/**
	 * Get a value representing the "line-of-sight" distance between two points, using an approximation which works
	 * okay for short distances (surface of the earth is approximated by a plane).
	 * 
	 * The value returned is only usable for comparison purposes (i.e. it has a nonlinear relationship to the actual
	 * distance).
	 */
	private double getDistanceValue(double approxLatitude, LatLong pointA, LatLong pointB) {
		double latAdjust = Math.cos(Math.PI * approxLatitude / 180.0);
		double latDiff = pointA.getLatitude() - pointB.getLatitude();
		double longDiff = pointA.getLongitude() - pointB.getLongitude();
		
		return Math.pow(latDiff, 2) + Math.pow(latAdjust * longDiff, 2);
	}

	/**
	 * Create the overlay for POI markers.
	 */
	private ArrayItemizedOverlay createPOIOverlay() {
		return new ArrayItemizedOverlay(getResources().getDrawable(R.drawable.map_pin_blue)) {
			@Override
			protected boolean onTap(int index) {
				PointOfInterest poi = selectedPOIs.get(index);
				AlertDialog.Builder dialog = new AlertDialog.Builder(MapDisplayActivity.this);
				dialog.setTitle(poi.getDisplayName());
				dialog.setMessage(poi.getDescription());
				dialog.show();
				return true;
			}
		};
	}

	/**
	 * Create the overlay for the user's current location.
	 */
	private ArrayItemizedOverlay createMyLocationOverlay() {
		return new ArrayItemizedOverlay(getResources().getDrawable(R.drawable.map_pin_walk));
	}

	/**
	 * Create the overlay for the route connecting the selected POI's.
	 */
	private ArrayWayOverlay createTourOverlay() {
		Paint wayOverlayFill = new Paint(Paint.ANTI_ALIAS_FLAG);
		wayOverlayFill.setStyle(Paint.Style.STROKE);
		wayOverlayFill.setColor(DARK_BLUE);
		wayOverlayFill.setStrokeWidth(4);

		return new ArrayWayOverlay(wayOverlayFill, null);
	}

	/**
	 * Create the overlay connecting the user's current location to the closest selected POI.
	 */
	private ArrayWayOverlay createRouteToTourOverlay() {
		Paint wayOverlayFill = new Paint(Paint.ANTI_ALIAS_FLAG);
		wayOverlayFill.setStyle(Paint.Style.STROKE);
		wayOverlayFill.setColor(DARK_BLUE);
		wayOverlayFill.setStrokeWidth(4);

		return new ArrayWayOverlay(wayOverlayFill, null);
	}

	/**
	 * Calls the routing service to obtain a route which connects the specified list of lat/long points.
	 * 
	 * @param overlay The way overlay which will be updated with the resulting route.
	 * @param points Points which the route must pass through.
	 * @param useCache If set to true, the routing service will return a cached route if one is available 
	 *                 (and will cache the result if no cached route is found).
	 */
	private void findRouteAndUpdateOverlay(ArrayWayOverlay overlay, List<LatLong> points, boolean useCache) {
		// Retrieve routes in a separate thread, as it can take some time and we do not want to 
	    // block the UI thread.
		RouteRetriever retriever = new RouteRetriever(overlay, points, useCache);
		new Thread(retriever).start();
	}
	
	/**
	 * Add a route to the specified overlay.
	 */
	private void addRouteToOverlay(ArrayWayOverlay overlay, List<LatLong> waypoints) {
		// IMPLEMENT ME!
		
		//Make each one of the waypoints into a Geopoint object array
		List<GeoPoint> geo = new ArrayList<GeoPoint>();
		for (LatLong ptn: waypoints) {
			geo.add(new GeoPoint(ptn.getLatitude(), ptn.getLongitude()));
		}
		
		
		//Make a 2D array of Geopoints with 1 element in one dimension and the number of waypoints in the other
		//This is done because the method setWayData takes a 2D array of GeoPoints
		//Which is needed to plot the geopoints onto the overlay
		GeoPoint[][] myGeoArray = new GeoPoint[1][geo.size()];
		OverlayWay myWay = new OverlayWay();
		
		
		geo.toArray(myGeoArray[0]);
		myWay.setWayData(myGeoArray);
		overlay.addWay(myWay);
		
	}
	
	/**
	 * Calls the routing service to obtain a route which connects the specified list of lat/long points,
	 * and updates the overlay provided with the resulting route.
	 * 
	 * Routes are retrieved in a separate thread, as it can take some time and we do not want to 
	 * block the UI thread.
	 */
	private class RouteRetriever implements Runnable {
		private ArrayWayOverlay overlay;
		private List<LatLong> points;
		private boolean useCache;
		
		public RouteRetriever(ArrayWayOverlay overlay, List<LatLong> points, boolean useCache) {
			this.overlay = overlay;
			this.points = points;
			this.useCache = useCache;
		}
		
		@Override
		public void run() {
			LatLong firstPoint = null;
			LatLong lastPoint = null;
			try {
				if (points.size() > 1) {
					for (int i = 1; i < points.size(); i++) {
						LatLong currPoint = points.get(i-1);
						LatLong nextPoint = points.get(i);
						RouteInfo info = routingService.getRoute(currPoint, nextPoint, useCache);
						
						
						
						final List<LatLong> waypoints = new ArrayList<LatLong>();
						waypoints.add(currPoint);
						waypoints.addAll(info.getWaypoints());
						waypoints.add(nextPoint);
						
						// Updates to the UI must run on the UI thread.
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								addRouteToOverlay(overlay, waypoints);
							}
							
						});
					}
					
					//My function------------------------------------------------------------
					firstPoint = points.get(0);
					lastPoint = points.get(points.size()-1);
					
					RouteInfo info = routingService.getRoute(firstPoint, lastPoint, useCache);
					
					
					
					final List<LatLong> waypoints = new ArrayList<LatLong>();
					waypoints.add(firstPoint);
					waypoints.addAll(info.getWaypoints());
					waypoints.add(lastPoint);
					
					// Updates to the UI must run on the UI thread.
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							addRouteToOverlay(overlay, waypoints);
						}
						
					});
				}
			} catch (Exception e) { 
				Log.e(LOG_TAG, "Error retrieving route from route service", e);
			}
		}
	}
	
	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			//Clear everything off overlays
			routeToTourOverlay.clear();
			myLocationOverlay.clear();
			
			
			//Location is drawn onto an overlay
			OverlayItem myOI = new OverlayItem(new GeoPoint(location.getLatitude(), location.getLongitude()), null, null);
			myLocationOverlay.addItem(myOI);
			
			
			//Use the following functions to find and draw a line between gps location and route
			List<LatLong> myGPStoPOI = new ArrayList<LatLong>();
			
			PointOfInterest closestPOI = findClosestPOI(location, selectedPOIs);
			LatLong closestPtn = closestPOI.getLatLong();			
			
			myGPStoPOI.add(closestPtn);
			myGPStoPOI.add(new LatLong(location.getLatitude(), location.getLongitude()));
			
			
			//Finally, update the overlay with the user's location
			findRouteAndUpdateOverlay(routeToTourOverlay, myGPStoPOI, false);
		}

		
		/**
		 * Empty constructor with default visibility to avoid a synthetic method.
		 */
		MyLocationListener() {
			// empty, left unimplemented
		}
		
		
		@Override
		public void onProviderDisabled(String arg0) {
			// empty, left unimplemented
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// empty, left unimplemented
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// empty, left unimplemented
			
		}
	}


}
