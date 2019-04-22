package com.intland.eurocup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intland.eurocup.jms.DefaultJmsService;
import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.repository.VoucherRepository;
import com.intland.eurocup.service.lot.LotService;
import com.intland.eurocup.service.persist.PersistentService;
import com.intland.eurocup.service.validation.ValidationStrategies;
import com.intland.eurocup.service.validation.strategy.ValidationStrategy;


/**
 * Redeem voucher: validate, persist and after lot, return result.
 */
@Service
public class DefaultRedeemService implements RedeemService {
	private Logger logger = LoggerFactory.getLogger(DefaultRedeemService.class);
	
	@Autowired
	private VoucherRepository repository;
	
	@Autowired
	private ValidationStrategies validationService;
	
	@Autowired
	private PersistentService persistantService;
	
	@Autowired
	private LotService lotService;
	
	@Override
	public LotStatus redeem(final Voucher voucher) {
		final Voucher persistedVoucher = persistIfAbsent(voucher);		
		return lotService.lot(persistedVoucher);
	}
	
	private Voucher persistIfAbsent(final Voucher voucher) {
		Voucher persistedVoucher = tryToRetriveFromDB(voucher);
		if (persistedVoucher == null) {
			validationService.validate(voucher);
			persistedVoucher = persistantService.save(voucher);
		}	
		logger.info("Voucher persisted: " + persistedVoucher);
		return persistedVoucher;
	}
	
	private Voucher tryToRetriveFromDB(final Voucher voucher) {
		final List<Voucher> vouchers =  repository.findByEmailAndCodeAndTerritory(voucher.getEmail(), voucher.getCode(), voucher.getTerritory());
		return vouchers.isEmpty() ? null : vouchers.get(0);
	}
}
