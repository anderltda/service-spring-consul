package br.com.app.service.spring.consul.controller;

import java.net.URI;
import java.util.Optional;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.app.service.spring.consul.config.ApplicationConfig;
import br.com.app.service.spring.consul.config.ControllerConfig;

@RestController
public class ServiceSpringConsulController {

	@Autowired
	private ApplicationConfig applicaitonConfig;

	@Autowired
	private ControllerConfig controllerConfig;

	@Autowired
	private DiscoveryClient discoveryClient;

	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/getDataMessage")
	private String getDataMessage() {
		return applicaitonConfig.getDataMessage();
	}

	@GetMapping("/getMessage")
	private String getMessage() {
		return controllerConfig.getMessage();
	}

	@GetMapping("/my-health-check")
	public ResponseEntity<String> customHealthCheck() {
		String message = "Testing my healh check function";
		return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
	}

	@GetMapping("/services")
	public Optional<URI> serviceURL() {

		discoveryClient.getInstances("service-spring-consul").forEach(serviceInstance -> {
			System.out.println(serviceInstance.getServiceId());
			System.out.println(serviceInstance.getPort());
		});

		return discoveryClient.getInstances("service-spring-consul").stream().map(instance -> instance.getUri())
				.findFirst();
	}

	@GetMapping("/loadBalancer")
	public String loadBalancer() {
		return loadBalancerClient.choose("service-spring-consul").getUri().toString();
	}

	@GetMapping("/discoveryClient")
	public String discoveryMethod() throws ServiceUnavailableException {
		URI service = serviceURL().map(s -> s.resolve("/method")).orElseThrow(ServiceUnavailableException::new);
		return restTemplate.getForEntity(service, String.class).getBody();
	}

	@GetMapping("/method")
	public String method() {
		return "Teste de acesso ao method " + applicaitonConfig.getDataMessage();
	}

}
