package com.etwinkle.solutions.tikiri.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etwinkle.solutions.tikiri.adapter.ListAdapter;
import com.etwinkle.solutions.tikiri.helper.SQLiteHelper;
import com.etwinkle.solutions.tikiri.listener.Listener;
import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.listener.OnFinishListener;
import com.etwinkle.solutions.tikiri.model.Word;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements Listener {


    RecyclerView rvTop;
    RecyclerView rvBottom;
    TextView tvEmptyListTop;
    TextView tvEmptyListBottom;
    ImageView backArrow;

    private List<Word> wordList;
    private Word word;
    private ImageView imageView;
    private OnFinishListener onFinishListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        String level = intent.getStringExtra("level");
        wordList = SQLiteHelper.getWords(level);
        word = wordList.get(0);

        onFinishListener = new OnFinishListener() {
            @Override
            public void onFinish() {
                finish();
            }
        };

        rvTop = (RecyclerView) findViewById(R.id.rvTop);
        rvBottom = (RecyclerView) findViewById(R.id.rvBottom);
        tvEmptyListBottom = (TextView) findViewById(R.id.tvEmptyListBottom);
        tvEmptyListTop = (TextView) findViewById(R.id.tvEmptyListTop);
        backArrow = (ImageView) findViewById(R.id.back_arrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initTopRecyclerView();
        initBottomRecyclerView();

        tvEmptyListTop.setVisibility(View.GONE);
        tvEmptyListBottom.setVisibility(View.GONE);

        imageView = (ImageView) findViewById(R.id.image);
        Glide.with(getApplicationContext()).load(word.getImage()).into(imageView);
    }

    private void initTopRecyclerView() {
        rvTop.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));

        List<String> topList = new ArrayList<>();
        topList.add(word.getList().split(",")[1]);
        topList.add(word.getList().split(",")[0]);

        ListAdapter topListAdapter = new ListAdapter(topList, this,word,getApplicationContext(),onFinishListener);
        rvTop.setAdapter(topListAdapter);
        tvEmptyListTop.setOnDragListener(topListAdapter.getDragInstance());
        rvTop.setOnDragListener(topListAdapter.getDragInstance());
    }

    private void initBottomRecyclerView() {
        rvBottom.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));

        List<String> bottomList = new ArrayList<>();
        bottomList.add("__");
        bottomList.add("__");

        ListAdapter bottomListAdapter = new ListAdapter(bottomList, this,word,getApplicationContext(),onFinishListener);
        rvBottom.setAdapter(bottomListAdapter);
        tvEmptyListBottom.setOnDragListener(bottomListAdapter.getDragInstance());
        rvBottom.setOnDragListener(bottomListAdapter.getDragInstance());
    }

    @Override
    public void setEmptyListTop(boolean visibility) {
        tvEmptyListTop.setVisibility(visibility ? View.VISIBLE : View.GONE);
        rvTop.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setEmptyListBottom(boolean visibility) {
        tvEmptyListBottom.setVisibility(visibility ? View.VISIBLE : View.GONE);
        rvBottom.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }
}
