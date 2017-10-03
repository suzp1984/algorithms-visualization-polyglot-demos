package io.github.jacobsu.collisionball;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    private CollisionBallVisualizer visualizer;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onStart() {
        super.onStart();

        CollisionBallView collisionBallView = (CollisionBallView) findViewById(R.id.balls);

        visualizer = new CollisionBallVisualizer(collisionBallView, 10);

        animator = ValueAnimator.ofFloat(0, 1.0f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                visualizer.move();
            }
        });

        animator.start();
    }

    @Override
    public void onStop() {
        animator.cancel();
        animator = null;

        super.onStop();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
