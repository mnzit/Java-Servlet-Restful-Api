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
public class Course {

    private Long id;
    private String name;
    private Date createdDate;

    public Course(Long id) {
        this.id = id;
    }
    
    
}
