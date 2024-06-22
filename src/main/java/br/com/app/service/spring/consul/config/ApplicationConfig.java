package br.com.app.service.spring.consul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ApplicationConfig {
	
	@Value("${dataMessage}")
	private String dataMessage;

	public String getDataMessage() {
		return dataMessage;
	}

	public void setDataMessage(String dataMessage) {
		this.dataMessage = dataMessage;
	}
	

}
