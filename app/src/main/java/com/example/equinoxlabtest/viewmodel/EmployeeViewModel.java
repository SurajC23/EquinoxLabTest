package com.example.equinoxlabtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.equinoxlabtest.model.Employee;
import com.example.equinoxlabtest.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel
{
    private EmployeeRepository employeeRepository;
    private LiveData<List<Employee>> getAllEmployee;

    public EmployeeViewModel(@NonNull Application application)
    {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        getAllEmployee = employeeRepository.getAllEmployee();
    }

    public LiveData<List<Employee>> getAllEmployee()
    {
        return getAllEmployee;
    }
}
