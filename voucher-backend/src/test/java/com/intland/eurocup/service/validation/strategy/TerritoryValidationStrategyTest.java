package com.intland.eurocup.service.validation.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.VoucherTestModel;
import com.intland.eurocup.service.lot.DrawStrategies;
import com.intland.eurocup.service.lot.exception.UnsupportedTerritoryException;

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
    Mockito.when(drawStrategies.isStrategyExist(VoucherTestModel.TERRITORY)).thenReturn(true);

    // When
    territoryValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }

  @Test(expected=UnsupportedTerritoryException.class)
  public void validateShouldThrowExceptionWhenNoStrategyExistForTerritory() {
    // Given
    Mockito.when(drawStrategies.isStrategyExist(VoucherTestModel.TERRITORY)).thenReturn(false);

    // When
    territoryValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }
}
