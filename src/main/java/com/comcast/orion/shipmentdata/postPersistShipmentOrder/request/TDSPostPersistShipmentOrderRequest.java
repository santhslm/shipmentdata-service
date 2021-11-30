
package com.comcast.orion.shipmentdata.postPersistShipmentOrder.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Root Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "shipmentOrder"
})
public class TDSPostPersistShipmentOrderRequest {

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private ShipmentOrder shipmentOrder;

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    public ShipmentOrder getShipmentOrder() {
        return shipmentOrder;
    }

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    public void setShipmentOrder(ShipmentOrder shipmentOrder) {
        this.shipmentOrder = shipmentOrder;
    }

    public TDSPostPersistShipmentOrderRequest withShipmentOrder(ShipmentOrder shipmentOrder) {
        this.shipmentOrder = shipmentOrder;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(shipmentOrder).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TDSPostPersistShipmentOrderRequest) == false) {
            return false;
        }
        TDSPostPersistShipmentOrderRequest rhs = ((TDSPostPersistShipmentOrderRequest) other);
        return new EqualsBuilder().append(shipmentOrder, rhs.shipmentOrder).isEquals();
    }

}
