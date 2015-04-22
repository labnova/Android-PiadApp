package com.example.android.piadapp.data;


import android.os.Bundle;

public class Piada {

    //costanti
    public static final String PIADA_INGREDIENTI = "ingredientiPiada";
    public static final String IMMAGINE = "risorseImmagini";
    public static final String PREZZO = "prezzo";
    public static final String ISTRUZIONI = "istruzioni";
    public static final String NOME = "nome";

    //campi privati
    public String ingredientiPiada;
    private StringBuilder iP;
    private int risorseImmagini;
    private String istruzioni;
    private double prezzo;
    private String nome;
    String salumi;
    String carne;
    String altro;
    String salsa;
    String formaggio;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Piada() {

    }



    public StringBuilder getiP() {
        return iP;
    }

    public void setiP(StringBuilder iP) {
        this.iP = iP;
    }

    //getters e setters
    public String getIngredientiPiada() { return ingredientiPiada; }

    public void setIngredientiPiada(String ingredientiPiada) {this.ingredientiPiada = ingredientiPiada; }

    public int getRisorseImmagini() {return risorseImmagini; }

    public void setRisorseImmagini(int risorseImmagini) {this.risorseImmagini = risorseImmagini; }

    public String getIstruzioni() {return istruzioni; }

    public void setIstruzioni(String istruzioni) { this.istruzioni = istruzioni; }

    public double getPrezzo() {return prezzo; }

    public void setPrezzo(double prezzo) {this.prezzo = prezzo; }

   /*

    " (_id integer primary key autoincrement, "
           + "nome text, " +
            "salumi text," +
            "carne text," +
            "altro text," +
            "salsa text," +
            "formaggio, " +
            //" prezzo double null," +
            "dettagli text);";


     */

    public Piada(String nome, int risorseImmagini, double prezzo, String istruzioni) {
        this.nome = nome;
        this.risorseImmagini = risorseImmagini;
        this.prezzo = prezzo;
        this.istruzioni = istruzioni;
    }

    public Piada(String nome, int risorseImmagini, double prezzo, String salumi, String carne, String altro, String salsa, String formaggio,String istruzioni) {
        this.nome = nome;
        this.risorseImmagini = risorseImmagini;
        this.istruzioni = istruzioni;
        this.prezzo = prezzo;
        this.salumi = salumi;
        this.carne = carne;
        this.altro = altro;
        this.salsa = salsa;
        this.formaggio = formaggio;
    }

    public Piada(String nome, StringBuilder iP, int risorseImmagini) {
        this.nome = nome;
        this.iP = iP;
        this.risorseImmagini = risorseImmagini;
    }

    //creato da un bundle
    public Piada(Bundle b) {
        if(b != null) {
            this.ingredientiPiada = b.getString(PIADA_INGREDIENTI);
            this.risorseImmagini = b.getInt(IMMAGINE);
            this.prezzo = b.getDouble(PREZZO);
            this.istruzioni = b.getString(ISTRUZIONI);
        }
    }

    //Pacchetto dati da trasferire tra le varie activity
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(PIADA_INGREDIENTI, this.ingredientiPiada);
        b.putInt(IMMAGINE, this.risorseImmagini);
        b.putDouble(PREZZO, this.prezzo);
        b.putString(ISTRUZIONI, this.istruzioni);
        return b;
    }
}
