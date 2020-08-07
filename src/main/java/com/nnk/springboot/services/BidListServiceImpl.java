package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListServiceImpl implements BidListService {

	@Autowired
	BidListRepository bidListRepository;
	
	@Override
	public List<BidList> findAll() {
		List<BidList> listBidList = new ArrayList<>();
		listBidList = bidListRepository.findAll();
		return listBidList;
	}

}
