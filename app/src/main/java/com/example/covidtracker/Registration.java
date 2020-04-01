package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    private EditText fullname_input, email_input, password_input, password_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        fullname_input = (EditText) findViewById(R.id.fullname_input);
        email_input = (EditText) findViewById(R.id.email_input);
        password_input = (EditText) findViewById(R.id.password_input);
        password_confirm = (EditText) findViewById(R.id.password_confirm);


    }






}
