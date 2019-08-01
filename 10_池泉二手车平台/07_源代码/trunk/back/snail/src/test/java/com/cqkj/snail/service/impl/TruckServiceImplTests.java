package com.cqkj.snail.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cqkj.snail.domain.TTruck;
import com.cqkj.snail.service.TruckService;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TruckServiceImplTests{

	@Autowired
	private TruckService truckService;

    @Test
	public void addsTwoNumbers() {
		assertEquals(2, truckService.addTwoNumber(1, 1), "1 + 1 should equal 2");
	}

	
}

