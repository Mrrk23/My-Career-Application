package com.example.mycareer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SearchCareer extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter2 myAdapter2;
    List<String> myCareerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_career);
        myCareerList = new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_Car);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter2 = new MyAdapter2(SearchCareer.this,myCareerList);
        recyclerView.setAdapter(myAdapter2);

        myCareerList.add("Computer engineering");
        myCareerList.add("Electrical engineering");
        myCareerList.add("Civil engineering");
        myCareerList.add("Mechanical engineering");
        myCareerList.add("Bachelor of Business Administration");
        myCareerList.add("Bachelor of Pharmacy");
        myCareerList.add("Bachelor of Medicine, Bachelor of Surgery");
        myCareerList.add("Bachelor of Science");
    }
}