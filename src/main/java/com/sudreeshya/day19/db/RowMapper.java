package com.sudreeshya.day19.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public interface RowMapper<T> {

    T map(ResultSet resultSet) throws SQLException;

}
