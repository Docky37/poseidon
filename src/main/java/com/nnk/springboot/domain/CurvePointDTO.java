package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurvePointDTO {
    /**
     * Id of the entity mapped to primary key field.
     */
    @Getter
    @Setter
    private Integer curvePointId;

    /**
     * Id of the curve that contains this curvePoint(Foreign Key).
     */
    @NotNull(message = "Curve id mandatory to avoid orphan curve point!")
    @Getter
    @Setter
    private Integer curveId;

    /**
     * The date of the CurvePoint data.
     */
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    private LocalDateTime asOfDate;

    /**
     * Term BigDecimal field.
     */
    @Getter
    @Setter
    private BigDecimal term;

    /**
     * Value BigDecimal field.
     */
    @Getter
    @Setter
    private BigDecimal value;

    /**
     * The date & time when user has created the CurvePoint record.
     */
    @Getter
    @Setter
    private LocalDateTime creationDate;

}
