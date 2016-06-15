package org.klarblick.document.webservice;

import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.klarblick.document.model.Book;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class WebserviceIT {

	private String lastTicket;
	
	@Test
	public void testGetABook() {
		Response response = given().port(8082)
			.expect()
				.statusCode(200)
			.when()
				.get("/watermark.webservice/document/get")
			.then()
				.contentType(ContentType.JSON)
			.extract().response();

		String expected = "{\"type\":\"book\",\"author\":\"Aldous Huxley\",\"id\":1,\"title\":\"Brave New World\",\"topic\":\"Fiction\"}";
		String actual = response.asString();
		// System.out.println(expected);
		// System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGenerateWatermark() {

		Response response = given().port(8082)
			.contentType(ContentType.JSON)
			.body("{\"type\":\"book\",\"author\":\"Aldous Huxley\",\"id\":1,\"title\":\"Brave New World\",\"topic\":\"Fiction\"}")
			.expect()
				.statusCode(200)
			.when()
				.post("/watermark.webservice/document/watermark")
			.then()
				.contentType(ContentType.TEXT)
			.extract().response();

		String ticket = response.asString();
		System.out.println("Ticket received: " + ticket);
		
		lastTicket = ticket;
	}
	
	@Test
	public void testGetWatermark() {
		
		if(lastTicket == null){
			testGenerateWatermark();
		}
		
		Response response = given().port(8082)
			.queryParam("ticket", lastTicket)
			.expect()
				.statusCode(200)
			.when()
				.get("/watermark.webservice/watermark")
			.then()
				.contentType(ContentType.JSON)
			.extract().response();

		String expected = "{\"author\":\"Aldous Huxley\",\"content\":\"book\",\"title\":\"Brave New World\",\"topic\":\"Fiction\"}";
		String actual = response.asString();
		// System.out.println(expected);
		// System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}

	protected Book createBook(int id, String author, String title, String topic) {
		Book book = new Book();
		book.setId(new Long(id));
		book.setAuthor(author);
		book.setTitle(title);
		book.setTopic(topic);
		return book;
	}
}