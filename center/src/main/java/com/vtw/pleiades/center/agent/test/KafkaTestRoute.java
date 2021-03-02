package com.vtw.pleiades.center.agent.test;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaTestRoute extends EndpointRouteBuilder {

	@Override
	public void configure() throws Exception {
		from(timer("test").period(5000))
		.setBody().constant("hello")
		.to(kafka("test-001"))
		.log("send");
		
		
		from(kafka("test-001"))
		.log("receive ${body}");
	}

}
