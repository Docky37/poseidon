package com.nnk.springboot.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.nnk.springboot.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is the entity in relation with the table of rating.
 *
 * @author Thierry Schreiner
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingDTO {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Getter
    @Setter
    private Integer id;

    /**
     * Rating of Moodys' Credit rating agency.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String moodysRating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String standPoorsRating;

    /**
     * Rating of Fitch rating agency.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    @Getter
    @Setter
    private String fitchRating;

    /**
     * Rating of Moodys' Credit rating agency
     */
    
    @Positive(message = "Order number must be positive!")
    @Getter
    @Setter
    private Integer orderNumber;

}
