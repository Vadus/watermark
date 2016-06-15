# watermark

This REST webservice creates watermarks for documents, such as books and journals. 

run with: mvn clean package verify

The web application is being build, deployed and run on an internal tomcat instance.
During that run, integration tests are being executed against the REST interface.

## Exercise

### Watermark-Test

A global publishing company that publishes books and journals wants to develop a service to
watermark their documents. Book publications include topics in business, science and media. Journals don’t include any specific topics. A document (books, journals) has a title, author and a watermark property. An empty watermark property indicates that the document has not been watermarked yet.

The watermark service has to be asynchronous. For a given content document the service should return a ticket, which can be used to poll the status of processing. If the watermarking is finished the document can be retrieved with the ticket. The watermark of a book or a journal is identified by setting the watermark property of the object. For a book the watermark includes the properties content, title, author and topic. The journal watermark includes the content, title and author.

Examples for watermarks:
```
{content:”book”, title:”The Dark Code”, author:”Bruce Wayne”, topic:”Science”}

{content:”book”, title:”How to make money”, author:”Dr. Evil”, topic:”Business”}

{content:”journal”, title:”Journal of human flight routes”, author:”Clark Kent”}
```
a) Create an appropriate object-oriented model for the problem.
b) Implement the Watermark-Service, meeting the above conditions.
c) Provide Unit-Tests to ensure the functionality of the service.

## Class Diagram

![Class_Diagram.png](https://raw.githubusercontent.com/Vadus/watermark/master/src/doc/Class_Diagram.png "Class_Diagram.png")

## Thoughts and remarks on solution

The current solution emphasizes the asynchronous creation of a Watermark for a document. The persistence/caching layer for Documents, Watermarks and Tickets is out of scope and exists in a very simple manner, which only supports this specific use case.

The solution provides a REST webservice and an integration test, which shows the client/server communication. Introducing integration tests early has a great impact on how to design and implement the server side business logic. In this case the need for persistence of Tickets and Documents was made clear through the integration test. In this use case multiple separate client requests (threads) access the application. One request provides a Document and triggers the Watermark generation and another request reads that document again after some time. Therefore a persistence layer of documents became mandatory. Having only UnitTests and no early integration test it is more difficult to see this design relation.