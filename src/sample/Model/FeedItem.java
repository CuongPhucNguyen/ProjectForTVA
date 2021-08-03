package sample.Model;

import java.util.List;

public class FeedItem implements Runnable{
    private String title;
    private String description;
    private String pubDate;
    private String thumbnail;
    private String link;
    private String category;
    private String name;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return limit(description, 300);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "\nTitle: " + title + "\n" + "Data: " + pubDate + "\n" + "Summary: "
                + description + "\n" + "Picture: " + thumbnail + "\n" + "Link:" + link + "\n";
    }

    public static String limit(String value, int length) {
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length);
            buf.append("...");
        }
        return buf.toString();
    }

    @Override
    public void run() {

    }
}
