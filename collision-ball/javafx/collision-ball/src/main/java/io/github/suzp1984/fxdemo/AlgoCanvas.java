package io.github.suzp1984.fxdemo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlgoCanvas extends Canvas {

    private GraphicsContext gc;
    private int canvasWidth;
    private int canvasHeight;

    public AlgoCanvas(int width, int height) {
        super(width, height);

        canvasWidth = width;
        canvasHeight = height;
        gc = getGraphicsContext2D();
    }

    public void render(Circle[] circles) {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setLineWidth(1);
        for (Circle circle : circles) {
            if (circle.isFilled) {
                gc.setFill(Color.RED);
                gc.fillOval(circle.x - circle.getR(), circle.y - circle.getR(), 2 * circle.getR(), 2 * circle.getR());
            } else {
                gc.setStroke(Color.RED);
                gc.strokeOval(circle.x - circle.getR(), circle.y - circle.getR(), 2 * circle.getR(), 2 * circle.getR());
            }
        }
    }
}
