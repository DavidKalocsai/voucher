package com.intland.eurocup.common.model;

import lombok.Getter;

/**
 * Status of an order. It can be active, or deleted. On delete order is only set
 * to deleted, but not removed.
 */
@Getter
public enum Territory {
	HUN("HUN", "Hungary"), GER("GER", "Germany");

  private final String code;
	private final String description;

	private Territory(final String code, final String description) {
	  this.code = code;
	  this.description = description;
	}
	
	public static Territory getEnumFromCode(final String code) {
		Territory orderStatus = null;
		for (final Territory o : Territory.values()) {
			if (orderStatus == null && o.getCode().equals(code)) {
				orderStatus = o;
			}
		}
		return orderStatus;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
