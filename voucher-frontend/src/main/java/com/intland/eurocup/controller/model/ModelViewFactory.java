
package com.intland.eurocup.controller.model;

import org.springframework.web.servlet.ModelAndView;

import com.intland.eurocup.controller.model.DefaultModelViewFactory.ModelViewType;
import com.intland.eurocup.model.Voucher;

/**
 * Model View Factory to build views for given page.
 */
public interface ModelViewFactory {

  /**
   * Get {@link ModelAndView} of given {@link ModelViewType}.
   * 
   * @param type    {@link ModelViewType} - type of the view
   * @param voucher {@link Voucher} - voucher is used before (submit form) and
   *                after submit (status form).
   * @return {@link ModelAndView}
   */
  ModelAndView getModelView(ModelViewType type, Voucher voucher);
}
