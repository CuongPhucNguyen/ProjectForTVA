package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import sample.Model.FeedItem;

public class CardController {
    @FXML
    private ImageView thumbnail;

    @FXML
    private Label title;

    @FXML
    private Label pubDate;

    public void setdata(FeedItem item) {
        Rectangle clip = new Rectangle(
                thumbnail.getFitWidth(), thumbnail.getFitHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        thumbnail.setClip(clip);
        thumbnail.setEffect(new DropShadow(20, Color.BLACK));
        String thumbnailUrl = item.getThumbnail();
        Image image;
        //catch error by unsupported image url
        if (!thumbnailUrl.equals("")) {
            image = new Image(thumbnailUrl);
        } else {
            image = new Image("sample/public/404.jpg");
        }
        thumbnail.setImage(image);
        if (!item.getTitle().equals("")) {
            title.setText(item.getTitle());
        } else {
            title.setText("News don't have title");
        }
        if (!item.getPubDate().equals("")) {
            pubDate.setText(item.getPubDate());
        } else {
            pubDate.setText("News don't have time");
        }
    }
}
