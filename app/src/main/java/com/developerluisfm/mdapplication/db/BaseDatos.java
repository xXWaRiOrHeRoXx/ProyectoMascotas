package com.developerluisfm.mdapplication.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by LuisFM on 11/06/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreateTableMascotas = " "+
                "CREATE TABLE "+Constantes.TABLE_MASCOTA+" ( "+
                Constantes.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constantes.TABLE_MASCOTA_NOMBRE+" TEXT, "+
                Constantes.TABLE_MASCOTA_EDAD+" INTEGER, "+
                Constantes.TABLE_MASCOTA_FOTO+" INTEGER "+
                ");";

        String queryCreateTableMascotasLikes = " "+
                " CREATE TABLE "+Constantes.TABLE_MASCOTA_LIKES+" ( "+
                Constantes.TABLE_MASCOTA_LIKES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Constantes.TABLE_MASCOTA_LIKES_MASCOTA_ID+" INTEGER, "+
                Constantes.TABLE_MASCOTA_LIKES_NUMERO+" INTEGER, "+
                "FOREIGN KEY ( "+Constantes.TABLE_MASCOTA_LIKES_MASCOTA_ID+" ) "+
                "REFERENCES "+Constantes.TABLE_MASCOTA+" ( "+Constantes.TABLE_MASCOTA_ID+" ) "+
                ");";

        db.execSQL(queryCreateTableMascotas);
        db.execSQL(queryCreateTableMascotasLikes);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+Constantes.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST "+Constantes.TABLE_MASCOTA_LIKES);
    }

    public ArrayList<Mascota> consultarMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        String selectMascotas = "SELECT * FROM "+Constantes.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor query = db.rawQuery(selectMascotas, null);

        while ( query.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(query.getInt(0));
            mascota.setNombre(query.getString(1));
            mascota.setEdad(query.getInt(2));
            mascota.setFoto(query.getInt(3));
            mascota.setLikes(consultarLikes(mascota));

            mascotas.add(mascota);
        }

        return  mascotas;
    }

    public ArrayList<Mascota> consultarMascotasFavoritas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        String selectMascotas = "SELECT * FROM "+Constantes.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor query = db.rawQuery(selectMascotas, null);

        while ( query.moveToNext()) {
            Mascota mascota = new Mascota();
            mascota.setId(query.getInt(0));
            int like = consultarLikes(mascota);

            if(like > 4) {

                mascota.setNombre(query.getString(1));
                mascota.setEdad(query.getInt(2));
                mascota.setFoto(query.getInt(3));
                mascota.setLikes(like);
                mascotas.add(mascota);
            }

        }

        Collections.sort(mascotas, new MyComparator());

        if(mascotas.size() >4 )
            for (int i=0; i<mascotas.size(); i++){
                if(i > 4){
                    mascotas.remove(i);
                }
            }

        return  mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes.TABLE_MASCOTA,null, contentValues);
        db.close();
    }

    public void insertarMascotaLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constantes.TABLE_MASCOTA_LIKES,null, contentValues);
        db.close();
    }

    public int consultarLikes(Mascota mascota){

        int likes = 0;

        String query = "SELECT COUNT("+Constantes.TABLE_MASCOTA_LIKES_NUMERO+")"+
                " FROM "+ Constantes.TABLE_MASCOTA_LIKES+
                " WHERE "+Constantes.TABLE_MASCOTA_LIKES_MASCOTA_ID +" = "+mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(query, null);

        if(result.moveToNext()){
            likes = result.getInt(0);
        }

        db.close();

        return likes;
    }



    class MyComparator implements Comparator<Mascota> {

        @Override
        public int compare(Mascota o1, Mascota o2) {
            if (o1.getLikes() > o2.getLikes()) {
                return -1;
            } else if (o1.getLikes() < o2.getLikes()) {
                return 1;
            }
            return 0;
        }}


}

