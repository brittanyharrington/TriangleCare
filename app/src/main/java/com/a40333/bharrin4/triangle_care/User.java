package com.a40333.bharrin4.triangle_care;

import java.io.Serializable;

/**
 * Created by User on 4/21/2017.
 */

public class User implements Serializable {
    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String phone;
    public String facility;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String first_name, String last_name, String email, String password, String phone, String facility) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.facility = facility;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
