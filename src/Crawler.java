import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawler {
    private ArrayList visitedLinks = new ArrayList<>();
    private int maxDepth = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire link");
        String url = scanner.nextLine();
        String seedUrl = "http://" + url;
        Crawler crawler = new Crawler();
        crawler.crawl(seedUrl, 0);
    }

    public void crawl(String url, int depth) {
        if (depth > maxDepth) {
            return;
        }

        if (visitedLinks.contains(url)) {
            return;
        }

        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println("URL:" + url);
            visitedLinks.add(url);
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String nextUrl = link.absUrl("href");
                crawl(nextUrl, depth + 1);
            }

        } catch (IOException e) {
            System.out.println("Errore nell'url" + url);
            e.printStackTrace();
        }
    }
}
