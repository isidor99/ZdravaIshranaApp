package com.example.saki99.zdravaishranaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saki99 on 20.2.2018..
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ZdravaIshrana.db";

    //tabela recepata
    private static final class TabelaRecepti implements BaseColumns {
        public static final String TABELA_IME = "favoriti_tabela";
        public static final String NAZIV = "naziv";
        public static final String OPIS = "opis";
        public static final String PROTEINI = "proteini";
        public static final String UGLJENI_HIDRATI = "ugljeni_hidrati";
        public static final String MASTI = "masti";
        public static final String OMILJENI = "omiljeni";
        public static final String SLIKA = "slika";
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_QUERY = "CREATE TABLE " + TabelaRecepti.TABELA_IME + " ("
                                + TabelaRecepti._ID + " INTEGER PRIMARY KEY, "
                                + TabelaRecepti.NAZIV + " TEXT, "
                                + TabelaRecepti.OPIS + " TEXT, "
                                + TabelaRecepti.PROTEINI + " TEXT, "
                                + TabelaRecepti.UGLJENI_HIDRATI + " TEXT, "
                                + TabelaRecepti.MASTI + " TEXT, "
                                + TabelaRecepti.SLIKA + " BLOB, "
                                + TabelaRecepti.OMILJENI + " INTEGER)";

        sqLiteDatabase.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TabelaRecepti.TABELA_IME;

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //upisi recept u bazu
    public void addRecept(Recept recept) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaRecepti.NAZIV, recept.getNaziv());
        values.put(TabelaRecepti.OPIS, recept.getOpis());
        values.put(TabelaRecepti.PROTEINI, recept.getProteini());
        values.put(TabelaRecepti.UGLJENI_HIDRATI, recept.getUgljenHidrati());
        values.put(TabelaRecepti.MASTI, recept.getMasti());
        values.put(TabelaRecepti.SLIKA, getBytes(recept.getSlika()));
        values.put(TabelaRecepti.OMILJENI, (recept.isOmiljeni()) ? 1 : 0);


        db.insert(TabelaRecepti.TABELA_IME, null, values);
        db.close();
    }

    //procitaj sve recepte iz baze
    public List<Recept> getAllRecepti() {

        List<Recept> recepti = new ArrayList<>();

        String query = "SELECT * FROM " + TabelaRecepti.TABELA_IME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                recepti.add(procitajRecept(cursor));

            } while (cursor.moveToNext());
        }

        return recepti;
    }

    //dodaj recept u omiljene
    public void addOmiljnei(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaRecepti.OMILJENI, 1);

        db.update(TabelaRecepti.TABELA_IME, values, "_id = " + id, null);
        db.close();
    }

    //ukloni recept iz omiljenih
    public void removeOmiljeni(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TabelaRecepti.OMILJENI, 0);

        db.update(TabelaRecepti.TABELA_IME, values, "_id = " + id, null);
        db.close();
    }

    //procitaj sve omiljene
    public List<Recept> getOmiljeniRecepti() {

        ArrayList<Recept> recepti = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String QUERY = "SELECT * FROM " + TabelaRecepti.TABELA_IME +
                        " WHERE " + TabelaRecepti.OMILJENI + " = 1";

        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor.moveToFirst()) {
            do {

                recepti.add(procitajRecept(cursor));

            } while (cursor.moveToNext());
        }

        return recepti;
    }

    // convert bitmap to byte array
    private static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    private static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    // funkcija za citanje recepta
    private Recept procitajRecept(Cursor cursor) {
        Recept recept = new Recept();

        recept.setId(cursor.getInt(0));
        recept.setNaziv(cursor.getString(1));
        recept.setOpis(cursor.getString(2));
        recept.setProteini(cursor.getString(3));
        recept.setUgljenHidrati(cursor.getString(4));
        recept.setMasti(cursor.getString(5));
        recept.setSlika(getImage(cursor.getBlob(6)));

        if (cursor.getInt(7) == 0)
            recept.setOmiljeni(false);

        else if (cursor.getInt(7) == 1)
            recept.setOmiljeni(true);

        return recept;
    }
}
