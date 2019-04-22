package com.intland.eurocup.service.response;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.model.Response;
import com.intland.eurocup.model.ResponseStatus;
import com.intland.eurocup.service.date.DateService;


/**
 * Test {@link DefaultResponseStorage}
 */
@RunWith(SpringRunner.class)
public class DefaultResponseStorageTest {
  private static final int REPSONSE_TIMEOUT_MIN = 5;
  private static final String ERROR_RESPONSE = "Request id not found!";
  
  private static final Long REQUEST_ID = 10L;
  private static final DateTime NOW = DateTime.now();

  @TestConfiguration
  static class DrawStrategyHungaryTestContext {
      @Bean
      public DefaultResponseStorage getBean() {
          return new DefaultResponseStorage();
      }
  }
  
  @Autowired
  private ResponseStorage responseStorage;
  
  @MockBean
  private DateService dateService;
  
  @Test
  public void registerRequestIdShouldRegisterRequestIdWhenCalled() {
    // given
    
    // when
    responseStorage.registerRequestId(REQUEST_ID);
    final Response response = responseStorage.getResponse(REQUEST_ID);
    
    // then
    Assert.assertEquals(ResponseStatus.NO, response.getStatus());
    Assert.assertEquals(response.getMessage(), "");
    Assert.assertTrue(NOW.minusMinutes(1).isBefore(response.getCreatedDate()) && NOW.plusMinutes(1).isAfter(response.getCreatedDate()));
  }
  
  @Test
  public void saveShouldNotSaveWhenRequestIdNotFound() {
    // given
    
    // when
    responseStorage.save(REQUEST_ID, new Response());
    final Response response = responseStorage.getResponse(REQUEST_ID);
    
    // then
    Assert.assertEquals(ResponseStatus.ERROR, response.getStatus());
    Assert.assertTrue(response.getMessage().equals(ERROR_RESPONSE));
  }
  
  @Test
  public void saveShouldSaveWhenRequestIdRegistered() {
    // given
    final ResponseStatus responseStatus = ResponseStatus.OK;
    final String msg = "Just a message";
    responseStorage.registerRequestId(REQUEST_ID);
    
    // when
    responseStorage.save(REQUEST_ID, new Response(responseStatus, msg));
    final Response response = responseStorage.getResponse(REQUEST_ID);
    
    // then
    Assert.assertEquals(responseStatus, response.getStatus());
    Assert.assertEquals(msg, response.getMessage());
    Assert.assertTrue(NOW.minusMinutes(1).isBefore(response.getCreatedDate()) && NOW.plusMinutes(1).isAfter(response.getCreatedDate()));
  }
  
  @Test
  public void getResponseShouldReturnResponseButNotRemoveWhenResponseNotYetArrived() {
    // given
    final ResponseStatus responseStatus = ResponseStatus.NO;
    final String msg = "Just a message";
    responseStorage.registerRequestId(REQUEST_ID);
    responseStorage.save(REQUEST_ID, new Response(responseStatus, msg));
    
    // when
    responseStorage.getResponse(REQUEST_ID);
    final Response secondResponse = responseStorage.getResponse(REQUEST_ID);
    
    // then
    Assert.assertEquals(responseStatus, secondResponse.getStatus());
    Assert.assertEquals(msg, secondResponse.getMessage());
    Assert.assertTrue(NOW.minusMinutes(1).isBefore(secondResponse.getCreatedDate()) && NOW.plusMinutes(1).isAfter(secondResponse.getCreatedDate()));
  }
  
  @Test
  public void getResponseShouldReturnResponseAndRemoveWhenResponseArrived() {
    // given
    final ResponseStatus responseStatus = ResponseStatus.OK;
    final String msg = "Just a message";
    responseStorage.registerRequestId(REQUEST_ID);
    responseStorage.save(REQUEST_ID, new Response(responseStatus, msg));
    
    // when
    responseStorage.getResponse(REQUEST_ID);
    final Response secondResponse = responseStorage.getResponse(REQUEST_ID);
    
    // then
    Assert.assertEquals(ResponseStatus.ERROR, secondResponse.getStatus());
    Assert.assertEquals(secondResponse.getMessage(), ERROR_RESPONSE);
    Assert.assertTrue(NOW.minusMinutes(1).isBefore(secondResponse.getCreatedDate()) && NOW.plusMinutes(1).isAfter(secondResponse.getCreatedDate()));
  }
}  
 