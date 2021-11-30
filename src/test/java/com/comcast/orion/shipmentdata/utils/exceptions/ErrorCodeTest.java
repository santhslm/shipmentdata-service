package com.comcast.orion.shipmentdata.utils.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.comcast.orion.shipmentdata.utils.exceptions.ErrorCode;

public class ErrorCodeTest {

	/**
	 * String toString() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testToString()
		throws Exception {
		ErrorCode errorCode = ErrorCode.BUSINESS_ERROR_AMIL;

		String result = errorCode.toString();

		assertEquals("OrionErrorCode{errorCode=BUSINESS_ERROR_DOWNSTREAM, errorDescription='', httpStatus='590'}", result);
	}
}