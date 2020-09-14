package com.nnk.springboot.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.nnk.springboot.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is the entity in relation with the table of trade.
 *
 * @author Thierry Schreiner
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeDTO {

    /**
     * Id of the TradeDTO.
     */
    private Integer tradeId;

    /**
     * The trade account.
     */
    @NotBlank(message = "Account is mandatory!")
    @Size(max = Constants.LENGTH_30, message = "Max length = 30!")
    private String account;

    /**
     * The trade type.
     */
    @NotBlank(message = "Type is mandatory!")
    @Size(max = Constants.LENGTH_30, message = "Max length = 30!")
    private String type;

    /**
     * The trade buy quantity.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal buyQuantity;

    /**
     * /** The trade sell quantity.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal sellQuantity;

    /**
     * The trade buy price.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal buyPrice;

    /**
     * The trade sell price.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal sellPrice;

}
