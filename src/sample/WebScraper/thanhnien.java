package sample.WebScraper;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import sample.Model.FeedItem;
public class thanhnien {
    public static void list_thanh_nien(String URL, List<FeedItem> News)
    {
        try{
            Document doc = Jsoup.connect(URL).timeout(5000).get();
            Elements TNTable = doc.select("article");

            for(Element header : TNTable){
                FeedItem item = new FeedItem();
                String title = header.select("a").attr("title");
                String link = header.select("a").attr("href");
                String thumbnail = header.select("img").attr("data-src");
                String pubDate = header.select("time.timebox").text();
                item.setTitle(title);
                item.setThumbnail(thumbnail);
                item.setPubDate(pubDate);
                News.add(item);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
