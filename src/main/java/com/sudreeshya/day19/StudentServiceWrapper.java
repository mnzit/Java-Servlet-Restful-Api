package com.sudreeshya.day19;

import com.sudreeshya.day19.dao.impl.CourseDaoDatabaseImpl;
import com.sudreeshya.day19.dao.impl.StudentDaoDatabaseImpl;
import com.sudreeshya.day19.service.StudentService;
import com.sudreeshya.day19.service.impl.StudentServiceImpl;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class StudentServiceWrapper {

    private static StudentService studentService;

    private StudentServiceWrapper() {
    }

    public static StudentService getStudentService() {

        if (studentService == null) {
            synchronized (StudentServiceWrapper.class) {
                studentService = new StudentServiceImpl(
                        new StudentDaoDatabaseImpl(new CourseDaoDatabaseImpl()));
            }
        }
        return studentService;
    }
}
