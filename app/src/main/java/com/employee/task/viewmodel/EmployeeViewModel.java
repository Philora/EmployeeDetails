package com.employee.task.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.employee.task.model.Employee;
import com.employee.task.model.EmployeeDatabase;
import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private final LiveData<List<Employee>> listLiveData;
    private EmployeeDatabase empDatabase;

    public EmployeeViewModel(Application application) {
        super(application);

        empDatabase = EmployeeDatabase.getDatabase(this.getApplication());

        listLiveData = empDatabase.employeeDao().getEmpList();
    }

    public void addEmployee(Employee employee) {
        new EmployeeViewModel.addEmployeeAsyncTask(empDatabase).execute(employee);
    }

    public LiveData<List<Employee>> getEmployee() {
        return listLiveData;
    }

    public void deleteItem(Employee empDel) {
        new EmployeeViewModel.deleteAsyncTask().execute(empDel);
    }

    private class addEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        EmployeeDatabase doDatabase;

        public addEmployeeAsyncTask(EmployeeDatabase empDatabase) {
            doDatabase = empDatabase;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            empDatabase.employeeDao().addnewEmp(employees[0]);
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask<Employee, Void, Void> {

        @Override
        protected Void doInBackground(final Employee... params) {
            empDatabase.employeeDao().deleteEmployee(params[0]);
            return null;
        }
    }

}


