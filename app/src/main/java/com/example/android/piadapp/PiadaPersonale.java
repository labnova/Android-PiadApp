package com.example.android.piadapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class PiadaPersonale extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    LinearLayout radice;


   /* LinearLayout salumi;
        TextView tvSalumi;
        AutoCompleteTextView edSalumi;
    LinearLayout carne;
        TextView tvCarne;
        AutoCompleteTextView edCarne;
    LinearLayout altro;
        TextView tvAltro;
*/
    TextView tvCarne;
    TextView tvSalumi;


    CheckBox vegetariano;
    AutoCompleteTextView formaggi;
    AutoCompleteTextView edAltro;
    AutoCompleteTextView edSalse;
    AutoCompleteTextView edCarne;
    AutoCompleteTextView edSalumi;
    EditText nomePiada;
    Button crea;

   String[] tipoFormaggi = { "Stracchino", "Squaquerone", "Edamer", "Scamorza", "Pecorino", "Grana", "Philadelphia" };
   String[] tipoSalumi = {"Prosciutto Crudo", "Brie", "Bresaola","Prosciutto Cotto", "Speck", "Mortadella", "Salame", "Salame Piccante"};
   String[] tipoCarne = {"Salsiccia", "Arrosto di Tacchino", "Wurstel"};
   String[] tipoAltro = {"Patate", "Cipolla", "Pancetta", "Tonno", "Funghi", "Rucola", "Noci", "Salmone" };
   String[] tipoSalse = {"Maionese", "Pate d'Olive", "Salsa Rosa" };

    Spinner spinnerSalse;
    Spinner spinnerAltro;
    Spinner spinnerFormaggi;
    Spinner spSalumi;
    Spinner spCarne;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piada_personale);

        tvCarne = (TextView) findViewById(R.id.tvCarne);
        tvSalumi = (TextView) findViewById(R.id.tvSalumi);

        radice = (LinearLayout) findViewById(R.id.radice);
        formaggi = (AutoCompleteTextView) findViewById(R.id.formaggi);
        edAltro = (AutoCompleteTextView) findViewById(R.id.altriIngredienti);
        edSalse = (AutoCompleteTextView) findViewById(R.id.edSalse);
        edCarne = (AutoCompleteTextView) findViewById(R.id.edCarne);
        edSalumi = (AutoCompleteTextView) findViewById(R.id.edSalumi);
        nomePiada = (EditText) findViewById(R.id.nomePiada);
        crea = (Button) findViewById(R.id.creaPiada);

        spinnerSalse = (Spinner) findViewById(R.id.spinnerSalse);
        spinnerAltro = (Spinner) findViewById(R.id.spinnerAltro);
        spinnerFormaggi = (Spinner) findViewById(R.id.spinnerFormaggi);
        spCarne = (Spinner) findViewById(R.id.spinnerCarne);
        spSalumi = (Spinner) findViewById(R.id.spinnerSalumi);

        spinnerSalse.setOnItemSelectedListener(this);
        spinnerAltro.setOnItemSelectedListener(this);
        spinnerFormaggi.setOnItemSelectedListener(this);
        spCarne.setOnItemSelectedListener(this);
        spSalumi.setOnItemSelectedListener(this);



        ArrayAdapter<CharSequence> adapterSalse = ArrayAdapter.createFromResource(this,
                                    R.array.spinnerSalse, R.layout.support_simple_spinner_dropdown_item);

        spinnerSalse.setAdapter(adapterSalse);


        ArrayAdapter<CharSequence> adapterAltro = ArrayAdapter.createFromResource(this,
                                                                     R.array.spinnerAltro,
                                                                    R.layout.support_simple_spinner_dropdown_item);

        spinnerAltro.setAdapter(adapterAltro);

        ArrayAdapter<CharSequence> adapterFormaggi = ArrayAdapter.createFromResource(this,
                R.array.spinnerFormaggi, R.layout.support_simple_spinner_dropdown_item);
        spinnerFormaggi.setAdapter(adapterFormaggi);

        ArrayAdapter<CharSequence> adapterCarne = ArrayAdapter.createFromResource(this,
                R.array.spinnerCarne, R.layout.support_simple_spinner_dropdown_item);
        spCarne.setAdapter(adapterCarne);

        ArrayAdapter<CharSequence> adapterSalumi = ArrayAdapter.createFromResource(this,
                R.array.spinnerSalumi, R.layout.support_simple_spinner_dropdown_item);
        spSalumi.setAdapter(adapterSalumi);

        crea.setOnClickListener(this);

        formaggi.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_formaggi, tipoFormaggi));
        edAltro.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_formaggi, tipoAltro));
        edSalse.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_formaggi, tipoSalse));
        edCarne.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_formaggi, tipoCarne));
        edSalumi.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_formaggi, tipoSalumi));

    vegetariano = (CheckBox) findViewById(R.id.vegetariano);




       /* if(vegetariano.isChecked()) {
            salumi.removeView(remove);

        }*/




      /*  salumi = new LinearLayout(this);
        LinearLayout.LayoutParams paramsSalumi = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        salumi.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams pS = new LinearLayout.LayoutParams(
                150,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );*/




       /* tvSalumi= new TextView(this);
        tvSalumi.setLayoutParams(pS);
        edSalumi = new AutoCompleteTextView(this);
        spSalumi = new Spinner(this);
        ArrayAdapter<CharSequence> adapterSalumi = ArrayAdapter.createFromResource(this,
                R.array.spinnerCarne, R.layout.support_simple_spinner_dropdown_item);
        spSalumi.setAdapter(adapterSalumi);

        spSalumi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvSalumi.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSalumi.setLayoutParams(pS);

        edSalumi.setLayoutParams(pS);
        tvSalumi.setText("Salumi");
        salumi.addView(tvSalumi);
        salumi.addView(spSalumi);
        salumi.addView(edSalumi);

        carne = new LinearLayout(this);
        LinearLayout.LayoutParams paramsCarne = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        carne.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams pC = new LinearLayout.LayoutParams(
                150,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ); */




        /*tvCarne= new TextView(this);
        tvCarne.setLayoutParams(pC);
        edCarne = new AutoCompleteTextView(this);
        spCarne = new Spinner(this);
        ArrayAdapter<CharSequence> adapterCarne = ArrayAdapter.createFromResource(this,
                R.array.spinnerCarne, R.layout.support_simple_spinner_dropdown_item);
        spCarne.setAdapter(adapterCarne);

        spCarne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edCarne.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spCarne.setLayoutParams(pC);

        edCarne.setLayoutParams(pC);
        tvCarne.setText("Carne");
        carne.addView(tvCarne);
        carne.addView(spCarne);
        carne.addView(edCarne); */


        //radice.addView(salumi, paramsSalumi);
       // this.addContentView(carne, paramsCarne);


    }

    public void onVegClicked(View view) {

        if(vegetariano.isChecked()) {
            edCarne.setVisibility(View.GONE);
            spCarne.setVisibility(View.GONE);
            tvCarne.setVisibility(View.GONE);
            edSalumi.setVisibility(View.GONE);
            spSalumi.setVisibility(View.GONE);
            tvSalumi.setVisibility(View.GONE);
            Toast.makeText(this, "vegetariano clicked!", Toast.LENGTH_SHORT).show();
        } else {
            edCarne.setVisibility(View.VISIBLE);
            spCarne.setVisibility(View.VISIBLE);
            tvCarne.setVisibility(View.VISIBLE);
            edSalumi.setVisibility(View.VISIBLE);
            spSalumi.setVisibility(View.VISIBLE);
            tvSalumi.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_piada_personale, menu);
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

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, PiadaFinale.class);

        Bundle b = new Bundle();
        b.putString("NOME_PIADA", nomePiada.getText().toString());
        b.putString("NOME_FORMAGGIO", formaggi.getText().toString());
        b.putString("NOME_SALSA", edSalse.getText().toString());
        b.putString("NOME_ALTRO", edAltro.getText().toString());
        b.putString("NOME_CARNE", edCarne.getText().toString());
        b.putString("NOME_SALUMI", edSalumi.getText().toString());

        i.putExtras(b);
        startActivity(i);



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSalse:
                edSalse.setText(parent.getItemAtPosition(position).toString());
            break;
            case R.id.spinnerAltro:
                edAltro.setText(parent.getItemAtPosition(position).toString());
            break;

            case R.id.spinnerFormaggi:
                formaggi.setText(parent.getItemAtPosition(position).toString());
                break;
            case R.id.spinnerCarne:
                edCarne.setText(parent.getItemAtPosition(position).toString());
                break;

            case R.id.spinnerSalumi:
               edSalumi.setText(parent.getItemAtPosition(position).toString());
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
