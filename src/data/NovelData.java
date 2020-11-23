package data;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import dao.JdbcDao;

public abstract class NovelData {

	protected String bookName;
	
	public abstract List<Catalog> searchCatalog(String url);
	
	public abstract String searchContent(String url);
	
	public void saveData(String bookUrl){
		List<Catalog> catalogs = searchCatalog(bookUrl);
		String sql = "insert into t_novel(book_name,book_url,title,href,content,sort) values(?,?,?,?,?,?)";
		JdbcDao dao = new JdbcDao();
		List<String> hrefs = dao.find("select href from t_novel where book_url=?", String.class, bookUrl);
		Iterator<Catalog> it = catalogs.stream().filter(x -> !hrefs.contains(x.getUrl())).iterator();
		while(it.hasNext()){
			Catalog catalog = it.next();
			String url = catalog.getUrl();
			String content = searchContent(url);
			dao.execute(sql, bookName,bookUrl,catalog.getTitle(),url,content,catalog.getIndex());
			System.out.println("save "+catalog.getTitle());
		}
		dao.close();
	}
	
	public void saveTimer(String bookUrl){
		List<Catalog> catalogs = searchCatalog(bookUrl);
		String sql = "insert into t_novel(book_name,book_url,title,href,content,sort) values(?,?,?,?,?,?)";
		JdbcDao dao = new JdbcDao();
		List<String> hrefs = dao.find("select href from t_novel where book_url=?", String.class, bookUrl);
		Iterator<Catalog> it = catalogs.stream().filter(x -> !hrefs.contains(x.getUrl())).iterator();
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				if(it.hasNext()){
					Catalog catalog = it.next();
					String url = catalog.getUrl();
					String content = searchContent(url);
					dao.execute(sql, bookName,bookUrl,catalog.getTitle(),url,content,catalog.getIndex());
					System.out.println("save "+catalog.getTitle());
				}else{
					dao.close();
					timer.cancel();
				}
			}
		}, 1000, 1000*3);
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
//		NovelData nd = new BiduoNovel();
//		nd.saveData("https://www.biduo.cc/biquge/54_54909/");
//		NovelData nd = new MeegoqNovel();
//		nd.saveTimer("https://www.meegoq.com/book133890.html");
	  NovelData nd = new BooktxtNovel();
//	  nd.saveData("https://www.booktxt.net/0_697/");
		nd.look(1660);
	}
}
