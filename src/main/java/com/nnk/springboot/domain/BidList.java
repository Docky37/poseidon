package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nnk.springboot.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is the entity in relation with the table of BidList.
 *
 * @author Thierry Schreiner
 */
@Entity
@Table(name = "bid_list")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidList {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer bidListId;

    /**
     * The BidList account name.
     */
    @NotBlank
    @Column(nullable = false, length = Constants.LENGTH_30)
    @Getter
    @Setter
    private String account;

    /**
     * The BidList type.
     */
    @NotBlank
    @Column(nullable = false, length = Constants.LENGTH_30)
    @Getter
    @Setter
    private String type;

    /**
     * The number of bid auctions.
     */
    @NotNull
    @Digits(fraction = Constants.N4_DIGITS, integer = Constants.N12_DIGITS, message = "Must be a number < 1,000,000,000,000 with"
            + " 4 fractional digits max")
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    @Getter
    @Setter
    private BigDecimal bidQuantity;

    /**
     * The number of ask auctions.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    @Getter
    @Setter
    private BigDecimal askQuantity;

    /**
     * The bid price represents the highest price an investor is willing to pay
     * for a share.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    @Getter
    @Setter
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    @Getter
    @Setter
    private BigDecimal ask;

    /**
     * Benchmark String variable.
     */
    @Column(length = Constants.LENGTH_125)
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
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String commentary;

    /**
     * Security String variable.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String security;

    /**
     * Status String variable.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String status;

    /**
     * Trader String variable.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String trader;

    /**
     * Book String variable.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @Column(length = Constants.LENGTH_125)
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
    @Column(length = Constants.LENGTH_125)
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
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String dealName;

    /**
     * The type of the deal.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String dealType;

    /**
     * The id of the source list.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String side;

}
