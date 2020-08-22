package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Size;

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
@ToString
public class BidListFullDTO extends BidListDTO {

    /**
     * The number of bid to buy.
     */
    @Getter
    @Setter
    private BigDecimal askQuantity;

    /**
     * The bid sell price.
     */
    @Getter
    @Setter
    private BigDecimal bid;

    /**
     * Ask price, that is bid price
     */
    @Getter
    @Setter
    private BigDecimal ask;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String benchmark;

    @Getter
    @Setter
    private LocalDateTime bidListDate;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String commentary;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String security;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String status;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String trader;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String book;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String creationName;

    @Getter
    @Setter
    private LocalDateTime creationDate;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String revisionName;

    @Getter
    @Setter
    private LocalDateTime revisionDate;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealName;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String dealType;

    @Size(max = 125, message = "Max length = 125!")
    @Getter
    @Setter
    private String sourceListId;

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
    public BidListFullDTO(BigDecimal askQuantity, BigDecimal bid,
            BigDecimal ask,
            @Size(max = 125, message = "Max length = 125!") String benchmark,
            LocalDateTime bidListDate,
            @Size(max = 125, message = "Max length = 125!") String commentary,
            @Size(max = 125, message = "Max length = 125!") String security,
            @Size(max = 125, message = "Max length = 125!") String status,
            @Size(max = 125, message = "Max length = 125!") String trader,
            @Size(max = 125, message = "Max length = 125!") String book,
            @Size(max = 125, message = "Max length = 125!") String creationName,
            LocalDateTime creationDate,
            @Size(max = 125, message = "Max length = 125!") String revisionName,
            LocalDateTime revisionDate,
            @Size(max = 125, message = "Max length = 125!") String dealName,
            @Size(max = 125, message = "Max length = 125!") String dealType,
            @Size(max = 125, message = "Max length = 125!") String sourceListId,
            @Size(max = 125, message = "Max length = 125!") String side) {
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
