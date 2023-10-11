package com.developerluisfm.mdapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerluisfm.mdapplication.R;
import com.developerluisfm.mdapplication.adapter.MascotaAdapter;
import com.developerluisfm.mdapplication.adapter.MascotaAdapterPerfil;
import com.developerluisfm.mdapplication.pojo.Mascota;
import com.developerluisfm.mdapplication.present.ListaPresent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IListaFragment{

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;
    Context context;
    ListaPresent listaPresent;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        context = container.getContext();

        listaMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);

        listaPresent = new ListaPresent(getContext(), this , 12);

        //String result = listaPresent.obtenerPerfil();

        //TextView user = (TextView) view.findViewById(R.id.user_ins);
        //user.setText(result);

        ImageView img = (ImageView) view.findViewById(R.id.view);

        Picasso.with(context)
                .load("https://scontent.cdninstagram.com/t51.2885-19/s150x150/13473271_253980281643096_1008500370_a.jpg")
                .placeholder(R.drawable.p1)
                .into(img);


        return view;
    }

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
