package com.sudreeshya.day19.service.impl;

import com.sudreeshya.day19.dao.StudentDao;
import com.sudreeshya.day19.dto.StudentDTO;
import com.sudreeshya.day19.exception.DataNotFoundException;
import com.sudreeshya.day19.exception.SaveUpdateFailureException;
import com.sudreeshya.day19.model.Course;
import com.sudreeshya.day19.model.Student;
import com.sudreeshya.day19.service.StudentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.fetchAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.fetchById(id);
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {

        Student student = new Student();

        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setContactNo(studentDTO.getContactNo());
        student.setAddress(studentDTO.getAddress());
        student.setCourse(new Course(studentDTO.getCourseId()));

        if (!studentDao.save(student)) {
            throw new SaveUpdateFailureException("Student save failed!");
        }

    }

    @Override
    public void updateStudent(StudentDTO studentDTO, Long id) {

        // Reads old data from database;
        Student studentFromDB = studentDao.fetchById(id);

        if (studentDTO == null) {
            throw new DataNotFoundException("Student not found with id=" + id);
        } else {
            // Inserts new value if only the dto object has data
            if (studentDTO.getFirstname() != null) {
                studentFromDB.setFirstname(studentDTO.getFirstname());
            }
            if (studentDTO.getLastname() != null) {
                studentFromDB.setLastname(studentDTO.getLastname());
            }
            if (studentDTO.getAddress() != null) {
                studentFromDB.setAddress(studentDTO.getAddress());
            }
            if (studentDTO.getContactNo() != null) {
                studentFromDB.setContactNo(studentDTO.getContactNo());
            }
            if (studentDTO.getCourseId() != null) {
                studentFromDB.setCourse(new Course(studentDTO.getCourseId()));
            }

            if (studentDao.update(studentFromDB)) {
                throw new SaveUpdateFailureException("Student update failed!");
            }
        }

    }

    @Override
    public void deleteStudent(Long id) {
        log.info("studentDao.deleteStudentById(id) : {}", studentDao.deleteById(id));
    }
}
