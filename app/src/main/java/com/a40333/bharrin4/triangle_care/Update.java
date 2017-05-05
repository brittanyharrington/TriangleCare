package com.a40333.bharrin4.triangle_care;

import java.io.Serializable;

/**
 * Created by User on 5/4/2017.
 */

public class Update implements Serializable {
    public String patientName;
    public String nature;
    public String comments;

    public Update() {
        // Default constructor required for calls to DataSnapshot.getValue(Update.class)
    }

    public Update(String patientName, String nature, String comments) {
        this.patientName = patientName;
        this.nature = nature;
        this.comments = comments;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getNature() {
        return this.nature;
    }

    public String getComments() {
        return this.comments;
    }

}
