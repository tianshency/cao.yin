package com.jce.framework.core.groovy.impl;

import com.jce.framework.core.groovy.IScript;
import org.springframework.stereotype.Component;

/**
 * 系统脚本
 */
@Component
public class ScriptImpl implements IScript {
	public String getCurrentUserId() {
		return "1";
	}
}