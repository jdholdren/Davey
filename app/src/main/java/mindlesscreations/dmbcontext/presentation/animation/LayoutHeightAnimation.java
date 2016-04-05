package mindlesscreations.dmbcontext.presentation.animation;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.lang.ref.WeakReference;
import android.view.View;


public class LayoutHeightAnimation extends Animation {

    private WeakReference<View> view;
    private int newValue;
    private int oldValue;

    public LayoutHeightAnimation(View view, int newValue) {
        this.view = new WeakReference<>(view);
        this.newValue = newValue;
        this.oldValue = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (view.get() != null) {
            // Obtain the reference
            View v = view.get();

            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.height = this.oldValue + (int)((this.newValue - this.oldValue) * interpolatedTime);
            v.setLayoutParams(params);
        }
    }
}
