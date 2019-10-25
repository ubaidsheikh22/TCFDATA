package com.example.tcfapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton =(Button) findViewById(R.id.loginButton);
        username=(EditText)findViewById(R.id.etUser_name);
        password=(EditText)findViewById(R.id.etpassword);
        loginButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(username.getText().toString().trim().length()==0 || password.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"User name or Password is Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    openSelectRegionActivity();
                }
            }
        });
    }
    public void openSelectRegionActivity()
    {
        Intent intent=new Intent(this,ConstructionPanelActivity.class);
        startActivity(intent);
        finish();

    }
}

