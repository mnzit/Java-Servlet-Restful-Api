package com.sudreeshya.day19.dao.impl;

import com.sudreeshya.day19.dao.CourseDao;
import com.sudreeshya.day19.dao.StudentDao;
import com.sudreeshya.day19.db.JdbcTemplate;
import com.sudreeshya.day19.db.RowMapper;
import com.sudreeshya.day19.model.Course;
import com.sudreeshya.day19.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
@AllArgsConstructor
public class StudentDaoDatabaseImpl implements StudentDao {

    private JdbcTemplate<Student> jdbcTemplate = new JdbcTemplate<>();

    private final CourseDao courseDao;

    public StudentDaoDatabaseImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Student> fetchAll() {
        List<Student> students = null;
        String query = "SELECT * FROM STUDENTS";
        try {

            jdbcTemplate.getAll(query, new StudentMapper());
        } catch (Exception ex) {
            Logger.getLogger(StudentDaoDatabaseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public Student fetchById(Long id) {
        Student student = null;
        try {
            String sql = "SELECT * FROM STUDENTS WHERE id=?";
            student = jdbcTemplate.getByObject(sql, new Object[]{id}, new StudentMapper());
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        return student;
    }

    @Override
    public Boolean save(Student student) {
        try {
            String sql = "INSERT INTO STUDENTS (firstname,lastname,address,contact_no,course_id) VALUES (?,?,?,?,?)";
            int result = jdbcTemplate.update(sql,
                    new Object[]{
                        student.getFirstname(),
                        student.getLastname(),
                        student.getAddress(),
                        student.getContactNo(),
                        student.getCourse().getId()
                    });

            return result > 0;
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean update(Student student) {

        try {
            String sql = "UPDATE STUDENTS SET firstname=?,lastname=?,address=?,contact_no=?,course_id=? WHERE id=?";
            int result = jdbcTemplate.update(sql,
                    new Object[]{
                        student.getFirstname(),
                        student.getLastname(),
                        student.getAddress(),
                        student.getContactNo(),
                        student.getCourse().getId(),
                        student.getId()
                    });
            return result > 0;
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            String sql = "DELETE FROM STUDENTS WHERE id=?";
            int result = jdbcTemplate.update(
                    sql,
                    new Object[]{id}
            );
            return result > 0;
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        return false;
    }

    private class StudentMapper implements RowMapper<Student> {

        @Override
        public Student map(ResultSet rs) throws SQLException {
            Student student = new Student();

            Long studentId = rs.getLong("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String address = rs.getString("address");
            String contactNo = rs.getString("contact_no");
            Date createdDate = rs.getDate("created_date");
            Long courseId = rs.getLong("course_id");

            student.setId(studentId);
            student.setFirstname(firstname);
            student.setLastname(lastname);
            student.setContactNo(contactNo);
            student.setAddress(address);
            student.setCreatedDate(createdDate);

            Course course = courseDao.fetchById(courseId);
            student.setCourse(course);
            return student;
        }
    }
}
