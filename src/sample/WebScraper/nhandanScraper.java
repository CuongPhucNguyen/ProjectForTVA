package sample.WebScraper;

import sample.Model.FeedItem;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class nhandanScraper {
    public static void list_paper_nhandan(String nhandanurl, List<FeedItem> News) {
        try {
            Document doc = Jsoup.connect(nhandanurl).timeout(5000).get();
            Elements nhandantable = doc.select("article");
            for (Element header : nhandantable.select("div")) {
                FeedItem item = new FeedItem();
                String title = header.select("div.box-img a").attr("title");
                String link = header.select("div.box-img a").attr("href");
                String thumbnail = header.select("div.box-img a img").attr("data-src");
                String pubDate = nhandantable.select("div.box-meta-small").text();
                item.setTitle(title);
                item.setPubDate(pubDate);
                item.setThumbnail(thumbnail);
                News.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
