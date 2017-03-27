package camel.timer;

import org.apache.camel.Exchange;

public class ErrorBodyBean {
	
	public Exception caughtExceptionToBody(Exchange exchange) {
		Exception caught = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		if(caught != null)
			return caught;
		Exception handled = exchange.getProperty(Exchange.EXCEPTION_HANDLED, Exception.class);
		if(handled != null)
			return handled;
		return null;
	}

}
