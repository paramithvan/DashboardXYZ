package com.example.testblog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testblog.data.Database;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username_edt_login);
        password = findViewById(R.id.password_edt_login);
        login = findViewById(R.id.login_button);

        login.setOnClickListener(v -> {
            String checkUsername = username.getText().toString();
            String checkPassword = password.getText().toString();
            if (checkUsername.isEmpty()|| checkPassword.isEmpty()){
                Toast.makeText(MainActivity.this, "Please fill all field", Toast.LENGTH_SHORT).show();
            }else if(checkUsername.length() < 4 ){
                Toast.makeText(MainActivity.this, "Username length must be more than 4", Toast.LENGTH_SHORT).show();

            }else if (checkPassword.length() < 6){
                Toast.makeText(MainActivity.this, "Password length must be more than 6", Toast.LENGTH_SHORT).show();
            }else if(!checkPassword.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")){
                Toast.makeText(MainActivity.this, "Password must be alphanumeric", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

        database = new Database(this);

        if(database.checkDatabase()){
            database.insertEmployee(R.drawable.e1,1, "ZYX001","Albert Suryadi", "Transportlead", "Active");
            database.insertEmployee(R.drawable.e2,2, "ZYX002","Kanaya Calista", "Cashier", "Active");
            database.insertEmployee(R.drawable.e3,3, "ZYX003","Bintang Yumin", "Driver", "Active");
            database.insertEmployee(R.drawable.e4,4, "ZYX004","Ahmad Subardjo", "Dispatcher", "Non-Active");
            database.insertEmployee(R.drawable.e5,5, "ZYX005","Amelia Kamila", "Cashier", "Active");
            database.insertEmployee(R.drawable.e6,6, "ZYX006","Dewi Kinanti", "Transportlead", "Active");
            database.insertEmployee(R.drawable.e7,7, "ZYX007","Nisrina Zeira", "Transportlead", "Active");
            database.insertEmployee(R.drawable.e8,8, "ZYX008","Faisyal Daffa", "Superadmin", "Active");
        }



    }
}