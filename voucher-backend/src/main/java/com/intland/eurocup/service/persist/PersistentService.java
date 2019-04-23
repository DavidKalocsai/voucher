package com.intland.eurocup.service.persist;

import com.intland.eurocup.model.Voucher;

/**
 * Interface to retrieve/persist {@link Voucher} from/to DB.
 */
public interface PersistentService {
  /**
   * Save or update {@link Voucher}.
   * 
   * @param voucher {@link Voucher}
   * @return persisted {@link Voucher}
   */
  Voucher save(Voucher voucher);
  
  /**
   * Fetch {@link Voucher} from DB based on email, territory and code of the voucher.
   * @param voucher {@link Voucher}
   * @return {@link Voucher}
   */
  Voucher get(Voucher voucher);
}
