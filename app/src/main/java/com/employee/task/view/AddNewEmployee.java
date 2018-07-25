package com.employee.task.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.employee.task.R;
import com.employee.task.model.Employee;
import com.employee.task.viewmodel.EmployeeViewModel;

public class AddNewEmployee extends AppCompatActivity {
    private static final String TAG = "AddNewEmployee";
    EditText editName, editAge, editAddress;
    Button buttonSave;
    private EmployeeViewModel empViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_employee);
        editName = (EditText) findViewById(R.id.edt_Name);
        editAge = (EditText) findViewById(R.id.edt_Age);
        editAddress = (EditText) findViewById(R.id.edt_Address);
        buttonSave =(Button) findViewById(R.id.btn_save);

        empViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewClick();
            }
        });
    }

    private void addNewClick() {
        
        String lName = editName.getText().toString();
        String lAddress = editAddress.getText().toString();
        String lAge = editAge.getText().toString();
        int checkNumber=0;
        Log.d(TAG, "checkAddNewClick: "+lName+"-"+lAge+"-"+lAge);
        try {
            checkNumber = Integer.parseInt(lAge);
            Log.i("",checkNumber+" is a number");
        } catch (NumberFormatException e) {
            Log.i("",lAge+" is not a number");
        }
        Log.d(TAG, "checkNumber: "+checkNumber);

        if (!TextUtils.isEmpty(lName) && !TextUtils.isEmpty(lAddress) && checkNumber>0 && empViewModel != null) {
           
            empViewModel.addEmployee(new Employee(lName, Integer.valueOf(editAge.getText().toString()),lAddress));
            Log.d(TAG, "addedEmployeeValue: "+lName+"-"+lAge+"-"+lAge);
            Toast.makeText(this, "New Employee Added", Toast.LENGTH_SHORT).show();
            finish();
            
        } else {
            Toast.makeText(getApplicationContext(), "Can't be in empty state", Toast.LENGTH_SHORT).show();
        }
    }
}
