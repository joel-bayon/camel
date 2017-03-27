package camel.direct.seda;

import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		errorHandler(deadLetterChannel("file:./deadLetter/error.txt"));
		from("direct:in")
	    .to("bean:myBean?method=run")
	    .to("seda:out");
	}
}