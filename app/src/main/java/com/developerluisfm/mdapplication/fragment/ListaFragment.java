package com.developerluisfm.mdapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developerluisfm.mdapplication.R;
import com.developerluisfm.mdapplication.adapter.MascotaAdapter;
import com.developerluisfm.mdapplication.pojo.Mascota;
import com.developerluisfm.mdapplication.present.ListaPresent;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment implements IListaFragment {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;
    Context context;
    ListaPresent listaPresent;


    public ListaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        context = container.getContext();

        listaMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);

        listaPresent = new ListaPresent(getContext(), this, 0 );


        return view;
    }



    /*
    public void inicializarMascotas(){

        Mascota mascota1 = new Mascota(1,"Tofi",1,R.drawable.p1,0);
        Mascota mascota2 = new Mascota(2,"Oreo",3,R.drawable.p2,2);
        Mascota mascota3 = new Mascota(3,"Black",2,R.drawable.p3,1);
        Mascota mascota4 = new Mascota(4,"Nii",6,R.drawable.p4,3);
        Mascota mascota5 = new Mascota(5,"Sisi",4,R.drawable.p5,4);

        mascotas = new ArrayList<Mascota>();

        mascotas.add(mascota1);
        mascotas.add(mascota2);
        mascotas.add(mascota3);
        mascotas.add(mascota4);
        mascotas.add(mascota5);

    }*/


    @Override
    public void crearLineraLayout() {
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(lm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listaMascotas.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter mascotaAdapter = new MascotaAdapter(mascotas);
        //iniciarAdaptador(mascotaAdapter);

        return mascotaAdapter;
    }

    @Override
    public void iniciarAdaptador(MascotaAdapter adapter) {
        listaMascotas.setAdapter(adapter);
    }


}
