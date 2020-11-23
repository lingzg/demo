package data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BooktxtNovel extends NovelData {

    public List<Catalog> searchCatalog(String url){
        String res = HttpUtil.sendGet(url);
        Document doc = Jsoup.parse(res);
        bookName = doc.select("div#info h1").text();
        Elements elements = doc.select("div#list dl dd:gt(6) a");
        List<Catalog> catalogs = new ArrayList<Catalog>();
        int i=1;
        for(Element el : elements){
            String href = url+el.attr("href");
            Catalog catalog = new Catalog(el.text(), href, i++);
            catalogs.add(catalog);
        }
        return catalogs;
    }
    
    public String searchContent(String url){
        String res = HttpUtil.sendGet(url);
        Document doc = Jsoup.parse(res);
        Elements elements = doc.select("div#content");
        String content = elements.html();
        content = content.replace("&nbsp;", "").replace("<br>", "");
        content = content.substring(0,content.lastIndexOf("<script>chaptererror();</script>")-"<script>chaptererror();</script>".length());
        return content;
    }
}
