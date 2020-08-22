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
    @Column(nullable = false, length = 30)
    @Getter
    @Setter
    private String account;

    /**
     * The BidList type.
     */
    @NotBlank
    @Column(nullable = false, length = 30)
    @Getter
    @Setter
    private String type;

    /**
     * The number of bid auctions.
     */
    @NotNull
    @Digits(fraction = 12, integer = 6, message = "Must be a number, 999999 max with 4 fractional digits max")
    @Column(columnDefinition = "DECIMAL(16,4)")
    @Getter
    @Setter
    private BigDecimal bidQuantity;

    /**
     * The number of ask auctions.
     */
    @Column(columnDefinition = "DECIMAL(16,4)")
    @Getter
    @Setter
    private BigDecimal askQuantity;

    /**
     * The bid price represents the highest price an investor is willing to pay
     * for a share.
     */
    @Column(columnDefinition = "DECIMAL(16,4)")
    @Getter
    @Setter
    private BigDecimal bid;

    /**
     * The ask price (offer) represents the lowest price at which a shareholder
     * is willing to sell at.
     */
    @Column(columnDefinition = "DECIMAL(16,4)")
    @Getter
    @Setter
    private BigDecimal ask;

    @Column(length = 125)
    @Getter
    @Setter
    private String benchmark;

    @Getter
    @Setter
    private LocalDateTime bidListDate;

    @Column(length = 125)
    @Getter
    @Setter
    private String commentary;

    @Column(length = 125)
    @Getter
    @Setter
    private String security;

    @Column(length = 125)
    @Getter
    @Setter
    private String status;

    @Column(length = 125)
    @Getter
    @Setter
    private String trader;

    @Column(length = 125)
    @Getter
    @Setter
    private String book;

    @Column(length = 125)
    @Getter
    @Setter
    private String creationName;

    @Getter
    @Setter
    private LocalDateTime creationDate;

    @Column(length = 125)
    @Getter
    @Setter
    private String revisionName;

    @Getter
    @Setter
    private LocalDateTime revisionDate;

    @Column(length = 125)
    @Getter
    @Setter
    private String dealName;

    @Column(length = 125)
    @Getter
    @Setter
    private String dealType;

    @Column(length = 125)
    @Getter
    @Setter
    private String sourceListId;

    @Column(length = 125)
    @Getter
    @Setter
    private String side;

}
