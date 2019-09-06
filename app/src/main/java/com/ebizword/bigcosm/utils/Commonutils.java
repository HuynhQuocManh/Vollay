package com.ebizword.bigcosm.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import com.ebizword.bigcosm.R;

public class Commonutils {
    private static Dialog mDialog;


    public static void showtoast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void progressdialog_show(Context context, String msg) {
        if (context != null) {
            mDialog = new Dialog(context, R.style.DialogSlideAnim_leftright);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(false);
            mDialog.setContentView(R.layout.animation_loading);
            TextView tv_title = (TextView) mDialog.findViewById(R.id.tv_title);
            tv_title.setText(msg);
            mDialog.show();
        }

    }

    public static String message_json(String message_id, String type, String driver_id,
                                      String client_id, String request_id, String message) {

        JSONObject jObject = new JSONObject();
        try {

            jObject.put("id", message_id);
            jObject.put("type", type);
            jObject.put("driver_id", driver_id);
            jObject.put("client_id", client_id);
            jObject.put("request_id", request_id);
            jObject.put("message", message);

            return jObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    public static void progressdialog_hide() {
        try {
            if (mDialog != null) {

                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
