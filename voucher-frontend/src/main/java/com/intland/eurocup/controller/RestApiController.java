package com.intland.eurocup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intland.eurocup.controller.response.ResponseStorage;
import com.intland.eurocup.model.Response;

import lombok.extern.log4j.Log4j;

/**
 * Rest endpoint that is used to fetch response by the UI asynchronously.  
 *
 */
@Log4j
@RestController
@RequestMapping("/api")
public class RestApiController {
	private static final String URL_FETCH_REPSONSE = "fetchResponse";
	
	@Autowired
	private ResponseStorage responseStorage;

	/**
	 * Fetches current state of the response.
	 * @param responseId id that identifies the response in storage.
	 * @return {@link Response}
	 */
	@GetMapping(value = URL_FETCH_REPSONSE)
	public Response fetchResponse(final Long responseId) {
		log.info("Fetching repsone: " + responseId);
		return responseStorage.getResponse(responseId);
	}
}