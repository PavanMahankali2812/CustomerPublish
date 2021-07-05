package com.pkglobal.service;


import com.pkglobal.model.MessageRequest;
import com.pkglobal.model.MessageResponse;


public interface CustomerPublishService {
	public MessageResponse publishMessage(MessageRequest message);
}
