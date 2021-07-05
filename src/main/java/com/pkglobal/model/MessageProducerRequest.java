package com.pkglobal.model;

import java.util.Objects;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;


public class MessageProducerRequest {

	private String customerNumber = null;

	private String firstName = null;

	private String lastName = null;

	private String birthDate = null;

	private String country = null;

	private String countryCode = null;

	private String mobileNumber = null;

	private String email = null;

	private CustomerStatusEnum customerStatus = null;

	private AddressProducerRequest addressProducerRequest = null;

	public MessageProducerRequest customerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}

	/**
	 * The is unique customer number
	 * 
	 * @return customerNumber
	 **/

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public MessageProducerRequest firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * User's first name
	 * 
	 * @return firstName
	 **/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public MessageProducerRequest lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * User's last name
	 * 
	 * @return lastName
	 **/

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public MessageProducerRequest birthDate(String birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	/**
	 * User's birth date
	 * 
	 * @return birthDate
	 **/

	@Valid
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public MessageProducerRequest country(String country) {
		this.country = country;
		return this;
	}

	/**
	 * User's country
	 * 
	 * @return country
	 **/

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public MessageProducerRequest countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * User's country
	 * 
	 * @return countryCode
	 **/
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public MessageProducerRequest mobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	/**
	 * User's mobile Number
	 * 
	 * @return mobileNumber
	 **/

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public MessageProducerRequest email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * User's email
	 * 
	 * @return email
	 **/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MessageProducerRequest customerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
		return this;
	}

	/**
	 * Customer Status
	 * 
	 * @return customerStatus
	 **/

	public CustomerStatusEnum getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerStatusEnum customerStatus) {
		this.customerStatus = customerStatus;
	}

	public MessageProducerRequest address(AddressProducerRequest addressProducerRequest) {
		this.addressProducerRequest = addressProducerRequest;
		return this;
	}

	/**
	 * Get address
	 * 
	 * @return address
	 **/

	public AddressProducerRequest getAddress() {
		return addressProducerRequest;
	}

	public void setAddress(AddressProducerRequest addressProducerRequest) {
		this.addressProducerRequest = addressProducerRequest;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MessageProducerRequest messageRequest = (MessageProducerRequest) o;
		return Objects.equals(this.customerNumber, messageRequest.customerNumber)
				&& Objects.equals(this.firstName, messageRequest.firstName)
				&& Objects.equals(this.lastName, messageRequest.lastName)
				&& Objects.equals(this.birthDate, messageRequest.birthDate)
				&& Objects.equals(this.country, messageRequest.country)
				&& Objects.equals(this.countryCode, messageRequest.countryCode)
				&& Objects.equals(this.mobileNumber, messageRequest.mobileNumber)
				&& Objects.equals(this.email, messageRequest.email)
				&& Objects.equals(this.customerStatus, messageRequest.customerStatus)
				&& Objects.equals(this.addressProducerRequest, messageRequest.addressProducerRequest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber, firstName, lastName, birthDate, country, countryCode, mobileNumber, email,
				customerStatus, addressProducerRequest);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MessageRequest {\n");

		sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
		sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
		sb.append("    address: ").append(toIndentedString(addressProducerRequest)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
