package com.intland.eurocup.io;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.common.jms.model.MessageFromFrontend;

/**
 * Interface to receive messages from input and send messages to output.
 */
public interface IoService {
  /**
   * Event handler of 'message arrived' event.
   * 
   * @param message - {@link MessageFromBackend}
   */
  void receiveMessage(MessageFromFrontend message);

  /**
   * Send result to output.
   * 
   * @param message {@link MessageFromBackend}
   */
  void send(MessageFromBackend message);
}
