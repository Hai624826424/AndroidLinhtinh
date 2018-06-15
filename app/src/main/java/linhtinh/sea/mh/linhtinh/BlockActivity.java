package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import linhtinh.sea.mh.linhtinh.Receiver.MyDeviceAdminReceiver;

public class BlockActivity extends Activity {

    private boolean isLock = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        initView();
    }

    private Button btn_unlock;

    private void initView() {
        btn_unlock = findViewById(R.id.btn_unlock);

        btn_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unlock();
            }
        });
    }

    private void unlock() {
        btn_unlock.setText("Unlock success");
        isLock = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isLock) {
            lockScreen();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isLock) {
            lockScreen();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLock) {
            lockAndNew();
        }
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

//            // bring app to top, avoid HOME button
//            Intent notificationIntent = new Intent(this, BlockActivity.class);
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//            try {
//                pendingIntent.send();
//            } catch (PendingIntent.CanceledException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void lockAndNew() {
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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(BlockActivity.this, BlockActivity.class);
                    startActivity(i);
                    finish();

                }
            }, 500);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_ENABLE_ADMIN && resultCode == RESULT_OK) {
        lockScreen();
//        } else {
//        }
    }

}
