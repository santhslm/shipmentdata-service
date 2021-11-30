/**
 * 
 */
package com.comcast.orion.shipmentdata.dao.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author 512833
 *
 */

@Entity
@Table(name = "OMW_SHIPING_TRACKING_DETAIL")
public class OmwShippingTrackingDetail {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "DELIVER_DATE")
	private String deliverDate;

	@Column(name = "DELIVER_LOCATION")
	private String deliverLocation;

	@Column(name = "DELIVER_NAME")
	private String deliverName;

	@Column(name = "SHIP_DATE")
	private String shipDate;

	@Column(name = "TRACKING_NUMBER")
	private String trackingNumber;

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
	 * @return the deliverDate
	 */
	public String getDeliverDate() {
		return deliverDate;
	}

	/**
	 * @return the deliverLocation
	 */
	public String getDeliverLocation() {
		return deliverLocation;
	}

	/**
	 * @return the deliverName
	 */
	public String getDeliverName() {
		return deliverName;
	}

	/**
	 * @return the shipDate
	 */
	public String getShipDate() {
		return shipDate;
	}

	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return trackingNumber;
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
	 * @param deliverDate
	 *            the deliverDate to set
	 */
	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	/**
	 * @param deliverLocation
	 *            the deliverLocation to set
	 */
	public void setDeliverLocation(String deliverLocation) {
		this.deliverLocation = deliverLocation;
	}

	/**
	 * @param deliverName
	 *            the deliverName to set
	 */
	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	/**
	 * @param shipDate
	 *            the shipDate to set
	 */
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	/**
	 * @param trackingNumber
	 *            the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
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
