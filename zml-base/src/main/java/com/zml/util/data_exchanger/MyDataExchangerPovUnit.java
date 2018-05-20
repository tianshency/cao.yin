package com.zml.util.data_exchanger;

import com.zml.util.IMyDataExchanger;
//期限单位的数据变换实体
public class MyDataExchangerPovUnit implements IMyDataExchanger {
	public Object exchange(Object value) {
		if (value == null) {
			return "";
		} else if ("1".equals(value.toString())) {
			return "年";
		} else if ("2".equals(value.toString())) {
			return "月";
		}else {
			return "日";
		}

	}
}