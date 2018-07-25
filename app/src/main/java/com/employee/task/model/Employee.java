package com.employee.task.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "employee_table")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    String mName;
    @ColumnInfo(name = "age")
    Integer mAge;
    @ColumnInfo(name = "address")
    String mAddress;

    public Employee(String mName, Integer mAge, String mAddress) {
        this.mName = mName;
        this.mAge = mAge;
        this.mAddress = mAddress;
    }

    public String getmName() {
        return mName;
    }

    public Employee setmName(String mName) {
        this.mName = mName;
        return this;
    }

    public Integer getmAge() {
        return mAge;
    }

    public Employee setmAge(Integer mAge) {
        this.mAge = mAge;
        return this;
    }

    public String getmAddress() {
        return mAddress;
    }

    public Employee setmAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public static final Comparator<? super Employee> BY_NAME_ASCENDING = new Comparator<Employee>() {
        @Override
        public int compare(Employee lFirstName, Employee lLastName) {
            return lFirstName.mName.compareTo(lLastName.mName);
        }
    };
    public static final Comparator<? super Employee> BY_NAME_DESCENDING = new Comparator<Employee>() {
        @Override
        public int compare(Employee lFirstName, Employee lLastName) {
            return lLastName.mName.compareTo(lFirstName.mName);
        }
    };

    public static final Comparator<? super Employee> BY_AGE_ASCENDING = new Comparator<Employee>() {
        @Override
        public int compare(Employee lSmallAge, Employee lBigAge) {
            return lSmallAge.mAge-lBigAge.mAge;
        }
    };
    public static final Comparator<? super Employee> BY_AGE_DESCENDING = new Comparator<Employee>() {
        @Override
        public int compare(Employee lSmallAge, Employee lBigAge) {
            return lBigAge.mAge-lSmallAge.mAge;
        }
    };
}
