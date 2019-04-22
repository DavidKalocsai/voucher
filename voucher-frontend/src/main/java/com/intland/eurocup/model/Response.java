package com.intland.eurocup.model;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Stores result of lot that is passed from the back end application.s
 */
@Getter
@ToString
public class Response {
  private ResponseStatus status;
  private String message;
  private DateTime createdDate;

  /**
   * Default constructor to be used, when adding empty response.
   */
  public Response() {
    status = ResponseStatus.NO;
    message = "";
    createdDate = DateTime.now();
  }

  /**
   * Constructor to be used, when response arrived.
   * 
   * @param responseStatus {@link ResponseStatus}
   * @param message Message to be passed to UI
   */
  public Response(final ResponseStatus responseStatus, final String message) {
    status = responseStatus;
    this.message = message;
    createdDate = DateTime.now();
  }
}
