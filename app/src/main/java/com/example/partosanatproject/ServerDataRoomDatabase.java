package com.example.partosanatproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.partosanatproject.model.ServerData;

@Database(entities = {ServerData.class}, version = 1)
public abstract class ServerDataRoomDatabase extends RoomDatabase {

    public abstract ServerDataDao serverDataDao();

    private static ServerDataRoomDatabase INSTANCE;

    public static ServerDataRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ServerDataRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ServerDataRoomDatabase.class, "server_data_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
