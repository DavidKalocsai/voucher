package com.intland.eurocup.service.converter;

import java.beans.PropertyEditorSupport;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.service.converter.exception.UnkownTerritoryException;

/**
 * Converts territory path variable (String) to {@link Territory}.
 * In case of not valid {@link Territory} {@link UnkownTerritoryException} is thrown.
 */
public class TerritoryConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(final String territoryCode) {
		final Territory territory = Territory.getEnumFromCode(territoryCode.toUpperCase());
		validate(territory);
		setValue(territory);
	}
	
	private void validate(final Territory territory) {
		if (territory == null) {
			throw new UnkownTerritoryException();
		}
	}
}