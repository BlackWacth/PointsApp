package com.bruce.points.ui.base;

import com.bruce.points.R;

public class TopIconConfig {

    private boolean isNeedLeftIcon;
    private boolean isNeedRightIcon;

    private IconType mIconType;

    private int mLeftIcon;
    private int mRightIcon;

    public boolean isNeedLeftIcon() {
        return isNeedLeftIcon;
    }

    public TopIconConfig setNeedLeftIcon(boolean needLeftIcon) {
        isNeedLeftIcon = needLeftIcon;
        return this;
    }

    public boolean isNeedRightIcon() {
        return isNeedRightIcon;
    }

    public TopIconConfig setNeedRightIcon(boolean needRightIcon) {
        isNeedRightIcon = needRightIcon;
        return this;
    }

    public IconType getIconType() {
        return mIconType;
    }

    public TopIconConfig setIconType(IconType iconType) {
        mIconType = iconType;
        return this;
    }

    public int getLeftIcon() {
        return mLeftIcon;
    }

    public TopIconConfig setLeftIcon(int leftIcon) {
        mLeftIcon = leftIcon;
        return this;
    }

    public int getRightIcon() {
        return mRightIcon;
    }

    public TopIconConfig setRightIcon(int rightIcon) {
        mRightIcon = rightIcon;
        return this;
    }

    public int getWhiteLeftIcon() {
        return R.drawable.ic_menu_send;
    }

    public int getWhiteRightIcon() {
        return R.drawable.ic_menu_share;
    }

    public int getBlackLeftIcon() {
        return R.drawable.ic_menu_send;
    }

    public int getBlackRightIcon() {
        return R.drawable.ic_menu_share;
    }

    public static TopIconConfig newDefaultWhite() {
        return new TopIconConfig().setNeedLeftIcon(true).setNeedRightIcon(true).setIconType(IconType.WHITE);
    }

    public static TopIconConfig newDefaultBlack() {
        return new TopIconConfig().setNeedLeftIcon(true).setNeedRightIcon(true).setIconType(IconType.BLACK);
    }

    protected enum IconType {
        WHITE,
        BLACK,
        OTHER
    }
}
