package com.example.redblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = (ListView) findViewById(R.id.listViewId);
        searchView = (SearchView) findViewById(R.id.searchViewId);
        databaseHelper = new DatabaseHelper(this);

        loadData();


        //Search View

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


    }

    public void loadData(){


        ArrayList<HashMap<String, String>> userList = databaseHelper.GetUsers();

        ListAdapter adapter = new SimpleAdapter(ListDataActivity.this,userList,R.layout.list_item_layout,new String[]{"name", "blood_group","gender","address"},
                new int[]{R.id.nametextViewId, R.id.bloodGroupTextViewId, R.id.genderTextViewId, R.id.addressTextViewId});
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected value : "+selectedValue,Toast.LENGTH_SHORT).show();

            }
        });
    }

    // view profile

    public void ViewProfile(View view){
        if(view.getId()==R.id.viewProfileButtonId){
            Intent intent = new Intent(ListDataActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
    }


    //call him
    public void CallHim(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }




// optionsMenu controlling

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.feedbackId){
            Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
