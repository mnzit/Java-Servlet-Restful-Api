package com.sudreeshya.day19.dao.impl;

import com.sudreeshya.day19.dao.CourseDao;
import com.sudreeshya.day19.dao.StudentDao;
import com.sudreeshya.day19.model.Course;
import com.sudreeshya.day19.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
@AllArgsConstructor
public class StudentDaoDatabaseImpl implements StudentDao {

    private final CourseDao courseDao;

    @Override
    public List<Student> fetchAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");

            // Create statement 
            stmt = connection.createStatement();

            // Execution
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");

            students = new ArrayList<>();

            while (rs.next()) {
                log.debug("Student id: {} , name: {}", rs.getLong("id"), rs.getString("firstname") + rs.getString("lastname"));
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

                Course course = courseDao.fetchCourseById(courseId);
                student.setCourse(course);
                students.add(student);
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
        return students;
    }

    @Override
    public Student fetchStudentById(Long id) {
        Student student = null;
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");

            // Create statement 
            stmt = connection.createStatement();

            // Execution
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS WHERE id=" + id);

            while (rs.next()) {
                log.debug("Student id: {} , name: {}", rs.getLong("id"), rs.getString("firstname") + rs.getString("lastname"));
                student = new Student();

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

                Course course = courseDao.fetchCourseById(courseId);
                student.setCourse(course);
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
        return student;
    }

    @Override
    public Boolean saveStudent(Student student) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");
            String sql = "INSERT INTO STUDENTS (firstname,lastname,address,contact_no,course_id) VALUES (?,?,?,?,?)";

            // Create statement 
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getFirstname());
            pstmt.setString(2, student.getLastname());
            pstmt.setString(3, student.getAddress());
            pstmt.setString(4, student.getContactNo());
            pstmt.setLong(5, student.getCourse().getId());

            // Execution    
            boolean result = pstmt.execute();

            connection.close();
            pstmt.close();

            return result;

        } catch (ClassNotFoundException | SQLException ex) {
            log.error("Exception : {}", ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    pstmt.close();
                }
            } catch (Exception ex) {
                log.error("Exception : {}", ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public Boolean updateStudent(Student student) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");
            String sql = "UPDATE STUDENTS SET firstname=?,lastname=?,address=?,contact_no=?,course_id=? WHERE id=?";

            // Create statement 
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getFirstname());
            pstmt.setString(2, student.getLastname());
            pstmt.setString(3, student.getAddress());
            pstmt.setString(4, student.getContactNo());
            pstmt.setLong(5, student.getCourse().getId());
            pstmt.setLong(6, student.getId());

            // Execution    
            boolean result = pstmt.execute();

            connection.close();
            pstmt.close();

            return result;

        } catch (ClassNotFoundException | SQLException ex) {
            log.error("Exception : {}", ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    pstmt.close();
                }
            } catch (Exception ex) {
                log.error("Exception : {}", ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public Boolean deleteStudentById(Long id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection established
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCHOOL?useSSL=false", "root", "root");
            String sql = "DELETE FROM STUDENTS WHERE id=?";

            // Create statement 
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            // Execution    
            boolean result = pstmt.execute();

            connection.close();
            pstmt.close();

            return result;

        } catch (ClassNotFoundException | SQLException ex) {
            log.error("Exception : {}", ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    pstmt.close();
                }
            } catch (Exception ex) {
                log.error("Exception : {}", ex.getMessage());
            }
        }
        return false;
    }

}
