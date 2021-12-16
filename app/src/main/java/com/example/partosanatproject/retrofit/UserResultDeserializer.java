package com.example.partosanatproject.retrofit;

import android.util.Log;

import com.example.partosanatproject.model.UserResult;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserResultDeserializer implements JsonDeserializer<UserResult> {

    @Override
    public UserResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Log.d("Arezoo", json.toString());

        JsonObject bodyObject = json.getAsJsonObject();
        Gson gson = new Gson();
        UserResult userResult = gson.fromJson(bodyObject.toString(), UserResult.class);
        Log.d("Arezoo", "Deserialize:" + userResult.getErrorCode());

        return userResult;
    }
}
