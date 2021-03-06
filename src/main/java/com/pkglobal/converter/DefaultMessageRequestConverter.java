package com.pkglobal.converter;

import org.springframework.stereotype.Component;

import com.pkglobal.constant.PublisherRegexConstants;
import com.pkglobal.model.Address;
import com.pkglobal.model.AddressProducerRequest;
import com.pkglobal.model.MessageProducerRequest;
import com.pkglobal.model.MessageRequest;

@Component
public class DefaultMessageRequestConverter implements ProducerConverter<MessageRequest, MessageProducerRequest> {

	@Override
	public MessageProducerRequest convert(MessageRequest messageRequest) {
		return buildMessageProducerRequest(messageRequest);

	}

	private MessageProducerRequest buildMessageProducerRequest(MessageRequest messageRequest) {
		MessageProducerRequest messageProducerRequest = new MessageProducerRequest();
		messageProducerRequest.setAddress(buildMessageAddressRequest(messageRequest.getAddress()));
		messageProducerRequest.setBirthDate(messageRequest.getBirthDate());
		messageProducerRequest.setCountry(messageRequest.getCountry());
		messageProducerRequest.setCountryCode(messageRequest.getCountryCode());
		messageProducerRequest.setCustomerNumber(messageRequest.getCustomerNumber());
		messageProducerRequest.setCustomerStatus(messageRequest.getCustomerStatus());
		messageProducerRequest.setEmail(messageRequest.getEmail());
		messageProducerRequest.setFirstName(messageRequest.getFirstName());
		messageProducerRequest.setLastName(messageRequest.getLastName());
		messageProducerRequest.setMobileNumber(messageRequest.getMobileNumber());
		maskCustomerRequest(messageProducerRequest);
		return messageProducerRequest;
	}

	private AddressProducerRequest buildMessageAddressRequest(Address address) {
		AddressProducerRequest addressProducerRequest = new AddressProducerRequest();
		addressProducerRequest.setAddressLine1(address.getAddressLine1());
		addressProducerRequest.setAddressLine2(address.getAddressLine2());
		addressProducerRequest.setPostalCode(address.getPostalCode());
		addressProducerRequest.setStreet(address.getStreet());
		return addressProducerRequest;
	}

	public MessageProducerRequest maskCustomerRequest(MessageProducerRequest messageProducerRequest) {
		messageProducerRequest.setCustomerNumber(messageProducerRequest.getCustomerNumber().replaceAll(
				(PublisherRegexConstants.CUSTOMER_NUMBER_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		messageProducerRequest.setBirthDate(messageProducerRequest.getBirthDate().replaceAll(
				(PublisherRegexConstants.BIRTH_DATE_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		messageProducerRequest.setEmail(messageProducerRequest.getEmail().replaceAll(
				(PublisherRegexConstants.EMAIL_ID_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		return messageProducerRequest;

	}
}
