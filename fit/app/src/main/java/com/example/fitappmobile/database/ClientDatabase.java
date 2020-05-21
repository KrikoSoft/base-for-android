package com.example.fitappmobile.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitappmobile.dao.ClientDao;
import com.example.fitappmobile.java.Client;

import lombok.NonNull;

@Database(entities = Client.class, version = 1)
public abstract class ClientDatabase extends RoomDatabase {

    private static ClientDatabase instance;

    public abstract ClientDao clientDao();

    public static synchronized ClientDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ClientDatabase.class, "client_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ClientDao clientDao;

        private PopulateDbAsyncTask(ClientDatabase db) {
            clientDao = db.clientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.insert(new Client("Yolowski", "Yolo", 22, 55, 66, 7, "male", 9, "", 10, 10));
            clientDao.insert(new Client("Mariowski", "Mario", 22, 55, 66, 7, "male", 9, "", 10, 10));
            clientDao.insert(new Client("Trubadurski", "Trubadur", 22, 55, 66, 7, "male", 9, "", 10, 10));
            return null;
        }
    }

}
