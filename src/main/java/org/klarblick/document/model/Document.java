package org.klarblick.document.model;

public interface Document {

	public Watermark getWatermark();
	
	public Long getId();
	
	public void generateWatermark();
}
