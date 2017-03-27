package camel.restlet;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Header;

public class MyServiceImpl {
	static Map<String, String> capitals = new HashMap<>();
	static {capitals.put("fr", "Paris"); capitals.put("en", "London");
		    capitals.put("us", "Washingtown"); }

	public String getCities(@Header("countryCode") String countryCode) {
		return "<capital>"+capitals.get(countryCode) + "</capital>";
	}

}
