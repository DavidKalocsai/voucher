package com.intland.eurocup.service.lot.strategy;

import org.springframework.stereotype.Service;

import com.intland.eurocup.common.model.Territory;

/**
 * Draw strategy for Hungary.
 */
@Service
public class DrawStrategyHungary extends BaseDrawStrategy {
  
  private static final Long ALL_TIME_PRIZE_LIMIT = 5000L;
  private static final Long DAILY_PRIZE_LIMIT = 100L;
  private static final Long WINNING_SEQUENCE = 80L;

  public DrawStrategyHungary() {
    super(ALL_TIME_PRIZE_LIMIT, DAILY_PRIZE_LIMIT, WINNING_SEQUENCE);
  }

  @Override
  public Territory getType() {
    return Territory.HUN;
  }
}
