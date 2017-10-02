package io.github.suzp1984.fxdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello world");

        Group root = new Group();
        Scene scene = new Scene(root, Color.LIGHTGRAY);

        final AlgoCanvas canvas = new AlgoCanvas(800, 800);
        root.getChildren().add(canvas);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        AlgoVisualizer visualizer = new AlgoVisualizer(canvas, 10);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
