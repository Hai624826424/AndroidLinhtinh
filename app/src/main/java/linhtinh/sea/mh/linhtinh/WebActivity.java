package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import linhtinh.sea.mh.linhtinh.Adapter.AlertAdapter;
import linhtinh.sea.mh.linhtinh.Entity.ItemEntity;
import linhtinh.sea.mh.linhtinh.Entity.ListGsonEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.Utility.Utils;

public class WebActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TestOTG", "onCreate");
        handleSchemeIntent(getIntent());
        setContentView(R.layout.activity_web);
        initView();
        callAPI();
//        getData();


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TestOTG", "onConfigurationChanged");
    }

    TextView txt_title = null;
    RelativeLayout rlt_temp = null;
    RecyclerView rcl_main = null;
    AlertAdapter mAdapter = null;
    ArrayList<ItemEntity> mList = null;
    private Button btn_save;

    private void initView() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;

        Log.e(dpHeight + "__", dpWidth + "");

        rlt_temp = findViewById(R.id.rlt_temp);
        txt_title = rlt_temp.findViewById(R.id.txt_title);
        btn_save = findViewById(R.id.btn_save);
        rcl_main = findViewById(R.id.rcl_main);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_main.setLayoutManager(lm);


        mList = new ArrayList<>();
        mAdapter = new AlertAdapter(this, mList, mClick);
        rcl_main.setAdapter(mAdapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValue();
            }
        });
    }


    private void callAPI() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.1.118:8080/data.txt";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if (this != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    handleLoginResult(response);
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

    private void handleLoginResult(String response) {
        response = Utils.getUtf8String(response);
        Log.e("handleLoginResult", response);
        Gson gson = new Gson();
        ListGsonEntity entity = gson.fromJson(response, ListGsonEntity.class);
        Log.e("result", entity.data.toString());
        mList.addAll(entity.data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleSchemeIntent(intent);
    }

    void handleSchemeIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        int i = 0;
        int j = i++;

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String valueOne = uri.getQueryParameter("token");
            Log.e("val", valueOne + "");
        }
    }

    ClickInterface mClick = new ClickInterface() {
        @Override
        public void onClick(int position) {
            try {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("smdkabochat://kabochat.com/?action=login&token=XXXXXXX"));
//                startActivity(intent);

            } catch (Exception e) {

            }
        }
    };

    private void saveValue() {
        String s = "";
        for (ItemEntity item : mList) {
//            String line = String.format("%-50\t %s", item.title, item.content);
            txt_title.setText(item.title);

            boolean isEllipsize = !((txt_title.getLayout().getText().toString()).equalsIgnoreCase(item.title));
            String line = "";

            if (isEllipsize) {
                line = (String) txt_title.getText().subSequence(0, txt_title.getLayout().getEllipsisStart(0)) + "...";
                line += "\t\t\t" + item.content;
            } else {
//                line = item.title;
//            line+="\t\t"+item.content;
            }
            s += line + "\n\n";
        }
        createFile(s);
    }

    @SuppressWarnings("NewApi")
    private void createFile(String s) {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //File write logic here
            writeFile(s);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 112212);
        }
    }

    private void writeFile(String s) {
        File file = new File(Environment.getExternalStorageDirectory(), "hahahahahah.txt");
        try {
            FileWriter out = new FileWriter(file);
            out.write(s);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
