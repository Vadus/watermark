package org.klarblick.document.model;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class Watermark {

	private String content;
	private String title;
	private String author;
	private String topic;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writer().writeValueAsString(this);
		} catch (IOException e) {
		}
		
		return super.toString();
	}
}
