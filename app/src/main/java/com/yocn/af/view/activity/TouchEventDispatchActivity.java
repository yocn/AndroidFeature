package com.yocn.af.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.view.widget.eventdispatch.TouchView;
import com.yocn.af.view.widget.eventdispatch.TouchViewGroup;

public class TouchEventDispatchActivity extends BaseActivity {

    private TouchViewGroup touchViewGroupOut;
    private TouchViewGroup touchViewGroupIn;
    private TextView logTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        touchViewGroupOut = findViewById(R.id.vg);
        touchViewGroupIn = findViewById(R.id.view);
        logTv = findViewById(R.id.tv_log);
        touchViewGroupOut.setPosition("out");
        touchViewGroupIn.setPosition("in");
    }

}
