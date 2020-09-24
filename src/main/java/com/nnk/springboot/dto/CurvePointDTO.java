package com.nnk.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.nnk.springboot.constants.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data transfer object for CurvePoint.
 *
 * @author Thierry Schreiner
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Details of a CurvePoint.")
public class CurvePointDTO {
    /**
     * Id of the CurvePointDTO.
     */
    @ApiModelProperty(hidden = true)
    @Getter
    @Setter
    private Integer curvePointId;

    /**
     * Id of the curve that contains this curvePoint(Foreign Key).
     */
    @ApiModelProperty(notes = "The link to the mother curve.", required = true)
    @NotNull(message = "Curve id mandatory to avoid orphan curve point!")
    @Max(value = Constants.MAX_POSITIVE_INTEGER)
    @Getter
    @Setter
    private Integer curveId;

    /**
     * The date of the CurvePoint data.
     */
    @ApiModelProperty(notes = "The date of the CurvePoint data that cannot be"
            + " in the futur.", required = true)
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    @Past(message = "As of date cannot be in future!")
    private LocalDateTime asOfDate;

    /**
     * Term BigDecimal field.
     */
    @ApiModelProperty(notes = "The term value. Must be positive!",
            required = true)
    @Positive(message = "Term must be positive!")
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
    message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal term;

    /**
     * Value BigDecimal field.
     */
    @ApiModelProperty(notes = "The value at asOfDate. Must be positive!",
            required = true)
    @Positive(message = "Term must be positive!")
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
    message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal value;

    /**
     * The date & time when user has created the CurvePoint record.
     */
    @ApiModelProperty(notes = "The date & time when user has created the"
            + " CurvePoint record. Setted by the application.", required = true)
    @Getter
    @Setter
    private LocalDateTime creationDate;

}
