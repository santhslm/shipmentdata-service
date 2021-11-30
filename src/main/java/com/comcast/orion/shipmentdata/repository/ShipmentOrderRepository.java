package com.comcast.orion.shipmentdata.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrder;

/**
 * ShipmentOrderRepository
 *
 */

@Repository
@ComponentScan({ "com.comcast.orion.shipmentdata.service" })
public interface ShipmentOrderRepository extends JpaRepository<OmwShippingOrder, BigInteger> {

	List<OmwShippingOrder> findAllByOrderStatusIn(List<String> orderStatus);
	
	@Query(value = "SELECT orders FROM OmwShippingOrder orders LEFT JOIN FETCH orders.omwShippingAddress address LEFT JOIN FETCH address.omwShippingAddressContact contact LEFT JOIN FETCH orders.omwShippingOrderDetail detail LEFT JOIN FETCH orders.omwShippingOrderAddtAttr attr LEFT JOIN FETCH orders.omwShippingTrackingDetails tracking LEFT JOIN FETCH orders.omwShippingDeviceDetail device WHERE orders.vendorOrderId = :orderId ")
	//@Query(value = "SELECT orders FROM OmwShippingOrder orders LEFT JOIN FETCH orders.omwShippingAddress address WHERE orders.vendorOrderId = :vendorOrder ")
	OmwShippingOrder orderDetailsByVendorOrderIds(@Param("orderId") String vendorOrderId);
	
	//Fix for defect 113497 - Searching by COMPS id
	@Query(value = "SELECT orders FROM OmwShippingOrder orders LEFT JOIN FETCH orders.omwShippingAddress address LEFT JOIN FETCH address.omwShippingAddressContact contact LEFT JOIN FETCH orders.omwShippingOrderDetail detail LEFT JOIN FETCH orders.omwShippingOrderAddtAttr attr LEFT JOIN FETCH orders.omwShippingTrackingDetails tracking LEFT JOIN FETCH orders.omwShippingDeviceDetail device WHERE orders.compsOrderID = :orderId ")
	OmwShippingOrder orderDetailsByCompsOrderIds(@Param("orderId") String compsOrderId);
	
	OmwShippingOrder findByVendorOrderId(String vendorOrderId);
}
