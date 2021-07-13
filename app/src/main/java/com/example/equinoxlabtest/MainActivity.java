package com.example.equinoxlabtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.equinoxlabtest.adapter.EmployeeAdapter;
import com.example.equinoxlabtest.dao.EmployeeDoa;
import com.example.equinoxlabtest.model.BaseResponseArrayEntity;
import com.example.equinoxlabtest.model.Employee;
import com.example.equinoxlabtest.network.APIRequestService;
import com.example.equinoxlabtest.network.RetrofitClient;
import com.example.equinoxlabtest.repository.EmployeeRepository;
import com.example.equinoxlabtest.viewmodel.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    private EmployeeViewModel employeeViewModel;
    private RecyclerView rvEmployee;
    private EmployeeRepository employeeRepository;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> employeeList;
    private EditText etSearchView;
    private RelativeLayout rlNoInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing repository
        employeeRepository = new EmployeeRepository(getApplication());

        //binding views
        rvEmployee = findViewById(R.id.rvEmployee);
        etSearchView = findViewById(R.id.etSearchView);
        rlNoInternetConnection = findViewById(R.id.rlNoInternetConnection);

        //setting up adapter
        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);

        //initialize view model
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        //Making a request to fetch data
        networkRequest();

        //observing data changes in model and set data inside adapter
        employeeViewModel.getAllEmployee().observe(this, new Observer<List<Employee>>()
        {
            @Override public void onChanged(List<Employee> employeeList)
            {
                rvEmployee.setAdapter(employeeAdapter);
                employeeAdapter.getAllEmployee(employeeList);
            }
        });

        //serach filter
        etSearchView.addTextChangedListener(new TextWatcher()
        {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override public void afterTextChanged(Editable editable)
            {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text)
    {
        ArrayList<Employee> filteredList = new ArrayList<>();
        for (Employee item : employeeList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            else if (item.getDept_name().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        employeeAdapter.filterList(filteredList);
    }

    private void networkRequest()
    {
        final APIRequestService apiRequestService = RetrofitClient.getApiService();
        Call<BaseResponseArrayEntity<Employee>> call = apiRequestService.getAllEmployee();
        call.enqueue(new Callback<BaseResponseArrayEntity<Employee>>()
        {
            @Override public void onResponse(Call<BaseResponseArrayEntity<Employee>> call, Response<BaseResponseArrayEntity<Employee>> response)
            {
                if (response != null)
                {
                    BaseResponseArrayEntity<Employee> entity = response.body();
                    if (entity != null)
                    {
                        employeeList = entity.getData();
                        if (employeeList != null && employeeList.size() > 0)
                        {
                            employeeRepository.insert(employeeList);
                        }
                    }
                }
            }

            @Override public void onFailure(Call<BaseResponseArrayEntity<Employee>> call, Throwable t)
            {

            }
        });
    }
}