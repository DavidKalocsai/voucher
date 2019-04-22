package com.intland.eurocup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.controller.model.ModelViewFactory;
import com.intland.eurocup.controller.model.ModelViewFactory.ModelViewType;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.service.converter.TerritoryConverter;

import lombok.extern.log4j.Log4j;

/**
 * Controller for "submit form" handling.
 */
@Log4j
@Controller
public class FormController {
	private static final String FORM_URL = "{territory}/form";
	
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
	 * Builds and returns model and view of submit form. 
	 * @param territory {@link Territory}
	 * @return
	 */
	@GetMapping(value = FORM_URL)
	public ModelAndView showSubmitForm(@PathVariable Territory territory) {
		log.info("Show SubmitForm territory: " + territory);
		return modelViewFactory.getModelView(ModelViewType.VOUCHER_FORM_VIEW, getDefaultVoucher(territory));
	}
	
	private Voucher getDefaultVoucher(final Territory territory) {
		final Voucher voucher = new Voucher();
		voucher.setCode("ABCDEF1234");
		voucher.setEmail("test@gmail.com");
		voucher.setTerritory(territory);
		return voucher;
	}
}