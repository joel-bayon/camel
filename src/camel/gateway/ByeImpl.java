package camel.gateway;


import org.springframework.stereotype.Component;

@Component
public class ByeImpl {
	public String bye(String name) {
		return "Bye to " + name.toUpperCase() + " from Camel route !";
	}
}
