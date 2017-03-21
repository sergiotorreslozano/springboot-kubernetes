package com.springboot.kubernetes.chuck;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChuckController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private ChuckFactClient chuckFactClient;

	@RequestMapping("/")
	public @ResponseBody String home() {
		log.debug("Got a request!");
		return "Hello Spring boot and Kubernetes";
	}

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping("/service-instances")
	public List<String> serviceInstances() {
		return this.discoveryClient.getServices();
	}

	@RequestMapping("/chuck")
	public @ResponseBody ChuckFact greeting() {
		log.debug("Got a request!");
		return chuckFactClient.randomFact();
	}

}

@FeignClient("springboot-docker-db")
interface ChuckFactClient {

	@RequestMapping(method = RequestMethod.GET, value = "/chuck")
	ChuckFact randomFact();
}
