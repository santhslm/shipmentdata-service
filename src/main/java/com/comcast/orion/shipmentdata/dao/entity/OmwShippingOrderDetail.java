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
@Table(name = "OMW_SHIPPING_ORDER_DETAIL")
public class OmwShippingOrderDetail {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "SHIPPING_METHOD")
	private String shippingMethod;

	@Column(name = "SHIPPING_SYSTEM")
	private String shippingSystem;

	@Column(name = "PRODUCT_TYPE")
	private String productType;

	@Column(name = "SPECIAL_INSTRUCTIONS")
	private String specialInstructions;

	@Column(name = "SHIPPING_OPTION")
	private String shippingOption;

	@Column(name = "SHIPPER")
	private String shipper;

	@Column(name = "SIGNATURE_REQUIRED")
	private String signatureRequired;

	@Column(name = "OMW_SHIPPING_ORDER_id", insertable = false, updatable = false)
	private BigInteger omwShippingOrderid;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @return the shippingSystem
	 */
	public String getShippingSystem() {
		return shippingSystem;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @return the specialInstructions
	 */
	public String getSpecialInstructions() {
		return specialInstructions;
	}

	/**
	 * @return the shippingOption
	 */
	public String getShippingOption() {
		return shippingOption;
	}

	/**
	 * @return the shipper
	 */
	public String getShipper() {
		return shipper;
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
	 * @param shippingMethod
	 *            the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @param shippingSystem
	 *            the shippingSystem to set
	 */
	public void setShippingSystem(String shippingSystem) {
		this.shippingSystem = shippingSystem;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @param specialInstructions
	 *            the specialInstructions to set
	 */
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	/**
	 * @param shippingOption
	 *            the shippingOption to set
	 */
	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}

	/**
	 * @param shipper
	 *            the shipper to set
	 */
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	/**
	 * @return the signatureRequired
	 */
	public String getSignatureRequired() {
		return signatureRequired;
	}

	/**
	 * @param signatureRequired the signatureRequired to set
	 */
	public void setSignatureRequired(String signatureRequired) {
		this.signatureRequired = signatureRequired;
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
