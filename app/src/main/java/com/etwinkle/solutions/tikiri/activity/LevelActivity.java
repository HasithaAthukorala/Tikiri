package com.etwinkle.solutions.tikiri.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.adapter.LevelAdapter;
import com.etwinkle.solutions.tikiri.adapter.ListAdapter;
import com.etwinkle.solutions.tikiri.helper.SQLiteHelper;
import com.etwinkle.solutions.tikiri.model.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Level> levelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        getSupportActionBar().hide();
        levelList = SQLiteHelper.getLevels();
        recyclerView = (RecyclerView) findViewById(R.id.level_list);
        ImageView backArrow = (ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initTopRecyclerView(levelList);
    }
    private void initTopRecyclerView(List<Level> list) {
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        LevelAdapter topListAdapter = new LevelAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(topListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        levelList = SQLiteHelper.getLevels();
        recyclerView = (RecyclerView) findViewById(R.id.level_list);
        ImageView backArrow = (ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initTopRecyclerView(levelList);
    }
}
