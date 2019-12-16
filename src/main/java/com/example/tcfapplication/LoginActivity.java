package com.example.tcfapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText UserEmail;
    EditText Userpassword;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();   ///firebase insatance initialiazations///
        loginButton =(Button) findViewById(R.id.loginButton);
        UserEmail=(EditText)findViewById(R.id.etUser_name);
        Userpassword=(EditText)findViewById(R.id.etpassword);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        loginButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
               UserAcountLogin();
            }
        });
    }

    private void UserAcountLogin(){
        String email,password;
        email=UserEmail.getText().toString();
        password=Userpassword.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Provide Email To login", Toast.LENGTH_SHORT).show();
            UserEmail.setText("");
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Provide Password To login", Toast.LENGTH_SHORT).show();
            Userpassword.setText("");
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginActivity.this, ConstructionPanelActivity.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            Userpassword.setText("");
                        }
                    }
                });
    }
    }



