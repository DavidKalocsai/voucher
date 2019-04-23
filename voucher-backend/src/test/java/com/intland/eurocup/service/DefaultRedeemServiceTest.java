package com.intland.eurocup.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.model.VoucherTest;
import com.intland.eurocup.service.lot.LotService;
import com.intland.eurocup.service.persist.PersistentService;
import com.intland.eurocup.service.validation.ValidationStrategies;

/**
 * Redeem voucher: validate, persist and after lot, return result.
 */
  @RunWith(SpringRunner.class)
  public class DefaultRedeemServiceTest {
    private static final Voucher INCOMING_VOUCHER = VoucherTest.createBasicVoucher();
    private static final Voucher PREVIOUSLY_SAVED_VOUCHER = VoucherTest.createBasicVoucher();
    private static final Voucher NEWLY_PERSISTED_VOUCHER = VoucherTest.createBasicVoucher();
    private static final LotStatus LOT_STATUS_PERSISTED = LotStatus.WINNER;
    private static final LotStatus LOT_STATUS_NEW = LotStatus.LOSER;
    
    @TestConfiguration
    static class DefaultRedeemServiceTestContext {
      @Bean
      public DefaultRedeemService redeemSevice() {
        return new DefaultRedeemService();
      }
    }  
    
    
    @Autowired
    private DefaultRedeemService redeemService;
    
    @MockBean
    private PersistentService persistantService;
  
    @MockBean
    private ValidationStrategies validationService;
  
    @MockBean
    private LotService lotService;
    
    @Before
    public void setUp() {
      
      
    }
    
    
    @Test
  public void redeemShouldSkipValidateAndPersistIfVoucherAlreadyPresisted() {
    // Given
    Mockito.when(persistantService.get(INCOMING_VOUCHER)).thenReturn(PREVIOUSLY_SAVED_VOUCHER);
    Mockito.when(lotService.lot(PREVIOUSLY_SAVED_VOUCHER)).thenReturn(LOT_STATUS_PERSISTED);
    
    // When
    final LotStatus lotStatus = redeemService.redeem(INCOMING_VOUCHER); 
    
    // Then
    Assert.assertEquals(LOT_STATUS_PERSISTED, lotStatus);
    Mockito.verify(validationService, Mockito.times(0)).validate(INCOMING_VOUCHER);
    Mockito.verify(persistantService, Mockito.times(0)).save(INCOMING_VOUCHER);
  }
  
  @Test
  public void redeemShouldValideteAndPersistIfVoucherNew() {
    // Given
    Mockito.when(persistantService.get(INCOMING_VOUCHER)).thenReturn(null);
    Mockito.when(persistantService.save(INCOMING_VOUCHER)).thenReturn(NEWLY_PERSISTED_VOUCHER);
    Mockito.when(lotService.lot(NEWLY_PERSISTED_VOUCHER)).thenReturn(LOT_STATUS_NEW);
    
    // When
    final LotStatus lotStatus = redeemService.redeem(INCOMING_VOUCHER); 
    
    // Then
    Assert.assertEquals(LOT_STATUS_NEW, lotStatus);
    Mockito.verify(validationService, Mockito.times(1)).validate(INCOMING_VOUCHER);
    Mockito.verify(persistantService, Mockito.times(1)).save(INCOMING_VOUCHER);
  }
  
  /*
  public LotStatus redeem(final Voucher voucher) {
    Voucher persistedVoucher = persistantService.get(voucher);
    if (persistedVoucher == null) {
      validationService.validate(voucher);
      persistedVoucher = persistantService.save(voucher);
    }
    logger.info("Voucher persisted: " + persistedVoucher);
    return lotService.lot(persistedVoucher);
  }*/
}
