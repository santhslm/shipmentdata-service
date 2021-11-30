package com.comcast.orion.shipmentdata.utils;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConverter {

	private static final Logger DATA_CONV_LOGGER = LoggerFactory.getLogger(DateConverter.class);

	public static Timestamp stringToDateConverter(String date) {

		DATA_CONV_LOGGER.info("Converting String to date {}", date);
		Timestamp timestamp = null;

		if (null != date && !date.isEmpty()) {

			timestamp = Timestamp.valueOf(date);

		}

		DATA_CONV_LOGGER.info("Converted time stamp : {}", timestamp);
		return timestamp;
	}

	public static String datetoStringConverter(Timestamp timestamp) {

		DATA_CONV_LOGGER.info("datetoStringConverter {}", timestamp);
		String date = "";
		if (null != timestamp) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = dateFormat.format(timestamp);
		}

		DATA_CONV_LOGGER.info("Converted Timestamp to string {}", date);
		return date;

	}
	
	
	public static String createDatetoStringConverter(BigInteger getId) {

		DATA_CONV_LOGGER.info("current datetoStringConverter {}" );
		String date = null;
		if (null == getId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 date = dateFormat.format(timestamp);
		}
		DATA_CONV_LOGGER.info("Converted current to string {}", date);
		return date;

	}
	
	public static String modifiedDatetoStringConverter(BigInteger getId) {

		DATA_CONV_LOGGER.info("current datetoStringConverter {}" );
		String date ="";
		if (null != getId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 date = dateFormat.format(timestamp);
		}
		DATA_CONV_LOGGER.info("Converted current to string {}", date);
		return date;

	}
	
	
	public static Timestamp generateTime() {

		DATA_CONV_LOGGER.info("datetoStringConverter {}");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		DATA_CONV_LOGGER.info("generated Timestamp  {}", timestamp);
		return timestamp;

	}
}
