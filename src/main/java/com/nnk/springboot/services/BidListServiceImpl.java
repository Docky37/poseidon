package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.mapping.BidListMapping;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.BidListFullDTO;
import com.nnk.springboot.exceptions.BidListNotFoundException;
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

        return bidListMapping
                .mapAListOfBidList(bidListRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListDTO save(final BidListDTO bidListDTO) {
        BidList bidList = bidListMapping.mapDTOToEntity(bidListDTO);
        BidList savedBidList = bidListRepository.save(bidList);
        BidListDTO savedBidListDTO = bidListMapping
                .mapEntityToDTO(savedBidList);
        return savedBidListDTO;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListFullDTO saveFullDTO(@Valid final BidListFullDTO bidListDTO) {
        BidList bidList = bidListMapping.mapFullDTOToEntity(bidListDTO);
        BidList savedBidList = bidListRepository.save(bidList);
        BidListFullDTO savedBidListDTO = bidListMapping
                .mapEntityToFullDTO(savedBidList);
        return savedBidListDTO;
    }

    /**
     * {@inheritDoc}
     *
     * @throws BidListNotFoundException
     */
    @Override
    public BidListDTO delete(final Integer id) throws BidListNotFoundException {
        BidList bidList = bidListRepository.findById(id)
                .orElseThrow(() -> new BidListNotFoundException(
                        "No BidList record exist for given id"));
        return bidListMapping.mapEntityToDTO(bidList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BidListFullDTO getById(final Integer id)
            throws BidListNotFoundException {
        BidList bidList = bidListRepository.findById(id)
                .orElseThrow(() -> new BidListNotFoundException(
                        "No BidList record exist for given id"));

        return bidListMapping.mapEntityToFullDTO(bidList);
    }

}
