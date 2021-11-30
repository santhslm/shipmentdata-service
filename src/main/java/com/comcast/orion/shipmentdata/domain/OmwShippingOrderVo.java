/**
 * 
 */
package com.comcast.orion.shipmentdata.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author 512833
 *
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
	"id",
	"purchaseOrderNumber",
	"compsOrderID",
	"vendorOrderId",
	"orderStatus",
	"externalTransactionId",
	"createdBy",
	"createdTimeStamp",
	"modifiedTimeStamp",
	"vendorOrderStatus"
})
public class OmwShippingOrderVo {


	@JsonProperty("id")
	@JsonPropertyDescription("")
	private BigInteger id;

	@JsonProperty("purchaseOrderNumber")
	@JsonPropertyDescription("")
	private String purchaseOrderNumber;


	@JsonProperty("compsOrderID")
	@JsonPropertyDescription("")
	private String compsOrderID;


	@JsonProperty("vendorOrderId")
	@JsonPropertyDescription("")
	private String vendorOrderId;

	@JsonProperty("orderStatus")
	@JsonPropertyDescription("")
	private String orderStatus;

	@JsonProperty("externalTransactionId")
	@JsonPropertyDescription("")
	private String externalTransactionId;

	@JsonProperty("createdBy")
	@JsonPropertyDescription("")
	private String createdBy;

	@JsonProperty("createdTimeStamp")
	@JsonPropertyDescription("")
	private Timestamp createdTimeStamp;

	@JsonProperty("modifiedTimeStamp")
	@JsonPropertyDescription("")
	private Timestamp modifiedTimeStamp;

	@JsonProperty("vendorOrderStatus")
	@JsonPropertyDescription("")
	private String vendorOrderStatus;


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
