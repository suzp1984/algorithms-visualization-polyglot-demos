package io.github.suzp1984.money;

import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AlgoCanvas extends Canvas {

    private GraphicsContext gc;
    private int canvasWidth;
    private int canvasHeight;

    public AlgoCanvas(int width, int height) {
        super(width, height);

        canvasWidth = width;
        canvasHeight = height;
        gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        setCache(true);
    }

    public void render(int[] moneys) {
        gc.clearRect(0, 0, getWidth(), getHeight());

        Arrays.sort(moneys);
        int w = (int) (getWidth() / moneys.length);

        for (int i = 0; i < moneys.length; i++) {
            gc.fillRect(i*w + 1, getHeight() - moneys[i], w - 1, moneys[i]);
        }
    }
}
