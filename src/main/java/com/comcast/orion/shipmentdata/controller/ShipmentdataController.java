package com.comcast.orion.shipmentdata.controller;

import javax.validation.Valid;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.orion.shipmentdata.dao.entity.OmwShippingDeviceDetail;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrder;
import com.comcast.orion.shipmentdata.domain.OmwShippingOrderVo;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.TDSGetRetrieveDeviceDetailsRequest;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.TDSGetRetrieveDeviceDetailsResponse;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentOrder;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.TDSPostPersistShipmentOrderRequest;
import com.comcast.orion.shipmentdata.service.ShipmentdataService;
import com.comcast.orion.shipmentdata.utils.exceptions.OrionMiddlewareServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//
@RestController
@Api(value = "shipmentdata")
@RequestMapping("/shipmentdata/v1")
@Validated
public class ShipmentdataController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShipmentdataService shipmentdataService;

	@PostMapping(value = "/retrieveDeviceDetails", produces = "application/json")
	@ApiOperation(value = "Operation to retrieve shipment Device Names and details.", notes = "To retrieve shipment Device Names and details.", response = TDSGetRetrieveDeviceDetailsResponse[].class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trackingId", value = "trackingId", required = false, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TDSGetRetrieveDeviceDetailsResponse[].class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<TDSGetRetrieveDeviceDetailsResponse> retrieveDeviceDetails(HttpServletRequest httpRequest,
			@RequestBody @Valid TDSGetRetrieveDeviceDetailsRequest getRetrieveDeviceDetailsRequest, HttpServletResponse httpResponse)
			throws OrionMiddlewareServiceException {
		log.info("Start retrieveDeviceDetails method in ShipmentdataController  operation:"
				+ getRetrieveDeviceDetailsRequest.toString());
		TDSGetRetrieveDeviceDetailsResponse getRetrieveDeviceDetailsResponse = null;
		getRetrieveDeviceDetailsResponse = shipmentdataService
				.getTDSRetrieveDeviceDetails(getRetrieveDeviceDetailsRequest);
		return new ResponseEntity<TDSGetRetrieveDeviceDetailsResponse>(getRetrieveDeviceDetailsResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/persistShipmentOrder")
	@ApiOperation(value = "POST operation to Save shipment order.", notes = "POST operation to Save shipment order.", response = String[].class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trackingId", value = "trackingId", required = false, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String[].class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<String> persistShipmentOrder(HttpServletRequest httpRequest,
			@RequestHeader(value = "source", required = false) String createdBy,
			@RequestHeader(value = "trackingId", required = false) String externalTransactionId,
			@RequestBody @Valid TDSPostPersistShipmentOrderRequest persistShipmentOrderDetailsRequest, HttpServletResponse httpResponse)
			throws OrionMiddlewareServiceException {
		log.info("Start persistShipmentOrder method in ShipmentdataController  operation:"
				+ persistShipmentOrderDetailsRequest.toString());
		shipmentdataService.persistShipmentOrderDetails(persistShipmentOrderDetailsRequest,createdBy,externalTransactionId);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping(value = "/retrieveShipmentOrder")
	@ApiOperation(value = "GET operation to retrieve shipment order details.", notes = "GET operation to retrieve shipment order details.", response = OmwShippingOrder[].class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trackingId", value = "trackingId", required = false, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String[].class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<List<OmwShippingOrderVo>> retrieveShipmentOrder(HttpServletRequest httpRequest,
			@RequestHeader(value = "trackingId", required = false) String externalTransactionId,
			@RequestParam(value = "status", required = false) List<String> status, HttpServletResponse httpResponse)
			throws OrionMiddlewareServiceException {
		log.info("Start retrieveShipmentOrder method in ShipmentdataController operation");
		List<OmwShippingOrderVo> response = shipmentdataService.retrieveShipmentOrders(status);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/orderDetails")
	@ApiOperation(value = "GET operation to retrieve shipment order details based on on orderId [ Both Vendor and COMPS Order Id ].", notes = "Please note that the vendorOrde", response = OmwShippingOrder.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trackingId", value = "trackingId", required = false, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String[].class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<TDSPostPersistShipmentOrderRequest> getOrderDetails(HttpServletRequest httpRequest,
			@RequestHeader(value = "trackingId", required = false) String externalTransactionId,
			@RequestParam(value = "vendorOrderId", required = false) String orderId, HttpServletResponse httpResponse)
			throws OrionMiddlewareServiceException {
		log.info("Start retrieveShipmentOrder method in ShipmentdataController operation"
				+ orderId);
		TDSPostPersistShipmentOrderRequest response = shipmentdataService.getOrderDetails(orderId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
    @PutMapping(value = "/orderStatus")
    @ApiOperation(value = "Operation to update shipment order status.", notes = "To update shipment order status.", response = String[].class)
    @ApiImplicitParams({
                  @ApiImplicitParam(name = "trackingId", value = "trackingId", required = false, dataType = "string", paramType = "header"),
                  @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String[].class),
                  @ApiResponse(code = 400, message = "Bad request", response = String.class),
                  @ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
                  @ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
                  @ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
    public ResponseEntity<String> updateOrderStatus(HttpServletRequest httpRequest,
                  @RequestHeader(value = "trackingId", required = false) String externalTransactionId,
                  @RequestBody @Valid TDSPostPersistShipmentOrderRequest persistShipmentOrderDetailsRequest, HttpServletResponse httpResponse)
                  throws OrionMiddlewareServiceException {
           log.info("Start persistShipmentOrder method in ShipmentdataController  operation:");
           TDSPostPersistShipmentOrderRequest response = shipmentdataService.updateOrderStatus(persistShipmentOrderDetailsRequest,externalTransactionId);
           return new ResponseEntity<>("Success", HttpStatus.OK);
    }


}
