package com.sudreeshya.day19.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudreeshya.day19.StudentServiceWrapper;
import com.sudreeshya.day19.dao.impl.CourseDaoDatabaseImpl;
import com.sudreeshya.day19.dao.impl.StudentDaoDatabaseImpl;
import com.sudreeshya.day19.dto.ServerResponse;
import com.sudreeshya.day19.dto.StudentDTO;
import com.sudreeshya.day19.model.Student;
import com.sudreeshya.day19.service.StudentService;
import com.sudreeshya.day19.service.impl.StudentServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        
        List<Student> students = StudentServiceWrapper
                .getStudentService()
                .getAllStudents();

        log.info("Students : {}", students.size());
        String responseString = objectMapper.writeValueAsString(students);
        response.setContentType("application/json");
        response.setStatus(200);
        response.getWriter().write(responseString);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Fetch the stream of data from the request which in bytes
        ServletInputStream inputStream = request.getInputStream();

        ObjectMapper objectMapper = new ObjectMapper();

        // Maps the stream of data in JSON to StudentDTO class
        StudentDTO studentDTO = objectMapper.readValue(inputStream, StudentDTO.class);

        // Perform save operation
        StudentServiceWrapper
                .getStudentService()
                .saveStudent(studentDTO);

        // Response Object
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(true);
        serverResponse.setResultDescription("Student saved sucessfully");

        // Converts Object to JSON
        String responseString = objectMapper.writeValueAsString(serverResponse);

        // Status code and response body type
        response.setStatus(201);
        response.setContentType("application/json");

        // Writes to the response stream
        PrintWriter writer = response.getWriter();
        writer.write(responseString);
    }
}
