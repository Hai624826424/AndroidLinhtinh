package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Adapter.TimelineAdapter;
import linhtinh.sea.mh.linhtinh.Entity.TimelineEntity;
import linhtinh.sea.mh.linhtinh.Entity.TimelineGsonEntity;
import linhtinh.sea.mh.linhtinh.Utility.Utils;
import linhtinh.sea.mh.linhtinh.View.GameView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        getTimeline();
//        startActivity(new Intent(this, CamActivity.class));
    }

    View view_1;
    View view_2;
    View view_3;
    Button btn_noti;
    Button btn_main;
    Button btn_undo;
    Button btn_reload;
    GameView gv;
    RecyclerView rcl_main;
    TimelineAdapter mAdapter;
    ArrayList<TimelineEntity> mList;

    private void initView() {
        rcl_main = findViewById(R.id.rcl_main);
        btn_noti = findViewById(R.id.btn_noti);
        btn_main = findViewById(R.id.btn_main);
        btn_reload = findViewById(R.id.btn_reload);
        btn_undo = findViewById(R.id.btn_undo);
        btn_main.setText(getResources().getString(R.string.my_text));
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gv.clear();
            }
        });
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                gv.undo();
                sendNoti();
            }

        });
        gv = findViewById(R.id.gv);
        gv.setWillNotDraw(false);
        gv.post(new Runnable() {
            @Override
            public void run() {
                centerLayout();
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_main.setLayoutManager(lm);
        mList = new ArrayList<>();
        mAdapter = new TimelineAdapter(this, mList);
        rcl_main.setAdapter(mAdapter);

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mList.clear();
//                mAdapter.notifyDataSetChanged();

                startActivity(new Intent(MainActivity.this, CamActivity.class));
            }
        });
//        handleAnimation();

        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNoti();
            }
        });
    }

    @SuppressWarnings("NewApi")
    private void sendNoti() {

        String replyLabel = "R.string.reply";
        RemoteInput remoteInput = new RemoteInput.Builder("InlineReplyReceiver.KEY_REPLY")
                .setLabel(replyLabel)
                .build();
        android.app.RemoteInput remoteInput1 = new android.app.RemoteInput.Builder("InlineReplyReceiver.KEY_REPLY")
                .setLabel(replyLabel)
                .build();

        Intent intent = new Intent(this, ExchangeActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification.Action replyAction1 = new Notification.Action.Builder(
                R.mipmap.ic_launcher, replyLabel, pIntent)
                .addRemoteInput(remoteInput1)
                .setAllowGeneratedReplies(true)
                .build();

        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = new NotificationChannel("123", "HAHA", importance);
        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("User_display_name");
        style.setConversationTitle("Team lunch")
                .addMessage("Hi mmmm  mmm mmm mmm  mmmm mmmm mm  m mmm     mmmm mm mmmmmmm mm mmm m m", 1413907868040L, null) // Pass in null for user.
                .addMessage("Not mu chm mmmm mmm mm m m mm m m mm m mm bb bb bb b bbb t tt tt ttttttt gggggg gggggg    hhhh hhhhh hhh j", 1413907968040L, null)
                .addMessage("How about lunch?", 1414907868040L, "Coworker")
                .addMessage("How a\nbout lun\nchhxf\nghhf xfg?", 1414907868040L, "Coworker")
                .addMessage("How axfghbout xfhg  lunxfghfxg hfghch?", 1414907868040L, "Coworker");
        NotificationCompat.MessagingStyle.Message m = new NotificationCompat.MessagingStyle.Message("How about lunch?", 1414907868040L, "Coworker");


        Notification.InboxStyle ibs = new Notification.InboxStyle();
        ibs.setSummaryText("10:20");
        ibs.addLine("Hi mmmm  mmm mmm mmm  mmmm mmmm m k l n b h g y f t r e d c v bn h mmmmmmm mm mmm m m") // Pass in null for user.
                .addLine("Not mu chm mmmm mmm mm m m mm m m mm m mm bb bb bb b bbb t tt tt ttttttt gggggg gggggg    hhhh hhhhh hhh j")
                .addLine("How about lunchhxfghhf KK?")
                .addLine("How about lunch\nhxfghhf KK?")
                .addLine("How axf\nghbout xf\nhg  lunxfghfxg hfghch?");

        try {
            byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


            Notification.Builder builder1 = new Notification.Builder(this)
                    .setContentTitle("Mr President")
                    .setContentText("Subject")
                    .setSmallIcon(R.mipmap.ic_launcher, 0)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .setStyle(ibs)
                    .setLargeIcon(decodedByte)
                    .setActions(replyAction1)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .addAction(R.mipmap.ic_launcher, "And more", pIntent)
                    .addAction(R.mipmap.ic_launcher, "And more", pIntent)
                    .setChannelId("12357")
                    .setColor(Color.BLUE)
                    .setColorized(true);



            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannel(mChannel);

            Notification n = builder1.build();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("12357", "NOTITTLE", importance);
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(11289, n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void centerLayout() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) gv.getLayoutParams();
        lp.height = gv.getWidth();
        gv.setLayoutParams(lp);
    }

    private void getTimeline() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://192.168.1.118:8080/timeline.txt";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (this != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    handleTimelineResult(response);
                                }
                            });
                        } else {
                            // ignore
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = Utils.getDefaultParams();
//                return params;
//            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);

    }

    private void handleTimelineResult(String response) {
        response = Utils.getUtf8String(response);
//        Log.e("handleTimelineResult", response);
        Gson gson = new Gson();
        TimelineGsonEntity entity = gson.fromJson(response, TimelineGsonEntity.class);
//        Log.e(entity.code + "", entity.body.data.toString());
        mList.addAll(entity.body.data);
        mAdapter.notifyDataSetChanged();
    }

    String imageBase64 = "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";
}
