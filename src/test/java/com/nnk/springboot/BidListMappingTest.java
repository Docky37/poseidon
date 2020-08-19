package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.domain.mapping.BidListMapping;

@SpringJUnitConfig(value = BidListMapping.class)
public class BidListMappingTest {

    @Autowired
    BidListMapping bidListMapping;

    static List<BidList> listOfBidList = new ArrayList<>();
    static List<BidListDTO> listOfBidListDTO = new ArrayList<>();
    static {
        listOfBidList.add(new BidList());
        listOfBidList.get(0).setBidListId(1);
        listOfBidList.get(0).setAccount("Account1");
        listOfBidList.get(0).setAccount("Type1");
        listOfBidList.get(0).setBidQuantity(new BigDecimal("1"));
        listOfBidList.add(new BidList());
        listOfBidList.get(1).setBidListId(2);
        listOfBidList.get(1).setAccount("Account2");
        listOfBidList.get(1).setAccount("Type2");
        listOfBidList.get(1).setBidQuantity(new BigDecimal("2"));

        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(0).setBidListId(1);
        listOfBidListDTO.get(0).setAccount("Account1");
        listOfBidListDTO.get(0).setAccount("Type1");
        listOfBidListDTO.get(0).setBidQuantity(new BigDecimal("1"));
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(1).setBidListId(2);
        listOfBidListDTO.get(1).setAccount("Account2");
        listOfBidListDTO.get(1).setAccount("Type2");
        listOfBidListDTO.get(1).setBidQuantity(new BigDecimal("2"));
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
    public void givenABidListDTO_whenMapToEntity_thenReturnsBidList() {
        // GIVEN
        // WHEN
        BidList result = bidListMapping.mapDTOToEntity(listOfBidListDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfBidList.get(0).toString());

    }

}
