package com.example.pocus;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SendsmsRequest extends StringRequest {
    final static private String URL = "http://qazx1110.dothome.co.kr/Sms.php";
    private Map<String, String> map;

    public SendsmsRequest(String Sender, String Contents, String Times, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("Sender", Sender);
        map.put("Contents", Contents);
        map.put("Times", Times);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
