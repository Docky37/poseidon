package com.nnk.springboot.domain.mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.BidListFullDTO;
import com.nnk.springboot.util.UserRetrieve;

/**
 * This class is used to perform bidirectional mapping between a BidList entity
 * and a BidListDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class BidListMapping {

    /**
     * Spring autowired UserRetrieve instance declaration.
     */
    @Autowired
    private UserRetrieve userRetrieve;

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
     * This method is in charge of the mapping of a BidList entity to a
     * BidListFullDTO.
     *
     * @param bidList
     * @return a BidListFullDTO
     */
    public BidListFullDTO mapEntityToFullDTO(final BidList bidList) {
        BidListFullDTO bidListDTO = new BidListFullDTO();
        bidListDTO.setBidListId(bidList.getBidListId());
        bidListDTO.setAccount(bidList.getAccount());
        bidListDTO.setType(bidList.getType());
        bidListDTO.setBidQuantity(bidList.getBidQuantity());
        bidListDTO.setAskQuantity(bidList.getAskQuantity());
        bidListDTO.setBid(bidList.getBid());
        bidListDTO.setAsk(bidList.getAsk());
        bidListDTO.setBenchmark(bidList.getBenchmark());
        bidListDTO.setBidListDate(bidList.getBidListDate());
        bidListDTO.setCommentary(bidList.getCommentary());
        bidListDTO.setSecurity(bidList.getSecurity());
        bidListDTO.setStatus(bidList.getStatus());
        bidListDTO.setTrader(bidList.getTrader());
        bidListDTO.setBook(bidList.getBook());
        bidListDTO.setCreationName(bidList.getCreationName());
        bidListDTO.setCreationDate(bidList.getCreationDate());
        bidListDTO.setRevisionName(bidList.getRevisionName());
        bidListDTO.setRevisionDate(bidList.getRevisionDate());
        bidListDTO.setDealName(bidList.getDealName());
        bidListDTO.setDealType(bidList.getDealType());
        bidListDTO.setSourceListId(bidList.getSourceListId());
        bidListDTO.setSide(bidList.getSide());

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
        String connectedUser = userRetrieve.getLoggedUser();
        BidList bidList = new BidList();
        bidList.setBidListId(bidListDTO.getBidListId());
        bidList.setAccount(bidListDTO.getAccount());
        bidList.setType(bidListDTO.getType());
        bidList.setBidQuantity(bidListDTO.getBidQuantity());
        bidList.setCreationName(connectedUser);
        bidList.setCreationDate(LocalDateTime.now());
        bidList.setRevisionName(connectedUser);
        bidList.setRevisionDate(LocalDateTime.now());
        return bidList;
    }

    /**
     * This method is in charge of the mapping of a BidListFullDTO to a BidList
     * entity.
     *
     * @param bidListDTO
     * @return a BidList
     */
    public BidList mapFullDTOToEntity(final BidListFullDTO bidListDTO) {
        String connectedUser = userRetrieve.getLoggedUser();
        BidList bidList = new BidList();
        bidList.setBidListId(bidListDTO.getBidListId());
        bidList.setAccount(bidListDTO.getAccount());
        bidList.setType(bidListDTO.getType());
        bidList.setBidQuantity(bidListDTO.getBidQuantity());
        bidList.setAskQuantity(bidListDTO.getAskQuantity());
        bidList.setBid(bidListDTO.getBid());
        bidList.setAsk(bidListDTO.getAsk());
        bidList.setBenchmark(bidListDTO.getBenchmark());
        bidList.setBidListDate(bidListDTO.getBidListDate());
        bidList.setCommentary(bidListDTO.getCommentary());
        bidList.setSecurity(bidListDTO.getSecurity());
        bidList.setStatus(bidListDTO.getStatus());
        bidList.setTrader(bidListDTO.getTrader());
        bidList.setBook(bidListDTO.getBook());
        bidList.setCreationDate(bidListDTO.getCreationDate());
        bidList.setCreationName(bidListDTO.getCreationName());
        bidList.setRevisionName(connectedUser);
        bidList.setRevisionDate(LocalDateTime.now());
        bidList.setDealName(bidListDTO.getDealName());
        bidList.setDealType(bidListDTO.getDealType());
        bidList.setSourceListId(bidListDTO.getSourceListId());
        bidList.setSide(bidListDTO.getSide());

        return bidList;
    }

}
