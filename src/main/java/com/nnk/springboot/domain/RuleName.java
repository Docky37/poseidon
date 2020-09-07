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
 * This class is the entity in relation with the table of rule_name.
 *
 * @author Thierry Schreiner
 */
@Entity
@Table(name = "rule_name")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuleName {

    /**
     * Id of the entity mapped to primary key field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_125)
    private String name;

    /**
     * Description of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_125)
    private String description;

    /**
     * JavaScript Object Notation (json) of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_125)
    private String json;

    /**
     * Template of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_512)
    private String template;

    /**
     * SQL request of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_125)
    private String sqlStr;

    /**
     * SQL request of the RuleNameDTO instance.
     */
    @Column(length = Constants.LENGTH_125)
    private String sqlPart;

}
