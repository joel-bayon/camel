package camel.restlet.commande;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments)
	{
		new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
	
	}
}
