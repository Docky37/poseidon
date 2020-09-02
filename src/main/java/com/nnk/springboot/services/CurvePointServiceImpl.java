package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.CurvePointDTO;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;

public class CurvePointServiceImpl implements CurvePointService {

    @Override
    public List<CurvePointDTO> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CurvePointDTO save(CurvePointDTO curvePointDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CurvePointDTO saveFullDTO(@Valid CurvePointDTO curvePointDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CurvePointDTO delete(Integer id) throws CurvePointNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CurvePointDTO getById(Integer id)
            throws CurvePointNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
