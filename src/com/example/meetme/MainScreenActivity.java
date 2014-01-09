package com.example.meetme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainScreenActivity extends Activity {
	
	//declare variables
	private static String lat ="";
	private static String lon="";
	private static String myId="";
	private static String urId="";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PID = "pid";
	private static final String TAG_LATITUDE= "la";
	private static final String TAG_LONGITUDE = "lo";
	private static final String UPLOAD_LOCATION = "http://54.200.84.125/ourmeet/update_locations.php";
	private static final String GET_LOCATION = "http://54.200.84.125/ourmeet/read_specific_location.php";
	public static String getUPLOAD_LOCATION() {
		return UPLOAD_LOCATION;
	}

	//getter
	public static String getLocation() {
		return GET_LOCATION;
	}
	
	public static String getLat() {
		return lat;
	}
	
	public static String getmyID () {
		return myId;
	}
	
	public static String geturID(){
		return urId;
	}
	public static String getLon() {
		return lon;
	}
	EditText editId;
	
	Button btnFriendLocation;
	Button btnUploadLocation;
	Button btnLocation;
	Button btnRegisterId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		editId=(EditText)findViewById(R.id.editId);
		
		//Buttons
		btnLocation=(Button)findViewById(R.id.btnLocation);
		btnUploadLocation=(Button)findViewById(R.id.btnUploadLocation);
		btnFriendLocation=(Button)findViewById(R.id.btnFriendLocation);
		btnRegisterId=(Button)findViewById(R.id.btnRegisterId);
		
		btnRegisterId.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				myId = editId.getText().toString();
			}
		});
		
		
		btnLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Acquire a reference to the system Location Manager
				LocationManager locationManager = (LocationManager) MainScreenActivity.this.getSystemService(Context.LOCATION_SERVICE);
				// Define a listener that responds to location updates
				LocationListener locationListener = new LocationListener() {
					public void onLocationChanged(Location location) {
						// Called when a new location is found by the network location provider.
						lat = Double.toString(location.getLatitude());
						lon = Double.toString(location.getLongitude());
						TextView tv = (TextView) findViewById(R.id.txtLoc);
						tv.setText("Your Location is:" + lat + "--" + lon);
					}

					public void onStatusChanged(String provider, int status, Bundle extras) {}
					public void onProviderEnabled(String provider) {}
					public void onProviderDisabled(String provider) {}
				};
				// Register the listener with the Location Manager to receive location updates
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			
		
			//post to database
			//new uploadLocation().execute();
			}
		});

		//class uploadLocation extends AsyncTask<String,String,String> {
			
		//}
	//upload location click event
	btnUploadLocation.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick (View view) {
				Intent i = new Intent(getApplicationContext(), UploadLocation.class);
				startActivity(i);
		}
	});
	
	//Friend location
	btnFriendLocation.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick (View view) {
				Intent i = new Intent(getApplicationContext(), DisplayLocation.class);
				startActivity(i);
		}
	});	
	

	}

}