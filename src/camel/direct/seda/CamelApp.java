package camel.direct.seda;

import java.util.Scanner;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelApp {
	
	public static void main(String[] args) throws Exception {
		
		
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myBean", new MyService());
		CamelContext camelContext = new DefaultCamelContext(registry);
		camelContext.addRoutes(new MyRoute());
		Scanner clavier = new Scanner(System.in);
		ProducerTemplate producer = camelContext.createProducerTemplate();
		ConsumerTemplate consumer = camelContext.createConsumerTemplate();
		camelContext.start();
		while(true) {
			System.out.print("Entrez un prénom :");
			String prenom = clavier.nextLine();
			producer.sendBody("direct:in", prenom);
			System.out.println(consumer.receiveBody("seda:out", String.class));
			if("0".equals(prenom))
				break;
		}
		camelContext.stop();
		clavier.close();
		
	}

}
