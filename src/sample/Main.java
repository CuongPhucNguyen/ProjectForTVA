package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Objects;

import javafx.stage.StageStyle;

public class Main extends Application {
    double xOffset, yOffset;
    Parent root;


    @Override
    public void start(Stage primaryStage) {

        try {
//            //chinh lai de phu hop
//            int sceneWidth = 0;
//            int sceneHeight = 0;
//            if (screenWidth <= 800 && screenHeight <= 600) {
//                sceneWidth = 800;
//                sceneHeight = 600;
//            } else if (screenWidth <= 1280 && screenHeight <= 768) {
//                sceneWidth = 1000;
//                sceneHeight = 600;
//            } else if (screenWidth <= 1920 && screenHeight <= 1080) {
//                sceneWidth = 1720;
//                sceneHeight = 980;
//            }

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design/MainDesign.fxml")));
            root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Design/Css/NewSpaper.css")).toExternalForm());
            Scene scene = new Scene(root);
            primaryStage.setTitle("NewSpaper");
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(new Image("sample/public/icon/icon.png"));
            primaryStage.show();
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
