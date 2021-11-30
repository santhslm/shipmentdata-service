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
@Table(name = "OMW_SHIPPING_ADDRESS_CONTACT")
public class OmwShippingAddressContact {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "OMW_SHIPPING_ADDRESS_id", insertable = false, updatable = false)
	private BigInteger omwShippingAddressOrderid;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "OMW_SHIPPING_ADDRESS_ID")
	//@Fetch(FetchMode.JOIN)
    private OmwShippingAddress omwShippingAddress;
	
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the omwShippingAddressOrderid
	 */
	public BigInteger getOmwShippingAddressOrderid() {
		return omwShippingAddressOrderid;
	}

	/**
	 * @param omwShippingAddressOrderid the omwShippingAddressOrderid to set
	 */
	public void setOmwShippingAddressOrderid(BigInteger omwShippingAddressOrderid) {
		this.omwShippingAddressOrderid = omwShippingAddressOrderid;
	}

	/**
	 * @return the omwShippingAddress
	 */
	public OmwShippingAddress getOmwShippingAddress() {
		return omwShippingAddress;
	}

	/**
	 * @param omwShippingAddress the omwShippingAddress to set
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
