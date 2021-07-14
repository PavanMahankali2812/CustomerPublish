package com.pkglobal.service;

import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pkglobal.converter.ProducerConverter;
import com.pkglobal.exceptions.ProducerServiceException;
import com.pkglobal.model.MessageProducerRequest;
import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;

@Service
public class DefaultCustomerPublishService implements CustomerPublishService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerPublishService.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	@Autowired
	private ProducerConverter<MessageRequest, MessageProducerRequest> messageRequestMaskConverter;

	@Value("${cloudkarafka.topic}")
	private String kafkaTopic;

	@Override
	public MessageResponse publishMessage(MessageRequest messageRequest) {
		try {
			MessageProducerRequest maskMessageRequest = messageRequestMaskConverter.convert(messageRequest);
			logger.info("MessageResponse:{}", maskMessageRequest);
			logger.info("Start to Publish message and message is {}", maskMessageRequest);
			kafkaTemplate.send(kafkaTopic, messageRequest);
			logger.info("End to Publish message and message is {}", maskMessageRequest);
		} catch (TimeoutException ex) {
			throw new ProducerServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
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
