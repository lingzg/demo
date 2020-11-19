package data;

public class Catalog {

	private String title;
	
	private String url;
	
	private int index;

	public Catalog(String title, String url, int index) {
		this.title = title;
		this.url = url;
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Catalog [title=" + title + ", url=" + url + ", index=" + index + "]";
	}

}
