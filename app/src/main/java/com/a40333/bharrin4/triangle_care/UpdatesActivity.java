package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by troyprince on 4/21/17.
 */


public class UpdatesActivity extends ToolBarActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;


    //view objects
    private TextView Hygiene_Update;
    private TextView Medication_Update;
    private TextView Sleep_Update;
    private TextView Behavioral_Update;
    private TextView Health_Update;
    private TextView Activities_Update;
    private LinearLayout updates;
    private ImageButton newUpdate;

    private String uID;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.updates_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
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

        //initializing views
        Hygiene_Update = (TextView) findViewById(R.id.Hygiene);
        Medication_Update = (TextView) findViewById(R.id.Medication);
        Sleep_Update= (TextView) findViewById(R.id.Sleep);
        Behavioral_Update = (TextView) findViewById(R.id.Behavioral);
        Health_Update = (TextView) findViewById(R.id.Health);
        Activities_Update= (TextView) findViewById(R.id.Activities);
        updates = (LinearLayout) findViewById(R.id.updates_linear);
        newUpdate = (ImageButton) findViewById(R.id.new_update_button);

        //gather user data from Database
        final DatabaseReference myRef = mDatabase.child("users");
        Query query = myRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("first_name").getValue();
                    System.out.println("First name: "+ name);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Hygiene_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gather user data from Database
                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("hygieneUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        Medication_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gather user data from Database
                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("medUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
        Sleep_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//gather user data from Database
                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("sleepUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
        Behavioral_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("behaviorUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
        Health_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gather user data from Database
                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("healthUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
        Activities_Update.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gather user data from Database
                final DatabaseReference myRef = mDatabase.child("users");
                Query query = myRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            String name = (String) messageSnapshot.child("first_name").getValue();
                            System.out.println("First name: "+ name);

                            if(((LinearLayout) updates).getChildCount() > 0)
                                ((LinearLayout) updates).removeAllViews();

                            for (DataSnapshot updateSnapshot : messageSnapshot.child("activityUpdates").getChildren()) {

                                TextView tv1 = new TextView(getApplicationContext());
                                tv1.setTextColor(Color.parseColor("#000000"));
                                tv1.setGravity(Gravity.CENTER);
                                tv1.setText("Patient Name: " + updateSnapshot.child("patientName").getValue() + "\nNature: " + updateSnapshot.child("nature").getValue() + "\nComments: " +
                                        updateSnapshot.child("comments").getValue() + "\n");

                                updates.addView(tv1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
        newUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(UpdatesActivity.this, NewUpdateActivity.class);
                startActivity(mIntent);
            }
        });
    }

}
