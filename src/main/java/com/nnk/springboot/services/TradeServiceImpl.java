package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.mapping.TradeMapping;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.exceptions.TradeNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * Implementation class of the TradeService interface, this class answer to
 * TradeController request using TradeRepository and tradeMapping classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class TradeServiceImpl implements TradeService {

    /**
     * TradeRepository bean injected by Spring when service is created.
     */
    @Autowired
    private TradeRepository tradeRepository;

    /**
     * TradeMapping bean injected by Spring when service is created.
     */
    @Autowired
    private TradeMapping tradeMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TradeDTO> findAll() {
        List<Trade> listTrade = new ArrayList<>();
        listTrade = tradeRepository.findAll();

        List<TradeDTO> listTradeDTO = tradeMapping.mapAListOfTrade(listTrade);

        return listTradeDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TradeDTO save(TradeDTO tradeDTO) {
        Trade trade = tradeMapping.mapDTOToEntity(tradeDTO);
        Trade savedTrade = tradeRepository.save(trade);
        TradeDTO savedTradeDTO = tradeMapping.mapEntityToDTO(savedTrade);
        return savedTradeDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TradeFullDTO saveFullDTO(@Valid final TradeFullDTO tradeDTO) {
        Trade trade = tradeMapping.mapFullDTOToEntity(tradeDTO);
        Trade savedTrade = tradeRepository.save(trade);
        TradeFullDTO savedTradeDTO = tradeMapping
                .mapEntityToFullDTO(savedTrade);
        return savedTradeDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws TradeNotFoundException
     */
    @Override
    public TradeDTO delete(int id) throws TradeNotFoundException {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new TradeNotFoundException(
                        "No RuleName record exist for given id"));
        if (trade != null) {
            tradeRepository.deleteById(id);
            return tradeMapping.mapEntityToDTO(trade);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @throws TradeNotFoundException 
     */
    @Override
    public TradeDTO getById(int id) throws TradeNotFoundException {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new TradeNotFoundException(
                        "No RuleName record exist for given id"));

        TradeFullDTO tradeDTO;
        if (trade != null) {
            tradeDTO = tradeMapping.mapEntityToFullDTO(trade);
        } else {
            throw new TradeNotFoundException(
                    "No Trade record exist for given id");
        }

        return tradeDTO;
    }

}
