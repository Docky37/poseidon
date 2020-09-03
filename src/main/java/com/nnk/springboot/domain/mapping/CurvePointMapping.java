package com.nnk.springboot.domain.mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.CurvePointDTO;

@Component
public class CurvePointMapping {

    public List<CurvePointDTO> mapAListOfCurvePoint(
            List<CurvePoint> listOfCurvePoint) {
        List<CurvePointDTO> listCurvePointDTO = new ArrayList<>();
        for (CurvePoint curvePoint : listOfCurvePoint) {
            CurvePointDTO curvePointDTO = mapEntityToDTO(curvePoint);
            listCurvePointDTO.add(curvePointDTO);
        }
        return listCurvePointDTO;
    }

    public CurvePoint mapDTOToEntity(CurvePointDTO curvePointDTO) {
        CurvePoint curvePoint = new CurvePoint();
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

    public CurvePointDTO mapEntityToDTO(CurvePoint curvePoint) {
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
