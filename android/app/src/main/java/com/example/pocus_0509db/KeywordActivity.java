package com.example.pocus_0509db;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class KeywordActivity extends AppCompatActivity {

    private EditText et_key;
    private Button btn_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);

        et_key = findViewById(R.id.et_key);
        btn_key = findViewById(R.id.btn_key);

        btn_key.setOnClickListener(v -> {
            String userKey = et_key.getText().toString();

            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        Toast.makeText(getApplicationContext(), "키워드 등록이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KeywordActivity.this, ShowKey.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "키워드 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };
            AddKeyRequest addKeyRequest = new AddKeyRequest(userKey, responseListener);
            RequestQueue queue = Volley.newRequestQueue(KeywordActivity.this);
            queue.add(addKeyRequest);
        });
    }
}