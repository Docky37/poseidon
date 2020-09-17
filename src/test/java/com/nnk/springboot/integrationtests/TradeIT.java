package com.nnk.springboot.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.security.util.UserRetrieve;
import com.nnk.springboot.services.TradeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TradeIT {

    static final Logger LOGGER = LoggerFactory.getLogger(TradeIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private UserRetrieve userRetrieve;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepository tradeRepository;

    TradeDTO tradeDTO;
    TradeFullDTO tradeFullDTO;
    static List<Trade> listOfTrade = new ArrayList<>();
    static List<TradeDTO> listOfTradeDTO = new ArrayList<>();
    static {
        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(0).setTradeId(1);
        listOfTradeDTO.get(0).setAccount("Account1");
        listOfTradeDTO.get(0).setType("Type1");
        listOfTradeDTO.get(0).setBuyQuantity(new BigDecimal("1.0000"));
        listOfTradeDTO.add(new TradeDTO());
        listOfTradeDTO.get(1).setTradeId(2);
        listOfTradeDTO.get(1).setAccount("Account2");
        listOfTradeDTO.get(1).setType("Type2");
        listOfTradeDTO.get(1).setBuyQuantity(new BigDecimal("2.0000"));

    }

    @BeforeEach
    public void setup() {

        tradeDTO = new TradeDTO(1, "account1", "type1", new BigDecimal("1"),
                null, null, null);
        tradeFullDTO = new TradeFullDTO();
        tradeFullDTO.setTradeId(1);
        tradeFullDTO.setAccount("account1");
        tradeFullDTO.setType("type1");
        tradeFullDTO.setBuyQuantity(new BigDecimal("158.0000"));
        tradeFullDTO.setSellQuantity(new BigDecimal("161.0000"));
        tradeFullDTO.setRevisionName("user");

        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void tearDrop() {
        tradeRepository.truncate();
    }

    @Test // POST VALIDATE
    public void givenExistingTradeDTO_whenFindAll_thenListed()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        tradeService.save(listOfTradeDTO.get(0));
        tradeService.save(listOfTradeDTO.get(1));
        List<TradeDTO> resultList = tradeService.findAll();
        // THEN
        assertThat(resultList.toString()).isEqualTo(listOfTradeDTO.toString());
    }

    @Test // POST VALIDATE
    public void givenAValidNewTradeDTO_when_thenSaved() throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("tradeDTO", tradeDTO)
                .param("tradeId", tradeDTO.getTradeId().toString())
                .param("account", tradeDTO.getAccount())
                .param("type", tradeDTO.getType())
                .param("bidQuantity", tradeDTO.getBuyQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andReturn();
        TradeFullDTO tradeDTOResult = null;
        try {
            tradeDTOResult = tradeService.getById(1);
        } catch (TradeNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(tradeDTOResult.getAccount())
                .isEqualTo(tradeDTO.getAccount());
        assertThat(tradeDTOResult.getCreationDate().getDayOfMonth())
                .isEqualTo(LocalDate.now().getDayOfMonth());
        assertThat(tradeDTOResult.getCreationName()).isEqualTo("Testeur");
    }

    @Test // POST UPDATE
    public void givenAValidTradeDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("tradeFullDTO", tradeFullDTO)
                .param("tradeId", tradeFullDTO.getTradeId().toString())
                .param("account", "account1")
                .param("type", tradeFullDTO.getType())
                .param("buyQuantity", tradeFullDTO.getBuyQuantity().toString())
                .param("sellQuantity", "161.0000")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andReturn();
        TradeFullDTO tradeDTOResult = null;
        try {
            tradeDTOResult = tradeService.getById(1);
        } catch (TradeNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(tradeDTOResult.getAccount())
                .isEqualTo(tradeFullDTO.getAccount());
        assertThat(tradeDTOResult.getRevisionDate().getDayOfMonth())
                .isEqualTo(LocalDate.now().getDayOfMonth());
        assertThat(tradeDTOResult.getRevisionName()).isEqualTo("Testeur");
        // THEN
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, TradeNotFoundException {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        TradeDTO existingTradeDTO = tradeService.save(listOfTradeDTO.get(0));
        // WHEN
        TradeDTO deletedTradeDTO = null;
        try {
            deletedTradeDTO = tradeService
                    .delete(existingTradeDTO.getTradeId());
        } catch (TradeNotFoundException e) {
            LOGGER.error(" => No Trade record exist for id={}!",
                    existingTradeDTO.getTradeId());
        }

        // THEN
        assertThat(deletedTradeDTO.toString())
                .isEqualTo(existingTradeDTO.toString());
        assertThrows(TradeNotFoundException.class, () -> {
            tradeService.getById(1);
        });
    }

}
