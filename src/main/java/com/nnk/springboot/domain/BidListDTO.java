package com.nnk.springboot.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.stereotype.Component;

/**
 * Data transfer object that contains only 4 attributes of a BidList.
 *
 * @author Thierry Schreiner
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidListDTO {

    /**
     * Id of the DTO.
     */
    @Getter
    @Setter
    private Integer bidListId;

    /**
     * The BidListDTO account name.
     */
    @Getter
    @Setter
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * The BidListDTO type.
     */
    @Getter
    @Setter
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * The number of bid for sale.
     */
    @NotNull(message = "Bid quantity is mandatory")
    @Digits(fraction = 4, integer = 12, message = "Must be a number, "
            + "less than 1,000,000,000,000 max with 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal bidQuantity;

}
