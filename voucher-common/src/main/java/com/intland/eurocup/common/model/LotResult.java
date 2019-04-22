package com.intland.eurocup.common.model;

import lombok.Getter;
import lombok.ToString;

/**
 * .
 */
@Getter
@ToString
public enum LotResult {
	DEFAULT("No lot result yet!"), WINNER("You have won!"), LOSER("Sorry, but you weren't lucky!"),
	VOUCHER_USED("Voucher was already submitted by someone else"),
	EMAIL_USED("Email is already used! A user can only submit one voucher!"), 
	TERRITORY_NOT_SUPPORTED("Unsupported territory"),
	ERROR("Error");

	private String description;

	LotResult(final String description) {
		this.description = description;
	}

	public static LotResult getEnumFromCode(final String description) {
		LotResult orderStatus = null;
		for (final LotResult o : LotResult.values()) {
			if (orderStatus == null && o.getDescription().equals(description)) {
				orderStatus = o;
			}
		}
		return orderStatus;
	}
}
