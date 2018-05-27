package com.example.zhaoxinwu.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.PrimaryKey;
import android.os.AsyncTask;

import java.security.PrivateKey;
import java.util.List;

public class EventRepo {
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;
    private Event mOneEvent;

    EventRepo(Application application) {
        EventRoomDatabase db = EventRoomDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvents = mEventDao.getAllEvents();
    }
    LiveData<List<Event>> getmAllEvents() {return mAllEvents;}

    // Don't do it on the main thread!
    public void insert(Event event) { new insertAsyncTask(mEventDao).execute(event); }

    public void deleteOneEvent(Event event) { new deleteOneAsyncTask(mEventDao).execute(event);}

    private static class insertAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        insertAsyncTask(EventDao dao) { mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(final Event... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteOneAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        deleteOneAsyncTask(EventDao dao) { mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(final Event... params) {
            mAsyncTaskDao.deleteOne(params[0]);
            return null;
        }
    }

}
