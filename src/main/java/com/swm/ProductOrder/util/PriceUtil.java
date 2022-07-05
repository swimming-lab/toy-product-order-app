package com.swm.ProductOrder.util;

import java.text.DecimalFormat;

public class PriceUtil {
    /**
     * 한화 콤마 추가
     * @param price
     * @return
     */
    public static String setComma(Long price) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price);
    }
}
