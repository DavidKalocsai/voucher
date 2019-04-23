package com.intland.eurocup.service.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.model.VoucherTest;
import com.intland.eurocup.repository.VoucherRepository;
import com.intland.eurocup.service.lot.strategy.DrawStrategies;
import com.intland.eurocup.service.validation.strategy.EmailValidationStrategy;
import com.intland.eurocup.service.validation.strategy.TerritoryValidationStrategy;
import com.intland.eurocup.service.validation.strategy.VoucherValidationStrategy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationStrategiesTest.ValidationStrategiesTestContext.class)
public class ValidationStrategiesTest {

  @Configuration
  @ComponentScan(basePackages = "com.intland.eurocup.service.validation")
  public static class ValidationStrategiesTestContext {
    
  }

  @Autowired
  private DefaultValidationStrategies validationStrategies;

  @SpyBean
  private EmailValidationStrategy emailValidationStrategy;
  
  @Autowired
  private TerritoryValidationStrategy territoryValidationStrategy;
  
  @Autowired
  private VoucherValidationStrategy voucherValidationStrategy;
  
  @MockBean
  private VoucherRepository voucherRepository;
  
  @MockBean
  private DrawStrategies drawStrategies;
  
  @Test
  public void validateShouldCallAllValidateImplementations() {
    // Given
    final Voucher voucher = VoucherTest.createBasicVoucher();
    
    // When
    validationStrategies.validate(voucher);
    
    // Then
    Mockito.verify(emailValidationStrategy, Mockito.times(1)).validate(voucher);
    Mockito.verify(voucherValidationStrategy, Mockito.times(1)).validate(voucher);
    Mockito.verify(territoryValidationStrategy, Mockito.times(1)).validate(voucher);
  }
}
