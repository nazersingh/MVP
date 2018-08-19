package com.example.saini.mvp.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.saini.mvp.R;

public class Utility {

    public static boolean isOnline(Context context)
    {
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(Context context,View myView, int posFromRight, boolean containsOverflow, final boolean isShow) {


        int width = myView.getWidth();

        if (posFromRight > 0)
            width -= (posFromRight * context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2);
        if (containsOverflow)
            width -= context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);

        anim.setDuration((long) 220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if (isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();
    }
}
