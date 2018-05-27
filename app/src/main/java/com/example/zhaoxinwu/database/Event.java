package com.example.zhaoxinwu.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey()
    @NonNull
    //Basic column for event table
    @ColumnInfo(name = "event")
    private String mEvent;
    @ColumnInfo(name = "zipcode")
    private String mZipcode;
    @ColumnInfo(name = "address")
    private String mAddress;
    @ColumnInfo(name = "building")
    private String mBuilding;

    public Event(@NonNull String event, String zipcode, String address, String building) {
        this.mEvent = event;
        this.mZipcode = zipcode;
        this.mAddress = address;
        this.mBuilding = building;
    }
    /*
    public String[] getEvent() {
        String[] eventArray = new String[4];
        eventArray[0] = this.mEvent;
        eventArray[1] = this.mZipCode;
        eventArray[2] = this.mAddress;
        eventArray[3] = this.mBuilding;
        return eventArray;
    }
    */

    public String getEvent() {return this.mEvent;}

    public String getZipcode() {return this.mZipcode; }

    public String getAddress() {return this.mAddress;}

    public String getBuilding() {return this.mBuilding; }

    public void setEvent(@NonNull String event) {this.mEvent = event; }

    public void setZipcode(String zipcode) {this.mZipcode = zipcode; }

    public void setAddress(String address ) {this.mAddress = address; }

    public void setBuilding(String building) {this.mBuilding = building; }

}
