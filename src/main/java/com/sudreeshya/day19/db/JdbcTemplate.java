package com.sudreeshya.day19.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class JdbcTemplate<T> {

    DatabaseHelper db = new DatabaseHelper();

    public <T> List<T> getAll(String sql, RowMapper<T> mapper) throws Exception {
        log.debug("EXECUTING : [{}]", sql);

        db.connect();
        db.init(sql);
        ResultSet resultSet = db.execute();
        List<T> rows = new ArrayList<>();
        while (resultSet.next()) {
            rows.add(mapper.map(resultSet));
        }
        db.close();

        return rows;
    }

    public T getByObject(String sql, Object[] parameters, RowMapper<T> mapper) throws Exception {
        log.debug("EXECUTING : [{}]", sql);

        T result = null;

        db.connect();
        db.init(sql);
        addParameter(db.init(sql), parameters);
        ResultSet resultSet = db.execute();
        while (resultSet.next()) {
            result = mapper.map(resultSet);
        }
        db.close();
        
        return result;
    }

    public List<T> getAllByObject(String sql, Object[] parameters, RowMapper<T> mapper) throws Exception {
        log.debug("EXECUTING : [{}]", sql);
        
        db.connect();
        addParameter(db.init(sql), parameters);
        ResultSet resultSet = db.execute();
        List<T> rows = new ArrayList<>();
        while (resultSet.next()) {
            rows.add(mapper.map(resultSet));
        }
        db.close();

        return rows;
    }

    public int update(String sql, Object[] parameters) throws Exception {
        log.debug("EXECUTING : [{}]", sql);

        db.connect();
        addParameter(db.init(sql), parameters);
        int result = db.update();
        db.close();
        return result;
    }

    public void addParameter(PreparedStatement preparedStatement, Object[] parameters) throws Exception {
        int index = 1;
        for (Object parameter : parameters) {
            preparedStatement.setObject(index++, parameter);
        }
    }

}
