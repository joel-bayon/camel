package camel.content.based.router;

import org.apache.camel.main.Main;

public class CamelBasedRouterApp {
	public static void main(String[] args) throws Exception {
		// Main makes it easier to run a Spring application
		Main main = new Main();
		// configure the location of the Spring XML file
		main.addRouteBuilder(new MyRoute());
		// enable hangup support allows Camel to detect when the JVM is terminated
		main.enableHangupSupport();
		// run and block until Camel is stopped (or JVM terminated)
		main.run();
		
		}
}
