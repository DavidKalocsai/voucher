package com.intland.eurocup.controller.exception;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

/**
 * Model View Factory to build error views for given issues.
 */
@Service
@Log4j
public class ErrorModelViewFactory {
  private static final String MSG_KEY = "msg";
  private static final String ERROR_VIEW = "error";
  private static final String UNKNOWN_TERRITORY = "Invalid Path: Territory is unsupported!";
  private static final String UNKNOWN_ISSUE = "Sorry! Please try it later!";
  private static final String UNSUPPORTED_VIEW = "Sorry! View is unsupported!";

  /**
   * Get {@link ModelAndView} of given {@link ErrorModelViewType}.
   * 
   * @param type {@link ErrorModelViewType}
   * @return {@link ModelAndView}
   */
  public ModelAndView getModelView(final ErrorModelViewType type) {
    final ModelAndView modelView;
    if (type == ErrorModelViewType.UNKNOWN_TERRITORY) {
      modelView = getErrorModelAndView(UNKNOWN_TERRITORY);
    } else if (type == ErrorModelViewType.UNSUPPORTED_VIEW) {
      modelView = getErrorModelAndView(UNSUPPORTED_VIEW);
    } else {
      modelView = getErrorModelAndView(UNKNOWN_ISSUE);
    }
    log.info(modelView);
    return modelView;
  }

  private ModelAndView getErrorModelAndView(final String message) {
    final ModelAndView model = new ModelAndView(ERROR_VIEW);
    model.addObject(MSG_KEY, message);
    return model;
  }

  /**
   * Types supported by ErrorModelViewFactory.
   */
  public enum ErrorModelViewType {
    UNKNOWN_TERRITORY, UNSUPPORTED_VIEW, OTHER
  }
}
