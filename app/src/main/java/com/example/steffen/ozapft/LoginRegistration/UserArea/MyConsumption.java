package com.example.steffen.ozapft.LoginRegistration.UserArea;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class MyConsumption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_consumption);

        int beers = 0;

        Intent i = getIntent();
        String email = i.getStringExtra("email");

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.d("SUCCESS", Boolean.toString(success));


                    if (success) {
                        int beercount = jsonResponse.getInt("beers");
                        TextView beercounter = (TextView) findViewById(R.id.txt_beer_consumption);
                        beercounter.setText(Integer.toString(beercount));

                    } else {


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };



        BeerCountRequest beercountrequest = new BeerCountRequest(email, beers, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(beercountrequest);

    }

}
