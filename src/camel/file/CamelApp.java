package camel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.RouteContext;

public class CamelApp {
	
	public static void main(String[] args) throws Exception {
		
		
		//final long durationMs = extractDurationMsFromCommandLineArgs(arguments);
		CamelContext camelContext = new DefaultCamelContext();
		  
	      camelContext.addRoutes(
	         new RouteBuilder() {
	            @Override
	            public void configure() throws Exception
	            {
	               from("file:./src/camel/file/in?noop=false&fileName=client.xml")
	               .convertBodyTo(String.class)
	               .setHeader("clientName").xpath("/client/nom", String.class)
	               .setHeader("CamelFileName").simple("${header.clientName}.xml")
	               .setBody().spel("#{request.body.replace(request.headers['clientName'], request.headers['clientName'].toUpperCase())}")
	               .to("file:./src/camel/file/out");
	            }
	         });
	     MyEventNotifier notifier  = new MyEventNotifier();
	     notifier.setCamelContext(camelContext);
	      camelContext.start();
	     
	      System.out.println("CamelContext="+ camelContext);
	     /* Thread.sleep(1000);
	      for(Route r : camelContext.getRoutes()) {
	    	  RouteContext rc = r.getRouteContext();
	    	  System.out.println("message history = "+ rc.getRoute().getMessageHistory());
	      }*/
	      Thread.sleep(20000);
	      camelContext.stop();
		  camelContext.stop();
		  
		
	}

}
