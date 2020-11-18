package data;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dao.JdbcDao;

public class NovelData {

	public List<String> searchMenu(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
        Elements list = doc.select("div#list");
        Elements a = list.select("dd").select("a");
//        System.out.println(a);
        List<String> urls = a.eachAttr("href");
//        System.out.println(urls.indexOf("/biquge/54_54909/c23693675.html"));
        return urls;
	}
	
	public String[] searchContent(String url){
		String res = HttpUtil.sendGet(url);
    	Document doc = Jsoup.parse(res);
    	String title = doc.select("div.bookname").select("h1").text();
        String content = doc.select("div#content").html();
        content = content.replace("&nbsp;", "").replace("<br>", "");
        String[] result = {title, content};
        return result;
	}
	
	public void saveData(){
		String bookName = "明尊";
		String bookUrl = "https://www.biduo.cc/biquge/54_54909/";
		List<String> urls = searchMenu(bookUrl);
		String sql = "insert into t_novel(book_name,book_url,title,href,content,sort) values(?,?,?,?,?,?)";
		int index=1;
		JdbcDao dao = new JdbcDao();
		for(String href : urls){
			String url = "https://www.biduo.cc"+href;
			String[] arr = searchContent(url);
			dao.execute(sql, bookName,bookUrl,arr[0],url,arr[1],index++);
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
//		nd.saveData();
		nd.look(308);
	}
}
