package linhtinh.sea.mh.linhtinh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import linhtinh.sea.mh.linhtinh.Class.AndroidPhone;
import linhtinh.sea.mh.linhtinh.Class.SmartPhone;
import linhtinh.sea.mh.linhtinh.Class.WindowPhone;

public class CamActivity extends Activity {

    private TextView txt_result;
    AndroidPhone mAndroid;

    private void initView() {
        txt_result = (TextView) findViewById(R.id.txt_result);
        lnl_view = (LinearLayout) findViewById(R.id.lnl_view);
        Integer i10 = 10;
        Integer in10 = 10;
        Integer i10k = 1000;
        Integer in10k = 1000;
        Log.e("val", "val");


        mAndroid = new AndroidPhone(8, "Qualcomn");
        mAndroid.getOS();

        WindowPhone window = new WindowPhone(12, "Microsoft");

        SmartPhone smart = (SmartPhone) mAndroid;
        smart.getPrintAction();

        MonthDisplayHelper month = new MonthDisplayHelper(2017, 1);
        month.getColumnOf(5);

        draw();
        findViewById(R.id.btn_cap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CamActivity.this, ExchangeActivity.class));
                draw();
            }
        });
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txt_auto);
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.showDropDown();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        ObjectAnimator ob = ObjectAnimator.ofFloat(mAndroid, "value", 0, 100).setDuration(2000);
        ob.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mAndroid.print();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAndroid.print();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mAndroid.print();
            }
        });
        ob.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        initView();
//        makeLeakMemory();
        callAPI();

    }

    private void callAPI() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    }

    public void handleResult(String s) {
        showResult(s);

    }

    public void showResult(String s) {
        txt_result.setText(s);

    }

    LinearLayout lnl_view;

    private void draw() {
        for (int i = 0; i < 1000; i++) {
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            p.setMargins(setSizeByDp(1), 0, setSizeByDp(1), 0);
            p.weight = 1;
            LinearLayout lnl_row = new LinearLayout(this);
            lnl_row.setOrientation(LinearLayout.HORIZONTAL);
            lnl_row.setLayoutParams(p);

            for (int j = 0; j < 10; j++) {

                Drawable shape = getResources().getDrawable(R.drawable.metter_line);
                shape.setColorFilter(j % 10 < Math.random() * 10 ? getResources().getColor(R.color.colorPrimary) : Color.RED, PorterDuff.Mode.SRC_ATOP);
                ImageView line = new ImageView(this);
                line.setImageDrawable(shape);
                line.setAlpha((float) (10 - j) / 10);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
                line.setLayoutParams(lp);
                lnl_row.addView(line);
            }
            lnl_view.addView(lnl_row);
        }
    }

    private int setSizeByDp(int dp) {
        return 2 * dp;
    }
}