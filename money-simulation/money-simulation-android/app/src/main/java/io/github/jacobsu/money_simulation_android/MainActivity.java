package io.github.jacobsu.money_simulation_android;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    private MoneySimulationView moneyView;
    private ValueAnimator valueAnimator;
    private int[] moneys;
    private int n = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyView = (MoneySimulationView) findViewById(R.id.money);

        moneys = new int[n];
        for (int i = 0; i < n; i++) {
            moneys[i] = 200;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        valueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                moneyView.render(moneys);

                for (int m = 0; m < 40; m++) {
                    for (int i = 0; i < moneys.length; i++) {
                        if (moneys[i] > 0) {
                            int j = (int) (Math.random() * moneys.length);

                            if (i != j) {
                                moneys[i] -= 1;
                                moneys[j] += 1;
                            }
                        }
                    }
                }
            }
        });

        valueAnimator.start();
    }

    @Override
    public void onStop() {

        valueAnimator.cancel();
        valueAnimator = null;

        super.onStop();
    }
}
