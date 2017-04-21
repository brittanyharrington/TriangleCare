package com.a40333.bharrin4.triangle_care;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by troyprince on 4/21/17.
 */

public class UpdatesActivity extends ToolBarActivity implements View.OnClickListener {

    private View mainTab;
    private Button buttonMyTriangle;
    private Button buttonFacility;
    private Button buttonUpdates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.updates_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.onCreate(savedInstanceState);

        //initializing views
        mainTab = findViewById(R.id.main_tab);
        buttonMyTriangle = (Button) mainTab.findViewById(R.id.mytriangle);
        buttonFacility = (Button) mainTab.findViewById(R.id.facilities);
        buttonUpdates = (Button) mainTab.findViewById(R.id.updates);

    }

        @Override
    public void onClick(View view) {

        if(view == buttonMyTriangle){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
