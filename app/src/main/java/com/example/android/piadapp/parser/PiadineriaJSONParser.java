package com.example.android.piadapp.parser;

import com.example.android.piadapp.data.Piadineria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PiadineriaJSONParser {



    public static List<Piadineria> parseFeed(String content) throws JSONException {

        try {


            JSONArray data = new JSONArray(content);
            List<Piadineria> piadineriaList = new ArrayList<>();

            for (int i= 0; i< data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                Piadineria locale = new Piadineria();
                locale.setLat((float) obj.getDouble("Lat"));
                locale.setLng((float) obj.getDouble("Lng"));
                locale.setNomePiadineria(obj.getString("NomeLocale"));
                locale.setVia(obj.getString("Via"));
                locale.setDettagli(obj.getString("Dettagli"));

                piadineriaList.add(locale);

            }

            return piadineriaList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;

            }
        }


}
