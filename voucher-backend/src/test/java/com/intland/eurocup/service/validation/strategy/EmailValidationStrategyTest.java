package com.intland.eurocup.service.validation.strategy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.model.VoucherTestModel;
import com.intland.eurocup.repository.VoucherRepository;
import com.intland.eurocup.service.validation.exception.EmailAlreadyInUseException;

@RunWith(SpringRunner.class)
public class EmailValidationStrategyTest {

  @TestConfiguration
  static class EmailValidationStrategyTestContext {
    @Bean
    public EmailValidationStrategy validationStrategy() {
      return new EmailValidationStrategy();
    }
  }

  @Autowired
  private EmailValidationStrategy emailValidator;

  @MockBean
  private VoucherRepository voucherRepository;

  @Test
  public void validateShouldReturnSuccessfullyWhenEmailNotInUse() {
    // Given
    Mockito.when(voucherRepository.findByEmail(VoucherTestModel.EMAIL)).thenReturn(new ArrayList<Voucher>());

    // When
    emailValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }

  @Test(expected=EmailAlreadyInUseException.class)
  public void validateShouldThrowExceptionWhenEmailInUse() {
    // Given
    Mockito.when(voucherRepository.findByEmail(VoucherTestModel.EMAIL)).thenReturn(createArrayListWithVouchers());

    // When
    emailValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }
  
  private List<Voucher> createArrayListWithVouchers() {
    final List<Voucher> vouchers = new ArrayList<>();
    vouchers.add(VoucherTestModel.createBasicVoucher());
    vouchers.add(VoucherTestModel.createBasicVoucher());
    return vouchers;    
  }

  
}
