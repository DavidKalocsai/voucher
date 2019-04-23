package com.intland.eurocup.service.validation.strategy;

import com.intland.eurocup.model.Voucher;

public interface ValidationStrategy {
  void validate(final Voucher voucher);
}
