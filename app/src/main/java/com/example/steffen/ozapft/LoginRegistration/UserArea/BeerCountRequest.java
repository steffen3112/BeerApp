package com.example.steffen.ozapft.LoginRegistration.UserArea;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steffen on 18.06.2017.
 */

public class BeerCountRequest extends StringRequest {


    private static final String BEERCOUNT_REQUEST_URL = "https://ozapftis.000webhostapp.com/Beercount.php";
    private static final String BEERRANKING_REQUEST_URL = "https://ozapftis.000webhostapp.com/BeerRanking.php";

    private Map<String, String> params;

    public BeerCountRequest(String email, int beers, Response.Listener<String> listener){
        super(Request.Method.POST, BEERCOUNT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("beers", beers + "");
    }

    public BeerCountRequest(Response.Listener<String> listener){
        super(Request.Method.POST, BEERRANKING_REQUEST_URL, listener, null);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
