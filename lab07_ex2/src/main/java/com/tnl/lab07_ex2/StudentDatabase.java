package com.tnl.lab07_ex2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase singleton;

    public static StudentDatabase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        StudentDatabase.class, "student_database.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract StudentDAO studentDAO();
}
