package com.nnk.springboot.dto;

import javax.validation.constraints.Size;

import com.nnk.springboot.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data transfer object for RuleName.
 *
 * @author Thierry Schreiner
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuleNameDTO {

    /**
     * Id of the RuleNameDTO instance.
     */
    private Integer id;

    /**
     * Name of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String name;

    /**
     * Description of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String description;

    /**
     * JavaScript Object Notation (json) of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String json;

    /**
     * Template of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_512, message = "Max length = 512!")
    private String template;

    /**
     * SQL request of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sqlStr;

    /**
     * SQL request of the RuleNameDTO instance.
     */
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sqlPart;

}
