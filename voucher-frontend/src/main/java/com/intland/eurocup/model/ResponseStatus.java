package com.intland.eurocup.model;

import lombok.Getter;
import lombok.ToString;

/**
 * Status of the response. When UI fetches response, it determinates statuf of
 * the response from this: No answer arrived, Answer arrived successfully,
 * Answer arrived with error.
 *
 */
@Getter
@ToString
public enum ResponseStatus {
  NO("Answer not yet arrived"), OK("Response arrived"), ERROR("Error occured");

  private String description;

  ResponseStatus(final String description) {
    this.description = description;
  }

  /**
   * Get {@link ResponseStatus} from the description.
   * 
   * @param description used to identify {@link ResponseStatus}
   * @return {@link ResponseStatus}
   */
  public static ResponseStatus getEnumFromCode(final String description) {
    ResponseStatus orderStatus = null;
    for (final ResponseStatus o : ResponseStatus.values()) {
      if (orderStatus == null && o.getDescription().equals(description)) {
        orderStatus = o;
      }
    }
    return orderStatus;
  }
}
