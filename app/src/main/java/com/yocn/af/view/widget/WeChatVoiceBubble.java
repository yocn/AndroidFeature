package com.yocn.af.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

public class WeChatVoiceBubble extends View {
    private Paint redPaint;
    private Paint greenPaint;
    private Paint currPaint;
    private RectF translateRectF;
    private RectF cancelRectF;
    private RectF centerRectF;
    private RectF currRectF;
    private RectF targetRectF;
    private PointF[] translateTrianglePoints = new PointF[3];
    private PointF[] cancelTrianglePoints = new PointF[3];
    private PointF[] centerTrianglePoints = new PointF[3];
    private PointF[] currTrianglePoints = new PointF[3];
    private int height;
    private int type = SHOW_TYPE.TYPE_NORMAL;
    private int width;
    private float triangleLine = 50;
    private float triangleHeight = 40;
    private Path trianglePath;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            SHOW_TYPE.TYPE_NORMAL,
            SHOW_TYPE.TYPE_CANCEL,
            SHOW_TYPE.TYPE_TRANSLATE
    })
    public @interface SHOW_TYPE {
        public int TYPE_NORMAL = 101;
        public int TYPE_CANCEL = 102;
        public int TYPE_TRANSLATE = 103;
    }

    public WeChatVoiceBubble(Context context) {
        this(context, null);
    }

    public WeChatVoiceBubble(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeChatVoiceBubble(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    private void init() {
        greenPaint = new Paint();
        greenPaint.setAntiAlias(true);
        greenPaint.setColor(0xFF00cb32);
        greenPaint.setStyle(Paint.Style.FILL);
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(0xFFcb3a35);
        redPaint.setStyle(Paint.Style.FILL);
        currPaint = greenPaint;
        triangleHeight = (float) Math.sqrt(Math.pow(triangleLine, 2) - Math.pow(triangleLine / 2, 2));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getResources().getDimensionPixelSize(R.dimen.height_round_rect);
        width = getMeasuredWidth();
        if (translateRectF == null) {
            translateRectF = new RectF(0, 0, width, height - triangleHeight);
            cancelRectF = new RectF(0, 0, height - triangleHeight, height - triangleHeight);
            centerRectF = new RectF(width / 2 - (height - triangleHeight), 0, width / 2 + (height - triangleHeight), height - triangleHeight);

            translateTrianglePoints[0] = new PointF(width - cancelRectF.width() / 2 - triangleLine / 2, height - triangleHeight - 1);
            translateTrianglePoints[1] = new PointF(width - cancelRectF.width() / 2, height);
            translateTrianglePoints[2] = new PointF(width - cancelRectF.width() / 2 + triangleLine / 2, height - triangleHeight - 1);

            cancelTrianglePoints[0] = new PointF(cancelRectF.width() / 2 - triangleLine / 2, height - triangleHeight - 1);
            cancelTrianglePoints[1] = new PointF(cancelRectF.width() / 2, height);
            cancelTrianglePoints[2] = new PointF(cancelRectF.width() / 2 + triangleLine / 2, height - triangleHeight - 1);

            centerTrianglePoints[0] = new PointF(width / 2 - triangleLine / 2, height - triangleHeight - 1);
            centerTrianglePoints[1] = new PointF(width / 2, height);
            centerTrianglePoints[2] = new PointF(width / 2 + triangleLine / 2, height - triangleHeight - 1);

            trianglePath = new Path();
            currRectF = centerRectF;
            currTrianglePoints = centerTrianglePoints;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(getTmpRectF(), 50, 50, currPaint);
        trianglePath.reset();
        trianglePath.setFillType(Path.FillType.EVEN_ODD);
        trianglePath.moveTo(currTrianglePoints[0].x, currTrianglePoints[0].y);
        trianglePath.lineTo(currTrianglePoints[1].x, currTrianglePoints[1].y);
        trianglePath.lineTo(currTrianglePoints[2].x, currTrianglePoints[2].y);
        trianglePath.close();
        canvas.drawPath(trianglePath, currPaint);
    }

    float deltaLeftX = 0;
    float deltaRightX = 0;

    private RectF getTmpRectF() {
        if (targetRectF == null) {
            return currRectF;
        }
        if (!isSame()) {
            currRectF.left = currRectF.left + deltaLeftX;
            currRectF.right = currRectF.right + deltaRightX;
        }
        return currRectF;
    }

    private boolean isSame() {
        boolean result = Math.abs((currRectF.right - currRectF.left) - (targetRectF.right - targetRectF.left)) < 10;
        return result;
    }

    public void setShowType(@SHOW_TYPE int type) {
        this.type = type;
        switch (type) {
            case SHOW_TYPE.TYPE_NORMAL:
                targetRectF = centerRectF;
                currTrianglePoints = centerTrianglePoints;
                currPaint = greenPaint;
                break;
            case SHOW_TYPE.TYPE_CANCEL:
                targetRectF = cancelRectF;
                currTrianglePoints = cancelTrianglePoints;
                currPaint = redPaint;
                break;
            case SHOW_TYPE.TYPE_TRANSLATE:
                targetRectF = translateRectF;
                currTrianglePoints = translateTrianglePoints;
                currPaint = greenPaint;
                break;
            default:
        }
        int num = 10;
        deltaLeftX = (targetRectF.left - currRectF.left) / num;
        deltaRightX = (targetRectF.right - currRectF.right) / num;
        LogUtil.d("deltaLeftX：" + deltaLeftX + "  deltaRightX： " + deltaRightX);
        invalidate();
    }
}
