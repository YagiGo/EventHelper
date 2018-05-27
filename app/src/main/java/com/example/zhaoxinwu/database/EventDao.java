package com.example.zhaoxinwu.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(Event event);

    @Delete
    void deleteOne(Event event);

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM event_table where event LIKE :eventName")
    LiveData<List<Event>> selectEvent(String eventName);

    @Query("SELECT COUNT(event) from event_table")
    int countEvents();

    @Query("DELETE FROM event_table")
    void deleteAll();



}
