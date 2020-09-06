package com.nnk.springboot.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.stereotype.Component;

import com.nnk.springboot.constants.Constants;

/**
 * Data transfer object that contains only 4 attributes of a BidList.
 *
 * @author Thierry Schreiner
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidListDTO {

    /**
     * Id of the DTO.
     */
    private Integer bidListId;

    /**
     * The BidListDTO account name.
     */
    @NotBlank(message = "Account is mandatory")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String account;

    /**
     * The BidListDTO type.
     */
    @NotBlank(message = "Type is mandatory")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String type;

    /**
     * The number of bid for sale.
     */
    @NotNull(message = "Bid quantity is mandatory")
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
    message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    private BigDecimal bidQuantity;

}
