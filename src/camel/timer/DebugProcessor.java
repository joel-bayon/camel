package camel.timer;

import org.apache.camel.Exchange;

public class DebugProcessor {
	public void debug(Exchange exchange) {
		Object o = exchange;
		o=null;
	}

}
