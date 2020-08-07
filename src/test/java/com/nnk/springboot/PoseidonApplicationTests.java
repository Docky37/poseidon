package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.UserController;

@SpringBootTest
class PoseidonApplicationTests {
	
	@Autowired
	private UserController controller;

	@Autowired
	private BidListController bidListController;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(bidListController).isNotNull();
	}

}
