package linhtinh.sea.mh.linhtinh;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import java.util.List;

import linhtinh.sea.mh.linhtinh.Adapter.AppAdapter;
import linhtinh.sea.mh.linhtinh.Adapter.LeftAdapter;
import linhtinh.sea.mh.linhtinh.Adapter.TimelineAdapter;
import linhtinh.sea.mh.linhtinh.Class.MyAppInfo;
import linhtinh.sea.mh.linhtinh.Entity.TimelineEntity;
import linhtinh.sea.mh.linhtinh.Entity.TimelineGsonEntity;
import linhtinh.sea.mh.linhtinh.Utility.Utils;
import linhtinh.sea.mh.linhtinh.View.GameView;
import me.leolin.shortcutbadger.ShortcutBadger;

public class ExchangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeSmall();
        }
        initView();

        long l = 0;
        Long k = 1L;
        k.equals(l);
        Integer i = 0;
    }

    @TargetApi(26)
    @RequiresApi(26)
    private void makeSmall() {
//        enterPictureInPictureMode();
        enterPictureInPictureMode(new PictureInPictureParams.Builder().build());
    }

    Button btn_main;
    Button btn_undo;
    Button btn_reload;
    Button btn_badge;
    RecyclerView rcl_main;
    AppAdapter mAdapter;
    ArrayList<MyAppInfo> mList;

    private void initView() {
        rcl_main = findViewById(R.id.rcl_main);
        btn_main = findViewById(R.id.btn_main);
        btn_reload = findViewById(R.id.btn_reload);
        btn_badge = findViewById(R.id.btn_badge);
        btn_undo = findViewById(R.id.btn_undo);
        btn_main.setText(getResources().getString(R.string.my_text));

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_main.setLayoutManager(lm);
        mList = new ArrayList<>();
        mAdapter = new AppAdapter(this, mList);
        rcl_main.setAdapter(mAdapter);

        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.clear();
                getExchange();
                mAdapter.notifyDataSetChanged();
            }
        });
        btn_badge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postBadge();
            }
        });

        getExchange();
        btn_badge.setText("EXCHANGE ACTIVITY");

    }

    private void postBadge() {
        int badgeCount = (int) (System.currentTimeMillis() % 1000);
        ShortcutBadger.applyCount(this, badgeCount); //for 1.1.4+
//        ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3
    }

    private void getExchange() {

        final PackageManager pm = getPackageManager();
//get a data of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
//            Log.d(TAG, "Installed package :" + packageInfo.packageName);
//            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
//            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            try {
                MyAppInfo p = new MyAppInfo();
                p.packageName = packageInfo.packageName;
                p.sourceDir = packageInfo.sourceDir;
                p.name = packageInfo.name;


                Drawable icon = pm.getApplicationIcon(packageInfo.packageName);
                p.mIcon = icon;
                mList.add(p);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void handleTimelineResult(String response) {
        response = Utils.getUtf8String(response);
        Gson gson = new Gson();
        TimelineGsonEntity entity = gson.fromJson(response, TimelineGsonEntity.class);
//        mList.addAll(entity.body.data);
        mAdapter.notifyDataSetChanged();
    }
}
