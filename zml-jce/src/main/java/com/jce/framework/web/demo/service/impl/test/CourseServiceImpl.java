package com.jce.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.web.demo.entity.test.CourseEntity;
import com.jce.framework.web.demo.entity.test.StudentEntity;
import com.jce.framework.web.demo.service.test.CourseServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends CommonServiceImpl implements CourseServiceI {

	
	public void saveCourse(CourseEntity course) {
		this.save(course.getTeacher());
		this.save(course);
		for (StudentEntity s :course.getStudents()){
			s.setCourse(course);
		}
		this.batchSave(course.getStudents());
		
	}

	
	public void updateCourse(CourseEntity course) {
		this.updateEntitie(course);
		this.updateEntitie(course.getTeacher());
		for (StudentEntity s :course.getStudents()){
            s.setCourse(course);
			this.saveOrUpdate(s);
		}
	}
	
}