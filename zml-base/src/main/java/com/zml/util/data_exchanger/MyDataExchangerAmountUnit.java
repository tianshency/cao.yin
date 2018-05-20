package com.zml.util.data_exchanger;
import com.zml.util.IMyDataExchanger;
//规格的数据变换实体
public class MyDataExchangerAmountUnit implements IMyDataExchanger {
	public Object exchange(Object value) {
		if (value == null) {
			return "";
		} else if ("1".equals(value.toString())) {
			return "袋";
		} else if ("2".equals(value.toString())) {
			return "箱";
		}else if ("3".equals(value.toString())) {
			return "瓶";
		}else if ("4".equals(value.toString())) {
			return "桶";
		}else if ("5".equals(value.toString())) {
			return "台";
		}else {
			return "个";
		}

	}
}