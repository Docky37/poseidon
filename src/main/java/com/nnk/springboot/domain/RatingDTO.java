package com.nnk.springboot.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String moodys_rating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    private String sand_p_rating;

    /**
     * Rating of Fitch rating agency.
     */
    private String fitch_rating;

    /**
     * Rating of Moodys' Credit rating agency
     */
    private Integer order_number;

}
