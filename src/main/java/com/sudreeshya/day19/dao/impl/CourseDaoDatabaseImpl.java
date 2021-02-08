package com.sudreeshya.day19.dao.impl;

import com.sudreeshya.day19.dao.CourseDao;
import com.sudreeshya.day19.db.JdbcTemplate;
import com.sudreeshya.day19.db.RowMapper;
import com.sudreeshya.day19.model.Course;
import com.sudreeshya.day19.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class CourseDaoDatabaseImpl implements CourseDao {

    private JdbcTemplate<Course> jdbcTemplate = new JdbcTemplate<>();

    public CourseDaoDatabaseImpl() {
    }

    @Override
    public List<Course> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course fetchById(Long id) {
        Course course = null;
        try {
            String sql = "SELECT * FROM COURSES WHERE id=?";
            course = jdbcTemplate.getByObject(sql, new Object[]{id}, new CourseMapper());
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        return course;
    }

    @Override
    public Boolean save(Course student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(Course student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class CourseMapper implements RowMapper<Course> {

        @Override
        public Course map(ResultSet rs) throws SQLException {

            Course course = new Course();

            Long courseId = rs.getLong("id");
            String courseName = rs.getString("name");
            Date createdDate = rs.getDate("created_date");
            course.setId(courseId);
            course.setName(courseName);
            course.setCreatedDate(createdDate);

            return course;
        }
    }
}
