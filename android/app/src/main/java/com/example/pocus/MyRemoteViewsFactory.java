package com.example.pocus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * 런처 앱에 리스트뷰의 어뎁터 역할을 해주는 클래스
 */
public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    //context 설정하기
    public Context context = null;
    public ArrayList<WidgetItem> arrayList;
    public ArrayList<WidgetItem2> arrayList2;

    public MyRemoteViewsFactory(Context context) {
        this.context = context;
    }

    //DB를 대신하여 arrayList에 데이터를 추가하는 함수
    public void setData() {
        arrayList = new ArrayList<>();
        arrayList.add(new WidgetItem(1, "쿠팡", "쿠팡이 도착했어요"));
        arrayList.add(new WidgetItem(2, "무신사", "< 상품명 : 슬랙스 무신사가 도착했어요"));
        arrayList.add(new WidgetItem(3, "택배", "택배가 도착했습니다"));
        arrayList.add(new WidgetItem(4, "배달의민족", "배달의민족 - 배달이 도착했습니다"));
        arrayList.add(new WidgetItem(5, "로젠", "<로젠> 택배 문 앞에 두고갑니다"));
        arrayList.add(new WidgetItem(6, "우체국", "우체국 택배 도착했습니다"));
        arrayList.add(new WidgetItem(7, "SSG", "SSG 택배 도착"));
        arrayList.add(new WidgetItem(8, "한진", "한진 배송 완료"));
        arrayList.add(new WidgetItem(9, "CJ", "CJ 주문하신 물건이 도착했습니다"));


        arrayList2 = new ArrayList<>();
        arrayList2.add(new WidgetItem2(1, "안전재난문자 1단계"));
        arrayList2.add(new WidgetItem2(2, "안전재난문자 2단계"));
        arrayList2.add(new WidgetItem2(3, "안전재난문자 3단계"));
        arrayList2.add(new WidgetItem2(4, "안전재난문자 4단계"));
        arrayList2.add(new WidgetItem2(5, "안전재난문자 5단계"));
        arrayList2.add(new WidgetItem2(6, "안전재난문자 6단계"));
        arrayList2.add(new WidgetItem2(7, "안전재난문자 7단계"));
        arrayList2.add(new WidgetItem2(8, "안전재난문자 8단계"));
        arrayList2.add(new WidgetItem2(9, "안전재난문자 9단계"));
        arrayList2.add(new WidgetItem2(10, "안전재난문자 10단계"));
        arrayList2.add(new WidgetItem2(11, "안전재난문자 11단계"));
    }

    // arraylist 안에 객체 하나는 위젯 아이템이고 그 위젯 아이템 하나는 내가 원하는 키워드랑 문자 메세지
    // 넣어줄거임 => 나중에 디비에서 받아오겠지 ?

    //이 모든게 필수 오버라이드 메소드

    //실행 최초로 호출되는 함수
    @Override
    public void onCreate() {
        setData();
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

    }

    // 항목 개수를 반환하는 함수
    @Override
    public int getCount() {
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

        return views;
    }

        //RemoteViews listviewWidget2 = new RemoteViews(context.getPackageName(), R.layout.item_collection2);
        // 이렇게 해주고 싶은데 리턴이 하나밖에 안되잖아 적용을 어떻게 해줘 그럼 ?

        // 텍스트 뷰 하나에서 매인 텍스트는 keyword 로 할거야

        // 텍스트 뷰 하나에서 서브 텍스트는 sms 로 할거야

        //listviewWidget.setTextViewText(R.id.textmain1, arrayList2.get(position).calamity_sms);
        //listviewWidget2.setTextViewText(R.id.textmain1, arrayList2.get(position).calamity_sms);

        // 항목 선택 이벤트 발생 시 인텐트에 담겨야 할 항목 데이터를 추가해주는 코드
//        Intent dataIntent = new Intent();
//        dataIntent.putExtra("item_id", arrayList.get(position)._id);
//        dataIntent.putExtra("item_data", arrayList.get(position).content);
//        listviewWidget.setOnClickFillInIntent(R.id.text1, dataIntent);
        //setOnClickFillInIntent 브로드캐스트 리시버에서 항목 선택 이벤트가 발생할 때 실행을 의뢰한 인텐트에 각 항목의 데이터를 추가해주는 함수
        //브로드캐스트 리시버의 인텐트와 Extra 데이터가 담긴 인텐트를 함치는 역할을 한다.



    //로딩 뷰를 표현하기 위해 호출, 없으면 null
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
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