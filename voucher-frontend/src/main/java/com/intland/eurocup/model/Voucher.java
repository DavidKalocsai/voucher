package com.intland.eurocup.model;

import com.intland.eurocup.common.model.Territory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Domain of Voucher to be submitted to back end application.
 *
 */
@Getter @Setter @ToString @EqualsAndHashCode
public class Voucher {
	private Long id;
	private String code;
	private String email;
	private Territory territory;	
}
