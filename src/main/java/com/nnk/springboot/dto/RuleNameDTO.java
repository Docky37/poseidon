package com.nnk.springboot.dto;

import javax.validation.constraints.NotNull;
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
 * Data transfer object for RuleName.
 *
 * @author Thierry Schreiner
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "Details of a RuleName.")
public class RuleNameDTO {

    /**
     * Id of the RuleNameDTO instance.
     */
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * Name of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "Name of the Rule.", required = true)
    @NotNull(message = "Name of the Rule mandatory!")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String name;

    /**
     * Description of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "Description of the Rule.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String description;

    /**
     * JavaScript Object Notation (json) of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "JavaScript Object Notation (json) of the Rule.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String json;

    /**
     * Template of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "Template of the Rule.")
    @Size(max = Constants.LENGTH_512, message = "Max length = 512!")
    private String template;

    /**
     * SQL request of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "SQL request of the Rule.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sqlStr;

    /**
     * SQL part of the RuleNameDTO instance.
     */
    @ApiModelProperty(notes = "SQL part of the Rule.")
    @Size(max = Constants.LENGTH_125, message = "Max length = 125!")
    private String sqlPart;

}
