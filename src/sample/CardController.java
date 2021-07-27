package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.Model.FeedItem;

public class CardController {

    @FXML
    private ImageView thumbnail;

    @FXML
    private Label title;

    @FXML
    private Label pubDate;

    @FXML
    private Label description;


    public void setdata(FeedItem item) {
        String thumbnailUrl = item.getThumbnail();
        if (!thumbnailUrl.equals("")) {
            Image image = new Image(thumbnailUrl);
            thumbnail.setImage(image);
        } else {
            Image image = new Image("sample/public/404.jpg");
            thumbnail.setImage(image);
        }
        if (!item.getTitle().equals("")) {
            title.setText(item.getTitle());
        } else {
            title.setText("Tin tức không có title");
        }
        if (!item.getPubDate().equals("")) {
            pubDate.setText(item.getPubDate());
        } else {
            pubDate.setText("H bị lỗi");
        }
        if (!item.getDescription().equals("")) {
            description.setText(item.getDescription());
        } else {
            description.setText("Tin bị lỗi");
        }
    }
}
