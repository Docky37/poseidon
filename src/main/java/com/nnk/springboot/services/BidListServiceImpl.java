package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.domain.mapping.BidListMapping;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * Implementation class of the BidListService interface, this class answer to
 * BidListController request using BidListRepository and bidListMapping classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class BidListServiceImpl implements BidListService {

    /**
     * BidListRepository bean injected by Spring when service is created.
     */
    @Autowired
    private BidListRepository bidListRepository;

    /**
     * BidListMapping bean injected by Spring when service is created.
     */
    @Autowired
    private BidListMapping bidListMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BidListDTO> findAll() {
        List<BidList> listBidList = new ArrayList<>();
        listBidList = bidListRepository.findAll();

        List<BidListDTO> listBidListDTO =
                bidListMapping.mapAListOfBidList(listBidList);

        return listBidListDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListDTO save(final BidListDTO bidListDTO) {
        BidList bidList = bidListMapping.mapDTOToEntity(bidListDTO);
        BidList savedBidList = bidListRepository.save(bidList);
        BidListDTO savedBidListDTO =
                bidListMapping.mapEntityToDTO(savedBidList);
        return savedBidListDTO;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListDTO delete(final Integer id) {
        BidListDTO bidListDTO = getById(id);
        if (bidListDTO != null) {
            bidListRepository.deleteById(id);
        }
        return bidListDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListDTO getById(final Integer id) {
        BidList bidList = bidListRepository.findByBidListId(id);
        BidListDTO bidListDTO = bidListMapping.mapEntityToDTO(bidList);
        return bidListDTO;
    }

}
