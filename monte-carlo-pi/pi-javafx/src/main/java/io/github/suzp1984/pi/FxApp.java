package io.github.suzp1984.pi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Monte Carlo - PI simulation");

        Group root = new Group();
        Scene scene = new Scene(root, Color.LIGHTGREY);

        final AlgoCanvas canvas = new AlgoCanvas(800);
        root.getChildren().add(canvas);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        AlgoVisualizer visualizer = new AlgoVisualizer(canvas, 100);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
