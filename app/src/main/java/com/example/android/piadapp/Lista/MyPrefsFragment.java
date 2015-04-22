package com.example.android.piadapp.Lista;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.android.piadapp.R;


public class MyPrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
