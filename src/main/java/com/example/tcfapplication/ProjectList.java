package com.example.tcfapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ProjectList extends AppCompatActivity {

    String campusname[]=new String[]{"Karachi","Lahore","Hydrabad","Sukkhar","Dadu","Islamabad"};
    String Location[]=new String[]{"Karachi","Lahore","Hydrabad","Sukkhar","Dadu","Islamabad"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,campusname);
        listView.setAdapter(adapter);


        ListView listView1 = findViewById(R.id.list_view1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Location);
        listView1.setAdapter(adapter1);


        Spinner spinner =findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Region));
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter2);
    }
}
