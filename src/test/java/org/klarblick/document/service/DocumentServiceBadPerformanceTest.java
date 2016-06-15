package org.klarblick.document.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.klarblick.document.model.Book;
import org.klarblick.document.model.Document;
import org.klarblick.document.watermark.service.Status;
import org.klarblick.document.watermark.service.Ticket;
import org.klarblick.document.watermark.service.WatermarkService;

public class DocumentServiceBadPerformanceTest extends AbstractDocumentServiceTest {

	private DocumentService service = new DocumentService();
	
	@Before
	public void init(){
		
		service.setWatermarkGenerationService(new BadPerformanceWatermarkService());
	}
	
	@Test
	public void testGetWatermarkGenerationStatusProcessing() {
		
		Book book = createBook(1, "Aldous Huxley", "Brave New World", "Fiction");
		Ticket ticket = service.generateWatermark(book);
	
		Status status = service.getWatermarkGenerationStatus(ticket);
		
		Assert.assertEquals(Status.PROCESSING, status);
		Assert.assertNull(ticket.getDocument().getWatermark());
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
		
		Assert.assertNotNull(ticket.getDocument().getWatermark());
	}
	
	private static class BadPerformanceWatermarkService extends WatermarkService{
		
		@Override
		public void generateWatermark(Document document) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			super.generateWatermark(document);
		}
	}

	@Override
	protected Status getWatermarkGenerationStatus(Ticket ticket) {
		return service.getWatermarkGenerationStatus(ticket);
	}
}
