package linhtinh.sea.mh.linhtinh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import linhtinh.sea.mh.linhtinh.Class.AndroidPhone;
import linhtinh.sea.mh.linhtinh.Class.SmartPhone;
import linhtinh.sea.mh.linhtinh.Class.WindowPhone;
import linhtinh.sea.mh.linhtinh.Fragment.LeftFragment;

import static android.app.ActivityOptions.makeTaskLaunchBehind;

public class DoubleActivity extends Activity implements View.OnTouchListener {


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Called when the activity is first created.
     */

    @SuppressWarnings("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double);
        initView();
//        makeLeakMemory();
//        startCamActivity();

    }

    private void startCamActivity() {
        Intent i = new Intent(this, CamActivity.class);
        Rect r = new Rect(100, 299, 1200, 1200);
//        ActivityOptions op = makeTaskLaunchBehind();
        ActivityOptions op = makeTaskLaunchBehind();
        op.setLaunchBounds(r);
        startActivity(i, op.toBundle());
    }

    private View view_divider;
    private View rlt_left;

    private void initView() {
        view_divider = findViewById(R.id.view_divider);
        rlt_left = findViewById(R.id.rlt_left);
        view_divider.setOnTouchListener(this);
        initFragment();
    }

    float dX;

    @SuppressWarnings("NewApi")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                //     dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX )
                       // .y(event.getRawY() + dY - (view.getHeight() / 2))
                        .setDuration(0)
                        .start();
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rlt_left.getLayoutParams();
//                lp.setMargins((int) (event.getRawX() + dX - (view.getWidth() / 2)), 0, 0, 0);
                lp.width = (int) (event.getRawX() + dX + (view.getWidth() / 2));
                rlt_left.setLayoutParams(lp);
                notifyLeftList();
                break;
            default:
                return false;
        }
        return true;
    }

    private void initFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        LeftFragment leftFragment = new LeftFragment();
        ft.add(R.id.rlt_left, leftFragment, leftFragment.getClass().getSimpleName());
        ft.commit();
    }

    private void notifyLeftList() {
//        notifyOnSizeChange
        FragmentManager fm = getFragmentManager();
        LeftFragment left = (LeftFragment) fm.findFragmentByTag(LeftFragment.class.getSimpleName());
        if (left != null) {
            left.notifyOnSizeChange();
        }
    }
}