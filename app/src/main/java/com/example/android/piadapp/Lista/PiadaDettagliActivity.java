package com.example.android.piadapp.Lista;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.piadapp.MainActivityLista;
import com.example.android.piadapp.R;


public class PiadaDettagliActivity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piada_dettagli);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null) {
            //creare il fragment, settare i suoi argomenti, aggiungere il container per i dettagli
            PiadaDettagliFragment frag = new PiadaDettagliFragment();

            Bundle b = getIntent().getBundleExtra(MainActivityLista.PIADA_BUNDLE);
            frag.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.dettagliContainer, frag)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piada_dettagli, menu);
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
            return true;
        }

        if (id == R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
