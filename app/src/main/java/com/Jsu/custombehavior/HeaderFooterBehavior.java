package com.Jsu.custombehavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by JSu on 2016/5/30.
 */

public class HeaderFooterBehavior extends CoordinatorLayout.Behavior<View> {

    private int childHeight;
    private boolean isHide = false;
    private int totalDistance;

    public HeaderFooterBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        childHeight = child.getHeight();
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0 && totalDistance < 0 || dy < 0 && totalDistance > 0) {
            totalDistance = 0;
        }
        totalDistance += dy;
        if (!isHide && totalDistance > childHeight) {
            hideView(child);
            isHide = true;
        } else if (isHide && totalDistance < childHeight) {
            showView(child);
            isHide = false;
        }
    }

    private void hideView(View view) {
        ObjectAnimator animator = null;
        if (view.getId() == R.id.normal_header) {
            animator = ObjectAnimator.ofFloat(view, "translationY", 0, -(view.getHeight()));
        } else if (view.getId() == R.id.normal_footer) {
            animator = ObjectAnimator.ofFloat(view, "translationY", 0, view.getHeight());
        } else if (view.getId() == R.id.fab) {
            ViewGroup.MarginLayoutParams fabLp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            animator = ObjectAnimator.ofFloat(view, "translationY", 0, view.getHeight() + fabLp.bottomMargin);
        }
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.start();
    }

    private void showView(View view) {
        ObjectAnimator animator = null;
        if (view.getId() == R.id.normal_header) {
            animator = ObjectAnimator.ofFloat(view, "translationY", -(view.getHeight()), 0);
        } else if (view.getId() == R.id.normal_footer) {
            animator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0);
        } else if (view.getId() == R.id.fab) {
            ViewGroup.MarginLayoutParams fabLp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            animator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight() + fabLp.bottomMargin, 0);
        }
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(300);
        animator.start();
    }
}
