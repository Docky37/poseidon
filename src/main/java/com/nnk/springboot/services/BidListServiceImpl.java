package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServiceImpl implements BidListService {

	@Autowired
	BidListRepository bidListRepository;

	@Override
	public List<BidListDTO> findAll() {
		List<BidList> listBidList = new ArrayList<>();
		listBidList = bidListRepository.findAll();

		List<BidListDTO> listBidListDTO = new ArrayList<>();
		for (BidList bidList : listBidList) {
			BidListDTO bidListDTO = mapEntityToDTO(bidList);
			listBidListDTO.add(bidListDTO);
		}
		return listBidListDTO;
	}

	@Override
	public void add(@Valid final BidListDTO bidListDTO) {
		BidList bidList = mapDTOToEntity(bidListDTO);
		bidListRepository.save(bidList);
	}

	@Override
	public void update(@Valid BidListDTO bidListDTO) {
		BidList bidList = mapDTOToEntity(bidListDTO);
		bidListRepository.save(bidList);

	}

	@Override
	public void delete(Integer id) {
		bidListRepository.deleteById(id);
	}

	@Override
	public BidListDTO getById(final Integer id) {
		BidList bidList = bidListRepository.findByBidListId(id);
		BidListDTO bidListDTO = mapEntityToDTO(bidList);
		return bidListDTO;
	}

	public BidListDTO mapEntityToDTO(BidList bidList) {
		BidListDTO bidListDTO = new BidListDTO();
		bidListDTO.setBidListId(bidList.getBidListId());
		bidListDTO.setAccount(bidList.getAccount());
		bidListDTO.setType(bidList.getType());
		bidListDTO.setBidQuantity(bidList.getBidQuantity());
		return bidListDTO;
	}

	public BidList mapDTOToEntity(BidListDTO bidListDTO) {
		BidList bidList = new BidList();
		bidList.setBidListId(bidListDTO.getBidListId());
		bidList.setAccount(bidListDTO.getAccount());
		bidList.setType(bidListDTO.getType());
		bidList.setBidQuantity(bidListDTO.getBidQuantity());
		return bidList;
	}

}
