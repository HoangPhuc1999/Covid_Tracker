package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Sym extends AppCompatActivity {
    CheckBox cold, sore,breathe,cough;
    boolean cold_check,sore_check,breathe_check,cough_check;
    Button submit;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String user_id = user.getUid();
    ArrayList<String> symptoms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sym);

        cold = (CheckBox)findViewById(R.id.cold);
        sore = (CheckBox)findViewById(R.id.sore_throat);
        breathe = (CheckBox)findViewById(R.id.hard_to_breathe);
        cough = (CheckBox)findViewById(R.id.cough);
        cold.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(cold.isChecked()){
                    cold_check = true;
                }
            }
        });

        cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cough.isChecked()){
                    cough_check = true;
                }
            }
        });


        sore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sore.isChecked()){
                    sore_check = true;
                }
            }
        });

        breathe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(breathe.isChecked()){
                    breathe_check = true;
                }
            }
        });
        // Fuck Phuc


        submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Symptoms");
//                if(cold_check){
//                    symptoms.add("Cold");
//                }
//
//                if(cough_check){
//                    symptoms.add("Cough");;
//                }
//
//                if(sore_check){
//                    symptoms.add("Sore Throat");;
//                }
//
//                if(breathe_check){
//                    symptoms.add("Hard to breathe");;
//                }



                ref.setValue(true);
                Intent intent = new Intent(Sym.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
