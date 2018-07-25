package com.employee.task.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.employee.task.R;
import com.employee.task.model.Employee;
import com.employee.task.model.EmployeeAdapter;
import com.employee.task.viewmodel.EmployeeViewModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMain extends AppCompatActivity {

    private static String TAG = EmployeeMain.class.getSimpleName();
    private EmployeeViewModel empViewModel;
    private EmployeeAdapter empAdapter;
    private RecyclerView recyclerView;
    boolean checkBoolean = true;
    ImageView imageName, imageSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_main);

        empViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_Employee);
        imageName = (ImageView) findViewById(R.id.name_sort_icon);
        imageSort = (ImageView) findViewById(R.id.age_sort_icon);
        empAdapter = new EmployeeAdapter( new ArrayList<Employee>(),this , empViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(empAdapter);

        empViewModel.getEmployee().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                empAdapter.setTodoList(employees);
                Log.d(TAG, "EmployeeAdapter: "+empAdapter);
            }
        });
    }

    public void openNew(View view) {
        startActivity(new Intent(this,AddNewEmployee.class));
    }

    public void sortAge(View view) {
        if (checkBoolean) {
            empAdapter.sortAgeDescending();
            imageSort.setImageResource(R.drawable.down_arrow);
            checkBoolean = false;
        } else {
            empAdapter.sortAgeAscending();
            imageSort.setImageResource(R.drawable.up_arrow);
            checkBoolean = true;
        }
    }

    public void sortName(View view) {
        if (checkBoolean) {
            empAdapter.sortNameDescending();
            imageName.setImageResource(R.drawable.down_arrow);
            checkBoolean = false;
        } else {
            empAdapter.sortNameAscending();
            imageName.setImageResource(R.drawable.up_arrow);
            checkBoolean = true;
        }
    }
}