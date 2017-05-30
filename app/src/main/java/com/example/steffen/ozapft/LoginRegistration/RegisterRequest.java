package com.example.steffen.ozapft.LoginRegistration;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steffen on 20.05.2017.
 */

public class RegisterRequest extends StringRequest {

    /*Die URL muss die lokale IPv4 Adresse enthalten, sowie das aufzurufende Skript.
      Dies befindet sich im www-Ordner von Wamp
    */
    private static final String REGISTER_REQUEST_URL = "http://192.168.187.155/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String vorname, String nachname, String email, int age, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("vorname", vorname);
        params.put("nachname", nachname);
        params.put("email", email);
        params.put("age", age + "");
        params.put("password", password );
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
