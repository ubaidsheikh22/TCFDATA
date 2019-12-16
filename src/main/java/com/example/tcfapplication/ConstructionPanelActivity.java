package com.example.tcfapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;

public class ConstructionPanelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    public ActionBarDrawerToggle toggle;
    public AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_panel);

      toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setTitle("The Citizen Foundation");

      drawerLayout=findViewById(R.id.drawer_layout);
      navigationView=findViewById(R.id.navigationdrawer);

      navigationView.setNavigationItemSelectedListener(this);

ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
drawerLayout.setDrawerListener(toggle);
toggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id)
        {
            case R.id.createproject:
               Intent createproject = new Intent(ConstructionPanelActivity.this,CreateProject.class);
               startActivity(createproject);
                break;
            case R.id.list:
                Intent projlist = new Intent(ConstructionPanelActivity.this,ProjectList.class);
                startActivity(projlist);
                break;

            case R.id.conprogress:
                Intent consproj = new Intent(ConstructionPanelActivity.this,ConsProjProgress.class);
                startActivity(consproj);
                break;

            case R.id.mainprogress:
                Intent mainproj = new Intent(ConstructionPanelActivity.this,MainProjProgress.class);
                startActivity(mainproj);
                break;

            case R.id.site:
                Intent sitevisit = new Intent(ConstructionPanelActivity.this,Sitevisite.class);
                startActivity(sitevisit);
                break;

            case R.id.form:
                Intent fillform = new Intent(ConstructionPanelActivity.this,FillForm.class);
                startActivity(fillform);
                break;

            case R.id.chat:
                Intent chatbox = new Intent(ConstructionPanelActivity.this,ChatBox.class);
                startActivity(chatbox);
                break;

            case R.id.setting:
                Intent setting = new Intent(ConstructionPanelActivity.this,Setting.class);
                startActivity(setting);
                break;

            case R.id.logout:
                Intent logout = new Intent(ConstructionPanelActivity.this,LoginActivity.class);
                startActivity(logout);
                this.finish();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
