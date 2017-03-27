package camel.http.enricher;

import org.apache.camel.main.Main;

public class CamelHttpEnricherApp {
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.addRouteBuilder(new MyRoute());
		main.enableHangupSupport();
		main.run();
	}
}
