package com.intland.eurocup.io.jms.adapter;

import org.springframework.stereotype.Service;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.common.jms.model.MessageFromFrontend;
import com.intland.eurocup.common.model.LotResult;
import com.intland.eurocup.model.Response;
import com.intland.eurocup.model.ResponseStatus;
import com.intland.eurocup.model.Voucher;

/**
 * Converts between JMS Messages and Voucher/Response. 
 */
@Service
public class MessageConverter {
	
	/**
	 * Convert incoming message to repsonse.
	 * @param message {@link MessageFromBackend}
	 * @return {@link Response}
	 */
	public Response convert(final MessageFromBackend message) {
		Response response;
		final LotResult result = message.getLotResult();
		if (result == LotResult.WINNER || result == LotResult.LOSER) {
			response = new Response(ResponseStatus.OK, result.getDescription());
		} else {
			response = new Response(ResponseStatus.ERROR, result.getDescription());
		}
		return response;
	}
	
	/**
	 * Converts voucher to outgoing message.
	 * @param voucher {@link Voucher}
	 * @return {@link MessageFromFrontend}
	 */
	public MessageFromFrontend convert(final Voucher voucher) {
		final MessageFromFrontend message = new MessageFromFrontend();
		message.setRequestId(voucher.getId());
		message.setEmail(voucher.getEmail());
		message.setVoucher(voucher.getCode());
		message.setTerritory(voucher.getTerritory());
		return message;
	}
}
