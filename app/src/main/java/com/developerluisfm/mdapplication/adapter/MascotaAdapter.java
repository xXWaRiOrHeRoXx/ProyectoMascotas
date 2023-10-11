package com.developerluisfm.mdapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerluisfm.mdapplication.R;
import com.developerluisfm.mdapplication.db.ContructorDatos;
import com.developerluisfm.mdapplication.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LuisFM on 28/05/16.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaviewHolder> {

    ArrayList<Mascota> mascotas;
    Context context;

    public MascotaAdapter(ArrayList<Mascota> mascotas ){
        this.mascotas = mascotas;
    }

    @Override
    public MascotaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotaviewHolder mascotaviewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaviewHolder.nombre.setText(mascota.getNombre());
        //mascotaviewHolder.foto.setImageResource(mascota.getFoto());
        mascotaviewHolder.puntos.setText(""+mascota.getLikes());

        Picasso.with(context)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.p1)
                .into(mascotaviewHolder.foto);


        // Aumentar ranking de mascota
       /* mascotaviewHolder.icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContructorDatos contructorDatos = new ContructorDatos(context);
                contructorDatos.insertarContactoLike(mascota);

                mascotaviewHolder.puntos.setText(""+contructorDatos.obtenerLikes(mascota));


            }
        });
    */

    }

    @Override
    public int getItemCount() { //cantidad de elementos
        return  mascotas.size();
    }

    public static class MascotaviewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private ImageView foto;
        private TextView puntos;
        private TextView icono;

        public MascotaviewHolder(final View itemView) {

            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.tvNombre);
            puntos = (TextView) itemView.findViewById(R.id.tvNumero);
            foto   = (ImageView) itemView.findViewById(R.id.ivFoto);
            icono   = (TextView) itemView.findViewById(R.id.tvIcono);


            icono.setVisibility(View.GONE);


        }
    }
}
