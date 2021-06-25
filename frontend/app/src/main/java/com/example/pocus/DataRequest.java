package com.example.pocus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRequest extends Activity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_KEY = "userKEY";

    JSONArray data = null;

    ArrayList<HashMap<String, String>> dataList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
    }
}
