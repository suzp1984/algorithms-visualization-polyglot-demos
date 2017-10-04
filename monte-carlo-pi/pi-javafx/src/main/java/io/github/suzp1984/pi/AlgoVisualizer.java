package io.github.suzp1984.pi;

public class AlgoVisualizer {

    private AlgoCanvas mCanvas;
    private int mInterval;
    private double mR = 0;

    public AlgoVisualizer(AlgoCanvas canvas, int interval) {
        mCanvas = canvas;
        mInterval = interval;
        mR = canvas.getWidth();

        new Thread(() -> {
            run();
        }).start();
    }

    private void run() {
        while (true) {
            for (int i = 0; i < mInterval; i++) {
                double x = Math.random() * mR;
                double y = Math.random() * mR;

                mCanvas.renderPoint(x, y);
            }

            mCanvas.showPI();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
