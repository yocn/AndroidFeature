package com.yocn.af.view.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.view.widget.WeChatParentViewGroup;
import com.yocn.af.view.widget.WeChatVoiceView;

public class WeChatSendVoiceActivity extends BaseActivity {

    private LinearLayout optionLl;
    private TextView voiceTv;
    private WeChatVoiceView voiceView;
    private WeChatParentViewGroup rootView;

    protected void useWindowParams() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_voice);
        optionLl = findViewById(R.id.ll_option);
        voiceTv = findViewById(R.id.tv_voice);
        voiceView = findViewById(R.id.voice_view);
        rootView = findViewById(R.id.wechat_root);
    }

}
