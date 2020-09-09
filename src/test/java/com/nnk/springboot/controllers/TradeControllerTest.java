package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.services.TradeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TradeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private TradeService tradeService;

    TradeDTO tradeDTO;
    TradeFullDTO tradeFullDTO;

    @BeforeEach
    public void setup() {
        tradeDTO = new TradeDTO(1, "account1", "type1", new BigDecimal("1"),
                null, null, null);
        tradeFullDTO = new TradeFullDTO();
        tradeFullDTO.setTradeId(1);
        tradeFullDTO.setAccount("account1");
        tradeFullDTO.setType("type1");
        tradeFullDTO.setBuyQuantity(new BigDecimal("1"));
        tradeFullDTO.setRevisionName("user");

        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<TradeDTO> list = new ArrayList<TradeDTO>();
        list.add(new TradeDTO(1, "account1", "type1", new BigDecimal("1"), null,
                null, null));
        given(tradeService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/list")).andDo(print())
                .andExpect(
                        MockMvcResultMatchers.model().attribute("trades", list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(tradeService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("tradeDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // POST VALIDATE
    public void givenAValidNewTradeDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(tradeService.save(any(TradeDTO.class))).willReturn(tradeDTO);
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
        // THEN
        verify(tradeService).save(any(TradeDTO.class));
    }

    @Test // POST VALIDATE WITH ERROR
    public void givenANonValidNewTradeDTO_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        tradeDTO.setAccount("");
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("tradeDTO", tradeDTO)
                .param("tradeId", tradeDTO.getTradeId().toString())
                .param("account", tradeDTO.getAccount())
                .param("type", tradeDTO.getType())
                .param("bidQuantity", tradeDTO.getBuyQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("trade/add"))
                .andReturn();
        // THEN
        verify(tradeService, never()).save(any(TradeDTO.class));
    }

    @Test // GET UPDATE
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, TradeNotFoundException {
        // GIVEN
        given(tradeService.getById(1)).willReturn(tradeFullDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("tradeFullDTO"))
                .andExpect(MockMvcResultMatchers.view().name("trade/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // GET UPDATE WITH EXCEPTION
    public void givenAnUnknownId_whenGetUpdatePage_thenRedirectToListPage()
            throws Exception, TradeNotFoundException {
        // GIVEN
        given(tradeService.getById(7)).willThrow(TradeNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/update/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/trade/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidTradeDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        given(tradeService.saveFullDTO(any(TradeFullDTO.class)))
                .willReturn(tradeFullDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
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
        // THEN
        verify(tradeService).saveFullDTO(any(TradeFullDTO.class));
    }

    @Test // POST UPDATE WITH ERROR
    public void givenANonValidTradeDTOToUpdate_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        tradeDTO.setType(null);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("tradeDTO", tradeDTO)
                .param("tradeId", tradeDTO.getTradeId().toString())
                .param("account", tradeDTO.getAccount())
                .param("type", tradeDTO.getType())
                .param("bidQuantity", tradeDTO.getBuyQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"))
                .andReturn();
        // THEN
        verify(tradeService, never()).save(any(TradeDTO.class));
    }

    @Test // POST UPDATE WITH ERROR BidQuantity
    public void givenANonValidBidQuantityToUpdate_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        TradeDTO trade2DTO = new TradeDTO(2, "account1", "type1",
                new BigDecimal("127.1234567"), null, null, null);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/trade/update/2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("tradeDTO", tradeDTO)
                .param("tradeId", trade2DTO.getTradeId().toString())
                .param("account", trade2DTO.getAccount())
                .param("type", trade2DTO.getType())
                .param("bidQuantity", trade2DTO.getBuyQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"))
                .andReturn();
        // THEN
        verify(tradeService, never()).save(any(TradeDTO.class));
    }

    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleteAndRedirectToListPage()
            throws Exception, TradeNotFoundException {
        // GIVEN
        given(tradeService.delete(1)).willReturn(tradeDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/delete/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andReturn();
        // THEN
        verify(tradeService).delete(1);
    }

    @Test // DELETE WITH EXCEPTION
    public void givenUnknownId_whenDelete_thenRedirectToListPage()
            throws Exception, TradeNotFoundException {
        // GIVEN
        given(tradeService.delete(7)).willThrow(TradeNotFoundException.class);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/trade/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view()
                        .name("redirect:/trade/list"))
                .andExpect(MockMvcResultMatchers.status().is(302)).andReturn();
        // THEN
        verify(tradeService).delete(7);
    }

}
