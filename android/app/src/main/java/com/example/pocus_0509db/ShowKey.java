package com.example.pocus_0509db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowKey extends AppCompatActivity {
    private Button btn_addkey2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_key);

        btn_addkey2 = findViewById(R.id.btn_addkey2);

        btn_addkey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowKey.this, KeywordActivity.class);
                startActivity(intent);
            }
        });
    }
}