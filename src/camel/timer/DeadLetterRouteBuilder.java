package camel.timer;

import org.apache.camel.builder.RouteBuilder;

public class DeadLetterRouteBuilder extends RouteBuilder {

	@Override
    public void configure() throws Exception
    {
       from("direct:deadLetter")
      .to("file:./src/camel/timer/dead.letter")
      .to("bean:errorBodyBean")
      .to("log:camel.error?level=ERROR");
    }
}
