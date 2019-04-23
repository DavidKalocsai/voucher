package com.intland.eurocup.controller.exception;

import org.springframework.web.servlet.ModelAndView;

import com.intland.eurocup.controller.exception.DefaultErrorModelViewFactory.ErrorModelViewType;

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
