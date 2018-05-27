package com.example.zhaoxinwu.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Event.class}, version=1)
public abstract class EventRoomDatabase extends RoomDatabase {
    public abstract EventDao eventDao();

    private static EventRoomDatabase INSTANCE;

    static EventRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (EventRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventRoomDatabase.class, "event_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //comment below if you don't want to init the db every time the app restarts
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    /* Add the Init Event for testing. WILL BE REMOVED! */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final EventDao mDao;

        PopulateDbAsync(EventRoomDatabase db) {
            mDao = db.eventDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //Start the app with a clean database every time for test purpose
            //WILL BE REMOVED!
            mDao.deleteAll();

            Event event = new Event("コミケ94",
                    "135-0063",
                    "〒135-0063 Tokyo, 江東区有明３丁目１１−１",
                    "東京ビッグサイト");
            mDao.insert(event);
            return null;
        }
    }

}
