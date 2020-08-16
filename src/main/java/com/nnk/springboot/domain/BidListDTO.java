package com.nnk.springboot.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Thierry Schreiner
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidListDTO {

	/**
	 * Id of the DTO.
	 */
	@Getter
	@Setter
	Integer bidListId;

	@Getter
	@Setter
	@NotBlank(message = "Account is mandatory")
	String account;

	@Getter
	@Setter
	@NotBlank(message = "Type is mandatory")
	String type;

	@NotNull(message = "Bid quantity is mandatory")
	@Digits(fraction = 4, integer = 6, message = "Must be a number, 999999 max with 4 fractional digits max")
	@Getter
	@Setter
	@Digits(fraction = 4, integer = 6, message = "Must be a number, 999999 max with 4 fractional digits max")
	Double bidQuantity;
}
