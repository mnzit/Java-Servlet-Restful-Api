package com.sudreeshya.day19.dao.impl;

import com.sudreeshya.day19.dao.CourseDao;
import com.sudreeshya.day19.model.Course;
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

    @Override
    public List<Course> fetchAllCourses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course fetchCourseById(Long id) {
        Course course = null;
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");

            // Create statement 
            stmt = connection.createStatement();

            // Execution
            ResultSet rs = stmt.executeQuery("SELECT * FROM COURSES WHERE id = " + id);

            while (rs.next()) {
                log.debug("Course id: {} , name: {}", rs.getLong("id"), rs.getString("name"));
                course = new Course();

                Long courseId = rs.getLong("id");
                String courseName = rs.getString("name");
                Date createdDate = rs.getDate("created_date");
                course.setId(courseId);
                course.setName(courseName);
                course.setCreatedDate(createdDate);
            }

            connection.close();
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            log.error("Exception : {}", ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    stmt.close();
                }
            } catch (Exception ex) {
                log.error("Exception : {}", ex.getMessage());
            }
        }
        return course;
    }

}
