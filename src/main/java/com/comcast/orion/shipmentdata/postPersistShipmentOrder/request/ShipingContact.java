
package com.comcast.orion.shipmentdata.postPersistShipmentOrder.request;

import java.math.BigInteger;

import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "id",
    "company",
    "firstName",
    "lastName",
    "phone"
})
public class ShipingContact {

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private BigInteger id = null;
    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String company = "";
    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String firstName = "";
    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String lastName = "";
    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String phone = "";

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public void setId(BigInteger id) {
        this.id = id;
    }

    public ShipingContact withId(BigInteger id) {
        this.id = id;
        return this;
    }

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    public ShipingContact withCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ShipingContact withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ShipingContact withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ShipingContact withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(company).append(firstName).append(lastName).append(phone).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipingContact) == false) {
            return false;
        }
        ShipingContact rhs = ((ShipingContact) other);
        return new EqualsBuilder().append(id, rhs.id).append(company, rhs.company).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(phone, rhs.phone).isEquals();
    }

}
