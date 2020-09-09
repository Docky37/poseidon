package com.nnk.springboot.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nnk.springboot.constants.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is the entity in relation with the table of trade.
 *
 * @author Thierry Schreiner
 */
@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trade {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    /**
     * The trade account.
     */
    @Column(length = Constants.LENGTH_30)
    private String account;

    /**
     * The trade type.
     */
    @Column(length = Constants.LENGTH_30)
    private String type;

    /**
     * The trade buy quantity.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    private BigDecimal buyQuantity;

    /**
     * /** The trade sell quantity.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    private BigDecimal sellQuantity;

    /**
     * The trade buy price.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    private BigDecimal buyPrice;

    /**
     * The trade sell price.
     */
    @Column(columnDefinition = "DECIMAL(" + Constants.N16_DIGITS + ", "
            + Constants.N4_DIGITS + ")")
    private BigDecimal sellPrice;

    /**
     * Date of the Trade.
     */
    private LocalDateTime tradeDate;
    /**
     * Security String variable.
     */
    @Column(length = Constants.LENGTH_125)
    private String security;

    /**
     * Status String variable.
     */
    @Column(length = Constants.LENGTH_10)
    private String status;

    /**
     * Trader String variable.
     */
    @Column(length = Constants.LENGTH_125)
    private String trader;

    /**
     * Trader String variable.
     */
    @Column(length = Constants.LENGTH_125)
    private String benchmark;

    /**
     * Book String variable.
     */
    @Column(length = Constants.LENGTH_125)
    private String book;

    /**
     * The name of the user who has created the BidList record.
     */
    @Column(length = Constants.LENGTH_125)
    private String creationName;

    /**
     * The date & time when user has created the BidList record.
     */
    private LocalDateTime creationDate;

    /**
     * The name of the user who has modified the trade record.
     */
    @Column(length = Constants.LENGTH_125)
    private String revisionName;

    /**
     * The date & time when user has modified the BidList record.
     */
    private LocalDateTime revisionDate;

    /**
     * The name of the deal.
     */
    @Column(length = Constants.LENGTH_125)
    private String dealName;

    /**
     * The type of the deal.
     */
    @Column(length = Constants.LENGTH_125)
    private String dealType;

    /**
     * The id of the source list.
     */
    @Column(length = Constants.LENGTH_125)
    private String sourceListId;

    /**
     * The side of the bidList.
     */
    @Column(length = Constants.LENGTH_125)
    private String side;

}
