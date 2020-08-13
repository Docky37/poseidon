package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

	@Getter
	@Setter
	Double askQuantity;

	@Getter
	@Setter
	Double bid;

	@Getter
	@Setter
	Double ask;

	@Getter
	@Setter
	String benchmark;

	@Getter
	@Setter
	LocalDateTime bidListDate;

	@Getter
	@Setter
	String commentary;

	@Getter
	@Setter
	String security;

	@Getter
	@Setter
	String status;

	@Getter
	@Setter
	String trader;

	@Getter
	@Setter
	String book;

	@Getter
	@Setter
	String creationName;

	@Getter
	@Setter
	LocalDateTime creationDate;

	@Getter
	@Setter
	String revisionName;

	@Getter
	@Setter
	LocalDateTime revisionDate;

	@Column(length = 125)
	@Getter
	@Setter
	String dealName;

	@Getter
	@Setter
	String dealType;

	@Getter
	@Setter
	String sourceListId;

	@Getter
	@Setter
	String side;

}
