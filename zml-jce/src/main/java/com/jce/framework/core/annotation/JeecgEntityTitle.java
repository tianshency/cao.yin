package com.jce.framework.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
* @author  bobo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JeecgEntityTitle {
	  String name();
}
