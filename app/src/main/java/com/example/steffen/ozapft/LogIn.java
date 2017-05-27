package com.example.steffen.ozapft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button bPinLogin = (Button) findViewById(R.id.btn_pin_login);
        Button bCreateAccount = (Button) findViewById(R.id.btn_create_account);

        bPinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PINLogin.class);
                startActivity(i);
            }
        });

        bCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

    }
}
