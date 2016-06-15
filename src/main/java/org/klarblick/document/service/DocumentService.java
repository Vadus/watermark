package org.klarblick.document.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.klarblick.document.model.Document;
import org.klarblick.document.persistence.DocumentCache;
import org.klarblick.document.watermark.service.Status;
import org.klarblick.document.watermark.service.Ticket;
import org.klarblick.document.watermark.service.WatermarkService;

/**
 * Document service for generating Watermarks for documents.
 * 
 * @author DTramnitzke
 *
 */
public class DocumentService {

	//TODO: use a proper persistence and cache layer ( e.g. JPA implementation )
	DocumentCache documentCache = DocumentCache.instance();
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	//TODO: introduce DI container
	private WatermarkService watermarkGenerationService = new WatermarkService();

	public Ticket generateWatermark(Document document){
		
		Ticket ticket = new Ticket(document);
		
		documentCache.put(ticket, document);
		
		executorService.submit(() -> {
			watermarkGenerationService.generateWatermark(document);
		});
		
		return ticket;
	}
	
	public Status getWatermarkGenerationStatus(Ticket ticket){
		
		Document document = ticket.getDocument();
		if(document == null){
			document = documentCache.getDocument(ticket);
		}
		if(document == null){
			return Status.UNKNOWN_TICKET;
		}
		else if (document.getWatermark() == null){
			return Status.PROCESSING;
		}
		else{
			return Status.FINISHED;
		}
	}
	
	public Document getDocumentByTicket(Ticket ticket){
		
		return documentCache.getDocument(ticket);
	}

	/**
	 * @deprecated TODO introduce DI container
	 * @param watermarkGenerationService
	 */
	void setWatermarkGenerationService(WatermarkService watermarkGenerationService) {
		this.watermarkGenerationService = watermarkGenerationService;
	}
}
