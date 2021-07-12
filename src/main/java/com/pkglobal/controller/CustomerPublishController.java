package com.pkglobal.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkglobal.converter.DefaultMessageRequestConverter;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;
import com.pkglobal.service.CustomerPublishService;

@RestController
@RequestMapping("/customer-publish/v1")
public class CustomerPublishController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPublishController.class);

	@Autowired
	private CustomerPublishService customerPublishService;

	@Autowired
	private DefaultMessageRequestConverter messageRequestMaskConverter;

	@PostMapping(path = "/publish-message")
	public ResponseEntity<MessageResponse> publishService(@Valid @RequestBody MessageRequest messageRequest,
			@RequestHeader("Transaction-Id") String transactionId, @RequestHeader("Activity-Id") String activityId,
			@RequestHeader("Authorization") String authorization) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Transaction-Id", transactionId);
		headers.add("Activity-Id", activityId);

		MessageRequest maskMessageRequest = messageRequestMaskConverter.maskCustomerRequest(messageRequest);
		Message<MessageRequest> message = MessageBuilder.withPayload(maskMessageRequest)
				.setHeader("Transaction-Id", transactionId).setHeader("Activity-Id", activityId).build();
		logger.info("messageRequest : {} ", message);
		long startTime = System.currentTimeMillis();
		MessageResponse response = customerPublishService.publishMessage((MessageRequest) message);
		logger.info("Publisher Service required time:{}", System.currentTimeMillis() - startTime);
		logger.info("MessageResponse:{}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
