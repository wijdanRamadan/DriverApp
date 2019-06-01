package com.example.eman.driverapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    public static String demail,dpassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;
    private ListView mListView;
    private static final String TAG = "MainActivity";
    private static final int PERMISSIONS_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.activity_main_edtname);
        password = (EditText)findViewById(R.id.activity_main_edtpass);
        login = (Button) findViewById(R.id.activity_main_btnlogin);
      //  mListView = (ListView) findViewById(R.id.activity_main_listview);
        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Driver");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demail=email.getText().toString();
                dpassword=password.getText().toString();
                     signIn(demail,dpassword);
            }
        });


    }

    private void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           startTracking();
                        } else {
                            Log.e(TAG, "Login fail", task.getException());
                        }

                    }
                });

    }

    private void startTracking() {
        Intent x = new Intent(this,TrackBus.class);
        startActivity(x);
    }


    /*private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Bus uInfo = new Bus();
            uInfo.setDriverName(ds.child(userID).getValue(Bus.class).getDriverName()); //set the name
            uInfo.setBusNo(ds.child(userID).getValue(Bus.class).getBusNo()); //set the Bus no
            uInfo.setLat(ds.child(userID).getValue(Bus.class).getLat()); //set the phone_num
            uInfo.setLng(ds.child(userID).getValue(Bus.class).getLng()); //set the phone_num
            //display all the information
            Log.d(TAG, "showData: name: " + uInfo.getDriverName());
            Log.d(TAG, "showData: bus no: " + uInfo.getBusNo());
            Log.d(TAG, "showData: lat: " + uInfo.getLat());
            Log.d(TAG, "showData: lng: " + uInfo.getLng());
            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getDriverName());
            //array.add(uInfo.getBusNo());
            array.add(uInfo.getLat());
            array.add(uInfo.getLng());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }
    }*/
    protected void onStart() {
        super.onStart();
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}