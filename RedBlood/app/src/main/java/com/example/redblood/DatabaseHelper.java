package com.example.redblood;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "BloodDonor.db";
    private static final String TABLE_NAME = "donor_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String BLOOD_GROUP = "Blood_group";
    private static final String GENDER = "Gender";
    private static final String MOBILE_NUMBER = "Mobile_number";
    private static final String ADDRESS = "Address";

    private static final int VERSION_NUMBER = 16;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(50) NOT NULL,"+EMAIL+" TEXT NOT NULL,"+USERNAME+" VARCHAR(50)  NOT NULL,"+PASSWORD+" TEXT NOT NULL,"+BLOOD_GROUP+" VARCHAR(25),"+GENDER+" TEXT NOT NULL,"+MOBILE_NUMBER+" INTEGER NOT NULL,"+ADDRESS+" TEXT NOT NULL);";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context context;



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, VERSION_NUMBER);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(CREATE_TABLE);

            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();

            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }

    }



    public Cursor showAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return  cursor;
    }




    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "select name, blood_group, gender, address,mobile_number from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(NAME)));
            user.put("blood_group",cursor.getString(cursor.getColumnIndex(BLOOD_GROUP)));
            user.put("gender",cursor.getString(cursor.getColumnIndex(GENDER)));
            user.put("address",cursor.getString(cursor.getColumnIndex(ADDRESS)));
            user.put("mobile_number",cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)));
            userList.add(user);
        }
        return  userList;
    }




//update data
    public Boolean updateData(String id,String name,String blood_group){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(BLOOD_GROUP,blood_group);

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return true;

    }

    //delete data
    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
        return value;
    }

    //insert data or sign up

    public long insertData(UserDetails userDetails) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, userDetails.getName());
        contentValues.put(EMAIL, userDetails.getEmail());
        contentValues.put(PASSWORD, userDetails.getPassword());
        contentValues.put(USERNAME, userDetails.getUsername());
        contentValues.put(GENDER, userDetails.getGender());
        contentValues.put(BLOOD_GROUP, userDetails.getBlood_group());
        contentValues.put(MOBILE_NUMBER, userDetails.getPhone_number());
        contentValues.put(ADDRESS, userDetails.getAddress());

        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowid;

    }



    public Boolean findPassword(String username, String password) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        Boolean result = false;


        if (cursor.getCount() == 0) {

            Toast.makeText(context, "No data is found,please sinup first", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String uname = cursor.getString(3);
                String pass = cursor.getString(4);

                if (uname.equals(username) && pass.equals(password)) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }



}
