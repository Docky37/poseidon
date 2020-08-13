package com.nnk.springboot.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.BidListDTO;

/**
 * This class is used to perform bidirectional mapping betwenn a BidList entity
 * and a BidListDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class BidListMapping {

    /**
     * This method is in charge of the mapping of a list of BidList entities to
     * a list of BidListDTO. Use the mapEntityToDTO(BidList bidList)as a sub
     * method.
     *
     * @param listBidList
     * @return a List<BidListDTO>
     */
    public List<BidListDTO> mapAListOfBidList(final List<BidList> listBidList) {
        List<BidListDTO> listBidListDTO = new ArrayList<>();
        for (BidList bidList : listBidList) {
            BidListDTO bidListDTO = mapEntityToDTO(bidList);
            listBidListDTO.add(bidListDTO);
        }
        return listBidListDTO;
    }

    /**
     * This method is in charge of the mapping of a BidList entity to a
     * BidListDTO.
     *
     * @param bidList
     * @return a BidListDTO
     */
    public BidListDTO mapEntityToDTO(final BidList bidList) {
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setBidListId(bidList.getBidListId());
        bidListDTO.setAccount(bidList.getAccount());
        bidListDTO.setType(bidList.getType());
        bidListDTO.setBidQuantity(bidList.getBidQuantity());
        return bidListDTO;
    }

    /**
     * This method is in charge of the mapping of a BidListDTO to a BidList
     * entity.
     *
     * @param bidListDTO
     * @return a BidList
     */
    public BidList mapDTOToEntity(final BidListDTO bidListDTO) {
        BidList bidList = new BidList();
        bidList.setBidListId(bidListDTO.getBidListId());
        bidList.setAccount(bidListDTO.getAccount());
        bidList.setType(bidListDTO.getType());
        bidList.setBidQuantity(bidListDTO.getBidQuantity());
        return bidList;
    }

}
