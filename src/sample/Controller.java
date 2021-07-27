package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Model.FeedItem;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import sample.WebScraper.nhandanScraper;
import sample.WebScraper.zing;


public class Controller implements Initializable {
    public String nhandanurl = "https://nhandan.vn/";
    public String zingurl = "https://zingnews.vn/";

    @FXML
    private GridPane NewsGrid;

    private List<FeedItem> itemsInController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemsInController = new ArrayList<>(dataUnique());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < 50; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Design/NewCard.fxml"));
                VBox NewsPosition = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setdata(itemsInController.get(i));
                if (column == 4) {
                    column = 0;
                    ++row;
                }
                NewsGrid.add(NewsPosition, column++, row);
                GridPane.setMargin(NewsPosition, new Insets(10, 10, 10, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FeedItem> data() {
        List<FeedItem> ls = new ArrayList<>();
        nhandanScraper.list_paper_nhandan(nhandanurl, ls);
        zing.list_zing_home(zingurl, ls);
        return ls;
    }

    public HashSet<FeedItem> dataUnique() {
        HashSet<FeedItem> NewHashSet = new HashSet<>(data());
        return NewHashSet;
    }
}
