/**
 * 
 */
package com.comcast.orion.shipmentdata.dao.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author 512833
 *
 */

@Entity
@DynamicUpdate
@Table(name = "OMW_SHIPPING_DEVICE_DETAIL")
public class OmwShippingDeviceDetail {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "MAKE")
	private String make;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "EXT_DEVICE_ID")
	private String extDeviceId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MAC_ADDRESS")
	private String macAddress;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private Timestamp  createdDate;

	@Column(name = "MODIFIED_DATE",insertable=false, updatable = true)
	@UpdateTimestamp
	private Timestamp  modifiedDate;

	@Column(name = "DEVICE_NAME")
	private String deviceName;
	
	@Column(name = "REQ_WAN_LICENSE_COUNT")
	private Integer requiredWANLicenseCount;
	 
	@Column(name = "MAX_WAN_LICENSE_COUNT")
	private Integer currentWANLicenseCount;

	@Column(name = "OMW_SHIPPING_ORDER_id", insertable = false, updatable = false)
	private BigInteger omwShippingOrderid;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "OMW_SHIPPING_ORDER_ID")
	//@Fetch(FetchMode.JOIN)
	private OmwShippingOrder omwShippingOrder;
	
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}
	
	

	/**
	 * @return the requiredWANLicenseCount
	 */
	public Integer getRequiredWANLicenseCount() {
		return requiredWANLicenseCount;
	}



	/**
	 * @param requiredWANLicenseCount the requiredWANLicenseCount to set
	 */
	public void setRequiredWANLicenseCount(Integer requiredWANLicenseCount) {
		this.requiredWANLicenseCount = requiredWANLicenseCount;
	}



	/**
	 * @return the currentWANLicenseCount
	 */
	public Integer getCurrentWANLicenseCount() {
		return currentWANLicenseCount;
	}



	/**
	 * @param currentWANLicenseCount the currentWANLicenseCount to set
	 */
	public void setCurrentWANLicenseCount(Integer currentWANLicenseCount) {
		this.currentWANLicenseCount = currentWANLicenseCount;
	}



	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the extDeviceId
	 */
	public String getExtDeviceId() {
		return extDeviceId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @return the omwShippingOrderid
	 */
	public BigInteger getOmwShippingOrderid() {
		return omwShippingOrderid;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param extDeviceId
	 *            the extDeviceId to set
	 */
	public void setExtDeviceId(String extDeviceId) {
		this.extDeviceId = extDeviceId;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param macAddress
	 *            the macAddress to set
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	

	/**
	 * @return the createdDate
	 */
	public Timestamp  getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Timestamp  getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp  createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Timestamp  modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @param omwShippingOrderid
	 *            the omwShippingOrderid to set
	 */
	public void setOmwShippingOrderid(BigInteger omwShippingOrderid) {
		this.omwShippingOrderid = omwShippingOrderid;
	}

	/**
	 * @return the omwShippingOrder
	 */
	public OmwShippingOrder getOmwShippingOrder() {
		return omwShippingOrder;
	}

	/**
	 * @param omwShippingOrder the omwShippingOrder to set
	 */
	public void setOmwShippingOrder(OmwShippingOrder omwShippingOrder) {
		this.omwShippingOrder = omwShippingOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
