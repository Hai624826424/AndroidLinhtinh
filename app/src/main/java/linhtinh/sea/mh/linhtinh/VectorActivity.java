package linhtinh.sea.mh.linhtinh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import linhtinh.sea.mh.linhtinh.Utility.Utils;

public class VectorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        initView();
    }

    private RelativeLayout rlt_container;

    private View view_animate;
    private View view_bird;
    private View view_eye;
    private View view_check;
    private View view_up_down;

    private void initView() {

        rlt_container = (RelativeLayout) findViewById(R.id.rlt_container);
//        addView();
        view_animate = findViewById(R.id.view_animate);
        view_bird = findViewById(R.id.view_bird);
        view_eye = findViewById(R.id.view_eye);
        view_check = findViewById(R.id.view_check);
        view_up_down = findViewById(R.id.view_up_down);
        setClick(view_animate);
        setClick(view_bird);
        setClick(view_eye);
        setClick(view_check);
        setClick(view_up_down);
        view_eye.setSelected(false);

//        AnimatedVectorDrawable dr = (AnimatedVectorDrawable) view_animate.getBackground();
//        if (!dr.isRunning()) {
//            dr.start();
//        }

    }

    @SuppressWarnings("NewApi")
    private void setClick(final View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (view_eye != v) {
                    AnimatedVectorDrawable dr = (AnimatedVectorDrawable) v.getBackground();
                    if (!dr.isRunning()) {
                        dr.start();
                    } else {
//                    dr.jumpToCurrentState();
                    }
                    boolean val = view_eye.isSelected();
                    Log.e("setSelected", val + "");

                } else {
                    v.setSelected(!v.isSelected());
                    StateListDrawable state = (StateListDrawable) v.getBackground();
                    final AnimatedVectorDrawable dr = (AnimatedVectorDrawable) state.getCurrent();
                    dr.registerAnimationCallback(new Animatable2.AnimationCallback() {
                        @Override
                        public void onAnimationStart(Drawable drawable) {
                            super.onAnimationStart(drawable);
                        }

                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            super.onAnimationEnd(drawable);
                            dr.clearAnimationCallbacks();
                        }
                    });

                    if (!dr.isRunning()) {
                        dr.start();
                    }
                }

            }
        });


    }

    int count = 0;
    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            View v = LayoutInflater.from(VectorActivity.this).inflate(R.layout.view_drop, null, false);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            rlt_container.addView(v);
            lp.width = Utils.dpToPx(VectorActivity.this, 50);
            lp.height = Utils.dpToPx(VectorActivity.this, 50);

            lp.leftMargin = (count % 3) * Utils.dpToPx(VectorActivity.this, 50);
            lp.topMargin = (count / 3) * Utils.dpToPx(VectorActivity.this, 50);
            v.setLayoutParams(lp);
            AnimatedVectorDrawable dr = (AnimatedVectorDrawable) v.getBackground();
            if (!dr.isRunning()) {
                dr.start();
            }
            count++;
            if (count < 10) {
                mHandler.postDelayed(this, 10);
            }
        }
    };

    private void addView() {
        mHandler.post(mRunnable);
    }
}
