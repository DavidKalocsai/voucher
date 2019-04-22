package com.intland.eurocup.controller.exception;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.intland.eurocup.controller.exception.DefaultErrorModelViewFactory.ErrorModelViewType;

import lombok.extern.log4j.Log4j;

/**
 * Model View Factory to build error views for given issues.
 */
public interface ErrorModelViewFactory {
  /**
   * Get {@link ModelAndView} of given {@link ErrorModelViewType}.
   * 
   * @param type {@link ErrorModelViewType}
   * @return {@link ModelAndView}
   */
  ModelAndView getModelView(ErrorModelViewType type);
 }
