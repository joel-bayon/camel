package camel.http.enricher;

import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder{
	public void configure() throws Exception {
		from("jetty:http://localhost:8080/newOrder")
		.pollEnrich(simple("activemq:queue:${header.product}"), 100, null, false);
			
		
		/* for Camel 2.15 ans olders ...
		.choice()
				.when()
				.simple("${header.product} == 'widget'")
					.pollEnrich("activemq:queue:widget",100)
				.otherwise()
				.pollEnrich("activemq:queue:gadget",100)
			.end();
			*/
	}
}