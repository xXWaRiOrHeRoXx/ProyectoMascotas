package com.developerluisfm.mdapplication.restApi.deserializador;

import android.util.Log;

import com.developerluisfm.mdapplication.pojo.Mascota;
import com.developerluisfm.mdapplication.restApi.JsonKeys;
import com.developerluisfm.mdapplication.restApi.model.MascotaResponse;
import com.developerluisfm.mdapplication.restApi.model.PerfilResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by LuisFM on 26/06/16.
 */
public class PerfilDeserializador implements JsonDeserializer {


    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        JsonArray perfilResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        Log.i("info_luis",perfilResponseData.toString());


        return "";
    }

    private void deserializarPerfilDeJson(JsonArray perfilResponseData){

        Log.i("info_luis",perfilResponseData.toString());


        for (int i = 0; i < perfilResponseData.size() ; i++) {
            JsonObject contactoResponseDataObject = perfilResponseData.get(i).getAsJsonObject();

            JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota mascotaActual = new Mascota();
            //mascotaActual.setId(Integer.parseInt(id));
            mascotaActual.setNombre(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            //mascotas.add(mascotaActual);

        }


    }


}
