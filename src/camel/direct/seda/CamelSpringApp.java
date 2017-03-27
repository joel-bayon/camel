package camel.direct.seda;

import java.util.Scanner;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments)
	{
		ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
		ProducerTemplate producer  = (ProducerTemplate)springContext.getBean(ProducerTemplate.class);
		ConsumerTemplate consumer  = (ConsumerTemplate)springContext.getBean(ConsumerTemplate.class);
		Scanner clavier = new Scanner(System.in);
		while(true) {
			System.out.print("Entrez un prénom :");
			String prenom = clavier.nextLine();
			producer.sendBody("direct:in", prenom);
			System.out.println(consumer.receiveBody("seda:out", String.class));
			if("0".equals(prenom))
				break;
		}
		
		springContext.close();
		clavier.close();
	
	}
}
