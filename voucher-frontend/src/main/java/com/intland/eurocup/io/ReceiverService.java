package com.intland.eurocup.io;

import com.intland.eurocup.common.jms.model.MessageFromBackend;

/**
 * Interface to connect to back end - receive incoming messages.
 *
 */
public interface ReceiverService {
  /**
   * Save incoming message.
   * 
   * @param message {@link MessageFromBackend}
   */
  void persist(MessageFromBackend message);
}
