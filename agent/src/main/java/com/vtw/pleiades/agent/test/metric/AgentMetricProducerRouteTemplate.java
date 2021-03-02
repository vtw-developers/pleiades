package com.vtw.pleiades.agent.test.metric;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AgentMetricProducerRouteTemplate extends EndpointRouteBuilder {

	public static final String TEMPLATE_ID = "AgentMetricProducer";
	
	public static final String TOPIC = "agent-metric";

	private final String routeId = "4";
	private final String period = "10000";
	private final String topic = "agent-metrics";
	
	@Override
	public void configure() throws Exception {
		from(timer(routeId).period(period)).routeId(routeId)
		.log(LoggingLevel.DEBUG, "Collecting agent metric...")
		.bean(AgentMetricCollector.class)
		.log(LoggingLevel.DEBUG, "Collected agent metric: ${body}")
		.marshal().json()
		.to(kafka(topic))
		.log("Produced agent metric to Kafka topic named " + topic);
	}

}
