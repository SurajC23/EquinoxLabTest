package com.example.equinoxlabtest.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.equinoxlabtest.dao.EmployeeDoa;
import com.example.equinoxlabtest.database.EmployeeDatabase;
import com.example.equinoxlabtest.model.Employee;

import java.util.List;

public class EmployeeRepository
{
    private EmployeeDatabase employeeDatabase;
    private LiveData<List<Employee>> getAllEmployee;

    public EmployeeRepository(Application application)
    {
        employeeDatabase = EmployeeDatabase.getInstance(application);
        getAllEmployee = employeeDatabase.employeeDoa().getAllEmployee();
    }

    public void insert(List<Employee> employeeList)
    {
        new InsertAsyncTask(employeeDatabase).execute(employeeList);
    }

    public LiveData<List<Employee>> getAllEmployee()
    {
        return getAllEmployee;
    }

    class InsertAsyncTask extends AsyncTask<List<Employee>, Void, Void>
    {
        private EmployeeDoa employeeDoa;

        public InsertAsyncTask(EmployeeDatabase employeeDatabase)
        {
            employeeDoa = employeeDatabase.employeeDoa();
        }

        @Override protected Void doInBackground(List<Employee>... lists)
        {
            employeeDoa.addEmployee(lists[0]);
            return null;
        }
    }
}
