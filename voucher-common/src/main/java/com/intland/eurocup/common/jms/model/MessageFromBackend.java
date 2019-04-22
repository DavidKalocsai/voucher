package com.intland.eurocup.common.jms.model;

import com.intland.eurocup.common.model.LotResult;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageFromBackend {
	private Long requestId;
	private LotResult lotResult;
}
