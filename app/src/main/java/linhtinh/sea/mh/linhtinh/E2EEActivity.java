package linhtinh.sea.mh.linhtinh;

import android.app.Activity;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import linhtinh.sea.mh.linhtinh.Utility.Utils;
import linhtinh.sea.mh.linhtinh.View.ChatScreen;

public class E2EEActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e2ee);
        initView();
    }

    private RelativeLayout rlt_left;
    private RelativeLayout rlt_right;

    RecyclerView rcl_left;
    RecyclerView rcl_right;

    EditText edt_left;
    EditText edt_right;

    Button btn_left;
    Button btn_right;

    private void initView() {

        rlt_left = (RelativeLayout) findViewById(R.id.rlt_left);
        rlt_right = (RelativeLayout) findViewById(R.id.rlt_right);

        rcl_left = (RecyclerView) rlt_left.findViewById(R.id.rcl_message);
        rcl_right = (RecyclerView) rlt_right.findViewById(R.id.rcl_message);

        edt_left = (EditText) rlt_left.findViewById(R.id.edt_message);
        edt_right = (EditText) rlt_right.findViewById(R.id.edt_message);

        btn_left = (Button) rlt_left.findViewById(R.id.btn_send);
        btn_right = (Button) rlt_right.findViewById(R.id.btn_send);

        LinearLayoutManager lmLeft = new LinearLayoutManager(this);
        lmLeft.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_left.setLayoutManager(lmLeft);

        LinearLayoutManager lmRight = new LinearLayoutManager(this);
        lmRight.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_right.setLayoutManager(lmRight);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(true);
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage(false);
            }
        });
        mrA = new ChatScreen(rcl_left, "mrA");
        mrB = new ChatScreen(rcl_right, "mrB");

        mrA.setFriend(mrB);
        mrB.setFriend(mrA);

    }

    ChatScreen mrA;
    ChatScreen mrB;

    private void sendMessage(boolean isLeft) {
        String message = (isLeft ? edt_left : edt_right).getText().toString();
        if (isLeft) {
            mrA.sendMessage(message);
        } else {
            mrB.sendMessage(message);
        }
    }

}
