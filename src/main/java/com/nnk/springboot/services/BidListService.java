package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.BidListDTO;

public interface BidListService {

	List<BidListDTO> findAll();

	void add(@Valid BidListDTO bidListDTO);

	void update(@Valid BidListDTO bidListDTO);

	void delete(Integer id);

	BidListDTO getById(Integer id);

}
