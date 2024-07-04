package com.ManageDrink.until.PriceUtils;

import com.ManageDrink.until.constant.CommonConstant;

public class PriceUtils {
    public static int roundPrice(int price) {
        if (price < CommonConstant.ZERO)
            return CommonConstant.ZERO;
        return (price / CommonConstant.ONE_THOUSAND) * CommonConstant.ONE_THOUSAND;
    }
}
