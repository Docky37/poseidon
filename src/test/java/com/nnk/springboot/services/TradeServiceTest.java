package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.mapping.TradeMapping;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import com.nnk.springboot.services.TradeServiceImpl;

@SpringJUnitConfig(value = TradeServiceImpl.class)
public class TradeServiceTest {

    @Autowired
    TradeService tradeService;

    @MockBean
    TradeRepository tradeRepository;

    @MockBean
    TradeMapping tradeMapping;

    static List<Trade> listOfTrade = new ArrayList<>();
    static List<TradeDTO> listOfTradeDTO = new ArrayList<>();
    static TradeFullDTO tradeFullDTO = new TradeFullDTO();
    static {
        listOfTrade.add(new Trade());
        listOfTrade.get(0).setTradeId(1);
        listOfTrade.get(0).setAccount("Account1");
        listOfTrade.get(0).setType("Type1");
        listOfTrade.get(0).setBuyQuantity(new BigDecimal("15"));
        listOfTrade.add(new Trade());
        listOfTrade.get(1).setTradeId(2);
        listOfTrade.get(1).setAccount("Account2");
        listOfTrade.get(1).setType("Type2");
        listOfTrade.get(1).setBuyQuantity(new BigDecimal("20"));

        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(0).setTradeId(1);
        listOfTradeDTO.get(0).setAccount("Account1");
        listOfTradeDTO.get(0).setType("Type1");
        listOfTradeDTO.get(0).setBuyQuantity(new BigDecimal("15"));
        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(1).setTradeId(2);
        listOfTradeDTO.get(1).setAccount("Account2");
        listOfTradeDTO.get(1).setType("Type2");
        listOfTradeDTO.get(1).setBuyQuantity(new BigDecimal("20"));

        tradeFullDTO.setTradeId(2);
        tradeFullDTO.setAccount("Account2");
        tradeFullDTO.setAccount("Type2");
        tradeFullDTO.setBuyQuantity(new BigDecimal("20"));;

    }

    @Test
    public void whenFindAll_thenReturnsListOfAllTrades() {
        // GIVEN
        given(tradeRepository.findAll()).willReturn(listOfTrade);
        given(tradeMapping.mapAListOfTrade(listOfTrade))
                .willReturn(listOfTradeDTO);
        // WHEN
        List<TradeDTO> resultList = tradeService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfTradeDTO.toString());
    }

    @Test
    public void givenATradeDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(tradeMapping.mapDTOToEntity(listOfTradeDTO.get(0)))
                .willReturn(listOfTrade.get(0));
        given(tradeRepository.save(listOfTrade.get(0)))
                .willReturn(listOfTrade.get(0));
        given(tradeMapping.mapEntityToDTO(any(Trade.class)))
                .willReturn(listOfTradeDTO.get(0));
        // WHEN
        TradeDTO result = tradeService.save(listOfTradeDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfTradeDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedTrade()
            throws TradeNotFoundException {
        // GIVEN
        given(tradeRepository.findById(2))
                .willReturn(Optional.of(listOfTrade.get(1)));
        given(tradeMapping.mapEntityToFullDTO(any(Trade.class)))
                .willReturn(tradeFullDTO);
        // WHEN
        TradeDTO result = tradeService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString())
                .isEqualTo(tradeFullDTO.toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenTradeNotFoundException()
            throws TradeNotFoundException {
        // GIVEN
        // WHEN - THEN
        assertThrows(TradeNotFoundException.class, () -> {
            tradeService.getById(3);
        });
    }

    @Test
    public void givenATradeDTO_whenDelete_thenReturnsDeletedTrade()
            throws TradeNotFoundException {
        // GIVEN
        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(2).setTradeId(3);
        listOfTradeDTO.get(2).setAccount("Account3");
        listOfTradeDTO.get(2).setAccount("Type3");
        listOfTradeDTO.get(2).setBuyQuantity(new BigDecimal("100"));
        listOfTrade.add(new Trade());
        listOfTrade.get(2).setTradeId(3);
        listOfTrade.get(2).setAccount("Account3");
        listOfTrade.get(2).setAccount("Type3");
        listOfTrade.get(2).setBuyQuantity(new BigDecimal("100"));
        given(tradeRepository.findById(3))
                .willReturn(Optional.of(listOfTrade.get(2)));
        given(tradeMapping.mapEntityToDTO(any(Trade.class)))
                .willReturn(listOfTradeDTO.get(2));
        // WHEN
        TradeDTO result = tradeService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfTradeDTO.get(2).toString());
    }

}
