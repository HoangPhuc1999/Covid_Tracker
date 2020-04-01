package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText fullname_input, email_input, password_input, password_confirm;
    private Button registration_button;
    private FirebaseAuth authentication;
    private boolean verify = false;
    private static final String TAG = "Registration";









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        fullname_input = (EditText) findViewById(R.id.fullname_input);
        email_input = (EditText) findViewById(R.id.email_input);
        password_input = (EditText) findViewById(R.id.password_input);
        password_confirm = (EditText) findViewById(R.id.password_confirm);

        registration_button = (Button) findViewById(R.id.registration_button);
        authentication = FirebaseAuth.getInstance();

        registration_button.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View v) {
                final String email = email_input.getText().toString();
                final String fullname = fullname_input.getText().toString();
                final String password = password_input.getText().toString();
                final String password_check = password_confirm.getText().toString();

                if(!(password.equals(password_check))){

                    password_input.requestFocus();
                    password_confirm.setError("Password does not match");

                }else {





                        authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {

                                    Toast.makeText(Registration.this, "Sign up Error", Toast.LENGTH_SHORT).show();

                                } else {
                                    authentication.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    // Re-enable button
                                                    findViewById(R.id.registration_button).setEnabled(true);

                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Registration.this,
                                                                "Verification email sent to " + email_input.getText().toString(),
                                                                Toast.LENGTH_SHORT).show();
                                                        verify = true;
                                                    } else {
                                                        Log.e(TAG, "sendEmailVerification", task.getException());
                                                        Toast.makeText(Registration.this,
                                                                "Failed to send verification email.",
                                                                Toast.LENGTH_SHORT).show();

                                                        verify = false;
                                                    }
                                                }
                                            });

                                    String user_id = authentication.getCurrentUser().getUid();
                                    findViewById(R.id.registration_button).setEnabled(true);
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(fullname).child("User_ID").child(user_id);
                                    current_user_db.setValue(true);

                                    Toast.makeText(Registration.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Registration.this, Login.class);
                                    startActivity(intent);
                                    }

                            }
                        });

                }

            }
        });




    }






}
