package com.nnk.springboot;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.services.BidListService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BidListControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Mock
    private View view;

    @MockBean
    private BidListService bidListService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test // GET LIST
    public void whenGetListPage_thenDisplayListPage() throws Exception {
        // GIVEN
        List<BidListDTO> list = new ArrayList<BidListDTO>();
        list.add(new BidListDTO(1, "account1", "type1", 1D));
        given(bidListService.findAll()).willReturn(list);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bidList/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.model().attribute("bidLists",
                        list))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
        verify(bidListService).findAll();
    }

    @Test // GET ADD
    public void whenGetAddPage_thenDisplayAddPage() throws Exception {
        // GIVEN

        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bidList/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("bidListDTO"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // POST VALIDATE
    public void givenAValidNewBidListDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, "account1", "type1", 1D);
        given(bidListService.save(any(BidListDTO.class)))
                .willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListDTO", bidListDTO)
                .param("bidListId", bidListDTO.getBidListId().toString())
                .param("account", bidListDTO.getAccount())
                .param("type", bidListDTO.getType())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andReturn();
        // THEN
        verify(bidListService).save(any(BidListDTO.class));
    }

    @Test // POST VALIDATE WITH ERROR
    public void givenANonValidNewBidListDTO_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, null, "type1", 1D);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListDTO", bidListDTO)
                .param("bidListId", bidListDTO.getBidListId().toString())
                .param("account", bidListDTO.getAccount())
                .param("type", bidListDTO.getType())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
                .andReturn();
        // THEN
        verify(bidListService, never()).save(any(BidListDTO.class));
    }

    @Test // GET ADD
    public void whenGetUpdatePage_thenDisplayUpdatePage()
            throws Exception, BidListNotFoundException {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, "account1", "type1", 1D);
        given(bidListService.getById(1)).willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bidList/update/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("bidListDTO"))
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        // THEN
    }

    @Test // POST UPDATE
    public void givenAValidBidListDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, "account1", "type1", 1D);
        given(bidListService.save(any(BidListDTO.class)))
                .willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListDTO", bidListDTO)
                .param("bidListId", bidListDTO.getBidListId().toString())
                .param("account", bidListDTO.getAccount())
                .param("type", bidListDTO.getType())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andReturn();
        // THEN
        verify(bidListService).save(any(BidListDTO.class));
    }

    @Test // POST UPDATE WITH ERROR
    public void givenANonValidBidListDTOToUpdate_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, "account1", "", 1D);
        given(bidListService.save(any(BidListDTO.class)))
                .willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListDTO", bidListDTO)
                .param("bidListId", bidListDTO.getBidListId().toString())
                .param("account", bidListDTO.getAccount())
                .param("type", bidListDTO.getType())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andReturn();
        // THEN
        verify(bidListService, never()).save(any(BidListDTO.class));
    }

    @Test // POST UPDATE WITH ERROR BidQuantity
    public void givenANonValidBidQuantityToUpdate_whenPost_thenTryAgain()
            throws Exception {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(1, "account1", "type1",
                0.12345D);
        given(bidListService.save(any(BidListDTO.class)))
                .willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListDTO", bidListDTO)
                .param("bidListId", bidListDTO.getBidListId().toString())
                .param("account", bidListDTO.getAccount())
                .param("type", bidListDTO.getType())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andReturn();
        // THEN
        verify(bidListService, never()).save(any(BidListDTO.class));
    }

    @Test // DELETE
    public void whenGetDeletePage_thenDisplayDeletePage()
            throws Exception, BidListNotFoundException {
        // GIVEN
        BidListDTO bidListDTO = new BidListDTO(7, "account1", "type1", 1D);
        given(bidListService.getById(1)).willReturn(bidListDTO);
        given(bidListService.delete(1)).willReturn(bidListDTO);
        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bidList/delete/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andReturn();
        // THEN
        verify(bidListService).delete(7);
    }
    
}
