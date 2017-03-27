package camel.jms;

import org.apache.camel.CamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments) throws Exception
	{
		ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
		CamelContext camelContext = springContext.getBean(CamelContext.class);
		
		Thread.sleep(60000);
		camelContext.stop();
		springContext.close();
	}
}
