package com.ringcentral.rcandroidsdk.rcsdk.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by andrew.pang on 6/26/15.
 */
public class RCResponse extends RCHeaders {

    Response response;
    int status;
    String body;

    public RCResponse(){

    }
    public RCResponse(Response response){
        try {
            this.response = response;
            this.status = response.code();
            this.body = response.body().string();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean checkStatus(){
        return ((this.status >= 200) && (this.status < 300));
    }

    public String getBody(){
        return this.body;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Parses JSON string to a Map<String, String> that is used to set authorization data
     * @return
     */
    public Map getJson(){
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> ser = gson.fromJson(this.body, mapType);
        return ser;
    }

//    public String getResponses(){
//        if (!this.isMultipart()){
//            Log.e("Error", "RCResponse is not Batch (Multipart)");
//        }
//
//
//    }


}

