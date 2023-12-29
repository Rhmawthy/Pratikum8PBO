package DataSpasial_N06;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class data extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);
        Random random = new Random();

        List<Circle> circles = new ArrayList<>();
        scene.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            for (int i = 0; i < 10; i++) {
                Circle circle = new Circle(x, y, 3, Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                circles.add(circle);
                root.getChildren().add(circle);

                double angle = Math.random() * 360;
                RotateTransition rotate = new RotateTransition(Duration.seconds(2), circle);
                rotate.setByAngle(angle);
                rotate.setCycleCount(1);
                rotate.setOnFinished(e -> {
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(2), circle);
                    double offsetX = Math.cos(Math.toRadians(angle)) * 100; // Ganti nilai 100 sesuai dengan jarak yang Anda inginkan
                    double offsetY = Math.sin(Math.toRadians(angle)) * 100; // Ganti nilai 100 sesuai dengan jarak yang Anda inginkan
                    transition.setByX(offsetX);
                    transition.setByY(offsetY);
                    transition.setCycleCount(TranslateTransition.INDEFINITE);
                    transition.setAutoReverse(true);
                    transition.setInterpolator(Interpolator.LINEAR);
                    transition.play();
                });
                rotate.play();
            }
        });

        primaryStage.setTitle("Floating Points Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
