package com.intland.eurocup.service.lot.strategy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.repository.VoucherRepository;

@RunWith(SpringRunner.class)
public class DrawStrategiesTest {

  @Configuration
  @ComponentScan("com.intland.eurocup.service.lot.strategy")
  static class DrawStrategiesTestContext {
  }

  @Autowired
  private DefaultDrawStrategies drawStrategies;
  
  @MockBean
  private VoucherRepository voucherRepository;

  @Test
  public void validateShouldReturnSuccessfullyWhenEmailNotInUse() {
    for (final Territory territory : Territory.values()) {
      Assert.assertTrue(drawStrategies.isStrategyExist(territory));
    }
  }
}
