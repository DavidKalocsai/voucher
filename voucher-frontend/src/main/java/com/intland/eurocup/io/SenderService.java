package com.intland.eurocup.io;

import com.intland.eurocup.model.Voucher;

/**
 * Interface to connect to back end - send outgoing messages. 
 *
 */
public interface SenderService {
	/**
	 * Send voucher to back end application.
	 * @param voucher {@link Voucher} - to be lot.
	 */
	void send(Voucher voucher);
}
