package com.bruce.points.ui.main;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.ServiceUtils;
import com.bruce.points.R;
import com.bruce.points.service.ScreenOffService;
import com.bruce.points.ui.BlankFragment;
import com.bruce.points.ui.base.BaseWithDrawerActivity;
import com.bruce.points.ui.base.TopIconConfig;
import com.bruce.points.ui.main.fragment.HomeFirstFragment;
import com.bruce.points.ui.main.fragment.HomeSecondFragment;
import com.bruce.points.ui.main.fragment.HomeThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseWithDrawerActivity {

    @BindView(R.id.vp_main_view_pager)
    ViewPager2 mViewPager;

    private List<Fragment> mFragmentList;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public TopIconConfig getTopIconConfig() {
        return TopIconConfig.newDefaultWhite().setNeedLeftIcon(false);
    }

    @Override
    protected void initEvent() {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        }
        mFragmentList.clear();
        mFragmentList.add(new HomeFirstFragment());
        mFragmentList.add(new HomeSecondFragment());
        mFragmentList.add(new HomeThirdFragment());
//        mViewPager.setLayoutDirection(View.LAYOUT_DIRECTION_INHERIT);
        mViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
//        mViewPager.setUserInputEnabled(false);
//        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//                if (state == ViewPager2.SCROLL_STATE_IDLE) {
//                    if (mViewPager.getCurrentItem() == 0) {
//                        mViewPager.setCurrentItem(mFragmentList.size() - 2, false);
//                    } else if (mViewPager.getCurrentItem() == mFragmentList.size() - 1) {
//                        mViewPager.setCurrentItem(1, false);
//                    }
//                }
//            }
//        });
        mViewPager.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragmentList.get(position % mFragmentList.size());
            }




            @Override
            public int getItemCount() {
                return mFragmentList.size() * 100;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        initScreenOffService();
    }

    private void initScreenOffService() {
        if (!ServiceUtils.isServiceRunning(ScreenOffService.class)) {
            Intent intent = new Intent(this, ScreenOffService.class);
            intent.setAction(Intent.ACTION_SCREEN_OFF);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startService(intent);
        }
    }
}
