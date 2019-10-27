package com.example.android_developer_certification_tutorial.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {RoomEntity.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    private static RoomDb INSTANCE;

    public abstract RoomDao entityDao();

    public static  RoomDb getRoomDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyRoomDbInstance() {
        INSTANCE = null;
    }
}
