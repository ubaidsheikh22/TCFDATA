package com.example.tcfapplication;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.example.tcfapplication.Chatmessage;
import com.example.tcfapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatBox extends AppCompatActivity {


    ListView messageList;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    private DatabaseReference databaseReference;
    EditText editText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        imageView = findViewById(R.id.submit_button);
        editText = findViewById(R.id.messageedittext);

        databaseReference = FirebaseDatabase.getInstance().getReference("ChatMessage");

        ///get a listview first//
        messageList = findViewById(R.id.list_of_message);
        ////put adapter to messagelist//

        ////display message from database////
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(Chatmessage.class).getMessageText().toString();
                arrayList.add(value);
                arrayAdapter = new ArrayAdapter(ChatBox.this, R.layout.support_simple_spinner_dropdown_item,arrayList);
                messageList.setAdapter(arrayAdapter);
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("ChatMessage").push().setValue(new Chatmessage(editText.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                editText.setText("");

            }
        });





    }



}