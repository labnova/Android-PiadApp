package com.example.android.piadapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.piadapp.data.Piada;
import com.example.android.piadapp.data.PiadaArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class Preferiti extends ListActivity {

    List<Piada> piadaData = new ArrayList<>();
    StringBuilder nomeIngredienti = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti);

        Uri allPiade = Uri.parse("content://com.example.android.piadapp.Piade/piade");
        Cursor c;

        if (Build.VERSION.SDK_INT < 11) {
            c = managedQuery(allPiade, null, null, null, null);
        } else {
            CursorLoader cursorLoader = new CursorLoader(this, allPiade, null, null, null, null);
            c = cursorLoader.loadInBackground();
        }

        if (c.moveToFirst()) {
            do {

              Toast.makeText(this, c.getString(c.getColumnIndex(PiadaProvider.NOME)) + ": " +
                      c.getString(c.getColumnIndex(PiadaProvider.CARNE)) + ", " +
                        c.getString(c.getColumnIndex(PiadaProvider.FORMAGGIO)) + ", " +
                        c.getString(c.getColumnIndex(PiadaProvider.SALSA)) + ", " +
                        c.getString(c.getColumnIndex(PiadaProvider.ALTRO)), Toast.LENGTH_LONG ).show();




            } while (c.moveToNext());

           // c.close();


        // piadaData = ;

        if(piadaData == null) {
            Toast.makeText(this, "Non ci sono piade preferite!", Toast.LENGTH_SHORT).show();
        }



        PiadaArrayAdapter adapter = new PiadaArrayAdapter(this,R.layout.piada_preferiti_list_item, ritrovaPiade());
        setListAdapter(adapter);
        }

    }

    private void eliminaPiada(View view) {
        ContentValues piada = new ContentValues();
        piada.remove(view.toString());
    }


    public List<Piada> ritrovaPiade() {
        Uri allPiade = Uri.parse("content://com.example.android.piadapp.Piade/piade");
        Cursor c;

        if (Build.VERSION.SDK_INT < 11) {
            c = managedQuery(allPiade, null, null, null, null);
        } else {
            CursorLoader cursorLoader = new CursorLoader(this, allPiade, null, null, null, null);
            c = cursorLoader.loadInBackground();
        }

        if(c.moveToFirst()) {
           do {



            nomeIngredienti =  ingredienti(c.getString(c.getColumnIndex(PiadaProvider.SALUMI)).toString(),
                    c.getString(c.getColumnIndex(PiadaProvider.CARNE)).toString(),
                    c.getString(c.getColumnIndex(PiadaProvider.FORMAGGIO)).toString(),
                    c.getString(c.getColumnIndex(PiadaProvider.SALSA)).toString(),
                    c.getString(c.getColumnIndex(PiadaProvider.ALTRO)).toString() );


            piadaData.add(new Piada(
                    c.getString(c.getColumnIndex(PiadaProvider.NOME)).toString(),
                    nomeIngredienti,
                    0 ));

           } while (c.moveToNext());
        }

        //c.close();

        return piadaData;

    }



    private StringBuilder ingredienti(String salumi, String carne, String formaggio, String salsa, String altro) {
        StringBuilder nomeIngr= new StringBuilder();
        nomeIngr.append(salumi + ", ").append(carne+ ", ").append(formaggio+ ", ")
                        .append(salsa + ", ").append(altro+ ", ");

        return nomeIngr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferiti, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
