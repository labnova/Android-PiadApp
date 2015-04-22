package com.example.android.piadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.piadapp.Mappa.Mappa;
import com.example.android.piadapp.connection.HttpManager;
import com.example.android.piadapp.data.Piada;
import com.example.android.piadapp.parser.PiadaJSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class InizioApp extends Activity implements View.OnClickListener {

    Button amico;
    Button lista;
    Button mappa;
    Button preferiti;
    Button personale;

    TextView output;
    ProgressBar progressBar;
    List<MyTask> tasks;

    List<Piada> piadaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inizio_app);

        amico = (Button) findViewById(R.id.amico);
        lista = (Button) findViewById(R.id.lista);
        mappa = (Button) findViewById(R.id.mappa);
        preferiti = (Button) findViewById(R.id.preferiti);
        personale = (Button) findViewById(R.id.personale);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.INVISIBLE);

      //  amico.setOnClickListener(this);
        lista.setOnClickListener(this);
        mappa.setOnClickListener(this);
        preferiti.setOnClickListener(this);
        personale.setOnClickListener(this);

        //output = (TextView) findViewById(R.id.output);
        //output.setMovementMethod(new ScrollingMovementMethod());

        tasks = new ArrayList<MyTask>();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inizio_app, menu);
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

        if(id == R.id.action_do_task) {
            if(isOnline()) {
                requestData("http://www.storialab.it/PiadApp/piade.json");
            } else {
                Toast.makeText(this, "Il Network non è disponibile", Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private void updateDisplay() {

      /*  {
          PiadaArrayAdapter adapter = new PiadaArrayAdapter(this, R.layout.piada_listitem, piadaList); */


        if(piadaList != null) {


            for(Piada piada : piadaList) {
               output.append(piada.getIngredientiPiada() + "\n");
               output.append(piada.getPrezzo() + "\n");
               output.append(piada.getIstruzioni() + "\n");
               output.append(piada.getRisorseImmagini() + "\n");

           }
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.amico:
                //intent = new Intent(this, FragmentLogin.class);
                //startActivity(intent);
                break;
            case R.id.mappa:
                intent = new Intent(this, Mappa.class);
                startActivity(intent);
                break;
            case R.id.lista:
                intent = new Intent(this, MainActivityLista.class);
                startActivity(intent);
                break;
            case R.id.personale:
                intent = new Intent(this, PiadaPersonale.class);
                startActivity(intent);
                break;
           case R.id.preferiti:
                intent = new Intent(this, Preferiti.class);
                startActivity(intent);
                break;


        }

    }

    protected  boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            //updateDisplay("Starting Task");

            if(tasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }

            tasks.add(this);

        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                piadaList = PiadaJSONParser.parseFeed(result);
                updateDisplay();
               // Toast.makeText(getBaseContext(), "JSON arrivato", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }



            tasks.remove(this);
            if(tasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }


        }


        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }
}
