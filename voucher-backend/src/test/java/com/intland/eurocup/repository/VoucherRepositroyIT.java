package com.intland.eurocup.repository;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.model.LotStatus;
import com.intland.eurocup.model.Voucher;
import com.intland.eurocup.model.VoucherTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VoucherRepositroyIT {
	@Autowired
	private VoucherRepository repository;

	@Test
	public void testFindByEmail() {
		// given
		final Voucher voucher = setupBasicVoucher();

		// when
		Voucher foundVouhcer = repository.findByEmail(VoucherTest.EMAIL).get(0);

		// then
		Assert.assertEquals(voucher.getEmail(), foundVouhcer.getEmail());
	}

	@Test
	public void testFindByCode() {
		// given
		final Voucher voucher = setupBasicVoucher();

		// when
		Voucher foundVouhcer = repository.findByCode(VoucherTest.CODE).get(0);

		// then
		Assert.assertEquals(voucher.getEmail(), foundVouhcer.getEmail());
	}

	@Test
	public void testFindByEmailAndCodeAndTerritory() {
		// given
		final Voucher voucher = setupBasicVoucher();

		// when
		Voucher foundVouhcer = repository.findByEmailAndCodeAndTerritory(VoucherTest.EMAIL, VoucherTest.CODE,
				VoucherTest.TERRITORY).get(0);
		// then
		Assert.assertEquals(voucher.getEmail(), foundVouhcer.getEmail());
	}

	@Test
	public void testUpdate() {
		// given
		final Voucher voucher = setupBasicVoucher();

		// when
		voucher.setTerritory(Territory.HUN);
		final Voucher foundVouhcer = repository.findById(voucher.getId()).get();
		
		// then
		Assert.assertEquals(voucher.getTerritory(), foundVouhcer.getTerritory());
	}

	@Test
	public void testCountWinners() {
		final Long expectedGermanWinnersCount = 2L;
		// given
		setupAllTimeWinner();

		// when
		final Long winnersInGermany = repository.countWinners(Territory.GER.getCode());
		// then
		Assert.assertEquals(expectedGermanWinnersCount, winnersInGermany);
	}

	@Test
	public void testWinnersOnDate() {
		// given
		final Long expectedGermanWinnersCountOnDate = 2L;
		setupAllTimeWinner();

		// when
		final Long winnersInGermany = repository.countWinnersOnDate(getTodayDate(), Territory.GER.getCode());

		// then
		Assert.assertEquals(expectedGermanWinnersCountOnDate, winnersInGermany);
	}

	@Test
	public void countVouchersOnDate() {
		// given
		final Long expectedSequenceNumber = 4L;
		setupAllTimeWinner();

		// when
		final Voucher voucher = repository.save(VoucherTest.createVoucher(Territory.GER, LotStatus.WINNER));
		final Long voucherDailySequenceNumber = repository.countVouchersOnDate(voucher.getCreationDate(), voucher.getId(),
				Territory.GER.getCode());
		
		// then
		Assert.assertEquals(expectedSequenceNumber, voucherDailySequenceNumber);
	}

	private Voucher setupBasicVoucher() {
		final Voucher voucher = VoucherTest.createBasicVoucher();
		return repository.save(voucher);
	}

	private void setupAllTimeWinner() {
		repository.save(VoucherTest.createVoucher(Territory.GER, LotStatus.WINNER));
		repository.save(VoucherTest.createVoucher(Territory.HUN, LotStatus.LOSER));
		repository.save(VoucherTest.createVoucher(Territory.GER, LotStatus.WINNER));
		repository.save(VoucherTest.createVoucher(Territory.HUN, LotStatus.WINNER));
		repository.save(VoucherTest.createVoucher(Territory.GER, LotStatus.LOSER));
	}

	private String getTodayDate() {
		final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
		return DateTime.now().toString(dateTimeFormat);
	}
}
