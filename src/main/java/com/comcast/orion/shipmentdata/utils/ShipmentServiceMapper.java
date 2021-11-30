package com.comcast.orion.shipmentdata.utils;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comcast.orion.shipmentdata.dao.entity.OmwShippingAddress;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingAddressContact;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingDeviceDetail;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrder;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrderAddtAttr;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrderDetail;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingTrackingDetail;
import com.comcast.orion.shipmentdata.domain.OmwShippingOrderVo;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.TDSGetRetrieveDeviceDetailsRequest;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.TDSGetRetrieveDeviceDetailsResponse;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.AdditionalAttribute;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.LineItem;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentOrder;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentOrderDetail;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShipmentTrackingDetail;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.ShippingAddress;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.TDSPostPersistShipmentOrderRequest;



/**
 * ShipmentServiceMapper
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShipmentServiceMapper {
	
	/*
	 * 
	 * @param source
	 * @return ListResponse
	 */
	TDSGetRetrieveDeviceDetailsResponse mapTDSRequestToTDSResponse(TDSGetRetrieveDeviceDetailsRequest source);
	
	
	@Mappings({
		@Mapping(target = "compsOrderID", source = "shipmentOrder.compsOrderNumber"),
		@Mapping(target = "purchaseOrderNumber", source = "shipmentOrder.purchaseOrderNumber"),
		@Mapping(target = "vendorOrderId", source = "shipmentOrder.vendorOrderNumber"),
		@Mapping(target = "orderStatus", source = "shipmentOrder.orderStatus"),
		@Mapping(target = "createdTimeStamp", expression = "java(com.comcast.orion.shipmentdata.utils.DateConverter.generateTime())"),
		
		@Mapping(target = "omwShippingTrackingDetails", source = "shipmentOrder.shipmentTrackingDetails", qualifiedByName = {"mapOmwShipmentTrackingDetails" }),
		@Mapping(target = "omwShippingDeviceDetail", source = "shipmentOrder.lineItems", qualifiedByName = {"mapOmwShippingDeviceDetails" }),
		@Mapping(target = "omwShippingOrderDetail", source = "shipmentOrder.shipmentOrderDetail", qualifiedByName = {"mapOmwShipmentOrderDetail" }),
		@Mapping(target = "omwShippingAddress", source = "shipmentOrder.shippingAddress", qualifiedByName = {"mapOmwShippingAddress" }),
		@Mapping(target = "omwShippingOrderAddtAttr", source = "shipmentOrder.additionalAttributes", qualifiedByName = {"mapOmwAdditionalAttributes" })
		
		})
	OmwShippingOrder mapperOmwShippingOrderFromShipmentOrdermVO(ShipmentOrder shipmentOrder);
	
	
	/*@Named("mapOmwShipmentTrackingDetails")
	default List<OmwShippingOrderAddtAttr> mapOmwShipmentTrackingDetails(List<AdditionalAttribute> additionalAttribute){
		List<OmwShippingOrderAddtAttr> omwShippingOrderAddtAttrs;
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		omwShippingOrderAddtAttrs = mapperOmwShippingOrderAddtAttrFromAdditionalAttributeVO(additionalAttribute);
		omwShippingOrderAddtAttrs.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});
		return omwShippingOrderAddtAttrs;
		
	}
	
	
	@Named("mapOmwShippingDeviceDetails")
	default List<OmwShippingDeviceDetail> mapOmwShippingDeviceDetails(List<LineItem> source){
		List<OmwShippingDeviceDetail> omwShippingDeviceDetails;
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		omwShippingDeviceDetails = mapperOmwShippingDeviceDetailFromLineItemVO(source);
		omwShippingDeviceDetails.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});
		
		return omwShippingDeviceDetails;
	}*/
	
	
	@Named("mapOmwShipmentOrderDetail")
	default OmwShippingOrderDetail mapOmwShipmentOrderDetail(ShipmentOrderDetail shipmentOrderDetail){
		OmwShippingOrderDetail omwShippingOrderDetail;
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		omwShippingOrderDetail = mapperOmwShippingOrderDetailFromShipmentOrderDetailVO(shipmentOrderDetail);
		omwShippingOrderDetail.setOmwShippingOrder(omwShippingOrder);
		return omwShippingOrderDetail;
	}
	
	
	@Named("mapOmwShippingAddress")
	default OmwShippingAddressContact mapOmwShippingAddress(ShippingAddress shippingAddress){
		
		OmwShippingAddress omwShippingAddress;
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		omwShippingAddress = mapperOmwShippingAddressFromShippingAddressVO(shippingAddress);
		omwShippingAddress.setOmwShippingOrder(omwShippingOrder);
		
		OmwShippingAddressContact omwShippingAddressContact;
		omwShippingAddressContact = mapperOmwShippingAddressContactFromShippingAddressVO(shippingAddress);
		omwShippingAddressContact.setOmwShippingAddress(omwShippingAddress);
		omwShippingAddress.setOmwShippingAddressContact(omwShippingAddressContact);
		
		return omwShippingAddressContact;
		
	}
	
	/*@Named("mapOmwAdditionalAttributes")
	default List<OmwShippingOrderAddtAttr> mapOmwAdditionalAttributes(List<AdditionalAttribute> additionalAttribute){
		
		List<OmwShippingOrderAddtAttr> omwShippingOrderAddtAttrs;
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		omwShippingOrderAddtAttrs = mapperOmwShippingOrderAddtAttrFromAdditionalAttributeVO(additionalAttribute);
		omwShippingOrderAddtAttrs.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});
		
		return omwShippingOrderAddtAttrs;
		
	}*/
	
	
	
	@Mapping(target = "addressLine1", source = "shippingAddress.address.addressLine1")
	@Mapping(target = "city", source = "shippingAddress.address.city")
	@Mapping(target = "state", source = "shippingAddress.address.state")
	@Mapping(target = "zipCode", source = "shippingAddress.address.zip")
	@Mapping(target = "id", source = "shippingAddress.address.id")
	@Mapping(target = "omwShippingAddressContact.company", source = "company")
	@Mapping(target = "omwShippingAddressContact.firstName", source = "firstName")
	@Mapping(target = "omwShippingAddressContact.lastName", source = "lastName")
	@Mapping(target = "omwShippingAddressContact.phone", source = "phone")
	@Mapping(target = "omwShippingAddressContact.id", source = "id")
	//@Named("mapOmwSshippingAddress")
	OmwShippingAddress mapperOmwShippingAddressFromShippingAddressVO(ShippingAddress shippingAddress);
	
	@Mapping(target = "company", source = "company")
	@Mapping(target = "firstName", source = "firstName")
	@Mapping(target = "lastName", source = "lastName")
	@Mapping(target = "phone", source = "phone")
	OmwShippingAddressContact mapperOmwShippingAddressContactFromShippingAddressVO(ShippingAddress shippingAddress);
	
	@Mapping(target = "shipper", source = "shipmentOrderDetail.shippingOptions.shipper")
	@Mapping(target = "shippingMethod", source = "shipmentOrderDetail.shippingOptions.method")
	@Mapping(target = "signatureRequired", source = "shipmentOrderDetail.shippingOptions.signatureRequired")
	@Mapping(target = "shippingSystem", source = "shipmentOrderDetail.shipmentSystem")
	//@Named("mapOmwSshipmentOrderDetail")
	OmwShippingOrderDetail mapperOmwShippingOrderDetailFromShipmentOrderDetailVO(ShipmentOrderDetail shipmentOrderDetail);
	
	//@Named("mapOmwAdditionalAttributes")
	Set<OmwShippingOrderAddtAttr> mapperOmwShippingOrderAddtAttrFromAdditionalAttributeVO(List<AdditionalAttribute> additionalAttribute);
	
	
	//@Named("mapOmwShipmentTrackingDetails")
	Set<OmwShippingTrackingDetail> mapperOmwShippingTrackingDetailFromShipmentTrackingDetailVO(List<ShipmentTrackingDetail> additionalAttribute);
	
	//@Named("mapOmwShippingDeviceDetails")
	@IterableMapping(qualifiedByName = "IfTrue")
	Set<OmwShippingDeviceDetail> mapperOmwShippingDeviceDetailFromLineItemVO(List<LineItem> source);
	
	@Mapping(target = "extDeviceId", source = "resourceId")
	@Mapping(target = "make", source = "deviceMake")
	@Mapping(target = "model", source = "deviceModel")
	@Mapping(target = "type", source = "deviceType")
	//@Mapping(target = "createdDate", expression = "java(com.comcast.orion.shipmentdata.utils.DateConverter.createDatetoStringConverter(source.getId()))")
	//@Mapping(target = "modifiedDate", expression = "java(com.comcast.orion.shipmentdata.utils.DateConverter.modifiedDatetoStringConverter(source.getId()))")
	@Named(value = "IfTrue")
	OmwShippingDeviceDetail mapLineItemToOmwShippingDeviceDetail(LineItem source);
	
	
/*********************************************  Entity To VO   *****************************************************/
	
	@IterableMapping(qualifiedByName = "IfTrueVO")
	List<LineItem> mapperLineItemVOFromOmwShippingDeviceDetail(Set<OmwShippingDeviceDetail> source);
	
	@Mapping(target = "resourceId", source = "extDeviceId")
	@Mapping(target = "deviceMake", source = "make")
	@Mapping(target = "deviceModel", source = "model")
	@Mapping(target = "deviceType", source = "type")
	@Named(value = "IfTrueVO")
	LineItem mapLineItemToOmwShippingDeviceDetail(OmwShippingDeviceDetail source);
	
	
	/*************************/
	
	
	@Mapping(source = "addressLine1", target = "address.addressLine1")
	@Mapping(source = "city", target = "address.city")
	@Mapping(source = "state", target = "address.state")
	@Mapping(source = "zipCode", target = "address.zip")
	@Mapping(source = "id", target = "address.id")
	@Mapping(source = "omwShippingAddressContact.company", target = "company")
	@Mapping(source = "omwShippingAddressContact.firstName", target = "firstName")
	@Mapping(source = "omwShippingAddressContact.lastName", target = "lastName")
	@Mapping(source = "omwShippingAddressContact.phone", target = "phone")
	@Mapping(source = "omwShippingAddressContact.id", target = "id")
	ShippingAddress mapperShippingAddressVOFromOmwShippingAddress(OmwShippingAddress shippingAddress);
	
	@Mapping(source = "company", target = "company")
	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "phone", target = "phone")
	ShippingAddress mapperShippingAddressContactVOFromOmwShippingAddress(OmwShippingAddressContact shippingAddress);
	
	
	
	@IterableMapping(qualifiedByName = "IfList")
	List<OmwShippingOrderVo> mapperShipmentOrdermsFromOmwShippingOrders(List<OmwShippingOrder> shipmentOrder);
	
	
	@Named(value = "IfList")
	OmwShippingOrderVo mapperShipmentOrdermsFromOmwShippingOrders(OmwShippingOrder shipmentOrder);
	
	/*************************/
	
	
	
	@Mapping(source = "shipper", target = "shippingOptions.shipper")
	@Mapping(source = "shippingMethod", target = "shippingOptions.method")
	@Mapping(source = "signatureRequired", target = "shippingOptions.signatureRequired")
	@Mapping(source = "shippingSystem", target = "shipmentSystem")
	ShipmentOrderDetail mapperShipmentOrderDetailVOFromOmwShippingOrderDetail( OmwShippingOrderDetail shipmentOrderDetail);
	
	List<AdditionalAttribute> mapperAdditionalAttributeVOFromOmwShippingOrderAddtAttr(Set<OmwShippingOrderAddtAttr>  additionalAttribute);
	
	
	List<ShipmentTrackingDetail> mapperShipmentTrackingDetailVOFromOmwShippingTrackingDetailFrom(Set<OmwShippingTrackingDetail>  additionalAttribute);
	
	
	/**
	 * @param request
	 * @return
	 */
	
	default TDSPostPersistShipmentOrderRequest mapShippingOrder(OmwShippingOrder omwShippingOrder){
		TDSPostPersistShipmentOrderRequest response = new TDSPostPersistShipmentOrderRequest();
		ShipmentOrder shipmentOrder = new ShipmentOrder(); 
		
		shipmentOrder.setId(omwShippingOrder.getId());
		shipmentOrder.setVendorOrderNumber(omwShippingOrder.getVendorOrderId());
		shipmentOrder.setCompsOrderNumber(omwShippingOrder.getCompsOrderID());
		shipmentOrder.setCreatedBy(omwShippingOrder.getCreatedBy());
		shipmentOrder.setCreatedTimeStamp(omwShippingOrder.getCreatedTimeStamp());
		shipmentOrder.setOrderStatus(omwShippingOrder.getOrderStatus());
		shipmentOrder.setPurchaseOrderNumber(omwShippingOrder.getPurchaseOrderNumber());
		shipmentOrder.setVendorOrderStatus(omwShippingOrder.getVendorOrderStatus());
		shipmentOrder.setExternalTransactionId(omwShippingOrder.getExternalTransactionId());
		
		shipmentOrder.setLineItems(mapperLineItemVOFromOmwShippingDeviceDetail(omwShippingOrder.getOmwShippingDeviceDetail()));
		shipmentOrder.setShippingAddress(mapperShippingAddressVOFromOmwShippingAddress(omwShippingOrder.getOmwShippingAddress()));
		shipmentOrder.setShipmentOrderDetail(mapperShipmentOrderDetailVOFromOmwShippingOrderDetail(omwShippingOrder.getOmwShippingOrderDetail()));
		shipmentOrder.setShipmentTrackingDetails(mapperShipmentTrackingDetailVOFromOmwShippingTrackingDetailFrom(omwShippingOrder.getOmwShippingTrackingDetails()));
		shipmentOrder.setAdditionalAttributes(mapperAdditionalAttributeVOFromOmwShippingOrderAddtAttr(omwShippingOrder.getOmwShippingOrderAddtAttr()));
		response.setShipmentOrder(shipmentOrder);
		return response;
		
	}
	
	
	/**
	 * @param request
	 * @return
	 */
	
	default List<OmwShippingOrderVo> mapperShipmentOrdermsFromObjectLists(List<Object[]> objectList){
		List<OmwShippingOrderVo> responseList = new ArrayList();
		for (Object[] result : objectList) {
			OmwShippingOrderVo omwShippingOrderVo = new OmwShippingOrderVo();
			omwShippingOrderVo.setId((BigInteger) result[0]);
			omwShippingOrderVo.setPurchaseOrderNumber((String) result[1]);
			omwShippingOrderVo.setCompsOrderID((String) result[2]);
			omwShippingOrderVo.setVendorOrderId((String) result[3]);
			omwShippingOrderVo.setOrderStatus((String) result[4]);
			omwShippingOrderVo.setExternalTransactionId((String) result[5]);
			omwShippingOrderVo.setCreatedBy((String) result[6]);
			omwShippingOrderVo.setCreatedTimeStamp((Timestamp) result[7]);
			omwShippingOrderVo.setModifiedTimeStamp((Timestamp) result[8]);
			omwShippingOrderVo.setVendorOrderStatus((String) result[9]);
			responseList.add(omwShippingOrderVo);
		}
		return responseList;
		
	}
	
/*********************************************  Entity To VO   *****************************************************/	
	
	
	
	
	/**
	 * @param request
	 * @return
	 */
	default OmwShippingOrder mapOmwShippingOrder(TDSPostPersistShipmentOrderRequest persistShipmentOrder){
		
		OmwShippingOrder omwShippingOrder = new OmwShippingOrder();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		java.util.Date date = new java.util.Date();
		
		
		OmwShippingAddress omwShippingAddress;
		omwShippingAddress = mapperOmwShippingAddressFromShippingAddressVO(persistShipmentOrder.getShipmentOrder().getShippingAddress());
		omwShippingAddress.setOmwShippingOrder(omwShippingOrder);

		OmwShippingAddressContact omwShippingAddressContact;
		omwShippingAddressContact = mapperOmwShippingAddressContactFromShippingAddressVO(persistShipmentOrder.getShipmentOrder().getShippingAddress());
		omwShippingAddressContact.setOmwShippingAddress(omwShippingAddress);
		omwShippingAddress.setOmwShippingAddressContact(omwShippingAddressContact);

		OmwShippingOrderDetail omwShippingOrderDetail;
		omwShippingOrderDetail = mapperOmwShippingOrderDetailFromShipmentOrderDetailVO(persistShipmentOrder.getShipmentOrder().getShipmentOrderDetail());
		omwShippingOrderDetail.setOmwShippingOrder(omwShippingOrder);

		Set<OmwShippingOrderAddtAttr> omwShippingOrderAddtAttrs;
		omwShippingOrderAddtAttrs = mapperOmwShippingOrderAddtAttrFromAdditionalAttributeVO(persistShipmentOrder.getShipmentOrder().getAdditionalAttributes());
		omwShippingOrderAddtAttrs.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});

		Set<OmwShippingTrackingDetail> shipmentTrackingDetails;
		shipmentTrackingDetails = mapperOmwShippingTrackingDetailFromShipmentTrackingDetailVO(persistShipmentOrder.getShipmentOrder().getShipmentTrackingDetails());
		shipmentTrackingDetails.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});

		Set<OmwShippingDeviceDetail> omwShippingDeviceDetails;
		omwShippingDeviceDetails = mapperOmwShippingDeviceDetailFromLineItemVO(persistShipmentOrder.getShipmentOrder().getLineItems());
		omwShippingDeviceDetails.stream().forEach(t -> {
			t.setOmwShippingOrder(omwShippingOrder);
		});
		
		
		/*if(persistShipmentOrder.getShipmentOrder().getCreatedTimeStamp() != null) {
		omwShippingOrder.setId(persistShipmentOrder.getShipmentOrder().getId());
		omwShippingOrder.setCreatedTimeStamp(persistShipmentOrder.getShipmentOrder().getCreatedTimeStamp());
		omwShippingOrder.setCreatedBy(persistShipmentOrder.getShipmentOrder().getCreatedBy());
		omwShippingOrder.setModifiedTimeStamp(timestamp);
		}else {
		omwShippingOrder.setCreatedTimeStamp(timestamp);
		}*/
		
		
		omwShippingOrder.setCompsOrderID(persistShipmentOrder.getShipmentOrder().getCompsOrderNumber());
		omwShippingOrder.setOrderStatus(persistShipmentOrder.getShipmentOrder().getOrderStatus());
		omwShippingOrder.setPurchaseOrderNumber(persistShipmentOrder.getShipmentOrder().getPurchaseOrderNumber());
		omwShippingOrder.setVendorOrderId(persistShipmentOrder.getShipmentOrder().getVendorOrderNumber());
		omwShippingOrder.setVendorOrderStatus(persistShipmentOrder.getShipmentOrder().getVendorOrderStatus());
		omwShippingOrder.setExternalTransactionId(persistShipmentOrder.getShipmentOrder().getExternalTransactionId());

		omwShippingOrder.setOmwShippingAddress(omwShippingAddress);
		omwShippingOrder.setOmwShippingOrderAddtAttr(omwShippingOrderAddtAttrs);
		omwShippingOrder.setOmwShippingTrackingDetails(shipmentTrackingDetails);
		omwShippingOrder.setOmwShippingDeviceDetail(omwShippingDeviceDetails);
		omwShippingOrder.setOmwShippingOrderDetail(omwShippingOrderDetail);
		
		
		return omwShippingOrder;
	}
	
}
