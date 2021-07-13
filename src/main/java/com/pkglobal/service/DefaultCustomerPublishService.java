package com.pkglobal.service;

import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.pkglobal.exceptions.ApplicationRuntimeException;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;

@Service
public class DefaultCustomerPublishService implements CustomerPublishService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerPublishService.class);

	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${cloudkarafka.topic}")
	private String kafkaTopic;

	public DefaultCustomerPublishService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public MessageResponse publishMessage(MessageRequest maskMessageRequest) {
		try {
			Message<MessageRequest> message = MessageBuilder.withPayload(maskMessageRequest)
					.setHeader("Transaction-Id", "transactionId").setHeader("Activity-Id", "activityId").build();
			logger.info("messageRequest : {} ", message);
			logger.info("Start to Publish message and message is {}", message);
			kafkaTemplate.send(kafkaTopic, message);
			logger.info("End to Publish message and message is {}", message);
		} catch (TimeoutException ex) {
			throw new ApplicationRuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					ex.getMessage());
		}
		return buildMessageResponse();

	}

	private MessageResponse buildMessageResponse() {
		MessageResponse response = new MessageResponse();
		response.setData("Published Message sucessfully");
		response.setStatus("success");
		return response;
	}

}
