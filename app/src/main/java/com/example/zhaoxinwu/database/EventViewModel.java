package com.example.zhaoxinwu.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class EventViewModel extends AndroidViewModel{
    private EventRepo mRepo;
    private LiveData<List<Event>> mALlEvents;

    public EventViewModel(Application application) {
        super(application);
        mRepo = new EventRepo(application);
        mALlEvents = mRepo.getmAllEvents();
    }
    LiveData<List<Event>> getAllEvents() {return mALlEvents; }

    public void insert(Event event) {mRepo.insert(event);}

}
