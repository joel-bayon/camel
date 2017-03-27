package camel.cxfrs;

import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelSpringApp {
	public static void main(final String[] arguments)
	{
		ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring.camel.xml", CamelSpringApp.class);
		MyService myService = springContext.getBean(MyService.class);
		Scanner clavier = new Scanner(System.in);
		while(true) {
			System.out.print("Entrez le code I18N d'un pays :");
			String countryCode = clavier.nextLine();
			System.out.println(myService.getCities(countryCode));
			if("0".equals(countryCode))
				break;
		}
		springContext.close();
		clavier.close();
	
	}
}
