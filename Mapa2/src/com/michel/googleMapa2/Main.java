package com.michel.googleMapa2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;

public class Main extends MapActivity implements LocationListener {
	MapView map;
	long start, stop;
	//MyLocationOverlay compass;
	Location last_known_location;
	
	int x, y;
	GeoPoint touchedPoint;
	Drawable  d;
	MapController controller;// Helps to go where we want to our map activity
	List<Overlay> overlayList;
	CustomPinpoint custom ;

	LocationManager lm;
	String towers;
	int lat = 0;
    int longi = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = (MapView)findViewById(R.id.mvMain);
        map.setBuiltInZoomControls(true);                             
        overlayList = map.getOverlays();             
        controller= map.getController();
        GeoPoint point = new GeoPoint(4164334, 7848593);// Handles longitud and latitude
        controller.animateTo(point);// Set the position
        controller.setZoom(4);        
        //
        d = getResources().getDrawable(R.drawable.ic_launcher);                
        //placing pinpoint at location                
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria crit= new Criteria();// Standart default Criteria         
        towers = lm.getBestProvider(crit, false);// getting best provider
        //towers= lm.GPS_PROVIDER;
      //  LocationProvider location_provider = LocationManager.NETWORK_PROVIDER;               
        last_known_location = lm.getLastKnownLocation(towers);
        //Toast.makeText(Main.this,towers, Toast.LENGTH_SHORT).show();        
        //At the beginning of the app
        if (last_known_location!= null)
        {
        	lat = (int)(last_known_location.getLatitude()*1E6);
        	longi = (int)(last_known_location.getLongitude()*1E6);
        	GeoPoint ourLocation = new GeoPoint(lat, longi);
     		OverlayItem overlayItem  = new OverlayItem(ourLocation, "Whats up", "2 string");
     		custom = new CustomPinpoint(d, Main.this);
     		custom.insertPinpoint(overlayItem);
     		overlayList.add(custom);     		        
        }
        
        else{
        	Toast.makeText(Main.this,"Couldnt get provider", Toast.LENGTH_SHORT).show();
        	
        }
        
       
		
        
        
    }

   // Compass widget
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub	
    	//compass.disableCompass();
    	super.onPause();	
    	lm.removeUpdates(this);
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers, 5000, 50, this);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
//Method called when the location has changed
	@Override	
	public void onLocationChanged(Location new_location)
		{ 
			// TODO Auto-generated method stub			   	
		    overlayList.remove(custom);			
		    lat=(int)(new_location.getLatitude()*1E6);
			longi = (int)(new_location.getLongitude()*1E6);
			GeoPoint ourLocation = new GeoPoint(lat, longi);
	 		OverlayItem overlayItem  = new OverlayItem(ourLocation, "Whats up", "2 string");
	 		custom = new CustomPinpoint(d, Main.this);
	 		custom.insertPinpoint(overlayItem);
	 		overlayList.add(custom);	 						
		}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}



