package com.nnk.springboot.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

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
@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeFullDTO extends TradeDTO {

    /**
     * Date of the Trade.
     */
    @Past(message = "Must be past!")
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    private LocalDateTime tradeDate;

    /**
     * Security String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String security;

    /**
     * Status String variable.
     */
    @Size(max = Constants.LENGTH_10, message = "Max length = 125!")
    private String status;

    /**
     * Trader String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String trader;

    /**
     * Trader String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String benchmark;

    /**
     * Book String variable.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String book;

    /**
     * The name of the user who has created the Trade record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String creationName;

    /**
     * The date & time when user has created the Trade record.
     */
    private LocalDateTime creationDate;

    /**
     * The name of the user who has modified the Trade record.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String revisionName;

    /**
     * The date & time when user has modified the Trade record.
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
     * The side of the trade.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String side;

}
