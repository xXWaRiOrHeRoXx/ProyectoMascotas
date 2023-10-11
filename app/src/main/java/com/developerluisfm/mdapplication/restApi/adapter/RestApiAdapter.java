package com.developerluisfm.mdapplication.restApi.adapter;

import com.developerluisfm.mdapplication.restApi.ConstantesRestApi;
import com.developerluisfm.mdapplication.restApi.EndpointsApi;
import com.developerluisfm.mdapplication.restApi.deserializador.MascotaDeserializador;
import com.developerluisfm.mdapplication.restApi.deserializador.PerfilDeserializador;
import com.developerluisfm.mdapplication.restApi.model.MascotaResponse;
import com.developerluisfm.mdapplication.restApi.model.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, new PerfilDeserializador());
        return gsonBuilder.create();
    }

}
