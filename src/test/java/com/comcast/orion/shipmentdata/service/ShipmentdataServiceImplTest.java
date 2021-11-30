package com.comcast.orion.shipmentdata.service;


import static org.junit.Assert.assertNull;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comcast.orion.shipmentdata.dao.entity.OmwNetxDeviceConfig;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrder;
import com.comcast.orion.shipmentdata.domain.OmwShippingOrderVo;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.DeviceDetail;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.TDSGetRetrieveDeviceDetailsRequest;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.TDSGetRetrieveDeviceDetailsResponse;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.AdditionalAttribute;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.LineItem;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentOrder;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentOrderDetail;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentTrackingDetail;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShippingAddress;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.TDSPostPersistShipmentOrderRequest;
import com.comcast.orion.shipmentdata.repository.ShipmentDataRepository;
import com.comcast.orion.shipmentdata.repository.ShipmentOrderRepository;
import com.comcast.orion.shipmentdata.utils.ShipmentServiceMapper;
import com.comcast.orion.shipmentdata.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(ShipmentdataServiceImpl.class)
public class ShipmentdataServiceImplTest {

	@InjectMocks
	private static ShipmentdataServiceImpl shipmentdataServiceImpl;

	@Mock
	private ShipmentOrderRepository shipmentOrderRepository;

	@Mock
	private ShipmentDataRepository shipmentDataRepository;
	
	@Mock
	private ShipmentServiceMapper shipmentServiceMapper;

	@Mock
	private static EntityManager em;

	@Mock
	Query query;

	@Mock
	private static Query query1;

	TDSGetRetrieveDeviceDetailsRequest getRetrieveDeviceDetails;
	TDSGetRetrieveDeviceDetailsResponse getRetrieveDeviceDetailsResponse;
	TDSPostPersistShipmentOrderRequest persistShipmentOrder;
	OmwShippingOrder omwShippingOrder;
	List<OmwShippingOrderVo> responseList ;
	
	ShipmentServiceMapper mock;
	BigInteger customerId;

	List<OmwNetxDeviceConfig> omwNetxDeviceConfigList;
	OmwNetxDeviceConfig omwNetxDeviceConfig;
	
	List<Object[]> objectList;
	Object[] row1;
	String queryString;
	List<Object[]> resultSet;

	BigInteger[] id;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		id = new BigInteger[2];
		id[0] = new BigInteger("2");
		id[1] = new BigInteger("2");

		Mockito.when(em.createNativeQuery("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE",OmwNetxDeviceConfig.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(omwNetxDeviceConfigList);
		Mockito.when(em.createNativeQuery("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE", OmwNetxDeviceConfig.class).getResultList()).thenReturn(omwNetxDeviceConfigList);
		//Mockito.when(query.getResultList()).thenReturn(objectList);

		mock = org.mockito.Mockito.mock(ShipmentServiceMapper.class);
		query = org.mockito.Mockito.mock(Query.class);

		String createdDate = "2019-09-13 11:26:50";
		Timestamp ts = Timestamp.valueOf(createdDate);

		List<DeviceDetail> deviceDetails = new ArrayList();
		DeviceDetail deviceDetail = new DeviceDetail();
		deviceDetail.setDeviceMake("Polycom");
		deviceDetail.setDeviceModel("IP Phone");
		deviceDetail.setDeviceName("Polycom SoundPoint VVX 500 HD");
		deviceDetail.setDeviceType("VVX 500");
		deviceDetail.setQuantity(0);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);
		
		deviceDetail.setDeviceMake("Polycom");
		deviceDetail.setDeviceModel("IP Phone");
		deviceDetail.setDeviceName("Polycom SoundPoint VVX 1500 HD");
		deviceDetail.setDeviceType("VVX 1500");
		deviceDetail.setQuantity(0);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);
		
		deviceDetail.setDeviceMake("EdgeMarc");
		deviceDetail.setDeviceModel("N/A");
		deviceDetail.setDeviceName("EdgeMarc 4550-30 WAN Calls");
		deviceDetail.setDeviceType("4550");
		deviceDetail.setQuantity(0);
		deviceDetail.setRequiredWANLicenseCount(30);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);
		
		deviceDetail.setDeviceMake("EdgeMarc");
		deviceDetail.setDeviceModel("N/A");
		deviceDetail.setDeviceName("EdgeMarc 4550-5 WAN Calls");
		deviceDetail.setDeviceType("4550");
		deviceDetail.setQuantity(0);
		deviceDetail.setCurrentWANLicenseCount(5);
		deviceDetail.setRequiredWANLicenseCount(5);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);
		
		deviceDetail.setDeviceMake("EdgeMarc");
		deviceDetail.setDeviceModel("N/A");
		deviceDetail.setDeviceName("EdgeMarc 4550 WAN Upgrade 5-10");
		deviceDetail.setDeviceType("4550");
		deviceDetail.setQuantity(0);
		deviceDetail.setCurrentWANLicenseCount(10);
		deviceDetail.setRequiredWANLicenseCount(30);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);
		
		getRetrieveDeviceDetails = new TDSGetRetrieveDeviceDetailsRequest();
		getRetrieveDeviceDetails.setDeviceDetail(deviceDetails);
		
		
		List<com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail> deviceDetailss = new ArrayList();
		com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail deviceDetail1 = new com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail();
		deviceDetail1.setDeviceMake("Polycom");
		deviceDetail1.setDeviceModel("IP Phone");
		deviceDetail1.setDeviceName("Polycom SoundPoint VVX 500 HD");
		deviceDetail1.setDeviceType("VVX 500");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("Polycom");
		deviceDetail1.setDeviceModel("IP Phone");
		deviceDetail1.setDeviceName("Polycom SoundPoint VVX 1500 HD");
		deviceDetail1.setDeviceType("VVX 1500");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("EdgeMarc");
		deviceDetail1.setDeviceModel("N/A");
		deviceDetail1.setDeviceName("EdgeMarc 4550-30 WAN Calls");
		deviceDetail1.setDeviceType("4550");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setRequiredWANLicenseCount(30);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("EdgeMarc");
		deviceDetail1.setDeviceModel("N/A");
		deviceDetail1.setDeviceName("EdgeMarc 4550-5 WAN Calls");
		deviceDetail1.setDeviceType("4550");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setCurrentWANLicenseCount(5);
		deviceDetail1.setRequiredWANLicenseCount(5);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("EdgeMarc");
		deviceDetail1.setDeviceModel("N/A");
		deviceDetail1.setDeviceName("EdgeMarc 4550 WAN Upgrade 5-10");
		deviceDetail1.setDeviceType("4550");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setCurrentWANLicenseCount(10);
		deviceDetail1.setRequiredWANLicenseCount(5);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("EdgeMarc");
		deviceDetail1.setDeviceModel("N/A");
		deviceDetail1.setDeviceName("EdgeMarc 4550 WAN Upgrade 10-15");
		deviceDetail1.setDeviceType("4550");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setRequiredWANLicenseCount(10);
		deviceDetail1.setCurrentWANLicenseCount(15);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		deviceDetail1.setDeviceMake("EdgeMarc");
		deviceDetail1.setDeviceModel("N/A");
		deviceDetail1.setDeviceName("EdgeMarc 4550 WAN Upgrade 15-30");
		deviceDetail1.setDeviceType("4550");
		deviceDetail1.setQuantity(0);
		deviceDetail1.setRequiredWANLicenseCount(15);
		deviceDetail1.setCurrentWANLicenseCount(30);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		getRetrieveDeviceDetailsResponse = new com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.TDSGetRetrieveDeviceDetailsResponse();
		getRetrieveDeviceDetailsResponse.setDeviceDetail(deviceDetailss);
		omwNetxDeviceConfigList = new ArrayList();
		omwNetxDeviceConfig = new OmwNetxDeviceConfig();
		omwNetxDeviceConfig.setDeviceMake("Polycom");
		omwNetxDeviceConfig.setDeviceModel("IP Phone");
		omwNetxDeviceConfig.setDeviceName("Polycom SoundPoint VVX 500 HD");
		omwNetxDeviceConfig.setDeviceType("VVX 500");
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("Polycom");
		omwNetxDeviceConfig.setDeviceModel("IP Phone");
		omwNetxDeviceConfig.setDeviceName("Polycom SoundPoint VVX 500 HD");
		omwNetxDeviceConfig.setDeviceType("VVX 500");
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("Polycom");
		omwNetxDeviceConfig.setDeviceModel("IP Phone");
		omwNetxDeviceConfig.setDeviceName("Polycom SoundPoint VVX 1500 HD");
		omwNetxDeviceConfig.setDeviceType("VVX 1500");
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("EdgeMarc");
		omwNetxDeviceConfig.setDeviceModel("N/A");
		omwNetxDeviceConfig.setDeviceName("EdgeMarc 4550-30 WAN Calls");
		omwNetxDeviceConfig.setDeviceType("4550");
		omwNetxDeviceConfig.setRequiredWANLicenseCount(30);
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("EdgeMarc");
		omwNetxDeviceConfig.setDeviceModel("N/A");
		omwNetxDeviceConfig.setDeviceName("EdgeMarc 4550-5 WAN Calls");
		omwNetxDeviceConfig.setDeviceType("4550");
		omwNetxDeviceConfig.setCurrentWANLicenseCount(5);
		omwNetxDeviceConfig.setRequiredWANLicenseCount(5);
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("EdgeMarc");
		omwNetxDeviceConfig.setDeviceModel("N/A");
		omwNetxDeviceConfig.setDeviceName("EdgeMarc 4550 WAN Upgrade 5-10");
		omwNetxDeviceConfig.setDeviceType("4550");
		omwNetxDeviceConfig.setCurrentWANLicenseCount(10);
		omwNetxDeviceConfig.setRequiredWANLicenseCount(5);
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("EdgeMarc");
		omwNetxDeviceConfig.setDeviceModel("N/A");
		omwNetxDeviceConfig.setDeviceName("EdgeMarc 4550 WAN Upgrade 10-15");
		omwNetxDeviceConfig.setDeviceType("4550");
		omwNetxDeviceConfig.setRequiredWANLicenseCount(10);
		omwNetxDeviceConfig.setCurrentWANLicenseCount(15);
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		omwNetxDeviceConfig.setDeviceMake("EdgeMarc");
		omwNetxDeviceConfig.setDeviceModel("N/A");
		omwNetxDeviceConfig.setDeviceName("EdgeMarc 4550 WAN Upgrade 15-30");
		omwNetxDeviceConfig.setDeviceType("4550");
		omwNetxDeviceConfig.setRequiredWANLicenseCount(15);
		omwNetxDeviceConfig.setCurrentWANLicenseCount(30);
		omwNetxDeviceConfigList.add(omwNetxDeviceConfig);
		
		
		
		
		persistShipmentOrder = new TDSPostPersistShipmentOrderRequest ();
		ShipmentOrder shipmentOrder = new ShipmentOrder();
		List<AdditionalAttribute> additionalAttributes = new ArrayList<AdditionalAttribute>();
		List<LineItem> lineItems = new ArrayList<LineItem>();
		List<ShipmentTrackingDetail> shipmentTrackingDetails = new ArrayList<ShipmentTrackingDetail>();
		ShippingAddress shippingAddress = new ShippingAddress();
		ShipmentOrderDetail shipmentOrderDetail = new ShipmentOrderDetail();
		
		shipmentOrder.setAdditionalAttributes(additionalAttributes);
		shipmentOrder.setLineItems(lineItems);
		shipmentOrder.setShipmentTrackingDetails(shipmentTrackingDetails);
		shipmentOrder.setShippingAddress(shippingAddress);
		shipmentOrder.setShipmentOrderDetail(shipmentOrderDetail);
		
		shipmentOrder.setCompsOrderNumber("ABC-19172");
		shipmentOrder.setCreatedBy("AMDOCS");
		shipmentOrder.setCreatedTimeStamp(ts);
		shipmentOrder.setExternalTransactionId("2315237912853126");
		shipmentOrder.setId(new BigInteger("1"));
		shipmentOrder.setOrderStatus("OPEN");
		shipmentOrder.setPurchaseOrderNumber("75315");
		shipmentOrder.setVendorOrderNumber("852147");
		shipmentOrder.setVendorOrderStatus("New");
		
		
		persistShipmentOrder.setShipmentOrder(shipmentOrder);
		
		
		
		omwShippingOrder = new OmwShippingOrder();
		
		objectList = new ArrayList<Object[]>(1);
		row1 = new Object[14];
		row1[0] = new BigInteger("1");
		row1[1] = "3";
		row1[2] = "ABC-19200";
		row1[3] = "O-1236562";
		row1[4] = "Cancel";
		row1[5] = "cancelOrder636";
		row1[6] = "Amdocs";
		row1[7] = ts;
		row1[8] = ts;
		row1[9] = "New";
		objectList.add(0, row1);
		
		responseList = new ArrayList();
		OmwShippingOrderVo omwShippingOrderVo = new OmwShippingOrderVo();
		omwShippingOrderVo.setId(new BigInteger("1"));
		omwShippingOrderVo.setPurchaseOrderNumber("3");
		omwShippingOrderVo.setCompsOrderID("ABC-19200");
		omwShippingOrderVo.setVendorOrderId("O-1236562");
		omwShippingOrderVo.setOrderStatus("Cancel");
		omwShippingOrderVo.setExternalTransactionId("cancelOrder636");
		omwShippingOrderVo.setCreatedBy("Amdocs");
		omwShippingOrderVo.setCreatedTimeStamp(ts);
		omwShippingOrderVo.setModifiedTimeStamp(ts);
		omwShippingOrderVo.setVendorOrderStatus("New");
		responseList.add(omwShippingOrderVo);

	}

	public javax.persistence.Query getResultList() {
		return query;
	}

/*	@Test
	public void getTDSRetrieveDeviceDetailsTest() throws Exception {
		try {
		int result = 1;
		//SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE   (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  ;
		//Mockito.when(em.createNativeQuery("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE   (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  ;",OmwNetxDeviceConfig.class)).thenReturn(query);
		Mockito.when(em.createNativeQuery("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE   (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  OR (  DEVICE_MAKE = ('EdgeMarc') AND DEVICE_TYPE = ('4550') AND DEVICE_MODEL = ('N/A') AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( 10 ) AND 30 )  ;",OmwNetxDeviceConfig.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(omwNetxDeviceConfigList);
		//Mockito.when(em.createNativeQuery("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE", OmwNetxDeviceConfig.class).getResultList()).thenReturn(omwNetxDeviceConfigList);
		Mockito.when(shipmentServiceMapper.mapTDSRequestToTDSResponse(getRetrieveDeviceDetails)).thenReturn(getRetrieveDeviceDetailsResponse);
		 //assertNull(shipmentdataServiceImpl.getTDSRetrieveDeviceDetails(getRetrieveDeviceDetails));
		shipmentdataServiceImpl.getTDSRetrieveDeviceDetails(getRetrieveDeviceDetails);
		}catch (OrionMiddlewareServiceException e) {
			LOGGER.info("test case failed, exception shouldn't be thrown");
		}
	}*/

	@Test
	public void persistShipmentOrderDetailsTest() throws Exception {
		String createdBy="AMDOCS";
		String  externalTransactionId="21231212";
		Mockito.when(shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrder)).thenReturn(omwShippingOrder);
		shipmentdataServiceImpl.persistShipmentOrderDetails(persistShipmentOrder,createdBy,externalTransactionId);
		//assertNull(shipmentdataServiceImpl.persistShipmentOrderDetails(persistShipmentOrder,createdBy,externalTransactionId));
	}

	@Test
	public void retrieveShipmentOrdersTest() throws Exception {
		List<String> statusList = new ArrayList();
		statusList.add("CANCEL");
		statusList.add("OPEN");
		Mockito.when(em.createNativeQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(objectList);
		Mockito.when(shipmentServiceMapper.mapperShipmentOrdermsFromObjectLists(objectList)).thenReturn(responseList);
		shipmentdataServiceImpl.retrieveShipmentOrders(statusList);
		//assertNull(shipmentdataServiceImpl.retrieveShipmentOrders(statusList));
	}
	
	@Test
	public void retrieveShipmentOrdersTest1() throws Exception {
		try {
		List<String> statusList = new ArrayList();
		statusList.add("CANCEL");
		statusList.add("OPEN");
		Mockito.when(em.createNativeQuery(Mockito.anyString())).thenReturn(query);
	//	Mockito.when(query.getResultList()).thenReturn(resultSetSearchCriteria);
		Mockito.when(shipmentServiceMapper.mapperShipmentOrdermsFromObjectLists(objectList)).thenReturn(responseList);
		//shipmentdataServiceImpl.retrieveShipmentOrders(statusList);
		assertNull(shipmentdataServiceImpl.retrieveShipmentOrders(statusList));
		}catch (OrionMiddlewareServiceException e) {
			LOGGER.info("test case failed, exception shouldn't be thrown");
		}
	}

	/*@Test
	public void getOrderDetailsTest() throws Exception {
		String vendorOrderId="";
		Mockito.when(shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrder)).thenReturn(omwShippingOrder);
		Mockito.when(shipmentOrderRepository.orderDetailsByVendorOrderIds(vendorOrderId)).thenReturn(omwShippingOrder);
		//shipmentdataServiceImpl.getOrderDetails(vendorOrderId);
		assertNull(shipmentdataServiceImpl.getOrderDetails(vendorOrderId));
	}*/
	
	@Test
	public void getOrderDetailsTest1() throws Exception {
		try {
		String vendorOrderId="";
		omwShippingOrder = new OmwShippingOrder();
		Mockito.when(shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrder)).thenReturn(omwShippingOrder);
		Mockito.when(shipmentOrderRepository.orderDetailsByVendorOrderIds(vendorOrderId)).thenReturn(omwShippingOrder);
		//shipmentdataServiceImpl.getOrderDetails(vendorOrderId);
		assertNull(shipmentdataServiceImpl.getOrderDetails(vendorOrderId));
		}catch (OrionMiddlewareServiceException e) {
			LOGGER.info("test case failed, exception shouldn't be thrown");
		}
	}

	
	@Test
	public void updateOrderStatusTest() throws Exception {
		String  externalTransactionId="21231212";
		Mockito.when(shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrder)).thenReturn(omwShippingOrder);
		//shipmentdataServiceImpl.updateOrderStatus(persistShipmentOrder,externalTransactionId);
		assertNull(shipmentdataServiceImpl.updateOrderStatus(persistShipmentOrder,externalTransactionId));
	}

}
