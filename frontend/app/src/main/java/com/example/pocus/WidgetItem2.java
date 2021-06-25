package com.example.pocus;

public class WidgetItem2 {
    int id;
    String calamity_sms;

    public WidgetItem2(int id, String calamity_sms) {

        /* 위젯 아이템 객체 하나를 WidgetItem 이라고 할거임
            id 멤버 변수가 있고 keyword 받아온거 쓸거고 ?
            sms 문자 메세지 전체를 쓸거임
         */

        this.id = id;
        this.calamity_sms = calamity_sms;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int _id) {
        this.id = _id;
    }

    public String getcalamity_sms() {
        return calamity_sms;
    }

    public void setcalamity_sms(String calamity_sms) {
        this.calamity_sms = calamity_sms;
    }

    public int getViewType() {
        return 1;
    }
}