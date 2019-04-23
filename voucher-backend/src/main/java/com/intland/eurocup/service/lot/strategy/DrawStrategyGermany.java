package com.intland.eurocup.service.lot.strategy;

import org.springframework.stereotype.Service;

import com.intland.eurocup.common.model.Territory;

/**
 * Draw strategy for Germany.
 */
@Service
public class DrawStrategyGermany extends BaseDrawStrategy {
  private static final Long ALL_TIME_PRIZE_LIMIT = 10000L;
  private static final Long DAILY_PRIZE_LIMIT = 250L;
  private static final Long WINNING_SEQUENCE = 40L;
 
  public DrawStrategyGermany() {
    super(ALL_TIME_PRIZE_LIMIT, DAILY_PRIZE_LIMIT, WINNING_SEQUENCE);
  }

  @Override
  public Territory getType() {
    return Territory.GER;
  }
}
