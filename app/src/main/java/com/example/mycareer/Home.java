package com.example.mycareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    FirebaseAuth auth;
    CardView searchcollege,searchcareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth=FirebaseAuth.getInstance();
        searchcareer=(CardView)findViewById(R.id.searchcareer);
        searchcollege=(CardView)findViewById((R.id.searchcollege));

        Searchcareer();
        Searchcollege();

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        mToggle=new ActionBarDrawerToggle(Home.this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void Searchcareer()
    {
        searchcareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,SearchCareer.class));
            }
        });
    }

    public void Searchcollege()
    {
        searchcollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,SearchCollege.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        //initializing firebase authentication object

        if (id == R.id.nav_account) {
            startActivity(new Intent(Home.this, MyProfile.class));
        } else if (id == R.id.nav_college) {
            startActivity(new Intent(Home.this, SearchCollege.class));
        } else if (id == R.id.nav_career) {
            startActivity(new Intent(Home.this, SearchCareer.class));
//        } else if (id == R.id.nav_wishlist){
//            startActivity(new Intent(Home.this, .class));
        } else if (id == R.id.nav_logout) {
            auth.signOut();
            finish();
            startActivity(new Intent(Home.this,MainActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        return true;
    }
}