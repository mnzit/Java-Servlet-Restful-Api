package com.sudreeshya.day19.dao;

import com.sudreeshya.day19.model.Student;
import java.util.List;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public interface StudentDao {

    List<Student> fetchAllStudents();

    Student fetchStudentById(Long id);
    
    Boolean saveStudent(Student student);
    
    Boolean updateStudent(Student student);
    
    Boolean deleteStudentById(Long id);
    

}
