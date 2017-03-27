package camel.timer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments) throws Exception
	{
		ConfigurableApplicationContext factory = new ClassPathXmlApplicationContext("spring.camel2.xml", CamelSpringApp.class);
		//CamelContext camelContext = factory.getBean(CamelContext.class);
	    //camelContext.start();
	    Thread.sleep(10000);
	    //camelContext.stop();
	    factory.close();
		
	}
}
