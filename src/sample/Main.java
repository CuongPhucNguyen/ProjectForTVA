package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

import javafx.stage.StageStyle;

public class Main extends Application {
    double xOffset, yOffset;
    Parent root;

    @Override
    public void start(Stage primaryStage) {
        try {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design/MainDesign.fxml")));
            root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Design/Css/NewSpaper.css")).toExternalForm());
            Scene scene = new Scene(root);
            primaryStage.setTitle("NewSpaper");
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("sample/public/icon/icon.png"));
            primaryStage.show();
            primaryStage.setMaximized(true);
            scene.setFill(Color.TRANSPARENT);
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
