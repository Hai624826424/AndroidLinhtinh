package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
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
import java.io.PrintWriter;
import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Adapter.AlertAdapter;
import linhtinh.sea.mh.linhtinh.Entity.ItemEntity;
import linhtinh.sea.mh.linhtinh.Entity.ListGsonEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.Utility.Utils;

public class FileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        createFile();
        initView();
    }

    EditText edt_content = null;
    private Button btn_save;
    private Button btn_more;

    private void initView() {
        edt_content = findViewById(R.id.edt_content);
        btn_save = findViewById(R.id.btn_save);
        btn_more = findViewById(R.id.btn_more);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                writeFile(edt_content.getText().toString());
                edt_content.setText("");
            }
        });
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreSetting();
            }
        });

        edt_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    PopupWindow popupWindow;

    private void showMoreSetting() {
        if (popupWindow == null) {
            Button button = new Button(this);
            button.setText("Clear All");
            popupWindow = new PopupWindow(button, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearAll();
                    popupWindow.dismiss();
                }
            });
            popupWindow.setOutsideTouchable(false);
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(btn_more);
        }
    }

    private void clearAll() {
        if (createFile()) {
            clearFileContent();
        }
    }

    @SuppressWarnings("NewApi")
    private boolean createFile() {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //File write logic here
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 112212);
            return false;
        }

    }

    final String FILE_NAME = "myClipboard.txt";

    private void clearFileContent() {
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        try {
            if (file.exists()) {
                FileWriter out = new FileWriter(file, false);
                out.write("");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String s) {
        if (!createFile()) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        try {
            FileWriter out = new FileWriter(file, true);
            out.write(s+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
