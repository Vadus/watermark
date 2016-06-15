package org.klarblick.document.webservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.klarblick.document.model.Document;
import org.klarblick.document.model.Watermark;
import org.klarblick.document.service.DocumentService;
import org.klarblick.document.watermark.service.Ticket;

@Path("/watermark")
public class WatermarkResource {

	//TODO: introduce DI container
	private DocumentService documentService = new DocumentService();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Watermark getWatermark(@QueryParam("ticket") String ticketId) {

    	if(ticketId == null){
    		return null;
    	}
    	
    	Ticket ticket = new Ticket(ticketId);
    	Document document = documentService.getDocumentByTicket(ticket);
    	
    	if(document == null){
    		return null;
    	}
    	
    	return document.getWatermark();
    }
}
