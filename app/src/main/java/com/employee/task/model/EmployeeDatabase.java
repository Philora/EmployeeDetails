package com.employee.task.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase{

    public abstract EmployeeDao employeeDao();

    private static EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            synchronized (EmployeeDatabase.class){
                INSTANCE = Room.databaseBuilder(context,EmployeeDatabase.class,"emp_database").build();
            }
        }
        return INSTANCE;
    }
}

