package com.intland.eurocup.service.id;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

/**
 * Service to generate unique id for each voucher submit. It is used to identify
 * a request during its whole lifecycle.
 */
@Service
public class RequestIdGenerator implements IdGenerator {
  private AtomicLong id = new AtomicLong(0);

  @Override
  public Long getNext() {
    return id.incrementAndGet();
  }
}
