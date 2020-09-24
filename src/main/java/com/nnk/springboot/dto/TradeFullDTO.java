package com.nnk.springboot.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.nnk.springboot.constants.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "All details of a Trade.")
public class TradeFullDTO extends TradeDTO {

    /**
     * Date of the Trade.
     */
    @ApiModelProperty(notes = "The Date of the Trade. Must be past!")
    @Past(message = "Must be past!")
    @DateTimeFormat(pattern = "dd/MM/yyyy' 'HH:mm")
    private LocalDateTime tradeDate;

    /**
     * Security String variable.
     */
    @ApiModelProperty(notes = "Security String variable.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String security;

    /**
     * Status String variable.
     */
    @ApiModelProperty(notes = "Status of the trade.")
    @Size(max = Constants.LENGTH_10, message = "Max length = 125!")
    private String status;

    /**
     * Name of the trader.
     */
    @ApiModelProperty(notes = "Name of the trader.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String trader;

    /**
     * Benchmark String variable.
     */
    @ApiModelProperty(notes = "Benchmark of the trader.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String benchmark;

    /**
     * Book String variable.
     */
    @ApiModelProperty(notes = "Book of the trade.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String book;

    /**
     * The name of the user who has created the Trade record.
     */
    @ApiModelProperty(notes = "The name of the user who has created the"
            + " Trade record.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String creationName;

    /**
     * The date & time when user has created the Trade record.
     */
    private LocalDateTime creationDate;

    /**
     * The creation date of the Trade record.
     */
    @ApiModelProperty(notes = "The creation date of the Trade record."
            + " Setted by application")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String revisionName;

    /**
     * The date & time when user has modified the Trade record.
     */
    @ApiModelProperty(notes = "The date & time when user has modified the Trade"
            + " record. Setted by application")
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
     * The side of the trade.
     */
    @ApiModelProperty(notes = "The side of the trade.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String side;

}
