package com.intland.eurocup.io.jms.adapter;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.common.jms.model.MessageFromFrontend;
import com.intland.eurocup.model.Response;
import com.intland.eurocup.model.Voucher;

/**
 * Converts between JMS Messages and Voucher/Response.
 */
public interface MessageConverter {

  /**
   * Convert incoming message to repsonse.
   * 
   * @param message {@link MessageFromBackend}
   * @return {@link Response}
   */
  Response convert(MessageFromBackend message);

  /**
   * Converts voucher to outgoing message.
   * 
   * @param voucher {@link Voucher}
   * @return {@link MessageFromFrontend}
   */
  MessageFromFrontend convert(Voucher voucher);
}
