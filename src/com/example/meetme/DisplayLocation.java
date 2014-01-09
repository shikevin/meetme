package com.example.meetme;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayLocation extends Activity{

	String urId="";
	String get_location = MainScreenActivity.getLocation();
	TextView txtInformation, txtLocation;
	//EditText txtLocation;
	JsonParser jsonParser = new JsonParser();
	Button btnSetId, btnFetchLocation;
	EditText editSearch;
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_location);
		txtInformation = (TextView) findViewById(R.id.txtInformation);
		txtInformation.setText("Loading...");
		
		editSearch=(EditText)findViewById(R.id.editSearch);
		btnSetId=(Button)findViewById(R.id.btnSetId);
		
		btnSetId.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				urId = editSearch.getText().toString();
			}
		});
		
		btnFetchLocation=(Button)findViewById(R.id.btnFetchLocation);
		
		btnFetchLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				new getServerData().execute();
			}
		});
	}

	class getServerData extends AsyncTask<String, String, String> {
		
		protected String doInBackground (String... arg0){

			int success;		
            
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>(1);
                params.add(new BasicNameValuePair("pid", urId));
                // getting product details by making HTTP request
                // Note that product details url will use GET request
                JSONObject json = JsonParser.makeHttpRequest(get_location, "GET", params);

                // check your log for json response
                Log.d("Single Product Details", json.toString());

                //txtLocation=(EditText) findViewById(R.id.txtLocation);
                // display product data in EditText
                //txtLocation.setText("your location is: ");

                // json success tag
                /* try {
					success = Integer.parseInt(json.getString("success"));
	                if (success == 1) {
	                    txtLocation = (EditText) findViewById(R.id.txtLocation);

	                    // display product data in EditText
	                    txtLocation.setText("your location is: ");
	                }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				success = Integer.parseInt(json.getString("success"));
                if (success == 1) {
                    // successfully received product details
                    JSONArray productObj = json
                            .getJSONArray("location"); // JSON Array

                    // get first product object from JSON Array
                    JSONObject location = productObj.getJSONObject(0);
                    final String latitude=location.getString("la");
                    final String longitude=location.getString("lo");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
            		txtLocation = (TextView) findViewById(R.id.txtLocation);
            		txtLocation.setText("your location is: "+latitude+" -- "+longitude);
                        }
                    });
                    // product with this pid found
                    // Edit Text
                    //txtLocation = (EditText) findViewById(R.id.txtLocation);

                    // display product data in EditText
                    //txtLocation.setText("your location is: "+location.getString("la")+" -- "+location.getString("lo"));

                }else{
                	
                    // product with pid not found
                    
                } 
            } catch (JSONException e) {
                e.printStackTrace();
                
            }
            return null;
}
}
}