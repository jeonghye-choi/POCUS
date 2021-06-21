package com.example.pocus_0509db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView  tv_id, tv_pass;
    private Button btn_addkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        btn_addkey = findViewById(R.id.btn_addkey);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        tv_id.setText(userID);
        tv_pass.setText(userPass);

        btn_addkey.setOnClickListener(view -> {
            Intent intent_ = new Intent(MainActivity.this, KeywordActivity.class);
            startActivity(intent_);
        });
    }
}