package io.github.suzp1984.fxdemo;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class AlgoVisualizer {
    private Circle[] circles;
    private AlgoCanvas mCanvas;
    private boolean isAnimated = true;

    public AlgoVisualizer(AlgoCanvas canvas, int N) {
        mCanvas = canvas;
        mCanvas.setFocusTraversable(true);
        mCanvas.setOnKeyPressed(event -> {
            if (event.getText().equals(" ")) {
                isAnimated = !isAnimated;
            }
        });

        mCanvas.setOnMouseClicked(event -> {

            for (Circle circle : circles) {
                if (circle.contain(event.getX(), event.getY())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        });

        int sceneWidth = (int) canvas.getWidth();
        int sceneHeight = (int) canvas.getHeight();

        // 初始化数据
        circles = new Circle[N];
        int R = 50;
        for(int i = 0 ; i < N ; i ++){
            int x = (int)(Math.random()*(sceneWidth-2*R)) + R;
            int y = (int)(Math.random()*(sceneHeight-2*R)) + R;
            int vx = (int)(Math.random()*11) - 5;
            int vy = (int)(Math.random()*11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        new Thread(() -> {
            run();
        }).start();
    }

    private void run(){

        while(true){
            // 绘制数据
            mCanvas.render(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            if(isAnimated)
                for(Circle circle : circles)
                    circle.move(0, 0, (int) mCanvas.getWidth(), (int) mCanvas.getHeight());
        }
    }
}
