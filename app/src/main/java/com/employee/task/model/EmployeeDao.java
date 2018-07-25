package com.employee.task.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public  interface EmployeeDao {

    @Insert
    public void addnewEmp(Employee employee);

    @Query("SELECT * from employee_table")
    LiveData<List<Employee>> getEmpList();

    @Query("DELETE FROM employee_table")
    void deleteAll();

    @Delete
    void deleteEmployee(Employee... employees);
}
