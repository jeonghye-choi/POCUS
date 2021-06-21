package com.example.pocus;

public class WidgetItem {
    int _id;
    String keyword;
    String sms;

    public WidgetItem(int _id, String keyword, String sms) {

        /* 위젯 아이템 객체 하나를 WidgetItem 이라고 할거임
            id 멤버 변수가 있고 keyword 받아온거 쓸거고 ?
            sms 문자 메세지 전체를 쓸거임
         */

        this._id = _id;
        this.keyword = keyword;
        this.sms = sms;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getkeyword() {
        return keyword;
    }

    public void setsms(String sms) {
        this.sms = sms;
    }
    public void setContent(String keyword) {
        this.keyword = keyword;
    }

    public int getViewType() {
        return 0;
    }
}