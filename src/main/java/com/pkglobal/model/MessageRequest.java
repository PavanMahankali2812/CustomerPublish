package com.pkglobal.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MessageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-02T10:51:52.291Z")

public class MessageRequest {

	@JsonProperty("customerNumber")
	@Size(max = 10, message = "Customer number size should not be greater than 10.")
	@NotEmpty(message = "CustomerNumber should not be empty.")
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String customerNumber = null;

	@JsonProperty("firstName")
	@Size(min = 10, max = 50, message = "Size of first name  is in between 10 to 50.")
	@NotEmpty(message = "firstName should not be empty.")
	private String firstName = null;

	@JsonProperty("lastName")
	@Size(min = 10, max = 50, message = "Size of last name is in between 10 to 50")
	@NotEmpty(message = "lastName should not be empty.")
	private String lastName = null;

	@JsonProperty("birthDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotEmpty(message = "birthDate should not be empty.")
	private String birthDate = null;

	@JsonProperty("country")
	@NotEmpty(message = "country should not be empty.")
	private String country = null;

	@JsonProperty("countryCode")
	@Size(max = 2, message = "Size of Country code is in between 0 to 2")
	@NotEmpty(message = "countryCode should not be empty.")
	private String countryCode = null;

	@JsonProperty("mobileNumber")
	@Size(max = 10)
	@NotEmpty(message = "mobileNumber should not be empty.")
	private String mobileNumber = null;

	@JsonProperty("email")
	@Email(message = "Email should be valid")
	@NotEmpty(message = "email should not be empty.")
	private String email = null;

	@JsonProperty("customerStatus")
	private CustomerStatusEnum customerStatus = null;

	@JsonProperty("address")
	private Address address = null;

	public MessageRequest customerNumber(String customerNumber) {
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

	public MessageRequest firstName(String firstName) {
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

	public MessageRequest lastName(String lastName) {
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

	public MessageRequest birthDate(String birthDate) {
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

	public MessageRequest country(String country) {
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

	public MessageRequest countryCode(String countryCode) {
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

	public MessageRequest mobileNumber(String mobileNumber) {
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

	public MessageRequest email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * User's email
	 * 
	 * @return email
	 **/

	@Size(max = 50, message = "Size of email id is in between 0 to 50")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MessageRequest customerStatus(CustomerStatusEnum customerStatus) {
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

	public MessageRequest address(Address address) {
		this.address = address;
		return this;
	}

	/**
	 * Get address
	 * 
	 * @return address
	 **/
	@Valid
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MessageRequest messageRequest = (MessageRequest) o;
		return Objects.equals(this.customerNumber, messageRequest.customerNumber)
				&& Objects.equals(this.firstName, messageRequest.firstName)
				&& Objects.equals(this.lastName, messageRequest.lastName)
				&& Objects.equals(this.birthDate, messageRequest.birthDate)
				&& Objects.equals(this.country, messageRequest.country)
				&& Objects.equals(this.countryCode, messageRequest.countryCode)
				&& Objects.equals(this.mobileNumber, messageRequest.mobileNumber)
				&& Objects.equals(this.email, messageRequest.email)
				&& Objects.equals(this.customerStatus, messageRequest.customerStatus)
				&& Objects.equals(this.address, messageRequest.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber, firstName, lastName, birthDate, country, countryCode, mobileNumber, email,
				customerStatus, address);
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
		sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
