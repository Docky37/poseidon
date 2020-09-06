package com.nnk.springboot.model.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.mapping.CurvePointMapping;
import com.nnk.springboot.dto.CurvePointDTO;

@SpringJUnitConfig(value = CurvePointMapping.class)
public class CurvePointMappingTest {

    @Autowired
    CurvePointMapping curvePointMapping;

    static List<CurvePoint> listOfCurvePoint = new ArrayList<>();
    static List<CurvePointDTO> listOfCurvePointDTO = new ArrayList<>();
    static {
        listOfCurvePoint.add(new CurvePoint());
        listOfCurvePoint.get(0).setCurvePointId(1);
        listOfCurvePoint.get(0).setAsOfDate(LocalDateTime.now());
        listOfCurvePoint.get(0).setTerm(new BigDecimal("150"));
        listOfCurvePoint.get(0).setValue(new BigDecimal("140"));
        listOfCurvePoint.get(0).setAsOfDate(LocalDateTime.now());
        listOfCurvePoint.add(new CurvePoint());
        listOfCurvePoint.get(1).setCurvePointId(2);
        listOfCurvePoint.get(1).setAsOfDate(LocalDateTime.now());
        listOfCurvePoint.get(1).setTerm(new BigDecimal("100"));
        listOfCurvePoint.get(1).setValue(new BigDecimal("101"));
        listOfCurvePoint.get(1).setCreationDate(LocalDateTime.now());

        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(0).setCurvePointId(1);
        listOfCurvePointDTO.get(0)
                .setAsOfDate(listOfCurvePoint.get(0).getAsOfDate());
        listOfCurvePointDTO.get(0).setTerm(new BigDecimal("150"));
        listOfCurvePointDTO.get(0).setValue(new BigDecimal("140"));
        listOfCurvePointDTO.get(0)
                .setCreationDate(listOfCurvePoint.get(0).getCreationDate());
        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(1).setCurvePointId(2);
        listOfCurvePointDTO.get(1)
                .setAsOfDate(listOfCurvePoint.get(1).getAsOfDate());
        listOfCurvePointDTO.get(1).setTerm(new BigDecimal("100"));
        listOfCurvePointDTO.get(1).setValue(new BigDecimal("101"));
        listOfCurvePointDTO.get(1)
                .setCreationDate(listOfCurvePoint.get(1).getCreationDate());
    }

    @Test
    public void givenAListOfCurvePoint_whenMapToDTO_thenReturnsAListOfCurvePointDTO() {
        // GIVEN
        // WHEN
        List<CurvePointDTO> resultList = curvePointMapping
                .mapAListOfCurvePoint(listOfCurvePoint);
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfCurvePointDTO.toString());

    }

    @Test
    public void givenACurvePointDTO_whenMapToEntity_thenReturnsCurvePoint() {
        // GIVEN
        // WHEN
        CurvePoint result = curvePointMapping
                .mapDTOToEntity(listOfCurvePointDTO.get(0));
        // THEN
        result.setCreationDate(null);
        assertThat(result.toString())
                .isEqualTo(listOfCurvePoint.get(0).toString());

    }

}
