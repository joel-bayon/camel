package camel.restlet.commande;

import org.apache.camel.Exchange;

public class LogProcessor {
	public void process(Exchange exchange) {
		System.out.println("***** headers : " + exchange.getIn().getHeaders());
		System.out.println("***** body : " + exchange.getIn().getBody());
	}
}
