package com.cqkj.snail.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TruckUtilsTests {
	/*
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */
	@Test
	public void checkPriceFormat() {
		String price = "0-NO_LIMIT";
		assertEquals(true, TruckUtils.checkPriceFormat(price), price +"格式正常。");
	}

}
