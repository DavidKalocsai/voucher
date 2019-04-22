package com.intland.eurocup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.controller.model.ModelViewFactory;
import com.intland.eurocup.controller.model.ModelViewFactory.ModelViewType;
import com.intland.eurocup.controller.response.ResponseStorage;
import com.intland.eurocup.io.SenderService;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.service.converter.TerritoryConverter;
import com.intland.eurocup.service.id.UniqueRequestIdGenerator;

import lombok.extern.log4j.Log4j;

/**
 * Controller to handle status form. Status form displays information of submitted voucher and response status.
 *
 */
@Log4j
@Controller
public class StatusController {
	private static final String STATUS_URL = "{territory}/status";
	private static final String MODEL_ATTRIBUTE = "voucher";
	
	@Autowired
	private UniqueRequestIdGenerator uniqueRequestIdGenerator;

	@Autowired
	private SenderService senderService;
	
	@Autowired 
	private ResponseStorage responseStorage;

	@Autowired
	private ModelViewFactory modelViewFactory;

	/**
	 * Add new custom converter, that converts string to {@link Territory}. 
	 * @param webdataBinder {@link WebDataBinder}
	 */
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(Territory.class, new TerritoryConverter());
	}
	
	/**
	 * Show status form.
	 * @param territory {@link Territory} - fetched from path
	 * @param voucher {@link Voucher} - submitted by submit form
	 * @return model and view of status form.
	 */
	@PostMapping(value = STATUS_URL)
	public ModelAndView showStatus(@PathVariable Territory territory,
			@ModelAttribute(MODEL_ATTRIBUTE) Voucher voucher) {
		final ModelAndView model = modelViewFactory.getModelView(ModelViewType.STATUS_VIEW, setUpRequestId(voucher));
		senderService.send(voucher);
		log.info("Voucher submitted: " + voucher);
		return model;
	}
	
	private Voucher setUpRequestId(final Voucher voucher) {
		final Long requestId = uniqueRequestIdGenerator.getNext();
		responseStorage.registerRequestId(requestId);
		voucher.setId(requestId);
		return voucher;
	}
}