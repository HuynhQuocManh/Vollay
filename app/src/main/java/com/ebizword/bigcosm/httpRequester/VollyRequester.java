package com.ebizword.bigcosm.httpRequester;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.ebizword.bigcosm.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.ebizword.bigcosm.R;
import com.ebizword.bigcosm.utils.Commonutils;
import com.ebizword.bigcosm.app.AppController;


/**
 * Created by Amal on 28-06-2015.
 */
public class VollyRequester {

    private Context activity;
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    private Map<String, String> map;
    int servicecode;

    // SeekbarTimer seekbar;

    public VollyRequester(Context activity, int method_type, Map<String, String> map, int servicecode, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        int method;
        this.activity = activity;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        this.map = map;
        this.servicecode = servicecode;
        if (method_type == 0)
            method = Request.Method.GET;
        else
            method = Request.Method.POST;
        String URL = map.get(Const.Params.URL);
        map.remove(Const.Params.URL);

        if (method == Request.Method.POST) {
            volley_requester(method, URL, (map == null) ? null : map);
            Log.d("url", "VollyRequesterpost: " + URL);
        } else {
            volley_requester(URL);
            Log.d("url", "VollyRequester: " + URL);
        }

    }


    public void volley_requester(int method, String url, final Map<String, String> requestbody) {
        StringRequest jsonObjRequest = new StringRequest(method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //seekbar.cancel();
                        if (response != null) {
                            Log.d("mmmmmm", "onResponse: " + response);
                            asyncTaskCompleteListener.onTaskCompleted(response.toString(), servicecode);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    if (error instanceof NoConnectionError) {
                        Log.d("amal", "volley requester 2" + error.toString());
                        String msg = activity.getResources().getString(R.string.txt_network_not_available);
                        Commonutils.showtoast(msg, activity);
                        Commonutils.progressdialog_hide();
                    }
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        String Error = obj.getString("error");
                        Log.d("error_code", "onErrorResponse:" + Error);
                        if (Error.contains("400")) {
                            Toast.makeText(activity, "lỗi token" + Error, Toast.LENGTH_SHORT).show();
                        }
                        asyncTaskCompleteListener.onTaskCompleted(obj.toString(), servicecode);
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

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params = requestbody;
                return params;
            }
        };
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                Const.TIMEOUT,
                Const.MAX_RETRY,
                Const.DEFAULT_BACKOFF_MULT));
        Log.d("bbb", "volley_requester: " + jsonObjRequest);
        String tag_string_req = "string_req";
        AppController.getInstance().addToRequestQueue(jsonObjRequest, tag_string_req);
    }

    public void volley_requester(String url) {
        Log.d("url11", "volley_requester: "+url);
        JsonObjectRequest jsongetrequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("SSSSS", "onResponse: "+response);
                if (response != null) {
                    asyncTaskCompleteListener.onTaskCompleted(response.toString(), servicecode);
                }
                //seekbar.cancel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Log.d("amal", "volley requester 2" + error.toString());
                    String msg = activity.getResources().getString(R.string.txt_network_not_available);
                    Commonutils.showtoast(msg, activity);
                    Commonutils.progressdialog_hide();

                }
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
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
        });
        jsongetrequest.setRetryPolicy(new DefaultRetryPolicy(
                Const.TIMEOUT,
                Const.MAX_RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsongetrequest);
    }


    private class SeekbarTimer extends CountDownTimer {

        public SeekbarTimer(long startTime, long interval) {
            super(startTime, interval);


        }

        @Override
        public void onFinish() {
            Toast.makeText(activity, activity.getResources().getString(R.string.txt_network_not_available), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onTick(long millisUntilFinished) {


        }
    }

    public void volley_headers(String url, final String token) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("ccccc", "onResponse: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                    }
                }) {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", token);
                return headers;
            }
        };
    }
}
