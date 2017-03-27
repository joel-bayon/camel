package camel.timer;

import org.apache.camel.Message;

public class BeanTimerException {
	int count;
	public void process(Message message) {
		if(count == 3) throw new RuntimeException("count == 3");
		count++;
	}

}
