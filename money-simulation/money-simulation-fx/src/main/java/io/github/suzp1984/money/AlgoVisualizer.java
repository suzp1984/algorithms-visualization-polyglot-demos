package io.github.suzp1984.money;

public class AlgoVisualizer {

    private AlgoCanvas mCanvas;
    private int[] mMoneys;
    private int interval;


    public AlgoVisualizer(AlgoCanvas canvas, int n, int interval) {
        mCanvas = canvas;
        mCanvas.setFocusTraversable(true);
        this.interval = interval;

        mMoneys = new int[n];
        for (int i = 0; i < n; i++) {
            mMoneys[i] = 100;
        }

        new Thread(() -> {
            run();
        }).start();
    }

    private void run() {

        while (true) {

            mCanvas.render(mMoneys);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int m = 0; m < interval; m++) {
                for (int i = 0; i < mMoneys.length; i++) {
                    if (mMoneys[i] > 0) {
                        int j = (int) (Math.random() * mMoneys.length);
                        if (i != j) {
                            mMoneys[i] -= 1;
                            mMoneys[j] += 1;
                        }
                    }
                }
            }
        }
    }
}
