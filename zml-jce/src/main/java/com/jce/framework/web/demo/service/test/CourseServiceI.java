package com.jce.framework.web.demo.service.test;

import com.jce.framework.web.demo.entity.test.CourseEntity;

import com.jce.framework.core.common.service.CommonService;

public interface CourseServiceI extends CommonService{

	/**
	 * 保存课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void saveCourse(CourseEntity course);
	/**
	 * 更新课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void updateCourse(CourseEntity course);

}
