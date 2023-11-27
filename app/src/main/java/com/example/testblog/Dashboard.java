package com.example.testblog;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
        ColorStateList def;
        TextView status;
        TextView role;
        TextView select;
        ImageButton logout;
        FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logout = findViewById(R.id.logout_btn);
        status = findViewById(R.id.status_tab);
        role = findViewById(R.id.role_tab);
        status.setOnClickListener(this);
        role.setOnClickListener(this);
        select = findViewById(R.id.select);
        def = role.getTextColors();


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_tab, new StatusFragment()).commit();
        logout.setOnClickListener(v -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.status_tab:
                fragmentManager.beginTransaction().replace(R.id.layout_tab, new StatusFragment()).commit();
                select.animate().x(0).setDuration(100);
                status.setTextColor(Color.WHITE);
                role.setTextColor(def);
                break;
            case R.id.role_tab:
                fragmentManager.beginTransaction().replace(R.id.layout_tab, new RoleFragment()).commit();
                status.setTextColor(def);
                role.setTextColor(Color.WHITE);
                int size = role.getWidth();
                select.animate().x(size).setDuration(100);
                break;
        }
    }


}