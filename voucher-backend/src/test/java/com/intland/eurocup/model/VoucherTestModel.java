package com.intland.eurocup.model;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import com.intland.eurocup.common.model.Territory;

public class VoucherTestModel {
	public static final String EMAIL = "a@email.com";
	public static final String CODE = "1234567890";
	public static final Territory TERRITORY = Territory.GER;
	public static final Date DATE = new Date();

	public static Voucher createBasicVoucher() {
		final Voucher voucher = new Voucher();
		voucher.setEmail(EMAIL);
		voucher.setCode(CODE);
		voucher.setTerritory(TERRITORY);
		voucher.setCreatedAt(DATE);
		return voucher;
	}
	
	public static Voucher createBasicVoucher(final LotStatus lotStatus) {
    final Voucher voucher = createBasicVoucher();
    voucher.setLotStatus(lotStatus);
    return voucher;
  }
	
	public static Voucher createBasicVoucher(final Long id, final LotStatus lotStatus) {
    final Voucher voucher = createBasicVoucher(lotStatus);
    voucher.setId(id);    
    return voucher;
  }

	public static Voucher createVoucher(final Territory territory, final LotStatus lotStatus) {
		final Voucher voucher = new Voucher();
		voucher.setEmail(randomString());
		voucher.setCode(randomString());
		voucher.setTerritory(territory);
		voucher.setLotStatus(lotStatus);
		voucher.setCreatedAt(DATE);
		return voucher;
	}

	private static String randomString() {
		byte[] array = new byte[10]; // length is bounded by 7
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));

	}
}
