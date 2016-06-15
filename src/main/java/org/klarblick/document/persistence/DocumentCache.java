package org.klarblick.document.persistence;

import java.util.HashMap;
import java.util.Map;

import org.klarblick.document.model.Document;
import org.klarblick.document.watermark.service.Ticket;

/**
 * TODO: use a proper cache solution ( e.g. EHCache ) 
 * 
 * @author DTramnitzke
 *
 */
public class DocumentCache {

	private Map<Ticket, Document> documentCache = new HashMap<>();
	
	private static DocumentCache instance;
	
	private DocumentCache() {
	}
	
	public static DocumentCache instance(){
		if(instance == null){
			instance = new DocumentCache();
		}
		return instance;
	}
	
	public void put(Ticket ticket, Document document){
		
		documentCache.put(ticket, document);
	}
	
	public Document getDocument(Ticket ticket){
		return documentCache.get(ticket);
	}
}
