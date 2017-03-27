package camel.gateway;


import org.springframework.stereotype.Component;

@Component
public class HelloImpl  {
	public String hello(String name) {
		return "Hello to " + name.toUpperCase()+ " from Camel route !";
	}
}
