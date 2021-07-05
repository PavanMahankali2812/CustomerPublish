package com.pkglobal.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pkglobal.converter.DefaultMessageRequestMaskConverter;
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
	private DefaultMessageRequestMaskConverter messageRequestMaskConverter;

	@PostMapping(path = "/publish-message")
	public ResponseEntity<MessageResponse> publishService(@Valid @RequestBody MessageRequest messageRequest,
			@RequestHeader("Transaction-Id") String transactionId, @RequestHeader("Activity-Id") String activityId,
			@RequestHeader("Authorization") String authorization) {

		MessageRequest maskMessageRequest = messageRequestMaskConverter.convert(messageRequest);
		logger.info("messageRequest : {} ", maskMessageRequest);
		long startTime = System.currentTimeMillis();
		MessageResponse response = customerPublishService.publishMessage(messageRequest);
		logger.info("Publisher Service required time:{}", System.currentTimeMillis() - startTime);
		logger.info("MessageResponse:{}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
