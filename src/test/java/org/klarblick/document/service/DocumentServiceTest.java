package org.klarblick.document.service;

import org.junit.Assert;
import org.junit.Test;
import org.klarblick.document.model.Book;
import org.klarblick.document.model.Document;
import org.klarblick.document.model.Journal;
import org.klarblick.document.model.Watermark;
import org.klarblick.document.watermark.service.Status;
import org.klarblick.document.watermark.service.Ticket;

public class DocumentServiceTest extends AbstractDocumentServiceTest {

	private DocumentService service = new DocumentService();

	@Test
	public void testGetWatermarkGenerationStatusUnkownTicket() {
	
		Ticket unkownTicket = new Ticket("276some4389id1289");
		
		Status status = service.getWatermarkGenerationStatus(unkownTicket);
		
		Assert.assertEquals(Status.UNKNOWN_TICKET, status);
	}

	@Test
	public void testGetWatermarkGenerationStatusFinished() {
	
		Book book = createBook(1, "Aldous Huxley", "Brave New World", "Fiction");
		book.setWatermark(new Watermark());
		Ticket ticket = new Ticket(book);		
	
		Status status = service.getWatermarkGenerationStatus(ticket);
		
		Assert.assertEquals(Status.FINISHED, status);
	}
	
	@Test
	public void testGenerateWatermarkForBook() {

		Book book = createBook(1, "Aldous Huxley", "Brave New World", "Fiction");

		Ticket ticket = service.generateWatermark(book);
		Assert.assertNotNull(ticket);
		Assert.assertNotNull(ticket.getId());
		Assert.assertNotNull(ticket.getDocument());

		Status status = waitForStatus(ticket, Status.FINISHED);
		Assert.assertEquals(Status.FINISHED, status);
		
		Watermark watermark = ticket.getDocument().getWatermark();
		Assert.assertNotNull(watermark);
		
		Assert.assertEquals("{\"content\":\"book\",\"title\":\"Brave New World\",\"author\":\"Aldous Huxley\",\"topic\":\"Fiction\"}", watermark.toString());
	}
	
	@Test
	public void testGenerateWatermarkForJournal() {

		Journal journal = createJournal(1, "Clark Kent", "Journal of human flight routes");

		Ticket ticket = service.generateWatermark(journal);
		Assert.assertNotNull(ticket);
		Assert.assertNotNull(ticket.getId());
		Assert.assertNotNull(ticket.getDocument());

		Status status = waitForStatus(ticket, Status.FINISHED);
		Assert.assertEquals(Status.FINISHED, status);
		
		Assert.assertNotNull(ticket.getDocument().getWatermark());
	}
	
	@Test
	public void testGetDocumentByTicket(){
		
		Book book = createBook(1, "Aldous Huxley", "Brave New World", "Fiction");

		Ticket ticket = service.generateWatermark(book);

		waitForStatus(ticket, Status.FINISHED);
		
		Document document = service.getDocumentByTicket(ticket);
		Assert.assertNotNull(document);
		Assert.assertTrue(document instanceof Book);
		
		Watermark watermark = ticket.getDocument().getWatermark();
		Assert.assertNotNull(watermark);
		
		Assert.assertEquals("{\"content\":\"book\",\"title\":\"Brave New World\",\"author\":\"Aldous Huxley\",\"topic\":\"Fiction\"}", watermark.toString());
	}
	
	@Test
	public void testGetDocumentByUnkownTicket(){
		
		Ticket unkownTicket = new Ticket("276some4389id1289");

		Document document = service.getDocumentByTicket(unkownTicket);
		Assert.assertNull(document);
	}
	
	@Override
	protected Status getWatermarkGenerationStatus(Ticket ticket) {
		return service.getWatermarkGenerationStatus(ticket);
	}
}
