package com.comcast.orion.shipmentdata.utils.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import com.comcast.orion.shipmentdata.controller.ShipmentdataController;
import com.comcast.orion.shipmentdata.domain.ErrorMessage;



public class ShipmentdataServiceExceptionHandlerTest {

	@InjectMocks
	ShipmentdataServiceExceptionHandler shipmentdataServiceExceptionHandler;

	@InjectMocks
	com.comcast.orion.shipmentdata.controller.ShipmentdataController shipmentdataController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
	}

	@Test
	public void testHandleConstraintViolationException() throws Exception {
		Set<ConstraintViolation<?>> violations = new HashSet<>();
		ConstraintViolationException exception = new ConstraintViolationException("applicationSource should be empty",violations);
		ResponseEntity<Object> responseEntity = shipmentdataServiceExceptionHandler.handleResourceNotFoundException(exception);
		assertNotNull(responseEntity);
		assertEquals(ErrorCode.CONTRACT_VALIDATION_ERROR.getHttpStatus(), responseEntity.getStatusCode().value());		
	}
	
	@Test
	public void testMapErrorResponse() throws Exception {
		ErrorMessage responseEntity = shipmentdataServiceExceptionHandler.mapErrorResponse(ErrorCode.CONTRACT_VALIDATION_ERROR.getErrorCode(),ErrorCode.CONTRACT_VALIDATION_ERROR.getErrorDescription());
		assertNotNull(responseEntity);	
	}

	
	@Test
	public void testOrionMiddlewareServiceException() {
		OrionMiddlewareServiceException exception = new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_AMIL,
				"testMessage");
		ResponseEntity<ErrorMessage> responseEntity = shipmentdataServiceExceptionHandler.handleException(exception);
		assertNotNull(responseEntity);
		assertEquals(ErrorCode.BUSINESS_ERROR_AMIL.getHttpStatus(), 590);
	}
	
	@Test
	public void testHandleException() {
		ResponseEntity<ErrorMessage> responseEntity = shipmentdataServiceExceptionHandler.handleException(new Exception());
		assertNotNull(responseEntity);
		assertEquals(ErrorCode.TECHNICAL_ERROR.getHttpStatus(), responseEntity.getStatusCode().value());
	}
	
	@Test
	public void testHttpClientErrorException() {
		ResponseEntity<ErrorMessage> responseEntity = shipmentdataServiceExceptionHandler.handleException(new HttpClientErrorException(HttpStatus.NOT_FOUND));
		assertNotNull(responseEntity);
		assertEquals(ErrorCode.TECHNICAL_ERROR.getHttpStatus(), responseEntity.getStatusCode().value());
	}
	
	@Test
	public void testRestClientException() {
		ResponseEntity<ErrorMessage> responseEntity = shipmentdataServiceExceptionHandler.handleRestClientException(new RestClientException("Error"));
		assertNotNull(responseEntity);
		assertEquals(ErrorCode.CONNECTIVITY_ERROR.getHttpStatus(), responseEntity.getStatusCode().value());
	}
	
	/*@Test
	public void testHandleMethodArgumentNotValid() {
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(new MethodParameter(Object.class.getMethods()[0], 1), new BindException(new BeanPropertyBindingResult(new Object(), "")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept", "application/json");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		WebRequest request = null;
		ResponseEntity<Object> responseEntity = shipmentdataServiceExceptionHandler.handleMethodArgumentNotValid(ex,headers,status,request);
		assertNotNull(responseEntity.getBody());
	}*/
	
	/*@Test
	public void testHandleHttpMessageNotReadable() {
		HttpMessageNotReadableException exception = new HttpMessageNotReadableException("testData");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept", "application/json");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		WebRequest request = null;
		ResponseEntity<Object> responseEntity = shipmentdataServiceExceptionHandler.handleHttpMessageNotReadable(exception,headers,status,request);
		assertNotNull(responseEntity);
	}*/	
	
}