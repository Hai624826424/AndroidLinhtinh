package linhtinh.sea.mh.linhtinh;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Intents;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.ArrayList;
import java.util.HashMap;

import linhtinh.sea.mh.linhtinh.Entity.BaseEntity;
import linhtinh.sea.mh.linhtinh.Entity.HouseRoomGsonEntity;
import linhtinh.sea.mh.linhtinh.Utility.Constants;
import linhtinh.sea.mh.linhtinh.Utility.Utils;

public class QRCodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        initView();
        getRoomInformation();
    }



    private RelativeLayout rlt_container;

    private View rlt_scan;
    private Spinner spn_building;
    private Spinner spn_room;
    private TextView txt_status;
    private TextView txt_code;
    private TextView txt_api;
    private View lnl_error;
    private WebView wbv_error;
    private ImageView img_qr;
    private Button btn_reload_room;

    ArrayList<String> mArrBuilding = new ArrayList<>();
    ArrayList<ArrayList<String>> mArrRoom = new ArrayList<>();

    void initValue() {
        mArrBuilding.add("Phú Quốc");
        mArrBuilding.add("Mũi Né");
        mArrBuilding.add("Hồ Tràm Resort");
        mArrBuilding.add("Lý Sơn Đảo");
        mArrBuilding.add("Cửa Tùng Quán");
        mArrBuilding.add("Cồn Cỏ Lửa");
        //-------------
        ArrayList<String> arrPQ = new ArrayList<>();
        arrPQ.add("Phòng đôi nam");
        arrPQ.add("Phòng đơn");
        arrPQ.add("Phòng kín");
        arrPQ.add("Phòng view phòng bên cạnh");
        mArrRoom.add(arrPQ);

        ArrayList<String> arrMN = new ArrayList<>();
        arrMN.add("Phòng cua");
        arrMN.add("Phòng cá");
        arrMN.add("Phòng tôm");
        arrMN.add("Phòng sò");
        arrMN.add("Phòng ốc");
        mArrRoom.add(arrMN);

        ArrayList<String> arrHT = new ArrayList<>();
        arrHT.add("Phòng Hồ Cốc");
        arrHT.add("Phòng Hồ Ly");
        arrHT.add("Phòng Hồ Tôm");
        arrHT.add("Phòng Hồ Cá");
        arrHT.add("Phòng Hồ Bạch Đàn");
        mArrRoom.add(arrHT);

        ArrayList<String> arrLS = new ArrayList<>();
        arrLS.add("Phòng Tỏi");
        arrLS.add("Phòng Hành");
        arrLS.add("Phòng Hành Tăm");
        arrLS.add("Phòng Tỏi Cô Đơn");
        mArrRoom.add(arrLS);

        ArrayList<String> arrCT = new ArrayList<>();
        arrCT.add("Phòng Bắc");
        arrCT.add("Phòng Nam");
        arrCT.add("Phòng Biển");
        arrCT.add("Phòng Cát");
        mArrRoom.add(arrCT);

        ArrayList<String> arrCC = new ArrayList<>();
        arrCC.add("Phòng Cồn Cỏ");
        arrCC.add("Phòng Cồn Cát");
        arrCC.add("Phòng Cồn Lào");
        arrCC.add("Phòng Cồn Lái");
        mArrRoom.add(arrCC);

    }

    private void initView() {

        rlt_container = (RelativeLayout) findViewById(R.id.rlt_container);
//        addView();
        rlt_scan = findViewById(R.id.rlt_scan);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_code = (TextView) findViewById(R.id.txt_code);
        txt_api = (TextView) findViewById(R.id.txt_api);
        lnl_error = (View) findViewById(R.id.lnl_error);
        wbv_error = (WebView) findViewById(R.id.wbv_error);
        img_qr = (ImageView) findViewById(R.id.img_qr);
        btn_reload_room = (Button) findViewById(R.id.btn_reload_room);

        //   initValue();
        spn_building = (Spinner) findViewById(R.id.spn_building);
        spn_room = (Spinner) findViewById(R.id.spn_room);

        buildingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mArrBuilding);
        spn_building.setAdapter(buildingAdapter);
        // setRoomAdapter(0);

        spn_building.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setRoomAdapter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_reload_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRoomInformation();
            }
        });
        rlt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScan();
            }
        });
        txt_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnl_error.setVisibility(View.GONE);
            }
        });
    }

    ArrayAdapter<String> buildingAdapter;

    private void fillData() {

    }

    private void openScan() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //File write logic here
            startScan();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 112212);
        }
    }

    void startScan() {

        IntentIntegrator integrator = new IntentIntegrator(QRCodeActivity.this);
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    void setRoomAdapter(int pos) {
        if (mArrRoom.size() == 0) {
            return;
        }
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mArrRoom.get(pos));
        spn_room.setAdapter(roomAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String contents = data.getStringExtra(Intents.Scan.RESULT);
            String formatName = data.getStringExtra(Intents.Scan.RESULT_FORMAT);
            txt_status.setText(formatName);
            txt_code.setText(contents);
            String level = data.getStringExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL);
            generateQRCode(contents, level);
            registerInfo(contents);
        } else if (resultCode == Activity.RESULT_CANCELED) {
        }
    }

    Bitmap mBitmap = null;

    private void generateQRCode(String data, String level) {
        com.google.zxing.Writer writer = new QRCodeWriter();

        String finalData = Uri.encode(data, "ISO-8859-1");

        int width = 350;
//        width = img_qr.getLayoutParams().width;
        width = img_qr.getWidth();

        try {
            ErrorCorrectionLevel err = ErrorCorrectionLevel.H;
            if (level.toLowerCase().equals("h")) {
                err = ErrorCorrectionLevel.H;
            } else if (level.toLowerCase().equals("q")) {
                err = ErrorCorrectionLevel.Q;
            } else if (level.toLowerCase().equals("m")) {
                err = ErrorCorrectionLevel.M;
            } else if (level.toLowerCase().equals("l")) {
                err = ErrorCorrectionLevel.L;
            }
            HashMap<com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel> hm = new HashMap<>();
            hm.put(EncodeHintType.ERROR_CORRECTION, err);
            BitMatrix bm = writer.encode(finalData, BarcodeFormat.QR_CODE, width, width, hm);
            mBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    mBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (mBitmap != null) {
            img_qr.setImageBitmap(mBitmap);
        }
    }

    private void getRoomInformation() {
        mArrBuilding.clear();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = Constants.URL.GET_ALL_ROOM;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("String", response + "");
                        handleHouseRoomResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public HashMap<String, String> getParams() {
                HashMap hashMap = Utils.getMyParams();
                return hashMap;
            }
        };

        queue.add(stringRequest);
    }

    void handleHouseRoomResponse(String result) {
        try {


            Gson gson = new Gson();

            entity = gson.fromJson(result, HouseRoomGsonEntity.class);

            for (int i = 0; i < entity.data.size(); i++) {
                HouseRoomGsonEntity.HouseData house = entity.data.get(i);
                mArrBuilding.add(house.house_name);
                ArrayList<String> roomList = new ArrayList<>();
                for (int j = 0; j < entity.data.get(i).rooms.size(); j++) {
                    HouseRoomGsonEntity.RoomData room = entity.data.get(i).rooms.get(j);
                    if (j == 0) {
                        roomList = new ArrayList<>();
                    }
                    roomList.add(room.room_name);
                }
                mArrRoom.add(roomList);
            }
            buildingAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            showOkDialog("Get data failed!");
        }
    }

    HouseRoomGsonEntity entity;

    public int getHouseId() {
        if (entity == null) {
            return -1;
        }
        return entity.data.get(spn_building.getSelectedItemPosition()).house_id;
    }

    public int getRoomId() {
        if (entity == null) {
            return -1;
        }

        int house = getHouseId();
        if (house == -1) {
            return -1;
        }
        return entity.data.get(spn_building.getSelectedItemPosition()).rooms.get(spn_room.getSelectedItemPosition()).room_id;
    }

    private void registerInfo(final String value) {
        if (getRoomId() < 0) {
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = Constants.URL.REGISTER_INFO;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("String", response + "");
                        handleRegisterInfo(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                String s = new String(error.networkResponse.data);
                Log.e("error", s);
                showOkDialog("System error, please try again later!");
//                parseError(error);
            }
        }) {
            @Override
            public HashMap<String, String> getParams() {
                HashMap hashMap = Utils.getMyParams();
                hashMap.put("room_id", getRoomId() + "");
                hashMap.put("value", value + "");
                hashMap.put("name", spn_building.getSelectedItem().toString() + " _ " + spn_room.getSelectedItem().toString() + "");
                return hashMap;
            }
        };

        queue.add(stringRequest);
    }

    void handleRegisterInfo(String result) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();

            BaseEntity entity = gson.fromJson(result, BaseEntity.class);

            if (entity.success) {
                showOkDialog("Register Success!");
            } else {
                showOkDialog(entity.error.message);
            }
        } catch (Exception e) {
            showOkDialog("Register Failed!");
        }
    }

    private void showOkDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(true).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showError(String code, String message) {
        lnl_error.setVisibility(View.VISIBLE);
        String text = "Code : " + code;
        txt_api.setText(text);

        wbv_error.getSettings().setAppCacheEnabled(false);
        wbv_error.clearCache(true);
        wbv_error.loadUrl("about:blank");
        wbv_error.loadData(message, "text/html", "UTF-8");
        wbv_error.clearHistory();
        wbv_error.setBackgroundColor(Color.TRANSPARENT);
        wbv_error.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

    }

    private void parseError(VolleyError error) {
        try {
            String json = null;
            NetworkResponse response = error.networkResponse;
            if (response != null && response.data != null) {
                showError(response.statusCode + "", new String(response.data, "UTF-8"));
            }
        } catch (Exception e) {

        }
    }


}