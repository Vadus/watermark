package org.klarblick.document.service;

import org.junit.Assert;
import org.klarblick.document.model.Book;
import org.klarblick.document.model.Journal;
import org.klarblick.document.watermark.service.Status;
import org.klarblick.document.watermark.service.Ticket;

public abstract class AbstractDocumentServiceTest {

	protected Book createBook(int id, String author, String title, String topic) {
		Book book = new Book();
		book.setId(new Long(id));
		book.setAuthor(author);
		book.setTitle(title);
		book.setTopic(topic);
		return book;
	}
	
	protected Journal createJournal(int id, String author, String title) {
		Journal journal = new Journal();
		journal.setId(new Long(id));
		journal.setAuthor(author);
		journal.setTitle(title);
		return journal;
	}

	protected Status waitForStatus(Ticket ticket, Status statusExpected) {

		Status status = getWatermarkGenerationStatus(ticket);
		if (status != statusExpected) {
			int timeout = 5000;
			int time = 0;
			while (status != statusExpected && time < timeout) {
				time += 100;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					time = timeout;
				}
				status = getWatermarkGenerationStatus(ticket);
				if(status == statusExpected){
					break;
				}
			}
			Assert.assertEquals("Status["+status+"] not finished after " + timeout + " millis", 
					statusExpected, status);
		}
		return status;
	}
	
	protected abstract Status getWatermarkGenerationStatus(Ticket ticket);
}
