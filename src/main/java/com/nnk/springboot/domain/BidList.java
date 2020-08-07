package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Required;

/**
 * 
 * @author Thierry Schreiner
 */
@Entity
@Table(name = "bid_list")
@NoArgsConstructor
@AllArgsConstructor
public class BidList {

	/**
	 * Id of the entity mapped to primary key field.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	Integer bidListId;

	@NotBlank
	@Column(nullable = false, length = 30)
	@Getter
	@Setter
	String account;

	@NotBlank
	@Column(nullable = false, length = 30)
	@Getter
	@Setter
	String type;

	@Getter
	@Setter
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

	@Column(length = 125)
	@Getter
	@Setter
	String benchmark;

	@Getter
	@Setter
	LocalDateTime bidListDate;

	@Column(length = 125)
	@Getter
	@Setter
	String commentary;

	@Column(length = 125)
	@Getter
	@Setter
	String security;

	@Column(length = 125)
	@Getter
	@Setter
	String status;

	@Column(length = 125)
	@Getter
	@Setter
	String trader;

	@Column(length = 125)
	@Getter
	@Setter
	String book;

	@Column(length = 125)
	@Getter
	@Setter
	String creationName;

	@Getter
	@Setter
	LocalDateTime creationDate;

	@Column(length = 125)
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

	@Column(length = 125)
	@Getter
	@Setter
	String dealType;

	@Column(length = 125)
	@Getter
	@Setter
	String sourceListId;

	@Column(length = 125)
	@Getter
	@Setter
	String side;

}
