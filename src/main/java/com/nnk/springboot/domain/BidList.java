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
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

/**
 * 
 * @author docky
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
	Integer bidListId;

	@NotBlank
	@Column(nullable = false, length = 30)
	String account;

	@NotBlank
	@Column(nullable = false, length = 30)
	String type;

	Double bidQuantity;
	Double askQuantity;
	Double bid;
	Double ask;

	@Column(length = 125)
	String benchmark;

	LocalDateTime bidListDate;

	@Column(length = 125)
	String commentary;
	@Column(length = 125)
	String security;
	@Column(length = 125)
	String status;
	@Column(length = 125)
	String trader;
	@Column(length = 125)
	String book;
	@Column(length = 125)
	String creationName;

	LocalDateTime creationDate;
	@Column(length = 125)
	String revisionName;

	LocalDateTime revisionDate;
	@Column(length = 125)
	String dealName;
	@Column(length = 125)
	String dealType;
	@Column(length = 125)
	String sourceListId;
	@Column(length = 125)
	String side;

}
