package com.example.akhlaqcommunication.emaddrassa.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyPostCallBack {
    void OnSuccess(JSONObject jsonObject) ;
    void OnFailure(String err);
}
