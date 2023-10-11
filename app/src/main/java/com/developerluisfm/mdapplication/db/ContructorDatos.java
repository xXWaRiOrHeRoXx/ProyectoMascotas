package com.developerluisfm.mdapplication.db;

import android.content.ContentValues;
import android.content.Context;

import com.developerluisfm.mdapplication.R;
import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LuisFM on 11/06/16.
 */
public class ContructorDatos {

    private Context context;

    public ContructorDatos(Context context){

        this.context = context;

    }

    public ArrayList<Mascota> obtenerMascotas(){

        /*Mascota mascota1 = new Mascota(1,"Tofi",1, R.drawable.p1,0);
        Mascota mascota2 = new Mascota(2,"Oreo",3,R.drawable.p2,2);
        Mascota mascota3 = new Mascota(3,"Black",2,R.drawable.p3,1);
        Mascota mascota4 = new Mascota(4,"Nii",6,R.drawable.p4,3);
        Mascota mascota5 = new Mascota(5,"Sisi",4,R.drawable.p5,4);

        ArrayList mascotas = new ArrayList<>();

        mascotas.add(mascota1);
        mascotas.add(mascota2);
        mascotas.add(mascota3);
        mascotas.add(mascota4);
        mascotas.add(mascota5);*/
        insertarContactos(new BaseDatos(context));
        return new BaseDatos(context).consultarMascotas();
    }

    public int obtenerLikes(Mascota mascota){

        return  new BaseDatos(context).consultarLikes(mascota);
    }

    public void insertarContactos(BaseDatos db){

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Tofi");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 1);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p1);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Oreo");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 2);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p2);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Black");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 1);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p3);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Black");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 1);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p3);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Red");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 2);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p4);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Sisi");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 2);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.p5);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Lu");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 2);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.g1);
        db.insertarMascota(contentValues);

        contentValues.put(Constantes.TABLE_MASCOTA_NOMBRE, "Ninu");
        contentValues.put(Constantes.TABLE_MASCOTA_EDAD, 2);
        contentValues.put(Constantes.TABLE_MASCOTA_FOTO, R.drawable.g2);
        db.insertarMascota(contentValues);

        db.close();
    }

    public void insertarContactoLike(Mascota mascota){

        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constantes.TABLE_MASCOTA_LIKES_MASCOTA_ID, mascota.getId());
        contentValues.put(Constantes.TABLE_MASCOTA_LIKES_NUMERO, 1);

        db.insertarMascotaLike(contentValues);
    }

}
