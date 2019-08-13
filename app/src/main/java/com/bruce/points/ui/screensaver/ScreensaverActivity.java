package com.bruce.points.ui.screensaver;

import android.view.MotionEvent;

import com.bruce.points.R;
import com.bruce.points.ui.base.BaseActivity;
import com.bruce.points.ui.base.TopIconConfig;
import com.bruce.points.ui.params.ParamsActivity;
import com.bruce.points.widgets.TextureVideoView;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;

/**
 * 熄屏视频页
 */
public class ScreensaverActivity extends BaseActivity {

    @BindView(R.id.tvv_screensaver_video)
    TextureVideoView mVideoView;

    private float mRawX;
    private float mRawY;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_screensaver;
    }

    @Override
    public TopIconConfig getTopIconConfig() {
        return null;
    }

    @Override
    protected void initEvent() {
        mVideoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));

        addDisposable(RxView.touches(mVideoView).subscribe(motionEvent -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mRawX = motionEvent.getRawX();
                    mRawY = motionEvent.getRawY();
                    break;

                case MotionEvent.ACTION_UP:
                    if ((Math.abs(motionEvent.getRawX() - mRawX) < 5) && Math.abs(motionEvent.getRawY() - mRawY) < 5) {
                        startActivity(ParamsActivity.class);
                        finish();
                    }
                    break;
            }
        }));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            startVideo(R.raw.vv_full_screen);
        }
    }

    public void startVideo(int id) {
        mVideoView.setVideoPath(getVideoPath(id));
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView = null;
        }
    }
}
