package com.example.android.piadapp.Mappa;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MapStateManager {
    private static final String LATITUDE= "latitude";
    private static final String LONGITUDE= "longitude";

    private static final String PREFS_NAME = "mapStatoCamera";
    private static final String ZOOM = "zoom";
    private static final String BEARING = "bearing";
    private static final String TILT = "tilt";

    private SharedPreferences mapPreferenzaStato;

    public MapStateManager(Context context) {

        mapPreferenzaStato = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void salvaStatoMappa(GoogleMap map) {
        SharedPreferences.Editor editor = mapPreferenzaStato.edit();
        CameraPosition position = map.getCameraPosition();

        editor.putFloat(LATITUDE, (float) position.target.latitude);
        editor.putFloat(LONGITUDE, (float) position.target.longitude);
        editor.putFloat(ZOOM, position.zoom);
        editor.putFloat(BEARING, position.bearing);
        editor.putFloat(TILT, position.tilt);

        editor.commit();

    }

    public CameraPosition getSavedCameraPosition() {
        double latitude = mapPreferenzaStato.getFloat(LATITUDE, 0);
        if(latitude == 0) {
            return null;
        }

        double longitude = mapPreferenzaStato.getFloat(LONGITUDE, 0);

        LatLng target = new LatLng(latitude, longitude);
        float zoom = mapPreferenzaStato.getFloat(ZOOM, 0);
        float bearing = mapPreferenzaStato.getFloat(BEARING, 0);
        float tilt = mapPreferenzaStato.getFloat(TILT, 0);

        CameraPosition position = new CameraPosition(target, zoom, bearing, tilt);
        return position;


    }
}
