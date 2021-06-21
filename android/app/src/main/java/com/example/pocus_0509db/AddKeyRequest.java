package com.example.pocus_0509db;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddKeyRequest extends StringRequest {
    final static private String URL = "http://qazx1110.dothome.co.kr/Keyword.php";
    private Map<String, String> map;

    public AddKeyRequest(String userKey, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userKey",userKey);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
