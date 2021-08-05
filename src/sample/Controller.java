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
import sample.WebScraper.thanhnien;

public class Controller implements Initializable {
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
        int row = 1;
        try {
            for (int i = 0; i < 100; i++) {
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
}
