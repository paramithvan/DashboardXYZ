package com.example.testblog.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static Database dbHelper;
    private SQLiteDatabase db;

    public static Database getDbHelper(Context context) {
        if (dbHelper == null){
            synchronized (Database.class){
                if (dbHelper == null){
                    dbHelper = new Database(context);
                }
            }
        }
        return dbHelper;
    }

    public Database(@Nullable Context context){
        super(context, "testBlog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table employee(" +
                "photo INTEGER," +
                "id INTEGER primary key AUTOINCREMENT NOT NULL,"+
                "employeeId STRING," +
                "fullName TEXT," +
                "role TEXT," +
                "status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists employee");
    }

    public void insertEmployee(Integer photo, Integer id, String employeeId, String fullName,
                               String role, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("photo", photo);
        contentValues.put("id", id);
        contentValues.put("employeeId", employeeId);
        contentValues.put("fullName", fullName);
        contentValues.put("role", role);
        contentValues.put("status", status);

        db.insert("employee",null, contentValues);
    }

    public boolean checkDatabase(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM employee", null);

        cursor.getCount();

        return cursor.getCount() <= 0; //kalo ada data, return false
    }

    public ArrayList<EmployeeData> getEmployeeData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM employee", null);

        ArrayList<EmployeeData> employeeData = new ArrayList<>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            EmployeeData employeeData1 = new EmployeeData();
            employeeData1.setPhoto(cursor.getInt(0));
            employeeData1.setId(cursor.getInt(1));
            employeeData1.setEmployeeId(cursor.getString(2));
            employeeData1.setFullName(cursor.getString(3));
            employeeData1.setRole(cursor.getString(4));
            employeeData1.setStatus(cursor.getString(5));
            employeeData.add(employeeData1);
            cursor.moveToNext();
        }
        db.close();
        return  employeeData;
    }
}
