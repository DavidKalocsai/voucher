package com.intland.eurocup.service.validation.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.VoucherTest;
import com.intland.eurocup.service.lot.exception.UnsupportedTerritoryException;
import com.intland.eurocup.service.lot.strategy.DrawStrategies;
import com.intland.eurocup.service.validation.strategy.TerritoryValidationStrategy;

@RunWith(SpringRunner.class)
public class TerritoryValidationStrategyTest {

  @TestConfiguration
  static class TerritoryValidationStrategyTestContext {
    @Bean
    public TerritoryValidationStrategy validationStrategy() {
      return new TerritoryValidationStrategy();
    }
  }

  @Autowired
  private TerritoryValidationStrategy territoryValidator;

  @MockBean
  private DrawStrategies drawStrategies;

  @Test
  public void validateShouldPassWhenStrategyExistForTerritory() {
    // Given
    Mockito.when(drawStrategies.isStrategyExist(VoucherTest.TERRITORY)).thenReturn(true);

    // When
    territoryValidator.validate(VoucherTest.createBasicVoucher());

    // Then
  }

  @Test(expected=UnsupportedTerritoryException.class)
  public void validateShouldThrowExceptionWhenNoStrategyExistForTerritory() {
    // Given
    Mockito.when(drawStrategies.isStrategyExist(VoucherTest.TERRITORY)).thenReturn(false);

    // When
    territoryValidator.validate(VoucherTest.createBasicVoucher());

    // Then
  }
}
