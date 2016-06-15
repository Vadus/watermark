package org.klarblick.document.watermark.service;

import java.util.UUID;

import org.klarblick.document.model.Document;

/**
 * Ticket of a Watermark generation
 * 
 * @author DTramnitzke
 *
 */
public class Ticket {

	private String id;
	private Document document;
	
	public Ticket(String id) {
		this.id = id;
	}
	
	public Ticket(Document document) {
		this.id = UUID.randomUUID().toString() + "|" + document.getId();
		this.document = document;
	}
	
	public String getId() {
		return id;
	}

	public Document getDocument() {
		return document;
	}

	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Ticket){
			return id.equals(((Ticket)obj).getId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
