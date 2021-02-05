package com.sudreeshya.day19.dto;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Data
public class ServerResponse implements Serializable {

    private Boolean status;
    private String resultDescription;

}
