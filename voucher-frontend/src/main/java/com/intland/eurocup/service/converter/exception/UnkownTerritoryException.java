package com.intland.eurocup.service.converter.exception;

import com.intland.eurocup.common.model.Territory;

/**
 * It is thrown, when controller returns with path variable that can not be
 * converted into {@link Territory}.
 */
public class UnkownTerritoryException extends RuntimeException {
  private static final long serialVersionUID = 1640560135192484753L;
}
