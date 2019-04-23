package com.intland.eurocup.service.id;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestIdGeneratorTest {

  @TestConfiguration
  static class RequestIdGeneratorTestContext {
    @Bean
    public RequestIdGenerator idGenerator() {
      return new RequestIdGenerator();
    }
  }

  @Autowired
  private RequestIdGenerator idGenerator;

  @Test
  public void getNextShouldReturnNextValue() {
    // Given
    final Long oldId = idGenerator.getNext();

    // When
    final Long newId = idGenerator.getNext();

    // Then
    Assert.assertTrue(oldId + 1L == newId);
  }
}