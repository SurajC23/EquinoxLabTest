package com.example.equinoxlabtest.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.equinoxlabtest.model.Employee;

import java.util.List;

@Dao
public interface EmployeeDoa
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addEmployee(List<Employee> employeeList);

    @Query("SELECT * FROM employee")
    LiveData<List<Employee>> getAllEmployee();

    @Query("DELETE FROM employee")
    public void deleteAll();

    @Query("SELECT COUNT(user_id) FROM employee")
    LiveData<Integer> getRowCount();
}
