package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Model.FeedItem;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import sample.WebScraper.nhandanScraper;
import sample.WebScraper.zing;
import sample.WebScraper.thanhnien;

public class Controller implements Initializable {


    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

    private Stage stage;
    private Scene scene;

    public String nhandanurl = "https://nhandan.vn/";
    public String zingurl = "https://zingnews.vn/";
    public String thanhnienurl = "https://thanhnien.vn/";

    @FXML
    public GridPane NewsGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<FeedItem> itemsInController = new ArrayList<>(dataUnique());
        removeNull(itemsInController);

            int column = 0;
            int DinamixGridPane = 0;
            if (screenWidth <= 800 && screenHeight <= 600) {
                DinamixGridPane = 2;
            } else if (screenWidth <= 1280 && screenHeight <= 768) {
                DinamixGridPane = 3;
            }
            else if (screenWidth <= 1536 && screenHeight <= 864) {
                DinamixGridPane = 4;
            }else if (screenWidth <= 1920 && screenHeight <= 1080) {
                DinamixGridPane = 5;
            }

            System.out.println("Chiều rộng " + screenWidth + " chiều dài " + screenHeight);
        int row = 1;
        try {
            for (int i = 0; i < 10; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Design/NewCard.fxml"));
                VBox NewsPosition = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setdata(itemsInController.get(i));
                if (column == DinamixGridPane) {
                    column = 0;
                    ++row;
                }
                NewsGrid.add(NewsPosition, column++, row);
                GridPane.setMargin(NewsPosition,
                        new Insets(20, 20, 10, 25));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Collector all data
    private List<FeedItem> data() {
        List<FeedItem> ls = new ArrayList<>();
        thanhnien.list_thanh_nien(thanhnienurl, ls);
        nhandanScraper.list_paper_nhandan(nhandanurl, ls);
        zing.list_zing_home(zingurl, ls);
        return ls;
    }
    //first filter(avoid Repeat item)
    public HashSet<FeedItem> dataUnique() {
        return new HashSet<>(data());
    }

    //function remove all unsupported data
    private static void removeNull(List<FeedItem> filter){
        filter.removeIf(item -> item.getTitle().equals("") || item.getPubDate().equals("") || item.getThumbnail().equals(""));
    }


    public void Thethao(javafx.event.ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design/Sport.fxml")));
        stage =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void MainDesign(javafx.event.ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Design/MainDesign.fxml")));
        stage =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
