package com.intland.eurocup.service.lot;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.model.VoucherTestModel;
import com.intland.eurocup.repository.VoucherRepository;
import com.intland.eurocup.service.lot.strategy.BaseDrawStrategy;

@RunWith(SpringRunner.class)
public class DrawStrategyTest {
  private static final Long ALL_TIME_PRIZE_LIMIT = 5L;
  private static final Long DAILY_PRIZE_LIMIT = 2L;
  private static final Long WINNING_SEQUENCE = 2L;
  

  public class DrawStrategyImpl extends BaseDrawStrategy {
    public DrawStrategyImpl() {
      super(DrawStrategyTest.ALL_TIME_PRIZE_LIMIT, DrawStrategyTest.DAILY_PRIZE_LIMIT, DrawStrategyTest.WINNING_SEQUENCE);
    }

    @Override
    public Territory getType() {
      return Territory.HUN;
    }
  }

  @TestConfiguration
  static class DrawStrategyHungaryTestContext {
    @Bean
    public DrawStrategyTest.DrawStrategyImpl drawStrategy() {
      return new DrawStrategyTest().new DrawStrategyImpl();
    }
  }

  @Autowired
  private DrawStrategyImpl drawStrategy;

  @MockBean
  private VoucherRepository voucherRepository;

  @Test
  public void getTypeShouldReturnTerritoryHungary() {
    // Given

    // When
    final Territory territory = drawStrategy.getType();

    // Then
    Assert.assertEquals(Territory.HUN, territory);
  }

  @Test
  public void drawShouldNotChangeVoucherWhenLotStatusAlreadySet() {
    // Given
    final Voucher voucher = VoucherTestModel.createVoucher(Territory.GER, LotStatus.WINNER);

    // When
    drawStrategy.draw(voucher);

    // Then
    Assert.assertEquals(LotStatus.WINNER, voucher.getLotStatus());
    Mockito.verify(voucherRepository, Mockito.times(0)).countWinners(Matchers.anyString());
  }

  @Test
  public void drawShouldResultLoserWhenAllTimeLimitReached() {
    // Given
    final Voucher voucher = VoucherTestModel.createBasicVoucher(LotStatus.NO_DRAW);
    Mockito.when(voucherRepository.countWinners(VoucherTestModel.TERRITORY.getCode())).thenReturn(ALL_TIME_PRIZE_LIMIT);

    // When
    drawStrategy.draw(voucher);

    // Then
    Assert.assertEquals(LotStatus.LOSER, voucher.getLotStatus());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinners(voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(0)).countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode());
  }

  @Test
  public void drawShouldResultLoserWhenDailyLimitReached() {
    // Given
    final Voucher voucher = VoucherTestModel.createBasicVoucher(LotStatus.NO_DRAW);
    Mockito.when(voucherRepository.countWinners(voucher.getTerritory().getCode())).thenReturn(ALL_TIME_PRIZE_LIMIT - 1);
    Mockito.when(voucherRepository.countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode())).thenReturn(DAILY_PRIZE_LIMIT);

    // When
    drawStrategy.draw(voucher);

    // Then
    Assert.assertEquals(LotStatus.LOSER, voucher.getLotStatus());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinners(voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode());
  }

  @Test
  public void drawShouldResultLoserWhenVoucherSequnceNumberNotWinningSequence() {
    // Given
    final Voucher voucher = VoucherTestModel.createBasicVoucher(1L, LotStatus.NO_DRAW);
    Mockito.when(voucherRepository.countWinners(voucher.getTerritory().getCode())).thenReturn(ALL_TIME_PRIZE_LIMIT-1);
    Mockito.when(voucherRepository.countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode())).thenReturn(DAILY_PRIZE_LIMIT-1);
    Mockito.when(voucherRepository.countVouchersOnDate(voucher.getCreationDate(), voucher.getId(), voucher.getTerritory().getCode()))
        .thenReturn(WINNING_SEQUENCE - 1);

    // When
    drawStrategy.draw(voucher);

    // Then
    Assert.assertEquals(LotStatus.LOSER, voucher.getLotStatus());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinners(voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(1)).countVouchersOnDate(voucher.getCreationDate(), voucher.getId(), voucher.getTerritory().getCode());
  }

  @Test
  public void drawShouldResultWinnerWhenVoucherSequnceNumberWinningSequence() {
    // Given
    final Voucher voucher = VoucherTestModel.createBasicVoucher(1L, LotStatus.NO_DRAW);
    Mockito.when(voucherRepository.countWinners(voucher.getTerritory().getCode())).thenReturn(ALL_TIME_PRIZE_LIMIT-1);
    Mockito.when(voucherRepository.countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode())).thenReturn(DAILY_PRIZE_LIMIT-1);
    Mockito.when(voucherRepository.countVouchersOnDate(voucher.getCreationDate(), voucher.getId(), voucher.getTerritory().getCode()))
        .thenReturn(WINNING_SEQUENCE);

    // When
    drawStrategy.draw(voucher);

    // Then
    Assert.assertEquals(LotStatus.WINNER, voucher.getLotStatus());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinners(voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(1)).countWinnersOnDate(voucher.getCreationDate(), voucher.getTerritory().getCode());
    Mockito.verify(voucherRepository, Mockito.times(1)).countVouchersOnDate(voucher.getCreationDate(), voucher.getId(), voucher.getTerritory().getCode());
  }
}
