package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by User on 3/29/2017.
 */

public class FacilityActivity extends ToolBarActivity {
    ArrayList<Facility> al = new ArrayList<Facility>();

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String uID;
    private String email;
    private String facility_name;
    private Facility facility;
    private ImageView pic;
    private TextView facilityName;
    private TextView facilityAddress;
    private TextView facilityPhone;
    private TextView facilityEmail;
    private ImageView map;
    private ImageView cal;
    private TextView announcements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_facility);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        super.onCreate(savedInstanceState);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //initialize widgets
        pic = (ImageView) findViewById(R.id.facility_pic);
        facilityName = (TextView) findViewById(R.id.facility_name);
        facilityAddress = (TextView) findViewById(R.id.facility_address);
        facilityPhone = (TextView) findViewById(R.id.facility_phone);
        facilityEmail = (TextView) findViewById(R.id.facility_email);
        map = (ImageView) findViewById(R.id.map_screenshot);
        cal = (ImageView) findViewById(R.id.calendar_screenshot);
        announcements = (TextView) findViewById(R.id.announcements);


        //getting current user ID and email
        FirebaseUser user = firebaseAuth.getCurrentUser();
        uID = user.getUid();
        email = user.getEmail();
        System.out.println("user = " + uID + " email= " + email);

        //gather user data from Database
        final DatabaseReference myRef = mDatabase.child("users");
        Query query = myRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    facility_name = (String) messageSnapshot.child("facility").getValue();
                    System.out.println("facility name= " + facility_name);

                    //get facility info
                    MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());
                    String mDrawableName = "facilities";
                    int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
                    al = myCsvFileReader.readCsvFile(resID);

                    System.out.println("facility name= " + facility_name);

                    if (facility_name.equals("Holy Cross")) {
                        facility = al.get(0);
                    } else if (facility_name.equals("Wood Ridge")) {
                        facility = al.get(1);
                    }


                    String pictureName = facility.getPicture();
                    int picID = getResources().getIdentifier(pictureName, "drawable", getPackageName());
                    pic.setImageResource(picID);

                    facilityName.setText(facility.getName());
                    facilityAddress.setText(facility.getAddress());
                    facilityPhone.setText(facility.getPhone());
                    facilityEmail.setText(facility.getEmail());


                    String mapName = facility.getMap();
                    int mapID = getResources().getIdentifier(mapName, "drawable", getPackageName());
                    map.setImageResource(mapID);

                    String calName = facility.getCalendar();
                    int calID = getResources().getIdentifier(calName, "drawable", getPackageName());
                    cal.setImageResource(calID);

                    announcements.setText(facility.getAnnouncements());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        // When map is clicked, the MapsActivity should pop up
        map.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(FacilityActivity.this, MapsActivity.class);
                startActivity(mIntent);
            }
        });


    }

}