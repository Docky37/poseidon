package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
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
@NoArgsConstructor
@ToString
public class BidListFullDTO extends BidListDTO {

    /**
     * The number of ask auctions.
     */
    @Getter
    @Setter
    private BigDecimal askQuantity;

    /**
     * The bid price represents the highest price an investor is willing to pay
     * for a share.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS,
            message = "Must be a number < 1,000,000,000,000 with"
                    + " 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal ask;

    /**
     * Benchmark String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String benchmark;

    /**
     * Date of the Bidlist.
     */
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy/MM/dd' 'HH:mm")
    private LocalDateTime bidListDate;

    /**
     * Commentary String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String commentary;

    /**
     * Security String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String security;

    /**
     * Status String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String status;

    /**
     * Trader String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String trader;

    /**
     * Book String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String creationName;

    /**
     * The date & time when user has created the BidList record.
     */
    @Getter
    @Setter
    private LocalDateTime creationDate;

    /**
     * The name of the user who has modified the BidList record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String revisionName;

    /**
     * The date & time when user has modified the BidList record.
     */
    @Getter
    @Setter
    private LocalDateTime revisionDate;

    /**
     * The name of the deal.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealName;

    /**
     * The type of the deal.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealType;

    /**
     * The id of the source list.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String side;

    /**
     * All Arguments Constructor of the DTO.
     *
     * @param pAskQuantity
     * @param pBid
     * @param pAsk
     * @param pBenchmark
     * @param pBidListDate
     * @param pCommentary
     * @param pSecurity
     * @param pStatus
     * @param pTrader
     * @param pBook
     * @param pCreationName
     * @param pCreationDate
     * @param pRevisionName
     * @param pRevisionDate
     * @param pDealName
     * @param pDealType
     * @param pSourceListId
     * @param pSide
     */
    public BidListFullDTO(final BigDecimal pAskQuantity, final BigDecimal pBid,
            final BigDecimal pAsk,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pBenchmark, final LocalDateTime pBidListDate,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pCommentary,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pSecurity,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pStatus,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pTrader,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pBook,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pCreationName,
            final LocalDateTime pCreationDate,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pRevisionName,
            final LocalDateTime pRevisionDate,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pDealName,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pDealType,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pSourceListId,
            @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
                final String pSide) {
        super();
        askQuantity = pAskQuantity;
        bid = pBid;
        ask = pAsk;
        benchmark = pBenchmark;
        bidListDate = pBidListDate;
        commentary = pCommentary;
        security = pSecurity;
        status = pStatus;
        trader = pTrader;
        book = pBook;
        creationName = pCreationName;
        creationDate = pCreationDate;
        revisionName = pRevisionName;
        revisionDate = pRevisionDate;
        dealName = pDealName;
        dealType = pDealType;
        sourceListId = pSourceListId;
        side = pSide;
    }

}
