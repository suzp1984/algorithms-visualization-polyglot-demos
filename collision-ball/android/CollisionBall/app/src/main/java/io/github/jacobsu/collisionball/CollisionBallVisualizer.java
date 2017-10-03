package io.github.jacobsu.collisionball;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CollisionBallVisualizer {

    private final String TAG = "CollionBall";

    private Circle[] circles;
    private CollisionBallView view;
    private boolean isAnimated = true;

    public CollisionBallVisualizer(final CollisionBallView view, int n) {

        this.view = view;
        circles = new Circle[n];
        int R = 50;

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int left, int top, int right, int bottom,
                                       int oldL, int oldT, int oldR, int oldB) {
                Log.d(TAG, "(l, t, r, b) = " + left + ", " + top + ", " + right + ", " + bottom);
            }
        });

        int sceneWidth = view.getWidth();
        int sceneHeight = view.getHeight();

        view.setDrawingCacheEnabled(true);
        for (int i = 0; i < n; i++) {
            int x = (int)(Math.random()*(sceneWidth-2*R)) + R;
            int y = (int)(Math.random()*(sceneHeight-2*R)) + R;
            int vx = (int)(Math.random()*11) - 5;
            int vy = (int)(Math.random()*11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    Log.d(TAG, "(x, y) = " + x + ", " + y);

                    boolean shouldPause = true;

                    for (Circle circle : circles) {
                        if (circle.contain(x, y)) {
                            circle.isFilled = !circle.isFilled;
                            shouldPause = false;
                        }
                    }

                    if (shouldPause) {
                        isAnimated = !isAnimated;
                    }

                    view.invalidate();
                }

                return true;
            }
        });

        view.requestFocus();
    }

    public void move() {
        if (isAnimated) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    view.render(circles);
                }
            });


            for (Circle circle : circles) {
                circle.move(0, 0, view.getWidth(), view.getHeight());
            }
        }
    }
}
