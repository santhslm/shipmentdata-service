package com.comcast.orion.shipmentdata.config;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Callable;

//import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import org.junit.Test;

import com.comcast.orion.shipmentdata.config.MDCHystrixConcurrencyStrategy;
import com.comcast.orion.shipmentdata.config.MDCHystrixContextCallable;

public class MDCHystrixConcurrencyStrategyTest {
	/**
	 * Run the Callable<Object> wrapCallable(Callable<T>) method test.
	 *
	 * @throws Exception
	 * 
	 * 
	 */
	private Callable<String> actual ;
	
	@Test
	public void testWrapCallable_1()
		throws Exception {
		MDCHystrixConcurrencyStrategy mdcHystrixConcurrencyStrategy = new MDCHystrixConcurrencyStrategy();
		Callable<Object> callable = new MDCHystrixContextCallable(actual);
		//Callable<Object> callable = new MDCHystrixContextCallable(new IteratorClosureAdapter(new Object()));
		Callable<Object> response = mdcHystrixConcurrencyStrategy.wrapCallable(callable);
		assertNotNull(mdcHystrixConcurrencyStrategy);
	}
}