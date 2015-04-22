package com.example.android.piadapp.parser;


import com.example.android.piadapp.data.Piada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PiadaJSONParser {

    public static List<Piada> parseFeed(String content) throws JSONException {

        try {
            JSONArray ar = new JSONArray(content);
            List<Piada> piadaList = new ArrayList<>();

            for(int i=0; i< ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                Piada piada = new Piada();
                piada.setIngredientiPiada(obj.getString("Ingredienti"));
                piada.setPrezzo(obj.getDouble("Prezzo"));
                //piada.setRisorseImmagini(obj.getInt("immagine"));
                piada.setIstruzioni(obj.getString("istruzioni"));

                piadaList.add(piada);
             }

            return piadaList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
