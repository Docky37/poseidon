package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.CurvePointDTO;
import com.nnk.springboot.domain.mapping.CurvePointMapping;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;

/**
 * Implementation class of the CurvePointService interface, this class answer to
 * CurvePointController request using CurvePointRepository and bidListMapping
 * classes.
 *
 * @author Thierry Schreiner
 */
@Service
public class CurvePointServiceImpl implements CurvePointService {

    /**
     * CurvePointRepository bean injected by Spring when service is created.
     */
    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * CurvePointMapping bean injected by Spring when service is created.
     */
    @Autowired
    private CurvePointMapping curvePointMapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CurvePointDTO> findAll() {
        List<CurvePoint> listCurvePoint = new ArrayList<>();
        listCurvePoint = curvePointRepository.findAll();

        List<CurvePointDTO> listCurvePointDTO = curvePointMapping
                .mapAListOfCurvePoint(listCurvePoint);

        return listCurvePointDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurvePointDTO save(final CurvePointDTO curvePointDTO) {
        CurvePoint curvePoint = curvePointMapping.mapDTOToEntity(curvePointDTO);
        System.out.println(curvePoint.toString());
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        System.out.println(savedCurvePoint.toString());
        CurvePointDTO savedBidListDTO = curvePointMapping
                .mapEntityToDTO(savedCurvePoint);
        System.out.println(savedBidListDTO.toString());
        return savedBidListDTO;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurvePointDTO delete(final Integer id)
            throws CurvePointNotFoundException {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if (curvePoint.isPresent()) {
            curvePointRepository.deleteById(id);
            return curvePointMapping.mapEntityToDTO(curvePoint.get());
        } else {
            throw (new CurvePointNotFoundException("No CurvePoint record exist for given id"));
        }        
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurvePointDTO getById(final Integer id)
            throws CurvePointNotFoundException {
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new CurvePointNotFoundException(
                        "No CurvePoint record exist for given id"));

        return curvePointMapping.mapEntityToDTO(curvePoint);
    }

}
