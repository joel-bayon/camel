package camel.gateway;

import java.util.Scanner;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelApp {
	
	public static void main(String[] args) throws Exception {
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("helloImp", new HelloImpl());
		registry.put("byeImp", new ByeImpl());
		CamelContext camelContext = new DefaultCamelContext(registry);
		camelContext.addRoutes(new MyRoute());
		Scanner clavier = new Scanner(System.in);
		HelloByeService service  = new ProxyBuilder(camelContext).endpoint("direct:in").binding(false).build(HelloByeService.class);
		camelContext.start();
		while(true) {
			System.out.print("Entrez un prénom :");
			String prenom = clavier.nextLine();
			System.out.println(service.hello(prenom));
			System.out.println(service.bye(prenom));
			if("0".equals(prenom))
				break;
		}
		camelContext.stop();
		clavier.close();
		
	}

}
