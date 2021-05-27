package com.parul.fakeNews.reconnizer;

public class NewsData {
	private String News_id;
	private String News;
	private String Accuracy;

	public String getNews_id() {
		return News_id;
	}

	public void setNews_id(String news_id) {
		News_id = news_id;
	}

	public String getNews() {
		return News;
	}

	public void setNews(String news) {
		News = news;
	}

	public String getAccuracy() {
		return Accuracy;
	}

	public void setAccuracy(String accuracy) {
		Accuracy = accuracy;
	}

	public String toString() {
		return "NewsData[News_id=" + News_id + "News=" + News + "Accuracy=" + Accuracy + "]";

	}

}
