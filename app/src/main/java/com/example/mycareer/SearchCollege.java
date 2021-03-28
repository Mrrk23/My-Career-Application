package com.example.mycareer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class SearchCollege extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CollegeData> myCollegeList;
    CollegeData collegeData;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_college);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchCollege.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        myCollegeList = new ArrayList<>();

        collegeData = new CollegeData("Charusat University","Anand, Gujarat","",R.drawable.charusat_1);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Nirma University","Ahmedabad, Gujarat","",R.drawable.nirma);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("PDPU University","Gift city,Gandhinagar","",R.drawable.pdpu);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("LD College of Engineering","Ahmedabad, Gujarat","",R.drawable.ld);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Maharaja Sahajirav University","Vadodara, Gujarat","",R.drawable.msu);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Dharmsinh Desai University","Nadiad, Gujarat","",R.drawable.ddu);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Sardar Vallabhbhai National Institute of Technology","Surat, Gujarat","",R.drawable.svnit);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Indian Institute of Technology","Gandhinagar, Gujarat","",R.drawable.iit);
        myCollegeList.add(collegeData);
        collegeData = new CollegeData("Dhirubhai Ambani Institute of Information and Communication Technology","Gandhinagar, Gujarat","",R.drawable.daiict);
        myCollegeList.add(collegeData);

        myAdapter = new MyAdapter(SearchCollege.this,myCollegeList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}

