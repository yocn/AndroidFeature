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
    private final PointF[] translateTrianglePoints = new PointF[3];
    private final PointF[] cancelTrianglePoints = new PointF[3];
    private final PointF[] centerTrianglePoints = new PointF[3];
    private final PointF[] currTrianglePoints = new PointF[3];
    private PointF[] targetTrianglePoints = new PointF[3];
    private float triangleHeight;
    private Path trianglePath;
    private final float triangleLine = getResources().getDimensionPixelOffset(R.dimen.height_triangle_line);
    private final int topDivider = getResources().getDimensionPixelOffset(R.dimen.height_top_divider);
    private float deltaLeftX = 0, deltaRightX = 0, deltaTopY = 0, deltaTriangleX = 0;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            SHOW_TYPE.TYPE_NORMAL,
            SHOW_TYPE.TYPE_CANCEL,
            SHOW_TYPE.TYPE_TRANSLATE
    })
    public @interface SHOW_TYPE {
        int TYPE_NORMAL = 101;
        int TYPE_CANCEL = 102;
        int TYPE_TRANSLATE = 103;
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
        int height = getResources().getDimensionPixelSize(R.dimen.height_round_rect);
        float width = getMeasuredWidth();
        if (translateRectF == null) {
            translateRectF = new RectF(0, 0, width, height - triangleHeight);
            cancelRectF = new RectF(0, topDivider, height - triangleHeight, height - triangleHeight);
            centerRectF = new RectF(width / 2 - (height - triangleHeight), topDivider, width / 2 + (height - triangleHeight), height - triangleHeight);

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
            currRectF = new RectF(centerRectF);
            currTrianglePoints[0] = new PointF(centerTrianglePoints[0].x, centerTrianglePoints[0].y);
            currTrianglePoints[1] = new PointF(centerTrianglePoints[1].x, centerTrianglePoints[1].y);
            currTrianglePoints[2] = new PointF(centerTrianglePoints[2].x, centerTrianglePoints[2].y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refreshRectRectF();
        refreshTriangleRectF();
        canvas.drawRoundRect(currRectF, 50, 50, currPaint);
        trianglePath.reset();
        trianglePath.setFillType(Path.FillType.EVEN_ODD);
        trianglePath.moveTo(currTrianglePoints[0].x, currTrianglePoints[0].y);
        trianglePath.lineTo(currTrianglePoints[1].x, currTrianglePoints[1].y);
        trianglePath.lineTo(currTrianglePoints[2].x, currTrianglePoints[2].y);
        trianglePath.close();
        canvas.drawPath(trianglePath, currPaint);
    }


    private void refreshRectRectF() {
        if (!isSameRectRectF()) {
            currRectF.top += deltaTopY;
            currRectF.left += deltaLeftX;
            currRectF.right += deltaRightX;
            invalidate();
        }
    }

    private void refreshTriangleRectF() {
        if (!isSameTriangleRectF()) {
            currTrianglePoints[0].x += deltaTriangleX;
            currTrianglePoints[1].x += deltaTriangleX;
            currTrianglePoints[2].x += deltaTriangleX;
            invalidate();
        }
    }

    private boolean isSameRectRectF() {
        if (targetRectF == null) {
            return true;
        }
        return Math.abs((currRectF.right - currRectF.left) - (targetRectF.right - targetRectF.left)) < 10;
    }

    private boolean isSameTriangleRectF() {
        if (targetTrianglePoints == null || targetTrianglePoints[0] == null) {
            return true;
        }
        return Math.abs(targetTrianglePoints[0].x - currTrianglePoints[0].x) < 10;
    }

    public void setShowType(@SHOW_TYPE int type) {
        switch (type) {
            case SHOW_TYPE.TYPE_NORMAL:
                targetRectF = centerRectF;
                targetTrianglePoints = centerTrianglePoints;
                currPaint = greenPaint;
                break;
            case SHOW_TYPE.TYPE_CANCEL:
                targetRectF = cancelRectF;
                targetTrianglePoints = cancelTrianglePoints;
                currPaint = redPaint;
                break;
            case SHOW_TYPE.TYPE_TRANSLATE:
                targetRectF = translateRectF;
                targetTrianglePoints = translateTrianglePoints;
                currPaint = greenPaint;
                break;
            default:
        }
        int num = 10;
        deltaTopY = (targetRectF.top - currRectF.top) / num;
        deltaLeftX = (targetRectF.left - currRectF.left) / num;
        deltaRightX = (targetRectF.right - currRectF.right) / num;
        deltaTriangleX = (targetTrianglePoints[0].x - currTrianglePoints[0].x) / num;
        invalidate();
    }
}
