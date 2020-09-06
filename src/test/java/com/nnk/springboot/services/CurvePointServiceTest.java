package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.mapping.CurvePointMapping;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.exceptions.CurvePointNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.CurvePointServiceImpl;

@SpringJUnitConfig(value = CurvePointServiceImpl.class)
public class CurvePointServiceTest {

    @Autowired
    CurvePointService curvePointService;

    @MockBean
    CurvePointRepository curvePointRepository;

    @MockBean
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
        listOfCurvePoint.get(1).setAsOfDate(LocalDateTime.now());

        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(0).setCurvePointId(1);
        listOfCurvePointDTO.get(0).setAsOfDate(LocalDateTime.now());
        listOfCurvePointDTO.get(0).setTerm(new BigDecimal("150"));
        listOfCurvePointDTO.get(0).setValue(new BigDecimal("140"));
        listOfCurvePointDTO.get(0).setAsOfDate(LocalDateTime.now());
        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(1).setCurvePointId(2);
        listOfCurvePointDTO.get(1).setAsOfDate(LocalDateTime.now());
        listOfCurvePointDTO.get(1).setTerm(new BigDecimal("100"));
        listOfCurvePointDTO.get(1).setValue(new BigDecimal("101"));
        listOfCurvePointDTO.get(1).setAsOfDate(LocalDateTime.now());
    }

    @Test
    public void whenFindAll_thenReturnsListOfAllCurvePoints() {
        // GIVEN
        given(curvePointRepository.findAll()).willReturn(listOfCurvePoint);
        given(curvePointMapping.mapAListOfCurvePoint(listOfCurvePoint))
                .willReturn(listOfCurvePointDTO);
        // WHEN
        List<CurvePointDTO> resultList = curvePointService.findAll();
        // THEN
        assertThat(resultList.toString())
                .isEqualTo(listOfCurvePointDTO.toString());
    }

    @Test
    public void givenACurvePointDTO_whenSave_thenReturnsSavedObject() {
        // GIVEN
        given(curvePointMapping.mapDTOToEntity(listOfCurvePointDTO.get(0)))
                .willReturn(listOfCurvePoint.get(0));
        given(curvePointRepository.save(listOfCurvePoint.get(0)))
                .willReturn(listOfCurvePoint.get(0));
        given(curvePointMapping.mapEntityToDTO(any(CurvePoint.class)))
                .willReturn(listOfCurvePointDTO.get(0));
        // WHEN
        CurvePointDTO result = curvePointService
                .save(listOfCurvePointDTO.get(0));
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfCurvePointDTO.get(0).toString());
    }

    @Test
    public void whenGetById_thenReturnsExpectedCurvePoint()
            throws CurvePointNotFoundException {
        // GIVEN
        given(curvePointRepository.findById(2))
                .willReturn(Optional.of(listOfCurvePoint.get(1)));
        given(curvePointMapping.mapEntityToDTO(any(CurvePoint.class)))
                .willReturn(listOfCurvePointDTO.get(1));
        // WHEN
        CurvePointDTO result = curvePointService.getById(2);
        // THEN
        System.out.println(result);
        assertThat(result.toString())
                .isEqualTo(listOfCurvePointDTO.get(1).toString());
    }

    @Test
    public void givenAnUnknownId_whenGetById_thenCurvePointNotFoundException()
            throws CurvePointNotFoundException {
        // GIVEN
        // WHEN - THEN
        assertThrows(CurvePointNotFoundException.class, () -> {
            curvePointService.getById(3);
        });
    }

    @Test
    public void givenACurvePointDTO_whenDelete_thenReturnsDeletedCurvePoint()
            throws CurvePointNotFoundException {
        // GIVEN
        listOfCurvePointDTO.add(new CurvePointDTO());
        listOfCurvePointDTO.get(2).setCurvePointId(3);
        listOfCurvePointDTO.get(2).setAsOfDate(LocalDateTime.now());
        listOfCurvePointDTO.get(2).setTerm(new BigDecimal("50"));
        listOfCurvePointDTO.get(2).setValue(new BigDecimal("40"));
        listOfCurvePointDTO.get(2).setAsOfDate(LocalDateTime.now());
        listOfCurvePoint.add(new CurvePoint());
        listOfCurvePoint.get(2).setCurvePointId(1);
        listOfCurvePoint.get(2).setAsOfDate(LocalDateTime.now());
        listOfCurvePoint.get(2).setTerm(new BigDecimal("150"));
        listOfCurvePoint.get(2).setValue(new BigDecimal("140"));
        listOfCurvePoint.get(2).setAsOfDate(LocalDateTime.now());
        given(curvePointRepository.findById(3))
                .willReturn(Optional.of(listOfCurvePoint.get(2)));
        given(curvePointMapping.mapEntityToDTO(any(CurvePoint.class)))
                .willReturn(listOfCurvePointDTO.get(2));
        // WHEN
        CurvePointDTO result = curvePointService.delete(3);
        // THEN
        assertThat(result.toString())
                .isEqualTo(listOfCurvePointDTO.get(2).toString());
    }

}
