package linhtinh.sea.mh.linhtinh.Service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by WIN-HAIVM on 12/7/17.
 */

public class FBService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        Log.e("receive", remoteMessage.toString());
        if (remoteMessage.getData().size() > 0) {

        }
        postBadge(remoteMessage.getNotification().getBody());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    private void postBadge(String val) {
        int badgeCount = 0;
        try{
            badgeCount = Integer.parseInt(val);
        }catch (Exception e){

        }
        ShortcutBadger.applyCount(this, badgeCount); //for 1.1.4+
//        ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3
    }
}
