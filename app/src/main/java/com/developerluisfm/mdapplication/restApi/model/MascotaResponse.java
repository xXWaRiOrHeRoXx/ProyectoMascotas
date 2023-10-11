package com.developerluisfm.mdapplication.restApi.model;

import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class MascotaResponse {

    public ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getContactos() {
        return mascotas;
    }

    public void setContactos(ArrayList<Mascota> contactos) {
        this.mascotas = contactos;
    }
}
