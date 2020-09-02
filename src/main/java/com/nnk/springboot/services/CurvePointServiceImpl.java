package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;

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
    public CurvePointDTO save(CurvePointDTO curvePointDTO) {
        CurvePoint curvePoint = curvePointMapping.mapDTOToEntity(curvePointDTO);
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        CurvePointDTO savedBidListDTO = curvePointMapping
                .mapEntityToDTO(savedCurvePoint);
        return savedBidListDTO;

    }

    /**
     * {@inheritDoc}
     *
     * @throws CurvePointNotFoundException
     */
    @Override
    public CurvePointDTO delete(Integer id) throws CurvePointNotFoundException {
        CurvePoint curvePoint = curvePointRepository.findByCurvePointId(id);
System.out.println(id);
        if (curvePoint != null) {
System.out.println("OK  not null !");
            curvePointRepository.deleteById(id);
            return curvePointMapping.mapEntityToDTO(curvePoint);
        }
        return null;
    }

    @Override
    public CurvePointDTO getById(Integer id)
            throws CurvePointNotFoundException {
        CurvePoint curvePoint = curvePointRepository.findByCurvePointId(id);
        CurvePointDTO curvePointDTO;
        if (curvePoint != null) {
            curvePointDTO = curvePointMapping.mapEntityToDTO(curvePoint);
        } else {
            throw new CurvePointNotFoundException(
                    "No CurvePoint record exist for given id");
        }

        return curvePointDTO;
    }

}
