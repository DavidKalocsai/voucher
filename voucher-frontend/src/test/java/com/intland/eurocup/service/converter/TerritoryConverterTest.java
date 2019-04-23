package com.intland.eurocup.service.converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.intland.eurocup.common.model.Territory;
import com.intland.eurocup.service.converter.exception.UnkownTerritoryException;


/**
 * {@link TerritoryConverter}
 */
@RunWith(SpringRunner.class)
public class TerritoryConverterTest {

  @TestConfiguration
  static class TerritoryConverterTestContext {
    @Bean
    public TerritoryConverter territoryConverter() {
      return new TerritoryConverter();
    }
  }

  @Autowired
  private TerritoryConverter converter;
  
  @Test
  public void setAsTextShouldSetValueWhenTerritoryValid() {
    //given

    //when
    converter.setAsText(Territory.GER.getCode());
    
    //then
    Assert.assertEquals(Territory.GER, converter.getValue());    
  }
  
  @Test(expected = UnkownTerritoryException.class)
  public void setAsTextShouldThrowExceptoinWhenTerritoryInValid() {
    //given

    //when
    converter.setAsText("This is not a valid Territory");
    
    //then
  }
}