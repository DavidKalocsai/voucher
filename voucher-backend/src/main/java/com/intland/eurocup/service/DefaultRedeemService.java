package com.intland.eurocup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.service.lot.LotService;
import com.intland.eurocup.service.persist.PersistentService;
import com.intland.eurocup.service.validation.ValidationStrategies;

/**
 * Redeem voucher: validate, persist and after lot, return result.
 */
@Service
public class DefaultRedeemService implements RedeemService {
  private Logger logger = LoggerFactory.getLogger(DefaultRedeemService.class);

  @Autowired
  private PersistentService persistantService;

  @Autowired
  private ValidationStrategies validationService;

  @Autowired
  private LotService lotService;

  @Override
  public LotStatus redeem(final Voucher voucher) {
    Voucher persistedVoucher = persistantService.get(voucher);
    if (persistedVoucher == null) {
      validationService.validate(voucher);
      persistedVoucher = persistantService.save(voucher);
    }
    logger.info("Voucher persisted: " + persistedVoucher);
    return lotService.lot(persistedVoucher);
  }
}
