package camel.gateway.jms;


import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class HelloImpl  {
	public String hello(String name, Exchange exchange) {
		System.out.println(exchange.getIn().getHeaders());
		return "Hello to " + name.toUpperCase()+ " from JMS request-reply !";
	}
}
