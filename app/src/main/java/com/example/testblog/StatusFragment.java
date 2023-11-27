package com.example.testblog;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.TelephonyCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testblog.adapter.EmployeeAdapter;
import com.example.testblog.data.Database;
import com.example.testblog.data.EmployeeData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatusFragment extends Fragment {

    Database database;
    private RecyclerView StatusRV;
    EmployeeData employeeData;
    EmployeeAdapter employeeAdapter;
    ArrayList<EmployeeData> data = new ArrayList<>();
    PieChart pieChart;
    TextView driver, dispatcher, transportlead, cashier, superadmin;
    TextView driverCount, disCount, transportCount, cashierCount, adminCount;
    ImageView employeePhoto;
    TextView employeeId, fullName, role, status;

    public StatusFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.spinner_status);
        driver = view.findViewById(R.id.driver_tv);
        dispatcher = view.findViewById(R.id.dispatcher_tv);
        transportlead = view.findViewById(R.id.transportlead_tv);
        cashier = view.findViewById(R.id.cashier_tv);
        superadmin = view.findViewById(R.id.superadmin_tv);
        pieChart = view.findViewById(R.id.piechart_status);

        driverCount = view.findViewById(R.id.Countdriver_tv);
        disCount = view.findViewById(R.id.CountDispatcher_tv);
        transportCount = view.findViewById(R.id.CountTrans_tv);
        cashierCount = view.findViewById(R.id.CountCashier_tv);
        adminCount = view.findViewById(R.id.CountAdmin_tv);

        StatusRV = view.findViewById(R.id.recyclerview_status);

        employeePhoto = view.findViewById(R.id.employee_photo_rc);
        employeeId = view.findViewById(R.id.employee_id_rc);
        fullName = view.findViewById(R.id.employee_name_rc);
        role = view.findViewById(R.id.employee_role_rc);
        status = view.findViewById(R.id.employee_status_rc);

        database = Database.getDbHelper(getContext());

        data = new ArrayList<>();
        data = database.getEmployeeData();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.filterByStatus, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                database = new Database(getContext());
                data = database.getEmployeeData();
                ArrayList<EmployeeData> statusData = new ArrayList<>();

                pieChart.clearChart();

                if(position == 0){
                    data.stream().filter(e -> e.getStatus().equals("Active")).forEach(statusData::add);
                    filteringRecyclerView(statusData);
                }else{
                    data.stream().filter(e -> e.getStatus().equals("Non-Active")).forEach(statusData::add);
                    filteringRecyclerView(statusData);
                }

                driverCount.setText(String.valueOf(statusData.stream().filter(e -> e.getRole().equals("Driver")).collect(Collectors.toList()).size()));
                disCount.setText(String.valueOf(statusData.stream().filter(e -> e.getRole().equals("Dispatcher")).collect(Collectors.toList()).size()));
                transportCount.setText(String.valueOf(statusData.stream().filter(e -> e.getRole().equals("Transportlead")).collect(Collectors.toList()).size()));
                cashierCount.setText(String.valueOf( statusData.stream().filter(e -> e.getRole().equals("Cashier")).collect(Collectors.toList()).size()));
                adminCount.setText(String.valueOf(statusData.stream().filter(e -> e.getRole().equals("Superadmin")).collect(Collectors.toList()).size()));

                pieChart.addPieSlice(
                        new PieModel(
                                "Driver",
                                statusData.stream().filter(e -> e.getRole().equals("Driver")).collect(Collectors.toList()).size(),
                                Color.parseColor("#7D8FBF")
                        )
                );
                pieChart.addPieSlice(
                        new PieModel(
                                "Dispatcher",
                                statusData.stream().filter(e -> e.getRole().equals("Dispatcher")).collect(Collectors.toList()).size(),
                                Color.parseColor("#BF7D7D")
                        )
                );
                pieChart.addPieSlice(
                        new PieModel(
                                "Transportlead",
                                statusData.stream().filter(e -> e.getRole().equals("Transportlead")).collect(Collectors.toList()).size(),
                                Color.parseColor("#BE7DBF")
                        )
                );
                pieChart.addPieSlice(
                        new PieModel(
                                "Cashier",
                                statusData.stream().filter(e -> e.getRole().equals("Cashier")).collect(Collectors.toList()).size(),
                                Color.parseColor("#A2BF7D")
                        )
                );
                pieChart.addPieSlice(
                        new PieModel(
                                "Superadmin",
                                statusData.stream().filter(e -> e.getRole().equals("Superadmin")).collect(Collectors.toList()).size(),
                                Color.parseColor("#E2D091")
                        )
                );
                pieChart.startAnimation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void filteringRecyclerView(ArrayList<EmployeeData> employeeDataArrayList){
        employeeAdapter = new EmployeeAdapter();
        employeeAdapter.setData(employeeDataArrayList);

        StatusRV.setAdapter(employeeAdapter);
        StatusRV.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status, container, false);
    }
}