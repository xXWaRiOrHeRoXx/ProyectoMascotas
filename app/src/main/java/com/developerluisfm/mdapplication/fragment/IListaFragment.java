package com.developerluisfm.mdapplication.fragment;

import com.developerluisfm.mdapplication.adapter.MascotaAdapter;
import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LuisFM on 11/06/16.
 */
public interface IListaFragment {

    public void crearLineraLayout();

    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void iniciarAdaptador(MascotaAdapter adapter);

    public void generarGridLayout();

}
