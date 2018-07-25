package com.employee.task.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.employee.task.R;
import com.employee.task.viewmodel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {
    private static final String TAG = "EmployeeAdapter";
    Context context;
    private EmployeeViewModel empViewModel;
    private List<Employee> empList;

    public EmployeeAdapter(ArrayList<Employee> employees, Context context, EmployeeViewModel empGetMv) {
        this.empList = employees;
        this.context = context;
        this.empViewModel = empGetMv;
    }

    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_item, parent, false);

        return new EmployeeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EmployeeAdapter.MyViewHolder holder, final int position) {
        final Employee empPos = empList.get(position);
        holder.name.setText(empPos.getmName());
        holder.age.setText(Integer.toString(empPos.getmAge()));
        holder.address.setText(empPos.getmAddress());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee deleteTodoItem = empList.get(holder.getAdapterPosition());
                if (empViewModel != null) {
                    empViewModel.deleteItem(deleteTodoItem);
                    Toast.makeText(context, "Deleting the Header: " + empPos.getmName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return empList.size();
    }

    public void sortNameAscending() {
        Collections.sort(empList, Employee.BY_NAME_ASCENDING);
        notifyDataSetChanged();
    }

    public void sortNameDescending() {
        Collections.sort(empList, Employee.BY_NAME_DESCENDING);
        notifyDataSetChanged();
    }

    public void sortAgeAscending() {
        Collections.sort(empList, Employee.BY_AGE_ASCENDING);
        notifyDataSetChanged();
    }

    public void sortAgeDescending() {
        Collections.sort(empList, Employee.BY_AGE_DESCENDING);
        notifyDataSetChanged();
    }

    public void setTodoList(List<Employee> empList) {
        this.empList = empList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, address;
        ImageView imgDelete;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_Name);
            age = (TextView) view.findViewById(R.id.txt_Age);
            address = (TextView) view.findViewById(R.id.txt_Address);
            imgDelete = (ImageView) view.findViewById(R.id.img_Delete);
        }
    }

}
