package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rating {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    /**
     * Rating of Moodys' Credit rating agency.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String moodys_rating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    @Column(length = Constants.LENGTH_125)
    @Getter
    @Setter
    private String sand_p_rating;

    /**
     * Rating of Fitch rating agency.
     */
    @Getter
    @Setter
    @Column(length = Constants.LENGTH_125)
    private String fitch_rating;

    /**
     * Rating of Moodys' Credit rating agency
     */
    @Getter
    @Setter
    private Integer order_number;

}
