package com.nnk.springboot.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.nnk.springboot.constants.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data transfer object for Rating.
 *
 * @author Thierry Schreiner
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Details of a Rating.")
public class RatingDTO {

    /**
     * Id of the RatingDTO.
     */
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * Rating of Moodys' Credit rating agency.
     */
    @ApiModelProperty(notes = "Rating of Moodys' Credit rating agency.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String moodysRating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    @ApiModelProperty(notes = "Rating of Standard&Poor's Credit rating agency.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String standPoorsRating;

    /**
     * Rating of Fitch's rating agency.
     */
    @ApiModelProperty(notes = "Rating of Fitch's Credit rating agency.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String fitchRating;

    /**
     * Rating of Moodys' Credit rating agency.
     */
    @ApiModelProperty(notes = "Order number of rating. Must be positive")
    @Positive(message = "Order number must be positive!")
    private Integer orderNumber;

}
