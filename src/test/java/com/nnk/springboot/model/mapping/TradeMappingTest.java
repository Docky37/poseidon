package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.mapping.TradeMapping;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.security.util.UserRetrieve;

@SpringJUnitConfig(value = TradeMapping.class)
public class TradeMappingTest {

    @Autowired
    TradeMapping tradeMapping;

    @MockBean
    public UserRetrieve userRetrieve;

    static List<Trade> listOfTrade = new ArrayList<>();
    static List<TradeDTO> listOfTradeDTO = new ArrayList<>();
    static TradeFullDTO tradeDTO = new TradeFullDTO();
    static {
        listOfTrade.add(new Trade());
        listOfTrade.get(0).setTradeId(1);
        listOfTrade.get(0).setAccount("Account1");
        listOfTrade.get(0).setType("Type1");
        listOfTrade.get(0).setBuyQuantity(new BigDecimal("20"));
        listOfTrade.get(0).setCreationName("Testeur");
        listOfTrade.get(0).setRevisionName("Testeur");
        listOfTrade.add(new Trade());
        listOfTrade.get(1).setTradeId(2);
        listOfTrade.get(1).setAccount("Account2");
        listOfTrade.get(1).setType("Type2");
        listOfTrade.get(1).setBuyQuantity(new BigDecimal("20"));
        listOfTrade.get(1).setCreationName("Testeur");
        listOfTrade.get(1).setRevisionName("Testeur");

        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(0).setTradeId(1);
        listOfTradeDTO.get(0).setAccount("Account1");
        listOfTradeDTO.get(0).setType("Type1");
        listOfTradeDTO.get(0).setBuyQuantity(new BigDecimal("20"));
        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(1).setTradeId(2);
        listOfTradeDTO.get(1).setAccount("Account2");
        listOfTradeDTO.get(1).setType("Type2");
        listOfTradeDTO.get(1).setBuyQuantity(new BigDecimal("20"));

        tradeDTO.setTradeId(2);
        tradeDTO.setAccount("Account2");
        tradeDTO.setType("Type2");
        tradeDTO.setBuyQuantity(new BigDecimal("20"));
        tradeDTO.setCreationName("Testeur");
        tradeDTO.setRevisionName("Testeur");
    }

    @Test
    public void givenAListOfTrade_whenMapToDTO_thenReturnsAListOfTradeDTO() {
        // GIVEN
        // WHEN
        List<TradeDTO> resultList = tradeMapping
                .mapAListOfTrade(listOfTrade);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfTradeDTO.toString());

    }

    @Test
    public void givenATrade_whenMapToFullDTO_thenReturnsTradeFullDTO() {
        // GIVEN
        // WHEN
        TradeFullDTO result = tradeMapping
                .mapEntityToFullDTO(listOfTrade.get(1));
        // THEN
        assertThat(result.toString()).isEqualTo(tradeDTO.toString());

    }

    @Test
    public void givenATradeDTO_whenMapToEntity_thenReturnsTrade() {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        Trade result = tradeMapping.mapDTOToEntity(listOfTradeDTO.get(0));
        // THEN
        result.setCreationDate(null);
        result.setRevisionDate(null);
        assertThat(result.toString())
                .isEqualTo(listOfTrade.get(0).toString());

    }

    @Test
    public void givenATradeFullDTO_whenMapToEntity_thenReturnsTrade() {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        Trade result = tradeMapping.mapFullDTOToEntity(tradeDTO);
        // THEN
        result.setCreationDate(null);
        result.setRevisionDate(null);
        assertThat(result.toString())
                .isEqualTo(listOfTrade.get(1).toString());

    }

}
