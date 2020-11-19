package data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MeegoqNovel extends NovelData {

	public List<Catalog> searchCatalog(String url){
		String res = HttpUtil.sendGet(url, "utf-8");
    	Document doc = Jsoup.parse(res);
    	bookName = doc.select("header.line h1").text().replace("最新章节", "");
        Elements elements = doc.select("ul.mulu li:gt(11) a");
        List<Catalog> catalogs = new ArrayList<Catalog>();
        int i=1;
        for(Element el : elements){
        	String href = "https:"+el.attr("href");
        	Catalog catalog = new Catalog(el.text(), href, i++);
        	catalogs.add(catalog);
        }
        return catalogs;
	}
	
	public String searchContent(String url){
		String res = HttpUtil.sendGet(url, "utf-8");
    	Document doc = Jsoup.parse(res);
        String content = doc.select("div#content").text();
        content = content.replace(" 　　", "\n");
        return content;
	}
}
