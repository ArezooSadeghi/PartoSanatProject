package com.example.partosanatproject.retrofit;

import com.example.partosanatproject.model.CaseTypeResult;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CaseTypeDeserializer implements JsonDeserializer<CaseTypeResult> {

    @Override
    public CaseTypeResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject bodyObject = json.getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(bodyObject.toString(), CaseTypeResult.class);
    }
}
