package com.comcast.orion.shipmentdata.command;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipmentdata.utils.exceptions.ErrorCode;
import com.comcast.orion.shipmentdata.utils.exceptions.OrionMiddlewareServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * The Class downstreamOperationCommand.
 */
@Component
public class DownstreamOperationCommand {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/** The rest template. */
	@Autowired
	private RestTemplate downstreamRestTemplate;

	/** The amil disable auto pay endpoint. */
	@Value("${downstreamname.downstreamoperation.endpoint}")
	private String downstreamOperationEndpoint;

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "downstreamOperationHystrixFallback", ignoreExceptions = {
			HttpServerErrorException.class, HttpClientErrorException.class })
	public String downstreamOperation(String userName) {

		log.info("Invoking endpoint {}", downstreamOperationEndpoint);
		if (log.isDebugEnabled()) {
			log.debug("Request : {}", userName);
		}
	
		//Following code will invoke the downstream and send the response
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.set("Authorization", "UXF_SessionToken:" + token);
		HttpEntity<DownstreamRequest> entity = new HttpEntity<>(downstreamRequestObject, headers);

		ResponseEntity<DownstreamResponse> responseEntity = downstreamRestTemplate.postForEntity(downstreamOperationEndpoint,
				entity, DownstreamResponse.class);

		if (log.isDebugEnabled()) {
			log.debug("Response : {}", responseEntity.getBody());
		}

		DownstreamResponse downstreamResponse = responseEntity.getBody();*/
		
		log.info("endpoint Invocation SUCCESS");
		return "Hello " + userName + " ! Welcome to generated code. ";
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public String downstreamOperationHystrixFallback(String userName, Throwable e) throws OrionMiddlewareServiceException {
		throw new OrionMiddlewareServiceException(e, ErrorCode.CONNECTIVITY_ERROR);
	}

}
