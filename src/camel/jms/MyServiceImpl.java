package camel.jms;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl {
	public String run(@Body String text,  Exchange exchange) {
		System.out.println("**** headers : " + exchange.getIn().getHeaders());
		//return "Hello " + text.toUpperCase() + " from Camel route !";
		return "Hello " + exchange.getIn().getBody(String.class).toUpperCase() + " from Camel route !";
	}

}