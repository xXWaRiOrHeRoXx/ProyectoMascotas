package com.developerluisfm.mdapplication.restApi;

import com.developerluisfm.mdapplication.restApi.model.MascotaResponse;
import com.developerluisfm.mdapplication.restApi.model.PerfilResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by anahisalgado on 25/05/16.
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_INFO_USER)
    Call getInfoUsuario();

}
