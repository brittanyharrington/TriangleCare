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

import java.util.ArrayList;

/**
 * Created by User on 3/29/2017.
 */

public class FacilityActivity extends ToolBarActivity {
    ArrayList<Facility> al = new ArrayList<Facility>();

    //private View mainTab;
    //private Button buttonMyTriangle;
    //private Button buttonUpdates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_facility);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.onCreate(savedInstanceState);


        //initializing views
        //mainTab = findViewById(R.id.main_tab);
        //buttonMyTriangle = (Button) mainTab.findViewById(R.id.mytriangle);
        //buttonUpdates = (Button) mainTab.findViewById(R.id.updates);


        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());
        String mDrawableName = "facilities";
        int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
        al = myCsvFileReader.readCsvFile(resID);
        Facility holycross = al.get(1);
        Facility woodridge = al.get(1);

        ImageView pic = (ImageView) findViewById(R.id.facility_pic);
        String pictureName = holycross.getPicture();
        int picID = getResources().getIdentifier(pictureName, "drawable", getPackageName());
        pic.setImageResource(picID);

        TextView facilityName = (TextView) findViewById(R.id.facility_name);
        facilityName.setText(holycross.getName());

        TextView facilityAddress = (TextView) findViewById(R.id.facility_address);
        facilityAddress.setText(holycross.getAddress());

        TextView facilityPhone = (TextView) findViewById(R.id.facility_phone);
        facilityPhone.setText(holycross.getPhone());

        TextView facilityEmail = (TextView) findViewById(R.id.facility_email);
        facilityEmail.setText(holycross.getEmail());


        ImageView map = (ImageView) findViewById(R.id.map_screenshot);
        String mapName = holycross.getMap();
        int mapID = getResources().getIdentifier(mapName, "drawable", getPackageName());
        map.setImageResource(mapID);

        ImageView cal = (ImageView) findViewById(R.id.calendar_screenshot);
        String calName = holycross.getCalendar();
        int calID = getResources().getIdentifier(calName, "drawable", getPackageName());
        cal.setImageResource(calID);

        TextView announcements = (TextView) findViewById(R.id.announcements);
        announcements.setText(holycross.getAnnouncements());

        // When map is clicked, the MapsActivity should pop up
        map.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(FacilityActivity.this, MapsActivity.class);
                startActivity(mIntent);
            }
        });


        //mainTabListener menuListener = new mainTabListener();
        //buttonMyTriangle.setOnClickListener(this);

    }

    /*private mainTabListener listener = new View.OnClickListener() {

        //Button buttonMyTriangle;
        //Button buttonFacilities;
        //Button buttonUpdates;

        //mainTabListener(View mainTab) {
        Button buttonMyTriangle = (Button) mainTab.findViewById(R.id.mytriangle);
        Button buttonFacilities = (Button) mainTab.findViewById(R.id.facilities);
        Button buttonUpdates = (Button) mainTab.findViewById(R.id.updates);
        //}

    @Override
    public void onClick(View view) {

        if(view == buttonMyTriangle){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
*/

}