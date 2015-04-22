package com.example.android.piadapp.data;


public class Piadineria {

    public static final String NOME_PIADINERIA = "nomePiadineria";
    public static final String VIA = "via";
    public static final Float LATITUDE = null;
    public static final Float LONGITUDE = null;
    public static final String DETTAGLI = "Dettagli";

    private String nomePiadineria;
    private String via;
    private String dettagli;
    private Float lat;
    private Float lng;

    public Piadineria(String nomePiadineria, String via, String dettagli, Float lat, Float lng) {
        this.nomePiadineria = nomePiadineria;
        this.via = via;
        this.dettagli = dettagli;
        this.lat = lat;
        this.lng = lng;
    }

    public Piadineria() {

    }

    public String getNomePiadineria() {
        return nomePiadineria;
    }

    public String getVia() {
        return via;
    }

    public String getDettagli() {
        return dettagli;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setNomePiadineria(String nomePiadineria) {
        this.nomePiadineria = nomePiadineria;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
