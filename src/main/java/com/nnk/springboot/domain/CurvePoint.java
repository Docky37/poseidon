package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.nnk.springboot.constants.Constants;

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
    @NotNull(message = "Curve id mandatory to avoid orphan curve point!")
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
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    @Getter
    @Setter
    private BigDecimal term;

    /**
     * Value BigDecimal field.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
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
