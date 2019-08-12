package com.bruce.points.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bruce.points.R;

/**
 * author：HuaZhongWei
 * date：2019/8/12
 * description： 基类
 **/
public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout mAppContainer;
    private FrameLayout mContentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mAppContainer = findViewById(R.id.fl_all_container);
        fullScreen();

        mContentContainer = findViewById(R.id.fl_content_container);
        mContentContainer.removeAllViews();
        LayoutInflater.from(this).inflate(getLayoutResId(), mContentContainer);


    }

    private void fullScreen() {
        mAppContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public abstract int getLayoutResId();

}
