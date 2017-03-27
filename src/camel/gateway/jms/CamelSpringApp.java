package camel.gateway.jms;

import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments)
	{
		ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
		HelloByeService myService = springContext.getBean(HelloByeService.class);
		Scanner clavier = new Scanner(System.in);
		while(true) {
			System.out.print("Entrez un prénom :");
			String prenom = clavier.nextLine();
			System.out.println(myService.hello(prenom));
			System.out.println(myService.bye(prenom));
			if("0".equals(prenom))
				break;
		}
		springContext.close();
		clavier.close();
	
	}
}
