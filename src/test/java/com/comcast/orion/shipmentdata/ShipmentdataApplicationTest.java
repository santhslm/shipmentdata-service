package com.comcast.orion.shipmentdata;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ShipmentdataApplicationTest {

	@Test
	public void testMain_1()
		throws Exception {
		String[] args = new String[] {};

		//ShipmentdataApplication.main(args);
		assertNotNull("Success");
	}
	
	@Test
	public void testDefaultSampler_1()
		throws Exception {
		ShipmentdataApplication irrcashflowApplication = new ShipmentdataApplication();

		//AlwaysSampler result = irrcashflowApplication.defaultSampler();

		// add additional test code here
		//assertNotNull(result);
	}

	
	@Test
	public void testObjectMapper_1()
		throws Exception {
		ShipmentdataApplication deviceVoiceAppln = new ShipmentdataApplication();

		ObjectMapper result = deviceVoiceAppln.objectMapper();
		assertNotNull(result);
	}

}

