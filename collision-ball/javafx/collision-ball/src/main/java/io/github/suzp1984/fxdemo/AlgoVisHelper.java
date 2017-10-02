package io.github.suzp1984.fxdemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlgoVisHelper {
    private static Color scolor;

    private AlgoVisHelper() {}

    public static void setStrokeWidth(GraphicsContext gc, int width) {
        gc.setLineWidth(width);
    }

    public static void setColor(GraphicsContext gc, Color color) {
        scolor = color;
    }

    public static void strokeCircle(GraphicsContext gc, int x, int y, int r) {
        gc.setStroke(scolor != null ? scolor : Color.RED);
        gc.strokeOval(x - r, y - r, 2*r, 2*r);
    }

    public static void fillCircle(GraphicsContext gc, int x, int y, int r) {
        gc.setFill(scolor != null ? scolor : Color.RED);
        gc.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
