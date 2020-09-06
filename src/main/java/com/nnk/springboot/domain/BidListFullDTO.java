package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
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
@ToString
public class BidListFullDTO extends BidListDTO {

    /**
     * The number of ask auctions.
     */
    private BigDecimal askQuantity;

    /**
     * The bid price represents the highest price an investor is willing to pay
     * for a share.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    private BigDecimal ask;

    /**
     * Benchmark String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String benchmark;

    /**
     * Date of the Bidlist.
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    @Past(message = "Must be past!")
    private LocalDateTime bidListDate;

    /**
     * Commentary String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String commentary;

    /**
     * Security String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String security;

    /**
     * Status String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String status;

    /**
     * Trader String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String trader;

    /**
     * Book String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String creationName;

    /**
     * The date & time when user has created the BidList record.
     */
    private LocalDateTime creationDate;

    /**
     * The name of the user who has modified the BidList record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String revisionName;

    /**
     * The date & time when user has modified the BidList record.
     */
    private LocalDateTime revisionDate;

    /**
     * The name of the deal.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String dealName;

    /**
     * The type of the deal.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String dealType;

    /**
     * The id of the source list.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String side;


}
