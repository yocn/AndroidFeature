package com.yocn.af.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yocn.af.R;
import com.yocn.af.presenter.LogUtil;
import com.yocn.af.presenter.OnFocusInterface;
import com.yocn.af.presenter.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

public class WeChatVoiceTextView extends AppCompatTextView implements OnFocusInterface {
    private String TAG = "WeChatVoiceTextView";
    private WeChatParentViewGroup.OnVoiceViewStatusListener onVoiceViewStatusListener;

    public WeChatVoiceTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public WeChatVoiceTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeChatVoiceTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    public void setListener(WeChatParentViewGroup.OnVoiceViewStatusListener onVoiceViewStatusListener) {
        this.onVoiceViewStatusListener = onVoiceViewStatusListener;
    }

    public void updateFocus(boolean isFocus) {
        if (isFocus) {
            // 得到焦点状态
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_round_on_focus));
        } else {
            // 失去焦点
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_round));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        LogUtil.d(TAG + "::dispatchTouchEvent:::" + result + "  " + ViewUtil.printEvent(ev));
        updateFocus(ev.getAction() == MotionEvent.ACTION_MOVE || ev.getAction() == MotionEvent.ACTION_DOWN);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            onVoiceViewStatusListener.showVoiceView();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        LogUtil.d(TAG + "::onTouchEvent::" + result + "  " + ViewUtil.printEvent(event));
        return true;
    }
}
