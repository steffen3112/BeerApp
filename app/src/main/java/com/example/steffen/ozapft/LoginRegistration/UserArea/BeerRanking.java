package com.example.steffen.ozapft.LoginRegistration.UserArea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.steffen.ozapft.R;

import org.json.JSONException;
import org.json.JSONObject;

public class BeerRanking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_ranking);


        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.d("SUCCESS", Boolean.toString(success));



                    if (success) {
                        String vorname = jsonResponse.getString("vorname");
                        //int beercount = jsonResponse.getInt("beers");
                        TextView beercounter = (TextView) findViewById(R.id.txt_ranking);
                        beercounter.setText(vorname);


                    } else {


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        BeerCountRequest beerrankingrequest = new BeerCountRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(beerrankingrequest);
    }
}
