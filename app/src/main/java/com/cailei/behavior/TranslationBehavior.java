package com.cailei.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author : cailei
 * @date : 2020-03-27 12:19
 * @description :
 */
public class TranslationBehavior extends FloatingActionButton.Behavior {
    private LinearLayout mBottomLinerLayout;

    public TranslationBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }


    //FloatingButton 内置的Behavior 判断coordinatorLayout 的滚动方向 return true 才会继续执行onNestedScroll 这个方法
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }



    private boolean isOut=false;
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

        mBottomLinerLayout=coordinatorLayout.findViewById(R.id.bottom_tab_layout);
        float childMarginBottom = ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin;
        float totalHeigh = childMarginBottom + child.getMeasuredHeight();

        //totalHeigh 是FloatingButton 距离底部的长度
        //往上滑动 消失
        if (dyConsumed > 0) {
            if(!isOut) {
                child.animate().translationY(totalHeigh).setDuration(500).start();
                mBottomLinerLayout.animate().translationY(totalHeigh).setDuration(500).start();
                isOut=true;
            }
        }

        if (dyConsumed < 0) {
            if(isOut) {
                child.animate().translationY(0).setDuration(500).start();
                mBottomLinerLayout.animate().translationY(0).setDuration(500).start();
                isOut=false;
            }
        }
    }

}
