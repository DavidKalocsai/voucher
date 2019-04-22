package com.intland.eurocup.io.jms.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.controller.response.ResponseStorage;
import com.intland.eurocup.io.ReceiverService;

/**
 * Adapter between JMS and the application. Converts incoming message and saves result to response storage.
 */
@Component
@Profile("default")
public class AdapterReceiverService implements ReceiverService {
	@Autowired
	private ResponseStorage responseStorage;
	
	@Autowired
	private MessageConverter messageConverter;
	
	@Override
	public void persist(MessageFromBackend message) {
		responseStorage.save(message.getRequestId(), messageConverter.convert(message));
	}
}
