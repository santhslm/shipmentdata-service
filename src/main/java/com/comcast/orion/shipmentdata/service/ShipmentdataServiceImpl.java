package com.comcast.orion.shipmentdata.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.comcast.orion.shipmentdata.command.DownstreamOperationCommand;
import com.comcast.orion.shipmentdata.dao.entity.OmwNetxDeviceConfig;
import com.comcast.orion.shipmentdata.dao.entity.OmwShippingOrder;
import com.comcast.orion.shipmentdata.domain.OmwShippingOrderVo;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.DeviceDetail;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.request.TDSGetRetrieveDeviceDetailsRequest;
import com.comcast.orion.shipmentdata.getTDSRetrieveDeviceDetails.response.TDSGetRetrieveDeviceDetailsResponse;
import com.comcast.orion.shipmentdata.postPersistShipmentOrder.request.TDSPostPersistShipmentOrderRequest;
import com.comcast.orion.shipmentdata.repository.ShipmentDataRepository;
import com.comcast.orion.shipmentdata.repository.ShipmentOrderRepository;
import com.comcast.orion.shipmentdata.utils.ShipmentServiceMapper;
import com.comcast.orion.shipmentdata.utils.exceptions.ErrorCode;
import com.comcast.orion.shipmentdata.utils.exceptions.OrionMiddlewareServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;

@Service("shipmentdataService")
public class ShipmentdataServiceImpl implements ShipmentdataService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired(required = true)
	ShipmentDataRepository shipmentDataRepository;

	@Autowired
	ShipmentOrderRepository shipmentOrderRepository;

	@Autowired
	DownstreamOperationCommand downstreamOperationCommand;

	/** The mapper. */
	@Autowired
	ObjectMapper mapper;

	@VisibleForTesting
	@Autowired
	ShipmentServiceMapper shipmentServiceMapper;

	@Autowired
	private EntityManager em;

	int count = 0;

	@Value("${devices.wanupgradesupport}")
	private String[] wandevices;

	@Override
	public TDSGetRetrieveDeviceDetailsResponse getTDSRetrieveDeviceDetails(
			TDSGetRetrieveDeviceDetailsRequest getRetrieveDeviceDetails) throws OrionMiddlewareServiceException {
		TDSGetRetrieveDeviceDetailsResponse getRetrieveDeviceDetailsResponse = null;

		log.info("Start ##############getTDSRetrieveDeviceDetails###############");
		List<String> wandevicelist = new ArrayList<>(Arrays.asList(wandevices));

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT * FROM OMW_NETX_DEVICE_CONFIG WHERE (");
		queryString = createDynamicQuery(queryString, getRetrieveDeviceDetails);
		queryString.append(") AND DEVICE_RETIRED='N'");
		queryString.append(" ;");
		log.info("Start ##############getTDSRetrieveDeviceDetails queryString: " + queryString);

		List<DeviceDetail> deviceDetailList = new ArrayList<DeviceDetail>();

		List<OmwNetxDeviceConfig> omwNetxDeviceConfigList = em
				.createNativeQuery(queryString.toString(), OmwNetxDeviceConfig.class).getResultList();

		log.info("Start ##############getTDSRetrieveDeviceDetails omwNetxDeviceConfigList :"
				+ omwNetxDeviceConfigList.size());

		getRetrieveDeviceDetails.getDeviceDetail().stream().forEach(deviceDetails -> {
			omwNetxDeviceConfigList.stream().forEach(omwNetxDeviceConfigs -> {

				// Default Scenario without EdgeMarc Devices
				if (deviceDetails.getRequiredWANLicenseCount() == null) {
					if ((deviceDetails.getDeviceMake().equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceMake()))
							&& (deviceDetails.getDeviceType().equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceType()))
							&& (deviceDetails.getDeviceModel()
									.equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceModel()))) {
						deviceDetails.setDeviceName(omwNetxDeviceConfigs.getDeviceName());

					}
				}

				// EdgeMarc Devices Scenario starts
				else if (wandevicelist.contains(deviceDetails.getDeviceMake())) {

					// EdgeMarc New-New Scenario 1 -- if RequiredWANCount is not 0 and
					// CurrentWANCount is null
					if ((deviceDetails.getRequiredWANLicenseCount() != null
							&& deviceDetails.getRequiredWANLicenseCount() != 0)
							&& (deviceDetails.getCurrentWANLicenseCount() == null
									&& omwNetxDeviceConfigs.getCurrentWANLicenseCount() == null)
							&& ((deviceDetails.getDeviceMake().equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceMake()))
									&& (deviceDetails.getDeviceType()
											.equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceType()))
									&& ((deviceDetails.getRequiredWANLicenseCount() == omwNetxDeviceConfigs
											.getRequiredWANLicenseCount())))) {
						deviceDetails.setDeviceName(omwNetxDeviceConfigs.getDeviceName());
					}

					// EdgeMarc New-New Scenario 2 -- if both RequiredWANCount and CurrentWANCount
					// are same
					else if (((deviceDetails.getRequiredWANLicenseCount() != null
							&& omwNetxDeviceConfigs.getCurrentWANLicenseCount() == null)
							&& (deviceDetails.getRequiredWANLicenseCount() == deviceDetails.getCurrentWANLicenseCount()
									&& (deviceDetails.getRequiredWANLicenseCount() == omwNetxDeviceConfigs
											.getRequiredWANLicenseCount()))
							&& (deviceDetails.getRequiredWANLicenseCount() != 0
									&& deviceDetails.getCurrentWANLicenseCount() != 0))) {
						deviceDetails.setDeviceName(omwNetxDeviceConfigs.getDeviceName());
					}

					// EdgeMarc Amend Scenario
					else if (((deviceDetails.getCurrentWANLicenseCount() != null
							&& omwNetxDeviceConfigs.getCurrentWANLicenseCount() != null
							&& deviceDetails.getCurrentWANLicenseCount() != 0)
							&& (((deviceDetails.getRequiredWANLicenseCount() != null
									&& omwNetxDeviceConfigs.getRequiredWANLicenseCount() != null
									&& omwNetxDeviceConfigs.getRequiredWANLicenseCount() != 0)))
							&& (deviceDetails.getRequiredWANLicenseCount() != deviceDetails.getCurrentWANLicenseCount())
							&& deviceDetails.getDeviceMake().equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceMake()))
							&& (deviceDetails.getDeviceType().equalsIgnoreCase(omwNetxDeviceConfigs.getDeviceType()))) {
						if (deviceDetails.getRequiredWANLicenseCount() >= omwNetxDeviceConfigs
								.getCurrentWANLicenseCount() 
								&& deviceDetails.getCurrentWANLicenseCount() <=omwNetxDeviceConfigs
										.getRequiredWANLicenseCount() ) {
							DeviceDetail deviceDetail = new DeviceDetail();
							deviceDetail.setCurrentWANLicenseCount(omwNetxDeviceConfigs.getRequiredWANLicenseCount());
							deviceDetail.setDeviceMake(omwNetxDeviceConfigs.getDeviceMake());
							deviceDetail.setDeviceType(omwNetxDeviceConfigs.getDeviceType());
							deviceDetail.setDeviceModel(omwNetxDeviceConfigs.getDeviceModel());
							deviceDetail.setDeviceName(omwNetxDeviceConfigs.getDeviceName());
							deviceDetail.setResourceId(deviceDetails.getResourceId());
							deviceDetail.setQuantity(deviceDetails.getQuantity());
							deviceDetail.setRequiredWANLicenseCount(omwNetxDeviceConfigs.getCurrentWANLicenseCount());
							deviceDetailList.add(deviceDetail);
						}

					}
				}

			});

		});

		deviceDetailList.stream().forEach(deviceDetais -> {
			getRetrieveDeviceDetails.getDeviceDetail().add(deviceDetais);
		});

		getRetrieveDeviceDetails.getDeviceDetail().removeIf(deviceDetais -> deviceDetais.getDeviceName() == null);

		log.info("getRetrieveDeviceDetails " + getRetrieveDeviceDetails.toString());

		log.info("End ##############getTDSRetrieveDeviceDetails###############");

		getRetrieveDeviceDetailsResponse = shipmentServiceMapper.mapTDSRequestToTDSResponse(getRetrieveDeviceDetails);
		return getRetrieveDeviceDetailsResponse;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void persistShipmentOrderDetails(TDSPostPersistShipmentOrderRequest persistShipmentOrder, String createdBy,
			String externalTransactionId) throws OrionMiddlewareServiceException {

		log.info("Inside persistShipmentOrderDetails.toString() START");
		OmwShippingOrder omwShippingOrder;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		omwShippingOrder = shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrder);

		log.info("omwShippingOrder : " + omwShippingOrder.toString());

		if (createdBy != null) {
			omwShippingOrder.setCreatedBy(createdBy);
		} else {
			omwShippingOrder.setCreatedBy(persistShipmentOrder.getShipmentOrder().getCreatedBy());
		}
		omwShippingOrder.setExternalTransactionId(externalTransactionId);

		if (persistShipmentOrder.getShipmentOrder().getId() != null) {
			omwShippingOrder.setId(persistShipmentOrder.getShipmentOrder().getId());
			// omwShippingOrder.setCreatedTimeStamp(persistShipmentOrder.getShipmentOrder().getCreatedTimeStamp());
			// omwShippingOrder.setModifiedTimeStamp(timestamp);
		} else {
			// omwShippingOrder.setCreatedTimeStamp(persistShipmentOrder.getShipmentOrder().getCreatedTimeStamp());
		}

		log.info("omwShippingOrder : " + omwShippingOrder.toString());

		shipmentOrderRepository.save(omwShippingOrder);

		log.info("Inside persistShipmentOrderDetails.toString() ENDS :" + persistShipmentOrder.toString());

	}

	private StringBuilder createDynamicQuery(StringBuilder queryString,
			TDSGetRetrieveDeviceDetailsRequest getRetrieveDeviceDetails) {
		count = 1;

		// for (DeviceDetail deviceDetails : getRetrieveDeviceDetails.getDeviceDetail())
		// {
		getRetrieveDeviceDetails.getDeviceDetail().stream().forEach(deviceDetails -> {
			queryString.append(" ( ");
			if (deviceDetails.getDeviceMake() != null) {
				queryString.append(" DEVICE_MAKE = ('" + deviceDetails.getDeviceMake() + "')");
				queryString.append(" AND");
			}
			if (deviceDetails.getDeviceType() != null) {
				queryString.append(" DEVICE_TYPE = ('" + deviceDetails.getDeviceType() + "')");
				queryString.append(" AND");
			}
			if (deviceDetails.getDeviceModel() != null) {
				queryString.append(" DEVICE_MODEL = ('" + deviceDetails.getDeviceModel() + "')");
			}

			if ((deviceDetails.getRequiredWANLicenseCount() != null && (deviceDetails.getRequiredWANLicenseCount() != 0
					&& deviceDetails.getCurrentWANLicenseCount() == null))
					|| ((deviceDetails.getRequiredWANLicenseCount() != null
							&& deviceDetails.getCurrentWANLicenseCount() != null)
							&& (deviceDetails.getRequiredWANLicenseCount() == deviceDetails.getCurrentWANLicenseCount())
							&& (deviceDetails.getRequiredWANLicenseCount() != 0
									&& deviceDetails.getCurrentWANLicenseCount() != 0))) {
				queryString.append(" AND REQ_WAN_LICENSE_COUNT =" + deviceDetails.getRequiredWANLicenseCount()
						+ " AND MAX_WAN_LICENSE_COUNT IS NULL ");
			} else if ((deviceDetails.getRequiredWANLicenseCount() != null
					&& deviceDetails.getRequiredWANLicenseCount() != 0)
					&& (deviceDetails.getRequiredWANLicenseCount() != null
							&& deviceDetails.getCurrentWANLicenseCount() != 0)) {
				// queryString.append(" AND MAX_WAN_LICENSE_COUNT BETWEEN ( "+
				// deviceDetails.getCurrentWANLicenseCount() +" ) AND "+
				// deviceDetails.getRequiredWANLicenseCount() +"");
				queryString.append(" AND MAX_WAN_LICENSE_COUNT  BETWEEN  ( " + deviceDetails.getCurrentWANLicenseCount()
						+ " ) AND " + deviceDetails.getRequiredWANLicenseCount() + "");
				queryString.append(" AND REQ_WAN_LICENSE_COUNT  BETWEEN  ( " + deviceDetails.getCurrentWANLicenseCount()
						+ " ) AND " + deviceDetails.getRequiredWANLicenseCount() + "");

			}

			queryString.append(" ) ");
			if (getRetrieveDeviceDetails.getDeviceDetail().size() != count) {
				queryString.append(" OR");
			}
			count++;
		});

		return queryString;

	}

	@Override
	public List<OmwShippingOrderVo> retrieveShipmentOrders(List<String> statusList)
			throws OrionMiddlewareServiceException {

		log.info("Start retrieveShipmentOrders method in ShipmentdataServiceImpl");
		List<OmwShippingOrderVo> responseList;
		String statusListCommaSeparated = String.join("','", statusList);
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT * FROM OMW_SHIPPING_ORDER WHERE  ORDER_STATUS IN ('");
		queryString.append(statusListCommaSeparated);

		queryString.append(" ');");
		log.info("##############retrieveShipmentOrders queryString: " + queryString);

		List<Object[]> objectList = em.createNativeQuery(queryString.toString()).getResultList();
		if (objectList.size() < 1) {
			throw new OrionMiddlewareServiceException(ErrorCode.NO_DATA_FOUND);
		}
		responseList = shipmentServiceMapper.mapperShipmentOrdermsFromObjectLists(objectList);
		return responseList;
	}

	@Override
	public TDSPostPersistShipmentOrderRequest getOrderDetails(String orderId)
			throws OrionMiddlewareServiceException {
		log.info("Start getOrderDetails method in ShipmentdataServiceImpl");
		OmwShippingOrder omwShippingOrder;
		TDSPostPersistShipmentOrderRequest response = null;

		// omwShippingOrder =
		// Defect fix - 113497 -> Find order by COMPS orderNumber
		if(orderId.startsWith("O-")) {
			log.info("Querying for vendorOrderID");
			omwShippingOrder = shipmentOrderRepository.orderDetailsByVendorOrderIds(orderId);
		}else {
			log.info("Querying for COMPSOrderID");
			omwShippingOrder = shipmentOrderRepository.orderDetailsByCompsOrderIds(orderId);
		}

		if (omwShippingOrder == null) {
			throw new OrionMiddlewareServiceException(ErrorCode.NO_DATA_FOUND);
		}
		response = shipmentServiceMapper.mapShippingOrder(omwShippingOrder);

		log.info("End getOrderDetails method in ShipmentdataServiceImpl");
		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public TDSPostPersistShipmentOrderRequest updateOrderStatus(
			TDSPostPersistShipmentOrderRequest persistShipmentOrderDetailsRequest, String externalTransactionId)
			throws OrionMiddlewareServiceException {

		OmwShippingOrder omwShippingOrder;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		omwShippingOrder = shipmentServiceMapper.mapOmwShippingOrder(persistShipmentOrderDetailsRequest);
		// omwShippingOrder =
		// shipmentServiceMapper.mapShippingOrder(persistShipmentOrderDetailsRequest);

		omwShippingOrder.setId(persistShipmentOrderDetailsRequest.getShipmentOrder().getId());
		omwShippingOrder.setCreatedBy(persistShipmentOrderDetailsRequest.getShipmentOrder().getCreatedBy());
		omwShippingOrder
				.setCreatedTimeStamp(persistShipmentOrderDetailsRequest.getShipmentOrder().getCreatedTimeStamp());
		omwShippingOrder.setExternalTransactionId(externalTransactionId);
		// omwShippingOrder.setModifiedTimeStamp(timestamp);
		omwShippingOrder
				.setModifiedTimeStamp(persistShipmentOrderDetailsRequest.getShipmentOrder().getModifiedTimeStamp());
		log.info("omwShippingOrder : " + omwShippingOrder.toString());

		shipmentOrderRepository.save(omwShippingOrder);

		return null;
	}

}
