package com.example.tcfapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ProjectList extends AppCompatActivity{


    ListView camplistView,locationlistview;
    FirebaseDatabase firebaseDatabase ,firebaseDatabase1;
    DatabaseReference databaseReference,databaseReference1;
    ArrayAdapter campusarradapter,locationarradapter;
    ArrayList<String> camusearrlist =new ArrayList<String>();
    ArrayList<String> locationarrlist =new ArrayList<String>();
    public String selectedregion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        firebaseDatabase = FirebaseDatabase.getInstance();


        camplistView = (ListView) findViewById(R.id.list_view);
        locationlistview = (ListView) findViewById(R.id.locationlist_view);


//   delete list data from firebase///

        camplistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String itemid = camusearrlist.get(position).toString();
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProjectList.this);
                dialogBuilder.setTitle("Do You Want To Finish! " + itemid + " Project");
                dialogBuilder.setPositiveButton("Yes...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        Query applesQuery = ref.child("CreateProject").orderByChild("campusnametxt").equalTo(itemid);

                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                    Intent intent = new Intent(ProjectList.this, ProjectList.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogBuilder.create().show();

                return true;

            }
        });


        Spinner spinner = findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Region));
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter2);

        ///for selected item////
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                camusearrlist.clear();
                locationarrlist.clear();
                selectedregion = parent.getItemAtPosition(position).toString();
                showlist();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ///for selected item////

    }
private void showlist()
{

        ////for picup campus list on selected region ///
        DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference("CreateProject");
        Query regionqeury =databaseReference.orderByChild("regiontxt").equalTo(selectedregion);
       regionqeury.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                CreateProjectData createProjectData =dataSnapshot.getValue(CreateProjectData.class);
                String value=createProjectData.campusnametxt;
                camusearrlist.add(value);
                campusarradapter = new ArrayAdapter(ProjectList.this, R.layout.support_simple_spinner_dropdown_item, camusearrlist);
                camplistView.setAdapter(campusarradapter);



                String value1 =createProjectData.locationtxt;
                locationarrlist.add(value1);
                locationarradapter = new ArrayAdapter(ProjectList.this, R.layout.support_simple_spinner_dropdown_item, locationarrlist);
                locationlistview.setAdapter(locationarradapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    ////for picup campus list on selected region ///
}
