package com.Jsu.custombehavior;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CustomBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] myDataset = new String[]{"JAVA", "Objective-C", "C", "C++", "Swift",
                "GO", "JavaScript", "Python", "Ruby", "HTML", "SQL", "JAVA", "Objective-C", "C", "C++", "Swift",
                "GO", "JavaScript", "Python", "Ruby", "HTML", "SQL"};
        MyAdapter mAdapter = new MyAdapter(this, myDataset);

        recyclerView.setAdapter(mAdapter);
    }
}
