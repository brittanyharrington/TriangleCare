package com.a40333.bharrin4.triangle_care;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileActivity extends ToolBarActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;


    //view objects
    private TextView textViewUserName;
    private TextView textViewFacilityName;
    private TextView textViewFacilityLocation;
    private Button buttonLogout;
    private String uID;
    private String email;
    private LinearLayout scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user ID and email
        FirebaseUser user = firebaseAuth.getCurrentUser();
        uID = user.getUid();
        email = user.getEmail();

        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //initializing views
        textViewUserName = (TextView) findViewById(R.id.user_name);
        textViewFacilityLocation = (TextView) findViewById(R.id.facility_city);
        textViewFacilityName= (TextView) findViewById(R.id.facility_name);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        scroll = (LinearLayout) findViewById(R.id.updates_lin_menu);

        //gather user data from Database
        final DatabaseReference myRef = mDatabase.child("users");
        Query query = myRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("first_name").getValue();
                    String facility = (String) messageSnapshot.child("facility").getValue();
                    textViewUserName.setText("Welcome " + name);
                    textViewFacilityName.setText(facility);
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("hygieneUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Hygiene Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                        updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("medUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Medication Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("sleepUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Sleep Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("behaviorUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Behavioral Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("healthUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Health Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                    for (DataSnapshot updateSnapshot : messageSnapshot.child("activityUpdates").getChildren()) {

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("New Activity Update \nPatient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                updateSnapshot.child("comments").getValue() + "\n");

                        scroll.addView(tv1);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //adding listener to button
        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            firebaseAuth.signOut(); //logging out the user
            finish(); //closing activity
            startActivity(new Intent(this, LoginActivity.class)); //return to login
        }
    }
}