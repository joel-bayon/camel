package camel.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeFailedEvent;
import org.apache.camel.management.event.RouteStartedEvent;
import org.apache.camel.management.event.RouteStoppedEvent;
import org.apache.camel.support.EventNotifierSupport;


public class MyEventNotifier extends EventNotifierSupport {
	CamelContext camelContext = null;
	
	Date startRouteTime;
	Date stopRouteTime;
	long createdEventTime;
	long completedEventTime;
	int messageCompleteCount;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
		camelContext.getManagementStrategy().addEventNotifier(this);
	}
	
	public MyEventNotifier() {
		System.out.println("*** create MyEventNotifier ***");
	}
	
	public void monitor(Exchange exchange) {
		System.out.println("*** check same CamelContext  ***" + (camelContext == exchange.getContext()));
		System.out.println("*** EventNotifiers ***"+ camelContext.getManagementStrategy().getEventNotifiers());
	}

	@Override
	public boolean isEnabled(EventObject event) {
		return true;
	}

	@Override
	public void notify(EventObject event) throws Exception {
		Date current = new Date();
		if(event instanceof RouteStartedEvent) {
			System.out.println("*** notify RouteStartedEvent DateTime="+ sdf.format(current));
			startRouteTime = current;
			return;
		}
		if(event instanceof RouteStoppedEvent) {
			stopRouteTime = current;
			System.out.println("*** notify RouteStoppedEvent DateTime="+ sdf.format(current)
				+ " lifetime=" + (stopRouteTime.getTime() - startRouteTime.getTime()) + " ms"
				+ " - nb messages performed=" + messageCompleteCount);
			
			return;
		}
		if(event instanceof ExchangeCreatedEvent)  {
			System.out.println("*** notify ExchangeCreatedEvent DateTime="+ sdf.format(current) 
				+  " delay from previous message=" + (createdEventTime ==0?0:current.getTime()-createdEventTime) + " ms");
			createdEventTime = current.getTime();
			return;		
		}
		if(event instanceof ExchangeCompletedEvent)  {
			System.out.println("*** notify ExchangeCompletedEvent DateTime="+ sdf.format(current) 
				+  " message lifetime=" + (current.getTime()-createdEventTime) + " ms");
			completedEventTime = current.getTime();
			messageCompleteCount++;
			return;		
		} 
		if(event instanceof ExchangeFailedEvent)  {
			System.out.println("*** notify ExchangeFailedEvent DateTime="+ sdf.format(current) 
				+  " content=" + event);
			ExchangeFailedEvent fail  = (ExchangeFailedEvent) event;
			Exception exception = fail.getExchange().getProperty(Exchange.EXCEPTION_CAUGHT,Exception.class);
	        System.out.println("*** Exception = " + exception.getClass().getName() + " - message : "+exception.getMessage());
			return;		
		}
			
		System.out.println("*** notify ??? = " + event.getClass().getSimpleName());
		
	}
}

