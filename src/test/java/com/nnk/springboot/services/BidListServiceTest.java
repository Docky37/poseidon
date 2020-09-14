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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.mapping.BidListMapping;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.BidListFullDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.BidListServiceImpl;

@SpringJUnitConfig(value = BidListServiceImpl.class)
public class BidListServiceTest {

    @Autowired
    BidListService bidListService;

    @MockBean
    BidListRepository bidListRepository;

    @MockBean
    BidListMapping bidListMapping;

    static List<BidList> listOfBidList = new ArrayList<>();
    static List<BidListDTO> listOfBidListDTO = new ArrayList<>();
    static BidListFullDTO bidListFullDTO = new BidListFullDTO();
    static {
        listOfBidList.add(new BidList());
        listOfBidList.get(0).setBidListId(1);
        listOfBidList.get(0).setAccount("Account1");
        listOfBidList.get(0).setType("Type1");
        listOfBidList.get(0).setBidQuantity(new BigDecimal("1"));
        listOfBidList.add(new BidList());
        listOfBidList.get(1).setBidListId(2);
        listOfBidList.get(1).setAccount("Account2");
        listOfBidList.get(1).setType("Type2");
        listOfBidList.get(1).setBidQuantity(new BigDecimal("2"));

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

        bidListFullDTO.setBidListId(2);
        bidListFullDTO.setAccount("Account2");
        bidListFullDTO.setType("Type2");
        bidListFullDTO.setBidQuantity(new BigDecimal("2"));

    }

    @Test
    public void whenFindAll_thenReturnsListOfAllBidLists() {
        // GIVEN
        given(bidListRepository.findAll()).willReturn(listOfBidList);
        given(bidListMapping.mapAListOfBidList(listOfBidList))
                .willReturn(listOfBidListDTO);
        // WHEN
        List<BidListDTO> resultList = bidListService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfBidListDTO.toString());
    }

    @Test
    public void givenABidListDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(bidListMapping.mapDTOToEntity(listOfBidListDTO.get(0)))
                .willReturn(listOfBidList.get(0));
        given(bidListRepository.save(listOfBidList.get(0)))
                .willReturn(listOfBidList.get(0));
        given(bidListMapping.mapEntityToDTO(any(BidList.class)))
                .willReturn(listOfBidListDTO.get(0));
        // WHEN
        BidListDTO result = bidListService.save(listOfBidListDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfBidListDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedBidList()
            throws BidListNotFoundException {
        // GIVEN
        given(bidListRepository.findById(2))
                .willReturn(Optional.of(listOfBidList.get(1)));
        given(bidListMapping.mapEntityToFullDTO(any(BidList.class)))
                .willReturn(bidListFullDTO);
        // WHEN
        BidListDTO result = bidListService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString()).isEqualTo(bidListFullDTO.toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenBidListNotFoundException()
            throws BidListNotFoundException {
        // GIVEN
        // WHEN
        // THEN
        assertThrows(BidListNotFoundException.class, () -> {
            bidListService.getById(3);
        });
    }

    @Test
    public void givenABidListDTO_whenDelete_thenReturnsDeletedBidList()
            throws BidListNotFoundException {
        // GIVEN
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(2).setBidListId(3);
        listOfBidListDTO.get(2).setAccount("Account3");
        listOfBidListDTO.get(2).setType("Type3");
        listOfBidListDTO.get(2).setBidQuantity(new BigDecimal("3"));
        listOfBidList.add(new BidList());
        listOfBidList.get(2).setBidListId(3);
        listOfBidList.get(2).setAccount("Account3");
        listOfBidList.get(2).setType("Type3");
        listOfBidList.get(2).setBidQuantity(new BigDecimal("3"));
        given(bidListRepository.findById(3))
                .willReturn(Optional.of(listOfBidList.get(2)));
        given(bidListMapping.mapEntityToDTO(any(BidList.class)))
                .willReturn(listOfBidListDTO.get(2));
        // WHEN
        BidListDTO result = bidListService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfBidListDTO.get(2).toString());
    }

}
