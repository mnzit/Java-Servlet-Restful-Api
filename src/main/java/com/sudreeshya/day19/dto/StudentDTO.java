package com.sudreeshya.day19.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable{

    private String firstname;
    private String lastname;
    private String contactNo;
    private String address;
    private Long courseId;
    
}
