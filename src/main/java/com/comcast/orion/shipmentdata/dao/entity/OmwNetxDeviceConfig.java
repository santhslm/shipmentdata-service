/**
 * 
 */
package com.comcast.orion.shipmentdata.dao.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author 512833
 *
 */

@Entity
@Table(name = "OMW_NETX_DEVICE_CONFIG")
public class OmwNetxDeviceConfig {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;
	
	 @Column(name = "DEVICE_MAKE")
	 private String deviceMake;
		
	 @Column(name = "DEVICE_TYPE")
	 private String deviceType;
	    
	 @Column(name = "DEVICE_MODEL")
	 private String deviceModel;
	   
	 @Column(name = "DEVICE_RETIRED")
	 private String deviceRetired;
	 
	 @Column(name = "DEVICE_NAME")
	 private String deviceName;

	 @Column(name = "REQ_WAN_LICENSE_COUNT")
	 private Integer requiredWANLicenseCount;
	 
	 @Column(name = "MAX_WAN_LICENSE_COUNT")
	 private Integer currentWANLicenseCount;

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the deviceMake
	 */
	public String getDeviceMake() {
		return deviceMake;
	}

	/**
	 * @param deviceMake the deviceMake to set
	 */
	public void setDeviceMake(String deviceMake) {
		this.deviceMake = deviceMake;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceModel
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * @param deviceModel the deviceModel to set
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * @return the deviceRetired
	 */
	public String getDeviceRetired() {
		return deviceRetired;
	}

	/**
	 * @param deviceRetired the deviceRetired to set
	 */
	public void setDeviceRetired(String deviceRetired) {
		this.deviceRetired = deviceRetired;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	 
	 

}
