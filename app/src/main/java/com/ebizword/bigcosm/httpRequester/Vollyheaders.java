package com.ebizword.bigcosm.httpRequester;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ebizword.bigcosm.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;



public class Vollyheaders {
    private Context activity;
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    private Map<String, String> map;
    String Url;
    String headers;
    int servicecode;

    public Vollyheaders(String url, final String header, final int servicecode, final AsyncTaskCompleteListener asyncTaskCompleteListener) {
        Url = url;
        this.headers = header;
        this.servicecode = servicecode;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        Log.d("url2", "Vollyheaders: " + url);
        Log.d("headers2", "Vollyheaders: " + header);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("ccccc", "onResponse: " + response);
                        if (response != null) {
                            asyncTaskCompleteListener.onTaskCompleted(response.toString(), servicecode);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            if (error instanceof NoConnectionError) {
//                                Log.d("amal", "volley requester 2" + error.toString());
//                                String msg = activity.getResources().getString(R.string.txt_network_not_available);
//                                Commonutils.showtoast(msg, activity);
//                                Commonutils.progressdialog_hide();
                            }
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                asyncTaskCompleteListener.onTaskCompleted(obj.toString(), servicecode);
                                Log.d("onErrorResponse", "onErrorResponse: " + obj);

                                error.printStackTrace();
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                    }
                }) {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization",header);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
