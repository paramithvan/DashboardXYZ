package com.example.testblog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testblog.data.Database;
import com.example.testblog.data.EmployeeData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class RoleFragment extends Fragment {

    Database database;
    ArrayList<EmployeeData> data = new ArrayList<>();
    PieChart pieChart;
    TextView active, nonActive;
    TextView activeCount, nonActiveCount;

    public RoleFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.spinner_role);
        active = view.findViewById(R.id.active_tv_role);
        nonActive = view.findViewById(R.id.nonActive_tv_role);
        pieChart = view.findViewById(R.id.piechart_role);

        activeCount = view.findViewById(R.id.CountActive_tv_role);
        nonActiveCount = view.findViewById(R.id.CountNonActive_tv_role);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.filterByRoles, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                database = new Database(getContext());
                data = database.getEmployeeData();

                ArrayList<EmployeeData> statusData = new ArrayList<>();
                pieChart.clearChart();

                if(position == 0){
                    data.stream().filter(e -> e.getRole().equals("Driver")).forEach(statusData::add);
                    active.setText("Active Driver");
                    nonActive.setText("Non-Active Driver");
                }else if (position == 1){
                    data.stream().filter(e -> e.getRole().equals("Dispatcher")).forEach(statusData::add);
                    active.setText("Active Dispatcher");
                    nonActive.setText("Non-Active Dispatcher");
                }else if (position == 2){
                    data.stream().filter(e -> e.getRole().equals("Transportlead")).forEach(statusData::add);
                    active.setText("Active Transportlead");
                    nonActive.setText("Non-Active Transportlead");
                }else if (position == 3){
                    data.stream().filter(e -> e.getRole().equals("Cashier")).forEach(statusData::add);
                    active.setText("Active Cashier");
                    nonActive.setText("Non-Active Cashier");
                }else if (position == 4){
                    data.stream().filter(e -> e.getRole().equals("Superadmin")).forEach(statusData::add);
                    active.setText("Active Superadmin");
                    nonActive.setText("Non-Active Superadmin");
                }

                activeCount.setText(String.valueOf((int) statusData.stream().filter(e -> e.getStatus().equals("Active")).count()));
                nonActiveCount.setText(String.valueOf((int) statusData.stream().filter(e -> e.getStatus().equals("Non-Active")).count()));

                pieChart.addPieSlice(
                        new PieModel(
                                "Active",
                                (int) statusData.stream().filter(e -> e.getStatus().equals("Active")).count(),
                                Color.parseColor("#7DB3BF")
                        )
                );
                pieChart.addPieSlice(
                        new PieModel(
                                "Non-Active",
                                (int) statusData.stream().filter(e -> e.getStatus().equals("Non-Active")).count(),
                                Color.parseColor("#B96C6C")
                        )
                );
                pieChart.startAnimation();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_role, container, false);
    }
}