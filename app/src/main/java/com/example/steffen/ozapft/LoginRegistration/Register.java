package com.example.steffen.ozapft.LoginRegistration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.steffen.ozapft.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Der Nutzer übergibt dieser Activity seine Nutzerdaten. Analog zum Login wird hier auch ein
 * Request durchgeführt und ein JSON File zurückgegeben. Falls success true ist ist der Nutzer registriert.
 */
public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edName = (EditText) findViewById(R.id.edt_name);
        final EditText edSurname = (EditText) findViewById(R.id.edt_surname);
        final EditText edEmail = (EditText) findViewById(R.id.edt_email);
        final EditText edPassword = (EditText) findViewById(R.id.edt_password);

        final NumberPicker pckrAge = (NumberPicker) findViewById(R.id.pckr_age);
        String[] nums = new String[80];
        for(int i=0; i<nums.length; i++)
            nums[i] = Integer.toString(i + 18);

        pckrAge.setMinValue(18);
        pckrAge.setMaxValue(97);
        pckrAge.setWrapSelectorWheel(false);
        pckrAge.setDisplayedValues(nums);
        pckrAge.setValue(18);

        final Button bRegister = (Button) findViewById(R.id.btn_register);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String vorname = edName.getText().toString();
                final String nachname = edSurname.getText().toString();
                final String email = edEmail.getText().toString();
                final int age =  pckrAge.getValue();
                final String password = edPassword.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Sie sind jetzt registriert!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    Intent intent = new Intent(Register.this, LogIn.class);
                                    Register.this.startActivity(intent);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                if(vorname == "" || nachname == "" || email == "" || password=="" )
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Register Failed")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                else {

                    RegisterRequest registerRequest = new RegisterRequest(vorname, nachname, email, age, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Register.this);
                    queue.add(registerRequest);
                }

            }
        });
    }
}
