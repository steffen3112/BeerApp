package com.example.steffen.ozapft;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steffen on 20.05.2017.
 * Hier wird ein Request an einen Server über Volley an LOGIN_REQUEST_URL geschickt.
 * Diese Klasse wird in PINLogin implementiert, dort werden dann email & passwort als
 * Parameter zusammen mit einem ResponseListener übergeben.
 */

public class LogInRequest extends StringRequest{

    /*Die URL muss die lokale IPv4 Adresse enthalten, sowie das aufzurufende Skript.
      Dies befindet sich im www-Ordner von Wamp
    */
    private static final String LOGIN_REQUEST_URL = "http://192.168.178.32/Login.php";
    private Map<String, String> params;

    public LogInRequest(String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password );
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
