package org.klarblick.document.model;

public abstract class AbstractDocument implements Document {

	private Long id;
	private String title;
	private String author;
	private Watermark watermark;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Watermark getWatermark() {
		return watermark;
	}
	public synchronized void setWatermark(Watermark watermark) {
		this.watermark = watermark;
	}
	public abstract void generateWatermark();
}
