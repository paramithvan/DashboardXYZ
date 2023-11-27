package com.example.testblog.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testblog.R;
import com.example.testblog.data.EmployeeData;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    ArrayList<EmployeeData> data;

    public void setData(ArrayList<EmployeeData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        EmployeeData employeeData = data.get(position);
        holder.employeePhoto.setImageResource(employeeData.getPhoto());
        Log.i("CheckImageRe", "photo: " + employeeData.getPhoto() + " " + R.drawable.e1);
        holder.employeeId.setText(employeeData.getEmployeeId());
        holder.fullName.setText(employeeData.getFullName());
        holder.role.setText(employeeData.getRole());
        holder.status.setText(employeeData.getStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder{
        ImageView employeePhoto;
        TextView employeeId, fullName, role, status;
        CardView cardViewEmployee;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            employeePhoto = itemView.findViewById(R.id.employee_photo_rc);
            employeeId = itemView.findViewById(R.id.employee_id_rc);
            fullName = itemView.findViewById(R.id.employee_name_rc);
            role = itemView.findViewById(R.id.employee_role_rc);
            status = itemView.findViewById(R.id.employee_status_rc);
            cardViewEmployee = itemView.findViewById(R.id.employee_items);
        }

    }
}
