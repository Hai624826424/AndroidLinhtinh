package linhtinh.sea.mh.linhtinh.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import linhtinh.sea.mh.linhtinh.Service.MyService;

/**
 * Created by WIN-HAIVM on 11/13/17.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      //  context.startService(new Intent(context, MyService.class));
    }
}
