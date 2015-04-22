package com.example.android.piadapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class PiadaProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.android.piadapp.Piade";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/piade");

    public static final String _ID = "_id";
    public static final String INGREDIENTI = "ingredienti";
    public static final String NOME = "nome";
    public static final String FORMAGGIO = "formaggio";
    public static final String SALSA = "salsa";
    public static final String CARNE = "carne";
    public static final String SALUMI = "salumi";
    public static final String ALTRO = "altro";
    public static final String DETTAGLI = "dettagli";
    public static final Double PREZZO = 0.0;

    static final int PIADE = 1;
    static final int PIADA_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PROVIDER_NAME, "piade", PIADE);
        uriMatcher.addURI(PROVIDER_NAME, "piade/#", PIADA_ID);
    }

    //----PER USO DATABASE---
    SQLiteDatabase PiadeDB;
    static final String DATABASE_NAME = "Piade";
    static final String DATABASE_TABLE = "piade";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table " + DATABASE_TABLE
+ " (_id integer primary key autoincrement, "
           + "nome text, " +
            "salumi text," +
            "carne text," +
            "altro text," +
            "salsa text," +
            "formaggio text, " +
            //" prezzo double null," +
            "dettagli text);";



    /*
     *   tv_nomePiada.setText(piada.getString("NOME_PIADA"));
            formaggio.setText(piada.getString("NOME_FORMAGGIO"));
            salsa.setText(piada.getString("NOME_SALSA"));
            altro.setText(piada.getString("NOME_ALTRO"));
            carne.setText(piada.getString("NOME_CARNE"));
            salumi.setText(piada.getString("NOME_SALUMI"));
      *
      * */

    private static class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          //  Log.w("Content provides database", "Upgrade  da " +oldVersion+ " a " +newVersion);
            db.execSQL("DROP TABLE IF EXIST piade");
            onCreate(db);
        }
    }



    public PiadaProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {


        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PIADA_ID:
                String id = uri.getPathSegments().get(1);
                count = PiadeDB.delete(
                        DATABASE_TABLE,
                        _ID + " = " + id +  (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                 break;
            case PIADE:
                count = PiadeDB.delete(DATABASE_TABLE, selection, selectionArgs);
            break;

            default:  throw new UnsupportedOperationException("URI sconosciuto:" +uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PIADE:
                return "vnd.android.cursor.dir/com.example.android.piadapp";

            case PIADA_ID:
                return "vnd.android.cursor.item/com.example.android.piadapp";

            default:
                throw new UnsupportedOperationException("Uri non supportato: " +uri);
        }


    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //aggiungere una nuova piada
        long rowID = PiadeDB.insert(DATABASE_TABLE, "", values);

        //se è stata aggiunta correttamente
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);

            return _uri;
        }

        throw new UnsupportedOperationException("Fallito inserimento alla riga " + uri);
    }

    @Override
    public boolean onCreate() {
        //Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        PiadeDB = dbHelper.getWritableDatabase();
        return (PiadeDB == null) ? false : true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(DATABASE_TABLE);

        if(uriMatcher.match(uri) == PIADA_ID) {
            //SE PRENDI UNA PIADA IN PARTICOLARE
            sqlBuilder.appendWhere(
                    _ID + " = " + uri.getPathSegments().get(1));

        }

            if(sortOrder == null || sortOrder == "") {
                sortOrder =  NOME;
            }

            Cursor c = sqlBuilder.query(PiadeDB, projection, selection, selectionArgs, null, null, sortOrder);

            //registrati per guardare un content URI quando effettua un cambiamento
            c.setNotificationUri(getContext().getContentResolver(), uri);
           // c.close();

            return c;



    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PIADE:
                count = PiadeDB.update(DATABASE_TABLE, values, selection, selectionArgs);
            break;
            case PIADA_ID:
                count = PiadeDB.update(DATABASE_TABLE, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
            break;

            default:  throw new UnsupportedOperationException("URI sconosciuto:" +uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
