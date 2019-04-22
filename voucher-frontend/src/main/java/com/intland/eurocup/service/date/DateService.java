package com.intland.eurocup.service.date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * Date Service to get current DateTime.
 *
 */
public interface DateService {
  /**
   * Get current date time.
   * @return current date time.
   */
  DateTime getNow();
}
