package sample.WebScraper;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.Model.FeedItem;

public class zing {
    public static String list_paper_zing(String source, List<FeedItem> feedItems) {
        try {
            Document doc = Jsoup.connect("https://zingnews.vn/").timeout(5000).get();
            Elements zingTable = doc.select("article");
            for (Element header : zingTable) {
                Elements Title_selection = header.select("p.article-title");
                String title = Title_selection.select("a").text();
                System.out.println(title);
                Elements check = header.select("p.article-meta");

                String pubDate = check.select("span.article-publish span.date").text()
                        .concat(check.select("span.article-publish span.friendly-time").text());


                String link = check.select("a").attr("href");

                String description = header.select("p.article-summary").text();
                String thumbnail = header.select("img").attr("data-src");
                System.out.println(thumbnail);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String list_read_zing(String Link) {
        String HomePageZing = "https://zingnews.vn/";
        try {
            Document doc = Jsoup.connect(HomePageZing.concat(Link)).timeout(6000).get();
            Elements zingHeader = doc.select("div.page-wrapper article ");
            for (Element rows : zingHeader) {
                String Desc = rows.select("p").text();
                System.out.println(Desc);
                String image = rows.select("table tr td.pic img").attr("data-src");
                System.out.println(image);

                String header_detail = zingHeader.select("header.the-article-header p").text();
                String header_detail1 = zingHeader.select("header.the-article-header h1").text();
                String header_detail2 = zingHeader.select("header.the-article-header ul.the-article-meta li")
                        .text();
                System.out.println(header_detail);
                System.out.println(header_detail1);
                System.out.println(header_detail2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void list_zing_home(String zingUrl, List<FeedItem> ls) {
        try {
            Document doc = Jsoup.connect(zingUrl).timeout(6000).get();
            Elements zingTable = doc.select("article");
            for (Element header : zingTable) {
                FeedItem item = new FeedItem();
                String title = header.select("header p.article-title a").text();
                String pubDate = header.select("header p.article-meta span.article-publish span.friendly-time").text();
                String link = header.select("a").attr("href");
                String thumbnail = header.select("p.article-thumbnail a img").attr("data-src");
                item.setPubDate(pubDate);
                item.setThumbnail(thumbnail);
                item.setTitle(title);
                ls.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
