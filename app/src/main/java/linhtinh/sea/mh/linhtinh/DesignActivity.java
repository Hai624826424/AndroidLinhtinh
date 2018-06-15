package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import linhtinh.sea.mh.linhtinh.Utility.Utils;

public class DesignActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        initView();
    }

    private RelativeLayout rlt_container;


    private void initView() {

        rlt_container = (RelativeLayout) findViewById(R.id.rlt_container);
    }

}
