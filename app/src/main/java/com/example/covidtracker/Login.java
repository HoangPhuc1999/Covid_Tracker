package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {


    private EditText email_input, password_input;
    private Button login_button, registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        email_input = (EditText) findViewById(R.id.email_input);
        password_input = (EditText) findViewById(R.id.password_input);

        registration_button = (Button) findViewById(R.id.registration_button);
        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
                finish();
                return;

            }
        });







    }





















}
