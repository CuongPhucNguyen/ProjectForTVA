package sample.Thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.Model.FeedItem;

import java.io.IOException;
import java.util.ArrayList;

public class ThreadNhanDan implements Runnable{
    String nhandanurl = "https://nhandan.vn/";
    private ArrayList<FeedItem> News = new ArrayList<>();
    @Override
    public void run() {
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


    public static void main(String[] args) {
        ArrayList<ThreadNhanDan> t = new ArrayList<>();
        ThreadNhanDan thread = new ThreadNhanDan();

        t.add(new ThreadNhanDan());
        Thread t1= new Thread(thread);
        t1.start();

        for(ThreadNhanDan c: t){
            System.out.println(c);
        }
        System.out.println("Trash");
    }
}
