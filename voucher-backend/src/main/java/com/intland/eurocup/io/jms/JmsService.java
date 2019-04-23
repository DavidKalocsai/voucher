package com.intland.eurocup.io.jms;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.common.jms.model.MessageFromFrontend;

/**
 * Interface to receive messages from JMS and send messages to JMS.
 */
public interface JmsService {
  /**
   * JMS receiver. It will be called on 'message arrived from JMS' event.
   * 
   * @param message - {@link MessageFromBackend}
   */
  void receiveMessage(MessageFromFrontend message);

  /**
   * JMS sender. It will be called after received message (voucher) is saved,
   * drawn. The result of lot will be sent back to JMS.
   * 
   * @param message {@link MessageFromBackend}
   */
  void send(MessageFromBackend message);
}
