package com.example.android.piadapp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.piadapp.data.Piada;
import com.facebook.FacebookSdk;


public class PiadaFinale extends ActionBarActivity {

    String nomePiada;
    String nomeFormaggio;
    String nomeSalsa;
    String nomeAltro;

    TextView tv_nomePiada;
    TextView formaggio;
    TextView salsa;
    TextView carne;
    TextView salumi;
    TextView altro;
    Piada piada;
    StringBuilder ingredienti;
    String messaggioSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piada_finale);
        FacebookSdk.sdkInitialize(getApplicationContext());
        piada = new Piada();

        tv_nomePiada = (TextView) findViewById(R.id.nomePiada);
        formaggio = (TextView) findViewById(R.id.formaggi);
        salsa = (TextView) findViewById(R.id.salse);
        carne = (TextView) findViewById(R.id.carne);
        salumi = (TextView) findViewById(R.id.salumi);
        altro = (TextView) findViewById(R.id.altro);



        Bundle piada = getIntent().getExtras();



        if(piada != null) {

            tv_nomePiada.setText(piada.getString("NOME_PIADA"));
            formaggio.setText(piada.getString("NOME_FORMAGGIO"));
            salsa.setText(piada.getString("NOME_SALSA"));
            altro.setText(piada.getString("NOME_ALTRO"));
            carne.setText(piada.getString("NOME_CARNE"));
            salumi.setText(piada.getString("NOME_SALUMI"));

            ingredienti = new StringBuilder();
            ingredienti.append(piada.getString("NOME_FORMAGGIO") + ", ")
                       .append(piada.getString("NOME_SALSA") + ", ")
                       .append(piada.getString("NOME_ALTRO") + ", ")
                       .append(piada.getString("NOME_CARNE") + ", ")
                       .append(piada.getString("NOME_SALUMI") + ", ");
        }



        if(savedInstanceState == null) {
            FacebookLoginFragment frag = new FacebookLoginFragment();
            frag.setArguments(piada);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerFinale, frag)
                    .commit();
        }

    }

    public void addPiada(View view) {
     //aggiungi una piada
        ContentValues piada = new ContentValues();
        piada.put(PiadaProvider.NOME, tv_nomePiada.getText().toString());
        piada.put(PiadaProvider.FORMAGGIO, formaggio.getText().toString());
        piada.put(PiadaProvider.SALSA, salsa.getText().toString());
        piada.put(PiadaProvider.ALTRO, altro.getText().toString());
        piada.put(PiadaProvider.CARNE, carne.getText().toString());
        piada.put(PiadaProvider.SALUMI, salumi.getText().toString());

        /*if(PiadaProvider.PREZZO == 0) {
            String prezzoNullo = );
            piada.put(PiadaProvider.PREZZO, 0.0);
        }*/

        if(PiadaProvider.DETTAGLI == "") {
            piada.put(PiadaProvider.DETTAGLI, "Dettagli non disponibili.");
        }

        Uri uri = getContentResolver().insert(PiadaProvider.CONTENT_URI, piada);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();





    }

    public void onClick(View v) {
        messaggioSMS = "Ehi, ti va di mangiare insieme una piada con "+ ingredienti +" che ho appena fatto?";
        spedisciSMS("5556", messaggioSMS);
    }


    public void spedisciSMS(String phoneNumber, String message) {

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piada_finale, menu);
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
