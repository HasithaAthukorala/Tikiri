package com.etwinkle.solutions.tikiri.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.helper.SQLiteHelper;
import com.etwinkle.solutions.tikiri.model.Level;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Button play;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        sqLiteHelper = new SQLiteHelper(this);

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,LevelActivity.class));
            }
        });
    }
}
