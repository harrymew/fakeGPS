package com.running.frank.fakegps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.SystemClock;


public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }

    public void changeLoc(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            EditText altitude = (EditText) findViewById(R.id.altitude);
            EditText longitude = (EditText) findViewById(R.id.longitude);
            double curLat = Double.valueOf(altitude.getText().toString());
            double curLong = Double.valueOf(longitude.getText().toString());


            locationManager.addTestProvider("fakegps", false, false, false, false, false, false, false, 1, 1);
            locationManager.clearTestProviderEnabled("fakegps");
            locationManager.setTestProviderEnabled("fakegps", true);

            Location loc = new Location("fakegps");
            loc.setTime(System.currentTimeMillis());
            loc.setLatitude(curLat);
            loc.setLongitude(curLong);
            loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            loc.setAccuracy(Criteria.ACCURACY_FINE);
            locationManager.setTestProviderStatus("fakegps", LocationProvider.AVAILABLE, null,
                    System.currentTimeMillis());
            locationManager.setTestProviderLocation("fakegps", loc);
        } catch (Exception e) {
            locationManager.setTestProviderEnabled("fakegps",false);
            locationManager.removeTestProvider("fakegps");
            Log.e("TAG", "ecp", e);
        }
    }
}
