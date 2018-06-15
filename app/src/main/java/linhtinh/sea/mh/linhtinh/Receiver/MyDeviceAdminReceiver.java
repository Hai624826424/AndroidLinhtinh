package linhtinh.sea.mh.linhtinh.Receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import linhtinh.sea.mh.linhtinh.R;

/**
 * Created by WIN-HAIVM on 2/22/18.
 */

public class MyDeviceAdminReceiver extends DeviceAdminReceiver {

    void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "status_enabled");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "status_disable_warning";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "status_disabled");
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        showToast(context, "status_pw_changed");
    }
}