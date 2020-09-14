package com.nnk.springboot.domain.mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.TradeFullDTO;
import com.nnk.springboot.util.UserRetrieve;

/**
 * This class is used to perform bidirectional mapping between a Trade entity
 * and a TradeDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class TradeMapping {

    /**
     * Spring autowired UserRetrieve instance declaration.
     */
    @Autowired
    private UserRetrieve userRetrieve;

    /**
     * This method is in charge of the mapping of a list of Trade entities to a
     * list of TradeDTO. Use the mapEntityToDTO(Trade trade)as a sub method.
     *
     * @param listOfTrade
     * @return a List<TradeDTO>
     */
    public List<TradeDTO> mapAListOfTrade(final List<Trade> listOfTrade) {
        List<TradeDTO> listTradeDTO = new ArrayList<>();
        for (Trade trade : listOfTrade) {
            TradeDTO tradeDTO = mapEntityToDTO(trade);
            listTradeDTO.add(tradeDTO);
        }
        return listTradeDTO;
    }

    /**
     * This method is in charge of the mapping of a TradeDTO to a Trade Entity.
     *
     * @param tradeDTO
     * @return a Trade object
     */
    public Trade mapDTOToEntity(final TradeDTO tradeDTO) {
        String connectedUser = userRetrieve.getLoggedUser();
        Trade trade = new Trade();
        trade.setTradeId(tradeDTO.getTradeId());
        trade.setAccount(tradeDTO.getAccount());
        trade.setType(tradeDTO.getType());
        trade.setBuyQuantity(tradeDTO.getBuyQuantity());
        trade.setSellQuantity(tradeDTO.getSellQuantity());
        trade.setBuyPrice(tradeDTO.getBuyPrice());
        trade.setSellPrice(tradeDTO.getSellPrice());
        trade.setCreationName(connectedUser);
        trade.setCreationDate(LocalDateTime.now());
        trade.setRevisionName(connectedUser);
        trade.setRevisionDate(LocalDateTime.now());
        return trade;
    }

    /**
     * This method is in charge of the mapping of a Trade entity to a TradeDTO.
     *
     * @param trade
     * @return a TradeDTO object
     */
    public TradeDTO mapEntityToDTO(final Trade trade) {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setTradeId(trade.getTradeId());
        tradeDTO.setAccount(trade.getAccount());
        tradeDTO.setType(trade.getType());
        tradeDTO.setBuyQuantity(trade.getBuyQuantity());
        tradeDTO.setSellQuantity(trade.getSellQuantity());
        tradeDTO.setBuyPrice(trade.getBuyPrice());
        tradeDTO.setSellPrice(trade.getSellPrice());

        return tradeDTO;
    }

    /**
     * This method is in charge of the mapping of a Trade entity to a
     * TradeFullDTO.
     *
     * @param trade
     * @return a TradeDTO
     */
    public TradeFullDTO mapEntityToFullDTO(final Trade trade) {
        TradeFullDTO tradeDTO = new TradeFullDTO();
        tradeDTO.setTradeId(trade.getTradeId());
        tradeDTO.setAccount(trade.getAccount());
        tradeDTO.setType(trade.getType());
        tradeDTO.setBuyQuantity(trade.getBuyQuantity());
        tradeDTO.setSellQuantity(trade.getSellQuantity());
        tradeDTO.setBuyPrice(trade.getBuyPrice());
        tradeDTO.setSellPrice(trade.getSellPrice());
        tradeDTO.setTradeDate(trade.getTradeDate());
        tradeDTO.setSecurity(trade.getSecurity());
        tradeDTO.setTrader(trade.getTrader());
        tradeDTO.setStatus(trade.getStatus());
        tradeDTO.setBenchmark(trade.getBenchmark());
        tradeDTO.setBook(trade.getBook());
        tradeDTO.setCreationName(trade.getCreationName());
        tradeDTO.setCreationDate(trade.getCreationDate());
        tradeDTO.setRevisionName(trade.getRevisionName());
        tradeDTO.setRevisionDate(trade.getRevisionDate());
        tradeDTO.setDealName(trade.getDealName());
        tradeDTO.setDealType(trade.getDealType());
        tradeDTO.setSourceListId(trade.getSourceListId());
        tradeDTO.setSide(trade.getSide());

        return tradeDTO;
    }

    /**
     * This method is in charge of the mapping of a TradeFullDTO to a Trade
     * entity.
     *
     * @param tradeDTO
     * @return a Trade
     */
    public Trade mapFullDTOToEntity(final TradeFullDTO tradeDTO) {
        String connectedUser = userRetrieve.getLoggedUser();
        Trade trade = new Trade();
        trade.setTradeId(tradeDTO.getTradeId());
        trade.setAccount(tradeDTO.getAccount());
        trade.setType(tradeDTO.getType());
        trade.setBuyQuantity(tradeDTO.getBuyQuantity());
        trade.setSellQuantity(tradeDTO.getSellQuantity());
        trade.setBuyPrice(tradeDTO.getBuyPrice());
        trade.setSellPrice(tradeDTO.getSellPrice());
        trade.setTradeDate(tradeDTO.getTradeDate());
        trade.setSecurity(tradeDTO.getSecurity());
        trade.setTrader(tradeDTO.getTrader());
        trade.setStatus(tradeDTO.getStatus());
        trade.setBenchmark(tradeDTO.getBenchmark());
        trade.setBook(tradeDTO.getBook());
        trade.setCreationName(tradeDTO.getCreationName());
        trade.setCreationDate(tradeDTO.getCreationDate());
        trade.setRevisionName(connectedUser);
        trade.setRevisionDate(LocalDateTime.now());
        trade.setDealName(tradeDTO.getDealName());
        trade.setDealType(tradeDTO.getDealType());
        trade.setSourceListId(tradeDTO.getSourceListId());
        trade.setSide(tradeDTO.getSide());

        return trade;
    }

}
