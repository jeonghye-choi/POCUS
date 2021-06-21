package com.example.pocus;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 런처 앱에 리스트뷰의 어뎁터 역할을 해주는 클래스
 */


public class MyRemoteViewsFactory extends AppCompatActivity implements RemoteViewsService.RemoteViewsFactory {

    String myJSON;
    String mySmsJSON;
    String myDisasJSON;
    JSONArray data = null;
    JSONArray smsdata = null;
    JSONArray disasdata = null;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_SMS_RESULTS = "result_sms";
    private static final String TAG_DISAS_RESULTS = "result_disas";
    private static final String TAG_KEY = "userKEY";
    private static final String TAG_SMS = "Contents";
    private static final String TAG_DISAS = "message";
    private static final String TAG = "ShowData";

    //context 설정하기
    public Context context = null;
    public ArrayList<WidgetItem> arrayList;
    public ArrayList<WidgetItem2> arrayList2;
    public ArrayList<String> array_key = new ArrayList<String>(50);
    public ArrayList<String> array_sms = new ArrayList<String>(50);
    public ArrayList<String> array_disas = new ArrayList<String>(50);


    public MyRemoteViewsFactory(Context context) {
        this.context = context;
    }



    protected void showList() {
        try{
            Log.d(TAG, "4");
            JSONObject jsonObj = new JSONObject(myJSON);
            data = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i = 0; i<data.length();i++){
                JSONObject c = data.getJSONObject(i);
                String userKEY = c.getString((TAG_KEY));
                Log.d(TAG,"i => " + i);

                Log.d(TAG,"keyword 3 => " + userKEY);
                array_key.add(i, userKEY);
            }
            Log.d(TAG, "5");
            //setData();
            Log.d(TAG, "6");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void showSmsList() {
        try{
          //  Log.d(TAG, "66");
            JSONObject jsonSMSObj = new JSONObject(mySmsJSON);
            //Log.d(TAG, "55");
            smsdata = jsonSMSObj.getJSONArray(TAG_SMS_RESULTS);

            for(int i = 0; i<smsdata.length();i++){
                JSONObject c = smsdata.getJSONObject(i);
                String Contents = c.getString((TAG_SMS));
                Log.d(TAG,"i => " + i);

                Log.d(TAG,"keyword 4 => " + Contents);
                array_sms.add(i, Contents);
            }

           // Log.d(TAG, "88");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void showDisasList() {
        try{
            Log.d(TAG, "66");
            JSONObject jsonDisasObj = new JSONObject(myDisasJSON);
            Log.d(TAG, "55");
            disasdata = jsonDisasObj.getJSONArray(TAG_DISAS_RESULTS);

            for(int i = 0; i<disasdata.length();i++){
                JSONObject c = disasdata.getJSONObject(i);
                String message = c.getString((TAG_DISAS));
                Log.d(TAG,"i => " + i);

                Log.d(TAG,"keyword 5 => " + message);
                array_disas.add(i, message);
            }

            Log.d(TAG, "88");
            setData();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //DB를 대신하여 arrayList에 데이터를 추가하는 함수
    public void setData() {
        //Intent intent = getIntent();
//        for(int i=0; i<array_key.size(); i++){
//            Log.d(TAG, "키워드 몇번째 ? " + i + " " + array_key.get(i));
//        }
//        for(int i=0; i<array_sms.size(); i++){
//            Log.d(TAG, "SMS 몇번째 ? " + i + " " + array_sms.get(i));
//        }
        int count = 0;
        for(int i=0 ; i<array_key.size(); i++){
            for(int j=0; j<array_sms.size(); j++) {
                if(array_sms.get(j) == "null" || array_key.get(i) =="null")
                    ;
                else{
                    if (array_sms.get(j).contains(array_key.get(i)))  {
                        Log.d(TAG, "키워드와 SMS가 일치합니다 \n" + "키워드 = " + array_key.get(i) + "\n" + "문자 = " + array_sms.get(j) + "\n");
                        arrayList.add(count,new WidgetItem(count, array_key.get(i), array_sms.get(j)));
                        count++;
                    }
                }
            }
        }

        //Log.d(TAG, "setData_test ==> " + a);
//        arrayList = new ArrayList<>();
//        arrayList.add(new WidgetItem(1, array_key.get(0), array_sms.get(0)));
//        arrayList.add(new WidgetItem(2, array_key.get(1), array_sms.get(1)));
//        arrayList.add(new WidgetItem(3, array_key.get(2), array_sms.get(2)));
//        arrayList.add(new WidgetItem(4, array_key.get(3), array_sms.get(3)));
//        arrayList.add(new WidgetItem(5, array_key.get(4), array_sms.get(4)));
//        arrayList.add(new WidgetItem(6, array_key.get(5), array_sms.get(5)));
//        arrayList.add(new WidgetItem(7, array_key.get(6), array_sms.get(6)));
//        arrayList.add(new WidgetItem(8, array_key.get(7), array_sms.get(7)));
//        arrayList.add(new WidgetItem(9, array_key.get(8), array_sms.get(8)));


        int count2 = 0;
        for(int i=0 ; i<array_disas.size(); i++){
            arrayList2.add(count2,new WidgetItem2(count2,array_disas.get(i)));
            count2++;
        }



//        arrayList2 = new ArrayList<>();
//        arrayList2.add(new WidgetItem2(1, array_disas.get(0)));
//        arrayList2.add(new WidgetItem2(2, "안전재난문자 2단계"));
//        arrayList2.add(new WidgetItem2(3, "안전재난문자 3단계"));
//        arrayList2.add(new WidgetItem2(4, "안전재난문자 4단계"));
//        arrayList2.add(new WidgetItem2(5, "안전재난문자 5단계"));
//        arrayList2.add(new WidgetItem2(6, "안전재난문자 6단계"));
//        arrayList2.add(new WidgetItem2(7, "안전재난문자 7단계"));
//        arrayList2.add(new WidgetItem2(8, "안전재난문자 8단계"));
//        arrayList2.add(new WidgetItem2(9, "안전재난문자 9단계"));
//        arrayList2.add(new WidgetItem2(10, "안전재난문자 10단계"));
//        arrayList2.add(new WidgetItem2(11, "안전재난문자 11단계"));
    }

    public void fakedata() {
        //Intent intent = getIntent();
        for(int i = 0; i<50; i++){
            array_key.add("null");
            array_sms.add("null");
            array_disas.add("null");
        }
        arrayList = new ArrayList<>();
        arrayList.add(new WidgetItem(1, "null", "null"));
        arrayList.add(new WidgetItem(2, "null", "null"));
        arrayList.add(new WidgetItem(3, "null", "null"));
        arrayList.add(new WidgetItem(4, "null", "null"));
        arrayList.add(new WidgetItem(5, "null", "null"));
        arrayList.add(new WidgetItem(6, "null", "null"));
        arrayList.add(new WidgetItem(7, "null", "null"));
        arrayList.add(new WidgetItem(8, "null", "null"));
        arrayList.add(new WidgetItem(9, "null", "null"));

        arrayList2 = new ArrayList<>();
        arrayList2.add(new WidgetItem2(1, "null"));
        arrayList2.add(new WidgetItem2(2, "null"));
        arrayList2.add(new WidgetItem2(3, "null"));
        arrayList2.add(new WidgetItem2(4, "null"));
        arrayList2.add(new WidgetItem2(5, "null"));
        arrayList2.add(new WidgetItem2(6, "null"));
        arrayList2.add(new WidgetItem2(7, "null"));
        arrayList2.add(new WidgetItem2(8, "null"));
        arrayList2.add(new WidgetItem2(9, "null"));
        arrayList2.add(new WidgetItem2(10, "null"));
        arrayList2.add(new WidgetItem2(11, "null"));
    }

    // arraylist 안에 객체 하나는 위젯 아이템이고 그 위젯 아이템 하나는 내가 원하는 키워드랑 문자 메세지
    // 넣어줄거임 => 나중에 디비에서 받아오겠지 ?

    //이 모든게 필수 오버라이드 메소드

    //실행 최초로 호출되는 함수



    @Override
    public void onCreate() {
        //requirePerms();
        //  퍼미션이 없을때 퍼미션 가져오기 ! 본함수는 첫번째에

        Log.d(TAG, "1");

        getData("http://qazx1110.dothome.co.kr/Data.php");
        getSmsData("http://qazx1110.dothome.co.kr/SmsData.php");
        getDisasData("http://qazx1110.dothome.co.kr/DisasData.php");
        fakedata();
//        Button btn_sync = (Button) findViewById(R.id.btn_sync);
//        btn_sync.setOnClickListener(v -> {
//            Log.d(TAG, "버튼클릭");
//        });
//        btn_event();
    }

//    public void btn_event(){
//
//    }

    public void getData(String url) {
        Log.d(TAG, "2");
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected java.lang.String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                //Log.d(TAG, "2.5");
                try{
                  // Log.d(TAG, "2.75");
                    URL url = new URL(uri);
                  //  Log.d(TAG, "2.76");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                  //  Log.d(TAG, "2.77");
                    StringBuilder sb = new StringBuilder();
                  //  Log.d(TAG, "2.78");

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                  //  Log.d(TAG, "2.8");

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
                Log.d(TAG, "3");
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    public void getSmsData(String urL){
        Log.d(TAG, "2");
        class GetDataJSONSMS extends AsyncTask<String, Void, String> {

            @Override
            protected java.lang.String doInBackground(String... params) {
                String urI = params[0];

                BufferedReader bufferedReader = null;
                //Log.d(TAG, "2.5");
                try{
                    // Log.d(TAG, "2.75");
                    URL urL = new URL(urI);
                    //  Log.d(TAG, "2.76");
                    HttpURLConnection con = (HttpURLConnection) urL.openConnection();
                    //  Log.d(TAG, "2.77");
                    StringBuilder sb = new StringBuilder();
                    //  Log.d(TAG, "2.78");

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    //  Log.d(TAG, "2.8");

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
            protected  void onPostExecute(String result_SMS) {
                Log.d(TAG, "77");
                mySmsJSON = result_SMS;
                showSmsList();
            }
        }
        GetDataJSONSMS g = new GetDataJSONSMS();
        g.execute(urL);

    }

    public void getDisasData(String Url){
        Log.d(TAG, "2");
        class GetDataJSONDisas extends AsyncTask<String, Void, String> {

            @Override
            protected java.lang.String doInBackground(String... params) {
                String Uri = params[0];

                BufferedReader bufferedReader = null;
                //Log.d(TAG, "2.5");
                try{
                    // Log.d(TAG, "2.75");
                    URL Url = new URL(Uri);
                    //  Log.d(TAG, "2.76");
                    HttpURLConnection con = (HttpURLConnection) Url.openConnection();
                    //  Log.d(TAG, "2.77");
                    StringBuilder sb = new StringBuilder();
                    //  Log.d(TAG, "2.78");

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    //  Log.d(TAG, "2.8");

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
            protected  void onPostExecute(String result_Disas) {
                Log.d(TAG, "77");
                myDisasJSON = result_Disas;
                showDisasList();
            }
        }
        GetDataJSONDisas g = new GetDataJSONDisas();
        g.execute(Url);

    }

    //항목 추가 및 제거 등 데이터 변경이 발생했을 때 호출되는 함수
    //브로드캐스트 리시버에서 notifyAppWidgetViewDataChanged()가 호출 될 때 자동 호출
    @Override
    public void onDataSetChanged() {
       setData();
    }

    //마지막에 호출되는 함수
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 항목 개수를 반환하는 함수
    @Override
    public int getCount() {
        Log.d(TAG,"array size ==> " + arrayList.size());
        return arrayList.size();
    }

    //각 항목을 구현하기 위해 호출, 매개변수 값을 참조하여 각 항목을 구성하기위한 로직이 담긴다.
    // 항목 선택 이벤트 발생 시 인텐트에 담겨야 할 항목 데이터를 추가해주어야 하는 함수
    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views;

        views = new RemoteViews(context.getPackageName(), R.layout.item_collection);
        views.setTextViewText(R.id.textmain, arrayList.get(position).keyword);
        views.setTextViewText(R.id.textsub, arrayList.get(position).sms);
        views.setTextViewText(R.id.textsms, arrayList2.get(position).calamity_sms);

        return views;
    }

    //로딩 뷰를 표현하기 위해 호출, 없으면 null
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }


    @Override
    public int getViewTypeCount() {
        return 1;
    }


    //각 항목의 식별자 값을 얻기 위해 호출
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 같은 ID가 항상 같은 개체를 참조하면 true 반환하는 함수
    @Override
    public boolean hasStableIds() {
        return false;
    }


}