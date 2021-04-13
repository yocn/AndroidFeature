package com.yocn.af.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.yocn.af.R;
import com.yocn.af.util.DisplayUtil;
import com.yocn.af.util.LogUtil;

import androidx.annotation.Nullable;

public class WeChatVoiceBottomArc extends View {

    private Paint paint;
    private RectF rectf;

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
            float radius = style.getDimension(R.styleable.WeChatVoiceBottomArc_radius, 0);
        } finally {
            style.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0; //结果
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        LogUtil.d("specSize:::" + specSize);
        switch (specMode) {
            case MeasureSpec.AT_MOST:  // 子容器可以是声明大小内的任意大小
            case MeasureSpec.EXACTLY: //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.  比如EditTextView中的DrawLeft
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:  //父容器对于子容器没有任何限制,子容器想要多大就多大. 所以完全取决于子view的大小
                result = DisplayUtil.dip2px(getContext(), 120);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        int height = getContext().getResources().getDimensionPixelSize(R.dimen.arc_height);
        int[] wh = DisplayUtil.getHW(getContext());
        LogUtil.d("height----" + height);
        int screenWidth = wh[0];
        int screenHeight = wh[1];
        //采用默认设置创建一个画笔
        paint = new Paint();
        paint.setAntiAlias(true);//使用抗锯齿功能
        paint.setColor(0xFFCCC7CC);    //设置画笔的颜色为绿色
        paint.setStyle(Paint.Style.FILL);//设置画笔类型为填充
        rectf = new RectF(-screenWidth / 2, 0, screenWidth + screenWidth / 2, height * 6);
        LinearGradient linearGradient = new LinearGradient(screenWidth / 2, 0, screenWidth / 2, height,
                0xFF999999, 0xFFe6e6e6, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectf, 0, 360, false, paint);//绘制圆弧，不含圆心
    }
}
