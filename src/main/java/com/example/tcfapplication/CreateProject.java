package com.example.tcfapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProject extends AppCompatActivity {

    EditText  Location,area,campusname, primaray,hsecondary,
              coverdarea,plotsize,secondary,projectcode,
              sessiondate,buildyear,requestiondate,donordate;
    Button create;
    DatabaseReference databaseCreateProject;
    Spinner Regionspinner,categoryspinner,ProjectIncharge,AccountManager,Execution;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        databaseCreateProject= FirebaseDatabase.getInstance().getReference("CreateProject");

        ///all elements initialization///
        Location=findViewById(R.id.locationtxt);
        area=findViewById(R.id.area);
        campusname=findViewById(R.id.campustxt);
        primaray=findViewById(R.id.primarytxt);
        hsecondary=findViewById(R.id.hsecondarytxt);
        coverdarea=findViewById(R.id.coverdtxt);
        plotsize=findViewById(R.id.plotsizetxt);
        secondary=findViewById(R.id.secondarytxt);
        projectcode=findViewById(R.id.projectcodetxt);
        sessiondate=findViewById(R.id.sessiondate);
        buildyear=findViewById(R.id.buildyeartxt);
        requestiondate=findViewById(R.id.requestiondate);
        donordate=findViewById(R.id.donordate);
        create=findViewById(R.id.createproject);


        Regionspinner =findViewById(R.id.Region);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Region));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Regionspinner.setAdapter(adapter);


        categoryspinner =findViewById(R.id.categoryspinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.category));
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categoryspinner.setAdapter(adapter1);


        ProjectIncharge =findViewById(R.id.projincharge);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.ProjectIncharge));
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ProjectIncharge.setAdapter(adapter2);

        AccountManager =findViewById(R.id.accountmanager);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.AccountManager));
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        AccountManager.setAdapter(adapter3);


        Execution =findViewById(R.id.Execution);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.Execution));
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Execution.setAdapter(adapter4);

create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
            CreateProjectMethod();
    }
});




    }


    private void CreateProjectMethod(){
        String  Locationtxt,areatxt,campusnametxt,Regiontxt,
                Categorytxt,projectinchargetxt,executiontxt,accountmanagertxt,
                primaraytxt,hsecondarytxt,coverdareatxt,plotsizetxt,secondarytxt
                ,projectcodetxt,sessiondatetxt,buildyeartxt,requestiondatetxt,donordatetxt;

        Locationtxt=Location.getText().toString().trim();
        areatxt=area.getText().toString().trim();
        Regiontxt=Regionspinner.getSelectedItem().toString().trim();
        Categorytxt=categoryspinner.getSelectedItem().toString().trim();
        projectinchargetxt=ProjectIncharge.getSelectedItem().toString().trim();
        executiontxt=Execution.getSelectedItem().toString().trim();
        accountmanagertxt=AccountManager.getSelectedItem().toString().trim();
        campusnametxt=campusname.getText().toString().trim();
        primaraytxt=primaray.getText().toString().trim();
        hsecondarytxt=hsecondary.getText().toString().trim();
        coverdareatxt=coverdarea.getText().toString().trim();
        plotsizetxt=plotsize.getText().toString().trim();
        secondarytxt=secondary.getText().toString().trim();
        projectcodetxt=projectcode.getText().toString().trim();
        sessiondatetxt=sessiondate.getText().toString().trim();
        buildyeartxt=buildyear.getText().toString().trim();
        requestiondatetxt=requestiondate.getText().toString().trim();
        donordatetxt=donordate.getText().toString().trim();

        try {

            String id =databaseCreateProject.push().getKey();
            CreateProjectData data = new CreateProjectData(id,Locationtxt,areatxt,campusnametxt,
                    Regiontxt,projectinchargetxt,Categorytxt,executiontxt,accountmanagertxt,
                    primaraytxt,hsecondarytxt,coverdareatxt,plotsizetxt,secondarytxt,projectcodetxt
                    ,sessiondatetxt,buildyeartxt,requestiondatetxt,donordatetxt);

            databaseCreateProject.child(id).setValue(data);
            Toast.makeText(this, "Project Created Successfully ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception exception){
            Toast.makeText(this, "Cannot Submited", Toast.LENGTH_SHORT).show();
        }


}

}
