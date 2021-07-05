package com.pkglobal.model;

import java.util.Objects;

public class AddressProducerRequest {

    private String addressLine1 = null;

    private String addressLine2 = null;

    private String street = null;

    private String postalCode = null;

    public AddressProducerRequest addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    /**
     * User's first line address.
     * 
     * @return addressLine1
     **/
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public AddressProducerRequest addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    /**
     * User's Second line address.
     * 
     * @return addressLine2
     **/
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public AddressProducerRequest street(String street) {
        this.street = street;
        return this;
    }

    /**
     * User's street.
     * 
     * @return street
     **/

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public AddressProducerRequest postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * User's postal code of Address
     * 
     * @return postalCode
     **/

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressProducerRequest address = (AddressProducerRequest) o;
        return Objects.equals(this.addressLine1, address.addressLine1)
                && Objects.equals(this.addressLine2, address.addressLine2)
                && Objects.equals(this.street, address.street)
                && Objects.equals(this.postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, street, postalCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Address {\n");

        sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
        sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
        sb.append("    street: ").append(toIndentedString(street)).append("\n");
        sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
