package com.zml.util;

import java.math.BigDecimal;
/**
 * 金额转大写
 * 
 *
 */
public class MoneyUtil {
    public static void main(String agrs[]) {
        //整数
    	System.out.println("----------整数");
    	System.out.println(digitToUppercase(100000));
        System.out.println(digitToUppercase(0));              // 零元整
        System.out.println(digitToUppercase(123));            // 壹佰贰拾叁元整
        System.out.println(digitToUppercase(1000000));        // 壹佰万元整
        System.out.println(digitToUppercase(100000001));      // 壹亿零壹元整
        System.out.println(digitToUppercase(1000000000));     // 壹拾亿元整
        System.out.println(digitToUppercase(1234567890));     // 壹拾贰亿叁仟肆佰伍拾陆万柒仟捌佰玖拾元整
        System.out.println(digitToUppercase(1001100101));     // 壹拾亿零壹佰壹拾万零壹佰零壹元整
        System.out.println(digitToUppercase(110101010));      // 壹亿壹仟零壹拾万壹仟零壹拾元整
     
        //小数
        System.out.println("----------小数");
        System.out.println(digitToUppercase(0.94)); 
        System.out.println(digitToUppercase(0.12));          // 壹角贰分
        System.out.println(digitToUppercase(123.34));        // 壹佰贰拾叁元叁角肆分
        System.out.println(digitToUppercase(1000000.56));    // 壹佰万元伍角陆分
        System.out.println(digitToUppercase(100000001.78));  // 壹亿零壹元柒角捌分
        System.out.println(digitToUppercase(1000000000.90)); // 壹拾亿元玖角
        System.out.println(digitToUppercase(1234567890.03)); // 壹拾贰亿叁仟肆佰伍拾陆万柒仟捌佰玖拾元叁分
        System.out.println(digitToUppercase(1001100101.00)); // 壹拾亿零壹佰壹拾万零壹佰零壹元整
        System.out.println(digitToUppercase(110101010.10));  // 壹亿壹仟零壹拾万壹仟零壹拾元壹角

    }
     
    /**
     * 数字金额大写转换
     * 
     */
    public static String digitToUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
        String head = "";
        if(n<0){
        	head="负";
        	n = Math.abs(n);
        }
        String s = "";
        double f1=0.00;
        BigDecimal b1=new BigDecimal(n).setScale(2,BigDecimal.ROUND_HALF_UP);
        for (int i = 0; i < 2; i++) {
        	f1= b1.multiply(new BigDecimal(10 * Math.pow(10, i))).doubleValue();
        	s += (digit[(int) (Math.floor(f1) % 10)] + fraction[i]).replaceAll("(零.)+", ""); 
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
        for (int i = 0; i < 3 && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < 4 && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        s=head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
        return s;
    }
}