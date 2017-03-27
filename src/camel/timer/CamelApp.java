package camel.timer;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.RouteContext;

import camel.direct.seda.MyService;

public class CamelApp {
	
	public static void main(String[] args) throws Exception {
		
		
		//final long durationMs = extractDurationMsFromCommandLineArgs(arguments);
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("beanTimerException", new BeanTimerException());
		registry.put("debugProcessor", new DebugProcessor());
		registry.put("errorBodyBean", new ErrorBodyBean());
		registry.put("deadLetterRouteBuilder", new DeadLetterRouteBuilder());
		CamelContext camelContext = new DefaultCamelContext(registry);
		camelContext.setErrorHandlerBuilder(new DeadLetterChannelBuilder("direct:deadLetter"));
	 
		camelContext.addRoutes(
	         new RouteBuilder() {
	            @Override
	            public void configure() throws Exception
	            {
	               from("timer://foo?delay=1&period=1000&repeatCount=5")
	               .setBody().simple("Histoire de faire un contenu ...")
	               .to("bean:beanTimerException?method=process")
	               .to("file:./src/camel/timer/out");
	      
	            }
	         });
		
		camelContext.addRoutes(
		         new RouteBuilder() {
		            @Override
		            public void configure() throws Exception
		            {
		               from("seda:sendErrorByMail")
		               .to("bean:debugProcessor")
		               //.setHeader("CamelFileName").simple("exchangeFailure")
		              
		               .convertBodyTo(String.class)
		              // .setBody().simple("exchange failure at : ${header.dateFailure} cause by : ${body}")
		               .to("bean:debugProcessor")
		               .to("file:./src/camel/timer/email");
		            }
		         });
	      
	      camelContext.addRoutes((RouteBuilder)camelContext.getRegistry().lookupByName("deadLetterRouteBuilder"));
	      //new MyEventNotifier().setCamelContext(camelContext);
	      new ExchangeFailureListener().setCamelContext(camelContext);
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
