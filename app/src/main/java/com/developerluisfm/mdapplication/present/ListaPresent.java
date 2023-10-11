package com.developerluisfm.mdapplication.present;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.developerluisfm.mdapplication.db.ContructorDatos;
import com.developerluisfm.mdapplication.fragment.IListaFragment;
import com.developerluisfm.mdapplication.pojo.Mascota;
import com.developerluisfm.mdapplication.restApi.EndpointsApi;
import com.developerluisfm.mdapplication.restApi.adapter.RestApiAdapter;
import com.developerluisfm.mdapplication.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LuisFM on 11/06/16.
 */
public class ListaPresent implements IListaPresent {

    private Context context;
    private IListaFragment ilistaFragment;
    private ContructorDatos datos;
    private ArrayList<Mascota> mascotas;

    public ListaPresent(Context context, IListaFragment ilistaFragment, int option){

        this.context = context;
        this.ilistaFragment = ilistaFragment;
        //obtenrMascota();
        obtenerMediosRecientes();

    }

    @Override
    public void obtenrMascota() {
        datos = new ContructorDatos(context);
        mascotas = datos.obtenerMascotas();
        mostrarMascotaRV();
    }

    @Override
    public void mostrarMascotaRV() {
        ilistaFragment.iniciarAdaptador(ilistaFragment.crearAdaptador(mascotas));
        ilistaFragment.generarGridLayout();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> contactoResponseCall = endpointsApi.getRecentMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getContactos();
                mostrarMascotaRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }


    public String obtenerPerfil() {


        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorPerfil();

        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<String> perfil = endpointsApi.getInfoUsuario();

        perfil.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.getMessage().toString());
            }
        });

        return "prueba";


    }


}
