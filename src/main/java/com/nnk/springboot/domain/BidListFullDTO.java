package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

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
    @Digits(fraction = 4, integer = 12, message = "Must be a number, "
            + "less than 1,000,000,000,000 max with 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @Digits(fraction = 4, integer = 12, message = "Must be a number, "
            + "less than 1,000,000,000,000 max with 4 fractional digits max")
    @Getter
    @Setter
    private BigDecimal ask;

    /**
     * Benchmark String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String benchmark;

    /**
     * Date of the Bidlist.
     */
    @Getter
    @Setter
    private LocalDateTime bidListDate;

    /**
     * Commentary String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String commentary;

    /**
     * Security String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String security;

    /**
     * Status String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String status;

    /**
     * Trader String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String trader;

    /**
     * Book String variable.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @Size(max = 125, message = "Max length = 125!")
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
    @Size(max = 125, message = "Max length = 125!")
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
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealName;

    /**
     * The type of the deal.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealType;

    /**
     * The id of the source list.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String side;

    /**
     * All Args Constructor of the DTO.
     *
     * @param askQuantity
     * @param bid
     * @param ask
     * @param benchmark
     * @param bidListDate
     * @param commentary
     * @param security
     * @param status
     * @param trader
     * @param book
     * @param creationName
     * @param creationDate
     * @param revisionName
     * @param revisionDate
     * @param dealName
     * @param dealType
     * @param sourceListId
     * @param side
     */
    public BidListFullDTO(final BigDecimal askQuantity, final BigDecimal bid,
            final BigDecimal ask,
            @Size(max = 125, message = "Max length = 125!")
                final String benchmark, LocalDateTime bidListDate,
            @Size(max = 125, message = "Max length = 125!")
                final String commentary,
            @Size(max = 125, message = "Max length = 125!")
                final String security,
            @Size(max = 125, message = "Max length = 125!")
                final String status,
            @Size(max = 125, message = "Max length = 125!")
                final String trader,
            @Size(max = 125, message = "Max length = 125!")
                final String book,
            @Size(max = 125, message = "Max length = 125!")
                final String creationName,
            LocalDateTime creationDate,
            @Size(max = 125, message = "Max length = 125!")
                final String revisionName,
            LocalDateTime revisionDate,
            @Size(max = 125, message = "Max length = 125!")
                final String dealName,
            @Size(max = 125, message = "Max length = 125!")
                final String dealType,
            @Size(max = 125, message = "Max length = 125!")
                final String sourceListId,
            @Size(max = 125, message = "Max length = 125!")
                final String side) {
        super();
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = bidListDate;
        this.commentary = commentary;
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }

}
