package io.github.jacobsu.pi_android;

import android.content.Context;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Observer;

/**
 * Created by jacobsu on 10/6/17.
 */

public class PISimulateView extends View {

    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Paint paint;

    private int R = 0;
    private int intervalReport = 500;

    private int count = 0;
    private int redCount = 0;

    private final PIObservable piObservable = new PIObservable();

    public PISimulateView(Context context) {
        super(context);

        init();
    }

    public PISimulateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PISimulateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w == 0 || h == 0) {
            return;
        }

        int rectEdge = w < h ? w : h;
        R = rectEdge / 2;

        Bitmap newBitmap = Bitmap.createBitmap(rectEdge, rectEdge, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas(newBitmap);

        if (cacheBitmap != null && !cacheBitmap.isRecycled()) {
            cacheBitmap.recycle();
        }

        cacheBitmap = newBitmap;
        cacheCanvas = newCanvas;

        count = 0;
        redCount = 0;

        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        cacheCanvas.drawCircle(rectEdge / 2, rectEdge / 2, rectEdge/2, paint);

        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(cacheBitmap, 0, 0, null);
    }

    public void registerObserver(PIObserver observer) {
        piObservable.registerObserver(observer);
    }

    public void unregisterObserver(PIObserver observer) {
        piObservable.unregisterObserver(observer);
    }

    public void render() {
        for (int i = 0; i < 20; i++ ) {
            double x = Math.random() * 2 * R;
            double y = Math.random() * 2 * R;

            render((float) x, (float) y);

            if (count % intervalReport == 0 && redCount != 0) {
                onPIChanged((float) redCount / count * 4);
            }
        }

        invalidate();
    }

    private void render(float x, float y) {
        if (cacheCanvas != null) {
            if (insideCircle(x, y)) {
                paint.setColor(Color.RED);
                redCount += 1;
            } else {
                paint.setColor(Color.BLUE);
            }

            count += 1;

            cacheCanvas.drawCircle(x, y, 2, paint);
        }
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private boolean insideCircle(float x, float y) {
        return (x - R) * (x - R) + (y - R) * (y - R) < R * R;
    }

    private void onPIChanged(float pi) {
        piObservable.updatePI(pi);
    }

    private class PIObservable extends Observable<PIObserver> {
        public void updatePI(float pi) {
            Log.d("PI", "report pi = " + pi);
            synchronized (mObservers) {
                for (PIObserver observer : mObservers) {
                    observer.update(pi);
                }
            }
        }
    }

    public static class PIObserver {

        public void update(float pi) {

        }
    }
}
