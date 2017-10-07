package io.github.jacobsu.money_simulation_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by jacobsu on 10/7/17.
 */

public class MoneySimulationView extends View {

    private Paint paint;
    private int[] moneys;

    public MoneySimulationView(Context context) {
        super(context);

        init();
    }

    public MoneySimulationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MoneySimulationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d("Money", "w = " + w);
        Log.d("Money", "getWidth = " + getWidth());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (moneys != null && moneys.length > 0) {

            int w = getWidth() / (moneys.length - 1);

            for (int i = 0; i < moneys.length; i++) {
                float left = i * w + 1;
                float right = left + w - 1;
                canvas.drawRect(left, getHeight() - moneys[i], right, getHeight(), paint);
            }
        }
    }

    public void render(int[] moneys) {
        this.moneys = moneys.clone();

        Arrays.sort(this.moneys);

        invalidate();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        setDrawingCacheEnabled(true);
    }
}
