package com.example.pocus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // 메시지를 수신하면 이 메소드가 자동으로 호출된다.
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 호출됨.");

        Bundle bundle = intent.getExtras(); // 번들 형태로 인텐트를 받아온다.
        SmsMessage smsMessage[] = ParseSms(bundle);

        if(smsMessage.length > 0){
            String sender = smsMessage[0].getDisplayOriginatingAddress();
            Log.d(TAG, "SMSReiver => Sender: " +  sender); // 발신번호
            String contents = smsMessage[0].getMessageBody();
            Log.d(TAG, "SMSReiceiver => Contents: " + contents);  // 메세지 내용
           Date receivedDate = new Date(smsMessage[0].getTimestampMillis());
           Log.d(TAG, "SMSReiver => Received Date: " + receivedDate); // 발신시간

           SendToActivity(context, sender, contents);
        }
    }

    private SmsMessage[] ParseSms(Bundle bundle){
        Object object[] = (Object[]) bundle.get("pdus"); // pdus 안에 SMS 데이터와 관련된 내용이 들어가 있다.
        SmsMessage smsMessage[] = new SmsMessage[object.length];

        for(int i = 0 ; i < object.length ; i++){
            // 마시멜로우 버전 이상인지 확인
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) object[i],  bundle.getString("format"));
            else
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) object[i]);
        }

        return smsMessage;
    }


    private void SendToActivity(Context context, String sender, String contents) {
        Intent intent = new Intent(context, MainActivity.class);

        ArrayList <String> arrayList = new ArrayList<String>();
        arrayList.add(sender);
        arrayList.add(contents);

        intent.putExtra("arrayList", arrayList);

        //context.startActivity(intent);
    }
}