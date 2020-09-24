package com.nnk.springboot.dto;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Data transfer object for BidList.
 *
 * @author Thierry Schreiner
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(description = "All details of a BidList.")
public class BidListFullDTO extends BidListDTO {

    /**
     * The number of ask auctions.
     */
    @ApiModelProperty(notes = "The number of ask auctions.")
    private BigDecimal askQuantity;

    /**
     * The bid price represents the highest price an investor is willing to pay
     * for a share.
     */
    @ApiModelProperty(notes = "The bid price represents the highest price an "
            + "investor is willing to pay for a share.")
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @ApiModelProperty(notes = "The ask price (offer) represents the lowest"
            + " price at which a shareholder is willing to sell at.")
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    private BigDecimal ask;

    /**
     * Benchmark String variable.
     */
    @ApiModelProperty(notes = "Benchmark String variable.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String benchmark;

    /**
     * Date of the Bidlist.
     */
    @ApiModelProperty(notes = "Date of the Bidlist.")
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    @Past(message = "Must be past!")
    private LocalDateTime bidListDate;

    /**
     * Commentary String variable.
     */
    @ApiModelProperty(notes = "Commentary String variable.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String commentary;

    /**
     * Security String variable.
     */
    @ApiModelProperty(notes = "Security String variable.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String security;

    /**
     * Status of the Bid.
     */
    @ApiModelProperty(notes = "Status String variable.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String status;

    /**
     * Name of the trader.
     */
    @ApiModelProperty(notes = "Name of the trader.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String trader;

    /**
     * Book String variable.
     */
    @ApiModelProperty(notes = "Name of the book")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @ApiModelProperty(notes = "The bidList record creator."
            + " Setted by application.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String creationName;

    /**
     * The date & time when user has created the BidList record.
     */
    @ApiModelProperty(notes = "The BidList creation date."
            + " Setted by application.")
    private LocalDateTime creationDate;

    /**
     * The name of the user who has modified the BidList record.
     */
    @ApiModelProperty(notes = "The name of the user who has modified the"
            + " BidList record. Setted by application.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String revisionName;

    /**
     * The date & time when user has modified the BidList record.
     */
    @ApiModelProperty(notes = "The date & time when user has modified the"
            + " BidList record. Setted by application.")
    private LocalDateTime revisionDate;

    /**
     * The name of the deal.
     */
    @ApiModelProperty(notes = "The name of the deal.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String dealName;

    /**
     * The type of the deal.
     */
    @ApiModelProperty(notes = "The type of the deal.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String dealType;

    /**
     * The id of the source list.
     */
    @ApiModelProperty(notes = "The id of the source list.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @ApiModelProperty(notes = "The side of the bidList.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String side;

}
