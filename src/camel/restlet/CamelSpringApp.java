package camel.restlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments)
	{
		@SuppressWarnings({ "unused", "resource" })
		ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
	
	}
}
