package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private EditText email_input, password_input;
    private Button login_button, registration_button;
    private FirebaseAuth authentication;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        authentication = FirebaseAuth.getInstance();
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

        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    //username is empty
                    email_input.requestFocus();
                    email_input.setError("Invalid email");
                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    //password is empty
                    password_input.requestFocus();
                    password_input.setError("Invalid Password");
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }

                        authentication.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(Login.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                                        } else {

                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                            if (user != null) {
                                                // Check if the account email is verified
                                                if(user.isEmailVerified()) {
                                                    Intent intent = new Intent(Login.this, MapsActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                    return;
                                                }
                                                else{
                                                    Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }


                                    }
                                });




            }
        });




    }





    public boolean check_input_user(String email, String password){


        if(email.equals(null) ){
            email_input.requestFocus();
            email_input.setError("Invalid email");
            return false;
        }

        if(password.equals(null)){
            password_input.requestFocus();
            password_input.setError("Invalid password");
            return false;
        }
      return true;

    }


















}
