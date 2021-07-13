package com.example.equinoxlabtest.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "employee", indices = @Index(value = {"user_id"},unique = true))
public class Employee
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("name")
    private String name;

    @SerializedName("dept_name")
    private String dept_name;

    public Employee(int id, String user_id, String name, String dept_name)
    {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.dept_name = dept_name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDept_name()
    {
        return dept_name;
    }

    public void setDept_name(String dept_name)
    {
        this.dept_name = dept_name;
    }

    @Override public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
