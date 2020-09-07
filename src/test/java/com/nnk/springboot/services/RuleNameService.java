package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.dto.RuleNameDTO;

public interface RuleNameService {

    List<RuleNameDTO> findAll();

    RuleNameDTO save(RuleNameDTO ruleNameDTO);

    RuleNameDTO getById(int id);

    RuleNameDTO delete(int id);

}
