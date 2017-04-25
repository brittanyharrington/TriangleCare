package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by troyprince on 4/21/17.
 */

public class ToolBarActivity extends AppCompatActivity {

    public Toolbar toolbar;                              // Declaring the Toolbar Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_activity);

        //toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemene
        if (id == R.id.my_triangle) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
        else if (id == R.id.facilities) {
            finish();
            startActivity(new Intent(this, FacilityActivity.class));
        }
        else if (id == R.id.updates) {
            finish();
            startActivity(new Intent(this, UpdatesActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}