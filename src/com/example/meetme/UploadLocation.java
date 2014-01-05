package com.example.meetme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UploadLocation extends Activity {

	String lat = MainScreenActivity.getLat();
	String lon = MainScreenActivity.getLon();
	String myID = MainScreenActivity.getmyID();
	String upload_url = MainScreenActivity.getUPLOAD_LOCATION();

	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_location);
		LinearLayout rootLayout = new LinearLayout(getApplicationContext());
		TextView txt = new TextView(getApplicationContext());
		rootLayout.addView(txt);
		setContentView(rootLayout);

		txt.setText("Connecting...");
		new sendServerData().execute();
		//txt.setText(getStringFromInputStream(sendServerData(myID,upload_url, lat, lon)));
	}

	public class sendServerData extends AsyncTask <String, String, String>{
		
		protected String doInBackground (String... arg0) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(upload_url);
			
			try {
				List <NameValuePair> nameValuePairs = new ArrayList <NameValuePair>(3);
				nameValuePairs.add(new BasicNameValuePair("pid",myID));
				nameValuePairs.add(new BasicNameValuePair("la",lat));
				nameValuePairs.add(new BasicNameValuePair("lo",lon));
				
				httppost.setEntity((HttpEntity) new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				
			}catch (IOException e) {
				
			}
			return null;
		}
	}
}