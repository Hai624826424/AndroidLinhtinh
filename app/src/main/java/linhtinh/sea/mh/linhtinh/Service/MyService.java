package linhtinh.sea.mh.linhtinh.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import linhtinh.sea.mh.linhtinh.Utility.Utils;

/**
 * Created by WIN-HAIVM on 11/13/17.
 */

public class MyService extends Service {

    private static int TIMER = 2000;

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  initTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    Timer mTimer;

    private void initTimer() {
        mTimer = new Timer();
        mTimer.schedule(new MyTimerTask(), TIMER);
    }

    public class MyTimerTask extends TimerTask {
        public MyTimerTask() {
        }

        @Override
        public void run() {
            Log.e("Timer", Utils.getDateTimeFromSecond());
            mTimer.schedule(new MyTimerTask(), TIMER);
        }
    }
}
