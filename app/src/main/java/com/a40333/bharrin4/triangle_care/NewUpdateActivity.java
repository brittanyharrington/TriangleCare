package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NewUpdateActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    private String uID;
    private String email;

    Button saveButton;
    boolean healthCk = false;
    boolean hygieneCk = false;
    boolean sleepCk = false;
    boolean behaviorCk = false;
    boolean medicationCk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_update);

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

        //initializing views
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUpdate();

                finish(); //closing activity
            }
        });

    }

    public void sendUpdate() {

        String nature = null;
        if (((RadioButton) findViewById(R.id.selOutstanding)).isChecked()) {
            nature = "Outstanding";
        } else if (((RadioButton) findViewById(R.id.selSatisfactory)).isChecked()) {
            nature = "Satisfactoy";
        } else if (((RadioButton) findViewById(R.id.selUnsatisfactory)).isChecked()) {
            nature = "Unsatisfactoy";
        }
        String patientName = ((EditText)findViewById(R.id.patientNameEt)).getText().toString();
        String comments = ((EditText)findViewById(R.id.commentsEt)).getText().toString();

        if (((CheckBox) findViewById(R.id.healthCk)).isChecked()) {
            healthCk = true;
            Update healthupdate = new Update(patientName, nature, comments);
            DatabaseReference newRef = mDatabase.child("users").child(uID).child("healthUpdates").push();
            newRef.setValue(healthupdate);
        } if (((CheckBox) findViewById(R.id.hygieneCk)).isChecked()) {
            hygieneCk = true;
            Update hygieneupdate = new Update(patientName, nature, comments);
            DatabaseReference newRef = mDatabase.child("users").child(uID).child("hygieneUpdates").push();
            newRef.setValue(hygieneupdate);
        } if (((CheckBox) findViewById(R.id.sleepCk)).isChecked()) {
            sleepCk = true;
            Update sleepupdate = new Update(patientName, nature, comments);
            DatabaseReference newRef = mDatabase.child("users").child(uID).child("sleepUpdates").push();
            newRef.setValue(sleepupdate);
        } if (((CheckBox) findViewById(R.id.behaviorCk)).isChecked()) {
            behaviorCk = true;
            Update behaviorupdate = new Update(patientName, nature, comments);
            DatabaseReference newRef = mDatabase.child("users").child(uID).child("behaviorUpdates").push();
            newRef.setValue(behaviorupdate);
        } if (((CheckBox) findViewById(R.id.medicationCk)).isChecked()) {
            medicationCk = true;
            Update medupdate = new Update(patientName, nature, comments);
            DatabaseReference newRef = mDatabase.child("users").child(uID).child("medUpdates").push();
            newRef.setValue(medupdate);
        }
    }
}


