package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.domain.mapping.BidListMapping;
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
    static {
        listOfBidList.add(new BidList());
        listOfBidList.get(0).setBidListId(1);
        listOfBidList.get(0).setAccount("Account1");
        listOfBidList.get(0).setAccount("Type1");
        listOfBidList.get(0).setBidQuantity(1D);
        listOfBidList.add(new BidList());
        listOfBidList.get(1).setBidListId(2);
        listOfBidList.get(1).setAccount("Account2");
        listOfBidList.get(1).setAccount("Type2");
        listOfBidList.get(1).setBidQuantity(2D);

        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(0).setBidListId(1);
        listOfBidListDTO.get(0).setAccount("Account1");
        listOfBidListDTO.get(0).setAccount("Type1");
        listOfBidListDTO.get(0).setBidQuantity(1D);
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(1).setBidListId(2);
        listOfBidListDTO.get(1).setAccount("Account2");
        listOfBidListDTO.get(1).setAccount("Type2");
        listOfBidListDTO.get(1).setBidQuantity(2D);
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
        given(bidListRepository.findByBidListId(2))
                .willReturn(listOfBidList.get(1));
        given(bidListMapping.mapEntityToDTO(any(BidList.class)))
                .willReturn(listOfBidListDTO.get(1));
        // WHEN
        BidListDTO result = bidListService.getById(2);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfBidListDTO.get(1).toString());
    }

    @Test
    public void givenABidListDTO_whenDelete_thenReturnsDeletedBidList()
            throws BidListNotFoundException {
        // GIVEN
        listOfBidListDTO.add(new BidListDTO());
        listOfBidListDTO.get(2).setBidListId(3);
        listOfBidListDTO.get(2).setAccount("Account3");
        listOfBidListDTO.get(2).setAccount("Type3");
        listOfBidListDTO.get(2).setBidQuantity(3D);
        listOfBidList.add(new BidList());
        listOfBidList.get(2).setBidListId(3);
        listOfBidList.get(2).setAccount("Account3");
        listOfBidList.get(2).setAccount("Type3");
        listOfBidList.get(2).setBidQuantity(3D);
        given(bidListRepository.findByBidListId(3))
                .willReturn(listOfBidList.get(2));
        given(bidListMapping.mapEntityToDTO(any(BidList.class)))
                .willReturn(listOfBidListDTO.get(2));
        // WHEN
        BidListDTO result = bidListService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfBidListDTO.get(2).toString());
    }

}