package linhtinh.sea.mh.linhtinh.Utility;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by WIN-HAIVM on 11/13/17.
 */

public class Utils {
    public static String getDateTimeFromSecond() {
        try {
            Calendar cal = Calendar.getInstance();
            String res = "";
            res += String.format("%02d", (cal.get(Calendar.HOUR_OF_DAY)));
            res += ":" + String.format("%02d", (cal.get(Calendar.MINUTE)));
            res += ":" + String.format("%02d", (cal.get(Calendar.SECOND)));
            return res;
        } catch (Exception e) {
            return "0000-00-00";
        }
    }

    public static String getUtf8String(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public static CharSequence getStringData(String data) {
        if ((data != null) && (!TextUtils.isEmpty(data)) && (!data.equals("null"))) {
            return getFromHtml(data);
        }
        return "";
    }


    public static CharSequence getFromHtml(String data) {
        if (Build.VERSION.SDK_INT < 24) {
            return Html.fromHtml(data);
        } else {
            return Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY);
        }
    }

    public static int dpToPx(Context context, float input) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, input, r.getDisplayMetrics());
        return (int) px;
    }

    public static HashMap<String, String> getMyParams(){
        HashMap hashMap =new HashMap();
        hashMap.put("who_are_you", "Human");
        return hashMap;
    }


}
