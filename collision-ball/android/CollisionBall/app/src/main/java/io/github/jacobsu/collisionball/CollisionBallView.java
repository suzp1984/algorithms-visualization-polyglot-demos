package io.github.jacobsu.collisionball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CollisionBallView extends View {

    private Circle[] balls;
    private Paint mPaint;

    public CollisionBallView(Context context) {
        super(context);

        init();
    }

    public CollisionBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CollisionBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (balls != null && balls.length > 0) {
            for (Circle circle : balls) {
                if (circle.isFilled) {
                    mPaint.setStyle(Paint.Style.FILL);
                } else {
                    mPaint.setStyle(Paint.Style.STROKE);
                }

                canvas.drawCircle(circle.x, circle.y, circle.getR(), mPaint);
            }
        }

    }

    public void render(Circle[] circles) {
        this.balls = circles;

        invalidate();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(Color.RED);

        setDrawingCacheEnabled(true);

    }
}
