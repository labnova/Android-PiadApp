package com.example.android.piadapp.Lista;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.piadapp.R;
import com.example.android.piadapp.data.Piada;
import com.example.android.piadapp.data.PiadaArrayAdapter;
import com.example.android.piadapp.data.PiadaData;

import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyFragment extends ListFragment {

    List<Piada> piadaList = new PiadaData().getPiada();

    private Callbacks activity;

    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PiadaArrayAdapter adapter = new PiadaArrayAdapter(getActivity(), R.layout.piada_listitem, piadaList);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_fragment, container, false);
        return rootView;
    }

    public interface Callbacks {

        public void onItemSelected(Piada piada);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Piada piada = piadaList.get(position);
        activity.onItemSelected(piada);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (Callbacks) activity;
    }
}
