package camel.content.based.router;

import org.apache.camel.spring.Main;

public class CamelSpringBasedRouterApp {
	public static void main(final String[] arguments) throws Exception
	{
		// Main makes it easier to run a Spring application
		Main main = new Main();
		// configure the location of the Spring XML file
		main.setApplicationContextUri("camel/content/based/router/spring.camel.xml");
		// enable hangup support allows Camel to detect when the JVM is terminated
		main.enableHangupSupport();
		// run and block until Camel is stopped (or JVM terminated)
		main.run();
	}
}