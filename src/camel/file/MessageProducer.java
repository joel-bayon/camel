package camel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class MessageProducer {

	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		ProducerTemplate template =  camelContext.createProducerTemplate();
		
		// send to a specific queue
		template.sendBody("activemq:MyQueue", "<hello>world!</hello>");

	}

}
