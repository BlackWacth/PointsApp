package com.bruce.points.ui;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bruce.points.R;
import com.bruce.points.ui.base.BaseFragment;

public class BlankFragment extends BaseFragment {

    private int mIndex;

    public BlankFragment() {

    }

    public static BlankFragment newInstance(int index) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("BlankFragment_index", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt("BlankFragment_index");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("hzw", "onStart : " + mIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("hzw", "onResume : " + mIndex);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("hzw", "onPause : " + mIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("hzw", "onStop : " + mIndex);
    }
}
