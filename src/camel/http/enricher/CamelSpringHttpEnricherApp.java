package camel.http.enricher;

import org.apache.camel.spring.Main;

public class CamelSpringHttpEnricherApp {
	public static void main(final String[] arguments) throws Exception
	{
		Main main = new Main();
		main.setApplicationContextUri("camel/http/enricher/spring.camel.xml");
		main.enableHangupSupport();
		main.run();
	}
}