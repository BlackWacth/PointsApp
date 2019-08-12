package com.bruce.points.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    protected CompositeDisposable mCompositeDisposable;

    private Unbinder mUnbinder;
    private OnBackPressedCallback mOnBackPressedCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        if (isNeedOnBackPressed()) {
            mOnBackPressedCallback = new OnBackPressedCallback(isNeedOnBackPressed()) {
                @Override
                public void handleOnBackPressed() {
                    onBackPressed();
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(this, mOnBackPressedCallback);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = null;

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mUnbinder = null;

        if (mOnBackPressedCallback != null) {
            mOnBackPressedCallback.remove();
        }
        super.onDestroy();
    }

    protected void onClick(View view, int duration, Consumer<? super Object> consumer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxView.clicks(view)
                .throttleFirst(duration, TimeUnit.MILLISECONDS)
                .subscribe(consumer));
    }

    public boolean isNeedOnBackPressed() {
        return false;
    }

    public abstract int getLayoutResId();

    protected abstract void initEvent();

    protected void onBackPressed() {

    }
}
