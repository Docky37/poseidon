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
    @Getter
    @Setter
    private String moodysRating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    @Getter
    @Setter
    private String standPoorsRating;

    /**
     * Rating of Fitch rating agency.
     */
    @Getter
    @Setter
    private String fitchRating;

    /**
     * Rating of Moodys' Credit rating agency
     */
    @Getter
    @Setter
    private Integer orderNumber;

}
