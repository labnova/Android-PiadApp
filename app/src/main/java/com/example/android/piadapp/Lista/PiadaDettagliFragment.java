package com.example.android.piadapp.Lista;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.piadapp.PiadaProvider;
import com.example.android.piadapp.R;
import com.example.android.piadapp.data.Piada;

import java.text.NumberFormat;


public class PiadaDettagliFragment extends Fragment implements View.OnClickListener {

    Piada piada;
    //TextView tvName;
    Button salva;
    TextView tvInstructions;

    public PiadaDettagliFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(piada.PIADA_INGREDIENTI)) {
            piada = new Piada(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //caricare il layout
        View view = inflater.inflate(R.layout.piada_dettaglio_fragment, container, false);

        if(piada != null) {
            //visualizzare valori e immagini
            TextView tvName = (TextView) view.findViewById(R.id.piadaName);
            tvName.setText(piada.getIngredientiPiada());

            tvInstructions = (TextView) view.findViewById(R.id.piadaIstruzioni);
            tvInstructions.setText(piada.getIstruzioni());

            NumberFormat fm = NumberFormat.getCurrencyInstance();
            TextView tvPrice = (TextView) view.findViewById(R.id.piadaPrezzo);
            tvPrice.setText(fm.format(piada.getPrezzo()));

            ImageView ivPicture= (ImageView) view.findViewById(R.id.piadaImage);
            ivPicture.setImageResource(piada.getRisorseImmagini());

            salva = (Button) view.findViewById(R.id.salva);
            salva.setOnClickListener(this);

        }

        return view;

    }

    public void addPiada() {
        //aggiungi una piada
        ContentValues piada = new ContentValues();
        piada.put(PiadaProvider.NOME, tvInstructions.getText().toString());

        piada.put(PiadaProvider.FORMAGGIO, "");
        piada.put(PiadaProvider.SALSA, "");
        piada.put(PiadaProvider.ALTRO, "");
        piada.put(PiadaProvider.CARNE, "");
        piada.put(PiadaProvider.SALUMI, "");





        if(PiadaProvider.DETTAGLI == "") {
            piada.put(PiadaProvider.DETTAGLI, "Dettagli non disponibili.");
        }

        Uri uri = getActivity().getContentResolver().insert(PiadaProvider.CONTENT_URI, piada);
        Toast.makeText(getActivity().getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salva:
                addPiada();
            break;
            default: return;
        }
    }
}
