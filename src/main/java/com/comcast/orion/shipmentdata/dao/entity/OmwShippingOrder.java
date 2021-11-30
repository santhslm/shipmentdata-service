/**
 * 
 */
package com.comcast.orion.shipmentdata.dao.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * @author 512833
 *
 */

@Entity
@DynamicUpdate
@Table(name = "OMW_SHIPPING_ORDER")
public class OmwShippingOrder {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "PURCHASE_ORDER_NUMBER")
	private String purchaseOrderNumber;

	@Column(name = "COMPS_ORDER_ID")
	private String compsOrderID;

	@Column(name = "VENDOR_ORDER_ID")
	private String vendorOrderId;

	@Column(name = "ORDER_STATUS")
	private String orderStatus;

	@Column(name = "EXTERNAL_TRANSACTION_ID", updatable = false)
	private String externalTransactionId;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_TIMESTAMP", updatable = false)
	@CreationTimestamp
	private Timestamp createdTimeStamp;

	
	@Column(name = "MODIFIED_TIMESTAMP",insertable=false, updatable = true)
	@UpdateTimestamp
	private Timestamp modifiedTimeStamp;
	
	@Column(name = "VENDOR_ORDER_STATUS")
	private String vendorOrderStatus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "omwShippingOrder", cascade = CascadeType.ALL)
	//@Fetch(FetchMode.JOIN)
	private Set<OmwShippingTrackingDetail> omwShippingTrackingDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "omwShippingOrder", cascade = CascadeType.ALL)
	//@Fetch(FetchMode.SELECT)
	private Set<OmwShippingDeviceDetail> omwShippingDeviceDetail;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "omwShippingOrder", cascade = CascadeType.ALL)
	//@Fetch(FetchMode.JOIN)
	private Set<OmwShippingOrderAddtAttr> omwShippingOrderAddtAttr;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "omwShippingOrder")
	//@Fetch(FetchMode.JOIN)
	private OmwShippingOrderDetail omwShippingOrderDetail;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "omwShippingOrder")
	//@Fetch(FetchMode.JOIN)
	private OmwShippingAddress omwShippingAddress;

	//@PrePersist
	//protected void preInsert() {
		//createdTimeStamp = new Timestamp(System.currentTimeMillis());
		//modifiedTimeStamp = new Timestamp(System.currentTimeMillis());
	//}

	/**
	 * @return the createdTimeStamp
	 */
	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	
	
	
	public String getVendorOrderStatus() {
		return vendorOrderStatus;
	}

	public void setVendorOrderStatus(String vendorOrderStatus) {
		this.vendorOrderStatus = vendorOrderStatus;
	}

	/**
	 * @return the modifiedTimeStamp
	 */
	public Timestamp getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	/**
	 * @param createdTimeStamp
	 *            the createdTimeStamp to set
	 */
	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	/**
	 * @param modifiedTimeStamp
	 *            the modifiedTimeStamp to set
	 */
	public void setModifiedTimeStamp(Timestamp modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @return the purchaseOrderNumber
	 */
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	/**
	 * @return the compsOrderID
	 */
	public String getCompsOrderID() {
		return compsOrderID;
	}

	/**
	 * @return the vendorOrderId
	 */
	public String getVendorOrderId() {
		return vendorOrderId;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @return the externalTransactionId
	 */
	public String getExternalTransactionId() {
		return externalTransactionId;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @return the createdTimeStamp
	 */
	/*
	 * public Timestamp getCreatedTimeStamp() { return createdTimeStamp; }
	 * 
	 * 
	 * 
	 *//**
		 * @return the modifiedTimeStamp
		 *//*
			 * public Timestamp getModifiedTimeStamp() { return modifiedTimeStamp; }
			 */

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @param purchaseOrderNumber
	 *            the purchaseOrderNumber to set
	 */
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	/**
	 * @param compsOrderID
	 *            the compsOrderID to set
	 */
	public void setCompsOrderID(String compsOrderID) {
		this.compsOrderID = compsOrderID;
	}

	/**
	 * @param vendorOrderId
	 *            the vendorOrderId to set
	 */
	public void setVendorOrderId(String vendorOrderId) {
		this.vendorOrderId = vendorOrderId;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @param externalTransactionId
	 *            the externalTransactionId to set
	 */
	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @param createdTimeStamp
	 *            the createdTimeStamp to set
	 */
	/*
	 * public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
	 * this.createdTimeStamp = createdTimeStamp; }
	 * 
	 * 
	 * 
	 *//**
		 * @param modifiedTimeStamp
		 *            the modifiedTimeStamp to set
		 *//*
			 * public void setModifiedTimeStamp(Timestamp modifiedTimeStamp) {
			 * this.modifiedTimeStamp = modifiedTimeStamp; }
			 */

	/**
	 * @return the omwShippingTrackingDetails
	 */
	public Set<OmwShippingTrackingDetail> getOmwShippingTrackingDetails() {
		return omwShippingTrackingDetails;
	}

	/**
	 * @return the omwShippingDeviceDetail
	 */
	public Set<OmwShippingDeviceDetail> getOmwShippingDeviceDetail() {
		return omwShippingDeviceDetail;
	}

	/**
	 * @return the omwShippingOrderAddtAttr
	 */
	public Set<OmwShippingOrderAddtAttr> getOmwShippingOrderAddtAttr() {
		return omwShippingOrderAddtAttr;
	}

	/**
	 * @return the omwShippingOrderDetail
	 */
	public OmwShippingOrderDetail getOmwShippingOrderDetail() {
		return omwShippingOrderDetail;
	}

	/**
	 * @return the omwShippingAddress
	 */
	public OmwShippingAddress getOmwShippingAddress() {
		return omwShippingAddress;
	}

	/**
	 * @param omwShippingTrackingDetails
	 *            the omwShippingTrackingDetails to set
	 */
	public void setOmwShippingTrackingDetails(Set<OmwShippingTrackingDetail> omwShippingTrackingDetails) {
		this.omwShippingTrackingDetails = omwShippingTrackingDetails;
	}

	/**
	 * @param omwShippingDeviceDetail
	 *            the omwShippingDeviceDetail to set
	 */
	public void setOmwShippingDeviceDetail(Set<OmwShippingDeviceDetail> omwShippingDeviceDetail) {
		this.omwShippingDeviceDetail = omwShippingDeviceDetail;
	}

	/**
	 * @param omwShippingOrderAddtAttr
	 *            the omwShippingOrderAddtAttr to set
	 */
	public void setOmwShippingOrderAddtAttr(Set<OmwShippingOrderAddtAttr> omwShippingOrderAddtAttr) {
		this.omwShippingOrderAddtAttr = omwShippingOrderAddtAttr;
	}

	/**
	 * @param omwShippingOrderDetail
	 *            the omwShippingOrderDetail to set
	 */
	public void setOmwShippingOrderDetail(OmwShippingOrderDetail omwShippingOrderDetail) {
		this.omwShippingOrderDetail = omwShippingOrderDetail;
	}

	/**
	 * @param omwShippingAddress
	 *            the omwShippingAddress to set
	 */
	public void setOmwShippingAddress(OmwShippingAddress omwShippingAddress) {
		this.omwShippingAddress = omwShippingAddress;
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
