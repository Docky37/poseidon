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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.BidListFullDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.util.UserRetrieve;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BidListIT {

    static final Logger LOGGER = LoggerFactory
            .getLogger(BidListIT.class);

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
    private BidListService bidListService;

    @Autowired
    private BidListRepository bidListRepository;

    BidListDTO bidListDTO;
    BidListFullDTO bidListFullDTO;
    static List<BidList> listOfBidList = new ArrayList<>();
    static List<BidListDTO> listOfBidListDTO = new ArrayList<>();
    static {
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(0).setBidListId(1);
        listOfBidListDTO.get(0).setAccount("Account1");
        listOfBidListDTO.get(0).setType("Type1");
        listOfBidListDTO.get(0).setBidQuantity(new BigDecimal("1.0000"));
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(1).setBidListId(2);
        listOfBidListDTO.get(1).setAccount("Account2");
        listOfBidListDTO.get(1).setType("Type2");
        listOfBidListDTO.get(1).setBidQuantity(new BigDecimal("2.0000"));

    }

    @BeforeEach
    public void setup() {
        
        bidListDTO = new BidListDTO(1, "account1", "type1",
                new BigDecimal("1"));
        bidListFullDTO = new BidListFullDTO();
        bidListFullDTO.setBidListId(1);
        bidListFullDTO.setAccount("account1");
        bidListFullDTO.setType("type1");
        bidListFullDTO.setBidQuantity(new BigDecimal("158.0000"));
        bidListFullDTO.setAskQuantity(new BigDecimal("161.0000"));
        bidListFullDTO.setRevisionName("user");

        
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void tearDrop() {
        System.out.println("TEAR DROP");
        System.out.println(bidListRepository.count());
        //bidListRepository.truncate();
        System.out.println(bidListRepository.count());
    }

    @Test // POST VALIDATE
    public void givenAValidNewBidListDTO_whenPost_thenSaved() throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        bidListService.save(listOfBidListDTO.get(0));        
        bidListService.save(listOfBidListDTO.get(1));        
        List<BidListDTO> resultList = bidListService.findAll();
        // THEN
        assertThat(resultList.toString()).isEqualTo(listOfBidListDTO.toString());
    }

    @Test // POST VALIDATE
    public void givenAValidNewBidListDTO_when_thenSaved() throws Exception {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
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
        BidListFullDTO bidListDTOResult = null;
        try {
            bidListDTOResult = bidListService.getById(1);
        } catch (BidListNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(bidListDTOResult.getAccount()).isEqualTo(bidListDTO.getAccount());
        assertThat(bidListDTOResult.getCreationDate().getDayOfMonth()).isEqualTo(LocalDate.now().getDayOfMonth());
        assertThat(bidListDTOResult.getCreationName()).isEqualTo("Testeur");
    }

     @Test // POST UPDATE
    public void givenAValidBidListDTOToUpdate_whenPost_thenSaved()
            throws Exception {
        // GIVEN
         given(userRetrieve.getLoggedUser()).willReturn("Testeur");
       // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/bidList/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("bidListFullDTO", bidListFullDTO)
                .param("bidListId", bidListFullDTO.getBidListId().toString())
                .param("account", "account1")
                .param("type", bidListFullDTO.getType())
                .param("bidQuantity", bidListFullDTO.getBidQuantity().toString())
                .param("askQuantity", "161.0000"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andReturn();
        BidListFullDTO bidListDTOResult = null;
        try {
            bidListDTOResult = bidListService.getById(1);
        } catch (BidListNotFoundException e) {
            e.printStackTrace();
        }
        // THEN
        assertThat(bidListDTOResult.getAccount()).isEqualTo(bidListFullDTO.getAccount());
        assertThat(bidListDTOResult.getRevisionDate().getDayOfMonth()).isEqualTo(LocalDate.now().getDayOfMonth());
        assertThat(bidListDTOResult.getRevisionName()).isEqualTo("Testeur");
        // THEN
    }


    @Test // DELETE
    public void givenAValidId_whenDelete_thenDeleted()
            throws Exception, BidListNotFoundException {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        bidListService.save(listOfBidListDTO.get(0));
        // WHEN
        BidListDTO deletedBidListDTO = null;
        try {
            deletedBidListDTO = bidListService.delete(1);
         } catch (BidListNotFoundException e) {
            LOGGER.error(" => No BidList record exist for id={}!", 1);
        }
       
        // THEN
        assertThat(deletedBidListDTO.toString()).isEqualTo(listOfBidListDTO.get(0).toString());
        assertThrows(BidListNotFoundException.class, () -> {
            bidListService.getById(1);});
    }

}
