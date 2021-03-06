package data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BiduoNovel extends NovelData{

	public List<Catalog> searchCatalog(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
    	bookName = doc.select("div#info").select("h1").text();
        Elements elements = doc.select("div#list").select("dd").select("a");
        List<Catalog> catalogs = new ArrayList<Catalog>();
        int i=1;
        for(Element el : elements){
        	String href = "https://www.biduo.cc"+el.attr("href");
        	Catalog catalog = new Catalog(el.text(), href, i++);
        	catalogs.add(catalog);
        }
        return catalogs;
	}
	
	public String searchContent(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
        String content = doc.select("div#content").html();
        content = content.replace("&nbsp;", "").replace("<br>", "").replace("\n\n", "\n");
        return content;
	}
}
