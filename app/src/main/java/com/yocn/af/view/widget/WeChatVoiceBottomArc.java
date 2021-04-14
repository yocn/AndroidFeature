package com.yocn.af.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.yocn.af.R;
import com.yocn.af.util.DisplayUtil;

import androidx.annotation.Nullable;

public class WeChatVoiceBottomArc extends View {

    private Paint paint;
    private RectF rectf;
    private String type;
    private int height;
    private int screenWidth;
    private int[] screenWH;

    public WeChatVoiceBottomArc(Context context) {
        this(context, null);
    }

    public WeChatVoiceBottomArc(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeChatVoiceBottomArc(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.WeChatVoiceBottomArc);
        try {
            type = style.getString(R.styleable.WeChatVoiceBottomArc_type);
        } finally {
            style.recycle();
        }
    }

    private boolean isLightMode() {
        return "light".equals(type);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        height = getContext().getResources().getDimensionPixelSize(R.dimen.arc_height_dark);
        screenWH = DisplayUtil.getHW(getContext());
        screenWidth = screenWH[0];
        if (isLightMode()) {
            initLight();
        } else {
            initDark();
        }
    }

    private void initLight() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFCCC7CC);
        paint.setStyle(Paint.Style.FILL);
        rectf = new RectF(-screenWidth / 2, 0, screenWidth + screenWidth / 2, height * 6);
        LinearGradient linearGradient = new LinearGradient(screenWidth / 2, 0, screenWidth / 2, height,
                0xFF999999, 0xFFe6e6e6, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    private void initDark() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF4c4c4c);
        paint.setStyle(Paint.Style.FILL);
        rectf = new RectF(-screenWidth / 2, 20, screenWidth + screenWidth / 2, height * 6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectf, 0, 360, false, paint);
    }


    private boolean pointInRect(float x, float y) {
        Rect rect = new Rect(0, screenWH[1] - height, screenWH[0], screenWH[1]);
        return pointInRect(x, y, rect);
    }

    private boolean pointInRect(float x, float y, Rect rect) {
        return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
    }

    public boolean isOnRect(float x, float y) {
        return pointInRect(x, y);
    }
}
