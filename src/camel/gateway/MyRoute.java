package camel.gateway;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.BeanInvocation;

public class MyRoute extends RouteBuilder{
	@Override
	public void configure() throws Exception {
		errorHandler(deadLetterChannel("file:./deadLetter/error.txt"));
		from("direct:in")
		.to("log:org.apache.camel?level=INFO")
		.process(new Processor() {
			@Override
			public void process(Exchange arg0) throws Exception {
				BeanInvocation body = arg0.getIn().getBody(BeanInvocation.class);
				System.out.println(body.getMethod().getName());
			}
		})
		.choice()
			.when().simple("${body.method.name} == 'hello'").to("bean:helloImp")
			.when().simple("${body.method.name} == 'bye'").to("bean:byeImp")
		.end();
	}
}