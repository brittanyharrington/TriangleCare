package com.a40333.bharrin4.triangle_care;

import android.view.View;
import android.widget.Button;
import android.app.Activity;

/**
 * Created by troyprince on 4/21/17.
 */

public class mainTabListener implements View.OnClickListener {

    Button buttonMyTriangle;
    Button buttonFacilities;
    Button buttonUpdates;

    mainTabListener(View mainTab) {
        buttonMyTriangle = (Button) mainTab.findViewById(R.id.mytriangle);
        buttonFacilities = (Button) mainTab.findViewById(R.id.facilities);
        buttonUpdates = (Button) mainTab.findViewById(R.id.updates);
    }

    @Override
    public void onClick(View view) {

        if(view == buttonFacilities){
            //A.finish();
            //startActivity(new Intent(this, FacilityActivity.class));
        }


    }
}
