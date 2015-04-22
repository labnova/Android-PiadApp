package com.example.android.piadapp.Mappa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.android.piadapp.R;
import com.example.android.piadapp.connection.HttpManager;
import com.example.android.piadapp.data.Piadineria;
import com.example.android.piadapp.parser.PiadineriaJSONParser;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.List;

public class Mappa extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    List<Piadineria> piadineriaList;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappa);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();

        MapStateManager mgr = new MapStateManager(this);
        CameraPosition position = mgr.getSavedCameraPosition();

        if(position != null){
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            mMap.moveCamera(update);
        }

    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();


            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    protected  boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private void updateDisplay() {

      /*  {
          PiadaArrayAdapter adapter = new PiadaArrayAdapter(this, R.layout.piada_listitem, piadaList); */


        if(piadineriaList != null) {



           /* if(marker != null) {
                marker.remove();
            }*/

            for(Piadineria locale : piadineriaList) {
                marker = mMap.addMarker(new MarkerOptions().position(
                        new LatLng(locale.getLat(), locale.getLng()))
                        .title(locale.getNomePiadineria())
                        .snippet(locale.getDettagli())
                );

            }




        }
    }

    private void setUpMap() {

        if(isOnline()) {
            requestData("http://www.storialab.it/PiadApp/piadinerie.json");
        } else {
            Toast.makeText(this, "Il Network non è disponibile", Toast.LENGTH_LONG).show();
        }

        /*mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {

                    return null;
                }

                @Override
                public  View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_mappa, null);
                    TextView tvLocality = (TextView) findViewById(R.id.tv_locality);
                    TextView tvLat = (TextView) findViewById(R.id.tv_lat);
                    TextView tvLng = (TextView) findViewById(R.id.tv_lng);
                    TextView tvSnippet = (TextView) findViewById(R.id.tv_snippet);
                    ImageView image = (ImageView) findViewById(R.id.imageView1);

                    LatLng ll = marker.getPosition();

                    tvLat.setText("latitude " +ll.latitude);
                    tvLng.setText("latitude " +ll.longitude);
                    tvLocality.setText(marker.getTitle());
                    tvSnippet.setText(marker.getSnippet());

                    return v;
                }
            });*/


       // mMap.addMarker(new MarkerOptions().position(new LatLng(44.494183, 11.343137)).title("Marker"));

        mMap.setMyLocationEnabled(true);
        String locale = "piadinaro Nando";



        MarkerOptions options = new MarkerOptions()
                .title(locale)
                .position(new LatLng(44.493507, 11.339423));
        mMap.addMarker(options);




    }

    @Override
    protected void onStop() {
        super.onStop();
        MapStateManager mgr = new MapStateManager(this);
        mgr.salvaStatoMappa(mMap);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            //updateDisplay("Starting Task");



        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            try {


                piadineriaList = PiadineriaJSONParser.parseFeed(result);

                updateDisplay();

             } catch (JSONException e) {
                e.printStackTrace();
            }






        }


        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }




}
