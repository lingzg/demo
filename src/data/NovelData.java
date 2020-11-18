package data;

import java.util.List;

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
        content = content.replace("&nbsp;", "").replace("<br>", "");
        return content;
	}
	
	public void saveData(String bookUrl){
		Elements catalogs = searchMenu(bookUrl);
		String sql = "insert into t_novel(book_name,book_url,title,href,content,sort) values(?,?,?,?,?,?)";
		int index=1;
		JdbcDao dao = new JdbcDao();
		for(Element el : catalogs){
			String url = "https://www.biduo.cc"+el.attr("href");
			String title = el.text();
			String content = searchContent(url);
			dao.execute(sql, bookName,bookUrl,title,url,content,index++);
		}
		dao.close();
	}
	
	public void look(long id){
		String sql = "select content from t_novel where id=?";
		JdbcDao dao = new JdbcDao();
		List<String> content =  dao.find(sql, String.class, id);
		dao.close();
		System.out.println(content.get(0));
	}
	
	public static void main(String[] args) {
		NovelData nd = new NovelData();
//		nd.saveData("https://www.biduo.cc/biquge/54_54909/");
		nd.look(369);
	}
}
