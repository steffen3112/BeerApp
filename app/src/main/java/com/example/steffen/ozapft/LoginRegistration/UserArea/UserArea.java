package com.example.steffen.ozapft.LoginRegistration.UserArea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.steffen.ozapft.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserArea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Navigation Header, Textfelder (Layout: nav_header_user_area)
        View hView = navigationView.getHeaderView(0);
        TextView nav_name = (TextView) hView.findViewById(R.id.nav_name);
        TextView nav_email = (TextView) hView.findViewById(R.id.nav_email);

        //Hier werden die User-Daten in den Navigation Header geschrieben
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String nachname = intent.getStringExtra("surname");
        final String email = intent.getStringExtra("email");
        nav_name.setText(name + " " + nachname);
        nav_email.setText(email);

        navigationView.setNavigationItemSelectedListener(this);

        //Hier wird die UserArea erstellt
         final int beers = 1;

        ImageButton bMinus = (ImageButton) findViewById(R.id.btn_minus);
        ImageButton bPlus = (ImageButton) findViewById(R.id.btn_plus);
        final TextView edBeerCount = (TextView) findViewById(R.id.edt_count);

        edBeerCount.setText(Integer.toString(beers));

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int beers = Integer.parseInt(edBeerCount.getText().toString());
                if(beers > 1 ) {
                    beers  -=  1;
                    edBeerCount.setText(Integer.toString(beers));
                }


            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int beers = Integer.parseInt(edBeerCount.getText().toString());
                if(beers < 10) {
                    beers += 1;
                    edBeerCount.setText(Integer.toString(beers));
                }

            }
        });

        ImageButton bBuy = (ImageButton) findViewById(R.id.btn_buy);

        bBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int beers = Integer.parseInt(edBeerCount.getText().toString());


                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                int beercount = jsonResponse.getInt("beers");
                                TextView beercounter = (TextView) findViewById(R.id.beercounter);
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
        });




    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_area, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_radar) {
            // Handle the radar action
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RadarFragment radarFragment = RadarFragment.newInstance("NEW");
            fragmentTransaction.add(R.id.fragment_container, radarFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_ranking) {

        } else if (id == R.id.nav_news) {

        } else if (id == R.id.nav_consumption) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
