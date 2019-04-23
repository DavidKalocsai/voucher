package com.intland.eurocup.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.intland.eurocup.controller.exception.DefaultErrorModelViewFactory.ErrorModelViewType;
import com.intland.eurocup.controller.model.exception.UnsupportedModelViewTypeException;
import com.intland.eurocup.service.converter.exception.UnkownTerritoryException;

import lombok.extern.log4j.Log4j;

/**
 * Global Exception Handler. It catches exceptions, logs them and converts them
 * into error view.
 *
 */
@Log4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private ErrorModelViewFactory modelViewFactory;

  /**
   * Handler for {@link UnkownTerritoryException}.
   * 
   * @param exception exception stack to be logged
   * @return Specific ModelAndView for the exception.
   */
  @ExceptionHandler(value = UnkownTerritoryException.class)
  public ModelAndView unknownTerritoryHandler(final Exception exception) {
    logException(exception);
    return modelViewFactory.getModelView(ErrorModelViewType.UNKNOWN_TERRITORY);
  }

  /**
   * Handler for {@link UnsupportedModelViewTypeException}.
   * 
   * @param exception exception stack to be logged
   * @return Specific ModelAndView for the exception.
   */
  @ExceptionHandler(value = UnsupportedModelViewTypeException.class)
  public ModelAndView unsupportedModelViewTypeHandler(final Exception exception) {
    logException(exception);
    return modelViewFactory.getModelView(ErrorModelViewType.UNSUPPORTED_VIEW);
  }

  /**
   * Handler for {@link Throwable}.
   * 
   * @param exception exception stack to be logged
   * @return Specific ModelAndView for the exception.
   */
  @ExceptionHandler(value = Throwable.class)
  public ModelAndView defaultThrowableHandler(final Exception exception) throws Exception {
    logException(exception);
    return modelViewFactory.getModelView(ErrorModelViewType.OTHER);
  }

  private void logException(final Exception exception) {
    log.error("Exception handler: " + exception);
  }
}