package io.github.suzp1984.pi;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlgoCanvas extends Canvas {

    private GraphicsContext gc;
    public int count = 0;
    public int insideNum = 0;

    public AlgoCanvas(int width) {
        super(width, width);

        gc = getGraphicsContext2D();
        setCache(true);

        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(0,0,width, width);
    }

    public void renderPoint(double x, double y) {
        if (isInsideCircle(x, y)) {
            gc.setFill(Color.RED);
            insideNum ++;
        } else {
            gc.setFill(Color.BLUE);
        }

        count ++;

        gc.fillOval(x - 1, y - 1, 2, 2);
    }

    public void showPI() {
        double pi = 4 * ((double) insideNum / count);
        System.out.println(pi);
    }

    private boolean isInsideCircle(double x, double y) {
        double r = getWidth()/2;
        return (r - x)*(r - x) + (r - y)*(r - y) < r * r;
    }
}
