package com.comcast.orion.shipmentdata.controller;

import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
import com.comcast.orion.shipmentdata.repository.ShipmentOrderRepository;
import com.comcast.orion.shipmentdata.service.ShipmentdataService;
import com.comcast.orion.shipmentdata.utils.ShipmentServiceMapper;
import com.comcast.orion.shipmentdata.utils.exceptions.*;

@RunWith(MockitoJUnitRunner.class)
public class ShipmentdataControllerTest {

	@InjectMocks
	ShipmentdataController controller;

	@Mock
	ShipmentdataService service;

	@Mock
	private ShipmentOrderRepository repository;

	ResponseEntity<TDSGetRetrieveDeviceDetailsResponse> getRetrieveDeviceDetailsResponse;
	ResponseEntity<TDSPostPersistShipmentOrderRequest> tdsPostPersistShipmentOrderRequest;
	ResponseEntity<String> stringResponse;
	
	ResponseEntity<List<OmwShippingOrderVo>> ShippingOrderVoListResponse;
	
	
	private List<TDSGetRetrieveDeviceDetailsResponse> customerList;

	HttpServletRequest httpRequest;
	HttpServletResponse httpResponse;
	
	TDSGetRetrieveDeviceDetailsRequest getRetrieveDeviceDetailsRequest;
	TDSGetRetrieveDeviceDetailsResponse tDSGetRetrieveDeviceDetailsResponse;
	TDSPostPersistShipmentOrderRequest persistShipmentOrderDetailsRequest;
	OmwShippingOrder omwShippingOrder;
	List<OmwShippingOrderVo> response ;
	
	ShipmentServiceMapper mock;
	BigInteger customerId;

	List<Object[]> objectList;
	Object[] row1;
	String queryString;
	List<Object[]> resultSet;
	List<Object[]> resultSetRespOrgDataForIds;
	List<Object[]> resultSetSearchCriteria;

	List<Object[]> resultSetCurrentProvider;
	List<Object[]> resultSetSearchCriteriaLOA;
	List<Object[]> resultSetLOA;

	BigInteger[] id;
	
	
	private Logger logger = Logger.getLogger(ShipmentdataControllerTest.class.toString());

	@Before
	public void setUp() throws OrionMiddlewareServiceException {

		
		String createdDate = "2019-09-13 11:26:50";
		Timestamp ts = Timestamp.valueOf(createdDate);

		List<DeviceDetail> deviceDetails = new ArrayList();
		DeviceDetail deviceDetail = new DeviceDetail();
		deviceDetail.setDeviceMake("Polycom");
		deviceDetail.setDeviceModel("IP Phone");
		deviceDetail.setDeviceName("Polycom SoundPoint VVX 500 HD");
		deviceDetail.setDeviceType("VVX 500");
		deviceDetail.setQuantity(1);
		deviceDetail.setResourceId("2200-44500-001");
		deviceDetails.add(deviceDetail);

		getRetrieveDeviceDetailsRequest = new TDSGetRetrieveDeviceDetailsRequest();
		getRetrieveDeviceDetailsRequest.setDeviceDetail(deviceDetails);
		
		
		List<com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail> deviceDetailss = new ArrayList();
		com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail deviceDetail1 = new com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.DeviceDetail();
		deviceDetail1.setDeviceMake("Polycom");
		deviceDetail1.setDeviceModel("IP Phone");
		deviceDetail1.setDeviceName("Polycom SoundPoint VVX 500 HD");
		deviceDetail1.setDeviceType("VVX 500");
		deviceDetail1.setQuantity(1);
		deviceDetail1.setResourceId("2200-44500-001");
		deviceDetailss.add(deviceDetail1);
		
		tDSGetRetrieveDeviceDetailsResponse = new TDSGetRetrieveDeviceDetailsResponse();
		tDSGetRetrieveDeviceDetailsResponse.setDeviceDetail(deviceDetailss);

		customerList = new ArrayList();
		customerList.add(tDSGetRetrieveDeviceDetailsResponse);
		
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
		
		
		TDSPostPersistShipmentOrderRequest persistShipmentOrder = new TDSPostPersistShipmentOrderRequest ();
		persistShipmentOrder.setShipmentOrder(shipmentOrder);
		
		
		
		omwShippingOrder = new OmwShippingOrder();
		
		persistShipmentOrderDetailsRequest = new TDSPostPersistShipmentOrderRequest();
		persistShipmentOrderDetailsRequest.setShipmentOrder(shipmentOrder);
		
	}

	private ShipmentdataController createTestSubject() {
		return new ShipmentdataController();
	}

	@Test
	public void testRetrieveDeviceDetails() throws OrionMiddlewareServiceException {
		 httpRequest  = org.mockito.Mockito.mock(HttpServletRequest.class);
		 httpResponse = org.mockito.Mockito.mock(HttpServletResponse.class);
		getRetrieveDeviceDetailsResponse = new ResponseEntity<TDSGetRetrieveDeviceDetailsResponse>(HttpStatus.OK);
		Mockito.when(service.getTDSRetrieveDeviceDetails(getRetrieveDeviceDetailsRequest)).thenReturn(tDSGetRetrieveDeviceDetailsResponse);
		controller.retrieveDeviceDetails(httpRequest,getRetrieveDeviceDetailsRequest,httpResponse);
		assertNotNull(getRetrieveDeviceDetailsResponse);

	}
	
	
	@Test
	public void testPersistShipmentOrder() throws OrionMiddlewareServiceException {
		 httpRequest  = org.mockito.Mockito.mock(HttpServletRequest.class);
		 httpResponse = org.mockito.Mockito.mock(HttpServletResponse.class);
		String createdBy = "AMDOCS";
		String externalTransactionId="123121123";
		stringResponse = new ResponseEntity<String>(HttpStatus.OK);
		service.persistShipmentOrderDetails(persistShipmentOrderDetailsRequest,createdBy,externalTransactionId);
		controller.persistShipmentOrder(httpRequest,createdBy,externalTransactionId,persistShipmentOrderDetailsRequest,httpResponse);
		assertNotNull(stringResponse);

	}
	
	
	
	
	@Test
	public void testRetrieveShipmentOrder() throws OrionMiddlewareServiceException {
		 httpRequest  = org.mockito.Mockito.mock(HttpServletRequest.class);
		 httpResponse = org.mockito.Mockito.mock(HttpServletResponse.class);
		 List<String> status = new ArrayList<>();
		String externalTransactionId="123121123";
		ShippingOrderVoListResponse = new ResponseEntity<List<OmwShippingOrderVo>>(HttpStatus.OK);
		Mockito.when(service.retrieveShipmentOrders(status)).thenReturn(response);
		controller.retrieveShipmentOrder(httpRequest,externalTransactionId,status,httpResponse);
		assertNotNull(ShippingOrderVoListResponse);

	}
	
	
	
	@Test
	public void testGetOrderDetails() throws OrionMiddlewareServiceException {
		 httpRequest  = org.mockito.Mockito.mock(HttpServletRequest.class);
		 httpResponse = org.mockito.Mockito.mock(HttpServletResponse.class);
		 String vendorOrderId="8521463";
		 String externalTransactionId="123121123";
		 tdsPostPersistShipmentOrderRequest = new ResponseEntity<TDSPostPersistShipmentOrderRequest>(HttpStatus.OK);
		Mockito.when(service.getOrderDetails(vendorOrderId)).thenReturn(persistShipmentOrderDetailsRequest);
		controller.getOrderDetails(httpRequest,externalTransactionId,vendorOrderId,httpResponse);
		assertNotNull(tdsPostPersistShipmentOrderRequest);

	}
	
	
	@Test
	public void testUpdateOrderStatus() throws OrionMiddlewareServiceException {
		 httpRequest  = org.mockito.Mockito.mock(HttpServletRequest.class);
		 httpResponse = org.mockito.Mockito.mock(HttpServletResponse.class);
		 String externalTransactionId="123121123";
		 stringResponse = new ResponseEntity<String>(HttpStatus.OK);
		Mockito.when(service.updateOrderStatus(persistShipmentOrderDetailsRequest,externalTransactionId)).thenReturn(persistShipmentOrderDetailsRequest);
		controller.updateOrderStatus(httpRequest,externalTransactionId,persistShipmentOrderDetailsRequest,httpResponse);
		assertNotNull(stringResponse);

	}


}
