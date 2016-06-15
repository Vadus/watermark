package org.klarblick.document.webservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.klarblick.document.model.Book;
import org.klarblick.document.service.DocumentService;
import org.klarblick.document.watermark.service.Ticket;

@Path("/document")
public class DocumentResource {

	//TODO: introduce DI container
	private DocumentService documentService = new DocumentService();
	
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook() {

    	Book book = new Book();
    	book.setId(1L);
    	book.setAuthor("Aldous Huxley");
    	book.setTitle("Brave New World");
    	book.setTopic("Fiction");
    	
    	return book;
    }
    
    @POST
    @Path("/watermark")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String generateWatermark(Book book){
    	
    	Ticket ticket = documentService.generateWatermark(book);
    	
		return ticket.getId();
    }
}