package com.cqmc.drawcenterview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Drawable居中的TextView
 *
 * @author lwc
 * @date 2016.4.27
 * @note 1. 支持Drawable上、下、左、右四个方向。
 */
public class DrawableCenterTextView extends android.support.v7.widget.AppCompatTextView {
    /** 图片列表 */
    private Drawable[] drawables;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public DrawableCenterTextView(Context context) {
        super(context);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     */
    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attrs 属性
     * @param defStyle 样式
     */
    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        drawables = getCompoundDrawables();
        if (drawables[0] != null || drawables[2] != null) {
            // 左、右
            setGravity(Gravity.CENTER_VERTICAL | (drawables[0] != null ? Gravity.START : Gravity.END));
        } else if (drawables[1] != null || drawables[3] != null) {
            // 上、下
            setGravity(Gravity.CENTER_HORIZONTAL | (drawables[1] != null ? Gravity.TOP : Gravity.BOTTOM));
        }

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int drawablePadding = getCompoundDrawablePadding();
        if (drawables[0] != null) {
            // 左
            int drawableWidth = drawables[0].getIntrinsicWidth();
            float bodyWidth;
            if (TextUtils.isEmpty(getText())) {
                bodyWidth = drawableWidth;
            } else {
                float textWidth = getPaint().measureText(getText().toString());
                bodyWidth = textWidth + drawableWidth + drawablePadding;
            }
            canvas.translate((getWidth() - bodyWidth) / 2, 0);

        } else if (drawables[2] != null) {
            // 右
            int drawableWidth = drawables[2].getIntrinsicWidth();
            float bodyWidth;
            if (TextUtils.isEmpty(getText())) {
                bodyWidth = drawableWidth;
            } else {
                float textWidth = getPaint().measureText(getText().toString());
                bodyWidth = textWidth + drawableWidth + drawablePadding;
            }
            canvas.translate((bodyWidth - getWidth()) / 2, 0);

        } else if (drawables[1] != null) {
            // 上
            int drawableHeight = drawables[1].getIntrinsicHeight();
            float bodyHeight;
            if (TextUtils.isEmpty(getText())) {
                bodyHeight = drawableHeight;
            } else {
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                float fontHeight = (float) Math.ceil(fm.descent - fm.ascent);
                bodyHeight = fontHeight + drawableHeight + drawablePadding;
            }
            canvas.translate(0, (getHeight() - bodyHeight) / 2);

        } else if (drawables[3] != null) {
            // 下
            int drawableHeight = drawables[3].getIntrinsicHeight();
            float bodyHeight;
            if (TextUtils.isEmpty(getText())) {
                bodyHeight = drawableHeight;
            } else {
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                float fontHeight = (float) Math.ceil(fm.descent - fm.ascent);
                bodyHeight = fontHeight + drawableHeight + drawablePadding;
            }
            canvas.translate(0, (bodyHeight - getHeight()) / 2);
        }
        super.onDraw(canvas);
    }
}
