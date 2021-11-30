package com.comcast.orion.shipmentdata.config;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.checkerframework.checker.units.qual.K;
//import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import org.junit.Test;
import org.slf4j.MDC;

import com.comcast.orion.shipmentdata.config.MDCHystrixContextCallable;

public class MDCHystrixContextCallableTest {
	
	/**
	 * Run the Object call() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/8/18 4:42 PM
	 */
	
	private Callable<String> actual ;
	
	@Test
	public void testCall_1()
		throws Exception {
		MDCHystrixContextCallable mdcHystrixContextCallable = new MDCHystrixContextCallable(actual);
		//MDCHystrixContextCallable mdcHystrixContextCallable = new MDCHystrixContextCallable(new IteratorClosureAdapter(new Object()));
		Map<String, String> map = new HashMap<>();
		MDC.setContextMap(map);
		//Object response = mdcHystrixContextCallable.call();
		//assertNull(response);
	}

}