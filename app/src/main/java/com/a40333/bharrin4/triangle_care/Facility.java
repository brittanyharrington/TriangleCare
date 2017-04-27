package com.a40333.bharrin4.triangle_care;

import java.io.Serializable;

/**
 * Created by User on 3/29/2017.
 */

public class Facility implements Serializable {
    String picture;
    String name;
    String address;
    String phone;
    String email;
    String map;
    String calendar;
    String announcements;

    //... define all the strings that you need to fill all the TextViews  of activity_detail.

    public Facility (String [] facilityInfo) {
        setPicture(facilityInfo[0]);
        setName(facilityInfo[1]);
        setAddress(facilityInfo[2]);
        setPhone(facilityInfo[3]);
        setEmail(facilityInfo[4]);
        setMap(facilityInfo[5]);
        setCalendar(facilityInfo[6]);
        setAnnouncements(facilityInfo[7]);
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getPicture() {
        return this.picture;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setMap(String map) {
        this.map = map;
    }
    public String getMap() {
        return this.map;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
    public String getCalendar() {
        return this.calendar;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }
    public String getAnnouncements() {
        return this.announcements;
    }

}

