package linhtinh.sea.mh.linhtinh.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import linhtinh.sea.mh.linhtinh.Entity.MyPoint;

/**
 * Created by WIN-HAIVM on 10/27/17.
 */

public class GameView extends RelativeLayout implements View.OnTouchListener {

    private int CONFIG_STEP_WIDTH = 50;
    private int CONFIG_LINE_WITH = 4;
    private int CONFIG_LINE_VALUE = 3;
    private int CONFIG_VALUE_WITH = 20;

    public GameView(Context context) {
        super(context);
        initView(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    Context mContext = null;
    private Paint mLinePaint = null;
    private Paint mMePaint = null;
    private Paint mYouPaint = null;
    private Paint mWinPaint = null;
    private Paint mClearPaint = null;
    private float mClearAngel = 0;

    private void initView(Context c) {
        mContext = c;
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.GREEN);
        mLinePaint.setStrokeWidth(CONFIG_LINE_WITH);

        mMePaint = new Paint();
        mMePaint.setAntiAlias(true);
        mMePaint.setColor(Color.RED);
        mMePaint.setStrokeWidth(CONFIG_LINE_VALUE);
        mMePaint.setStyle(Paint.Style.STROKE);

        mYouPaint = new Paint();
        mYouPaint.setAntiAlias(true);
        mYouPaint.setColor(Color.BLUE);
        mYouPaint.setStrokeWidth(CONFIG_LINE_WITH);
        mYouPaint.setStyle(Paint.Style.STROKE);

        mWinPaint = new Paint();
        mWinPaint.setAntiAlias(true);
        mWinPaint.setColor(Color.YELLOW);
        mWinPaint.setStrokeWidth(CONFIG_LINE_WITH * 2);
        mWinPaint.setStyle(Paint.Style.STROKE);

        mClearPaint = new Paint();
        mClearPaint.setAntiAlias(true);
        mClearPaint.setColor(Color.MAGENTA);
        mClearPaint.setStrokeWidth(CONFIG_LINE_WITH * 20);
        mClearPaint.setStyle(Paint.Style.STROKE);

        mArrPoint = new HashMap<>();
        setOnTouchListener(this);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (width == 0) {
            width = canvas.getWidth();
            height = width;
            Resources r = getResources();
            mStepWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, CONFIG_STEP_WIDTH, r.getDisplayMetrics());
        }

        int left = mStepWidth / 2;
        while (left < width) {
            canvas.drawLine(left, 0, left, height, mLinePaint);
            canvas.drawLine(0, left, height, left, mLinePaint);
            left += mStepWidth;
        }
        if (mWinPointEnd != null) {
            drawWin(canvas);
        }
        drawValue(canvas);

        if (!isWin) {
            checkGame();
        }
    }

    private void drawWin(Canvas canvas) {
        float startX = mWinPointStart.x * mStepWidth + mStepWidth / 2;
        float startY = mWinPointStart.y * mStepWidth + mStepWidth / 2;
        float stopX = mWinPointEnd.x * mStepWidth + mStepWidth / 2;
        float stopY = mWinPointEnd.y * mStepWidth + mStepWidth / 2;
        if (mClearAngel == 0) {
            canvas.drawLine(startX, startY, stopX, stopY, mWinPaint);
        } else {

        }
    }

    private void calculatePointValueWithLine(float x, float y) {

    }

    // x + by = 0
    private float calculateLine() {
        float value = 0;
        if (mClearAngel > 89) {
            value = Float.MAX_VALUE;
        }
//value =
        return value;
    }

    private void drawValue(Canvas canvas) {
        Iterator it = mArrPoint.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException

            MyPoint point = (MyPoint) pair.getValue();

            if (point.type == MyPoint.TYPE_MY) {
                canvas.drawCircle(point.x * mStepWidth + mStepWidth / 2, point.y * mStepWidth + mStepWidth / 2, CONFIG_VALUE_WITH, mMePaint);
            } else {
                canvas.drawLine(point.x * mStepWidth + mStepWidth / 2 - CONFIG_VALUE_WITH,
                        point.y * mStepWidth + mStepWidth / 2 - CONFIG_VALUE_WITH,
                        point.x * mStepWidth + mStepWidth / 2 + CONFIG_VALUE_WITH,
                        point.y * mStepWidth + mStepWidth / 2 + CONFIG_VALUE_WITH,
                        mYouPaint);
                canvas.drawLine(point.x * mStepWidth + mStepWidth / 2 + CONFIG_VALUE_WITH,
                        point.y * mStepWidth + mStepWidth / 2 - CONFIG_VALUE_WITH,
                        point.x * mStepWidth + mStepWidth / 2 - CONFIG_VALUE_WITH,
                        point.y * mStepWidth + mStepWidth / 2 + CONFIG_VALUE_WITH,
                        mYouPaint);
            }
        }
    }

    private HashMap<Integer, MyPoint> mArrPoint = null;

    private int mStepWidth = 0;

    private int width = 0;
    private int height = 0;

    private boolean mState = true;

    MyPoint mWinPointStart = null;
    MyPoint mWinPointEnd = null;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (isWin) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int left = (int) motionEvent.getX();
                int top = (int) motionEvent.getY();
                int x = (left) / mStepWidth;
                int y = (top) / mStepWidth;

                MyPoint point = new MyPoint();
                point.x = x;
                point.y = y;
                point.type = mState ? MyPoint.TYPE_MY : MyPoint.TYPE_YOU;

                if (!mArrPoint.containsKey(x * 100 + y)) {
                    mLastKey = x * 100 + y;
                    mArrPoint.put(mLastKey, point);
                    mState = !mState;
                    invalidate();
                }
                break;
        }
        return false;
    }

    private boolean isWin = false;
    private int mLastKey = -1;

    public void clear() {
        mArrPoint.clear();
        isWin = false;
        mState = true;
        mWinPointEnd = null;
        mLastKey = -1;
        invalidate();
    }

    public void undo() {
        if (mLastKey == -1 || isWin) {
            return;
        }
        mArrPoint.remove(mLastKey);
        mState = !mState;
        mLastKey = -1;
        invalidate();
    }

    private final int DIRECTION_LEFT_RIGHT = 1;
    private final int DIRECTION_LEFT_RIGHT_DOWN = 2;
    private final int DIRECTION_TOP_DOWN = 3;
    private final int DIRECTION_RIGHT_LEFT_DOWN = 4;

    private void checkGame() {
        Iterator it = mArrPoint.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            MyPoint point = (MyPoint) pair.getValue();
            int key = (int) pair.getKey();

            int num = checkNext(key, DIRECTION_LEFT_RIGHT, false);
            if (num >= CONFIG_WIN_STEP) {
                mWinPointStart = point;
                checkNext(key, DIRECTION_LEFT_RIGHT, true);
                win();
                return;
            }
            num = checkNext(key, DIRECTION_LEFT_RIGHT_DOWN, false);
            if (num >= CONFIG_WIN_STEP) {
                mWinPointStart = point;
                checkNext(key, DIRECTION_LEFT_RIGHT_DOWN, true);
                win();
                return;
            }
            num = checkNext(key, DIRECTION_TOP_DOWN, false);
            if (num >= CONFIG_WIN_STEP) {
                mWinPointStart = point;
                checkNext(key, DIRECTION_TOP_DOWN, true);
                win();
                return;
            }
            num = checkNext(key, DIRECTION_RIGHT_LEFT_DOWN, false);
            if (num >= CONFIG_WIN_STEP) {
                mWinPointStart = point;
                checkNext(key, DIRECTION_RIGHT_LEFT_DOWN, true);
                win();
                return;
            }
        }
        return;
    }

    private int checkNext(int key, int direction, boolean isMarkWin) {
        if (isMarkWin) {
            mWinPointEnd = mArrPoint.get(key);
        }
        int nextEntityKey = getNextEntityKey(key, direction, isMarkWin);
        if (nextEntityKey != NOT_EXISTS) {
            return 1 + checkNext(nextEntityKey, direction, isMarkWin);
        }
        return 1;
    }

    private int NOT_EXISTS = -1;
    private int CONFIG_WIN_STEP = 5;

    /**
     * get next entity's key
     *
     * @param key
     * @param direction
     * @return
     */
    private int getNextEntityKey(int key, int direction, boolean isMarkWin) {
        int newKey = key;
        switch (direction) {
            case DIRECTION_LEFT_RIGHT:
                newKey += 100;
                break;
            case DIRECTION_LEFT_RIGHT_DOWN:
                newKey += 101;
                break;
            case DIRECTION_TOP_DOWN:
                newKey += 1;
                break;
            case DIRECTION_RIGHT_LEFT_DOWN:
                newKey += 99;
                break;
            default:
                return -NOT_EXISTS;
        }
        if (mArrPoint.containsKey(newKey)) {
            if (mArrPoint.get(newKey).type == mArrPoint.get(key).type) {
                return newKey;
            }
        }
        return NOT_EXISTS;
    }

    private void win() {
        isWin = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle("WIN");
        builder.setMessage("WINNNNNNNN");
        builder.setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();

        invalidate();
    }

}
