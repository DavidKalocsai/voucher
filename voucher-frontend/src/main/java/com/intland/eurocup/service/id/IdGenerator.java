package com.intland.eurocup.service.id;

/**
 * Service to generate unique id for each voucher submit. It is used to identify
 * a request during its whole lifecycle.
 */
public interface IdGenerator {

  /**
   * Get next id.
   * 
   * @return next id.
   */
  Long getNext();
}
