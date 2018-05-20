package com.zml.util.data_exchanger;

import com.zml.util.IMyDataExchanger;

public class MyDataExchangerGrade  implements IMyDataExchanger {
	public Object exchange(Object value) {
		if (value == null) {
			return "";
		} else if ("1".equals(value.toString())) {
			return "*";
		} else if ("2".equals(value.toString())) {
			return "**";
		}else if ("3".equals(value.toString())) {
			return "***";
		}else if ("4".equals(value.toString())) {
			return "****";
		}else if ("5".equals(value.toString())) {
			return "*****";
		}else {
			return "X";
		}

	}
}
