package com.developerluisfm.mdapplication.restApi.model;

/**
 * Created by LuisFM on 26/06/16.
 */
public class PerfilResponse {

    public String username;
    public String profile_picture;

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
