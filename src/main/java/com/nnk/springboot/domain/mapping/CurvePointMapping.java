package com.nnk.springboot.domain.mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;

/**
 * This class is used to perform bidirectional mapping between a CurvePoint
 * entity and a CurvePointDTO.
 *
 * @author Thierry Schreiner
 */
@Component
public class CurvePointMapping {

    /**
     * This method is in charge of the mapping of a list of CurvePoint entities
     * to a list of CurvePointDTO. Use the mapEntityToDTO(CurvePoint
     * curvePoint)as a sub method.
     *
     * @param listOfCurvePoint
     * @return a List<CurvePointDTO>
     */
    public List<CurvePointDTO> mapAListOfCurvePoint(
            final List<CurvePoint> listOfCurvePoint) {
        List<CurvePointDTO> listCurvePointDTO = new ArrayList<>();
        for (CurvePoint curvePoint : listOfCurvePoint) {
            CurvePointDTO curvePointDTO = mapEntityToDTO(curvePoint);
            listCurvePointDTO.add(curvePointDTO);
        }
        return listCurvePointDTO;
    }

    /**
     * This method is in charge of the mapping of a CurvePointDTO to a
     * CurvePoint entity.
     *
     * @param curvePointDTO
     * @return a CurvePoint object
     */
    public CurvePoint mapDTOToEntity(final CurvePointDTO curvePointDTO) {
        final CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurvePointId(curvePointDTO.getCurvePointId());
        curvePoint.setCurveId(curvePointDTO.getCurveId());
        curvePoint.setAsOfDate(curvePointDTO.getAsOfDate());
        curvePoint.setTerm(curvePointDTO.getTerm());
        curvePoint.setValue(curvePointDTO.getValue());
        if (curvePointDTO.getCreationDate() == null) {
            curvePoint.setCreationDate(LocalDateTime.now());
        } else {
            curvePoint.setCreationDate(curvePointDTO.getCreationDate());
        }

        return curvePoint;
    }

    /**
     * This method is in charge of the mapping of a CurvePoint entity to a
     * CurvePointDTO.
     *
     * @param curvePoint
     * @return a CurvePointDTO
     */
    public CurvePointDTO mapEntityToDTO(final CurvePoint curvePoint) {
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setCurvePointId(curvePoint.getCurvePointId());
        curvePointDTO.setCurveId(curvePoint.getCurveId());
        curvePointDTO.setAsOfDate(curvePoint.getAsOfDate());
        curvePointDTO.setTerm(curvePoint.getTerm());
        curvePointDTO.setValue(curvePoint.getValue());
        curvePointDTO.setCreationDate(curvePoint.getCreationDate());
        return curvePointDTO;
    }

}
