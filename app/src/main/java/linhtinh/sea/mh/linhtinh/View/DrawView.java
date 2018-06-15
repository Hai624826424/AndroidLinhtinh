package linhtinh.sea.mh.linhtinh.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Created by WIN-HAIVM on 10/27/17.
 */

public class DrawView extends RelativeLayout implements View.OnTouchListener {

    private int CONFIG_LINE_WITH = 4;

    public DrawView(Context context) {
        super(context);
        initView(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    Context mContext = null;
    private Paint mLinePaint = null;
    int mTime = 0;

    private void initView(Context c) {
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

        canvas.drawText(mTime + "", canvas.getWidth() / 2, canvas.getHeight() / 2, mLinePaint);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            mTime = (int) System.currentTimeMillis() % 60;
            invalidate();
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
}
