package camel.cxfrs;

import java.util.HashMap;
import java.util.Map;

public class MyServiceImpl implements MyService {
	static Map<String, String> capitals = new HashMap<>();
	
	static {
		capitals.put("fr", "Paris");
		capitals.put("en", "London");
		capitals.put("us", "Washingtown");
	}

	@Override
	public String getCities(String countryCode) {
		return "<capital>"+capitals.get(countryCode) + "</capital>";
	}

}
