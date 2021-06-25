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
        Log.d(TAG, "onReceive() 호출됨. = " +intent.getAction() );



        if(intent.getAction() == "android.provider.Telephony.SMS_RECEIVED"){
            Bundle bundle = intent.getExtras(); // 번들 형태로 인텐트를 받아온다.
            SmsMessage smsMessage[] = ParseSms(bundle);

            if(smsMessage.length > 0){
                String sender = smsMessage[0].getOriginatingAddress();
                Log.d(TAG, "\n SMSReceiver => Sender: " + sender); // 발신번호
                String contents = smsMessage[0].getMessageBody();
                Log.d(TAG, "\n SMSReiceiver => Contents: " + contents);  // 메세지 내용
                Date receivedDate = new Date(smsMessage[0].getTimestampMillis());
                Log.d(TAG, "\n SMSReceiver => Received Date: " + receivedDate); // 발신시간

                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String Times = transFormat.format(receivedDate);


                Intent intent1 = new Intent(context, SendsmsActivitytest.class);
                intent1.putExtra("Sender", sender);
                intent1.putExtra("Contents", contents);
                intent1.putExtra("Times", Times);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);

                //SendToMyRemoteViewsFactory(context, sender, contents);
            }
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


    private void SendToMyRemoteViewsFactory(Context context, String sender, String contents) {
        Intent intent = new Intent(context, MyRemoteViewsFactory.class);

        ArrayList <String[]> arrayList3 = new ArrayList<String[]>();
        arrayList3.add(new String[] {sender,contents});

        for(int i=0; i<arrayList3.size(); i++) {
            System.out.println();
            System.out.println(arrayList3.get(i)[0]);
            System.out.println(arrayList3.get(i)[1]);
        }
        intent.putExtra("arrayList3", arrayList3);
        Log.d(TAG, "putExtra 함");
        // intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // context.sendBroadcast(intent);

        context.startActivity(intent);
    }
}