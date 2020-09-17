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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.mapping.BidListMapping;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.BidListFullDTO;
import com.nnk.springboot.security.util.UserRetrieve;

@SpringJUnitConfig(value = BidListMapping.class)
public class BidListMappingTest {

    @Autowired
    BidListMapping bidListMapping;

    @MockBean
    public UserRetrieve userRetrieve;

    static List<BidList> listOfBidList = new ArrayList<>();
    static List<BidListDTO> listOfBidListDTO = new ArrayList<>();
    static BidListFullDTO bidListDTO = new BidListFullDTO();
    static {
        listOfBidList.add(new BidList());
        listOfBidList.get(0).setBidListId(1);
        listOfBidList.get(0).setAccount("Account1");
        listOfBidList.get(0).setType("Type1");
        listOfBidList.get(0).setBidQuantity(new BigDecimal("1"));
        listOfBidList.get(0).setCreationName("Testeur");
        listOfBidList.get(0).setRevisionName("Testeur");
        listOfBidList.add(new BidList());
        listOfBidList.get(1).setBidListId(2);
        listOfBidList.get(1).setAccount("Account2");
        listOfBidList.get(1).setType("Type2");
        listOfBidList.get(1).setBidQuantity(new BigDecimal("2"));
        listOfBidList.get(1).setCreationName("Testeur");
        listOfBidList.get(1).setRevisionName("Testeur");

        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(0).setBidListId(1);
        listOfBidListDTO.get(0).setAccount("Account1");
        listOfBidListDTO.get(0).setType("Type1");
        listOfBidListDTO.get(0).setBidQuantity(new BigDecimal("1"));
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(1).setBidListId(2);
        listOfBidListDTO.get(1).setAccount("Account2");
        listOfBidListDTO.get(1).setType("Type2");
        listOfBidListDTO.get(1).setBidQuantity(new BigDecimal("2"));

        bidListDTO.setBidListId(2);
        bidListDTO.setAccount("Account2");
        bidListDTO.setType("Type2");
        bidListDTO.setBidQuantity(new BigDecimal("2"));
        bidListDTO.setCreationName("Testeur");
        bidListDTO.setRevisionName("Testeur");
    }

    @Test
    public void givenAListOfBidList_whenMapToDTO_thenReturnsAListOfBidListDTO() {
        // GIVEN
        // WHEN
        List<BidListDTO> resultList = bidListMapping
                .mapAListOfBidList(listOfBidList);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfBidListDTO.toString());

    }

    @Test
    public void givenABidList_whenMapToFullDTO_thenReturnsBidListFullDTO() {
        // GIVEN
        // WHEN
        BidListFullDTO result = bidListMapping
                .mapEntityToFullDTO(listOfBidList.get(1));
        // THEN
        assertThat(result.toString()).isEqualTo(bidListDTO.toString());

    }

    @Test
    public void givenABidListDTO_whenMapToEntity_thenReturnsBidList() {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        BidList result = bidListMapping.mapDTOToEntity(listOfBidListDTO.get(0));
        // THEN
        result.setCreationDate(null);
        result.setRevisionDate(null);
        assertThat(result.toString())
                .isEqualTo(listOfBidList.get(0).toString());

    }

    @Test
    public void givenABidListFullDTO_whenMapToEntity_thenReturnsBidList() {
        // GIVEN
        given(userRetrieve.getLoggedUser()).willReturn("Testeur");
        // WHEN
        BidList result = bidListMapping.mapFullDTOToEntity(bidListDTO);
        // THEN
        result.setCreationDate(null);
        result.setRevisionDate(null);
        assertThat(result.toString())
                .isEqualTo(listOfBidList.get(1).toString());

    }

}
