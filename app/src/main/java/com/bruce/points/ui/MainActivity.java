package com.bruce.points.ui;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bruce.points.R;
import com.bruce.points.service.ScreenOffService;
import com.bruce.points.ui.base.BaseWithDrawerActivity;
import com.bruce.points.ui.base.TopIconConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseWithDrawerActivity {

    @BindView(R.id.vp_main_view_pager)
    ViewPager2 mViewPager;

    private List<BlankFragment> mFragmentList;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public TopIconConfig getTopIconConfig() {
        return TopIconConfig.newDefaultWhite();
    }

    @Override
    protected void initEvent() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(BlankFragment.newInstance(1));
        mFragmentList.add(BlankFragment.newInstance(2));
        mFragmentList.add(BlankFragment.newInstance(3));
        mFragmentList.add(BlankFragment.newInstance(4));
        mFragmentList.add(BlankFragment.newInstance(5));
        mViewPager.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragmentList.get(position);
            }


            @Override
            public int getItemCount() {
                return mFragmentList.size();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(this, ScreenOffService.class).setAction(Intent.ACTION_SCREEN_OFF));
    }
}
