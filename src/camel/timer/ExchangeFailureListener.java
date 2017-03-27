package camel.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeFailedEvent;
import org.apache.camel.management.event.ExchangeFailureHandledEvent;
import org.apache.camel.management.event.RouteStartedEvent;
import org.apache.camel.management.event.RouteStoppedEvent;
import org.apache.camel.support.EventNotifierSupport;


public class ExchangeFailureListener extends EventNotifierSupport {
	
	public void setCamelContext(CamelContext camelContext) {
		camelContext.getManagementStrategy().addEventNotifier(this);
	}

	@Override
	public boolean isEnabled(EventObject event) {
		return event instanceof ExchangeFailureHandledEvent;
	}

	@Override
	public void notify(EventObject event) throws Exception {
		System.out.println("ExchangeFailureListener.notify");
		ExchangeFailureHandledEvent failure = (ExchangeFailureHandledEvent)event;
		ProducerTemplate producer = failure.getExchange().getContext().createProducerTemplate();
		Exception exception = failure.getExchange().getProperty(Exchange.EXCEPTION_CAUGHT,Exception.class);
		System.out.println("*** exception="+exception);
		producer.sendBody("seda:sendErrorByMail", 
				exception.toString());
	}
}

