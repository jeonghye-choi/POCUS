package com.example.pocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

public class SendsmsActivitytest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_key);

        Intent smsIntent = getIntent();
        String Sender = smsIntent.getStringExtra("Sender");
        String Contents = smsIntent.getStringExtra("Contents");
        String Times = smsIntent.getStringExtra("Times");


            /*String sms_sender = "test".toString();
            String sms_contents = "test".toString();*/

        Response.Listener<String> responseListener = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");
                if (success) {
                    Toast.makeText(getApplicationContext(), "sms업로드 성공", Toast.LENGTH_SHORT).show();
                    /*Intent intent1 = new Intent(SendsmsActivitytest.this, NewAppWidget.class);
                    startActivity(intent1);*/
                } else {
                    Toast.makeText(getApplicationContext(), "sms업로드 실패", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };

        SendsmsRequest sendsmsRequest = new SendsmsRequest(Sender, Contents, Times, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SendsmsActivitytest.this);
        queue.add(sendsmsRequest);

    }
}