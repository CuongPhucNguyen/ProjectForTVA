package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design/MainDesign.fxml")));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Design/Css/NewSpaper.css")).toExternalForm());
        Scene scene = new Scene(root);
        primaryStage.setTitle("NewSpaper");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("sample/public/icon/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
