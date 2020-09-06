package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rating {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Rating of Moodys' Credit rating agency.
     */
    @Column(length = Constants.LENGTH_125)
    private String moodysRating;

    /**
     * Rating of Standard & Poor's Credit rating agency.
     */
    @Column(length = Constants.LENGTH_125)
    private String standPoorsRating;

    /**
     * Rating of Fitch rating agency.
     */
    @Column(length = Constants.LENGTH_125)
    private String fitchRating;

    /**
     * Order number.
     */
    private Integer orderNumber;

}
