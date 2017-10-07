package io.github.jacobsu.pi_android;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ValueAnimator animator;
    private PISimulateView piView;
    private TextView txtView;

    private PISimulateView.PIObserver piObserver = new PISimulateView.PIObserver() {

        @Override
        public void update(float pi) {
            if (txtView != null) {
                txtView.setText(String.valueOf(pi));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        piView = (PISimulateView) findViewById(R.id.pi);
        txtView = (TextView) findViewById(R.id.txt);

    }

    @Override
    public void onStart() {
        super.onStart();

        animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                piView.render();
            }
        });

        animator.start();

        piView.registerObserver(piObserver);
    }

    @Override
    public void onStop() {
        animator.cancel();
        animator = null;

        piView.unregisterObserver(piObserver);

        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
