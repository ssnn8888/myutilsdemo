package com.ssnn.mylibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: zw
 * @CreateTime: 2022-11-28  15:45
 * @Description: 分割线
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    public static final int GRID = 2;
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private final Rect mBounds = new Rect();
    private Drawable mDrawable;
    private Paint mPaint;
    private int mOrientation;
    private int mDividerWidth;
    private int mDividerHeight;
    private int mLeftSpace;
    private int mRightSpace;
    private int mTopSpace;
    private int mBottomSpace;
    private boolean lastDividerEnable;
    private boolean lastHorizontalEnable;
    private boolean lastVerticalEnable;
    private boolean horizontalEnable = true;
    private boolean verticalEnable = true;

    public RecyclerViewDivider(Context context, int orientation) {
        checkOrientation(orientation);
        initDrawable(context, orientation, 0);
    }

    public RecyclerViewDivider(Context context, int orientation, int drawableId) {
        checkOrientation(orientation);
        initDrawable(context, orientation, drawableId);
    }

    public RecyclerViewDivider(int orientation, int dividerSize, int dividerColor) {
        checkOrientation(orientation);
        initPaint(orientation, dividerSize, dividerSize, dividerColor);
    }

    public RecyclerViewDivider(int orientation, int dividerWidth, int dividerHeight, int dividerColor) {
        checkOrientation(orientation);
        initPaint(orientation, dividerWidth, dividerHeight, dividerColor);
    }

    private void checkOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL && orientation != GRID) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL or GRID");
        }
    }

    private void initDrawable(Context context, int orientation, int drawableId) {
        mOrientation = orientation;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        try {
            mDrawable = drawableId != 0 ? ContextCompat.getDrawable(context, drawableId) : a.getDrawable(0);
            if (mDrawable != null) {
                mDividerWidth = mDrawable.getIntrinsicWidth();
                mDividerHeight = mDrawable.getIntrinsicHeight();
            } else {
                initPaint(orientation, 2, 2, Color.BLACK);
            }
        } catch (Exception e) {
            initPaint(orientation, 2, 2, Color.BLACK);
        } finally {
            a.recycle();
        }
    }

    private void initPaint(int orientation, int dividerWidth, int dividerHeight, int dividerColor) {
        mOrientation = orientation;
        mDividerWidth = dividerWidth;
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置横向分割线间距
     *
     * @param leftSpace 左
     * @param rightSpace 右
     */
    public void setHorizontalSpace(int leftSpace, int rightSpace) {
        mLeftSpace = leftSpace;
        mRightSpace = rightSpace;
    }

    /**
     * 设置横向分割线间距
     *
     * @param space 间距
     */
    public void setHorizontalSpace(int space) {
        mLeftSpace = mRightSpace = space;
    }

    /**
     * 设置纵向分割线间距
     *
     * @param topSpace 上
     * @param bottomSpace 下
     */
    public void setVerticalSpace(int topSpace, int bottomSpace) {
        mTopSpace = topSpace;
        mBottomSpace = bottomSpace;
    }

    /**
     * 设置纵向分割线间距
     *
     * @param space 间距
     */
    public void setVerticalSpace(int space) {
        mTopSpace = mBottomSpace = space;
    }

    /**
     * 设置分割线间距
     *
     * @param leftSpace 左
     * @param topSpace 上
     * @param rightSpace 右
     * @param bottomSpace 下
     */
    public void setSpace(int leftSpace, int topSpace, int rightSpace, int bottomSpace) {
        mLeftSpace = leftSpace;
        mTopSpace = topSpace;
        mRightSpace = rightSpace;
        mBottomSpace = bottomSpace;
    }

    /**
     * 最后一个Item分割线是否可用
     *
     * @param lastDividerEnable {@code true} 最后一个Item分割线可用，{@code false} 不可用.
     */
    public void setLastDividerEnable(boolean lastDividerEnable) {
        this.lastDividerEnable = lastHorizontalEnable = lastVerticalEnable = lastDividerEnable;
    }

    /**
     * 最后一个Item横向分割线是否可用
     *
     * @param lastHorizontalEnable {@code true} 最后一个Item横向分割线可用，{@code false} 不可用.
     */
    public void setLastHorizontalEnable(boolean lastHorizontalEnable) {
        this.lastDividerEnable = this.lastHorizontalEnable = lastHorizontalEnable;
    }

    /**
     * 最后一个Item纵向分割线是否可用
     *
     * @param lastVerticalEnable {@code true} 最后一个Item纵向分割线可用，{@code false} 不可用.
     */
    public void setLastVerticalEnable(boolean lastVerticalEnable) {
        this.lastDividerEnable = this.lastVerticalEnable = lastVerticalEnable;
    }

    /**
     * 横向分割线是否可用
     *
     * @param horizontalEnable {@code true} 横向分割线可用，{@code false} 不可用.
     */
    public void setHorizontalEnable(boolean horizontalEnable) {
        this.horizontalEnable = horizontalEnable;
    }

    /**
     * 纵向分割线是否可用
     *
     * @param verticalEnable {@code true} 纵向分割线可用，{@code false} 不可用.
     */
    public void setVerticalEnable(boolean verticalEnable) {
        this.verticalEnable = verticalEnable;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (mOrientation) {
            case HORIZONTAL:
                outRect.set(0, 0, 0, mDividerHeight);
                break;
            case VERTICAL:
                outRect.set(0, 0, mDividerWidth, 0);
                break;
            default:
                outRect.set(0, 0, mDividerWidth, mDividerHeight);
                break;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (parent.getLayoutManager() == null) {
            return;
        }
        switch (mOrientation) {
            case HORIZONTAL:
                if (!horizontalEnable) {
                    return;
                }
                drawHorizontal(canvas, parent);
                break;
            case VERTICAL:
                if (!verticalEnable) {
                    return;
                }
                drawVertical(canvas, parent);
                break;
            default:
                if (!horizontalEnable && !verticalEnable) {
                    return;
                }
                RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    int orientation = ((GridLayoutManager) layoutManager).getOrientation();
                    if (orientation != RecyclerView.VERTICAL) {
                        return;
                    }
                    final int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                    drawVerticalGrid(canvas, parent, spanCount);
                }
                break;
        }
    }

    private void drawHorizontal(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        canvas.save();
        final int left = parent.getPaddingLeft() + mLeftSpace;
        final int right = parent.getWidth() - parent.getPaddingRight() - mRightSpace;
        final int childCount = lastDividerEnable ? parent.getChildCount() : parent.getChildCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - mDividerHeight;
            draw(left, top, right, bottom, canvas);
        }
        canvas.restore();
    }

    private void drawVertical(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        canvas.save();
        final int top = parent.getPaddingTop() + mTopSpace;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() - mBottomSpace;
        final int childCount = lastDividerEnable ? parent.getChildCount() : parent.getChildCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int right = mBounds.right + Math.round(child.getTranslationX());
            final int left = right - mDividerWidth;
            draw(left, top, right, bottom, canvas);
        }
        canvas.restore();
    }

    private void drawVerticalGrid(@NonNull Canvas canvas, @NonNull RecyclerView parent, int spanCount) {
        canvas.save();
        final int left = parent.getPaddingLeft() + mLeftSpace;
        final int right = parent.getWidth() - parent.getPaddingRight() - mRightSpace;
        final int top = parent.getPaddingTop() + mTopSpace;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() - mBottomSpace;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            if (horizontalEnable && i % spanCount == 0) {
                final int index = childCount - i;
                final int bottom1 = mBounds.bottom + Math.round(child.getTranslationY());
                final int top1 = bottom1 - mDividerHeight;
                if (index < spanCount) {
                    if (lastHorizontalEnable) {
                        int right1 = right * index / spanCount;
                        draw(left, top1, right1, bottom1, canvas);
                    }
                } else {
                    if (lastHorizontalEnable || index != spanCount) {
                        draw(left, top1, right, bottom1, canvas);
                    }
                }
            }
            if (verticalEnable && i < spanCount - 1) {
                final int index = childCount % spanCount;
                final int right1 = mBounds.right + Math.round(child.getTranslationX());
                final int left1 = right1 - mDividerWidth;
                if (i < index || index == 0) {
                    if (!lastVerticalEnable) {
                        if (i == childCount - 1) {
                            continue;
                        } else if (i == index - 1) {
                            int bottom1 = bottom - mBounds.bottom + Math.round(child.getTranslationY());
                            draw(left1, top, right1, bottom1, canvas);
                            continue;
                        }
                    }
                    draw(left1, top, right1, bottom, canvas);
                } else {
                    int bottom1 = bottom - mBounds.bottom + Math.round(child.getTranslationY());
                    draw(left1, top, right1, bottom1, canvas);
                }
            }
        }
        canvas.restore();
    }

    private void draw(int left, int top, int right, int bottom, @NonNull Canvas canvas) {
        if (mDrawable != null) {
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }
        if (mPaint != null) {
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }
}