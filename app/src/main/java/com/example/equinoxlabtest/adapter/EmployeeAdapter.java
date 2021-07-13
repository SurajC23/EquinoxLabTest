package com.example.equinoxlabtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.equinoxlabtest.R;
import com.example.equinoxlabtest.constants.StringConstants;
import com.example.equinoxlabtest.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>
{
    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList)
    {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull @Override public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_row, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position)
    {
        Employee employee = employeeList.get(position);
        holder.txtEmpName.setText(StringConstants.EMP_NAME + employee.getName());
        holder.txtEmpDept.setText(StringConstants.EMP_DEPARTMENT + employee.getDept_name());
    }

    public void getAllEmployee(List<Employee> employeeLists)
    {
        this.employeeList = employeeLists;
    }

    @Override public int getItemCount()
    {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtEmpName, txtEmpDept;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtEmpName = itemView.findViewById(R.id.txtEmpName);
            txtEmpDept = itemView.findViewById(R.id.txtEmpDept);
        }
    }

    public void filterList(ArrayList<Employee> filteredList)
    {
        employeeList = filteredList;
        notifyDataSetChanged();
    }
}
