package com.bruce.points.ui.params;

import android.view.View;

import com.bruce.points.R;
import com.bruce.points.ui.main.MainActivity;
import com.bruce.points.ui.base.BaseActivity;
import com.bruce.points.ui.base.TopIconConfig;

import butterknife.BindView;

/**
 * 参数页
 */
public class ParamsActivity extends BaseActivity {

    @BindView(R.id.tv_params_to_main)
    View mToMainView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_params;
    }

    @Override
    public TopIconConfig getTopIconConfig() {
        return null;
    }

    @Override
    protected void initEvent() {
        onClick(mToMainView, 300, o -> startActivity(MainActivity.class));
    }
}
