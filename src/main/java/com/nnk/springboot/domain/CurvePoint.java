package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class is the entity in relation with the table of CurvePoint.
 *
 * @author Thierry Schreiner
 */
@Entity
@Table(name = "curve_point")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurvePoint {
    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer curvePointId;

    /**
     * Id of the curve that contains this curvePoint (Foreign Key).
     */
    @NotNull
    @Getter
    @Setter
    private Integer curveId;

    /**
     * The date of the CurvePoint data.
     */
    @Getter
    @Setter
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