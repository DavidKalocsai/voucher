package com.intland.eurocup.io.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.io.ReceiverService;

import lombok.extern.log4j.Log4j;

/**
 * JMS Receiver handles incoming messages.
 */
@Log4j
@Component
@Profile("default")
public class JmsReceiver {
	
	@Autowired
	private ReceiverService receiverService;
	
	/**
	 * It is called on message arrived from JMS to given destination. 
	 * @param message {@link MessageFromBackend}
	 */
	@JmsListener(destination = "${jms.queue.to.ui.name}")
	public void receiveMessage(final MessageFromBackend message) {
		log.info("Received <" + message + ">");
		receiverService.persist(message);
	}
}
