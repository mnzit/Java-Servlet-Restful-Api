package com.sudreeshya.day19.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private Long id;
    private String firstname;
    private String lastname;
    private String contactNo;
    private String address;
    private Course course;
    private Date createdDate;
    
}
