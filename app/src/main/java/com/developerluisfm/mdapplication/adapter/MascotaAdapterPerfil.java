package com.developerluisfm.mdapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerluisfm.mdapplication.R;
import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by LuisFM on 28/05/16.
 */
public class MascotaAdapterPerfil extends RecyclerView.Adapter<MascotaAdapterPerfil.MascotaviewHolder> {

    ArrayList<Mascota> mascotas;
    Context context;

    public MascotaAdapterPerfil(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @Override
    public MascotaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil, parent, false);
        return new MascotaviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MascotaviewHolder mascotaviewHolder, int position) {
        Mascota mascota = mascotas.get(position);
        //mascotaviewHolder.nombre.setText(mascota.getNombre());
        mascotaviewHolder.foto.setImageResource(mascota.getFoto());
        mascotaviewHolder.puntos.setText(mascota.getLikes()+"");

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

            //nombre = (TextView) itemView.findViewById(R.id.tvNombre);
            puntos = (TextView) itemView.findViewById(R.id.tvNumero);
            foto   = (ImageView) itemView.findViewById(R.id.ivFoto);
            //icono   = (TextView) itemView.findViewById(R.id.tvIcono);


            // Aumentar ranking de mascota
          /*  icono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int numero = 1;

                    int numero2 =0;

                    try{
                       numero2 = Integer.parseInt(puntos.getText().toString());
                    }catch (Exception ex){

                    }finally {
                        puntos.setText(""+(numero+numero2));
                    }

                }
//            });*/

        }
    }
}
