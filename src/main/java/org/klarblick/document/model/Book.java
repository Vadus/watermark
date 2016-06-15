package org.klarblick.document.model;

public class Book extends AbstractDocument {

	private String topic;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public void generateWatermark() {
		Watermark watermark = new Watermark();
		watermark.setContent(getClass().getSimpleName().toLowerCase());
		watermark.setTitle(getTitle());
		watermark.setAuthor(getAuthor());
		watermark.setTopic(getTopic());
		setWatermark(watermark);
	}
}
