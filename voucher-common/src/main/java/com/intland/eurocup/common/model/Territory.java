package com.intland.eurocup.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status of an order. It can be active, or deleted. On delete order is only set
 * to deleted, but not removed.
 */
@Getter
@AllArgsConstructor
public enum Territory {
	HUN("Hungary"), GER("Germany");

	private String description;

	public static Territory getEnumFromCode(final String code) {
		Territory orderStatus = null;
		for (final Territory o : Territory.values()) {
			if (orderStatus == null && o.name().equals(code)) {
				orderStatus = o;
			}
		}
		return orderStatus;
	}
	
	public String getDbCode() {
		return this.name();
	}

	@Override
	public String toString() {
		return this.description;
	}
}
