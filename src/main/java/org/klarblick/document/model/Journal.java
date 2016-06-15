package org.klarblick.document.model;

public class Journal extends AbstractDocument {

	@Override
	public void generateWatermark() {
		Watermark watermark = new Watermark();
		watermark.setContent(getClass().getSimpleName().toLowerCase());
		watermark.setTitle(getTitle());
		watermark.setAuthor(getAuthor());
		setWatermark(watermark);
	}
}
