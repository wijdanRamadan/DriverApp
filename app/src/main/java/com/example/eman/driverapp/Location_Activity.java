package com.example.eman.driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Location_Activity extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_);
        logout = (Button) findViewById(R.id.activity_location_btnlogout);
        firebaseAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

                startActivity(new Intent(Location_Activity.this, MainActivity.class));

            }
        });
    }
    private void logout(){
        firebaseAuth.signOut();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        logout();
    }
}
