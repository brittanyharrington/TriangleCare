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

public class NewUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_update);


        //initializing views
        saveButton = (Button) findViewById(R.id.saveButton);

    }

    @Override
    public void onClick(View view) {
        if(view == saveButton){

            //needs to finish being implemented
            sendUpdate();


            finish(); //closing activity
            startActivity(new Intent(this, UpdatesActivity.class)); //return to login
        }
    }

    public void sendUpdate() {

        boolean healthCk = ((CheckBox) findViewById(R.id.healthCk)).isEnabled();
        boolean hygieneCk = ((CheckBox) findViewById(R.id.hygieneCk)).isEnabled();
        boolean sleepCk = ((CheckBox) findViewById(R.id.sleepCk)).isEnabled();
        boolean behaviorCk = ((CheckBox) findViewById(R.id.behaviorCk)).isEnabled();
        boolean medicationCk = ((CheckBox) findViewById(R.id.medicationCk)).isEnabled();
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

        MedUpdate Update = new MedUpdate(patientName, nature, healthCk, hygieneCk, sleepCk, behaviorCk, medicationCk, comments);
    }
}


