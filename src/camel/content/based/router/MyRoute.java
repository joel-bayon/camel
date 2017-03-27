package camel.content.based.router;

import org.apache.camel.Endpoint;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder{

	public void configure() throws Exception {
		Endpoint newOrder = endpoint("activemq:queue:newOrder");
		Predicate isWidget = xpath("/order/product = 'widget'");
		Endpoint widget = endpoint("activemq:queue:widget");
		Endpoint gadget = endpoint("activemq:queue:gadget");
		
		from(newOrder)
			.choice()
				.when(isWidget).to(widget)
				.otherwise().to(gadget)
			.end();
	}

}