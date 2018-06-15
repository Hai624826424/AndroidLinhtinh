package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import linhtinh.sea.mh.linhtinh.Receiver.MyDeviceAdminReceiver;

public class LockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockScreen();
    }

    DevicePolicyManager mDPM;
    int REQUEST_CODE_ENABLE_ADMIN = 13779;

    ComponentName mDeviceAdminSample;

    private void lockScreen() {
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdminSample = new ComponentName(this,
                MyDeviceAdminReceiver.class);
        if (!mDPM.isAdminActive(mDeviceAdminSample)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "For turn screen off permission");
            startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN);
        } else {
            mDPM.lockNow();
            Intent i = new Intent(this, BlockActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ENABLE_ADMIN && resultCode == RESULT_OK) {
            lockScreen();
        } else {
            finish();
        }
    }
}
