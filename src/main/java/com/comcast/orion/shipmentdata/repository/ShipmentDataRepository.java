package com.comcast.orion.shipmentdata.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

import com.comcast.orion.shipmentdata.dao.entity.OmwNetxDeviceConfig;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.TDSGetRetrieveDeviceDetailsRequest;

/**
 * ShipmentDataRepository
 *
 */

@Repository
@Transactional
@ComponentScan({ "com.comcast.orion.shipmentdata.service" })
public interface ShipmentDataRepository extends JpaRepository<OmwNetxDeviceConfig, BigInteger> {

}
