/**
 * 
 */
package com.comcast.orion.shipmentdata.dao.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author 512833
 *
 */

@Entity
@Table(name = "OMW_SHIPPING_ORDER_ADDT_ATTR")
public class OmwShippingOrderAddtAttr {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "name")
	private String name;

	@Column(name = "value")
	private String value;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
