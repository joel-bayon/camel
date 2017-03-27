package camel.gateway.jms;


import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ByeImpl {
	public String bye(String name, Exchange exchange) {
		System.out.println(exchange.getIn().getHeaders());
		return "Hello to " + name.toUpperCase()+ " from JMS request-reply !";
	}
}
