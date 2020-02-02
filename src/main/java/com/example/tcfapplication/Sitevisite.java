package com.example.tcfapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Sitevisite extends AppCompatActivity {
    Spinner campusname,locationname;
    private static int RESULT_LOAD_IMAGE = 1;
    ImageView imageView;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ProgressBar progressBar;
    Uri selectedImage;
    ProgressDialog progressDialog;
    String Location,Campusnametxt,UploadFor;
    FirebaseAuth firebaseAuth;
    ArrayAdapter arrayAdapter;
    ArrayList<String> campusarrlist=new ArrayList<String>();
    ArrayList<String> locationarrlist=new ArrayList<String>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference fDatabaseRoot = database.getReference("CreateProject");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitevisite);
        imageView = findViewById(R.id.image_view);
        campusname = findViewById(R.id.selcampusgdrop);
        locationname = findViewById(R.id.sellocdrop);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(Sitevisite.this);

        storageReference=FirebaseStorage.getInstance().getReference("SiteVisitImages");
        databaseReference =FirebaseDatabase.getInstance().getReference("SiteVisitImages");






        fDatabaseRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               CreateProjectData createProjectData = dataSnapshot.getValue(CreateProjectData.class);
                String value=createProjectData.campusnametxt;
                campusarrlist.add(value);
                arrayAdapter = new ArrayAdapter(Sitevisite.this,R.layout.support_simple_spinner_dropdown_item,campusarrlist);
                campusname.setAdapter(arrayAdapter);
                campusname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Campusnametxt =  parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                String value1=createProjectData.locationtxt;
                locationarrlist.add(value1);
                ArrayAdapter<String> locdrop = new ArrayAdapter<String>(Sitevisite.this, R.layout.support_simple_spinner_dropdown_item,locationarrlist);
                locdrop.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                locationname.setAdapter(locdrop);
                locationname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Location=parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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







        Spinner spinner2 = findViewById(R.id.browsimage);
        ArrayAdapter<String> browsimagedrop = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.Images));
        browsimagedrop.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(browsimagedrop);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UploadFor=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button buttonLoadImage = (Button) findViewById(R.id.chosefile);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });

        Button buttonUpload = (Button) findViewById(R.id.Upload);
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();

            Picasso.get().load(selectedImage).into(imageView);
        }
    }

    private  String getFileExtention(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void UploadImage(){

        if(selectedImage!=null){
            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(selectedImage));
            reference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    try {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url =uri.toString();
                                SiteVisitImages data = new SiteVisitImages(Location,Campusnametxt,UploadFor,url);
                                String imageuploadId=databaseReference.push().getKey();
                                databaseReference.child(imageuploadId).setValue(data);
                                Toast.makeText(Sitevisite.this, " Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });



                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Sitevisite.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress =(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Sitevisite.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        else {

            Toast.makeText(Sitevisite.this, "Please Select Image to Upload", Toast.LENGTH_LONG).show();

        }

    }
}
//
//
//
