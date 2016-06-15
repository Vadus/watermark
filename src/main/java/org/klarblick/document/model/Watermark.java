package org.klarblick.document.model;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * <p>
 * A Watermark constsis of attributes values of a Document.
 * Usually a document can be identified by its Watermark property. 
 * This means, that the Watermark class should provide some kind of ID
 * e.g. a signature or checksum or it should act as an ID itself by 
 * overriding methods hashcode and equals.
 * </p>
 * <p>
 * However the given exercise does not imply any identification ability of a Watermark
 * </p>
 * 
 * @author DTramnitzke
 *
 */
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
		//TODO: compare performance of ObjectMapper vs. simple StringBuilder approach
		try {
			return new ObjectMapper().writer().writeValueAsString(this);
		} catch (IOException e) {
		}
		
		return super.toString();
	}
}
