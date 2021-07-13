package com.example.equinoxlabtest.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.equinoxlabtest.dao.EmployeeDoa;
import com.example.equinoxlabtest.model.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "EmployeeDatabase";
    public abstract EmployeeDoa employeeDoa();
    private static volatile EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (EmployeeDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context, EmployeeDatabase.class, DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    private static class PopulateAsynTask extends AsyncTask<Void, Void, Void>
    {
        private EmployeeDoa employeeDoa;

        public PopulateAsynTask(EmployeeDatabase employeeDatabase)
        {
            employeeDoa = employeeDatabase.employeeDoa();
        }

        @Override protected Void doInBackground(Void... voids)
        {
            employeeDoa.deleteAll();
            return null;
        }
    }
}
