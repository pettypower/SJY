package com.cqkj.snail.utils;

import com.cqkj.snail.common.constant.TruckConstants;

import org.springframework.util.StringUtils;

public class TruckUtils {
	/*
	 * 验证接受到的报价参数格式是否是报价数据字典的数据格式 数字+“-”+数字 : 10-15
	 *
	 */
	public static boolean checkPriceFormat(String price) {
		// 参数为空的场合
		if (StringUtils.isEmpty(price)) {
			// 返回false
			return true;
		}
		// 参数不包括“-”的场合
		if (!price.contains("-")) {
			// 返回false
			return false;
		}
		String[] priceLst = price.split("-");
		if (priceLst != null && priceLst.length == 2) {
			// 第一个变量
			String priceFristStr = priceLst[0].trim();
			// 第二换个变量
			String priceSecondStr = priceLst[1].trim();
			// 如果第一个变量不是整数的场合
			if (!CommonUtils.isNumeric(priceFristStr)) {
				// 返回false
				return false;
			}

			// 第二个变量不是整数的场合
			if (!CommonUtils.isNumeric(priceSecondStr)) {
				// 第二个变量不是常量NO_LIMIT的场合
				if (!TruckConstants.NO_LIMIT.equals(priceSecondStr)) {
					return false;
				}
			}
		}
		// 长度不为2的场合
		else {
			// 返回false
			return false;
		}

		return true;
	}	
}
