package com.intland.eurocup.service.persist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.repository.VoucherRepository;

/**
 *  PersistentService implementation to save and get vouchers to/from DB.
 */
@Service
public class DefaultPersistentService implements PersistentService {

  @Autowired
  private VoucherRepository repository;

  @Override
  public Voucher save(final Voucher voucher) {
    return repository.save(voucher);
  }
  
  @Override
  public Voucher get(final Voucher voucher) {
    final List<Voucher> vouchers = repository.findByEmailAndCodeAndTerritory(voucher.getEmail(), voucher.getCode(),
        voucher.getTerritory());
    return vouchers.isEmpty() ? null : vouchers.get(0);
  }
}
