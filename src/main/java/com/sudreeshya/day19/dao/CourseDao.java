package com.sudreeshya.day19.dao;

import com.sudreeshya.day19.model.Course;
import java.util.List;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public interface CourseDao {

    List<Course> fetchAllCourses();

    Course fetchCourseById(Long id);

}
