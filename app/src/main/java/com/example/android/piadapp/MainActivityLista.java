package com.example.android.piadapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.piadapp.Lista.MyDialogFragment;
import com.example.android.piadapp.Lista.MyFragment;
import com.example.android.piadapp.Lista.MyPrefsActivity;
import com.example.android.piadapp.Lista.PiadaDettagliActivity;
import com.example.android.piadapp.data.Piada;


public class MainActivityLista extends ActionBarActivity
    implements MyFragment.Callbacks
{

    public static final String PIADA_BUNDLE = "PIADA_BUNDLE";
    private static final int REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/


        MyFragment frag = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.myContainer, frag)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent();
            intent.setClass(this, MyPrefsActivity.class);
            startActivityForResult(intent, 1002);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Piada piada) {
        Bundle b = piada.toBundle();
        Intent intent = new Intent(this, PiadaDettagliActivity.class);
        intent.putExtra(PIADA_BUNDLE, b);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1002) {
            SharedPreferences myPrefs =
                    PreferenceManager.getDefaultSharedPreferences(this);
            Boolean pref1 = myPrefs.getBoolean("pref1", false);
            //Toast.makeText(this, "Preference " +pref1, Toast.LENGTH_SHORT).show();

            MyDialogFragment dialog = new MyDialogFragment();
            Bundle b = new Bundle();
            b.putString("Message", "Preference " +pref1 );
            dialog.setArguments(b);

            dialog.show(getFragmentManager(), "MyDialog");
        }
    }


    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.my_fragment, container, false);
            return rootView;
        }
    }*/
}
