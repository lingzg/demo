package data;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.JdbcDao;

public class NovelData {

	private String bookName;
	
	public Elements searchMenu(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
    	bookName = doc.select("div#info").select("h1").text();
        Elements catalogs = doc.select("div#list").select("dd").select("a");
        return catalogs;
	}
	
	public String searchContent(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
        String content = doc.select("div#content").html();
        content = content.replace("&nbsp;", "").replace("<br>", "").replace("\n\n", "\n");
        return content;
	}
	
	public void saveData(String bookUrl){
		Elements catalogs = searchMenu(bookUrl);
		String sql = "insert into t_novel(book_name,book_url,title,href,content,sort) values(?,?,?,?,?,?)";
		int index=0;
		JdbcDao dao = new JdbcDao();
		List<String> hrefs = dao.find("select href from t_novel where book_url=?", String.class, bookUrl);
		for(Element el : catalogs){
			index++;
			String url = "https://www.biduo.cc"+el.attr("href");
			if(hrefs.contains(url)){
				continue;
			}
			String title = el.text();
			String content = searchContent(url);
			dao.execute(sql, bookName,bookUrl,title,url,content,index);
		}
		dao.close();
	}
	
	public void look(long id){
		String sql = "select title,content from t_novel where id=?";
		JdbcDao dao = new JdbcDao();
		Map<String, Object> novel =  dao.query(sql, id).get(0);
		dao.close();
		System.out.println(novel.get("title"));
		System.out.println(novel.get("content").toString().replaceAll("。", "。\n").replace("！", "！\n").replace("？", "？\n").replaceAll("\n\n", "\n"));
	}
	
	public static void main(String[] args) {
		NovelData nd = new NovelData();
//		nd.saveData("https://www.biduo.cc/biquge/54_54909/");
		nd.look(447);
	}
}
