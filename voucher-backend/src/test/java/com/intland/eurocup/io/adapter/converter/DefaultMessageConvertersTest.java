package com.intland.eurocup.io.adapter.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.common.jms.model.MessageFromBackend;
import com.intland.eurocup.common.jms.model.MessageFromFrontend;
import com.intland.eurocup.common.model.LotResult;
import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;

/**
 * Redeem voucher: validate, persist and after lot, return result.
 */
@RunWith(SpringRunner.class)
public class DefaultMessageConvertersTest {
  private static final Long ID = 1L;
  private static final LotStatus LOT_STATUS = LotStatus.WINNER;
  private static final LotResult LOT_RESULT = LotResult.LOSER;
  
  private static final Long REQUEST_ID = 1L;
  private static final String EMAIL = "email@domain.com";
  private static final Territory TERRITORY = Territory.GER;
  private static final String VOUCHER = "ABCDEF1234";
  
  @TestConfiguration
  static class DefaultMessageConvertersTestContext {
    @Bean
    public DefaultMessageConverters messageConverters() {
      return new DefaultMessageConverters();
    }
  }  
  
  
  @Autowired
  private DefaultMessageConverters messageConverters;
  
  @Before
  public void setUp() {
    
    
  }
  
  
  @Test
  public void convertShouldConvertToVoucher() {
    // Given
    final MessageFromFrontend frontEnd = create();
    
    // When
    final Voucher voucher = messageConverters.convert(frontEnd); 
    
    // Then
    Assert.assertEquals(EMAIL, voucher.getEmail());
    Assert.assertEquals(TERRITORY, voucher.getTerritory());
    Assert.assertEquals(VOUCHER, voucher.getCode());
    Assert.assertEquals(LotStatus.NO_DRAW, voucher.getLotStatus());
  }
  
  @Test
  public void convertShouldConvertToMessageFromBackend() {
    // Given
    
    // When
    final MessageFromBackend msg = messageConverters.convert(REQUEST_ID, LOT_RESULT); 
    
    // Then
    Assert.assertEquals(REQUEST_ID, msg.getRequestId());
    Assert.assertEquals(LOT_RESULT, msg.getLotResult());
  }
  
  @Test
  public void convertShouldConvertToErrorLotResult() {
    // Given
    
    // When
    final LotResult lotResult = messageConverters.convert(LotStatus.NO_DRAW); 
    
    // Then
    Assert.assertEquals(LotResult.ERROR, lotResult);
  }
  
  @Test
  public void convertShouldConvertToLoserLotResult() {
    // Given
    
    // When
    final LotResult lotResult = messageConverters.convert(LotStatus.LOSER); 
    
    // Then
    Assert.assertEquals(LotResult.LOSER, lotResult);
  }
  
  @Test
  public void convertShouldConvertToWinnerLotResult() {
    // Given
    
    // When
    final LotResult lotResult = messageConverters.convert(LotStatus.WINNER); 
    
    // Then
    Assert.assertEquals(LotResult.WINNER, lotResult);
  }
  
  private MessageFromFrontend create() {
    final MessageFromFrontend msg = new MessageFromFrontend();
    msg.setEmail(EMAIL);
    msg.setRequestId(REQUEST_ID);
    msg.setTerritory(TERRITORY);
    msg.setVoucher(VOUCHER);
    return msg;
  }
  
}
