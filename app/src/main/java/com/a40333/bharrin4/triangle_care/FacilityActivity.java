package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 3/29/2017.
 */

public class FacilityActivity extends AppCompatActivity {
    ArrayList<Facility> al = new ArrayList<Facility>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);

        setupEvenlyDistributedToolbar();

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

    }

    public void setupEvenlyDistributedToolbar(){
        // Use Display metrics to get Screen Dimensions
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // Toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // Inflate your menu
        mToolbar.inflateMenu(R.menu.menu_main);

        // Add 10 spacing on either side of the toolbar
        mToolbar.setContentInsetsAbsolute(10, 10);

        // Get the ChildCount of your Toolbar, this should only be 1
        int childCount = mToolbar.getChildCount();
        // Get the Screen Width in pixels
        int screenWidth = metrics.widthPixels;

        // Create the Toolbar Params based on the screenWidth
        Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(screenWidth, Toolbar.LayoutParams.WRAP_CONTENT);

        // Loop through the child Items
        for(int i = 0; i < childCount; i++){
            // Get the item at the current index
            View childView = mToolbar.getChildAt(i);
            // If its a ViewGroup
            if(childView instanceof ViewGroup){
                // Set its layout params
                childView.setLayoutParams(toolbarParams);
                // Get the child count of this view group, and compute the item widths based on this count & screen size
                int innerChildCount = ((ViewGroup) childView).getChildCount();
                int itemWidth  = (screenWidth / innerChildCount);
                Log.d("innerChildCount", "innerchildcount= "+innerChildCount);
                Log.d("itemWidth", "screenwidth= "+screenWidth + " itemWidth= "+itemWidth);
                // Create layout params for the ActionMenuView
                ActionMenuView.LayoutParams params = new ActionMenuView.LayoutParams(itemWidth, ActionMenuView.LayoutParams.WRAP_CONTENT);
                // Loop through the children
                for(int j = 0; j < innerChildCount; j++){
                    View grandChild = ((ViewGroup) childView).getChildAt(j);
                    if(grandChild instanceof ActionMenuItemView){
                        // set the layout parameters on each View
                        grandChild.setLayoutParams(params);
                    }
                }
            }
        }
    }

}
