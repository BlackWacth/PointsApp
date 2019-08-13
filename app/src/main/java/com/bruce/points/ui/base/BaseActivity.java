package com.bruce.points.ui.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bruce.points.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author：HuaZhongWei
 * date：2019/8/12
 * description： 基类
 **/
public abstract class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mCompositeDisposable;
    private FrameLayout mAppContainer;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setContentView(R.layout.activity_base);
        mAppContainer = findViewById(R.id.fl_all_container);
        fullScreen();

        initTopIcon();

        FrameLayout contentContainer = findViewById(R.id.fl_content_container);
        contentContainer.removeAllViews();
        LayoutInflater.from(this).inflate(getLayoutResId(), contentContainer);
        mUnbinder = ButterKnife.bind(this);

        initEvent();
    }

    private void initTopIcon() {
        ImageView leftIconView =  findViewById(R.id.iv_top_left);
        ImageView rightIconView =  findViewById(R.id.iv_top_right);

        TopIconConfig topIconConfig = getTopIconConfig();
        if (topIconConfig == null) {
            leftIconView.setVisibility(View.GONE);
            rightIconView.setVisibility(View.GONE);
        } else {
            if (topIconConfig.isNeedLeftIcon()) {
                leftIconView.setVisibility(View.VISIBLE);
                switch (topIconConfig.getIconType()) {
                    case WHITE:
                        leftIconView.setImageResource(topIconConfig.getWhiteLeftIcon());
                        break;

                    case BLACK:
                        leftIconView.setImageResource(topIconConfig.getBlackLeftIcon());
                        break;

                    case OTHER:
                        leftIconView.setImageResource(topIconConfig.getLeftIcon());
                        break;
                }
                onClick(leftIconView, 300, o -> onLeftIconClick());
            } else {
                leftIconView.setVisibility(View.GONE);
            }

            if (topIconConfig.isNeedRightIcon()) {
                rightIconView.setVisibility(View.VISIBLE);
                switch (topIconConfig.getIconType()) {
                    case WHITE:
                        rightIconView.setImageResource(topIconConfig.getWhiteRightIcon());
                        break;

                    case BLACK:
                        rightIconView.setImageResource(topIconConfig.getBlackRightIcon());
                        break;

                    case OTHER:
                        rightIconView.setImageResource(topIconConfig.getRightIcon());
                        break;
                }
                onClick(rightIconView, 300, o -> onRightIconClick());
            } else {
                rightIconView.setVisibility(View.GONE);
            }
        }
    }

    private void fullScreen() {
        mAppContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    protected void onClick(View view, int duration, Consumer<? super Object> consumer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxView.clicks(view)
                .throttleFirst(duration, TimeUnit.MILLISECONDS)
                .subscribe(consumer));
    }

    @Override
    protected void onDestroy() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = null;

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mUnbinder = null;
        super.onDestroy();
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void onLeftIconClick() {
        onBackPressed();
    }

    private void onRightIconClick() {

    }

    public String getVideoPath(int videoId) {
        return "android.resource://" + getApplicationContext().getPackageName() + "/" + videoId;
    }

    public void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    public abstract int getLayoutResId();

    public abstract TopIconConfig getTopIconConfig();

    protected abstract void initEvent();
}
