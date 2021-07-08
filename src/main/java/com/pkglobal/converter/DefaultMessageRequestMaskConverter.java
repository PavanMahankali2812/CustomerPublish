package com.pkglobal.converter;

import org.springframework.stereotype.Component;

import com.pkglobal.constant.PublisherRegexConstants;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.converter.MaskConverter;

@Component
public class DefaultMessageRequestMaskConverter implements MaskConverter<MessageRequest> {

	@Override
	public MessageRequest convert(MessageRequest messageRequest) {

		

		messageRequest.setCustomerNumber(messageRequest.getCustomerNumber().replaceAll(
				(PublisherRegexConstants.CUSTOMER_NUMBER_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		messageRequest.setBirthDate(messageRequest.getBirthDate().replaceAll(
				(PublisherRegexConstants.BIRTH_DATE_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		messageRequest.setEmail(messageRequest.getEmail().replaceAll(
				(PublisherRegexConstants.EMAIL_ID_REGEX_EXPRESSION.getRegexExpression()),
				PublisherRegexConstants.MASK_CONSTANTS.getRegexExpression()));
		return messageRequest;

	}

}
