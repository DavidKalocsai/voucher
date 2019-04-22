package com.intland.eurocup.io.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.intland.eurocup.common.jms.model.MessageFromFrontend;

/**
 * JMS Sender handles outgoing messages.
 */
@Component
@Profile("default")
public class JmsSender {
	Logger logger = LoggerFactory.getLogger(JmsSender.class);
	
	@Value("${jms.queue.from.ui.name}")
	private String queueName;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/**
	 * Sends message to destination.
	 * @param message {@link MessageFromFrontend}
	 */
	public void send(final MessageFromFrontend message) {
			logger.info("Frontend sends - " + message);
			jmsTemplate.convertAndSend(queueName, message);
	}
}
