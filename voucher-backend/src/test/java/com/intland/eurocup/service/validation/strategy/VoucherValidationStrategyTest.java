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
import com.intland.eurocup.service.validation.exception.VoucherAlreadyInUseException;

@RunWith(SpringRunner.class)
public class VoucherValidationStrategyTest {

  @TestConfiguration
  static class VoucherValidationStrategyTestContext {
    @Bean
    public VoucherValidationStrategy validationStrategy() {
      return new VoucherValidationStrategy();
    }
  }

  @Autowired
  private VoucherValidationStrategy voucherValidator;

  @MockBean
  private VoucherRepository voucherRepository;

  @Test
  public void validateShouldReturnSuccessfullyWhenVoucherNotInUse() {
    // Given
    Mockito.when(voucherRepository.findByCode(VoucherTestModel.CODE)).thenReturn(new ArrayList<Voucher>());

    // When
    voucherValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }

  @Test(expected=VoucherAlreadyInUseException.class)
  public void validateShouldThrowExceptionWhenVoucherInUse() {
    // Given
    Mockito.when(voucherRepository.findByCode(VoucherTestModel.CODE)).thenReturn(createArrayListWithVouchers());

    // When
    voucherValidator.validate(VoucherTestModel.createBasicVoucher());

    // Then
  }
  
  private List<Voucher> createArrayListWithVouchers() {
    final List<Voucher> vouchers = new ArrayList<>();
    vouchers.add(VoucherTestModel.createBasicVoucher());
    vouchers.add(VoucherTestModel.createBasicVoucher());
    return vouchers;    
  }

  
}
