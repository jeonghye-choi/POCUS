package com.example.pocus;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ShowData extends AppCompatActivity {

    private Button btn_returnkeypage;

    private static final String TAG = "ShowData";
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

        btn_returnkeypage = findViewById(R.id.btn_addkeypage);

        btn_returnkeypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowData.this, KeywordActivity.class);
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.dataView);
        dataList = new ArrayList<HashMap<String, String>>();
        getData("http://qazx1110.dothome.co.kr/Data.php");
    }

    protected void showList() {
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            data = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0; i<data.length();i++){
                JSONObject c = data.getJSONObject(i);
                String userKEY = c.getString((TAG_KEY));
                System.out.print(i);

                Log.d(TAG,"keyword => " + userKEY);

                HashMap<String,String> data = new HashMap<>();

                data.put(TAG_KEY,userKEY);

                dataList.add(data);
            }
            ListAdapter adapter = new SimpleAdapter(
                    ShowData.this, dataList, R.layout.list_item,
                    new String[]{TAG_KEY},
                    new int[]{R.id.userKEY}

            );
            list.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected java.lang.String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)
                            url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json+"\n");
                    }
                    return  sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected  void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


}
