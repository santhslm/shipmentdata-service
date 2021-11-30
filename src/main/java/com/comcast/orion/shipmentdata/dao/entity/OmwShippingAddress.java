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
import javax.persistence.OneToOne;
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
@Table(name = "OMW_SHIPPING_ADDRESS")
public class OmwShippingAddress {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "ADDRESS_LINE_1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE_2")
	private String addressLine2;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Column(name = "OMW_SHIPPING_ORDER_id", insertable = false, updatable = false)
	private BigInteger omwShippingOrderid;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "OMW_SHIPPING_ORDER_ID")
	//@Fetch(FetchMode.JOIN)
	private OmwShippingOrder omwShippingOrder;
	
	 @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "omwShippingAddress")
	 //@Fetch(FetchMode.JOIN)
	 private OmwShippingAddressContact omwShippingAddressContact;
	
	 
	 
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the omwShippingOrderid
	 */
	/*public BigInteger getOmwShippingOrderid() {
		return omwShippingOrderid;
	}*/

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param omwShippingOrderid
	 *            the omwShippingOrderid to set
	 */
	/*public void setOmwShippingOrderid(BigInteger omwShippingOrderid) {
		this.omwShippingOrderid = omwShippingOrderid;
	}*/

	/**
	 * @return the omwShippingOrder
	 */
	public OmwShippingOrder getOmwShippingOrder() {
		return omwShippingOrder;
	}

	/**
	 * @return the omwShippingAddressContact
	 */
	public OmwShippingAddressContact getOmwShippingAddressContact() {
		return omwShippingAddressContact;
	}

	/**
	 * @param omwShippingOrder the omwShippingOrder to set
	 */
	public void setOmwShippingOrder(OmwShippingOrder omwShippingOrder) {
		this.omwShippingOrder = omwShippingOrder;
	}

	/**
	 * @param omwShippingAddressContact the omwShippingAddressContact to set
	 */
	public void setOmwShippingAddressContact(OmwShippingAddressContact omwShippingAddressContact) {
		this.omwShippingAddressContact = omwShippingAddressContact;
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
