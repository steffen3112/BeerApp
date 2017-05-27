package com.example.steffen.ozapft;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *Hat der Nutzer Email und Passwort eingegeben und klickt den Button Login
 * wird der Request durchgeführt. Der ResponseListener wartet auf eine Antwort
 * des Servers. Diese ist im PHP-Skript "Login" festgehalten in Form eines boolean (success).
 * Zudem wird ein JSON File an die App mit den Nutzerdaten gesendet.
 * Über einen Intent werden diese an die Activity UserArea weitergeleitet
 */
public class PINLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinlogin);

        final EditText edPIN = (EditText) findViewById(R.id.edt_pin);
        final EditText edEmail = (EditText) findViewById(R.id.edt_email_login);

        final Button bLogin = (Button) findViewById(R.id.btn_login_to_mainmenu);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edEmail.getText().toString();
                final String password = edPIN.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("name");
                               int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(PINLogin.this, UserArea.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);

                                PINLogin.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PINLogin.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LogInRequest loginRequest = new LogInRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(loginRequest);
                    }
                });

            }
        }