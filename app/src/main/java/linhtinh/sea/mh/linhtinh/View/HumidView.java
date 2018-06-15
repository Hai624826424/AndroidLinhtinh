package linhtinh.sea.mh.linhtinh.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import linhtinh.sea.mh.linhtinh.R;


/**
 * Created by WIN-HAIVM on 10/27/17.
 */

public class HumidView extends RelativeLayout implements View.OnTouchListener {

    private int CONFIG_LINE_WITH = 4;

    public HumidView(Context context) {
        super(context);

        initView(context);
    }

    public HumidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HumidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    Context mContext = null;
    private Paint mLinePaint = null;
    int mTime = 0;

    private void initView(Context c) {
        LayoutInflater.from(c).inflate(R.layout.layout_humid, this);
        mContext = c;
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStrokeWidth(CONFIG_LINE_WITH);
        mLinePaint.setTextSize(100);
        mLinePaint.setAntiAlias(true);

        mHandler.postDelayed(mRunnable, 100);
        setOnTouchListener(this);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

     //   canvas.drawText(mTime + "", canvas.getWidth() / 2, canvas.getHeight() / 2, mLinePaint);

        Paint lightRed = new Paint();
        lightRed.setAntiAlias(true);
        Shader gradient = new SweepGradient(getWidth()/2,getHeight()/2, Color.WHITE, Color.RED);
        lightRed.setShader(gradient);

        RectF rectf = new RectF(0, 0 , getWidth(), getHeight());
        canvas.drawArc(rectf, 00, 160, false, lightRed);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
          //  mTime = (int) System.currentTimeMillis() % 60;
            invalidate();
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
}
