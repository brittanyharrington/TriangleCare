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

public class UpdatesActivity extends ToolBarActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.updates_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        super.onCreate(savedInstanceState);


    }

}
