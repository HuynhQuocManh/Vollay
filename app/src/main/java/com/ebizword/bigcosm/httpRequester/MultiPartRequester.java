package com.ebizword.bigcosm.httpRequester;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.Map;

import com.ebizword.bigcosm.R;
import com.ebizword.bigcosm.utils.AndyUtils;
import com.ebizword.bigcosm.utils.Const;

/**
 * @author Hardik A Bhalodi
 */
public class MultiPartRequester {

    private Map<String, String> map;
    private AsyncTaskCompleteListener mAsynclistener;
    private int serviceCode;

    private HttpClient httpclient;
    private Activity activity;
    private AsyncHttpRequest request;

    public MultiPartRequester(Activity activity, Map<String, String> map,
                              int serviceCode, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        this.map = map;
        this.serviceCode = serviceCode;
        this.activity = activity;

        // is Internet Connection Available...

        if (AndyUtils.isNetworkAvailable(activity)) {
            mAsynclistener = (AsyncTaskCompleteListener) asyncTaskCompleteListener;
            request = (AsyncHttpRequest) new AsyncHttpRequest().execute(map
                    .get("url"));
        } else {
            showToast(activity.getResources().getString(R.string.txt_network_not_available));
        }

    }

    class AsyncHttpRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            Log.d("mahi", "map sending"+map);
            map.remove("url");
            try {
                HttpPost httppost = new HttpPost(urls[0]);
               httpclient = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(
                        httpclient.getParams(), 60000);

                MultipartEntityBuilder builder = MultipartEntityBuilder
                        .create();
                for (String key : map.keySet()) {
                    Log.d("mahi"," " +key + "---->" + map.get(key));

                    if (key.equalsIgnoreCase(Const.Params.PICTURE) ||key.equalsIgnoreCase(Const.Params.DOC_URL)||key.equalsIgnoreCase(Const.Params.CAR_IMAGE) ) {
                        File f = new File(map.get(key));

                        builder.addBinaryBody(key, f,
                                ContentType.MULTIPART_FORM_DATA, f.getName());
                    } else {
                        builder.addTextBody(key, map.get(key), ContentType
                                .create("text/plain", MIME.UTF8_CHARSET));
                    }
                    // System.out.println(key + "---->" + map.get(key));

                }

                httppost.setEntity(builder.build());

                ActivityManager manager = (ActivityManager) activity
                        .getSystemService(Context.ACTIVITY_SERVICE);

                if (manager.getMemoryClass() < 25) {
                    System.gc();
                }
                HttpResponse response = httpclient.execute(httppost);

                String responseBody = EntityUtils.toString(
                        response.getEntity(), "UTF-8");

                return responseBody;

            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError oume) {
                System.gc();

                Toast.makeText(
                        activity.getParent().getParent(),
                        activity.getString(R.string.txt_out_of_memory),
                        Toast.LENGTH_LONG).show();
            } finally {
                if (httpclient != null)
                    httpclient.getConnectionManager().shutdown();

            }

            return null;

        }

        @Override
        protected void onPostExecute(String response) {

            if (mAsynclistener != null) {
                mAsynclistener.onTaskCompleted(response, serviceCode);
            }

        }
    }

    private void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public void cancelTask() {

        request.cancel(true);
        // System.out.println("task is canelled");

    }
}
