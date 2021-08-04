package sample.WebScraper;

import java.io.IOException;
import sample.Model.FeedItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HeaderScraper {
    public static String ThanhNien(String URL, ArrayList<FeedItem> ThanhNienNews)
    {
        try{
            Document doc = Jsoup.connect(URL).timeout(6000).get();
            Elements TNTable = doc.select("article");

            for(Element header : TNTable.select("article.story")){
                String title = header.select("a").attr("title");
                System.out.println(title);
                String link = header.select("a").attr("href");
                System.out.println(link);
                String thumbnail = header.select("img").attr("src");
                if(thumbnail == "")
                {
                    thumbnail = header.select("img").attr("data-src");
                }
                System.out.println(thumbnail);

                String description = header.select("div.summary").text();
                System.out.println(description);
                String pubDate = header.select("time.timebox").text();
                DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
                Date date = new Date();
                try {
                    date = formatter.parse(pubDate);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println(pubDate);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
