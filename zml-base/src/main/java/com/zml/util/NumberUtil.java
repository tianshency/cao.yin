package com.zml.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 */
public class NumberUtil {
    private static int SCALE = 16;
    public static int SCALE_2 = 2;
    private static NumberFormat nf = new DecimalFormat("0000");

    /**
     * double 相加
     * 
     * @param dd
     * @return
     */
    public static double doubleAdd(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return result.doubleValue();
    }

	/**
	 *
	 * @param dd
	 * @return
	 */
	public static double doubleAddCheckNull(Double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (Double n : dd) {
			if (n == null) {
				n = 0d;
			}
			result = result.add(new BigDecimal("" + n));
		}
		return result.doubleValue();
	}

    public static double doubleAdd(List<Double> dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return result.doubleValue();
    }
    
    public static double doubleAddCheckNull(Map<String,Double> map) {
		BigDecimal result = BigDecimal.ZERO;
		if(map==null)
			return result.doubleValue();
		for (Double n:map.values()) {
			if (n == null) {
				n = 0d;
			}
			result = result.add(new BigDecimal("" + n));
		}
		return result.doubleValue();
	}
    /**
     * double 相加 - 四舍五入
     * 
     * @param dd
     * @return
     */
    public static double doubleAddScaled(double... dd) {
        BigDecimal result = BigDecimal.ZERO;
        for (double n : dd) {
            result = result.add(new BigDecimal("" + n));
        }

        return scale(result);
    }

    /**
     * double 相减
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtract(double d1, double d2) {
        return new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)).setScale(SCALE_2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
	 * double 相减
	 *
	 * @param dd
	 * @return
	 */
	public static double doubleSubtractAndCheckNull(Double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (int i = 0; i < dd.length; i++) {
			if (dd[i] == null) {
				dd[i] = 0d;
			}
			if (i == 0) {
				result = result.add(new BigDecimal("" + dd[i]));
			} else {
				result = result.subtract(new BigDecimal("" + dd[i]));
			}
		}
		return result.doubleValue();
	}

	/**
     * double 相减 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static double doubleSubtractScaled(double d1, double d2) {
        return scale(new BigDecimal("" + d1).subtract(new BigDecimal("" + d2)));
    }

    /**
     * 四舍五入 - RoundingMode.HALF_UP
     * 
     * @param decimal
     * @return
     */
    private static double scale(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 货币形式格式字符串
     * 
     * @param ####,###0.00
     * @param value
     */
    public static String formatNumberString(String format, String value) {
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat(format);
        return df.format(bd);
    }

    /**
     * 相除 取位 d1/d2 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
    public static double doubleDivide(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        BigDecimal bd3 = bd1.divide(bd2, 16, RoundingMode.HALF_UP);
        return bd3.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static double doubleDivide(double d1, double d2) {
        return doubleDivide(d1, d2, SCALE);
    }

    public static double doubleDivideDown(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        BigDecimal bd3 = bd1.divide(bd2, 16, RoundingMode.HALF_UP);
        return bd3.setScale(scale, RoundingMode.DOWN).doubleValue();
    }

    /**
     * 相乘 取位 d1*d2 - 四舍五入
     * 
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
    public static double doubleMultiply(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static double doubleMultiply(double d1, double d2) {
        return doubleMultiply(d1, d2, SCALE);
    }

    /**
     * 相乘 取位 d1*d2 - 四舍五入
     * 
     * @param scale
     * @param dd
     * @return
     */
    public static double doubleMultiplyScale(int scale, double... dd) {
        BigDecimal result = BigDecimal.ONE;
        for (double n : dd) {
            result = result.multiply(new BigDecimal("" + n)).setScale(16, RoundingMode.HALF_UP);
        }
        return result.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 将整数转化为四位数字
     * 
     * @param number
     * @return
     */
    public static String formatFourNumber(Integer number) {
        return nf.format(number);
    }

    /**
     * 获取带千分符并保留两位小数的金额
     * 
     * 
     * @param amount
     * @return
     */
    public static String formatNumber(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount).substring(1);
    }
    /**
     * 获取带千分符并保留两位小数的金额
     * 
     * 
     * @param amount
     * @return
     */
    public static String formatPercent(double amount) {
    	NumberFormat nt=NumberFormat.getPercentInstance();
    	nt.setMinimumFractionDigits(SCALE_2);
        return nt.format(amount);
    }
    /***
     * 取模
     * 
     * 
     * @param d1
     * @param d2
     * @return d1%d2
     */
    public static double doubleMod(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.remainder(bd2).doubleValue();
    }
    
    /**
     * 四舍五入金额 
     *
     * @param amount
     * @return
     */
    public static Double formatDouble2Scale(Double amount) {
		if (amount == null)
			return 0.00d;
		else
			return scale(new BigDecimal(amount));
    }
    
    /**
     * total减去dd的和
     *
     * @param total
     * @param dd
     * @return
     * 
     */
    public static double doubleSubtractMore(double total, double... dd) {
        double add = 0.0d;
        for (double n : dd) {
            add = doubleAdd(add, n);
        }
        return doubleSubtract(total, add);
    }

    /**
     * 比较double的大小
     * @param d1
     * @param d2
     * @return 1 d1>d2;0 d1==d2;-1,d1<d2
     */
    public static int compareD1AndD2(double d1, double d2) {
        return new BigDecimal(d1).compareTo(new BigDecimal(d2));
    }
    
    /**
     * 获取一定范围内的随机整数 如40~50 包含40和50
     * @param start
     * @param end
     * @return 
     */
    public static int getRandomValue(int start, int end) {
    	Random rand = new Random();
    	//生成start--end 之间的随机数，包括start和end
    	int randVal = rand.nextInt(end-start+1)+start;
    	return randVal;
    }
    
    
    /**
     * 判断金额是不是零
     * @param d
     * @return
     *
     */
    public static boolean checkZeao(Double d){
    	if (null==d){
    		d=0.00;
    	}
    	String str = d +"";
    	Pattern pattern = Pattern.compile("[0 .]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
    }
    
    /**一个数乘以一个数再除以一个数
     * @param amount
     * @return
     */
    public static Double doubleMultAndDiv(Double amount,Double d1 ,Double d2) {
        return  doubleDivide(doubleMultiply(amount, d1, 5), d2, 2);
    }

    
    /**一个数乘以一个数再除以一个数
     * @param amount
     * @return
     */
    public static Double doubleMultAndDiv2(Double amount,Double d1 ,Double d2) {
        return  cutDouble(doubleDivide(doubleMultiply(amount, d1, 5), d2, 5),2);
    }
    
    
    /**小double保留两位小数
     * @param amount
     * @return
     */
    public static Double doubleGet2Dot(Double d) {
    	java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
        return  Double.valueOf(df.format(d));
    
    }   
    
    /**
     * 不四舍五入，保留小数点后几位
     * @param d
     * @param n
     * @return
     */
    public static Double cutDouble(Double d, int n){
    	d = doubleAdd(d,0.000000001);
    	BigDecimal bg=new BigDecimal(d);
    	return bg.setScale(n, RoundingMode.FLOOR).doubleValue();
	 }
    
    public static Long[] getTicketIds(String ticketIds){
    	if(StringUtils.isEmpty(ticketIds)){
    		return new Long[0];
    	}
    	String str[] = ticketIds.split(",");
    	Long ids[] = new Long[str.length];
    	for (int i = 0; i < str.length; i++) {
			ids[i]=Long.valueOf(str[i]);
			//System.out.println(ids[i]);
		}
    	
    	return ids;
    }
    
    /**
	 * 计算加息券收益
	 * 
	 * @param investAmt
	 * @param addRate
	 * @param addDays
	 * @return
	 */
	public static double calculateAddInterestAmt(double investAmt, double addRate,
			double addDays) {
		double amt=0.00;
		try{
			amt = NumberUtil.doubleMultiply(investAmt, addRate);
			amt = NumberUtil.doubleMultiply(amt, addDays);
			amt = NumberUtil.doubleDivide(amt, 360, 2);
		}catch(Exception ex){
			ex.printStackTrace();
			amt=0.00;
		}
		return amt;
	}
    
    
    
    public static void main(String[] args) {
    	//System.out.println(doubleMultAndDiv(11111200000.222246,5000.00,9000000.11100));
		//System.out.println(doubleGet2Dot(doubleMultAndDiv(11111200000.222246,5000.00,9000000.11100)));
    	System.out.println(calculateAddInterestAmt(1000,0.08,7));
    	
    	//NumberUtil.doubleGet2Dot()
    	System.out.println(
    			new StringBuilder("22").append(",").append(122).toString());
		//System.out.println( NumberUtil.doubleSubtract(0.00,1.00));
    	//System.out.println(cutDouble(3.14,2));
    	double amt1 = doubleSubtractAndCheckNull(100.00, 30.00, 10.00);
    	System.out.println("amt1==" + amt1);
    	
	}
   
}
