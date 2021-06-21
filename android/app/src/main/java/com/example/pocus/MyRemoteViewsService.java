package com.example.pocus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViewsService;


/**
 * RemoteViewsService를 상속받은 개발자 서비스 클래스
 * RemoteViesFactory를 얻을 목적으로 인텐트 발생에 의해 실행됩니다.
 */

public class MyRemoteViewsService extends RemoteViewsService {
    public MyRemoteViewsService() {
    }

    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewsFactory(this.getApplicationContext());
    }

   /* @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}